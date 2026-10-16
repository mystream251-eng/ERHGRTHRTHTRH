package com.dotscreen.portal.ui.cards;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.media.tv.TvContract;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.leanback.widget.Presenter;
import androidx.lifecycle.LiveData;
import com.dotscreen.bokit.model.Artwork;
import com.dotscreen.bokit.model.LiveStream;
import com.dotscreen.bokit.model.ParentalRating;
import com.dotscreen.bokit.model.PlaybackRight;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.farkit.tools.DSLog;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.tv.common.bokit.RightVChannel;
import com.dotscreen.tv.common.bokit.RightVUser;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.dotscreen.tv.common.search.LocalSearchProvider;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.dotscreen.tvinput.NoChannelFoundException;
import com.google.android.media.tv.companionlibrary.model.Channel;
import com.google.android.media.tv.companionlibrary.model.InternalProviderData;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.text.SimpleDateFormat;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: LiveCardPresenter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B%\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J(\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0002J\u0010\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0018\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0016J\u0010\u0010%\u001a\u00020\"2\u0006\u0010&\u001a\u00020'H\u0016J\u0012\u0010(\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\u0018\u0010)\u001a\u00020 2\u0006\u0010#\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0017H\u0002R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011¨\u0006*"}, d2 = {"Lcom/dotscreen/portal/ui/cards/LiveCardPresenter;", "Landroidx/leanback/widget/Presenter;", ToolboxAnalyticsModuleKt.M_WIDTH, "", ToolboxAnalyticsModuleKt.M_HEIGHT, "refresher", "Lcom/dotscreen/portal/ui/cards/RefreshCard;", "(Ljava/lang/Integer;Ljava/lang/Integer;Lcom/dotscreen/portal/ui/cards/RefreshCard;)V", "TAG", "", "kotlin.jvm.PlatformType", "context", "Landroid/content/Context;", "defaultCardImage", "Landroid/graphics/drawable/Drawable;", "handler", "Landroid/os/Handler;", "Ljava/lang/Integer;", "tuneRunnable", "Ljava/lang/Runnable;", "blockInterface", "", "cardView", "Lcom/dotscreen/portal/ui/cards/LiveCardView;", "airing", "Lcom/dotscreen/bokit/model/TVAiring;", "channel", "Lcom/dotscreen/tv/common/bokit/RightVChannel;", "getDurationString", "duration", "", "onBindViewHolder", "", "viewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "onUnbindViewHolder", "tune", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class LiveCardPresenter extends Presenter {
    private final String TAG;
    private Context context;
    private Drawable defaultCardImage;
    private final Handler handler;
    private final Integer height;
    private final RefreshCard refresher;
    private Runnable tuneRunnable;
    private final Integer width;

    public /* synthetic */ LiveCardPresenter(Integer num, Integer num2, RefreshCard refreshCard, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (Integer) null : num, (i & 2) != 0 ? (Integer) null : num2, refreshCard);
    }

    public LiveCardPresenter(Integer num, Integer num2, RefreshCard refresher) {
        Intrinsics.checkParameterIsNotNull(refresher, "refresher");
        this.width = num;
        this.height = num2;
        this.refresher = refresher;
        this.TAG = LiveCardPresenter.class.getSimpleName();
        this.handler = new Handler();
    }

    @Override // androidx.leanback.widget.Presenter
    public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
        Resources resources;
        Resources resources2;
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        Log.d(this.TAG, "onCreateViewHolder");
        this.context = parent.getContext();
        Integer numValueOf = null;
        this.defaultCardImage = parent.getResources().getDrawable(R.drawable.ph_horizontal, null);
        Context context = parent.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "parent.context");
        LiveCardView liveCardView = new LiveCardView(context, null, 0, this.refresher, 6, null);
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
        liveCardView.setLayoutParams(new ViewGroup.LayoutParams(iIntValue, iIntValue2));
        liveCardView.setFocusable(true);
        liveCardView.setFocusableInTouchMode(true);
        View childAt = liveCardView.getChildAt(0);
        Intrinsics.checkExpressionValueIsNotNull(childAt, "cardView.getChildAt(0)");
        ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
        Intrinsics.checkExpressionValueIsNotNull(layoutParams, "cardView.getChildAt(0).layoutParams");
        layoutParams.width = iIntValue;
        layoutParams.height = iIntValue2;
        View childAt2 = liveCardView.getChildAt(0);
        Intrinsics.checkExpressionValueIsNotNull(childAt2, "cardView.getChildAt(0)");
        childAt2.setLayoutParams(layoutParams);
        return new Presenter.ViewHolder(liveCardView);
    }

    @Override // androidx.leanback.widget.Presenter
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, final Object item) {
        Uri uri;
        String str;
        RightVChannel rightVChannel;
        Intrinsics.checkParameterIsNotNull(viewHolder, "viewHolder");
        Intrinsics.checkParameterIsNotNull(item, "item");
        View view = viewHolder.view;
        if (view == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.cards.LiveCardView");
        }
        final LiveCardView liveCardView = (LiveCardView) view;
        if (!liveCardView.getTuned()) {
            liveCardView.startLoading();
        }
        Log.d(this.TAG, "onBindViewHolder: " + liveCardView + " -> " + liveCardView.getAiring());
        String str2 = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("onBindViewHolder: current Item => ");
        sb.append(item);
        Log.d(str2, sb.toString());
        Context context = this.context;
        Context applicationContext = context != null ? context.getApplicationContext() : null;
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        if (!((PortalApplication) applicationContext).isScreenOn()) {
            Log.e(this.TAG, "Screen is off -> do not tune !");
            return;
        }
        if (item instanceof Uri) {
            Log.w(this.TAG, "onBindViewHolder: Uri case");
            LiveCardView.showOverlay$default(liveCardView, false, false, 2, null);
            Context context2 = this.context;
            if (context2 == null) {
                Intrinsics.throwNpe();
            }
            Context applicationContext2 = context2.getApplicationContext();
            if (applicationContext2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
            }
            if (!((PortalApplication) applicationContext2).getBootWithoutInternet()) {
                if (((Uri) item).getLastPathSegment() != null) {
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new LiveCardPresenter$onBindViewHolder$$inlined$let$lambda$1(null, this, item, liveCardView), 2, null);
                    return;
                }
                return;
            }
            liveCardView.setErrorMessage(R.string.livetv_unavaible_without_internet);
            return;
        }
        if (item instanceof TVAiring) {
            Log.w(this.TAG, "onBindViewHolder: TVAiring case");
            LiveCardView.showOverlay$default(liveCardView, true, false, 2, null);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH':'mm");
            TVAiring tVAiring = (TVAiring) item;
            String str3 = simpleDateFormat.format(tVAiring.getStartDate());
            Intrinsics.checkExpressionValueIsNotNull(str3, "dateFormatHeure.format(item.startDate)");
            String str4 = simpleDateFormat.format(tVAiring.getEndDate());
            Intrinsics.checkExpressionValueIsNotNull(str4, "dateFormatHeure.format(item.endDate)");
            liveCardView.setTime(str3, str4);
            liveCardView.setProgress((int) (tVAiring.getEndDate().getTime() - tVAiring.getStartDate().getTime()), (int) (System.currentTimeMillis() - tVAiring.getStartDate().getTime()));
            TVAiring airing = liveCardView.getAiring();
            if ((!Intrinsics.areEqual(airing != null ? airing.getChannel() : null, tVAiring.getChannel())) || !liveCardView.getTuned()) {
                Runnable runnable = this.tuneRunnable;
                if (runnable != null) {
                    this.handler.removeCallbacks(runnable);
                    Unit unit = Unit.INSTANCE;
                }
                Log.d(this.TAG, "onBindViewHolder: ask for the rights");
                Runnable runnable2 = new Runnable() { // from class: com.dotscreen.portal.ui.cards.LiveCardPresenter.onBindViewHolder.3
                    @Override // java.lang.Runnable
                    public final void run() {
                        Observable<PlaybackRight> playbackRights;
                        Single<List<PlaybackRight>> list;
                        Single<List<PlaybackRight>> singleSubscribeOn;
                        Single<List<PlaybackRight>> singleObserveOn;
                        LiveStream liveStream = ((TVAiring) item).getChannel().getLiveStream();
                        if (liveStream == null || (playbackRights = liveStream.getPlaybackRights()) == null || (list = playbackRights.toList()) == null || (singleSubscribeOn = list.subscribeOn(Schedulers.io())) == null || (singleObserveOn = singleSubscribeOn.observeOn(AndroidSchedulers.mainThread())) == null) {
                            return;
                        }
                        singleObserveOn.subscribe(new Consumer<List<PlaybackRight>>() { // from class: com.dotscreen.portal.ui.cards.LiveCardPresenter.onBindViewHolder.3.1
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(List<PlaybackRight> playbackRights2) {
                                Intrinsics.checkExpressionValueIsNotNull(playbackRights2, "playbackRights");
                                if (!(!playbackRights2.isEmpty())) {
                                    Log.d(LiveCardPresenter.this.TAG, ">>>> Cancel TUNE: user not subscribed");
                                    liveCardView.stopTune(StopTuneReason.NotSubscribed);
                                    liveCardView.block(true, Integer.valueOf(R.string.tvview_msg_not_subscribed));
                                    liveCardView.stopLoading();
                                    return;
                                }
                                DSLog.INSTANCE.m("Start tune on MiniPlayer");
                                Log.d(LiveCardPresenter.this.TAG, ">>>> TUNE !");
                                liveCardView.setErrorMessage("");
                                Log.d(LiveCardPresenter.this.TAG, ">>>> will tune on card view");
                                LiveCardPresenter.this.tune((TVAiring) item, liveCardView);
                            }
                        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.cards.LiveCardPresenter.onBindViewHolder.3.2
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Throwable th) {
                                Resources resources;
                                if (!CommonUtils.isDVBCSTB(LiveCardPresenter.this.context)) {
                                    Log.w(LiveCardPresenter.this.TAG, ">>>> Cancel TUNE: error", th);
                                    try {
                                        liveCardView.block(true, CommonUtils.formatTISException(LiveCardPresenter.this.context, th, 0));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        liveCardView.stopLoading();
                                        LiveCardView liveCardView2 = liveCardView;
                                        Context context3 = LiveCardPresenter.this.context;
                                        String string = (context3 == null || (resources = context3.getResources()) == null) ? null : resources.getString(R.string.tvview_msg_unknown_error_fallback);
                                        if (string == null) {
                                            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                                        }
                                        liveCardView2.setErrorMessage(string);
                                    }
                                    LiveCardView.stopTune$default(liveCardView, null, 1, null);
                                    liveCardView.stopLoading();
                                    return;
                                }
                                LiveCardPresenter.this.tune((TVAiring) item, liveCardView);
                            }
                        });
                    }
                };
                this.tuneRunnable = runnable2;
                this.handler.postDelayed(runnable2, 1000L);
            }
            if (Intrinsics.areEqual(liveCardView.getAiring(), item)) {
                if (liveCardView.getTuned()) {
                    blockInterface$default(this, liveCardView, tVAiring, null, 4, null);
                }
            } else {
                liveCardView.setAiring(tVAiring);
                Artwork squareArtwork = tVAiring.getChannel().getSquareArtwork();
                if (squareArtwork == null) {
                    Intrinsics.throwNpe();
                }
                Context context3 = this.context;
                if (context3 == null) {
                    Intrinsics.throwNpe();
                }
                float dimension = context3.getResources().getDimension(R.dimen.airing_card_channel_logo_width);
                Context context4 = this.context;
                if (context4 == null) {
                    Intrinsics.throwNpe();
                }
                int fraction = (int) (dimension * context4.getResources().getFraction(R.fraction.lb_focus_zoom_factor_small, 1, 1));
                Context context5 = this.context;
                if (context5 == null) {
                    Intrinsics.throwNpe();
                }
                float dimension2 = context5.getResources().getDimension(R.dimen.airing_card_channel_logo_width);
                Context context6 = this.context;
                if (context6 == null) {
                    Intrinsics.throwNpe();
                }
                liveCardView.setChannelLogoImage(String.valueOf(squareArtwork.urlWithSize(new Point(fraction, (int) (dimension2 * context6.getResources().getFraction(R.fraction.lb_focus_zoom_factor_small, 1, 1))), 1.0d)));
                liveCardView.setTitle(tVAiring.getTitle());
                if (tVAiring.getChannel().getZappingNumber() < 10) {
                    liveCardView.setChannelNumber("000" + tVAiring.getChannel().getZappingNumber());
                } else if (tVAiring.getChannel().getZappingNumber() < 100) {
                    liveCardView.setChannelNumber("00" + tVAiring.getChannel().getZappingNumber());
                } else if (tVAiring.getChannel().getZappingNumber() < 1000) {
                    liveCardView.setChannelNumber(LocalSearchProvider.NO_LIVE_CONTENTS + tVAiring.getChannel().getZappingNumber());
                } else {
                    liveCardView.setChannelNumber("" + tVAiring.getChannel().getZappingNumber());
                }
                if (tVAiring.getMetadata().getParentalLevel() != null) {
                    ParentalRating parentalLevel = tVAiring.getMetadata().getParentalLevel();
                    if (parentalLevel == null) {
                        Intrinsics.throwNpe();
                    }
                    int level = parentalLevel.getLevel();
                    if (level == 10) {
                        liveCardView.setParentalRatingLogo(R.drawable.ic_10);
                    } else if (level == 12) {
                        liveCardView.setParentalRatingLogo(R.drawable.ic_12);
                    } else if (level == 16) {
                        liveCardView.setParentalRatingLogo(R.drawable.ic_16);
                    } else if (level == 18) {
                        liveCardView.setParentalRatingLogo(R.drawable.ic_18);
                    } else {
                        liveCardView.setParentalRatingLogo(-1);
                    }
                } else {
                    liveCardView.setParentalRatingLogo(-1);
                }
            }
            liveCardView.setInteractiveTag(tVAiring.getChannel().getCapabilities().getCatchup() || tVAiring.getChannel().getCapabilities().getStartover());
            return;
        }
        if (item instanceof Pair) {
            Log.w(this.TAG, "onBindViewHolder: Pair case");
            Pair pair = (Pair) item;
            Object first = pair.getFirst();
            if (first == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.net.Uri");
            }
            Uri uri2 = (Uri) first;
            Object second = pair.getSecond();
            if (second == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVChannel");
            }
            RightVChannel rightVChannel2 = (RightVChannel) second;
            liveCardView.setAiring((TVAiring) null);
            liveCardView.setInteractiveTag(false);
            liveCardView.showOverlay(true, true);
            if (Intrinsics.areEqual(liveCardView.getChannel(), rightVChannel2)) {
                Log.w(this.TAG, "Current livecard channel is already set");
                if (liveCardView.getTuned()) {
                    blockInterface$default(this, liveCardView, null, rightVChannel2, 2, null);
                    str = "null cannot be cast to non-null type com.dotscreen.portal.PortalApplication";
                    rightVChannel = rightVChannel2;
                    uri = uri2;
                } else {
                    rightVChannel = rightVChannel2;
                    uri = uri2;
                    str = "null cannot be cast to non-null type com.dotscreen.portal.PortalApplication";
                }
            } else {
                uri = uri2;
                str = "null cannot be cast to non-null type com.dotscreen.portal.PortalApplication";
                if (blockInterface$default(this, liveCardView, null, rightVChannel2, 2, null)) {
                    Log.w(this.TAG, "set channel to livecardView");
                    rightVChannel = rightVChannel2;
                    liveCardView.setChannel(rightVChannel);
                    Artwork squareArtwork2 = rightVChannel.getSquareArtwork();
                    if (squareArtwork2 == null) {
                        Intrinsics.throwNpe();
                    }
                    Context context7 = this.context;
                    if (context7 == null) {
                        Intrinsics.throwNpe();
                    }
                    float dimension3 = context7.getResources().getDimension(R.dimen.airing_card_channel_logo_width);
                    Context context8 = this.context;
                    if (context8 == null) {
                        Intrinsics.throwNpe();
                    }
                    int fraction2 = (int) (dimension3 * context8.getResources().getFraction(R.fraction.lb_focus_zoom_factor_small, 1, 1));
                    Context context9 = this.context;
                    if (context9 == null) {
                        Intrinsics.throwNpe();
                    }
                    float dimension4 = context9.getResources().getDimension(R.dimen.airing_card_channel_logo_width);
                    Context context10 = this.context;
                    if (context10 == null) {
                        Intrinsics.throwNpe();
                    }
                    liveCardView.setChannelLogoImage(String.valueOf(squareArtwork2.urlWithSize(new Point(fraction2, (int) (dimension4 * context10.getResources().getFraction(R.fraction.lb_focus_zoom_factor_small, 1, 1))), 1.0d)));
                    if (rightVChannel.getZappingNumber() < 10) {
                        liveCardView.setChannelNumber("000" + rightVChannel.getZappingNumber());
                    } else if (rightVChannel.getZappingNumber() < 100) {
                        liveCardView.setChannelNumber("00" + rightVChannel.getZappingNumber());
                    } else if (rightVChannel.getZappingNumber() < 1000) {
                        liveCardView.setChannelNumber(LocalSearchProvider.NO_LIVE_CONTENTS + rightVChannel.getZappingNumber());
                    } else {
                        liveCardView.setChannelNumber("" + rightVChannel.getZappingNumber());
                    }
                    int level2 = rightVChannel.getParentalRating().getLevel();
                    if (level2 == 10) {
                        liveCardView.setParentalRatingLogo(R.drawable.ic_10);
                    } else if (level2 == 12) {
                        liveCardView.setParentalRatingLogo(R.drawable.ic_12);
                    } else if (level2 == 16) {
                        liveCardView.setParentalRatingLogo(R.drawable.ic_16);
                    } else if (level2 == 18) {
                        liveCardView.setParentalRatingLogo(R.drawable.ic_18);
                    } else {
                        liveCardView.setParentalRatingLogo(-1);
                    }
                } else {
                    rightVChannel = rightVChannel2;
                    liveCardView.setChannel(rightVChannel);
                    Artwork squareArtwork3 = rightVChannel.getSquareArtwork();
                    if (squareArtwork3 == null) {
                        Intrinsics.throwNpe();
                    }
                    Context context11 = this.context;
                    if (context11 == null) {
                        Intrinsics.throwNpe();
                    }
                    float dimension5 = context11.getResources().getDimension(R.dimen.airing_card_channel_logo_width);
                    Context context12 = this.context;
                    if (context12 == null) {
                        Intrinsics.throwNpe();
                    }
                    int fraction3 = (int) (dimension5 * context12.getResources().getFraction(R.fraction.lb_focus_zoom_factor_small, 1, 1));
                    Context context13 = this.context;
                    if (context13 == null) {
                        Intrinsics.throwNpe();
                    }
                    float dimension6 = context13.getResources().getDimension(R.dimen.airing_card_channel_logo_width);
                    Context context14 = this.context;
                    if (context14 == null) {
                        Intrinsics.throwNpe();
                    }
                    liveCardView.setChannelLogoImage(String.valueOf(squareArtwork3.urlWithSize(new Point(fraction3, (int) (dimension6 * context14.getResources().getFraction(R.fraction.lb_focus_zoom_factor_small, 1, 1))), 1.0d)));
                }
            }
            Context context15 = this.context;
            if (context15 == null) {
                Intrinsics.throwNpe();
            }
            Context applicationContext3 = context15.getApplicationContext();
            if (applicationContext3 == null) {
                throw new TypeCastException(str);
            }
            if (!((PortalApplication) applicationContext3).getBootWithoutInternet()) {
                if (uri.getLastPathSegment() != null) {
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new LiveCardPresenter$onBindViewHolder$$inlined$let$lambda$2(null, this, uri, liveCardView), 2, null);
                }
            } else {
                liveCardView.setErrorMessage(R.string.livetv_unavaible_without_internet);
            }
            liveCardView.setInteractiveTag(rightVChannel.getCapabilities().getCatchup() || rightVChannel.getCapabilities().getStartover());
            liveCardView.stopLoading();
            return;
        }
        if (item instanceof NoChannelFoundException) {
            LiveCardView.showOverlay$default(liveCardView, false, false, 2, null);
            liveCardView.setErrorMessage(((NoChannelFoundException) item).getMessage());
        }
    }

    static /* synthetic */ boolean blockInterface$default(LiveCardPresenter liveCardPresenter, LiveCardView liveCardView, TVAiring tVAiring, RightVChannel rightVChannel, int i, Object obj) {
        if ((i & 2) != 0) {
            tVAiring = (TVAiring) null;
        }
        if ((i & 4) != 0) {
            rightVChannel = (RightVChannel) null;
        }
        return liveCardPresenter.blockInterface(liveCardView, tVAiring, rightVChannel);
    }

    private final boolean blockInterface(LiveCardView cardView, TVAiring airing, RightVChannel channel) {
        ParentalRating parentalRating;
        LiveData<PortalApplication.Config> config;
        PortalApplication.Config value;
        Context context = this.context;
        Context applicationContext = context != null ? context.getApplicationContext() : null;
        if (!(applicationContext instanceof PortalApplication)) {
            applicationContext = null;
        }
        PortalApplication portalApplication = (PortalApplication) applicationContext;
        RightVUser user = (portalApplication == null || (config = portalApplication.getConfig()) == null || (value = config.getValue()) == null) ? null : value.getUser();
        cardView.setUser(user);
        boolean z = true;
        try {
            if (airing != null) {
                parentalRating = airing.getMetadata().getParentalLevel();
            } else {
                if (channel == null) {
                    Intrinsics.throwNpe();
                }
                parentalRating = channel.getParentalRating();
            }
            if (user == null || ((parentalRating == null && user.getParentalLevel().getLevel() != 6) || (parentalRating != null && user.getParentalLevel().compareTo(parentalRating) < 0))) {
                try {
                    cardView.block(true, Integer.valueOf(R.string.shrunken_tvview_content_locked));
                } catch (Exception e) {
                    e = e;
                    e.printStackTrace();
                    LiveCardView.block$default(cardView, false, null, 2, null);
                }
            } else {
                LiveCardView.block$default(cardView, false, null, 2, null);
                z = false;
            }
        } catch (Exception e2) {
            e = e2;
            z = false;
        }
        cardView.stopLoading();
        return z;
    }

    private final String getDurationString(long duration) {
        if (duration < 0) {
            return "";
        }
        long j = duration / ((long) 1000);
        long j2 = 60;
        if (j < j2) {
            return j + " sec";
        }
        return (j / j2) + " min";
    }

    @Override // androidx.leanback.widget.Presenter
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
        Log.d(this.TAG, "onUnbindViewHolder");
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.ui.cards.LiveCardPresenter$tune$1, reason: invalid class name */
    /* JADX INFO: compiled from: LiveCardPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.dotscreen.portal.ui.cards.LiveCardPresenter$tune$1", f = "LiveCardPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ LiveCardView $cardView;
        final /* synthetic */ TVAiring $item;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(TVAiring tVAiring, LiveCardView liveCardView, Continuation continuation) {
            super(2, continuation);
            this.$item = tVAiring;
            this.$cardView = liveCardView;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass1 anonymousClass1 = LiveCardPresenter.this.new AnonymousClass1(this.$item, this.$cardView, completion);
            anonymousClass1.p$ = (CoroutineScope) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            String videoUrl;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Log.v(LiveCardPresenter.this.TAG, "Init params for tune");
            Bundle bundle = new Bundle();
            Context context = LiveCardPresenter.this.context;
            if (context == null) {
                Intrinsics.throwNpe();
            }
            Channel channel = CommonUtils.getChannel(context, this.$item.getChannel().getId());
            Log.v(LiveCardPresenter.this.TAG, "tifChannel = " + channel);
            String inputId = channel != null ? channel.getInputId() : null;
            Uri uriBuildChannelUri = channel != null ? TvContract.buildChannelUri(channel.getId()) : null;
            Log.v(LiveCardPresenter.this.TAG, "     Done");
            if (CommonUtils.getCurrentPlatform() == 1) {
                Log.v(LiveCardPresenter.this.TAG, "O: Put channelVideoUrl in params");
                if (channel != null) {
                    try {
                        InternalProviderData internalProviderData = channel.getInternalProviderData();
                        if (internalProviderData != null && (videoUrl = internalProviderData.getVideoUrl()) != null) {
                            bundle.putString("net.oregan.tvinput.multicast.MulticastService.uri", videoUrl);
                        }
                    } catch (Exception unused) {
                    }
                }
                Log.v(LiveCardPresenter.this.TAG, "         Done channelVideoUrl in params");
            }
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass2(inputId, uriBuildChannelUri, bundle, null), 2, null);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.dotscreen.portal.ui.cards.LiveCardPresenter$tune$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: LiveCardPresenter.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.dotscreen.portal.ui.cards.LiveCardPresenter$tune$1$2", f = "LiveCardPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ String $channelInputId;
            final /* synthetic */ Uri $channelUri;
            final /* synthetic */ Bundle $params;
            int label;
            private CoroutineScope p$;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(String str, Uri uri, Bundle bundle, Continuation continuation) {
                super(2, continuation);
                this.$channelInputId = str;
                this.$channelUri = uri;
                this.$params = bundle;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass2 anonymousClass2 = AnonymousClass1.this.new AnonymousClass2(this.$channelInputId, this.$channelUri, this.$params, completion);
                anonymousClass2.p$ = (CoroutineScope) obj;
                return anonymousClass2;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                if (this.$channelInputId != null && this.$channelUri != null) {
                    this.$params.putBoolean("isSubscribed", true);
                    AnonymousClass1.this.$cardView.tune(this.$channelInputId, this.$channelUri, this.$params);
                }
                LiveCardPresenter.blockInterface$default(LiveCardPresenter.this, AnonymousClass1.this.$cardView, AnonymousClass1.this.$item, null, 4, null);
                return Unit.INSTANCE;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void tune(TVAiring item, LiveCardView cardView) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new AnonymousClass1(item, cardView, null), 2, null);
    }
}
