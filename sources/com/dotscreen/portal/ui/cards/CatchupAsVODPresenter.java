package com.dotscreen.portal.ui.cards;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import androidx.leanback.widget.Presenter;
import com.dotscreen.bokit.model.Artwork;
import com.dotscreen.bokit.model.ArtworkFormat;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.tv.common.bokit.CatchupAsVOD;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.dotscreen.tv.common.search.LocalSearchProvider;
import java.net.URL;
import java.text.SimpleDateFormat;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CatchupAsVODPresenter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B5\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0010\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0012\u0010 \u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0016R\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0016R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0016¨\u0006!"}, d2 = {"Lcom/dotscreen/portal/ui/cards/CatchupAsVODPresenter;", "Landroidx/leanback/widget/Presenter;", ToolboxAnalyticsModuleKt.M_WIDTH, "", ToolboxAnalyticsModuleKt.M_HEIGHT, "placeHolder", "adultPresenter", "", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;)V", "TAG", "", "kotlin.jvm.PlatformType", "Ljava/lang/Boolean;", "context", "Landroid/content/Context;", "dateFormat", "Ljava/text/SimpleDateFormat;", "dateFormatHeure", "defaultCardImage", "Landroid/graphics/drawable/Drawable;", "handler", "Landroid/os/Handler;", "Ljava/lang/Integer;", "onBindViewHolder", "", "viewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "onUnbindViewHolder", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public class CatchupAsVODPresenter extends Presenter {
    private final String TAG;
    private final Boolean adultPresenter;
    private Context context;
    private SimpleDateFormat dateFormat;
    private SimpleDateFormat dateFormatHeure;
    private Drawable defaultCardImage;
    private final Handler handler;
    private final Integer height;
    private final Integer placeHolder;
    private final Integer width;

    public CatchupAsVODPresenter() {
        this(null, null, null, null, 15, null);
    }

    @Override // androidx.leanback.widget.Presenter
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
    }

    public CatchupAsVODPresenter(Integer num, Integer num2, Integer num3, Boolean bool) {
        this.width = num;
        this.height = num2;
        this.placeHolder = num3;
        this.adultPresenter = bool;
        this.TAG = CatchupAsVODPresenter.class.getSimpleName();
        this.handler = new Handler();
    }

    public /* synthetic */ CatchupAsVODPresenter(Integer num, Integer num2, Integer num3, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (Integer) null : num, (i & 2) != 0 ? (Integer) null : num2, (i & 4) != 0 ? (Integer) null : num3, (i & 8) != 0 ? false : bool);
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
        CatchupAsVODCardView catchupAsVODCardView = new CatchupAsVODCardView(context, null, 0, 6, null);
        if (this.width != null && this.height != null) {
            catchupAsVODCardView.setLayoutParams(new ViewGroup.LayoutParams(this.width.intValue(), this.height.intValue()));
            if (this.width.intValue() > this.height.intValue() * 3) {
                catchupAsVODCardView.setArtworkFormat(ArtworkFormat.Banner);
            } else if (Intrinsics.compare(this.width.intValue(), this.height.intValue()) > 0) {
                catchupAsVODCardView.setArtworkFormat(ArtworkFormat.Still);
            } else {
                catchupAsVODCardView.setArtworkFormat(ArtworkFormat.Poster);
            }
        }
        catchupAsVODCardView.setPlaceHolder(this.placeHolder);
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
        catchupAsVODCardView.setLayoutParams(new ViewGroup.LayoutParams(iIntValue, iIntValue2));
        catchupAsVODCardView.setFocusable(true);
        catchupAsVODCardView.setFocusableInTouchMode(true);
        View childAt = catchupAsVODCardView.getChildAt(0);
        Intrinsics.checkExpressionValueIsNotNull(childAt, "cardView.getChildAt(0)");
        ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
        Intrinsics.checkExpressionValueIsNotNull(layoutParams, "cardView.getChildAt(0).layoutParams");
        layoutParams.width = iIntValue;
        layoutParams.height = iIntValue2;
        View childAt2 = catchupAsVODCardView.getChildAt(0);
        Intrinsics.checkExpressionValueIsNotNull(childAt2, "cardView.getChildAt(0)");
        childAt2.setLayoutParams(layoutParams);
        return new Presenter.ViewHolder(catchupAsVODCardView);
    }

    @Override // androidx.leanback.widget.Presenter
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        URL urlUrlWithSize;
        float dimension;
        float dimension2;
        Intrinsics.checkParameterIsNotNull(viewHolder, "viewHolder");
        Intrinsics.checkParameterIsNotNull(item, "item");
        View view = viewHolder.view;
        if (view == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.cards.CatchupAsVODCardView");
        }
        CatchupAsVODCardView catchupAsVODCardView = (CatchupAsVODCardView) view;
        if (item instanceof CatchupAsVOD) {
            CatchupAsVOD catchupAsVOD = (CatchupAsVOD) item;
            Artwork stillArtwork = catchupAsVOD.getStillArtwork();
            URL urlUrlWithSize2 = null;
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
                urlUrlWithSize = stillArtwork.urlWithSize(new Point(fraction, (int) (dimension2 * context4.getResources().getFraction(R.fraction.lb_focus_zoom_factor_small, 1, 1))), 1.0d);
            } else {
                urlUrlWithSize = null;
            }
            catchupAsVODCardView.setAiringImage(String.valueOf(urlUrlWithSize));
            Artwork squareArtwork = catchupAsVOD.getChannel().getSquareArtwork();
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
                urlUrlWithSize2 = squareArtwork.urlWithSize(new Point(fraction2, (int) (dimension4 * context8.getResources().getFraction(R.fraction.lb_focus_zoom_factor_small, 1, 1))), 1.0d);
            }
            catchupAsVODCardView.setChannelLogoImage(String.valueOf(urlUrlWithSize2));
            catchupAsVODCardView.setTitle(catchupAsVOD.getTitle());
            catchupAsVODCardView.setAiringId(catchupAsVOD.getChannel().getId());
            if (catchupAsVOD.getChannel().getZappingNumber() < 10) {
                catchupAsVODCardView.setChannelNumber("000" + catchupAsVOD.getChannel().getZappingNumber());
                return;
            }
            if (catchupAsVOD.getChannel().getZappingNumber() < 100) {
                catchupAsVODCardView.setChannelNumber("00" + catchupAsVOD.getChannel().getZappingNumber());
                return;
            }
            if (catchupAsVOD.getChannel().getZappingNumber() < 1000) {
                catchupAsVODCardView.setChannelNumber(LocalSearchProvider.NO_LIVE_CONTENTS + catchupAsVOD.getChannel().getZappingNumber());
                return;
            }
            catchupAsVODCardView.setChannelNumber("" + catchupAsVOD.getChannel().getZappingNumber());
        }
    }
}
