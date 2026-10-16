package com.dotscreen.portal.ui.cards;

import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.PresenterSelector;
import com.dotscreen.bokit.model.CatalogSection;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.Content;
import com.dotscreen.bokit.model.Episode;
import com.dotscreen.bokit.model.Movie;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.model.TVRecording;
import com.dotscreen.bokit.model.TVSeason;
import com.dotscreen.bokit.model.TVSeries;
import com.dotscreen.portal.ads.Ad;
import com.dotscreen.portal.model.ApplicationWidget;
import com.dotscreen.portal.model.BaseWidget;
import com.dotscreen.portal.model.HomeWidget;
import com.dotscreen.portal.model.LoadingWidget;
import com.dotscreen.tv.common.bokit.CatchupAsVOD;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HomePresenterSelector.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0018\u00002\u00020\u0001B[\b\u0007\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\u000bJ\u0012\u0010P\u001a\u0004\u0018\u00010Q2\u0006\u0010R\u001a\u00020SH\u0016R\u0012\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0012\u001a\u0004\b\u0015\u0010\u0016R\u001b\u0010\u0018\u001a\u00020\u00198BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u0012\u001a\u0004\b\u001a\u0010\u001bR\u001b\u0010\u001d\u001a\u00020\u001e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\u0012\u001a\u0004\b\u001f\u0010 R\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\"R\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\"R\u001b\u0010#\u001a\u00020$8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b'\u0010\u0012\u001a\u0004\b%\u0010&R\u001b\u0010(\u001a\u00020)8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b,\u0010\u0012\u001a\u0004\b*\u0010+R\u001b\u0010-\u001a\u00020.8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b1\u0010\u0012\u001a\u0004\b/\u00100R\u001b\u00102\u001a\u0002038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b6\u0010\u0012\u001a\u0004\b4\u00105R\u001b\u00107\u001a\u0002088BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b;\u0010\u0012\u001a\u0004\b9\u0010:R\u001b\u0010<\u001a\u00020=8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b@\u0010\u0012\u001a\u0004\b>\u0010?R\u0012\u0010\n\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR\u001b\u0010A\u001a\u00020B8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bE\u0010\u0012\u001a\u0004\bC\u0010DR\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\"R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\"R\u001b\u0010F\u001a\u00020G8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bJ\u0010\u0012\u001a\u0004\bH\u0010IR\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\"R\u001b\u0010K\u001a\u00020L8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bO\u0010\u0012\u001a\u0004\bM\u0010N¨\u0006T"}, d2 = {"Lcom/dotscreen/portal/ui/cards/HomePresenterSelector;", "Landroidx/leanback/widget/PresenterSelector;", "moreWidth", "", "moreHeight", "contentWidth", "contentHeight", "placeHolder", "adultPresenter", "", "isCatchup", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "airingCardPresenter", "Lcom/dotscreen/portal/ui/cards/AiringCardPresenter;", "getAiringCardPresenter", "()Lcom/dotscreen/portal/ui/cards/AiringCardPresenter;", "airingCardPresenter$delegate", "Lkotlin/Lazy;", "appCardPresenter", "Lcom/dotscreen/portal/ui/cards/AppCardPresenter;", "getAppCardPresenter", "()Lcom/dotscreen/portal/ui/cards/AppCardPresenter;", "appCardPresenter$delegate", "catchupAsVODCardPresenter", "Lcom/dotscreen/portal/ui/cards/CatchupAsVODPresenter;", "getCatchupAsVODCardPresenter", "()Lcom/dotscreen/portal/ui/cards/CatchupAsVODPresenter;", "catchupAsVODCardPresenter$delegate", "channelCardPresenter", "Lcom/dotscreen/portal/ui/cards/ChannelCardPresenter;", "getChannelCardPresenter", "()Lcom/dotscreen/portal/ui/cards/ChannelCardPresenter;", "channelCardPresenter$delegate", "Ljava/lang/Integer;", "customInAdSwimlaneNativeAdPresenter", "Lcom/dotscreen/portal/ui/cards/CustomInAdSwimlaneNativeAdPresenter;", "getCustomInAdSwimlaneNativeAdPresenter", "()Lcom/dotscreen/portal/ui/cards/CustomInAdSwimlaneNativeAdPresenter;", "customInAdSwimlaneNativeAdPresenter$delegate", "customInSettingsSwimlaneNativeAdPresenter", "Lcom/dotscreen/portal/ui/cards/CustomInSettingsSwimlaneNativeAdPresenter;", "getCustomInSettingsSwimlaneNativeAdPresenter", "()Lcom/dotscreen/portal/ui/cards/CustomInSettingsSwimlaneNativeAdPresenter;", "customInSettingsSwimlaneNativeAdPresenter$delegate", "defaultInSwimlandNativeAdPresenter", "Lcom/dotscreen/portal/ui/cards/DefaultInSwimlaneNativeAdPresenter;", "getDefaultInSwimlandNativeAdPresenter", "()Lcom/dotscreen/portal/ui/cards/DefaultInSwimlaneNativeAdPresenter;", "defaultInSwimlandNativeAdPresenter$delegate", "emptyCardPresenter", "Lcom/dotscreen/portal/ui/cards/EmptyCardPresenter;", "getEmptyCardPresenter", "()Lcom/dotscreen/portal/ui/cards/EmptyCardPresenter;", "emptyCardPresenter$delegate", "episodeCardPresenter", "Lcom/dotscreen/portal/ui/cards/EpisodeCardPresenter;", "getEpisodeCardPresenter", "()Lcom/dotscreen/portal/ui/cards/EpisodeCardPresenter;", "episodeCardPresenter$delegate", "homeWidgetCardPresenter", "Lcom/dotscreen/portal/ui/cards/HomeWidgetCardPresenter;", "getHomeWidgetCardPresenter", "()Lcom/dotscreen/portal/ui/cards/HomeWidgetCardPresenter;", "homeWidgetCardPresenter$delegate", "loadingCardPresenter", "Lcom/dotscreen/portal/ui/cards/LoadingCardPresenter;", "getLoadingCardPresenter", "()Lcom/dotscreen/portal/ui/cards/LoadingCardPresenter;", "loadingCardPresenter$delegate", "movieCardPresenter", "Lcom/dotscreen/portal/ui/cards/VODItemCardPresenter;", "getMovieCardPresenter", "()Lcom/dotscreen/portal/ui/cards/VODItemCardPresenter;", "movieCardPresenter$delegate", "textCardPresenter", "Lcom/dotscreen/portal/ui/cards/TextCardPresenter;", "getTextCardPresenter", "()Lcom/dotscreen/portal/ui/cards/TextCardPresenter;", "textCardPresenter$delegate", "getPresenter", "Landroidx/leanback/widget/Presenter;", "item", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class HomePresenterSelector extends PresenterSelector {
    private final Boolean adultPresenter;

    /* JADX INFO: renamed from: airingCardPresenter$delegate, reason: from kotlin metadata */
    private final Lazy airingCardPresenter;

    /* JADX INFO: renamed from: appCardPresenter$delegate, reason: from kotlin metadata */
    private final Lazy appCardPresenter;

    /* JADX INFO: renamed from: catchupAsVODCardPresenter$delegate, reason: from kotlin metadata */
    private final Lazy catchupAsVODCardPresenter;

    /* JADX INFO: renamed from: channelCardPresenter$delegate, reason: from kotlin metadata */
    private final Lazy channelCardPresenter;
    private final Integer contentHeight;
    private final Integer contentWidth;

    /* JADX INFO: renamed from: customInAdSwimlaneNativeAdPresenter$delegate, reason: from kotlin metadata */
    private final Lazy customInAdSwimlaneNativeAdPresenter;

    /* JADX INFO: renamed from: customInSettingsSwimlaneNativeAdPresenter$delegate, reason: from kotlin metadata */
    private final Lazy customInSettingsSwimlaneNativeAdPresenter;

    /* JADX INFO: renamed from: defaultInSwimlandNativeAdPresenter$delegate, reason: from kotlin metadata */
    private final Lazy defaultInSwimlandNativeAdPresenter;

    /* JADX INFO: renamed from: emptyCardPresenter$delegate, reason: from kotlin metadata */
    private final Lazy emptyCardPresenter;

    /* JADX INFO: renamed from: episodeCardPresenter$delegate, reason: from kotlin metadata */
    private final Lazy episodeCardPresenter;

    /* JADX INFO: renamed from: homeWidgetCardPresenter$delegate, reason: from kotlin metadata */
    private final Lazy homeWidgetCardPresenter;
    private final Boolean isCatchup;

    /* JADX INFO: renamed from: loadingCardPresenter$delegate, reason: from kotlin metadata */
    private final Lazy loadingCardPresenter;
    private final Integer moreHeight;
    private final Integer moreWidth;

    /* JADX INFO: renamed from: movieCardPresenter$delegate, reason: from kotlin metadata */
    private final Lazy movieCardPresenter;
    private final Integer placeHolder;

    /* JADX INFO: renamed from: textCardPresenter$delegate, reason: from kotlin metadata */
    private final Lazy textCardPresenter;

    public HomePresenterSelector() {
        this(null, null, null, null, null, null, null, 127, null);
    }

    public HomePresenterSelector(Integer num) {
        this(num, null, null, null, null, null, null, 126, null);
    }

    public HomePresenterSelector(Integer num, Integer num2) {
        this(num, num2, null, null, null, null, null, 124, null);
    }

    public HomePresenterSelector(Integer num, Integer num2, Integer num3) {
        this(num, num2, num3, null, null, null, null, 120, null);
    }

    public HomePresenterSelector(Integer num, Integer num2, Integer num3, Integer num4) {
        this(num, num2, num3, num4, null, null, null, 112, null);
    }

    public HomePresenterSelector(Integer num, Integer num2, Integer num3, Integer num4, Integer num5) {
        this(num, num2, num3, num4, num5, null, null, 96, null);
    }

    public HomePresenterSelector(Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Boolean bool) {
        this(num, num2, num3, num4, num5, bool, null, 64, null);
    }

    private final AiringCardPresenter getAiringCardPresenter() {
        return (AiringCardPresenter) this.airingCardPresenter.getValue();
    }

    private final AppCardPresenter getAppCardPresenter() {
        return (AppCardPresenter) this.appCardPresenter.getValue();
    }

    private final CatchupAsVODPresenter getCatchupAsVODCardPresenter() {
        return (CatchupAsVODPresenter) this.catchupAsVODCardPresenter.getValue();
    }

    private final ChannelCardPresenter getChannelCardPresenter() {
        return (ChannelCardPresenter) this.channelCardPresenter.getValue();
    }

    private final CustomInAdSwimlaneNativeAdPresenter getCustomInAdSwimlaneNativeAdPresenter() {
        return (CustomInAdSwimlaneNativeAdPresenter) this.customInAdSwimlaneNativeAdPresenter.getValue();
    }

    private final CustomInSettingsSwimlaneNativeAdPresenter getCustomInSettingsSwimlaneNativeAdPresenter() {
        return (CustomInSettingsSwimlaneNativeAdPresenter) this.customInSettingsSwimlaneNativeAdPresenter.getValue();
    }

    private final DefaultInSwimlaneNativeAdPresenter getDefaultInSwimlandNativeAdPresenter() {
        return (DefaultInSwimlaneNativeAdPresenter) this.defaultInSwimlandNativeAdPresenter.getValue();
    }

    private final EmptyCardPresenter getEmptyCardPresenter() {
        return (EmptyCardPresenter) this.emptyCardPresenter.getValue();
    }

    private final EpisodeCardPresenter getEpisodeCardPresenter() {
        return (EpisodeCardPresenter) this.episodeCardPresenter.getValue();
    }

    private final HomeWidgetCardPresenter getHomeWidgetCardPresenter() {
        return (HomeWidgetCardPresenter) this.homeWidgetCardPresenter.getValue();
    }

    private final LoadingCardPresenter getLoadingCardPresenter() {
        return (LoadingCardPresenter) this.loadingCardPresenter.getValue();
    }

    private final VODItemCardPresenter getMovieCardPresenter() {
        return (VODItemCardPresenter) this.movieCardPresenter.getValue();
    }

    private final TextCardPresenter getTextCardPresenter() {
        return (TextCardPresenter) this.textCardPresenter.getValue();
    }

    public /* synthetic */ HomePresenterSelector(Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Boolean bool, Boolean bool2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (Integer) null : num, (i & 2) != 0 ? (Integer) null : num2, (i & 4) != 0 ? (Integer) null : num3, (i & 8) != 0 ? (Integer) null : num4, (i & 16) != 0 ? (Integer) null : num5, (i & 32) != 0 ? false : bool, (i & 64) != 0 ? false : bool2);
    }

    public HomePresenterSelector(Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Boolean bool, Boolean bool2) {
        this.moreWidth = num;
        this.moreHeight = num2;
        this.contentWidth = num3;
        this.contentHeight = num4;
        this.placeHolder = num5;
        this.adultPresenter = bool;
        this.isCatchup = bool2;
        this.airingCardPresenter = LazyKt.lazy(new Function0<AiringCardPresenter>() { // from class: com.dotscreen.portal.ui.cards.HomePresenterSelector$airingCardPresenter$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final AiringCardPresenter invoke() {
                return new AiringCardPresenter(null, null, this.this$0.adultPresenter, 3, null);
            }
        });
        this.catchupAsVODCardPresenter = LazyKt.lazy(new Function0<CatchupAsVODPresenter>() { // from class: com.dotscreen.portal.ui.cards.HomePresenterSelector$catchupAsVODCardPresenter$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CatchupAsVODPresenter invoke() {
                return new CatchupAsVODPresenter(this.this$0.contentWidth, this.this$0.contentHeight, this.this$0.placeHolder, this.this$0.adultPresenter);
            }
        });
        this.homeWidgetCardPresenter = LazyKt.lazy(new Function0<HomeWidgetCardPresenter>() { // from class: com.dotscreen.portal.ui.cards.HomePresenterSelector$homeWidgetCardPresenter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final HomeWidgetCardPresenter invoke() {
                return new HomeWidgetCardPresenter();
            }
        });
        this.movieCardPresenter = LazyKt.lazy(new Function0<VODItemCardPresenter>() { // from class: com.dotscreen.portal.ui.cards.HomePresenterSelector$movieCardPresenter$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final VODItemCardPresenter invoke() {
                return new VODItemCardPresenter(this.this$0.contentWidth, this.this$0.contentHeight, this.this$0.placeHolder, this.this$0.adultPresenter, this.this$0.isCatchup, false, 32, null);
            }
        });
        this.episodeCardPresenter = LazyKt.lazy(new Function0<EpisodeCardPresenter>() { // from class: com.dotscreen.portal.ui.cards.HomePresenterSelector$episodeCardPresenter$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final EpisodeCardPresenter invoke() {
                return new EpisodeCardPresenter(this.this$0.contentWidth, this.this$0.contentHeight, this.this$0.placeHolder);
            }
        });
        this.textCardPresenter = LazyKt.lazy(new Function0<TextCardPresenter>() { // from class: com.dotscreen.portal.ui.cards.HomePresenterSelector$textCardPresenter$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextCardPresenter invoke() {
                return new TextCardPresenter(this.this$0.moreWidth, this.this$0.moreHeight);
            }
        });
        this.loadingCardPresenter = LazyKt.lazy(new Function0<LoadingCardPresenter>() { // from class: com.dotscreen.portal.ui.cards.HomePresenterSelector$loadingCardPresenter$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LoadingCardPresenter invoke() {
                Integer num6 = this.this$0.contentWidth;
                if (num6 == null) {
                    num6 = this.this$0.moreWidth;
                }
                Integer num7 = this.this$0.contentHeight;
                if (num7 == null) {
                    num7 = this.this$0.moreHeight;
                }
                return new LoadingCardPresenter(num6, num7);
            }
        });
        this.appCardPresenter = LazyKt.lazy(new Function0<AppCardPresenter>() { // from class: com.dotscreen.portal.ui.cards.HomePresenterSelector$appCardPresenter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final AppCardPresenter invoke() {
                return new AppCardPresenter(null, null, 3, null);
            }
        });
        this.channelCardPresenter = LazyKt.lazy(new Function0<ChannelCardPresenter>() { // from class: com.dotscreen.portal.ui.cards.HomePresenterSelector$channelCardPresenter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ChannelCardPresenter invoke() {
                return new ChannelCardPresenter();
            }
        });
        this.defaultInSwimlandNativeAdPresenter = LazyKt.lazy(new Function0<DefaultInSwimlaneNativeAdPresenter>() { // from class: com.dotscreen.portal.ui.cards.HomePresenterSelector$defaultInSwimlandNativeAdPresenter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DefaultInSwimlaneNativeAdPresenter invoke() {
                return new DefaultInSwimlaneNativeAdPresenter();
            }
        });
        this.customInSettingsSwimlaneNativeAdPresenter = LazyKt.lazy(new Function0<CustomInSettingsSwimlaneNativeAdPresenter>() { // from class: com.dotscreen.portal.ui.cards.HomePresenterSelector$customInSettingsSwimlaneNativeAdPresenter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CustomInSettingsSwimlaneNativeAdPresenter invoke() {
                return new CustomInSettingsSwimlaneNativeAdPresenter();
            }
        });
        this.customInAdSwimlaneNativeAdPresenter = LazyKt.lazy(new Function0<CustomInAdSwimlaneNativeAdPresenter>() { // from class: com.dotscreen.portal.ui.cards.HomePresenterSelector$customInAdSwimlaneNativeAdPresenter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CustomInAdSwimlaneNativeAdPresenter invoke() {
                return new CustomInAdSwimlaneNativeAdPresenter();
            }
        });
        this.emptyCardPresenter = LazyKt.lazy(new Function0<EmptyCardPresenter>() { // from class: com.dotscreen.portal.ui.cards.HomePresenterSelector$emptyCardPresenter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final EmptyCardPresenter invoke() {
                return new EmptyCardPresenter(null, null, 3, null);
            }
        });
    }

    @Override // androidx.leanback.widget.PresenterSelector
    public Presenter getPresenter(Object item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        if ((item instanceof TVAiring) || (item instanceof TVRecording)) {
            return getAiringCardPresenter();
        }
        if ((item instanceof Channel) || (item instanceof CatalogSection)) {
            return getChannelCardPresenter();
        }
        if (item instanceof Episode) {
            return getEpisodeCardPresenter();
        }
        if ((item instanceof Movie) || (item instanceof TVSeries) || (item instanceof TVSeason) || (item instanceof Content)) {
            return getMovieCardPresenter();
        }
        if (item instanceof ApplicationWidget) {
            return getAppCardPresenter();
        }
        if (item instanceof HomeWidget) {
            return getHomeWidgetCardPresenter();
        }
        if (item instanceof LoadingWidget) {
            return getLoadingCardPresenter();
        }
        if (item instanceof BaseWidget) {
            return getTextCardPresenter();
        }
        if (item instanceof CatchupAsVOD) {
            return getCatchupAsVODCardPresenter();
        }
        if (item instanceof Ad) {
            Ad ad = (Ad) item;
            if (ad.getDsNativeAdType() == Ad.DSNativeAdType.IN_SWIMLANE) {
                return getDefaultInSwimlandNativeAdPresenter();
            }
            if (Intrinsics.areEqual(ad.getDsAdUnitId(), "DS_HOME_PAGE_CUSTOM_IN_SETTINGS_SWIMLANE")) {
                return getCustomInSettingsSwimlaneNativeAdPresenter();
            }
            if (Intrinsics.areEqual(ad.getDsAdUnitId(), "DS_HOME_PAGE_CUSTOM_IN_AD_SWIMLANE")) {
                return getCustomInAdSwimlaneNativeAdPresenter();
            }
            return getEmptyCardPresenter();
        }
        return getEmptyCardPresenter();
    }
}
