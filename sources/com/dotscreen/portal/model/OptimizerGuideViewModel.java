package com.dotscreen.portal.model;

import android.content.Context;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.Lineup;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.tv.common.bokit.RightVChannel;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GuideViewModel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0019B\u0015\b\u0002\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J1\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0012\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0018\u00010\u0010R\u00020\u00010\u000fH\u0010¢\u0006\u0002\b\u0011J1\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0012\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0018\u00010\u0010R\u00020\u00010\u000fH\u0010¢\u0006\u0002\b\u0013J,\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0012\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0018\u00010\u0010R\u00020\u00010\u000fH\u0002J\b\u0010\u0018\u001a\u00020\nH\u0014R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/dotscreen/portal/model/OptimizerGuideViewModel;", "Lcom/dotscreen/portal/model/GuideViewModel;", "lineupSingle", "Lio/reactivex/Single;", "Lcom/dotscreen/bokit/model/Lineup;", "(Lio/reactivex/Single;)V", "loadAiringsDisposable", "Lio/reactivex/disposables/Disposable;", "loadChannelsDisposable", "loadAirings", "", "time", "", "delta", "data", "Landroidx/lifecycle/MutableLiveData;", "Lcom/dotscreen/portal/model/GuideViewModel$Data;", "loadAirings$MegaCable_Portal_0_75_1_396__prodRelease", "loadChannels", "loadChannels$MegaCable_Portal_0_75_1_396__prodRelease", "loadDataInternal", "start_calendar", "Ljava/util/Calendar;", "endCalendar", "onCleared", "Factory", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class OptimizerGuideViewModel extends GuideViewModel {
    private final Single<Lineup> lineupSingle;
    private Disposable loadAiringsDisposable;
    private Disposable loadChannelsDisposable;

    private OptimizerGuideViewModel(Single<Lineup> single) {
        this.lineupSingle = single;
    }

    public /* synthetic */ OptimizerGuideViewModel(Single single, DefaultConstructorMarker defaultConstructorMarker) {
        this(single);
    }

    /* JADX INFO: compiled from: GuideViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J'\u0010\u0005\u001a\u0002H\u0006\"\n\b\u0000\u0010\u0006*\u0004\u0018\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00060\tH\u0016¢\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/dotscreen/portal/model/OptimizerGuideViewModel$Factory;", "Landroidx/lifecycle/ViewModelProvider$NewInstanceFactory;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "create", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Factory extends ViewModelProvider.NewInstanceFactory {
        private final Context context;

        public Factory(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            this.context = context;
        }

        @Override // androidx.lifecycle.ViewModelProvider.NewInstanceFactory, androidx.lifecycle.ViewModelProvider.Factory
        public <T extends ViewModel> T create(Class<T> modelClass) {
            Intrinsics.checkParameterIsNotNull(modelClass, "modelClass");
            Single<Lineup> currentLineup = PortalApplication.INSTANCE.getSingleton(this.context).getCurrentLineup();
            Intrinsics.checkExpressionValueIsNotNull(currentLineup, "getSingleton(context).currentLineup");
            return new OptimizerGuideViewModel(currentLineup, null);
        }
    }

    @Override // com.dotscreen.portal.model.GuideViewModel
    public void loadChannels$MegaCable_Portal_0_75_1_396__prodRelease(final int time, final int delta, final MutableLiveData<GuideViewModel.Data> data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        Log.d(GuideViewModelKt.TAG, "loading channels...");
        Disposable disposable = this.loadChannelsDisposable;
        if (disposable != null) {
            if (disposable == null) {
                Intrinsics.throwNpe();
            }
            disposable.dispose();
        }
        this.loadChannelsDisposable = this.lineupSingle.flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.portal.model.OptimizerGuideViewModel$loadChannels$1
            @Override // io.reactivex.functions.Function
            public final Observable<Channel> apply(Lineup it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getItems();
            }
        }).filter(new Predicate<Channel>() { // from class: com.dotscreen.portal.model.OptimizerGuideViewModel$loadChannels$2
            @Override // io.reactivex.functions.Predicate
            public final boolean test(Channel it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                RightVChannel rightVChannel = (RightVChannel) it;
                return !rightVChannel.getIsHD() || rightVChannel.getExtras().getChannelVariantIdentifier() == null;
            }
        }).toList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Channel>>() { // from class: com.dotscreen.portal.model.OptimizerGuideViewModel$loadChannels$3
            @Override // io.reactivex.functions.Consumer
            public /* bridge */ /* synthetic */ void accept(List<Channel> list) {
                accept2((List<? extends Channel>) list);
            }

            /* JADX INFO: renamed from: accept, reason: avoid collision after fix types in other method */
            public final void accept2(List<? extends Channel> channels) {
                Intrinsics.checkParameterIsNotNull(channels, "channels");
                this.this$0.setChannels(channels);
                Log.d(GuideViewModelKt.TAG, "loading channels done");
                if (data.hasObservers()) {
                    this.this$0.loadValue$MegaCable_Portal_0_75_1_396__prodRelease(time, delta, data);
                }
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.model.OptimizerGuideViewModel$loadChannels$4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable error) {
                Intrinsics.checkParameterIsNotNull(error, "error");
                Log.d(GuideViewModelKt.TAG, "Channel loading failed reason: " + error.getMessage());
                error.printStackTrace();
                data.postValue(null);
            }
        });
    }

    @Override // com.dotscreen.portal.model.GuideViewModel
    public void loadAirings$MegaCable_Portal_0_75_1_396__prodRelease(int time, int delta, MutableLiveData<GuideViewModel.Data> data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        if (time == 5) {
            Log.d(GuideViewModelKt.TAG, "from: " + GuideViewModel.INSTANCE.getCurrentDayStart().getTime());
            Log.d(GuideViewModelKt.TAG, "to: " + GuideViewModel.INSTANCE.getCurrentDayEnd().getTime());
            loadDataInternal(GuideViewModel.INSTANCE.getCurrentDayStart(), GuideViewModel.INSTANCE.getCurrentDayEnd(), data);
            return;
        }
        if (time == 6) {
            Log.d(GuideViewModelKt.TAG, "from: " + GuideViewModel.INSTANCE.getPastDayStart().getTime());
            Log.d(GuideViewModelKt.TAG, "to: " + GuideViewModel.INSTANCE.getCurrentDayEnd().getTime());
            loadDataInternal(GuideViewModel.INSTANCE.getPastDayStart(), GuideViewModel.INSTANCE.getCurrentDayEnd(), data);
            return;
        }
        if (time == 7) {
            loadDataInternal(GuideViewModel.INSTANCE.getCurrentDayEnd(), GuideViewModel.INSTANCE.getLastDay(), data);
            return;
        }
        if (time != 9) {
            return;
        }
        Calendar now = Calendar.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(now, "now");
        now.setTime(new Date());
        Calendar past = Calendar.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(past, "past");
        past.setTime(new Date(new Date().getTime() - ((long) (((delta * 60) * 60) * 1000))));
        loadDataInternal(past, now, data);
    }

    private final void loadDataInternal(final Calendar start_calendar, final Calendar endCalendar, final MutableLiveData<GuideViewModel.Data> data) {
        Disposable disposable = this.loadAiringsDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        Observable<R> observableFlatMapObservable = this.lineupSingle.flatMapObservable((Function) new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.portal.model.OptimizerGuideViewModel.loadDataInternal.1
            @Override // io.reactivex.functions.Function
            public final Observable<TVAiring> apply(Lineup it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Date time = start_calendar.getTime();
                Intrinsics.checkExpressionValueIsNotNull(time, "start_calendar.time");
                Date time2 = endCalendar.getTime();
                Intrinsics.checkExpressionValueIsNotNull(time2, "endCalendar.time");
                return it.airings(time, time2);
            }
        });
        HashMap map = new HashMap();
        List<Channel> channels = getChannels();
        if (channels != null) {
            Iterator<T> it = channels.iterator();
            while (it.hasNext()) {
                map.put(((Channel) it.next()).getId(), new ArrayList());
            }
        }
        this.loadAiringsDisposable = observableFlatMapObservable.collectInto(map, new BiConsumer<U, T>() { // from class: com.dotscreen.portal.model.OptimizerGuideViewModel.loadDataInternal.3
            @Override // io.reactivex.functions.BiConsumer
            public final void accept(HashMap<String, List<TVAiring>> map2, TVAiring airing) {
                List<TVAiring> list = map2.get(airing.getChannel().getId());
                if (list != null) {
                    Intrinsics.checkExpressionValueIsNotNull(airing, "airing");
                    list.add(airing);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<HashMap<String, List<TVAiring>>>() { // from class: com.dotscreen.portal.model.OptimizerGuideViewModel.loadDataInternal.4
            @Override // io.reactivex.functions.Consumer
            public final void accept(HashMap<String, List<TVAiring>> airings) {
                MutableLiveData mutableLiveData = data;
                OptimizerGuideViewModel optimizerGuideViewModel = OptimizerGuideViewModel.this;
                List<Channel> channels2 = optimizerGuideViewModel.getChannels();
                if (channels2 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(airings, "airings");
                mutableLiveData.postValue(new GuideViewModel.Data(optimizerGuideViewModel, channels2, airings));
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.model.OptimizerGuideViewModel.loadDataInternal.5
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Log.e(GuideViewModelKt.TAG, "Error loading content", th);
            }
        });
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        Disposable disposable = this.loadChannelsDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        Disposable disposable2 = this.loadAiringsDisposable;
        if (disposable2 != null) {
            disposable2.dispose();
        }
    }
}
