package androidx.leanback.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public class ScaleFrameLayout extends FrameLayout {
    private static final int DEFAULT_CHILD_GRAVITY = 8388659;
    private float mChildScale;
    private float mLayoutScaleX;
    private float mLayoutScaleY;

    public ScaleFrameLayout(Context context) {
        this(context, null);
    }

    public ScaleFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScaleFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mLayoutScaleX = 1.0f;
        this.mLayoutScaleY = 1.0f;
        this.mChildScale = 1.0f;
    }

    public void setLayoutScaleX(float f) {
        if (f != this.mLayoutScaleX) {
            this.mLayoutScaleX = f;
            requestLayout();
        }
    }

    public void setLayoutScaleY(float f) {
        if (f != this.mLayoutScaleY) {
            this.mLayoutScaleY = f;
            requestLayout();
        }
    }

    public void setChildScale(float f) {
        if (this.mChildScale != f) {
            this.mChildScale = f;
            for (int i = 0; i < getChildCount(); i++) {
                getChildAt(i).setScaleX(f);
                getChildAt(i).setScaleY(f);
            }
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        view.setScaleX(this.mChildScale);
        view.setScaleY(this.mChildScale);
    }

    @Override // android.view.ViewGroup
    protected boolean addViewInLayout(View view, int i, ViewGroup.LayoutParams layoutParams, boolean z) {
        boolean zAddViewInLayout = super.addViewInLayout(view, i, layoutParams, z);
        if (zAddViewInLayout) {
            view.setScaleX(this.mChildScale);
            view.setScaleY(this.mChildScale);
        }
        return zAddViewInLayout;
    }

    /* JADX WARN: Code duplicated, block: B:31:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:39:0x00da  */
    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        float pivotX;
        int paddingLeft;
        int i5;
        int paddingRight;
        int paddingTop;
        int i6;
        int paddingBottom;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        ScaleFrameLayout scaleFrameLayout = this;
        int childCount = getChildCount();
        int layoutDirection = getLayoutDirection();
        if (layoutDirection == 1) {
            pivotX = getWidth() - getPivotX();
        } else {
            pivotX = getPivotX();
        }
        if (scaleFrameLayout.mLayoutScaleX != 1.0f) {
            int paddingLeft2 = getPaddingLeft();
            float f = scaleFrameLayout.mLayoutScaleX;
            paddingLeft = paddingLeft2 + ((int) ((pivotX - (pivotX / f)) + 0.5f));
            i5 = (int) ((((i3 - i) - pivotX) / f) + pivotX + 0.5f);
            paddingRight = getPaddingRight();
        } else {
            paddingLeft = getPaddingLeft();
            i5 = i3 - i;
            paddingRight = getPaddingRight();
        }
        int i14 = i5 - paddingRight;
        float pivotY = getPivotY();
        if (scaleFrameLayout.mLayoutScaleY != 1.0f) {
            int paddingTop2 = getPaddingTop();
            float f2 = scaleFrameLayout.mLayoutScaleY;
            paddingTop = paddingTop2 + ((int) ((pivotY - (pivotY / f2)) + 0.5f));
            i6 = (int) ((((i4 - i2) - pivotY) / f2) + pivotY + 0.5f);
            paddingBottom = getPaddingBottom();
        } else {
            paddingTop = getPaddingTop();
            i6 = i4 - i2;
            paddingBottom = getPaddingBottom();
        }
        int i15 = i6 - paddingBottom;
        int i16 = 0;
        while (i16 < childCount) {
            View childAt = scaleFrameLayout.getChildAt(i16);
            if (childAt.getVisibility() != 8) {
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i17 = layoutParams.gravity;
                if (i17 == -1) {
                    i17 = DEFAULT_CHILD_GRAVITY;
                }
                int absoluteGravity = Gravity.getAbsoluteGravity(i17, layoutDirection);
                int i18 = i17 & 112;
                int i19 = absoluteGravity & 7;
                if (i19 == 1) {
                    i7 = (((i14 - paddingLeft) - measuredWidth) / 2) + paddingLeft + layoutParams.leftMargin;
                    i8 = layoutParams.rightMargin;
                } else {
                    if (i19 == 5) {
                        i7 = i14 - measuredWidth;
                        i8 = layoutParams.rightMargin;
                    } else {
                        i9 = layoutParams.leftMargin + paddingLeft;
                    }
                    if (i18 != 16) {
                        i10 = (((i15 - paddingTop) - measuredHeight) / 2) + paddingTop + layoutParams.topMargin;
                        i11 = layoutParams.bottomMargin;
                    } else if (i18 != 48 && i18 == 80) {
                        i10 = i15 - measuredHeight;
                        i11 = layoutParams.bottomMargin;
                    } else {
                        i13 = layoutParams.topMargin;
                        i12 = i13 + paddingTop;
                        childAt.layout(i9, i12, measuredWidth + i9, measuredHeight + i12);
                        childAt.setPivotX(pivotX - i9);
                        childAt.setPivotY(pivotY - i12);
                    }
                    i12 = i10 - i11;
                    childAt.layout(i9, i12, measuredWidth + i9, measuredHeight + i12);
                    childAt.setPivotX(pivotX - i9);
                    childAt.setPivotY(pivotY - i12);
                }
                i9 = i7 - i8;
                if (i18 != 16) {
                    i10 = (((i15 - paddingTop) - measuredHeight) / 2) + paddingTop + layoutParams.topMargin;
                    i11 = layoutParams.bottomMargin;
                } else {
                    if (i18 != 48) {
                        i13 = layoutParams.topMargin;
                    } else {
                        i13 = layoutParams.topMargin;
                    }
                    i12 = i13 + paddingTop;
                    childAt.layout(i9, i12, measuredWidth + i9, measuredHeight + i12);
                    childAt.setPivotX(pivotX - i9);
                    childAt.setPivotY(pivotY - i12);
                }
                i12 = i10 - i11;
                childAt.layout(i9, i12, measuredWidth + i9, measuredHeight + i12);
                childAt.setPivotX(pivotX - i9);
                childAt.setPivotY(pivotY - i12);
            }
            i16++;
            scaleFrameLayout = this;
        }
    }

    private static int getScaledMeasureSpec(int i, float f) {
        return f == 1.0f ? i : View.MeasureSpec.makeMeasureSpec((int) ((View.MeasureSpec.getSize(i) / f) + 0.5f), View.MeasureSpec.getMode(i));
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.mLayoutScaleX != 1.0f || this.mLayoutScaleY != 1.0f) {
            super.onMeasure(getScaledMeasureSpec(i, this.mLayoutScaleX), getScaledMeasureSpec(i2, this.mLayoutScaleY));
            setMeasuredDimension((int) ((getMeasuredWidth() * this.mLayoutScaleX) + 0.5f), (int) ((getMeasuredHeight() * this.mLayoutScaleY) + 0.5f));
        } else {
            super.onMeasure(i, i2);
        }
    }

    @Override // android.view.View
    public void setForeground(Drawable drawable) {
        throw new UnsupportedOperationException();
    }
}
