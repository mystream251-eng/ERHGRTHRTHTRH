package com.dotscreen.portal.ui.screens.catchup;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import android.widget.Toast;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.HorizontalGridPresenter;
import androidx.leanback.widget.HorizontalGridView;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.farkit.design.DesignManager;
import com.dotscreen.farkit.tools.DSLog;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.model.GuideViewModel;
import com.dotscreen.portal.model.OptimizerGuideViewModel;
import com.dotscreen.portal.model.TIFGuideViewModel;
import com.dotscreen.portal.ui.cards.AiringCardPresenter;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import com.dotscreen.portal.ui.views.FontTextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CatchupDetailActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\u0012\u0010\u001c\u001a\u00020\u001b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\b\u0010\u001f\u001a\u00020\u001bH\u0014J\u001e\u0010 \u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\f2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\n\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u0006X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/dotscreen/portal/ui/screens/catchup/CatchupDetailActivity;", "Lcom/dotscreen/portal/ui/screens/BaseFragmentActivity;", "()V", "OnPreDrawListener", "Landroid/view/ViewTreeObserver$OnPreDrawListener;", "TAG", "", "kotlin.jvm.PlatformType", "adapter", "Landroidx/leanback/widget/ArrayObjectAdapter;", "airings", "Lkotlin/Pair;", "Lcom/dotscreen/bokit/model/Channel;", "", "Lcom/dotscreen/bokit/model/TVAiring;", "backgroundDesignPath", "getBackgroundDesignPath", "()Ljava/lang/String;", "channelId", "channel_catchup_duration", "", "h_grid_view", "Landroidx/leanback/widget/HorizontalGridView;", "position", "requestFirstFocus", "", "loadData", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "updateAdapter", "channel", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class CatchupDetailActivity extends BaseFragmentActivity {
    private ViewTreeObserver.OnPreDrawListener OnPreDrawListener;
    private HashMap _$_findViewCache;
    private ArrayObjectAdapter adapter;
    private Pair<? extends Channel, ? extends List<? extends TVAiring>> airings;
    private HorizontalGridView h_grid_view;
    private int position;
    private final String TAG = CatchupDetailActivity.class.getSimpleName();
    private boolean requestFirstFocus = true;
    private String channelId = "";
    private int channel_catchup_duration = 48;
    private final String backgroundDesignPath = "custom.epg.background.image";

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity
    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity
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

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity
    protected String getBackgroundDesignPath() {
        return this.backgroundDesignPath;
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context applicationContext = getApplicationContext();
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        if (((PortalApplication) applicationContext).getLoginErrorReason() != null) {
            finish();
        }
        setContentView(R.layout.activity_catchup_channel_detail);
        Intent intent = getIntent();
        if (intent != null) {
            Uri data = intent.getData();
            Intrinsics.checkExpressionValueIsNotNull(data, "intent.data");
            String queryParameter = data.getQueryParameter("channelId");
            if (queryParameter == null) {
                queryParameter = "";
            }
            this.channelId = queryParameter;
            String queryParameter2 = data.getQueryParameter("channelDuration");
            Intrinsics.checkExpressionValueIsNotNull(queryParameter2, "uri.getQueryParameter(\"channelDuration\")");
            this.channel_catchup_duration = Integer.parseInt(queryParameter2);
        }
        this.OnPreDrawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: com.dotscreen.portal.ui.screens.catchup.CatchupDetailActivity.onCreate.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public final boolean onPreDraw() {
                HorizontalGridView horizontalGridView = CatchupDetailActivity.this.h_grid_view;
                if (horizontalGridView == null) {
                    Intrinsics.throwNpe();
                }
                if (horizontalGridView.getChildCount() <= 0) {
                    return true;
                }
                HorizontalGridView horizontalGridView2 = CatchupDetailActivity.this.h_grid_view;
                if (horizontalGridView2 == null) {
                    Intrinsics.throwNpe();
                }
                horizontalGridView2.getViewTreeObserver().removeOnPreDrawListener(CatchupDetailActivity.this.OnPreDrawListener);
                HorizontalGridView horizontalGridView3 = CatchupDetailActivity.this.h_grid_view;
                if (horizontalGridView3 == null) {
                    Intrinsics.throwNpe();
                }
                horizontalGridView3.setSelectedPosition(CatchupDetailActivity.this.position);
                HorizontalGridView horizontalGridView4 = CatchupDetailActivity.this.h_grid_view;
                if (horizontalGridView4 == null) {
                    Intrinsics.throwNpe();
                }
                horizontalGridView4.requestFocus();
                return true;
            }
        };
        Iterator it = CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(R.id.tabNow), Integer.valueOf(R.id.tabTonight), Integer.valueOf(R.id.tabGuide), Integer.valueOf(R.id.tabFilter)}).iterator();
        while (it.hasNext()) {
            FontTextView fontTextView = (FontTextView) findViewById(((Number) it.next()).intValue());
            if (fontTextView != null && Build.VERSION.SDK_INT >= 26) {
                fontTextView.setSelection_v2(true);
                Context applicationContext2 = getApplicationContext();
                if (applicationContext2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                }
                PortalApplication.Config value = ((PortalApplication) applicationContext2).getConfig().getValue();
                if (value != null) {
                    DesignManager designManager = value.getDesignManager();
                    Drawable foreground = fontTextView.getForeground();
                    Intrinsics.checkExpressionValueIsNotNull(foreground, "it.foreground");
                    fontTextView.setForeground(designManager.tintDrawable(foreground, "standard.item.selected.outline.color"));
                    Integer color = value.getDesignManager().getColor("custom.epg.tab.default.textColor");
                    Integer color2 = value.getDesignManager().getColor("custom.epg.tab.selected.textColor");
                    if (color != null && color2 != null) {
                        fontTextView.setTextColor(new ColorStateList(new int[][]{new int[]{-16842908, -16842913}, new int[]{android.R.attr.state_focused}, new int[]{android.R.attr.state_selected}}, new int[]{color.intValue(), color2.intValue(), color2.intValue()}));
                    }
                }
            }
        }
        HorizontalGridPresenter horizontalGridPresenter = new HorizontalGridPresenter(1, false);
        horizontalGridPresenter.setNumberOfRows(3);
        horizontalGridPresenter.setShadowEnabled(true);
        horizontalGridPresenter.setOnItemViewClickedListener(new OnItemViewClickedListener() { // from class: com.dotscreen.portal.ui.screens.catchup.CatchupDetailActivity$onCreate$listener$1
            @Override // androidx.leanback.widget.BaseOnItemViewClickedListener
            public final void onItemClicked(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Row row) {
                try {
                    if (obj instanceof TVAiring) {
                        DSLog.INSTANCE.a("Clicked on an the " + ((TVAiring) obj).getTitle() + " channel");
                        this.this$0.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("megacable://com.dotscreen.megacable.portal/epg/details?channel_id=" + ((TVAiring) obj).getChannel().getId() + "&start=" + ((TVAiring) obj).getStartDate().getTime())));
                    }
                } catch (Throwable th) {
                    Log.w(this.this$0.TAG, "Error while starting Intent", th);
                }
            }
        });
        this.adapter = new ArrayObjectAdapter(new AiringCardPresenter(null, null, null, 7, null));
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.browse_grid_dock);
        HorizontalGridPresenter.ViewHolder viewHolderOnCreateViewHolder = horizontalGridPresenter.onCreateViewHolder(viewGroup);
        viewGroup.addView(viewHolderOnCreateViewHolder.view);
        horizontalGridPresenter.onBindViewHolder(viewHolderOnCreateViewHolder, this.adapter);
        View view = viewHolderOnCreateViewHolder.view;
        if (view == null) {
            throw new TypeCastException("null cannot be cast to non-null type androidx.leanback.widget.HorizontalGridView");
        }
        this.h_grid_view = (HorizontalGridView) view;
        CatchupDetailActivity catchupDetailActivity = this;
        if (PortalApplication.INSTANCE.getSingleton(catchupDetailActivity).getDegradedMode()) {
            Toast.makeText(catchupDetailActivity, R.string.noAvailable, 1).show();
            finish();
        } else {
            loadData();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateAdapter(Channel channel, List<? extends TVAiring> airings) {
        ArrayObjectAdapter arrayObjectAdapter = this.adapter;
        if (arrayObjectAdapter != null) {
            View viewFindViewById = findViewById(R.id.title);
            Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById<TextView>(R.id.title)");
            ((TextView) viewFindViewById).setText(getResources().getText(R.string.catchup_title) + " : " + channel.getTitle());
            if (airings.size() > 2 && airings.get(0).getStartDate().getTime() < airings.get(1).getStartDate().getTime()) {
                airings = CollectionsKt.asReversed(airings);
            }
            arrayObjectAdapter.addAll(0, airings);
        }
    }

    private final void loadData() {
        OptimizerGuideViewModel.Factory factory;
        setLoaderVisible(true);
        setBlockInput(true);
        Context applicationContext = getApplicationContext();
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        if (((PortalApplication) applicationContext).getDegradedMode()) {
            factory = new TIFGuideViewModel.Factory(this);
        } else {
            factory = new OptimizerGuideViewModel.Factory(this);
        }
        ViewModel viewModel = ViewModelProviders.of(this, factory).get(GuideViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…ideViewModel::class.java)");
        ((GuideViewModel) viewModel).getValue(9, this.channel_catchup_duration).observe(this, new Observer<GuideViewModel.Data>() { // from class: com.dotscreen.portal.ui.screens.catchup.CatchupDetailActivity.loadData.1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(GuideViewModel.Data data) {
                CatchupDetailActivity.this.setLoaderVisible(false);
                CatchupDetailActivity.this.setBlockInput(false);
                if (data == null) {
                    Toast.makeText(CatchupDetailActivity.this, R.string.technical_error_retry_later, 1).show();
                    CatchupDetailActivity.this.finish();
                }
                if (data != null) {
                    List<Channel> channels = data.getChannels();
                    ArrayList arrayList = new ArrayList();
                    for (T t : channels) {
                        if (Intrinsics.areEqual(((Channel) t).getId(), CatchupDetailActivity.this.channelId)) {
                            arrayList.add(t);
                        }
                    }
                    ArrayList arrayList2 = arrayList;
                    List<TVAiring> value = (List) null;
                    for (Map.Entry<String, List<TVAiring>> entry : data.getAiringPerChannelMap().entrySet()) {
                        if (Intrinsics.areEqual(entry.getKey(), CatchupDetailActivity.this.channelId)) {
                            value = entry.getValue();
                        }
                    }
                    if (arrayList2.size() > 0 && value != null) {
                        CatchupDetailActivity catchupDetailActivity = CatchupDetailActivity.this;
                        Channel channel = (Channel) arrayList2.get(0);
                        ArrayList arrayList3 = new ArrayList();
                        for (T t2 : value) {
                            TVAiring tVAiring = (TVAiring) t2;
                            if ((tVAiring.isLive() || tVAiring.isFuture()) ? false : true) {
                                arrayList3.add(t2);
                            }
                        }
                        catchupDetailActivity.updateAdapter(channel, arrayList3);
                        return;
                    }
                    Toast.makeText(CatchupDetailActivity.this, R.string.technical_error_retry_later, 1).show();
                }
            }
        });
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        requestVisibleBehind(true);
    }
}
