package com.dotscreen.portal.ui.screens.epg;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.media.tv.TvContract;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.HorizontalGridPresenter;
import androidx.leanback.widget.HorizontalGridView;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.dotscreen.bokit.model.BrowsableItem;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.farkit.design.DesignManager;
import com.dotscreen.farkit.tools.DSLog;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.ads.Ad;
import com.dotscreen.portal.ads.IFloatingAdListener;
import com.dotscreen.portal.ads.INativeAdListener;
import com.dotscreen.portal.model.EPGViewModel;
import com.dotscreen.portal.model.OptimizerEPGViewModel;
import com.dotscreen.portal.ui.cards.AiringCardPresenter;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import com.dotscreen.portal.ui.views.FontTextView;
import com.dotscreen.portal.ui.views.InLayoutNativeAdBannerView;
import com.dotscreen.tv.common.bokit.ChannelExtraCaster;
import com.dotscreen.tv.common.bokit.RightVChannel;
import com.dotscreen.tv.common.bokit.RightVUser;
import com.dotscreen.tv.common.providers.LastChannelsProvider;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.dotscreen.tv.common.utils.TIFCompanionLibraryExtensionKt;
import com.dotscreen.tv.ui.DirectChannelAccessView;
import com.dotscreen.tv.ui.dialog.CheckListDialog;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: EPGActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003:\u00019B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\"\u001a\u00020#H\u0016J\u0012\u0010$\u001a\u0004\u0018\u00010\u000e2\u0006\u0010%\u001a\u00020&H\u0002J \u0010'\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000e0(2\u0006\u0010)\u001a\u00020\u000eH\u0002J\u0010\u0010*\u001a\u00020#2\u0006\u0010+\u001a\u00020\u001cH\u0002J\u0012\u0010,\u001a\u00020#2\b\u0010-\u001a\u0004\u0018\u00010.H\u0014J\b\u0010/\u001a\u00020#H\u0016J\b\u00100\u001a\u00020#H\u0016J\b\u00101\u001a\u00020#H\u0014J\b\u00102\u001a\u00020\u001eH\u0016J*\u00103\u001a\u00020#2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0014H\u0002J\u0010\u00104\u001a\u00020#2\u0006\u00105\u001a\u000206H\u0002J\b\u00107\u001a\u00020#H\u0002J\u0010\u00108\u001a\u00020#2\u0006\u00105\u001a\u000206H\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\bX\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u000e\u0012\b\u0012\u00060\bj\u0002`\u0015\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0018\u001a\n\u0018\u00010\bj\u0004\u0018\u0001`\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082.¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lcom/dotscreen/portal/ui/screens/epg/EPGActivity;", "Lcom/dotscreen/portal/ui/screens/BaseFragmentActivity;", "Lcom/dotscreen/portal/ads/INativeAdListener;", "Lcom/dotscreen/portal/ads/IFloatingAdListener;", "()V", "OnPreDrawListener", "Landroid/view/ViewTreeObserver$OnPreDrawListener;", "TAG", "", "kotlin.jvm.PlatformType", "adapter", "Landroidx/leanback/widget/ArrayObjectAdapter;", "airings", "", "Lcom/dotscreen/bokit/model/Channel;", "Lcom/dotscreen/bokit/model/TVAiring;", "backgroundDesignPath", "getBackgroundDesignPath", "()Ljava/lang/String;", "channelGenres", "", "Lcom/dotscreen/bokit/model/Genre;", "channelId", "channels", "currentGenre", "h_grid_view", "Landroidx/leanback/widget/HorizontalGridView;", "position", "", "requestFirstFocus", "", "section", "topAdBanner", "Lcom/dotscreen/portal/ui/views/InLayoutNativeAdBannerView;", "clearSwimlaneNativeAds", "", "getItemChannel", "item", "", "getSurroundingChannels", "Lkotlin/Pair;", "channel", "loadData", "time", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onFloatingAdReceived", "onNativeAdReceived", "onResume", "overrideFloatingAdHandling", "updateAdapter", "updateFloatingAds", "category", "Lcom/dotscreen/portal/ui/screens/epg/EPGActivity$BannerAdEPGCategory;", "updateSection", "updateTopBannerAd", "BannerAdEPGCategory", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class EPGActivity extends BaseFragmentActivity implements INativeAdListener, IFloatingAdListener {
    private ViewTreeObserver.OnPreDrawListener OnPreDrawListener;
    private HashMap _$_findViewCache;
    private ArrayObjectAdapter adapter;
    private Map<Channel, ? extends TVAiring> airings;
    private List<String> channelGenres;
    private List<? extends Channel> channels;
    private String currentGenre;
    private HorizontalGridView h_grid_view;
    private int position;
    private String section;
    private InLayoutNativeAdBannerView topAdBanner;
    private final String TAG = EPGActivity.class.getSimpleName();
    private boolean requestFirstFocus = true;
    private String channelId = "";
    private final String backgroundDesignPath = "custom.epg.background.image";

    /* JADX INFO: compiled from: EPGActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/dotscreen/portal/ui/screens/epg/EPGActivity$BannerAdEPGCategory;", "", "(Ljava/lang/String;I)V", "NOW", "TONIGHT", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private enum BannerAdEPGCategory {
        NOW,
        TONIGHT
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BannerAdEPGCategory.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[BannerAdEPGCategory.NOW.ordinal()] = 1;
            $EnumSwitchMapping$0[BannerAdEPGCategory.TONIGHT.ordinal()] = 2;
        }
    }

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

    @Override // com.dotscreen.portal.ads.INativeAdListener
    public void clearSwimlaneNativeAds() {
    }

    @Override // com.dotscreen.portal.ads.IFloatingAdListener
    public boolean overrideFloatingAdHandling() {
        return true;
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
        Context applicationContext2 = getApplicationContext();
        if (applicationContext2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        ((PortalApplication) applicationContext2).addNativeAdAddedListener(this);
        Context applicationContext3 = getApplicationContext();
        if (applicationContext3 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        ((PortalApplication) applicationContext3).addFloatingAdListener(this);
        setContentView(R.layout.activity_epg);
        Intent intent = getIntent();
        if (intent != null) {
            Uri data = intent.getData();
            Intrinsics.checkExpressionValueIsNotNull(data, "intent.data");
            String queryParameter = data.getQueryParameter("section");
            if (queryParameter == null) {
                queryParameter = "now";
            }
            this.section = queryParameter;
            Log.d(this.TAG, "Show section #" + this.section);
        }
        View viewFindViewById = findViewById(R.id.activity_epg_in_layout_native_ad_banner_view_top);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById(R.id.activi…ative_ad_banner_view_top)");
        this.topAdBanner = (InLayoutNativeAdBannerView) viewFindViewById;
        this.OnPreDrawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: com.dotscreen.portal.ui.screens.epg.EPGActivity.onCreate.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public final boolean onPreDraw() {
                HorizontalGridView horizontalGridView = EPGActivity.this.h_grid_view;
                if (horizontalGridView == null) {
                    Intrinsics.throwNpe();
                }
                if (horizontalGridView.getChildCount() <= 0) {
                    return true;
                }
                HorizontalGridView horizontalGridView2 = EPGActivity.this.h_grid_view;
                if (horizontalGridView2 == null) {
                    Intrinsics.throwNpe();
                }
                horizontalGridView2.getViewTreeObserver().removeOnPreDrawListener(EPGActivity.this.OnPreDrawListener);
                HorizontalGridView horizontalGridView3 = EPGActivity.this.h_grid_view;
                if (horizontalGridView3 == null) {
                    Intrinsics.throwNpe();
                }
                horizontalGridView3.setSelectedPosition(EPGActivity.this.position);
                HorizontalGridView horizontalGridView4 = EPGActivity.this.h_grid_view;
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
                Context applicationContext4 = getApplicationContext();
                if (applicationContext4 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                }
                PortalApplication.Config value = ((PortalApplication) applicationContext4).getConfig().getValue();
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
        horizontalGridPresenter.setOnItemViewClickedListener(new OnItemViewClickedListener() { // from class: com.dotscreen.portal.ui.screens.epg.EPGActivity$onCreate$listener$1
            @Override // androidx.leanback.widget.BaseOnItemViewClickedListener
            public final void onItemClicked(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Row row) {
                String string;
                try {
                    if (obj instanceof TVAiring) {
                        DSLog.INSTANCE.a("Clicked on the " + ((TVAiring) obj).getTitle() + " channel TVAiring");
                        this.this$0.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("megacable://com.dotscreen.megacable.portal/epg/details?channel_id=" + ((TVAiring) obj).getChannel().getId() + "&start=" + ((TVAiring) obj).getStartDate().getTime())));
                        return;
                    }
                    if (obj instanceof RightVChannel) {
                        if (((RightVChannel) obj).getIsInteracive()) {
                            DSLog.INSTANCE.a("Clicked on the " + ((RightVChannel) obj).getTitle() + " RightVChannel");
                            ChannelExtraCaster channelExtraCaster = ((RightVChannel) obj).getExtras().getCasters().get("interactive");
                            Intent intent2 = null;
                            String src = channelExtraCaster != null ? channelExtraCaster.getSrc() : null;
                            if (src != null) {
                                int iHashCode = src.hashCode();
                                if (iHashCode == -787338382) {
                                    if (src.equals("Netflix")) {
                                        Pair surroundingChannels = this.this$0.getSurroundingChannels((Channel) obj);
                                        String str = "chID=" + ((RightVChannel) obj).getZappingNumber();
                                        StringBuilder sb = new StringBuilder();
                                        sb.append(str);
                                        String string2 = "";
                                        if (surroundingChannels.getFirst() != null) {
                                            StringBuilder sb2 = new StringBuilder();
                                            sb2.append("&prevChID=");
                                            Object first = surroundingChannels.getFirst();
                                            if (first == null) {
                                                Intrinsics.throwNpe();
                                            }
                                            sb2.append(((Channel) first).getZappingNumber());
                                            string = sb2.toString();
                                        } else {
                                            string = "";
                                        }
                                        sb.append(string);
                                        String string3 = sb.toString();
                                        StringBuilder sb3 = new StringBuilder();
                                        sb3.append(string3);
                                        if (surroundingChannels.getSecond() != null) {
                                            StringBuilder sb4 = new StringBuilder();
                                            sb4.append("&nextChID=");
                                            Object second = surroundingChannels.getSecond();
                                            if (second == null) {
                                                Intrinsics.throwNpe();
                                            }
                                            sb4.append(((Channel) second).getZappingNumber());
                                            string2 = sb4.toString();
                                        }
                                        sb3.append(string2);
                                        intent2 = new Intent("android.intent.action.VIEW", Uri.parse("http://www.netflix.com/browse?iid=6a70ad21&source_type_payload=" + URLEncoder.encode(sb3.toString() + "&category=OTHER")));
                                    }
                                } else if (iHashCode == 1964569124 && src.equals("Amazon")) {
                                    intent2 = this.this$0.getPackageManager().getLeanbackLaunchIntentForPackage("com.amazon.amazonvideo.livingroom");
                                }
                            }
                            if (intent2 != null) {
                                this.this$0.startActivity(CommonUtils.patchInteractiveIntent(intent2));
                                return;
                            }
                            return;
                        }
                        this.this$0.startActivity(new Intent("android.intent.action.VIEW", CommonUtils.getLiveTvDeeplink((Context) this.this$0, (Channel) obj, (Boolean) true)));
                    }
                } catch (Throwable th) {
                    Log.w(this.this$0.TAG, "Error while starting Intent", th);
                }
            }
        });
        AiringCardPresenter airingCardPresenter = new AiringCardPresenter(null, null, null, 7, null);
        airingCardPresenter.setCanShowInteractive(true);
        this.adapter = new ArrayObjectAdapter(airingCardPresenter);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.browse_grid_dock);
        HorizontalGridPresenter.ViewHolder viewHolderOnCreateViewHolder = horizontalGridPresenter.onCreateViewHolder(viewGroup);
        viewGroup.addView(viewHolderOnCreateViewHolder.view);
        horizontalGridPresenter.onBindViewHolder(viewHolderOnCreateViewHolder, this.adapter);
        View view = viewHolderOnCreateViewHolder.view;
        if (view == null) {
            throw new TypeCastException("null cannot be cast to non-null type androidx.leanback.widget.HorizontalGridView");
        }
        this.h_grid_view = (HorizontalGridView) view;
        EPGActivity ePGActivity = this;
        if (PortalApplication.INSTANCE.getSingleton(ePGActivity).getDegradedMode()) {
            Toast.makeText(ePGActivity, R.string.noAvailable, 1).show();
            finish();
            return;
        }
        updateSection();
        DirectChannelAccessView directChannelAccessView = (DirectChannelAccessView) findViewById(R.id.dca_view);
        if (directChannelAccessView != null) {
            directChannelAccessView.setShowTitleName(false);
            directChannelAccessView.setOnVisibilityChangedListener(new DirectChannelAccessView.OnVisibilityChangedListener() { // from class: com.dotscreen.portal.ui.screens.epg.EPGActivity$onCreate$$inlined$let$lambda$1
                @Override // com.dotscreen.tv.ui.DirectChannelAccessView.OnVisibilityChangedListener
                public void onVisibilityChanged(boolean visible) {
                    ImageView imageView = (ImageView) this.this$0.findViewById(R.id.dca_background);
                    if (imageView != null) {
                        imageView.setVisibility(visible ? 0 : 8);
                    }
                }
            });
            directChannelAccessView.setOnChannelSelectedListener(new DirectChannelAccessView.OnChannelSelectedListener() { // from class: com.dotscreen.portal.ui.screens.epg.EPGActivity$onCreate$$inlined$let$lambda$2
                @Override // com.dotscreen.tv.ui.DirectChannelAccessView.OnChannelSelectedListener
                public void onChannelSelected(int channelNumber) {
                    ArrayList arrayList;
                    boolean zContains;
                    boolean zContains2;
                    List list = this.this$0.channels;
                    ArrayList arrayList2 = null;
                    if (list != null) {
                        ArrayList arrayList3 = new ArrayList();
                        for (Object obj : list) {
                            Channel channel = (Channel) obj;
                            if (this.this$0.currentGenre != null) {
                                List<String> genres = channel.getGenres();
                                String str = this.this$0.currentGenre;
                                if (str == null) {
                                    Intrinsics.throwNpe();
                                }
                                zContains2 = genres.contains(str);
                            } else {
                                zContains2 = true;
                            }
                            if (zContains2) {
                                arrayList3.add(obj);
                            }
                        }
                        arrayList = arrayList3;
                    } else {
                        arrayList = null;
                    }
                    int iIndexOf = 0;
                    if (arrayList != null) {
                        ArrayList arrayList4 = new ArrayList();
                        for (Object obj2 : arrayList) {
                            if (((Channel) obj2).getZappingNumber() == channelNumber) {
                                arrayList4.add(obj2);
                            }
                        }
                        arrayList2 = arrayList4;
                    }
                    HorizontalGridView horizontalGridView = this.this$0.h_grid_view;
                    if (horizontalGridView == null) {
                        Intrinsics.throwNpe();
                    }
                    if (horizontalGridView.getChildCount() > 0) {
                        if ((arrayList2 != null ? arrayList2.size() : 0) > 0) {
                            HorizontalGridView horizontalGridView2 = this.this$0.h_grid_view;
                            if (horizontalGridView2 == null) {
                                Intrinsics.throwNpe();
                            }
                            horizontalGridView2.getViewTreeObserver().removeOnPreDrawListener(this.this$0.OnPreDrawListener);
                            HorizontalGridView horizontalGridView3 = this.this$0.h_grid_view;
                            if (horizontalGridView3 == null) {
                                Intrinsics.throwNpe();
                            }
                            List list2 = this.this$0.channels;
                            if (list2 != null) {
                                ArrayList arrayList5 = new ArrayList();
                                for (Object obj3 : list2) {
                                    Channel channel2 = (Channel) obj3;
                                    if (this.this$0.currentGenre != null) {
                                        List<String> genres2 = channel2.getGenres();
                                        String str2 = this.this$0.currentGenre;
                                        if (str2 == null) {
                                            Intrinsics.throwNpe();
                                        }
                                        zContains = genres2.contains(str2);
                                    } else {
                                        zContains = true;
                                    }
                                    if (zContains) {
                                        arrayList5.add(obj3);
                                    }
                                }
                                ArrayList arrayList6 = arrayList5;
                                if (arrayList2 == null) {
                                    Intrinsics.throwNpe();
                                }
                                iIndexOf = arrayList6.indexOf(arrayList2.get(0));
                            }
                            horizontalGridView3.setSelectedPosition(iIndexOf);
                            HorizontalGridView horizontalGridView4 = this.this$0.h_grid_view;
                            if (horizontalGridView4 == null) {
                                Intrinsics.throwNpe();
                            }
                            horizontalGridView4.requestFocus();
                            return;
                        }
                    }
                    if ((arrayList != null ? arrayList.size() : 0) > 0) {
                        if (arrayList == null) {
                            Intrinsics.throwNpe();
                        }
                        int size = arrayList.size();
                        int iAbs = 0;
                        int i = 0;
                        while (iIndexOf < size) {
                            if (iAbs == 0 || Math.abs(channelNumber - ((Channel) arrayList.get(iIndexOf)).getZappingNumber()) < iAbs) {
                                iAbs = Math.abs(channelNumber - ((Channel) arrayList.get(iIndexOf)).getZappingNumber());
                                i = iIndexOf;
                            }
                            iIndexOf++;
                        }
                        HorizontalGridView horizontalGridView5 = this.this$0.h_grid_view;
                        if (horizontalGridView5 == null) {
                            Intrinsics.throwNpe();
                        }
                        horizontalGridView5.getViewTreeObserver().removeOnPreDrawListener(this.this$0.OnPreDrawListener);
                        HorizontalGridView horizontalGridView6 = this.this$0.h_grid_view;
                        if (horizontalGridView6 == null) {
                            Intrinsics.throwNpe();
                        }
                        horizontalGridView6.setSelectedPosition(i);
                        HorizontalGridView horizontalGridView7 = this.this$0.h_grid_view;
                        if (horizontalGridView7 == null) {
                            Intrinsics.throwNpe();
                        }
                        horizontalGridView7.requestFocus();
                    }
                }
            });
        }
        FontTextView fontTextView2 = (FontTextView) findViewById(R.id.tabNow);
        if (fontTextView2 != null) {
            fontTextView2.setOnClickListener(new View.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.epg.EPGActivity.onCreate.4
                @Override // android.view.View.OnClickListener
                public final void onClick(View it2) {
                    DSLog.INSTANCE.a("Clicked on now");
                    EPGActivity.this.updateTopBannerAd(BannerAdEPGCategory.NOW);
                    EPGActivity.this.updateFloatingAds(BannerAdEPGCategory.NOW);
                    Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                    it2.setSelected(true);
                    FontTextView fontTextView3 = (FontTextView) EPGActivity.this.findViewById(R.id.tabTonight);
                    if (fontTextView3 != null) {
                        fontTextView3.setSelected(false);
                    }
                    EPGActivity.this.loadData(1);
                }
            });
        }
        FontTextView fontTextView3 = (FontTextView) findViewById(R.id.tabTonight);
        if (fontTextView3 != null) {
            fontTextView3.setOnClickListener(new View.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.epg.EPGActivity.onCreate.5
                @Override // android.view.View.OnClickListener
                public final void onClick(View it2) {
                    DSLog.INSTANCE.a("Clicked on tonight");
                    EPGActivity.this.updateTopBannerAd(BannerAdEPGCategory.TONIGHT);
                    EPGActivity.this.updateFloatingAds(BannerAdEPGCategory.TONIGHT);
                    Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                    it2.setSelected(true);
                    FontTextView fontTextView4 = (FontTextView) EPGActivity.this.findViewById(R.id.tabNow);
                    if (fontTextView4 != null) {
                        fontTextView4.setSelected(false);
                    }
                    EPGActivity.this.loadData(3);
                }
            });
        }
        FontTextView fontTextView4 = (FontTextView) findViewById(R.id.tabGuide);
        if (fontTextView4 != null) {
            fontTextView4.setOnClickListener(new View.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.epg.EPGActivity.onCreate.6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    DSLog.INSTANCE.a("Clicked on guide TV");
                    EPGActivity.this.startActivity(Intent.parseUri("megacable://com.dotscreen.megacable.portal/epg/guide", 0));
                }
            });
        }
        final FontTextView fontTextView5 = (FontTextView) findViewById(R.id.tabFilter);
        if (fontTextView5 != null) {
            fontTextView5.setText(getString(R.string.epg_tab_filter, new Object[]{getString(R.string.epg_filter_all)}));
            fontTextView5.setOnClickListener(new View.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.epg.EPGActivity$onCreate$$inlined$let$lambda$3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    DSLog.INSTANCE.a("Clicked on Filters");
                    List<String> listMutableListOf = CollectionsKt.mutableListOf(this.getString(R.string.epg_filter_all));
                    List list = this.channelGenres;
                    if (list == null) {
                        Intrinsics.throwNpe();
                    }
                    listMutableListOf.addAll(list);
                    CheckListDialog.Builder itemList = new CheckListDialog.Builder(this).setTitle(R.string.dialog_select_category_title).setItemList(listMutableListOf);
                    List list2 = this.channelGenres;
                    if (list2 == null) {
                        Intrinsics.throwNpe();
                    }
                    String str = this.currentGenre;
                    if (str == null) {
                        str = "";
                    }
                    itemList.setSelectedItem(list2.indexOf(str) + 1).setOnItemClickedListener(new CheckListDialog.OnItemClickedListener() { // from class: com.dotscreen.portal.ui.screens.epg.EPGActivity$onCreate$$inlined$let$lambda$3.1
                        @Override // com.dotscreen.tv.ui.dialog.CheckListDialog.OnItemClickedListener
                        public void onItemClicked(CheckListDialog dialog, int itemIndex) {
                            Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                            HorizontalGridView horizontalGridView = this.h_grid_view;
                            if (horizontalGridView == null) {
                                Intrinsics.throwNpe();
                            }
                            horizontalGridView.setSelectedPosition(0);
                            if (itemIndex == 0) {
                                this.currentGenre = (String) null;
                                EPGActivity ePGActivity2 = this;
                                Map map = this.airings;
                                if (map == null) {
                                    Intrinsics.throwNpe();
                                }
                                List list3 = this.channels;
                                if (list3 == null) {
                                    Intrinsics.throwNpe();
                                }
                                ePGActivity2.updateAdapter(map, list3);
                                fontTextView5.setText(this.getString(R.string.epg_tab_filter, new Object[]{this.getString(R.string.epg_filter_all)}));
                            } else {
                                EPGActivity ePGActivity3 = this;
                                List list4 = this.channelGenres;
                                if (list4 == null) {
                                    Intrinsics.throwNpe();
                                }
                                ePGActivity3.currentGenre = (String) list4.get(itemIndex - 1);
                                EPGActivity ePGActivity4 = this;
                                Map map2 = this.airings;
                                if (map2 == null) {
                                    Intrinsics.throwNpe();
                                }
                                List list5 = this.channels;
                                if (list5 == null) {
                                    Intrinsics.throwNpe();
                                }
                                ArrayList arrayList = new ArrayList();
                                for (Object obj : list5) {
                                    List<String> genres = ((Channel) obj).getGenres();
                                    String str2 = this.currentGenre;
                                    if (str2 == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    if (genres.contains(str2)) {
                                        arrayList.add(obj);
                                    }
                                }
                                ePGActivity4.updateAdapter(map2, arrayList);
                                fontTextView5.setText(this.getString(R.string.epg_tab_filter, new Object[]{this.currentGenre}));
                            }
                            dialog.dismiss();
                        }
                    }).create().show();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateTopBannerAd(BannerAdEPGCategory category) {
        Ad adFromCurrentNativeAdList;
        DSLog.INSTANCE.adLogM("[HANDLE AD][NATIVE][" + category.name() + "](delegated) -- update native ads");
        int i = WhenMappings.$EnumSwitchMapping$0[category.ordinal()];
        if (i == 1) {
            Application application = getApplication();
            if (application == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
            }
            String string = getString(R.string.DS_TV_GUIDE_NOW_PAGE_IN_LAYOUT_TOP);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.DS_TV…E_NOW_PAGE_IN_LAYOUT_TOP)");
            adFromCurrentNativeAdList = ((PortalApplication) application).getAdFromCurrentNativeAdList(string);
        } else {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            Application application2 = getApplication();
            if (application2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
            }
            String string2 = getString(R.string.DS_TV_GUIDE_TONIGHT_PAGE_IN_LAYOUT_TOP);
            Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.DS_TV…NIGHT_PAGE_IN_LAYOUT_TOP)");
            adFromCurrentNativeAdList = ((PortalApplication) application2).getAdFromCurrentNativeAdList(string2);
        }
        Ad ad = adFromCurrentNativeAdList;
        InLayoutNativeAdBannerView inLayoutNativeAdBannerView = this.topAdBanner;
        if (inLayoutNativeAdBannerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("topAdBanner");
        }
        inLayoutNativeAdBannerView.hideBanner();
        if (ad != null) {
            InLayoutNativeAdBannerView inLayoutNativeAdBannerView2 = this.topAdBanner;
            if (inLayoutNativeAdBannerView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("topAdBanner");
            }
            String simpleName = getClass().getSimpleName();
            Intrinsics.checkExpressionValueIsNotNull(simpleName, "this.javaClass.simpleName");
            inLayoutNativeAdBannerView2.setAdImageResource(simpleName, hashCode(), ad, (Ad.ImageResource) CollectionsKt.first((List) ad.getImageResources()), 0, new Function4<String, String, Integer, Long, Unit>() { // from class: com.dotscreen.portal.ui.screens.epg.EPGActivity.updateTopBannerAd.1
                {
                    super(4);
                }

                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Unit invoke(String str, String str2, Integer num, Long l) {
                    invoke(str, str2, num.intValue(), l.longValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(String runtimeTag, String owningActivityName, int i2, long j) {
                    Intrinsics.checkParameterIsNotNull(runtimeTag, "runtimeTag");
                    Intrinsics.checkParameterIsNotNull(owningActivityName, "owningActivityName");
                    Application application3 = EPGActivity.this.getApplication();
                    if (application3 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                    }
                    ((PortalApplication) application3).startBannerHidingCountDown(runtimeTag, owningActivityName, i2, j);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateFloatingAds(BannerAdEPGCategory category) {
        DSLog.INSTANCE.adLogM("[HANDLE AD][FLOATING][" + category.name() + "](delegated) -- update floating ads");
        Application application = getApplication();
        if (application == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        PortalApplication portalApplication = (PortalApplication) application;
        ArrayList arrayList = new ArrayList();
        portalApplication.hideDisplayedFloatingAds();
        for (Ad ad : portalApplication.getCurrentFloatingAdList()) {
            String dsAdUnitId = ad.getDsAdUnitId();
            if (dsAdUnitId != null) {
                Locale locale = Locale.ROOT;
                Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.ROOT");
                if (dsAdUnitId == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                String upperCase = dsAdUnitId.toUpperCase(locale);
                Intrinsics.checkNotNullExpressionValue(upperCase, "(this as java.lang.String).toUpperCase(locale)");
                if (upperCase != null) {
                    String str = upperCase;
                    String strTake = StringsKt.take(category.name(), 3);
                    Locale locale2 = Locale.ROOT;
                    Intrinsics.checkExpressionValueIsNotNull(locale2, "Locale.ROOT");
                    if (strTake == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                    }
                    String upperCase2 = strTake.toUpperCase(locale2);
                    Intrinsics.checkNotNullExpressionValue(upperCase2, "(this as java.lang.String).toUpperCase(locale)");
                    if (StringsKt.contains$default((CharSequence) str, (CharSequence) upperCase2, false, 2, (Object) null)) {
                        arrayList.add(ad);
                    }
                } else {
                    continue;
                }
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            portalApplication.handleFloatingAd((Ad) it.next());
        }
    }

    private final Channel getItemChannel(Object item) {
        if (item instanceof TVAiring) {
            return ((TVAiring) item).getChannel();
        }
        if (item instanceof Channel) {
            return (Channel) item;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Pair<Channel, Channel> getSurroundingChannels(Channel channel) {
        Channel itemChannel;
        ArrayObjectAdapter arrayObjectAdapter = this.adapter;
        Channel itemChannel2 = null;
        if (arrayObjectAdapter != null) {
            int size = arrayObjectAdapter.size();
            for (int i = 0; i < size; i++) {
                if (Intrinsics.areEqual(arrayObjectAdapter.get(i), channel)) {
                    if (i == 0) {
                        itemChannel = null;
                    } else {
                        Object obj = arrayObjectAdapter.get(i - 1);
                        Intrinsics.checkExpressionValueIsNotNull(obj, "adapter[i - 1]");
                        itemChannel = getItemChannel(obj);
                    }
                    if (i != arrayObjectAdapter.size() - 1) {
                        Object obj2 = arrayObjectAdapter.get(i + 1);
                        Intrinsics.checkExpressionValueIsNotNull(obj2, "adapter[i + 1]");
                        itemChannel2 = getItemChannel(obj2);
                    }
                    return TuplesKt.to(itemChannel, itemChannel2);
                }
            }
        }
        return TuplesKt.to(null, null);
    }

    private final void updateSection() {
        String str = this.section;
        if (str == null) {
            return;
        }
        int iHashCode = str.hashCode();
        if (iHashCode != -1141101955) {
            if (iHashCode == 109270 && str.equals("now")) {
                FontTextView fontTextView = (FontTextView) findViewById(R.id.tabNow);
                if (fontTextView != null) {
                    fontTextView.setSelected(true);
                    fontTextView.requestFocus();
                }
                loadData(1);
                return;
            }
            return;
        }
        if (str.equals("tonight")) {
            FontTextView fontTextView2 = (FontTextView) findViewById(R.id.tabTonight);
            if (fontTextView2 != null) {
                fontTextView2.setSelected(true);
                fontTextView2.requestFocus();
            }
            loadData(3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void updateAdapter(Map<Channel, ? extends TVAiring> airings, List<? extends Channel> channels) {
        ArrayObjectAdapter arrayObjectAdapter = this.adapter;
        if (arrayObjectAdapter != null) {
            int size = channels.size();
            for (int i = 0; i < size; i++) {
                TVAiring tVAiring = airings.get(channels.get(i));
                if (tVAiring == null) {
                    tVAiring = channels.get(i);
                }
                BrowsableItem browsableItem = tVAiring;
                if (i < arrayObjectAdapter.size()) {
                    if (arrayObjectAdapter.get(i).hashCode() != browsableItem.hashCode()) {
                        arrayObjectAdapter.replace(i, browsableItem);
                    }
                } else {
                    arrayObjectAdapter.add(browsableItem);
                }
            }
            if (arrayObjectAdapter.size() > channels.size()) {
                arrayObjectAdapter.removeItems(channels.size(), arrayObjectAdapter.size());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void loadData(int time) {
        setLoaderVisible(true);
        setBlockInput(true);
        ViewModel viewModel = ViewModelProviders.of(this, new OptimizerEPGViewModel.Factory(this)).get(EPGViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…EPGViewModel::class.java)");
        ((EPGViewModel) viewModel).getValue(time).observe(this, new Observer<EPGViewModel.Data>() { // from class: com.dotscreen.portal.ui.screens.epg.EPGActivity.loadData.1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(EPGViewModel.Data data) throws IOException {
                RightVUser user;
                EPGActivity.this.setLoaderVisible(false);
                EPGActivity.this.setBlockInput(false);
                if (data == null) {
                    Toast.makeText(EPGActivity.this, R.string.technical_error_retry_later, 1).show();
                    EPGActivity.this.finish();
                }
                if (data != null) {
                    EPGActivity ePGActivity = EPGActivity.this;
                    List<Channel> channels = data.getChannels();
                    ArrayList arrayList = new ArrayList();
                    for (T t : channels) {
                        Channel channel = (Channel) t;
                        if (channel == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVChannel");
                        }
                        RightVChannel rightVChannel = (RightVChannel) channel;
                        if (!rightVChannel.getIsHD() || rightVChannel.getExtras().getChannelVariantIdentifier() == null) {
                            arrayList.add(t);
                        }
                    }
                    ePGActivity.channels = arrayList;
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    for (TVAiring tVAiring : data.getAirings()) {
                        linkedHashMap.put(tVAiring.getChannel(), tVAiring);
                    }
                    EPGActivity.this.airings = MapsKt.toMap(linkedHashMap);
                    EPGActivity ePGActivity2 = EPGActivity.this;
                    Map map = ePGActivity2.airings;
                    if (map == null) {
                        Intrinsics.throwNpe();
                    }
                    List list = EPGActivity.this.channels;
                    if (list == null) {
                        Intrinsics.throwNpe();
                    }
                    ePGActivity2.updateAdapter(map, list);
                    if (EPGActivity.this.requestFirstFocus) {
                        if (!data.getAirings().isEmpty()) {
                            LiveData<PortalApplication.Config> config = PortalApplication.INSTANCE.getSingleton(EPGActivity.this).getConfig();
                            Intrinsics.checkExpressionValueIsNotNull(config, "PortalApplication.getSingleton(this).config");
                            PortalApplication.Config value = config.getValue();
                            if (value != null && (user = value.getUser()) != null) {
                                Cursor cursorQuery = EPGActivity.this.getContentResolver().query(LastChannelsProvider.INSTANCE.getCONTENT_URI(), null, LastChannelsProvider.INSTANCE.getCOLUMN_PROFILE_ID() + "=?", new String[]{user.getCompassId()}, null);
                                if (cursorQuery != null) {
                                    Cursor cursor = cursorQuery;
                                    Throwable th = (Throwable) null;
                                    try {
                                        Cursor cursor2 = cursor;
                                        if (cursor2.moveToLast()) {
                                            EPGActivity.this.channelId = "";
                                            Cursor cursorQuery2 = EPGActivity.this.getContentResolver().query(TvContract.buildChannelUri(cursor2.getLong(1)), com.google.android.media.tv.companionlibrary.model.Channel.PROJECTION, null, null, null);
                                            if (cursorQuery2 != null) {
                                                Cursor cursor3 = cursorQuery2;
                                                Throwable th2 = (Throwable) null;
                                                try {
                                                    Cursor cursor4 = cursor3;
                                                    if (cursor4.moveToFirst()) {
                                                        com.google.android.media.tv.companionlibrary.model.Channel tifChannel = com.google.android.media.tv.companionlibrary.model.Channel.fromCursor(cursor4);
                                                        Intrinsics.checkExpressionValueIsNotNull(tifChannel, "tifChannel");
                                                        Context applicationContext = EPGActivity.this.getApplicationContext();
                                                        if (applicationContext == null) {
                                                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                                                        }
                                                        Channel optimizerChannel = TIFCompanionLibraryExtensionKt.getOptimizerChannel(tifChannel, ((PortalApplication) applicationContext).getBOService());
                                                        int iIndexOf = CollectionsKt.indexOf((List<? extends Channel>) data.getChannels(), optimizerChannel);
                                                        if (iIndexOf >= 0) {
                                                            EPGActivity.this.position = iIndexOf;
                                                            EPGActivity ePGActivity3 = EPGActivity.this;
                                                            if (optimizerChannel == null) {
                                                                Intrinsics.throwNpe();
                                                            }
                                                            ePGActivity3.channelId = optimizerChannel.getId();
                                                        }
                                                    }
                                                    Unit unit = Unit.INSTANCE;
                                                    CloseableKt.closeFinally(cursor3, th2);
                                                } catch (Throwable th3) {
                                                    try {
                                                        throw th3;
                                                    } catch (Throwable th4) {
                                                        CloseableKt.closeFinally(cursor3, th3);
                                                        throw th4;
                                                    }
                                                }
                                            }
                                        }
                                        Unit unit2 = Unit.INSTANCE;
                                        CloseableKt.closeFinally(cursor, th);
                                    } catch (Throwable th5) {
                                        try {
                                            throw th5;
                                        } catch (Throwable th6) {
                                            CloseableKt.closeFinally(cursor, th5);
                                            throw th6;
                                        }
                                    }
                                }
                            }
                            HorizontalGridView horizontalGridView = EPGActivity.this.h_grid_view;
                            if (horizontalGridView == null) {
                                Intrinsics.throwNpe();
                            }
                            horizontalGridView.setSelectedPosition(EPGActivity.this.position);
                            HorizontalGridView horizontalGridView2 = EPGActivity.this.h_grid_view;
                            if (horizontalGridView2 == null) {
                                Intrinsics.throwNpe();
                            }
                            horizontalGridView2.getViewTreeObserver().addOnPreDrawListener(EPGActivity.this.OnPreDrawListener);
                        }
                        EPGActivity.this.requestFirstFocus = false;
                    }
                    LinkedHashSet linkedHashSet = new LinkedHashSet();
                    Iterator<T> it = data.getChannels().iterator();
                    while (it.hasNext()) {
                        linkedHashSet.addAll(((Channel) it.next()).getGenres());
                    }
                    EPGActivity.this.channelGenres = CollectionsKt.sorted(linkedHashSet);
                    Log.d(EPGActivity.this.TAG, "channelGenres = " + EPGActivity.this.channelGenres);
                    String str = EPGActivity.this.currentGenre;
                    if (str != null) {
                        EPGActivity ePGActivity4 = EPGActivity.this;
                        Map map2 = ePGActivity4.airings;
                        if (map2 == null) {
                            Intrinsics.throwNpe();
                        }
                        List list2 = EPGActivity.this.channels;
                        if (list2 == null) {
                            Intrinsics.throwNpe();
                        }
                        ArrayList arrayList2 = new ArrayList();
                        for (T t2 : list2) {
                            if (((Channel) t2).getGenres().contains(str)) {
                                arrayList2.add(t2);
                            }
                        }
                        ePGActivity4.updateAdapter(map2, arrayList2);
                    }
                }
            }
        });
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        requestVisibleBehind(true);
    }

    @Override // com.dotscreen.portal.ads.INativeAdListener
    public void onNativeAdReceived() {
        Log.d(this.TAG, "onNativeAdReceived");
        String str = this.section;
        if (str == null) {
            return;
        }
        int iHashCode = str.hashCode();
        if (iHashCode != -1141101955) {
            if (iHashCode == 109270 && str.equals("now")) {
                updateTopBannerAd(BannerAdEPGCategory.NOW);
                return;
            }
            return;
        }
        if (str.equals("tonight")) {
            updateTopBannerAd(BannerAdEPGCategory.TONIGHT);
        }
    }

    @Override // com.dotscreen.portal.ads.IFloatingAdListener
    public void onFloatingAdReceived() {
        Log.d(this.TAG, "onFloatingAdReceived");
        String str = this.section;
        if (str == null) {
            return;
        }
        int iHashCode = str.hashCode();
        if (iHashCode != -1141101955) {
            if (iHashCode == 109270 && str.equals("now")) {
                updateFloatingAds(BannerAdEPGCategory.NOW);
                return;
            }
            return;
        }
        if (str.equals("tonight")) {
            updateFloatingAds(BannerAdEPGCategory.TONIGHT);
        }
    }
}
