package com.dotscreen.portal.ui.screens.guide;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.leanback.widget.VerticalGridView;
import androidx.recyclerview.widget.RecyclerView;
import com.dotscreen.megacable.portal.R;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class ProgramGrid extends VerticalGridView {
    private static final long FOCUS_AREA_RIGHT_MARGIN_MILLIS = TimeUnit.MINUTES.toMillis(15);
    private static final int INVALID_INDEX = -1;
    private static final String TAG = "ProgramGrid";
    private ChildFocusListener mChildFocusListener;
    private int mFocusRangeLeft;
    private int mFocusRangeRight;
    private final ViewTreeObserver.OnGlobalFocusChangeListener mGlobalFocusChangeListener;
    private boolean mKeepCurrentProgram;
    public View mLastFocusedView;
    private View mNextFocusByUpDown;
    private final OnRepeatedKeyInterceptListener mOnRepeatedKeyInterceptListener;
    private final ViewTreeObserver.OnPreDrawListener mPreDrawListener;
    private ProgramManager mProgramManager;
    private final ProgramManager.Listener mProgramManagerListener;
    private final int mRowHeight;
    private final int mSelectionRow;
    private final Rect mTempRect;

    interface ChildFocusListener {
        void onRequestChildFocus(View view, View view2);
    }

    public ProgramGrid(Context context) {
        this(context, null);
    }

    public ProgramGrid(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ProgramGrid(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mGlobalFocusChangeListener = new ViewTreeObserver.OnGlobalFocusChangeListener() { // from class: com.dotscreen.portal.ui.screens.guide.ProgramGrid.1
            @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
            public void onGlobalFocusChanged(View view, View view2) {
                if (view2 != ProgramGrid.this.mNextFocusByUpDown) {
                    ProgramGrid.this.clearUpDownFocusState(view2);
                }
                ProgramGrid.this.mNextFocusByUpDown = null;
                ProgramGrid programGrid = ProgramGrid.this;
                if (view2 == programGrid || !programGrid.contains(view2)) {
                    return;
                }
                ProgramGrid.this.mLastFocusedView = view2;
            }
        };
        this.mProgramManagerListener = new ProgramManager.ListenerAdapter() { // from class: com.dotscreen.portal.ui.screens.guide.ProgramGrid.2
            @Override // com.dotscreen.portal.ui.screens.guide.ProgramManager.ListenerAdapter, com.dotscreen.portal.ui.screens.guide.ProgramManager.Listener
            public void onTimeRangeUpdated() {
                ProgramGrid.this.clearUpDownFocusState(null);
            }
        };
        this.mPreDrawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: com.dotscreen.portal.ui.screens.guide.ProgramGrid.3
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                ProgramGrid.this.getViewTreeObserver().removeOnPreDrawListener(this);
                ProgramGrid.this.updateInputLogo();
                return true;
            }
        };
        this.mTempRect = new Rect();
        clearUpDownFocusState(null);
        setItemViewCacheSize(0);
        Resources resources = context.getResources();
        this.mRowHeight = resources.getDimensionPixelSize(R.dimen.program_guide_table_item_row_height) + 50;
        this.mSelectionRow = resources.getInteger(R.integer.program_guide_selection_row);
        OnRepeatedKeyInterceptListener onRepeatedKeyInterceptListener = new OnRepeatedKeyInterceptListener(this);
        this.mOnRepeatedKeyInterceptListener = onRepeatedKeyInterceptListener;
        setOnKeyInterceptListener(onRepeatedKeyInterceptListener);
    }

    public void initialize(ProgramManager programManager) {
        this.mProgramManager = programManager;
    }

    public void setChildFocusListener(ChildFocusListener childFocusListener) {
        this.mChildFocusListener = childFocusListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        super.requestChildFocus(view, view2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalFocusChangeListener(this.mGlobalFocusChangeListener);
        this.mProgramManager.addListener(this.mProgramManagerListener);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnGlobalFocusChangeListener(this.mGlobalFocusChangeListener);
        this.mProgramManager.removeListener(this.mProgramManagerListener);
        clearUpDownFocusState(null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.ViewParent
    public View focusSearch(View view, int i) {
        Log.d(TAG, "focusSearch...");
        this.mNextFocusByUpDown = null;
        if (view == null || !contains(view)) {
            View viewFocusSearch = super.focusSearch(view, i);
            Log.d(TAG, "1. nextFocus = " + viewFocusSearch);
            return viewFocusSearch;
        }
        if (i == 33 || i == 130) {
            updateUpDownFocusState(view);
            View viewFocusFind = focusFind(view, i);
            Log.d(TAG, "2. nextFocus = " + viewFocusFind);
            if (viewFocusFind != null) {
                return viewFocusFind;
            }
        }
        View viewFocusSearch2 = super.focusSearch(view, i);
        Log.d(TAG, "3.  focused   = " + view);
        Log.d(TAG, "  > nextFocus = " + viewFocusSearch2);
        if (i == 17 && view == viewFocusSearch2) {
            Log.d(TAG, ">>>> DETECTED LIMIT REACHED");
            if (getContext() instanceof GuideActivity) {
                Log.d(TAG, ">>>> Context is GuideActivity");
                ((GuideActivity) getContext()).reloadFullGuide();
            }
        }
        return viewFocusSearch2;
    }

    public void resetFocusState() {
        this.mLastFocusedView = null;
        clearUpDownFocusState(null);
    }

    /* JADX WARN: Code duplicated, block: B:60:0x00d1  */
    private View focusFind(View view, int i) {
        int i2;
        int i3;
        int focusedChildIndex = getFocusedChildIndex();
        if (focusedChildIndex == -1) {
            Log.w(TAG, "No child view has focus");
            return null;
        }
        int i4 = i == 33 ? focusedChildIndex - 1 : focusedChildIndex + 1;
        if (i4 < 0 || i4 >= getChildCount()) {
            return view;
        }
        View childAt = getChildAt(i4);
        ArrayList arrayList = new ArrayList();
        findFocusables(childAt, arrayList);
        if (this.mKeepCurrentProgram) {
            i2 = 0;
            while (true) {
                if (i2 >= arrayList.size()) {
                    i2 = -1;
                    break;
                }
                View view2 = (View) arrayList.get(i2);
                if ((view2 instanceof ProgramItemMegaCell) && ((ProgramItemMegaCell) view2).getMTableEntry().isCurrentProgram()) {
                    break;
                }
                i2++;
            }
            if (i2 != -1) {
                View view3 = (View) arrayList.get(i2);
                this.mNextFocusByUpDown = view3;
                return view3;
            }
            this.mKeepCurrentProgram = false;
        } else {
            i2 = -1;
        }
        int i5 = Integer.MIN_VALUE;
        int i6 = Integer.MIN_VALUE;
        for (int i7 = 0; i7 < arrayList.size(); i7++) {
            View view4 = (View) arrayList.get(i7);
            Rect rect = this.mTempRect;
            view4.getGlobalVisibleRect(rect);
            if (this.mFocusRangeLeft <= rect.left && rect.right <= this.mFocusRangeRight) {
                int iWidth = rect.width();
                if (iWidth > i6) {
                    i2 = i7;
                    i6 = iWidth;
                }
            } else if (rect.left <= this.mFocusRangeLeft && this.mFocusRangeRight <= rect.right) {
                i2 = i7;
                break;
            }
        }
        if (i2 != -1) {
            View view5 = (View) arrayList.get(i2);
            this.mNextFocusByUpDown = view5;
            return view5;
        }
        for (int i8 = 0; i8 < arrayList.size(); i8++) {
            View view6 = (View) arrayList.get(i8);
            Rect rect2 = this.mTempRect;
            view6.getGlobalVisibleRect(rect2);
            if (this.mFocusRangeLeft <= rect2.left) {
                int i9 = rect2.left;
                int i10 = this.mFocusRangeRight;
                if (i9 <= i10) {
                    int i11 = i10 - rect2.left;
                    if (i11 > i5) {
                        i2 = i8;
                        i5 = i11;
                    }
                } else if (this.mFocusRangeLeft > rect2.right && rect2.right <= this.mFocusRangeRight && (i3 = rect2.right - this.mFocusRangeLeft) > i5) {
                    i2 = i8;
                    i5 = i3;
                }
            } else if (this.mFocusRangeLeft > rect2.right) {
            }
        }
        if (i2 != -1) {
            View view7 = (View) arrayList.get(i2);
            this.mNextFocusByUpDown = view7;
            return view7;
        }
        Log.w(TAG, "focusFind doesn't find proper focusable");
        return null;
    }

    private int getFocusedChildIndex() {
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i).hasFocus()) {
                return i;
            }
        }
        return -1;
    }

    private void updateUpDownFocusState(View view) {
        int rightMostFocusablePosition = getRightMostFocusablePosition();
        Rect rect = this.mTempRect;
        view.getGlobalVisibleRect(rect);
        this.mFocusRangeLeft = Math.min(this.mFocusRangeLeft, rightMostFocusablePosition);
        this.mFocusRangeRight = Math.min(this.mFocusRangeRight, rightMostFocusablePosition);
        rect.left = Math.min(rect.left, rightMostFocusablePosition);
        rect.right = Math.min(rect.right, rightMostFocusablePosition);
        if (rect.left <= this.mFocusRangeRight) {
            int i = rect.right;
            int i2 = this.mFocusRangeLeft;
            if (i >= i2) {
                this.mFocusRangeLeft = Math.max(i2, rect.left);
                this.mFocusRangeRight = Math.min(this.mFocusRangeRight, rect.right);
                return;
            }
        }
        Log.w(TAG, "The current focus is out of [mFocusRangeLeft, mFocusRangeRight]");
        this.mFocusRangeLeft = rect.left;
        this.mFocusRangeRight = rect.right;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearUpDownFocusState(View view) {
        boolean z = false;
        this.mFocusRangeLeft = 0;
        this.mFocusRangeRight = getRightMostFocusablePosition();
        this.mNextFocusByUpDown = null;
        if (view != null && (view instanceof ProgramItemMegaCell) && ((ProgramItemMegaCell) view).getMTableEntry().isCurrentProgram()) {
            z = true;
        }
        this.mKeepCurrentProgram = z;
    }

    private int getRightMostFocusablePosition() {
        if (getGlobalVisibleRect(this.mTempRect)) {
            return this.mTempRect.right - GuideUtils.INSTANCE.convertMillisToPixel(FOCUS_AREA_RIGHT_MARGIN_MILLIS);
        }
        return Integer.MAX_VALUE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean contains(View view) {
        if (view == this) {
            return true;
        }
        if (view == null || view == view.getRootView()) {
            return false;
        }
        return contains((View) view.getParent());
    }

    public void onItemSelectionReset() {
        getViewTreeObserver().addOnPreDrawListener(this.mPreDrawListener);
    }

    @Override // androidx.leanback.widget.BaseGridView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    public boolean onRequestFocusInDescendants(int i, Rect rect) {
        View view = this.mLastFocusedView;
        if (view != null && view.isShown() && this.mLastFocusedView.requestFocus()) {
            return true;
        }
        return super.onRequestFocusInDescendants(i, rect);
    }

    @Override // android.view.View
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        View viewFindFocus = findFocus();
        if (viewFindFocus != null && this.mOnRepeatedKeyInterceptListener.isFocusAccelerated()) {
            int[] iArr = new int[2];
            getLocationOnScreen(iArr);
            int[] iArr2 = new int[2];
            viewFindFocus.getLocationOnScreen(iArr2);
            int i5 = iArr2[1] - iArr[1];
            int i6 = (this.mSelectionRow - 1) * this.mRowHeight;
            if (i5 < i6) {
                scrollBy(0, i5 - i6);
            }
            int i7 = (this.mSelectionRow + 1) * this.mRowHeight;
            if (i5 > i7) {
                scrollBy(0, i5 - i7);
            }
        }
        updateInputLogo();
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        updateInputLogo();
    }

    private int getFirstVisibleChildIndex() {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        int paddingTop = layoutManager.getPaddingTop();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if ((layoutManager.getDecoratedTop(childAt) + layoutManager.getDecoratedBottom(childAt)) / 2 > paddingTop) {
                return i;
            }
        }
        return -1;
    }

    public void updateInputLogo() {
        int firstVisibleChildIndex;
        int childCount = getChildCount();
        if (childCount == 0 || (firstVisibleChildIndex = getFirstVisibleChildIndex()) == -1) {
            return;
        }
        View childAt = getChildAt(firstVisibleChildIndex);
        int childAdapterPosition = getChildAdapterPosition(childAt);
        ((ProgramTableAdapter.ProgramRowHolder) getChildViewHolder(childAt)).updateInputLogo(childAdapterPosition, true);
        for (int i = firstVisibleChildIndex + 1; i < childCount; i++) {
            View childAt2 = getChildAt(i);
            ((ProgramTableAdapter.ProgramRowHolder) getChildViewHolder(childAt2)).updateInputLogo(childAdapterPosition, false);
            childAdapterPosition = getChildAdapterPosition(childAt2);
        }
    }

    private static void findFocusables(View view, ArrayList<View> arrayList) {
        if (view.isFocusable()) {
            arrayList.add(view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                findFocusables(viewGroup.getChildAt(i), arrayList);
            }
        }
    }
}
