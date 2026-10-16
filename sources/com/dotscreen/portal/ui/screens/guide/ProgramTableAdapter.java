package com.dotscreen.portal.ui.screens.guide;

import android.animation.Animator;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.media.tv.TvContract;
import android.os.Handler;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.tv.common.search.LocalSearchProvider;
import com.dotscreen.tv.common.utils.CommonUtils;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ProgramTableAdapter extends RecyclerView.Adapter<ProgramRowHolder> implements ProgramManager.TableEntryChangedListener {
    private static final boolean DEBUG = false;
    private static final String TAG = "ProgramTableAdapter";
    private final int mAnimationDuration;
    private final int mChannelBlockedTextColor;
    private final int mChannelLogoHeight;
    private final int mChannelLogoWidth;
    private final int mChannelTextColor;
    private final Context mContext;
    private final int mDetailGrayedTextColor;
    private final int mDetailPadding;
    private final int mDetailTextColor;
    private final TextAppearanceSpan mEpisodeTitleStyle;
    private final int mImageHeight;
    private final int mImageWidth;
    private final GuideActivity mProgramGuide;
    private final ProgramManager mProgramManager;
    private final String mProgramTitleForBlockedChannel;
    private final String mProgramTitleForNoInformation;
    private final RecyclerView.RecycledViewPool mRecycledViewPool;
    private final Handler mHandler = new Handler();
    private final List<ProgramListAdapter> mProgramListAdapters = new ArrayList();

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return R.layout.program_guide_table_row;
    }

    public ProgramTableAdapter(Context context, ProgramManager programManager, GuideActivity guideActivity) {
        this.mContext = context;
        this.mProgramManager = programManager;
        this.mProgramGuide = guideActivity;
        Resources resources = context.getResources();
        this.mChannelLogoWidth = resources.getDimensionPixelSize(R.dimen.program_guide_table_header_column_channel_logo_width);
        this.mChannelLogoHeight = resources.getDimensionPixelSize(R.dimen.program_guide_table_header_column_channel_logo_height);
        this.mImageWidth = resources.getDimensionPixelSize(R.dimen.program_guide_table_detail_image_width);
        this.mImageHeight = resources.getDimensionPixelSize(R.dimen.program_guide_table_detail_image_height);
        this.mProgramTitleForNoInformation = resources.getString(R.string.program_title_for_no_information);
        this.mProgramTitleForBlockedChannel = resources.getString(R.string.program_title_for_blocked_channel);
        this.mChannelTextColor = resources.getColor(R.color.program_guide_table_header_column_channel_number_text_color);
        this.mChannelBlockedTextColor = resources.getColor(R.color.program_guide_table_header_column_channel_number_blocked_text_color);
        this.mDetailTextColor = resources.getColor(R.color.program_guide_table_detail_title_text_color);
        this.mDetailGrayedTextColor = resources.getColor(R.color.program_guide_table_detail_title_grayed_text_color);
        this.mAnimationDuration = resources.getInteger(R.integer.program_guide_table_detail_fade_anim_duration);
        this.mDetailPadding = resources.getDimensionPixelOffset(R.dimen.program_guide_table_detail_padding);
        this.mEpisodeTitleStyle = new TextAppearanceSpan(null, 0, resources.getDimensionPixelSize(R.dimen.program_guide_table_detail_episode_title_text_size), ColorStateList.valueOf(resources.getColor(R.color.program_guide_table_detail_episode_title_text_color)), null);
        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        this.mRecycledViewPool = recycledViewPool;
        recycledViewPool.setMaxRecycledViews(R.layout.program_guide_table_item, 50);
        this.mProgramManager.addListener(new AnonymousClass1());
        update();
        this.mProgramManager.addTableEntryChangedListener(this);
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.ui.screens.guide.ProgramTableAdapter$1, reason: invalid class name */
    class AnonymousClass1 extends ProgramManager.ListenerAdapter {
        AnonymousClass1() {
        }

        public /* synthetic */ void lambda$onChannelsUpdated$0$ProgramTableAdapter$1() {
            ProgramTableAdapter.this.update();
        }

        @Override // com.dotscreen.portal.ui.screens.guide.ProgramManager.ListenerAdapter, com.dotscreen.portal.ui.screens.guide.ProgramManager.Listener
        public void onChannelsUpdated() {
            ProgramTableAdapter.this.mHandler.post(new Runnable() { // from class: com.dotscreen.portal.ui.screens.guide.-$$Lambda$ProgramTableAdapter$1$MdZbI3aekhTklEf2byOyiOtlVNQ
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onChannelsUpdated$0$ProgramTableAdapter$1();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void update() {
        Iterator<ProgramListAdapter> it = this.mProgramListAdapters.iterator();
        while (it.hasNext()) {
            this.mProgramManager.removeTableEntriesUpdatedListener(it.next());
        }
        this.mProgramListAdapters.clear();
        for (int i = 0; i < this.mProgramManager.getChannelCount(); i++) {
            ProgramListAdapter programListAdapter = new ProgramListAdapter(this.mContext.getResources(), this.mProgramManager, i);
            this.mProgramManager.addTableEntriesUpdatedListener(programListAdapter);
            this.mProgramListAdapters.add(programListAdapter);
        }
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mProgramListAdapters.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ProgramRowHolder programRowHolder, int i) {
        programRowHolder.onBind(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ProgramRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View viewInflate = LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false);
        ((ProgramRow) viewInflate.findViewById(R.id.row)).setRecycledViewPool(this.mRecycledViewPool);
        return new ProgramRowHolder(viewInflate);
    }

    @Override // com.dotscreen.portal.ui.screens.guide.ProgramManager.TableEntryChangedListener
    public void onTableEntryChanged(TableEntry tableEntry) {
        int channelIndex = this.mProgramManager.getChannelIndex(tableEntry.getChannelId());
        this.mProgramListAdapters.get(channelIndex).notifyItemChanged(this.mProgramManager.getProgramIdIndex(tableEntry.getChannelId(), tableEntry.getId()), tableEntry);
    }

    public class ProgramRowHolder extends RecyclerView.ViewHolder implements ProgramRow.ChildFocusListener {
        private ImageLoader imageLoader;
        private final TextView interactive_text;
        private Channel mChannel;
        private final ImageView mChannelBlockView;
        private final View mChannelHeaderView;
        private final ImageView mChannelLogoView;
        private final TextView mChannelNameView;
        private final TextView mChannelNumberView;
        private final ViewGroup mContainer;
        private Animator mDetailInAnimator;
        private final Runnable mDetailInStarter;
        private Animator mDetailOutAnimator;
        private final ImageView mInputLogoView;
        private boolean mIsInputLogoVisible;
        private final RecyclerView.OnScrollListener mOnScrollListener;
        private final ProgramRow mProgramRow;
        private TableEntry mSelectedEntry;

        private boolean isChannelLocked(Channel channel) {
            return false;
        }

        private void updatePosterArt(Bitmap bitmap) {
        }

        public ProgramRowHolder(View view) {
            super(view);
            this.mDetailInStarter = new Runnable() { // from class: com.dotscreen.portal.ui.screens.guide.ProgramTableAdapter.ProgramRowHolder.1
                @Override // java.lang.Runnable
                public void run() {
                    ProgramRowHolder.this.mProgramRow.removeOnScrollListener(ProgramRowHolder.this.mOnScrollListener);
                    if (ProgramRowHolder.this.mDetailInAnimator != null) {
                        ProgramRowHolder.this.mDetailInAnimator.start();
                    }
                }
            };
            this.mOnScrollListener = new RecyclerView.OnScrollListener() { // from class: com.dotscreen.portal.ui.screens.guide.ProgramTableAdapter.ProgramRowHolder.2
                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                    ProgramRowHolder.this.onHorizontalScrolled();
                }
            };
            ViewGroup viewGroup = (ViewGroup) view;
            this.mContainer = viewGroup;
            this.mProgramRow = (ProgramRow) viewGroup.findViewById(R.id.row);
            this.imageLoader = PortalApplication.INSTANCE.getSingleton(ProgramTableAdapter.this.mContext).getImageLoader();
            this.mChannelHeaderView = this.mContainer.findViewById(R.id.header_column);
            this.mChannelNumberView = (TextView) this.mContainer.findViewById(R.id.channel_number);
            this.mChannelNameView = (TextView) this.mContainer.findViewById(R.id.channel_name);
            this.mChannelLogoView = (ImageView) this.mContainer.findViewById(R.id.channel_logo);
            this.mChannelBlockView = (ImageView) this.mContainer.findViewById(R.id.channel_block);
            this.mInputLogoView = (ImageView) this.mContainer.findViewById(R.id.input_logo);
            this.interactive_text = (TextView) this.mContainer.findViewById(R.id.interactive_text);
        }

        public void onBind(int i) {
            onBindChannel(ProgramTableAdapter.this.mProgramManager.getChannel(i));
            this.mProgramRow.swapAdapter((RecyclerView.Adapter) ProgramTableAdapter.this.mProgramListAdapters.get(i), true);
            this.mProgramRow.setProgramManager(ProgramTableAdapter.this.mProgramManager);
            this.mProgramRow.setChannel(ProgramTableAdapter.this.mProgramManager.getChannel(i));
            this.mProgramRow.setChildFocusListener(this);
            this.mProgramRow.resetScroll(ProgramTableAdapter.this.mProgramGuide.getTimelineRowScrollOffset());
        }

        private void onBindChannel(final Channel channel) {
            this.mChannel = channel;
            this.mInputLogoView.setVisibility(8);
            this.mIsInputLogoVisible = false;
            if (channel == null) {
                this.mChannelNumberView.setVisibility(8);
                this.mChannelNameView.setVisibility(8);
                this.mChannelLogoView.setVisibility(8);
                this.mChannelBlockView.setVisibility(8);
                this.interactive_text.setVisibility(8);
                return;
            }
            String str = channel.getZappingNumber() + "";
            if (str == null) {
                this.mChannelNumberView.setVisibility(8);
            } else {
                if (str.length() <= 2) {
                    str = "00" + str;
                } else if (str.length() <= 3) {
                    str = LocalSearchProvider.NO_LIVE_CONTENTS + str;
                } else if (str.length() <= 3) {
                    str = "" + str;
                }
                this.mChannelNumberView.setText(str);
                this.mChannelNumberView.setVisibility(0);
            }
            this.mChannelNumberView.setTextColor(isChannelLocked(channel) ? ProgramTableAdapter.this.mChannelBlockedTextColor : ProgramTableAdapter.this.mChannelTextColor);
            this.mChannelLogoView.setImageBitmap(null);
            this.mChannelLogoView.setVisibility(8);
            if (channel.getCapabilities() != null && channel.getCapabilities().getCatchup() && channel.getCapabilities().getStartover()) {
                this.interactive_text.setVisibility(0);
            } else {
                this.interactive_text.setVisibility(8);
            }
            if (isChannelLocked(channel)) {
                this.mChannelNameView.setVisibility(8);
                this.mChannelBlockView.setVisibility(0);
                return;
            }
            this.mChannelNameView.setText(channel.getTitle());
            this.mChannelNameView.setVisibility(0);
            this.mChannelBlockView.setVisibility(8);
            if (channel.getSquareArtwork() == null || channel.getSquareArtwork().urlWithSize(new Point(ProgramTableAdapter.this.mChannelLogoWidth, ProgramTableAdapter.this.mChannelLogoHeight), 1.0d) == null) {
                if (CommonUtils.isDVBCSTB(ProgramTableAdapter.this.mContext) && ((PortalApplication) ProgramTableAdapter.this.mContext.getApplicationContext()).getDegradedMode()) {
                    channel.getId();
                    Cursor cursorQuery = ProgramTableAdapter.this.mContext.getContentResolver().query(TvContract.Channels.CONTENT_URI, com.google.android.media.tv.companionlibrary.model.Channel.PROJECTION, "_id=" + channel.getId(), null, null);
                    while (cursorQuery != null && cursorQuery.moveToNext()) {
                        if (channel.getId().equals(String.valueOf(com.google.android.media.tv.companionlibrary.model.Channel.fromCursor(cursorQuery).getId()))) {
                            try {
                                AssetFileDescriptor assetFileDescriptorOpenAssetFileDescriptor = ProgramTableAdapter.this.mContext.getContentResolver().openAssetFileDescriptor(TvContract.buildChannelLogoUri(TvContract.buildChannelUri(Long.parseLong(channel.getId()))), "r");
                                if (assetFileDescriptorOpenAssetFileDescriptor != null) {
                                    updateChannelLogo(BitmapFactory.decodeFileDescriptor(assetFileDescriptorOpenAssetFileDescriptor.getFileDescriptor()));
                                    return;
                                }
                                return;
                            } catch (FileNotFoundException unused) {
                                return;
                            }
                        }
                    }
                    return;
                }
                return;
            }
            this.imageLoader.get(channel.getSquareArtwork().urlWithSize(new Point(200, 200), 1.0d).toString(), new ImageLoader.ImageListener() { // from class: com.dotscreen.portal.ui.screens.guide.ProgramTableAdapter.ProgramRowHolder.3
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                }

                @Override // com.android.volley.toolbox.ImageLoader.ImageListener
                public void onResponse(ImageLoader.ImageContainer imageContainer, boolean z) {
                    if (ProgramRowHolder.this.mChannel == null || ProgramRowHolder.this.mChannel.getId() != channel.getId()) {
                        return;
                    }
                    ProgramRowHolder.this.updateChannelLogo(imageContainer.getBitmap());
                }
            });
        }

        @Override // com.dotscreen.portal.ui.screens.guide.ProgramRow.ChildFocusListener
        public void onChildFocus(View view, View view2) {
            if (view2 == null) {
                return;
            }
            this.mSelectedEntry = ((ProgramItemMegaCell) view2).getMTableEntry();
        }

        public void updateInputLogo(int i, boolean z) {
            Channel channel;
            if (this.mChannel == null) {
                this.mInputLogoView.setVisibility(8);
                this.mIsInputLogoVisible = false;
                return;
            }
            if (!z && ((channel = ProgramTableAdapter.this.mProgramManager.getChannel(i)) == null || !this.mChannel.getId().equals(channel.getId()))) {
                z = true;
            }
            if (z) {
                if (this.mIsInputLogoVisible) {
                    return;
                }
                this.mIsInputLogoVisible = true;
                if (this.mChannel.getSquareArtwork() == null || this.mChannel.getSquareArtwork().urlWithSize(new Point(ProgramTableAdapter.this.mChannelLogoWidth, ProgramTableAdapter.this.mChannelLogoHeight), 1.0d) == null) {
                    return;
                }
                this.imageLoader.get(this.mChannel.getSquareArtwork().urlWithSize(new Point(200, 200), 1.0d).toString(), new ImageLoader.ImageListener() { // from class: com.dotscreen.portal.ui.screens.guide.ProgramTableAdapter.ProgramRowHolder.4
                    @Override // com.android.volley.Response.ErrorListener
                    public void onErrorResponse(VolleyError volleyError) {
                    }

                    @Override // com.android.volley.toolbox.ImageLoader.ImageListener
                    public void onResponse(ImageLoader.ImageContainer imageContainer, boolean z2) {
                        ProgramRowHolder.this.updateInputLogoInternal(imageContainer.getBitmap());
                    }
                });
                return;
            }
            this.mInputLogoView.setVisibility(8);
            this.mInputLogoView.setImageDrawable(null);
            this.mIsInputLogoVisible = false;
        }

        private void updateTextView(TextView textView, String str) {
            if (!TextUtils.isEmpty(str)) {
                textView.setVisibility(0);
                textView.setText(str);
            } else {
                textView.setVisibility(8);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void updateChannelLogo(Bitmap bitmap) {
            this.mChannelLogoView.setImageBitmap(bitmap);
            this.mChannelNameView.setVisibility(8);
            this.mChannelLogoView.setVisibility(0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void updateInputLogoInternal(Bitmap bitmap) {
            if (this.mIsInputLogoVisible) {
                this.mInputLogoView.setImageBitmap(bitmap);
                this.mInputLogoView.setVisibility(0);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onHorizontalScrolled() {
            if (this.mDetailInAnimator != null) {
                ProgramTableAdapter.this.mHandler.removeCallbacks(this.mDetailInStarter);
                ProgramTableAdapter.this.mHandler.postDelayed(this.mDetailInStarter, ProgramTableAdapter.this.mAnimationDuration);
            }
        }
    }
}
