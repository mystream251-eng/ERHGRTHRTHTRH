package androidx.leanback.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.leanback.system.Settings;
import androidx.leanback.transition.TransitionHelper;
import com.dotscreen.farkit.tools.DSLog;
import com.dotscreen.megacable.portal.R;

/* JADX INFO: loaded from: classes.dex */
public class HorizontalGridPresenter extends Presenter {
    private static final boolean DEBUG = false;
    private static final String TAG = "GridPresenter";
    private int mFocusZoomFactor;
    private boolean mKeepChildForeground;
    private int mNumRows;
    private OnItemViewClickedListener mOnItemViewClickedListener;
    private OnItemViewSelectedListener mOnItemViewSelectedListener;
    private boolean mRoundedCornersEnabled;
    private boolean mShadowEnabled;
    ShadowOverlayHelper mShadowOverlayHelper;
    private ItemBridgeAdapter.Wrapper mShadowOverlayWrapper;
    private boolean mUseFocusDimmer;

    class HorizontalGridItemBridgeAdapter extends ItemBridgeAdapter {
        HorizontalGridItemBridgeAdapter() {
        }

        @Override // androidx.leanback.widget.ItemBridgeAdapter
        protected void onCreate(ItemBridgeAdapter.ViewHolder viewHolder) {
            if (viewHolder.itemView instanceof ViewGroup) {
                TransitionHelper.setTransitionGroup((ViewGroup) viewHolder.itemView, true);
            }
            if (HorizontalGridPresenter.this.mShadowOverlayHelper != null) {
                HorizontalGridPresenter.this.mShadowOverlayHelper.onViewCreated(viewHolder.itemView);
            }
        }

        @Override // androidx.leanback.widget.ItemBridgeAdapter
        public void onBind(final ItemBridgeAdapter.ViewHolder viewHolder) {
            if (HorizontalGridPresenter.this.getOnItemViewClickedListener() != null) {
                viewHolder.mHolder.view.setOnClickListener(new View.OnClickListener() { // from class: androidx.leanback.widget.HorizontalGridPresenter.HorizontalGridItemBridgeAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        DSLog.INSTANCE.a("Clicked on a view");
                        if (HorizontalGridPresenter.this.getOnItemViewClickedListener() != null) {
                            HorizontalGridPresenter.this.getOnItemViewClickedListener().onItemClicked(viewHolder.mHolder, viewHolder.mItem, null, null);
                        }
                    }
                });
            }
        }

        @Override // androidx.leanback.widget.ItemBridgeAdapter
        public void onUnbind(ItemBridgeAdapter.ViewHolder viewHolder) {
            if (HorizontalGridPresenter.this.getOnItemViewClickedListener() != null) {
                viewHolder.mHolder.view.setOnClickListener(null);
            }
        }

