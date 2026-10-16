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
import com.dotscreen.bokit.model.TVAiringTimeOfDay;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.tv.common.bokit.RightVChannel;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EPGViewModel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\u0012B\u0015\b\u0002\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J)\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\n\u0012\b\u0018\u00010\u000eR\u00020\u00010\rH\u0010¢\u0006\u0002\b\u000fJ)\u0010\u0010\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\n\u0012\b\u0018\u00010\u000eR\u00020\u00010\rH\u0010¢\u0006\u0002\b\u0011R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/dotscreen/portal/model/OptimizerEPGViewModel;", "Lcom/dotscreen/portal/model/EPGViewModel;", "lineupSingle", "Lio/reactivex/Single;", "Lcom/dotscreen/bokit/model/Lineup;", "(Lio/reactivex/Single;)V", "loadChannelsDisposable", "Lio/reactivex/disposables/Disposable;", "loadAirings", "", "time", "", "data", "Landroidx/lifecycle/MutableLiveData;", "Lcom/dotscreen/portal/model/EPGViewModel$Data;", "loadAirings$MegaCable_Portal_0_75_1_396__prodRelease", "loadChannels", "loadChannels$MegaCable_Portal_0_75_1_396__prodRelease", "Factory", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class OptimizerEPGViewModel extends EPGViewModel {
    private final Single<Lineup> lineupSingle;
    private Disposable loadChannelsDisposable;

    private OptimizerEPGViewModel(Single<Lineup> single) {
        this.lineupSingle = single;
    }

    public /* synthetic */ OptimizerEPGViewModel(Single single, DefaultConstructorMarker defaultConstructorMarker) {
        this(single);
    }

    /* JADX INFO: compiled from: EPGViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J'\u0010\u0005\u001a\u0002H\u0006\"\n\b\u0000\u0010\u0006*\u0004\u0018\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00060\tH\u0016¢\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/dotscreen/portal/model/OptimizerEPGViewModel$Factory;", "Landroidx/lifecycle/ViewModelProvider$NewInstanceFactory;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "create", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
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
            return new OptimizerEPGViewModel(currentLineup, null);
        }
    }

    @Override // com.dotscreen.portal.model.EPGViewModel
    public void loadChannels$MegaCable_Portal_0_75_1_396__prodRelease(final int time, final MutableLiveData<EPGViewModel.Data> data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        Log.d(EPGViewModelKt.TAG, "loading channels...");
        Disposable disposable = this.loadChannelsDisposable;
        if (disposable != null) {
            if (disposable == null) {
                Intrinsics.throwNpe();
            }
            disposable.dispose();
        }
        this.loadChannelsDisposable = this.lineupSingle.flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.portal.model.OptimizerEPGViewModel$loadChannels$1
            @Override // io.reactivex.functions.Function
            public final Observable<Channel> apply(Lineup it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getItems();
            }
        }).filter(new Predicate<Channel>() { // from class: com.dotscreen.portal.model.OptimizerEPGViewModel$loadChannels$2
            @Override // io.reactivex.functions.Predicate
            public final boolean test(Channel it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                RightVChannel rightVChannel = (RightVChannel) it;
                return !rightVChannel.getIsHD() || rightVChannel.getExtras().getChannelVariantIdentifier() == null;
            }
        }).toList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Channel>>() { // from class: com.dotscreen.portal.model.OptimizerEPGViewModel$loadChannels$3
            @Override // io.reactivex.functions.Consumer
            public /* bridge */ /* synthetic */ void accept(List<Channel> list) {
                accept2((List<? extends Channel>) list);
            }

            /* JADX INFO: renamed from: accept, reason: avoid collision after fix types in other method */
            public final void accept2(List<? extends Channel> channels) {
                Intrinsics.checkParameterIsNotNull(channels, "channels");
                this.this$0.setChannels(channels);
                Log.d(EPGViewModelKt.TAG, "loading channels done");
                if (data.hasActiveObservers()) {
                    this.this$0.loadValue$MegaCable_Portal_0_75_1_396__prodRelease(time, data);
                }
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.model.OptimizerEPGViewModel$loadChannels$4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable error) {
                Intrinsics.checkParameterIsNotNull(error, "error");
                Log.d(EPGViewModelKt.TAG, "Channel loading failed reason: " + error.getMessage());
                error.printStackTrace();
                data.postValue(null);
            }
        });
    }

    @Override // com.dotscreen.portal.model.EPGViewModel
    public void loadAirings$MegaCable_Portal_0_75_1_396__prodRelease(int time, final MutableLiveData<EPGViewModel.Data> data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        if (time == 1) {
            Log.d(EPGViewModelKt.TAG, "loading now...");
            List<Channel> channels = getChannels();
            if (channels == null) {
                channels = CollectionsKt.emptyList();
            }
            Observable.fromIterable(channels).flatMapMaybe(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.portal.model.OptimizerEPGViewModel$loadAirings$1
                @Override // io.reactivex.functions.Function
                public final Maybe<TVAiring> apply(Channel it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return it.airing(TVAiringTimeOfDay.Now);
                }
            }).toList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<TVAiring>>() { // from class: com.dotscreen.portal.model.OptimizerEPGViewModel$loadAirings$2
                @Override // io.reactivex.functions.Consumer
                public final void accept(List<TVAiring> airings) {
                    Log.d(EPGViewModelKt.TAG, "loading done, airings = " + airings);
                    MutableLiveData mutableLiveData = data;
                    OptimizerEPGViewModel optimizerEPGViewModel = this.this$0;
                    List<Channel> channels2 = optimizerEPGViewModel.getChannels();
                    if (channels2 == null) {
                        Intrinsics.throwNpe();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(airings, "airings");
                    mutableLiveData.postValue(new EPGViewModel.Data(optimizerEPGViewModel, channels2, airings));
                }
            }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.model.OptimizerEPGViewModel$loadAirings$3
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                    Log.e(EPGViewModelKt.TAG, "Error loading content", th);
                }
            });
            return;
        }
        if (time == 2) {
            List<Channel> channels2 = getChannels();
            if (channels2 == null) {
                channels2 = CollectionsKt.emptyList();
            }
            Observable.fromIterable(channels2).flatMapMaybe(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.portal.model.OptimizerEPGViewModel$loadAirings$4
                @Override // io.reactivex.functions.Function
                public final Maybe<TVAiring> apply(Channel it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return it.airing(TVAiringTimeOfDay.Next);
                }
            }).toList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<TVAiring>>() { // from class: com.dotscreen.portal.model.OptimizerEPGViewModel$loadAirings$5
                @Override // io.reactivex.functions.Consumer
                public final void accept(List<TVAiring> list) {
                    MutableLiveData mutableLiveData = data;
                    OptimizerEPGViewModel optimizerEPGViewModel = this.this$0;
                    List<Channel> channels3 = optimizerEPGViewModel.getChannels();
                    if (channels3 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (list == null) {
                        Intrinsics.throwNpe();
                    }
                    mutableLiveData.postValue(new EPGViewModel.Data(optimizerEPGViewModel, channels3, list));
                }
            }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.model.OptimizerEPGViewModel$loadAirings$6
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                    Log.e(EPGViewModelKt.TAG, "Error loading content", th);
                }
            });
            return;
        }
        if (time == 3) {
            List<Channel> channels3 = getChannels();
            if (channels3 == null) {
                channels3 = CollectionsKt.emptyList();
            }
            Observable.fromIterable(channels3).flatMapMaybe(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.portal.model.OptimizerEPGViewModel$loadAirings$7
                @Override // io.reactivex.functions.Function
                public final Maybe<TVAiring> apply(Channel it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return it.airing(TVAiringTimeOfDay.PrimeTime);
                }
            }).toList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<TVAiring>>() { // from class: com.dotscreen.portal.model.OptimizerEPGViewModel$loadAirings$8
                @Override // io.reactivex.functions.Consumer
                public final void accept(List<TVAiring> list) {
                    MutableLiveData mutableLiveData = data;
                    OptimizerEPGViewModel optimizerEPGViewModel = this.this$0;
                    List<Channel> channels4 = optimizerEPGViewModel.getChannels();
                    if (channels4 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (list == null) {
                        Intrinsics.throwNpe();
                    }
                    mutableLiveData.postValue(new EPGViewModel.Data(optimizerEPGViewModel, channels4, list));
                }
            }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.model.OptimizerEPGViewModel$loadAirings$9
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                    Log.e(EPGViewModelKt.TAG, "Error loading content", th);
                }
            });
            return;
        }
        if (time != 4) {
            return;
        }
        List<Channel> channels4 = getChannels();
        if (channels4 == null) {
            channels4 = CollectionsKt.emptyList();
        }
        Observable.fromIterable(channels4).flatMapMaybe(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.portal.model.OptimizerEPGViewModel$loadAirings$10
            @Override // io.reactivex.functions.Function
            public final Maybe<TVAiring> apply(Channel it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.airing(TVAiringTimeOfDay.LateNight);
            }
        }).toList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<TVAiring>>() { // from class: com.dotscreen.portal.model.OptimizerEPGViewModel$loadAirings$11
            @Override // io.reactivex.functions.Consumer
            public final void accept(List<TVAiring> list) {
                MutableLiveData mutableLiveData = data;
                OptimizerEPGViewModel optimizerEPGViewModel = this.this$0;
                List<Channel> channels5 = optimizerEPGViewModel.getChannels();
                if (channels5 == null) {
                    Intrinsics.throwNpe();
                }
                if (list == null) {
                    Intrinsics.throwNpe();
                }
                mutableLiveData.postValue(new EPGViewModel.Data(optimizerEPGViewModel, channels5, list));
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.model.OptimizerEPGViewModel$loadAirings$12
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Log.e(EPGViewModelKt.TAG, "Error loading content", th);
            }
        });
    }
}
