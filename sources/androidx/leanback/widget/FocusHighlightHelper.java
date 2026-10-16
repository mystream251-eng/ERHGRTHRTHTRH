package androidx.leanback.widget;

import android.animation.TimeAnimator;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.leanback.R;
import androidx.leanback.graphics.ColorOverlayDimmer;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class FocusHighlightHelper {
    static boolean isValidZoomIndex(int i) {
        return i == 0 || getResId(i) > 0;
    }

    static int getResId(int i) {
        if (i == 1) {
            return R.fraction.lb_focus_zoom_factor_small;
        }
        if (i == 2) {
            return R.fraction.lb_focus_zoom_factor_medium;
        }
        if (i == 3) {
            return R.fraction.lb_focus_zoom_factor_large;
        }
        if (i != 4) {
            return 0;
        }
        return R.fraction.lb_focus_zoom_factor_xsmall;
    }

    static class FocusAnimator implements TimeAnimator.TimeListener {
        private final ColorOverlayDimmer mDimmer;
        private final int mDuration;
        private float mFocusLevelDelta;
        private float mFocusLevelStart;
        private final float mScaleDiff;
        private final View mView;
        private final ShadowOverlayContainer mWrapper;
        private float mFocusLevel = 0.0f;
        private final TimeAnimator mAnimator = new TimeAnimator();
        private final Interpolator mInterpolator = new AccelerateDecelerateInterpolator();

        void animateFocus(boolean z, boolean z2) {
            endAnimation();
            float f = z ? 1.0f : 0.0f;
            if (z2) {
                setFocusLevel(f);
                return;
            }
            float f2 = this.mFocusLevel;
            if (f2 != f) {
                this.mFocusLevelStart = f2;
                this.mFocusLevelDelta = f - f2;
                this.mAnimator.start();
            }
        }

        FocusAnimator(View view, float f, boolean z, int i) {
            this.mView = view;
            this.mDuration = i;
            this.mScaleDiff = f - 1.0f;
            if (view instanceof ShadowOverlayContainer) {
                this.mWrapper = (ShadowOverlayContainer) view;
            } else {
                this.mWrapper = null;
            }
            this.mAnimator.setTimeListener(this);
            if (z) {
                this.mDimmer = ColorOverlayDimmer.createDefault(view.getContext());
            } else {
                this.mDimmer = null;
            }
        }

        void setFocusLevel(float f) {
            this.mFocusLevel = f;
            float f2 = (this.mScaleDiff * f) + 1.0f;
            this.mView.setScaleX(f2);
            this.mView.setScaleY(f2);
            ShadowOverlayContainer shadowOverlayContainer = this.mWrapper;
            if (shadowOverlayContainer != null) {
                shadowOverlayContainer.setShadowFocusLevel(f);
            } else {
                ShadowOverlayHelper.setNoneWrapperShadowFocusLevel(this.mView, f);
            }
            ColorOverlayDimmer colorOverlayDimmer = this.mDimmer;
            if (colorOverlayDimmer != null) {
                colorOverlayDimmer.setActiveLevel(f);
                int color = this.mDimmer.getPaint().getColor();
                ShadowOverlayContainer shadowOverlayContainer2 = this.mWrapper;
                if (shadowOverlayContainer2 != null) {
                    shadowOverlayContainer2.setOverlayColor(color);
                } else {
                    ShadowOverlayHelper.setNoneWrapperOverlayColor(this.mView, color);
                }
            }
        }

        float getFocusLevel() {
            return this.mFocusLevel;
        }

        void endAnimation() {
            this.mAnimator.end();
        }

        @Override // android.animation.TimeAnimator.TimeListener
        public void onTimeUpdate(TimeAnimator timeAnimator, long j, long j2) {
            float interpolation;
            int i = this.mDuration;
            if (j >= i) {
                interpolation = 1.0f;
                this.mAnimator.end();
            } else {
                interpolation = (float) (j / ((double) i));
            }
            Interpolator interpolator = this.mInterpolator;
            if (interpolator != null) {
                interpolation = interpolator.getInterpolation(interpolation);
            }
            setFocusLevel(this.mFocusLevelStart + (interpolation * this.mFocusLevelDelta));
        }
    }

    static class BrowseItemFocusHighlight implements FocusHighlightHandler {
        private static final int DURATION_MS = 150;
        private int mScaleIndex;
        private final boolean mUseDimmer;

        BrowseItemFocusHighlight(int i, boolean z) {
            if (!FocusHighlightHelper.isValidZoomIndex(i)) {
                throw new IllegalArgumentException("Unhandled zoom index");
            }
            this.mScaleIndex = i;
            this.mUseDimmer = z;
        }

        private float getScale(Resources resources) {
            int i = this.mScaleIndex;
            if (i == 0) {
                return 1.0f;
            }
            return resources.getFraction(FocusHighlightHelper.getResId(i), 1, 1);
        }

        @Override // androidx.leanback.widget.FocusHighlightHandler
        public void onItemFocused(View view, boolean z) {
            view.setSelected(z);
            getOrCreateAnimator(view).animateFocus(z, false);
        }

        @Override // androidx.leanback.widget.FocusHighlightHandler
        public void onInitializeView(View view) {
            getOrCreateAnimator(view).animateFocus(false, true);
        }

        private FocusAnimator getOrCreateAnimator(View view) {
            FocusAnimator focusAnimator = (FocusAnimator) view.getTag(R.id.lb_focus_animator);
            if (focusAnimator != null) {
                return focusAnimator;
            }
            FocusAnimator focusAnimator2 = new FocusAnimator(view, getScale(view.getResources()), this.mUseDimmer, DURATION_MS);
            view.setTag(R.id.lb_focus_animator, focusAnimator2);
            return focusAnimator2;
        }
    }

    public static void setupBrowseItemFocusHighlight(ItemBridgeAdapter itemBridgeAdapter, int i, boolean z) {
        itemBridgeAdapter.setFocusHighlight(new BrowseItemFocusHighlight(i, z));
    }

    @Deprecated
    public static void setupHeaderItemFocusHighlight(VerticalGridView verticalGridView) {
        setupHeaderItemFocusHighlight(verticalGridView, true);
    }

    @Deprecated
    public static void setupHeaderItemFocusHighlight(VerticalGridView verticalGridView, boolean z) {
        if (verticalGridView == null || !(verticalGridView.getAdapter() instanceof ItemBridgeAdapter)) {
            return;
        }
        ((ItemBridgeAdapter) verticalGridView.getAdapter()).setFocusHighlight(new HeaderItemFocusHighlight(z));
    }

    public static void setupHeaderItemFocusHighlight(ItemBridgeAdapter itemBridgeAdapter) {
        setupHeaderItemFocusHighlight(itemBridgeAdapter, true);
    }

    public static void setupHeaderItemFocusHighlight(ItemBridgeAdapter itemBridgeAdapter, boolean z) {
        itemBridgeAdapter.setFocusHighlight(new HeaderItemFocusHighlight(z));
    }

    static class HeaderItemFocusHighlight implements FocusHighlightHandler {
        private int mDuration;
        private boolean mInitialized;
        boolean mScaleEnabled;
        private float mSelectScale;

        @Override // androidx.leanback.widget.FocusHighlightHandler
        public void onInitializeView(View view) {
        }

        HeaderItemFocusHighlight(boolean z) {
            this.mScaleEnabled = z;
        }

        void lazyInit(View view) {
            if (this.mInitialized) {
                return;
            }
            Resources resources = view.getResources();
            TypedValue typedValue = new TypedValue();
            if (this.mScaleEnabled) {
                resources.getValue(R.dimen.lb_browse_header_select_scale, typedValue, true);
                this.mSelectScale = typedValue.getFloat();
            } else {
                this.mSelectScale = 1.0f;
            }
            resources.getValue(R.dimen.lb_browse_header_select_duration, typedValue, true);
            this.mDuration = typedValue.data;
            this.mInitialized = true;
        }

        static class HeaderFocusAnimator extends FocusAnimator {
            ItemBridgeAdapter.ViewHolder mViewHolder;

            HeaderFocusAnimator(View view, float f, int i) {
                super(view, f, false, i);
                ViewParent parent = view.getParent();
                while (parent != null && !(parent instanceof RecyclerView)) {
                    parent = parent.getParent();
                }
                if (parent != null) {
                    this.mViewHolder = (ItemBridgeAdapter.ViewHolder) ((RecyclerView) parent).getChildViewHolder(view);
                }
            }

            @Override // androidx.leanback.widget.FocusHighlightHelper.FocusAnimator
            void setFocusLevel(float f) {
                Presenter presenter = this.mViewHolder.getPresenter();
                if (presenter instanceof RowHeaderPresenter) {
                    ((RowHeaderPresenter) presenter).setSelectLevel((RowHeaderPresenter.ViewHolder) this.mViewHolder.getViewHolder(), f);
                }
                super.setFocusLevel(f);
            }
        }

        private void viewFocused(View view, boolean z) {
            lazyInit(view);
            view.setSelected(z);
            FocusAnimator headerFocusAnimator = (FocusAnimator) view.getTag(R.id.lb_focus_animator);
            if (headerFocusAnimator == null) {
                headerFocusAnimator = new HeaderFocusAnimator(view, this.mSelectScale, this.mDuration);
                view.setTag(R.id.lb_focus_animator, headerFocusAnimator);
            }
            headerFocusAnimator.animateFocus(z, false);
        }

        @Override // androidx.leanback.widget.FocusHighlightHandler
        public void onItemFocused(View view, boolean z) {
            viewFocused(view, z);
        }
    }

    @Deprecated
    public FocusHighlightHelper() {
    }
}
