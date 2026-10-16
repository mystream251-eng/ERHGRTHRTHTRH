package androidx.leanback.widget;

import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Log;
import android.util.SparseArray;
import android.view.FocusFinder;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class CollectionLayoutManager extends LinearLayoutManager {
    private static final int NEXT_ITEM = 1;
    private static final int NEXT_ROW = 3;
    private static final int PREV_ITEM = 0;
    private static final int PREV_ROW = 2;
    private static final String TAG = "CollectionLayoutManager";
    private List<Rect> mChildPositions;
    private int mFirstVisiblePosition;
    private int mFocusPosition;
    private CollectionGridView mGridView;
    private int mLastVisiblePosition;
    private int mScrollOffset;
    private int mTotalSize;

    protected abstract List<Rect> buildLayout();

    public CollectionLayoutManager(CollectionGridView collectionGridView, int i, boolean z) {
        super(collectionGridView.getContext(), i, z);
        this.mScrollOffset = 0;
        this.mFocusPosition = 0;
        this.mGridView = collectionGridView;
    }

    public CollectionLayoutManager(CollectionGridView collectionGridView) {
        super(collectionGridView.getContext());
        this.mScrollOffset = 0;
        this.mFocusPosition = 0;
        this.mGridView = collectionGridView;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-1, -1);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() == 0) {
            detachAndScrapAttachedViews(recycler);
            return;
        }
        this.mChildPositions = buildLayout();
        this.mTotalSize = 0;
        for (int i = 0; i < getItemCount(); i++) {
            if (i < this.mChildPositions.size()) {
                if (getOrientation() == 1) {
                    this.mTotalSize = Math.max(this.mChildPositions.get(i).bottom, this.mTotalSize);
                } else {
                    this.mTotalSize = Math.max(this.mChildPositions.get(i).right, this.mTotalSize);
                }
            }
        }
        if (getOrientation() == 1) {
            this.mTotalSize += getPaddingTop() + getPaddingBottom();
        } else {
            this.mTotalSize += getPaddingLeft() + getPaddingRight();
        }
        detachAndScrapAttachedViews(recycler);
        fill(0, recycler, state.isPreLayout());
    }

    protected static final Rect createRect(float f, float f2, float f3, float f4) {
        return new Rect((int) f, (int) f2, (int) (f + f3), (int) (f2 + f4));
    }

    private final Rect getVisibleRect() {
        if (getOrientation() == 1) {
            return createRect(0.0f, this.mScrollOffset - getPaddingTop(), getWidth(), getHeight());
        }
        return createRect(this.mScrollOffset - getPaddingRight(), 0.0f, getWidth(), getHeight());
    }

    private int computeFirstVisiblePosition() {
        Rect visibleRect = getVisibleRect();
        for (int i = 0; i < getItemCount(); i++) {
            if (i < this.mChildPositions.size() && Rect.intersects(this.mChildPositions.get(i), visibleRect)) {
                return i;
            }
        }
        return 0;
    }

    public int computeLastVisiblePosition() {
        Rect visibleRect = getVisibleRect();
        int i = this.mFirstVisiblePosition;
        int i2 = i;
        while (i < getItemCount()) {
            if (i < this.mChildPositions.size() && Rect.intersects(this.mChildPositions.get(i), visibleRect)) {
                i2 = i;
            }
            i++;
        }
        return i2;
    }

    private void fillGrid(RecyclerView.Recycler recycler) {
        fill(0, recycler, false);
    }

    private void fill(int i, RecyclerView.Recycler recycler, boolean z) {
        if (recycler == null) {
            return;
        }
        SparseArray sparseArray = new SparseArray(getChildCount());
        if (getChildCount() != 0) {
            for (int i2 = 0; i2 < getChildCount(); i2++) {
                sparseArray.put(this.mFirstVisiblePosition + i2, getChildAt(i2));
            }
            for (int i3 = 0; i3 < sparseArray.size(); i3++) {
                detachView((View) sparseArray.valueAt(i3));
            }
        }
        this.mFirstVisiblePosition = computeFirstVisiblePosition();
        this.mLastVisiblePosition = computeLastVisiblePosition();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i4 = this.mFirstVisiblePosition; i4 <= this.mLastVisiblePosition; i4++) {
            View viewForPosition = (View) sparseArray.get(i4);
            if (viewForPosition == null) {
                try {
                    viewForPosition = recycler.getViewForPosition(i4);
                } catch (Throwable unused) {
                }
                if (viewForPosition != null) {
                    addView(viewForPosition);
                }
            } else {
                attachView(viewForPosition);
                sparseArray.remove(i4);
            }
            View view = viewForPosition;
            if (view != null) {
                Rect rect = this.mChildPositions.get(i4);
                Rect rect2 = new Rect();
                calculateItemDecorationsForChild(view, rect2);
                view.measure(View.MeasureSpec.makeMeasureSpec((rect.width() - rect2.left) - rect2.right, 1073741824), View.MeasureSpec.makeMeasureSpec((rect.height() - rect2.top) - rect2.bottom, 1073741824));
                if (getOrientation() == 1) {
                    layoutDecorated(view, rect.left + paddingLeft, (rect.top - this.mScrollOffset) + paddingTop, rect.right + paddingLeft, (rect.bottom - this.mScrollOffset) + paddingTop);
                } else {
                    layoutDecorated(view, (rect.left - this.mScrollOffset) + paddingLeft, rect.top + paddingTop, (rect.right - this.mScrollOffset) + paddingLeft, rect.bottom + paddingTop);
                }
            }
        }
        for (int i5 = 0; i5 < sparseArray.size(); i5++) {
            recycler.recycleView((View) sparseArray.valueAt(i5));
        }
    }

    protected int getTotalSize() {
        int height;
        int paddingTop;
        if (getOrientation() == 1) {
            height = getWidth() - getPaddingRight();
            paddingTop = getPaddingLeft();
        } else {
            height = getHeight() - getPaddingBottom();
            paddingTop = getPaddingTop();
        }
        return height - paddingTop;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        return getOrientation() == 0 && findLastCompletelyVisibleItemPosition() != getItemCount() - 1;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return getOrientation() == 1 && findLastCompletelyVisibleItemPosition() != getItemCount() - 1;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void scrollToPosition(int i) {
        if (i >= getItemCount()) {
            Log.e(TAG, "Cannot scroll to " + i + ", item count is " + getItemCount());
            return;
        }
        this.mFirstVisiblePosition = i;
        if (getOrientation() == 0) {
            this.mScrollOffset = this.mChildPositions.get(i).top;
        } else {
            this.mScrollOffset = this.mChildPositions.get(i).left;
        }
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        if (i >= getItemCount()) {
            Log.e(TAG, "Cannot scroll to " + i + ", item count is " + getItemCount());
            return;
        }
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext()) { // from class: androidx.leanback.widget.CollectionLayoutManager.1
            @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller
            public PointF computeScrollVectorForPosition(int i2) {
                Rect rect = (Rect) CollectionLayoutManager.this.mChildPositions.get(i2);
                return new PointF(rect.left, rect.top);
            }
        };
        linearSmoothScroller.setTargetPosition(i);
        startSmoothScroll(linearSmoothScroller);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int height = -i;
        int i2 = this.mScrollOffset;
        if (i2 - height < 0) {
            height = i2;
        }
        if (this.mScrollOffset - height >= this.mTotalSize - getHeight()) {
            height = this.mScrollOffset - ((this.mTotalSize - getHeight()) - 1);
        }
        this.mScrollOffset -= height;
        offsetChildrenVertical(height);
        fillGrid(recycler);
        return -height;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int width = -i;
        int i2 = this.mScrollOffset;
        if (i2 - width < 0) {
            width = i2;
        }
        if (this.mScrollOffset - width >= this.mTotalSize - getWidth()) {
            width = this.mScrollOffset - ((this.mTotalSize - getWidth()) - 1);
        }
        this.mScrollOffset -= width;
        offsetChildrenHorizontal(width);
        fillGrid(recycler);
        return -width;
    }

    public int getSelection() {
        return this.mFocusPosition;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0027, code lost:
    
        if (r10 != 130) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int getMovement(int r10) {
        /*
            r9 = this;
            int r0 = r9.getOrientation()
            r1 = 3
            r2 = 2
            r3 = 0
            r4 = 130(0x82, float:1.82E-43)
            r5 = 66
            r6 = 33
            r7 = 1
            r8 = 17
            if (r0 != 0) goto L1b
            if (r10 == r8) goto L2c
            if (r10 == r6) goto L2e
            if (r10 == r5) goto L2a
            if (r10 == r4) goto L31
            goto L30
        L1b:
            int r0 = r9.getOrientation()
            if (r0 != r7) goto L30
            if (r10 == r8) goto L2e
            if (r10 == r6) goto L2c
            if (r10 == r5) goto L31
            if (r10 == r4) goto L2a
            goto L30
        L2a:
            r1 = r7
            goto L31
        L2c:
            r1 = r3
            goto L31
        L2e:
            r1 = r2
            goto L31
        L30:
            r1 = r8
        L31:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.CollectionLayoutManager.getMovement(int):int");
    }

    private int getPositionByView(View view) {
        RecyclerView.LayoutParams layoutParams;
        if (view == null || (layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams()) == null || layoutParams.isItemRemoved()) {
            return -1;
        }
        return layoutParams.getViewPosition();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public View findViewByPosition(int i) {
        int i2 = this.mFirstVisiblePosition;
        if (i2 > i || i > this.mLastVisiblePosition) {
            return null;
        }
        return getChildAt(i - i2);
    }

    void onFocusChanged(boolean z, int i, Rect rect) {
        boolean z2;
        View viewFindViewByPosition;
        if (z) {
            int i2 = this.mFocusPosition;
            while (true) {
                View viewFindViewByPosition2 = findViewByPosition(i2);
                if (viewFindViewByPosition2 != null) {
                    if (viewFindViewByPosition2.getVisibility() == 0 && viewFindViewByPosition2.hasFocusable()) {
                        viewFindViewByPosition2.requestFocus();
                        z2 = true;
                        break;
                    }
                    i2++;
                } else {
                    z2 = false;
                    break;
                }
            }
            if (z2 || (viewFindViewByPosition = findViewByPosition(0)) == null || viewFindViewByPosition.getVisibility() != 0 || !viewFindViewByPosition.hasFocusable()) {
                return;
            }
            viewFindViewByPosition.requestFocus();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean onRequestChildFocus(RecyclerView recyclerView, RecyclerView.State state, View view, View view2) {
        this.mFocusPosition = getPositionByView(view);
        return super.onRequestChildFocus(recyclerView, state, view, view2);
    }

    boolean gridOnRequestFocusInDescendants(RecyclerView recyclerView, int i, Rect rect) {
        View viewFindViewByPosition = findViewByPosition(this.mFocusPosition);
        if (viewFindViewByPosition != null) {
            return viewFindViewByPosition.requestFocus(i, rect);
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public View onInterceptFocusSearch(View view, int i) {
        View viewFindNextFocus;
        View viewFindNextFocus2;
        View viewFocusSearch;
        FocusFinder focusFinder = FocusFinder.getInstance();
        if (i == 2 || i == 1) {
            if (canScrollVertically()) {
                viewFindNextFocus = focusFinder.findNextFocus(this.mGridView, view, i == 2 ? TsExtractor.TS_STREAM_TYPE_HDMV_DTS : 33);
            } else {
                viewFindNextFocus = null;
            }
            if (canScrollHorizontally()) {
                viewFindNextFocus2 = focusFinder.findNextFocus(this.mGridView, view, (i == 2) ^ (getLayoutDirection() == 1) ? 66 : 17);
            } else {
                viewFindNextFocus2 = viewFindNextFocus;
            }
        } else {
            viewFindNextFocus2 = focusFinder.findNextFocus(this.mGridView, view, i);
        }
        if (viewFindNextFocus2 != null) {
            return viewFindNextFocus2;
        }
        if (this.mGridView.getDescendantFocusability() == 393216) {
            return this.mGridView.getParent().focusSearch(view, i);
        }
        int movement = getMovement(i);
        if (movement == 1 || movement == 0) {
            viewFindNextFocus2 = view;
        }
        if (viewFindNextFocus2 != null) {
            return viewFindNextFocus2;
        }
        if (view == null || (viewFocusSearch = this.mGridView.getParent().focusSearch(view, i)) == null) {
            return view != null ? view : this.mGridView;
        }
        return viewFocusSearch;
    }
}
