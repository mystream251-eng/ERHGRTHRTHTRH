package com.dotscreen.portal.ui.screens.guide;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.TVAiring;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProgramRow.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u0000 @2\u00020\u0001:\u0002?@B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u0017\u001a\u00020\u0018J\u0010\u0010\u0019\u001a\u00020\u00182\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bJ\u0006\u0010\u001c\u001a\u00020\u0018J\u001a\u0010\u001d\u001a\u0004\u0018\u00010\n2\u0006\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\u0007H\u0016J\u0012\u0010 \u001a\u00020\u00142\b\u0010!\u001a\u0004\u0018\u00010\nH\u0002J\u0010\u0010\"\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u0007H\u0002J\u0010\u0010#\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u0007H\u0002J\b\u0010$\u001a\u00020\u0018H\u0014J\u0010\u0010%\u001a\u00020\u00182\u0006\u0010&\u001a\u00020\nH\u0016J\u0010\u0010'\u001a\u00020\u00182\u0006\u0010&\u001a\u00020\nH\u0016J\b\u0010(\u001a\u00020\u0018H\u0014J\u001a\u0010)\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u00072\b\u0010*\u001a\u0004\u0018\u00010+H\u0014J\u0018\u0010,\u001a\u00020\u00182\u0006\u0010-\u001a\u00020\u00072\u0006\u0010.\u001a\u00020\u0007H\u0016J\u000e\u0010/\u001a\u00020\u00182\u0006\u00100\u001a\u00020\u0007J\u0010\u00101\u001a\u00020\u00182\u0006\u00102\u001a\u000203H\u0002J\u0010\u00104\u001a\u00020\u00182\b\u00105\u001a\u0004\u0018\u00010\u000eJ\u0010\u00106\u001a\u00020\u00182\b\u00107\u001a\u0004\u0018\u00010\u0010J\u000e\u00108\u001a\u00020\u00182\u0006\u00109\u001a\u00020\u0016J\u0010\u0010:\u001a\u0004\u0018\u00010;2\u0006\u0010!\u001a\u00020\nJ\u001c\u0010<\u001a\u00020\u00182\b\u0010=\u001a\u0004\u0018\u00010\n2\b\u0010>\u001a\u0004\u0018\u00010\nH\u0002R\u0016\u0010\t\u001a\u0004\u0018\u00010\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000¨\u0006A"}, d2 = {"Lcom/dotscreen/portal/ui/screens/guide/ProgramRow;", "Lcom/dotscreen/portal/ui/screens/guide/TimelineGridView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "currentProgramView", "Landroid/view/View;", "getCurrentProgramView", "()Landroid/view/View;", "mChannel", "Lcom/dotscreen/bokit/model/Channel;", "mChildFocusListener", "Lcom/dotscreen/portal/ui/screens/guide/ProgramRow$ChildFocusListener;", "mGlobalFocusChangeListener", "Landroid/view/ViewTreeObserver$OnGlobalFocusChangeListener;", "mKeepFocusToCurrentProgram", "", "mProgramManager", "Lcom/dotscreen/portal/ui/screens/guide/ProgramManager;", "focusCurrentProgram", "", "focusOnProgram", "program", "Lcom/dotscreen/portal/ui/screens/guide/TableEntry;", "focusOnSelectedProgram", "focusSearch", "focused", "direction", "isChild", "view", "isDirectionEnd", "isDirectionStart", "onAttachedToWindow", "onChildAttachedToWindow", "child", "onChildDetachedFromWindow", "onDetachedFromWindow", "onRequestFocusInDescendants", "previouslyFocusedRect", "Landroid/graphics/Rect;", "onScrolled", "dx", "dy", "resetScroll", "scrollOffset", "scrollByTime", "timeToScroll", "", "setChannel", "channel", "setChildFocusListener", "childFocusListener", "setProgramManager", "programManager", "toRectString", "", "updateCurrentFocus", "oldFocus", "newFocus", "ChildFocusListener", "Companion", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class ProgramRow extends TimelineGridView {
    private static final boolean DEBUG = false;
    private static final long HALF_HOUR_MILLIS;
    private static final long ONE_HOUR_MILLIS;
    private static final String TAG = "ProgramRow";
    private HashMap _$_findViewCache;
    private Channel mChannel;
    private ChildFocusListener mChildFocusListener;
    private final ViewTreeObserver.OnGlobalFocusChangeListener mGlobalFocusChangeListener;
    private boolean mKeepFocusToCurrentProgram;
    private ProgramManager mProgramManager;

    /* JADX INFO: compiled from: ProgramRow.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/dotscreen/portal/ui/screens/guide/ProgramRow$ChildFocusListener;", "", "onChildFocus", "", "oldFocus", "Landroid/view/View;", "newFocus", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public interface ChildFocusListener {
        void onChildFocus(View oldFocus, View newFocus);
    }

    public ProgramRow(Context context) {
        this(context, null, 0, 6, null);
    }

    public ProgramRow(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    @Override // com.dotscreen.portal.ui.screens.guide.TimelineGridView
    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    @Override // com.dotscreen.portal.ui.screens.guide.TimelineGridView
    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProgramRow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.mGlobalFocusChangeListener = new ViewTreeObserver.OnGlobalFocusChangeListener() { // from class: com.dotscreen.portal.ui.screens.guide.ProgramRow$mGlobalFocusChangeListener$1
            @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
            public final void onGlobalFocusChanged(View view, View view2) {
                this.this$0.updateCurrentFocus(view, view2);
            }
        };
    }

    public /* synthetic */ ProgramRow(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? (AttributeSet) null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    static {
        long millis = TimeUnit.HOURS.toMillis(1L);
        ONE_HOUR_MILLIS = millis;
        HALF_HOUR_MILLIS = millis / ((long) 2);
    }

    public final void setChildFocusListener(ChildFocusListener childFocusListener) {
        this.mChildFocusListener = childFocusListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void onScrolled(int dx, int dy) {
        int right;
        int right2;
        super.onScrolled(dx, dy);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.guide.ProgramItemMegaCell");
            }
            ProgramItemMegaCell programItemMegaCell = (ProgramItemMegaCell) childAt;
            if (getLeft() <= programItemMegaCell.getRight() && programItemMegaCell.getLeft() <= getRight()) {
                if (getLayoutDirection() == 0) {
                    right = getLeft();
                    right2 = programItemMegaCell.getLeft();
                } else {
                    right = programItemMegaCell.getRight();
                    right2 = getRight();
                }
                programItemMegaCell.layoutVisibleArea(right - right2);
            }
        }
    }

    public final String toRectString(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        return "{l=" + view.getLeft() + ",r=" + view.getRight() + ",t=" + view.getTop() + ",b=" + view.getBottom() + ",w=" + view.getWidth() + ",h=" + view.getHeight() + "}";
    }

    public final void focusCurrentProgram() {
        Log.d(TAG, "focusCurrentProgram()");
        View currentProgramView = getCurrentProgramView();
        if (currentProgramView == null) {
            Log.e(TAG, "* * no current program");
            currentProgramView = getChildAt(0);
        }
        updateCurrentFocus(null, currentProgramView);
    }

    public final void focusOnProgram(TableEntry program) {
        TVAiring program2;
        Log.d(TAG, "focusOnProgram()");
        View currentProgramView = getCurrentProgramView();
        StringBuilder sb = new StringBuilder();
        sb.append("looking for ");
        sb.append((program == null || (program2 = program.getProgram()) == null) ? null : program2.getTitle());
        Log.d(TAG, sb.toString());
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.guide.ProgramItemMegaCell");
            }
            if (Intrinsics.areEqual(((ProgramItemMegaCell) childAt).getMTableEntry(), program)) {
                Log.d(TAG, "found at position: " + (i + 1));
                currentProgramView = getChildAt(i);
                break;
            }
        }
        if (currentProgramView != null) {
            updateCurrentFocus(null, currentProgramView);
        } else {
            Log.e(TAG, "program not found");
        }
    }

    public final void focusOnSelectedProgram() {
        ProgramManager programManager = this.mProgramManager;
        if (programManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProgramManager");
        }
        focusOnProgram(programManager.getMSelectedProgram());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateCurrentFocus(View oldFocus, View newFocus) {
        TVAiring program;
        boolean z = newFocus instanceof ProgramItemMegaCell;
        if (z) {
            ProgramManager programManager = this.mProgramManager;
            if (programManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mProgramManager");
            }
            if (programManager.getMSelectedProgram() != null) {
                TableEntry mTableEntry = ((ProgramItemMegaCell) newFocus).getMTableEntry();
                ProgramManager programManager2 = this.mProgramManager;
                if (programManager2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mProgramManager");
                }
                if (!Intrinsics.areEqual(mTableEntry, programManager2.getMSelectedProgram())) {
                    Log.e(TAG, "skip updating current focus");
                    return;
                }
            }
        }
        ChildFocusListener childFocusListener = this.mChildFocusListener;
        if (childFocusListener == null) {
            return;
        }
        if (childFocusListener == null) {
            Intrinsics.throwNpe();
        }
        String title = null;
        if (!isChild(oldFocus)) {
            oldFocus = null;
        }
        childFocusListener.onChildFocus(oldFocus, isChild(newFocus) ? newFocus : null);
        if (z) {
            StringBuilder sb = new StringBuilder();
            sb.append('{');
            TableEntry mTableEntry2 = ((ProgramItemMegaCell) newFocus).getMTableEntry();
            if (mTableEntry2 != null && (program = mTableEntry2.getProgram()) != null) {
                title = program.getTitle();
            }
            sb.append(title);
            sb.append("}.requestFocus()");
            Log.d(TAG, sb.toString());
            newFocus.requestFocus();
        }
    }

    private final boolean isChild(View view) {
        if (view == null) {
            return false;
        }
        for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
            if (parent == this) {
                return true;
            }
        }
        return false;
    }

    private final boolean isDirectionStart(int direction) {
        if (getLayoutDirection() == 0) {
            if (direction == 17) {
                return true;
            }
        } else if (direction == 66) {
            return true;
        }
        return false;
    }

    private final boolean isDirectionEnd(int direction) {
        if (getLayoutDirection() == 0) {
            if (direction == 66) {
                return true;
            }
        } else if (direction == 17) {
            return true;
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.ViewParent
    public View focusSearch(View focused, int direction) {
        Intrinsics.checkParameterIsNotNull(focused, "focused");
        TableEntry mTableEntry = ((ProgramItemMegaCell) focused).getMTableEntry();
        if (mTableEntry == null) {
            Intrinsics.throwNpe();
        }
        ProgramManager programManager = this.mProgramManager;
        if (programManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProgramManager");
        }
        long mFromUtcMillis = programManager.getMFromUtcMillis();
        ProgramManager programManager2 = this.mProgramManager;
        if (programManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProgramManager");
        }
        long mToUtcMillis = programManager2.getMToUtcMillis();
        if (isDirectionStart(direction) || direction == 1) {
            if (mTableEntry.getEntryStartUtcMillis() < mFromUtcMillis) {
                Log.d(TAG, "focusedEntry.entryStartUtcMillis = " + new Date(mTableEntry.getEntryStartUtcMillis()));
                Log.d(TAG, "fromMillis =                       " + new Date(mFromUtcMillis));
                scrollByTime(Math.max(-ONE_HOUR_MILLIS, mTableEntry.getEntryStartUtcMillis() - mFromUtcMillis));
                Log.d(TAG, "1...");
                return focused;
            }
        } else if (isDirectionEnd(direction) || direction == 2) {
            long entryEndUtcMillis = mTableEntry.getEntryEndUtcMillis();
            long j = ONE_HOUR_MILLIS;
            if (entryEndUtcMillis > mToUtcMillis + j) {
                scrollByTime(j);
                Log.d(TAG, "2...");
                return focused;
            }
        }
        View viewFocusSearch = super.focusSearch(focused, direction);
        Intrinsics.checkExpressionValueIsNotNull(viewFocusSearch, "super.focusSearch(focused, direction)");
        if (!(viewFocusSearch instanceof ProgramItemMegaCell)) {
            if ((isDirectionEnd(direction) || direction == 2) && mTableEntry.getEntryEndUtcMillis() != mToUtcMillis) {
                long entryEndUtcMillis2 = mTableEntry.getEntryEndUtcMillis();
                ProgramManager programManager3 = this.mProgramManager;
                if (programManager3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mProgramManager");
                }
                scrollByTime(entryEndUtcMillis2 - programManager3.getMToUtcMillis());
                Log.d(TAG, "3...");
                return focused;
            }
            Log.d(TAG, "4...");
            return viewFocusSearch;
        }
        TableEntry mTableEntry2 = ((ProgramItemMegaCell) viewFocusSearch).getMTableEntry();
        if (mTableEntry2 == null) {
            Intrinsics.throwNpe();
        }
        if (isDirectionStart(direction) || direction == 1) {
            if (mTableEntry2.getEntryStartUtcMillis() < mFromUtcMillis && mTableEntry2.getEntryEndUtcMillis() < HALF_HOUR_MILLIS + mFromUtcMillis) {
                scrollByTime(Math.max(-ONE_HOUR_MILLIS, mTableEntry2.getEntryStartUtcMillis() - mFromUtcMillis));
            }
        } else if (isDirectionEnd(direction) || direction == 2) {
            long entryStartUtcMillis = mTableEntry2.getEntryStartUtcMillis();
            long j2 = ONE_HOUR_MILLIS;
            if (entryStartUtcMillis > mFromUtcMillis + j2 + HALF_HOUR_MILLIS) {
                scrollByTime(Math.min(j2, (mTableEntry2.getEntryStartUtcMillis() - mFromUtcMillis) - ONE_HOUR_MILLIS));
            }
        }
        Log.d(TAG, "5..., target = " + viewFocusSearch);
        return viewFocusSearch;
    }

    private final void scrollByTime(long timeToScroll) {
        ProgramManager programManager = this.mProgramManager;
        if (programManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProgramManager");
        }
        programManager.shiftTime(timeToScroll);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalFocusChangeListener(this.mGlobalFocusChangeListener);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnGlobalFocusChangeListener(this.mGlobalFocusChangeListener);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void onChildDetachedFromWindow(View child) {
        Intrinsics.checkParameterIsNotNull(child, "child");
        if (child.hasFocus()) {
            TableEntry mTableEntry = ((ProgramItemMegaCell) child).getMTableEntry();
            if (mTableEntry == null) {
                Intrinsics.throwNpe();
            }
            if (mTableEntry.isCurrentProgram()) {
                this.mKeepFocusToCurrentProgram = true;
            }
        }
        super.onChildDetachedFromWindow(child);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void onChildAttachedToWindow(View child) {
        Intrinsics.checkParameterIsNotNull(child, "child");
        super.onChildAttachedToWindow(child);
        if (this.mKeepFocusToCurrentProgram) {
            TableEntry mTableEntry = ((ProgramItemMegaCell) child).getMTableEntry();
            if (mTableEntry == null) {
                Intrinsics.throwNpe();
            }
            if (mTableEntry.isCurrentProgram()) {
                post(new Runnable() { // from class: com.dotscreen.portal.ui.screens.guide.ProgramRow.onChildAttachedToWindow.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        ProgramRow.this.requestFocus();
                    }
                });
                this.mKeepFocusToCurrentProgram = false;
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        View currentProgramView = getCurrentProgramView();
        if (currentProgramView != null) {
            return currentProgramView.requestFocus();
        }
        boolean zOnRequestFocusInDescendants = super.onRequestFocusInDescendants(direction, previouslyFocusedRect);
        if (!zOnRequestFocusInDescendants) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = getChildAt(i);
                Intrinsics.checkExpressionValueIsNotNull(childAt, "getChildAt(i)");
                if (childAt.isShown() && childAt.hasFocusable()) {
                    return childAt.requestFocus();
                }
            }
        }
        return zOnRequestFocusInDescendants;
    }

    private final View getCurrentProgramView() {
        int childCount = getChildCount();
        int i = 0;
        while (true) {
            if (i < childCount) {
                View childAt = getChildAt(i);
                if (childAt == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.guide.ProgramItemMegaCell");
                }
                TableEntry mTableEntry = ((ProgramItemMegaCell) childAt).getMTableEntry();
                if (mTableEntry != null && mTableEntry.isCurrentProgram()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("currentProgramView -> ");
                    TVAiring program = mTableEntry.getProgram();
                    sb.append(program != null ? program.getTitle() : null);
                    Log.d(TAG, sb.toString());
                    return getChildAt(i);
                }
                i++;
            } else {
                Log.e(TAG, "currentProgramView -> null");
                return null;
            }
        }
    }

    public final void setChannel(Channel channel) {
        this.mChannel = channel;
    }

    public final void setProgramManager(ProgramManager programManager) {
        Intrinsics.checkParameterIsNotNull(programManager, "programManager");
        this.mProgramManager = programManager;
    }

    public final void resetScroll(int scrollOffset) {
        int programIndexAtTime;
        long jConvertPixelToMillis = GuideUtils.INSTANCE.convertPixelToMillis(scrollOffset);
        ProgramManager programManager = this.mProgramManager;
        if (programManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProgramManager");
        }
        long mStartUtcMillis = jConvertPixelToMillis + programManager.getMStartUtcMillis();
        if (this.mChannel == null) {
            programIndexAtTime = -1;
        } else {
            ProgramManager programManager2 = this.mProgramManager;
            if (programManager2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mProgramManager");
            }
            Channel channel = this.mChannel;
            if (channel == null) {
                Intrinsics.throwNpe();
            }
            programIndexAtTime = programManager2.getProgramIndexAtTime(channel.getId(), mStartUtcMillis);
        }
        if (programIndexAtTime < 0) {
            RecyclerView.LayoutManager layoutManager = getLayoutManager();
            if (layoutManager == null) {
                Intrinsics.throwNpe();
            }
            layoutManager.scrollToPosition(0);
            return;
        }
        ProgramManager programManager3 = this.mProgramManager;
        if (programManager3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProgramManager");
        }
        Channel channel2 = this.mChannel;
        if (channel2 == null) {
            Intrinsics.throwNpe();
        }
        TableEntry tableEntry = programManager3.getTableEntry(channel2.getId(), programIndexAtTime);
        if (tableEntry == null) {
            Intrinsics.throwNpe();
        }
        GuideUtils guideUtils = GuideUtils.INSTANCE;
        ProgramManager programManager4 = this.mProgramManager;
        if (programManager4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProgramManager");
        }
        int iConvertMillisToPixel = guideUtils.convertMillisToPixel(programManager4.getMStartUtcMillis(), tableEntry.getEntryStartUtcMillis()) - scrollOffset;
        RecyclerView.LayoutManager layoutManager2 = getLayoutManager();
        if (layoutManager2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
        }
        ((LinearLayoutManager) layoutManager2).scrollToPositionWithOffset(programIndexAtTime, iConvertMillisToPixel);
    }
}
