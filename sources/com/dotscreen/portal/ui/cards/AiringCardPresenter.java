package com.dotscreen.portal.ui.cards;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.leanback.widget.Presenter;
import com.dotscreen.bokit.model.Artwork;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.tv.common.bokit.ChannelExtraCaster;
import com.dotscreen.tv.common.bokit.RightVChannel;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.dotscreen.tv.common.search.LocalSearchProvider;
import java.net.URL;
import java.text.SimpleDateFormat;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AiringCardPresenter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B)\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020!H\u0016J\u0012\u0010\"\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u001a\u0010\f\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018¨\u0006#"}, d2 = {"Lcom/dotscreen/portal/ui/cards/AiringCardPresenter;", "Landroidx/leanback/widget/Presenter;", ToolboxAnalyticsModuleKt.M_WIDTH, "", ToolboxAnalyticsModuleKt.M_HEIGHT, "adultPresenter", "", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;)V", "TAG", "", "kotlin.jvm.PlatformType", "Ljava/lang/Boolean;", "canShowInteractive", "getCanShowInteractive", "()Z", "setCanShowInteractive", "(Z)V", "context", "Landroid/content/Context;", "dateFormat", "Ljava/text/SimpleDateFormat;", "dateFormatHeure", "defaultCardImage", "Landroid/graphics/drawable/Drawable;", "Ljava/lang/Integer;", "onBindViewHolder", "", "viewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "onUnbindViewHolder", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public class AiringCardPresenter extends Presenter {
    private final String TAG;
    private final Boolean adultPresenter;
    private boolean canShowInteractive;
    private Context context;
    private SimpleDateFormat dateFormat;
    private SimpleDateFormat dateFormatHeure;
    private Drawable defaultCardImage;
    private final Integer height;
    private final Integer width;

    public AiringCardPresenter() {
        this(null, null, null, 7, null);
    }

    @Override // androidx.leanback.widget.Presenter
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
    }

    public AiringCardPresenter(Integer num, Integer num2, Boolean bool) {
        this.width = num;
        this.height = num2;
        this.adultPresenter = bool;
        this.TAG = AiringCardPresenter.class.getSimpleName();
    }

    public /* synthetic */ AiringCardPresenter(Integer num, Integer num2, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (Integer) null : num, (i & 2) != 0 ? (Integer) null : num2, (i & 4) != 0 ? false : bool);
    }

    public final boolean getCanShowInteractive() {
        return this.canShowInteractive;
    }

    public final void setCanShowInteractive(boolean z) {
        this.canShowInteractive = z;
    }

    @Override // androidx.leanback.widget.Presenter
    public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
        Resources resources;
        Resources resources2;
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        this.context = parent.getContext();
        this.dateFormatHeure = new SimpleDateFormat("HH':'mm");
        this.dateFormat = new SimpleDateFormat("dd/MM HH':'mm");
        Integer numValueOf = null;
        this.defaultCardImage = parent.getResources().getDrawable(R.drawable.ph_horizontal, null);
        Context context = parent.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "parent.context");
        AiringCardView airingCardView = new AiringCardView(context, null, 0, 6, null);
        if (this.canShowInteractive) {
            airingCardView.canShowInteractive(true);
        }
        Integer numValueOf2 = this.width;
        if (numValueOf2 == null) {
            Context context2 = this.context;
            numValueOf2 = (context2 == null || (resources2 = context2.getResources()) == null) ? null : Integer.valueOf(resources2.getDimensionPixelSize(R.dimen.airing_card_width));
        }
        int iIntValue = numValueOf2 != null ? numValueOf2.intValue() : 0;
        Integer num = this.height;
        if (num != null) {
            numValueOf = num;
        } else {
            Context context3 = this.context;
            if (context3 != null && (resources = context3.getResources()) != null) {
                numValueOf = Integer.valueOf(resources.getDimensionPixelSize(R.dimen.airing_card_height));
            }
        }
        int iIntValue2 = numValueOf != null ? numValueOf.intValue() : 0;
        airingCardView.setLayoutParams(new ViewGroup.LayoutParams(iIntValue, iIntValue2));
        airingCardView.setFocusable(true);
        airingCardView.setFocusableInTouchMode(true);
        View childAt = airingCardView.getChildAt(0);
        Intrinsics.checkExpressionValueIsNotNull(childAt, "cardView.getChildAt(0)");
        ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
        Intrinsics.checkExpressionValueIsNotNull(layoutParams, "cardView.getChildAt(0).layoutParams");
        layoutParams.width = iIntValue;
        layoutParams.height = iIntValue2;
        View childAt2 = airingCardView.getChildAt(0);
        Intrinsics.checkExpressionValueIsNotNull(childAt2, "cardView.getChildAt(0)");
        childAt2.setLayoutParams(layoutParams);
        return new Presenter.ViewHolder(airingCardView);
    }

    @Override // androidx.leanback.widget.Presenter
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        URL urlUrlWithSize;
        URL urlUrlWithSize2;
        URL urlUrlWithSize3;
        float dimension;
        float dimension2;
        Intrinsics.checkParameterIsNotNull(viewHolder, "viewHolder");
        Intrinsics.checkParameterIsNotNull(item, "item");
        View view = viewHolder.view;
        if (view == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.cards.AiringCardView");
        }
        AiringCardView airingCardView = (AiringCardView) view;
        if (item instanceof TVAiring) {
            TVAiring tVAiring = (TVAiring) item;
            Artwork stillArtwork = tVAiring.getStillArtwork();
            if (stillArtwork != null) {
                Integer num = this.width;
                if (num != null) {
                    dimension = num.intValue();
                } else {
                    Context context = this.context;
                    if (context == null) {
                        Intrinsics.throwNpe();
                    }
                    dimension = context.getResources().getDimension(R.dimen.airing_card_width);
                }
                Context context2 = this.context;
                if (context2 == null) {
                    Intrinsics.throwNpe();
                }
                int fraction = (int) (dimension * context2.getResources().getFraction(R.fraction.lb_focus_zoom_factor_small, 1, 1));
                Integer num2 = this.height;
                if (num2 != null) {
                    dimension2 = num2.intValue();
                } else {
                    Context context3 = this.context;
                    if (context3 == null) {
                        Intrinsics.throwNpe();
                    }
                    dimension2 = context3.getResources().getDimension(R.dimen.airing_card_height);
                }
                Context context4 = this.context;
                if (context4 == null) {
                    Intrinsics.throwNpe();
                }
                urlUrlWithSize2 = stillArtwork.urlWithSize(new Point(fraction, (int) (dimension2 * context4.getResources().getFraction(R.fraction.lb_focus_zoom_factor_small, 1, 1))), 1.0d);
            } else {
                urlUrlWithSize2 = null;
            }
            airingCardView.setAiringImage(String.valueOf(urlUrlWithSize2));
            Artwork squareArtwork = tVAiring.getChannel().getSquareArtwork();
            if (squareArtwork != null) {
                Context context5 = this.context;
                if (context5 == null) {
                    Intrinsics.throwNpe();
                }
                float dimension3 = context5.getResources().getDimension(R.dimen.airing_card_channel_logo_width);
                Context context6 = this.context;
                if (context6 == null) {
                    Intrinsics.throwNpe();
                }
                int fraction2 = (int) (dimension3 * context6.getResources().getFraction(R.fraction.lb_focus_zoom_factor_small, 1, 1));
                Context context7 = this.context;
                if (context7 == null) {
                    Intrinsics.throwNpe();
                }
                float dimension4 = context7.getResources().getDimension(R.dimen.airing_card_channel_logo_width);
                Context context8 = this.context;
                if (context8 == null) {
                    Intrinsics.throwNpe();
                }
                urlUrlWithSize3 = squareArtwork.urlWithSize(new Point(fraction2, (int) (dimension4 * context8.getResources().getFraction(R.fraction.lb_focus_zoom_factor_small, 1, 1))), 1.0d);
            } else {
                urlUrlWithSize3 = null;
            }
            airingCardView.setChannelLogoImage(String.valueOf(urlUrlWithSize3));
            airingCardView.setTitle(tVAiring.getTitle());
            Log.v(this.TAG, " >>>>>>>>" + tVAiring.getChannel().getTitle());
            airingCardView.isInteractiveChannel(tVAiring.getChannel());
            airingCardView.setProgress((int) (tVAiring.getEndDate().getTime() - tVAiring.getStartDate().getTime()), (int) (System.currentTimeMillis() - tVAiring.getStartDate().getTime()));
            if (tVAiring.isPast()) {
                SimpleDateFormat simpleDateFormat = this.dateFormat;
                if (simpleDateFormat == null) {
                    Intrinsics.throwNpe();
                }
                String str = simpleDateFormat.format(tVAiring.getStartDate());
                Intrinsics.checkExpressionValueIsNotNull(str, "dateFormat!!.format(item.startDate)");
                airingCardView.setPastTime(str);
            } else {
                SimpleDateFormat simpleDateFormat2 = this.dateFormatHeure;
                if (simpleDateFormat2 == null) {
                    Intrinsics.throwNpe();
                }
                String str2 = simpleDateFormat2.format(tVAiring.getStartDate());
                Intrinsics.checkExpressionValueIsNotNull(str2, "dateFormatHeure!!.format(item.startDate)");
                SimpleDateFormat simpleDateFormat3 = this.dateFormatHeure;
                if (simpleDateFormat3 == null) {
                    Intrinsics.throwNpe();
                }
                String str3 = simpleDateFormat3.format(tVAiring.getEndDate());
                Intrinsics.checkExpressionValueIsNotNull(str3, "dateFormatHeure!!.format(item.endDate)");
                airingCardView.setTime(str2, str3, tVAiring.getStartDate().getTime());
            }
            airingCardView.setAiringId(tVAiring.getChannel().getId());
            if (tVAiring.getChannel().getZappingNumber() < 10) {
                airingCardView.setChannelNumber("000" + tVAiring.getChannel().getZappingNumber());
                return;
            }
            if (tVAiring.getChannel().getZappingNumber() < 100) {
                airingCardView.setChannelNumber("00" + tVAiring.getChannel().getZappingNumber());
                return;
            }
            if (tVAiring.getChannel().getZappingNumber() < 1000) {
                airingCardView.setChannelNumber(LocalSearchProvider.NO_LIVE_CONTENTS + tVAiring.getChannel().getZappingNumber());
                return;
            }
            airingCardView.setChannelNumber("" + tVAiring.getChannel().getZappingNumber());
            return;
        }
        if (item instanceof RightVChannel) {
            RightVChannel rightVChannel = (RightVChannel) item;
            if (rightVChannel.getIsInteracive()) {
                ChannelExtraCaster channelExtraCaster = rightVChannel.getExtras().getCasters().get("interactive");
                airingCardView.setAiringImage(channelExtraCaster != null ? channelExtraCaster.getBackground() : null);
            } else {
                airingCardView.setAiringImage(null);
            }
            Artwork squareArtwork2 = rightVChannel.getSquareArtwork();
            if (squareArtwork2 != null) {
                Context context9 = this.context;
                if (context9 == null) {
                    Intrinsics.throwNpe();
                }
                float dimension5 = context9.getResources().getDimension(R.dimen.airing_card_channel_logo_width);
                Context context10 = this.context;
                if (context10 == null) {
                    Intrinsics.throwNpe();
                }
                int fraction3 = (int) (dimension5 * context10.getResources().getFraction(R.fraction.lb_focus_zoom_factor_small, 1, 1));
                Context context11 = this.context;
                if (context11 == null) {
                    Intrinsics.throwNpe();
                }
                float dimension6 = context11.getResources().getDimension(R.dimen.airing_card_channel_logo_width);
                Context context12 = this.context;
                if (context12 == null) {
                    Intrinsics.throwNpe();
                }
                urlUrlWithSize = squareArtwork2.urlWithSize(new Point(fraction3, (int) (dimension6 * context12.getResources().getFraction(R.fraction.lb_focus_zoom_factor_small, 1, 1))), 1.0d);
            } else {
                urlUrlWithSize = null;
            }
            airingCardView.setChannelLogoImage(urlUrlWithSize != null ? urlUrlWithSize.toString() : null);
            if (rightVChannel.getZappingNumber() < 10) {
                airingCardView.setChannelNumber("000" + rightVChannel.getZappingNumber());
            } else if (rightVChannel.getZappingNumber() < 100) {
                airingCardView.setChannelNumber("00" + rightVChannel.getZappingNumber());
            } else if (rightVChannel.getZappingNumber() < 1000) {
                airingCardView.setChannelNumber(LocalSearchProvider.NO_LIVE_CONTENTS + rightVChannel.getZappingNumber());
            } else {
                airingCardView.setChannelNumber("" + rightVChannel.getZappingNumber());
            }
            airingCardView.setTitle(null);
            airingCardView.setNoTime();
        }
    }
}