        @Override // androidx.leanback.widget.ItemBridgeAdapter
        public void onAttachedToWindow(ItemBridgeAdapter.ViewHolder viewHolder) {
            viewHolder.itemView.setActivated(true);
        }
    }

    public static class ViewHolder extends Presenter.ViewHolder {
        final HorizontalGridView mGridView;
        boolean mInitialized;
        ItemBridgeAdapter mItemBridgeAdapter;

        public ViewHolder(HorizontalGridView horizontalGridView) {
            super(horizontalGridView);
            this.mGridView = horizontalGridView;
        }

        public HorizontalGridView getGridView() {
            return this.mGridView;
        }
    }

    public HorizontalGridPresenter() {
        this(3);
    }

    public HorizontalGridPresenter(int i) {
        this(i, true);
    }

    public HorizontalGridPresenter(int i, boolean z) {
        this.mNumRows = -1;
        this.mShadowEnabled = true;
        this.mKeepChildForeground = true;
        this.mRoundedCornersEnabled = true;
        this.mFocusZoomFactor = i;
        this.mUseFocusDimmer = z;
    }

    public void setNumberOfRows(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Invalid number of rows");
        }
        if (this.mNumRows != i) {
            this.mNumRows = i;
        }
    }

    public int getNumberOfRows() {
        return this.mNumRows;
    }

    public final void setShadowEnabled(boolean z) {
        this.mShadowEnabled = z;
    }

    public final boolean getShadowEnabled() {
        return this.mShadowEnabled;
    }

    public boolean isUsingDefaultShadow() {
        return ShadowOverlayHelper.supportsShadow();
    }

    public final void enableChildRoundedCorners(boolean z) {
        this.mRoundedCornersEnabled = z;
    }

    public final boolean areChildRoundedCornersEnabled() {
        return this.mRoundedCornersEnabled;
    }

    public boolean isUsingZOrder(Context context) {
        return !Settings.getInstance(context).preferStaticShadows();
    }

    final boolean needsDefaultShadow() {
        return isUsingDefaultShadow() && getShadowEnabled();
    }

    public final int getFocusZoomFactor() {
        return this.mFocusZoomFactor;
    }

    public final boolean isFocusDimmerUsed() {
        return this.mUseFocusDimmer;
    }

    @Override // androidx.leanback.widget.Presenter
    public final ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        ViewHolder viewHolderCreateGridViewHolder = createGridViewHolder(viewGroup);
        viewHolderCreateGridViewHolder.mInitialized = false;
        viewHolderCreateGridViewHolder.mItemBridgeAdapter = new HorizontalGridItemBridgeAdapter();
        initializeGridViewHolder(viewHolderCreateGridViewHolder);
        if (viewHolderCreateGridViewHolder.mInitialized) {
            return viewHolderCreateGridViewHolder;
        }
        throw new RuntimeException("super.initializeGridViewHolder() must be called");
    }

    protected ViewHolder createGridViewHolder(ViewGroup viewGroup) {
        return new ViewHolder((HorizontalGridView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lb_horizontal_grid, viewGroup, false).findViewById(R.id.browse_grid));
    }

    protected void initializeGridViewHolder(final ViewHolder viewHolder) {
        if (this.mNumRows == -1) {
            throw new IllegalStateException("Number of rows must be set");
        }
        viewHolder.getGridView().setNumRows(this.mNumRows);
        viewHolder.mInitialized = true;
        Context context = viewHolder.mGridView.getContext();
        if (this.mShadowOverlayHelper == null) {
            ShadowOverlayHelper shadowOverlayHelperBuild = new ShadowOverlayHelper.Builder().needsOverlay(this.mUseFocusDimmer).needsShadow(needsDefaultShadow()).needsRoundedCorner(areChildRoundedCornersEnabled()).preferZOrder(isUsingZOrder(context)).keepForegroundDrawable(this.mKeepChildForeground).options(createShadowOverlayOptions()).build(context);
            this.mShadowOverlayHelper = shadowOverlayHelperBuild;
            if (shadowOverlayHelperBuild.needsWrapper()) {
                this.mShadowOverlayWrapper = new ItemBridgeAdapterShadowOverlayWrapper(this.mShadowOverlayHelper);
            }
        }
        viewHolder.mItemBridgeAdapter.setWrapper(this.mShadowOverlayWrapper);
        this.mShadowOverlayHelper.prepareParentForShadow(viewHolder.mGridView);
        viewHolder.getGridView().setFocusDrawingOrderEnabled(this.mShadowOverlayHelper.getShadowType() != 3);
        FocusHighlightHelper.setupBrowseItemFocusHighlight(viewHolder.mItemBridgeAdapter, this.mFocusZoomFactor, this.mUseFocusDimmer);
        viewHolder.getGridView().setOnChildSelectedListener(new OnChildSelectedListener() { // from class: androidx.leanback.widget.HorizontalGridPresenter.1
            @Override // androidx.leanback.widget.OnChildSelectedListener
            public void onChildSelected(ViewGroup viewGroup, View view, int i, long j) {
                HorizontalGridPresenter.this.selectChildView(viewHolder, view);
            }
        });
    }

    public final void setKeepChildForeground(boolean z) {
        this.mKeepChildForeground = z;
    }

    public final boolean getKeepChildForeground() {
        return this.mKeepChildForeground;
    }

    protected ShadowOverlayHelper.Options createShadowOverlayOptions() {
        return ShadowOverlayHelper.Options.DEFAULT;
    }

    @Override // androidx.leanback.widget.Presenter
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object obj) {
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        viewHolder2.mItemBridgeAdapter.setAdapter((ObjectAdapter) obj);
        viewHolder2.getGridView().setAdapter(viewHolder2.mItemBridgeAdapter);
    }

    @Override // androidx.leanback.widget.Presenter
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        viewHolder2.mItemBridgeAdapter.setAdapter(null);
        viewHolder2.getGridView().setAdapter(null);
    }

    public final void setOnItemViewSelectedListener(OnItemViewSelectedListener onItemViewSelectedListener) {
        this.mOnItemViewSelectedListener = onItemViewSelectedListener;
    }

    public final OnItemViewSelectedListener getOnItemViewSelectedListener() {
        return this.mOnItemViewSelectedListener;
    }

    public final void setOnItemViewClickedListener(OnItemViewClickedListener onItemViewClickedListener) {
        this.mOnItemViewClickedListener = onItemViewClickedListener;
    }

    public final OnItemViewClickedListener getOnItemViewClickedListener() {
        return this.mOnItemViewClickedListener;
    }

    void selectChildView(ViewHolder viewHolder, View view) {
        if (getOnItemViewSelectedListener() != null) {
            ItemBridgeAdapter.ViewHolder viewHolder2 = view == null ? null : (ItemBridgeAdapter.ViewHolder) viewHolder.getGridView().getChildViewHolder(view);
            if (viewHolder2 == null) {
                getOnItemViewSelectedListener().onItemSelected(null, null, null, null);
            } else {
                getOnItemViewSelectedListener().onItemSelected(viewHolder2.mHolder, viewHolder2.mItem, null, null);
            }
        }
    }

    public void setEntranceTransitionState(ViewHolder viewHolder, boolean z) {
        viewHolder.mGridView.setChildrenVisibility(z ? 0 : 4);
    }
}
