package androidx.leanback.widget;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
class ItemAlignmentFacetHelper {
    private static Rect sRect = new Rect();

    static int getAlignmentPosition(View view, ItemAlignmentFacet.ItemAlignmentDef itemAlignmentDef, int i) {
        View viewFindViewById;
        int opticalTopInset;
        int width;
        int width2;
        int width3;
        GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        if (itemAlignmentDef.mViewId == 0 || (viewFindViewById = view.findViewById(itemAlignmentDef.mViewId)) == null) {
            viewFindViewById = view;
        }
        int opticalHeight = itemAlignmentDef.mOffset;
        if (i == 0) {
            if (view.getLayoutDirection() == 1) {
                if (viewFindViewById == view) {
                    width2 = layoutParams.getOpticalWidth(viewFindViewById);
                } else {
                    width2 = viewFindViewById.getWidth();
                }
                int paddingLeft = width2 - opticalHeight;
                if (itemAlignmentDef.mOffsetWithPadding) {
                    if (itemAlignmentDef.mOffsetPercent == 0.0f) {
                        paddingLeft -= viewFindViewById.getPaddingRight();
                    } else if (itemAlignmentDef.mOffsetPercent == 100.0f) {
                        paddingLeft += viewFindViewById.getPaddingLeft();
                    }
                }
                if (itemAlignmentDef.mOffsetPercent != -1.0f) {
                    if (viewFindViewById == view) {
                        width3 = layoutParams.getOpticalWidth(viewFindViewById);
                    } else {
                        width3 = viewFindViewById.getWidth();
                    }
                    paddingLeft -= (int) ((width3 * itemAlignmentDef.mOffsetPercent) / 100.0f);
                }
                if (view == viewFindViewById) {
                    return paddingLeft;
                }
                sRect.right = paddingLeft;
                ((ViewGroup) view).offsetDescendantRectToMyCoords(viewFindViewById, sRect);
                return sRect.right + layoutParams.getOpticalRightInset();
            }
            if (itemAlignmentDef.mOffsetWithPadding) {
                if (itemAlignmentDef.mOffsetPercent == 0.0f) {
                    opticalHeight += viewFindViewById.getPaddingLeft();
                } else if (itemAlignmentDef.mOffsetPercent == 100.0f) {
                    opticalHeight -= viewFindViewById.getPaddingRight();
                }
            }
            if (itemAlignmentDef.mOffsetPercent != -1.0f) {
                if (viewFindViewById == view) {
                    width = layoutParams.getOpticalWidth(viewFindViewById);
                } else {
                    width = viewFindViewById.getWidth();
                }
                opticalHeight += (int) ((width * itemAlignmentDef.mOffsetPercent) / 100.0f);
            }
            int i2 = opticalHeight;
            if (view == viewFindViewById) {
                return i2;
            }
            sRect.left = i2;
            ((ViewGroup) view).offsetDescendantRectToMyCoords(viewFindViewById, sRect);
            return sRect.left - layoutParams.getOpticalLeftInset();
        }
        if (itemAlignmentDef.mOffsetWithPadding) {
            if (itemAlignmentDef.mOffsetPercent == 0.0f) {
                opticalHeight += viewFindViewById.getPaddingTop();
            } else if (itemAlignmentDef.mOffsetPercent == 100.0f) {
                opticalHeight -= viewFindViewById.getPaddingBottom();
            }
        }
        if (itemAlignmentDef.mOffsetPercent != -1.0f) {
            opticalHeight += (int) (((viewFindViewById == view ? layoutParams.getOpticalHeight(viewFindViewById) : viewFindViewById.getHeight()) * itemAlignmentDef.mOffsetPercent) / 100.0f);
        }
        if (view != viewFindViewById) {
            sRect.top = opticalHeight;
            ((ViewGroup) view).offsetDescendantRectToMyCoords(viewFindViewById, sRect);
            opticalTopInset = sRect.top - layoutParams.getOpticalTopInset();
        } else {
            opticalTopInset = opticalHeight;
        }
        return itemAlignmentDef.isAlignedToTextViewBaseLine() ? opticalTopInset + viewFindViewById.getBaseline() : opticalTopInset;
    }

    private ItemAlignmentFacetHelper() {
    }
}
