package androidx.leanback.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

/* JADX INFO: loaded from: classes.dex */
public abstract class CollectionGridView extends RecyclerView {
    public static final int FOCUS_SCROLL_ALIGNED = 0;
    public static final int FOCUS_SCROLL_ITEM = 1;
    public static final int FOCUS_SCROLL_PAGE = 2;
    public static final float ITEM_ALIGN_OFFSET_PERCENT_DISABLED = -1.0f;
    public static final int SAVE_ALL_CHILD = 3;
    public static final int SAVE_LIMITED_CHILD = 2;
    public static final int SAVE_NO_CHILD = 0;
    public static final int SAVE_ON_SCREEN_CHILD = 1;
    public static final int WINDOW_ALIGN_BOTH_EDGE = 3;
    public static final int WINDOW_ALIGN_HIGH_EDGE = 2;
    public static final int WINDOW_ALIGN_LOW_EDGE = 1;
    public static final int WINDOW_ALIGN_NO_EDGE = 0;
    public static final float WINDOW_ALIGN_OFFSET_PERCENT_DISABLED = -1.0f;
    private boolean mAnimateChildLayout;
    RecyclerView.RecyclerListener mChainedRecyclerListener;
    private boolean mHasOverlappingRendering;
    int mInitialPrefetchItemCount;
    final CollectionLayoutManager mLayoutManager;
    private OnKeyInterceptListener mOnKeyInterceptListener;
    private OnMotionInterceptListener mOnMotionInterceptListener;
    private OnTouchInterceptListener mOnTouchInterceptListener;
    private OnUnhandledKeyListener mOnUnhandledKeyListener;
    private RecyclerView.ItemAnimator mSavedItemAnimator;

    public interface OnKeyInterceptListener {
        boolean onInterceptKeyEvent(KeyEvent keyEvent);
    }

    public interface OnMotionInterceptListener {
        boolean onInterceptMotionEvent(MotionEvent motionEvent);
    }

    public interface OnTouchInterceptListener {
        boolean onInterceptTouchEvent(MotionEvent motionEvent);
    }

    public interface OnUnhandledKeyListener {
        boolean onUnhandledKey(KeyEvent keyEvent);
    }

    protected abstract CollectionLayoutManager createLayoutManager();

    public CollectionGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mAnimateChildLayout = true;
        this.mHasOverlappingRendering = true;
        this.mInitialPrefetchItemCount = 4;
        CollectionLayoutManager collectionLayoutManagerCreateLayoutManager = createLayoutManager();
        this.mLayoutManager = collectionLayoutManagerCreateLayoutManager;
        setLayoutManager(collectionLayoutManagerCreateLayoutManager);
        setPreserveFocusAfterLayout(false);
        setDescendantFocusability(262144);
        setHasFixedSize(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setChildrenDrawingOrderEnabled(true);
        setWillNotDraw(true);
        setOverScrollMode(2);
        ((SimpleItemAnimator) getItemAnimator()).setSupportsChangeAnimations(false);
        super.setRecyclerListener(new RecyclerView.RecyclerListener() { // from class: androidx.leanback.widget.CollectionGridView.1
            @Override // androidx.recyclerview.widget.RecyclerView.RecyclerListener
            public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
                if (CollectionGridView.this.mChainedRecyclerListener != null) {
                    CollectionGridView.this.mChainedRecyclerListener.onViewRecycled(viewHolder);
                }
            }
        });
    }

    @Override // android.view.View
    protected void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        this.mLayoutManager.onFocusChanged(z, i, rect);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    public boolean onRequestFocusInDescendants(int i, Rect rect) {
        return this.mLayoutManager.gridOnRequestFocusInDescendants(this, i, rect);
    }

    @Override // android.view.View
    public View focusSearch(int i) {
        if (isFocused()) {
            CollectionLayoutManager collectionLayoutManager = this.mLayoutManager;
            View viewFindViewByPosition = collectionLayoutManager.findViewByPosition(collectionLayoutManager.getSelection());
            if (viewFindViewByPosition != null) {
                return focusSearch(viewFindViewByPosition, i);
            }
            View viewFindViewByPosition2 = this.mLayoutManager.findViewByPosition(0);
            if (viewFindViewByPosition2 != null) {
                return focusSearch(viewFindViewByPosition2, i);
            }
        }
        return super.focusSearch(i);
    }

    public int getSelectedPosition() {
        return this.mLayoutManager.getSelection();
    }
}
