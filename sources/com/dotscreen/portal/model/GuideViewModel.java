package com.dotscreen.portal.model;

import android.util.Log;
import android.util.SparseArray;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.TVAiring;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GuideViewModel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\t\b&\u0018\u0000 \u001b2\u00020\u0001:\u0002\u001b\u001cB\u0005¢\u0006\u0002\u0010\u0002J$\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0018\u00010\rR\u00020\u00000\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0011J1\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0012\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0018\u00010\rR\u00020\u00000\fH ¢\u0006\u0002\b\u0016J1\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0012\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0018\u00010\rR\u00020\u00000\fH ¢\u0006\u0002\b\u0018J1\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0012\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0018\u00010\rR\u00020\u00000\fH\u0000¢\u0006\u0002\b\u001aR\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR \u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0018\u00010\rR\u00020\u00000\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/dotscreen/portal/model/GuideViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "channels", "", "Lcom/dotscreen/bokit/model/Channel;", "getChannels", "()Ljava/util/List;", "setChannels", "(Ljava/util/List;)V", "values", "Landroid/util/SparseArray;", "Landroidx/lifecycle/MutableLiveData;", "Lcom/dotscreen/portal/model/GuideViewModel$Data;", "getValue", "Landroidx/lifecycle/LiveData;", "time", "", "delta", "loadAirings", "", "data", "loadAirings$MegaCable_Portal_0_75_1_396__prodRelease", "loadChannels", "loadChannels$MegaCable_Portal_0_75_1_396__prodRelease", "loadValue", "loadValue$MegaCable_Portal_0_75_1_396__prodRelease", "Companion", "Data", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public abstract class GuideViewModel extends ViewModel {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private List<? extends Channel> channels;
    private final SparseArray<MutableLiveData<Data>> values = new SparseArray<>();

    public abstract void loadAirings$MegaCable_Portal_0_75_1_396__prodRelease(int time, int delta, MutableLiveData<Data> data);

    public abstract void loadChannels$MegaCable_Portal_0_75_1_396__prodRelease(int time, int delta, MutableLiveData<Data> data);

    protected final List<Channel> getChannels() {
        return this.channels;
    }

    protected final void setChannels(List<? extends Channel> list) {
        this.channels = list;
    }

    /* JADX INFO: compiled from: GuideViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\f\u0010\u0006¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/model/GuideViewModel$Companion;", "", "()V", "currentDayEnd", "Ljava/util/Calendar;", "getCurrentDayEnd", "()Ljava/util/Calendar;", "currentDayStart", "getCurrentDayStart", "lastDay", "getLastDay", "pastDayStart", "getPastDayStart", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Calendar getLastDay() {
            Calendar cf = Calendar.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(cf, "cf");
            cf.setTime(new Date());
            cf.set(11, 0);
            cf.set(12, 0);
            cf.set(13, 0);
            cf.add(5, 6);
            cf.add(13, -1);
            return cf;
        }

        public final Calendar getCurrentDayEnd() {
            Calendar cf = Calendar.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(cf, "cf");
            cf.setTime(new Date());
            cf.set(11, 0);
            cf.set(12, 0);
            cf.set(13, 0);
            cf.add(5, 1);
            cf.add(13, -1);
            return cf;
        }

        public final Calendar getCurrentDayStart() {
            Calendar cf = Calendar.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(cf, "cf");
            cf.setTime(new Date());
            cf.set(11, 0);
            cf.set(12, 0);
            cf.set(13, 0);
            cf.add(5, 0);
            return cf;
        }

        public final Calendar getPastDayStart() {
            Calendar cf = Calendar.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(cf, "cf");
            cf.setTime(new Date());
            cf.setTimeZone(TimeZone.getTimeZone("UTC"));
            cf.set(11, 0);
            cf.set(12, 0);
            cf.set(13, 1);
            cf.add(5, -2);
            return cf;
        }
    }

    /* JADX INFO: compiled from: GuideViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001BG\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00122\u0010\u0005\u001a.\u0012\u0004\u0012\u00020\u0007\u0012\f\u0012\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b0\u0006j\u0016\u0012\u0004\u0012\u00020\u0007\u0012\f\u0012\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b`\n¢\u0006\u0002\u0010\u000bJ1\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\u0006\u0010\u0015\u001a\u00020\u00072\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0017¢\u0006\u0002\u0010\u0019Jp\u0010\u001a\u001a8\u00124\u00122\u0012\u0004\u0012\u00020\u0007\u0012\f\u0012\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0018\u00010\u0006j\u0018\u0012\u0004\u0012\u00020\u0007\u0012\f\u0012\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0018\u0001`\n0\u001b22\u0010\u001c\u001a.\u0012\u0004\u0012\u00020\u0007\u0012\f\u0012\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b0\u0006j\u0016\u0012\u0004\u0012\u00020\u0007\u0012\f\u0012\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b`\nRF\u0010\u0005\u001a.\u0012\u0004\u0012\u00020\u0007\u0012\f\u0012\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b0\u0006j\u0016\u0012\u0004\u0012\u00020\u0007\u0012\f\u0012\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b`\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u001d"}, d2 = {"Lcom/dotscreen/portal/model/GuideViewModel$Data;", "", "channels", "", "Lcom/dotscreen/bokit/model/Channel;", "airingPerChannelMap", "Ljava/util/HashMap;", "", "", "Lcom/dotscreen/bokit/model/TVAiring;", "Lkotlin/collections/HashMap;", "(Lcom/dotscreen/portal/model/GuideViewModel;Ljava/util/List;Ljava/util/HashMap;)V", "getAiringPerChannelMap", "()Ljava/util/HashMap;", "setAiringPerChannelMap", "(Ljava/util/HashMap;)V", "getChannels", "()Ljava/util/List;", "setChannels", "(Ljava/util/List;)V", "getAiringsByChannel", "channelId", "startTimeMilli", "", "endTimeMilli", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;)Ljava/util/List;", "updateData", "Lio/reactivex/Single;", "airings", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public final class Data {
        private HashMap<String, List<TVAiring>> airingPerChannelMap;
        private List<? extends Channel> channels;
        final /* synthetic */ GuideViewModel this$0;

        public Data(GuideViewModel guideViewModel, List<? extends Channel> channels, HashMap<String, List<TVAiring>> airingPerChannelMap) {
            Intrinsics.checkParameterIsNotNull(channels, "channels");
            Intrinsics.checkParameterIsNotNull(airingPerChannelMap, "airingPerChannelMap");
            this.this$0 = guideViewModel;
            this.channels = channels;
            this.airingPerChannelMap = airingPerChannelMap;
        }

        public final HashMap<String, List<TVAiring>> getAiringPerChannelMap() {
            return this.airingPerChannelMap;
        }

        public final List<Channel> getChannels() {
            return this.channels;
        }

        public final void setAiringPerChannelMap(HashMap<String, List<TVAiring>> map) {
            Intrinsics.checkParameterIsNotNull(map, "<set-?>");
            this.airingPerChannelMap = map;
        }

        public final void setChannels(List<? extends Channel> list) {
            Intrinsics.checkParameterIsNotNull(list, "<set-?>");
            this.channels = list;
        }

        public static /* synthetic */ List getAiringsByChannel$default(Data data, String str, Long l, Long l2, int i, Object obj) {
            if ((i & 2) != 0) {
                l = (Long) null;
            }
            if ((i & 4) != 0) {
                l2 = (Long) null;
            }
            return data.getAiringsByChannel(str, l, l2);
        }

        public final List<TVAiring> getAiringsByChannel(String channelId, Long startTimeMilli, Long endTimeMilli) {
            List<TVAiring> list;
            Intrinsics.checkParameterIsNotNull(channelId, "channelId");
            if (startTimeMilli == null && endTimeMilli == null) {
                if (this.airingPerChannelMap.containsKey(channelId) && (list = this.airingPerChannelMap.get(channelId)) != null) {
                    return list;
                }
                ArrayList arrayList = new ArrayList();
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            if (this.airingPerChannelMap.containsKey(channelId) && this.airingPerChannelMap.get(channelId) != null) {
                List<TVAiring> list2 = this.airingPerChannelMap.get(channelId);
                if (list2 == null) {
                    Intrinsics.throwNpe();
                }
                if (list2.size() > 0) {
                    List<TVAiring> list3 = this.airingPerChannelMap.get(channelId);
                    if (list3 == null) {
                        Intrinsics.throwNpe();
                    }
                    for (TVAiring tVAiring : list3) {
                        if (Intrinsics.areEqual(tVAiring.getChannel().getId(), channelId)) {
                            if (tVAiring.getEndDate().getTime() > (startTimeMilli != null ? startTimeMilli.longValue() : 0L)) {
                                if (tVAiring.getStartDate().getTime() < (endTimeMilli != null ? endTimeMilli.longValue() : Long.MAX_VALUE)) {
                                    arrayList2.add(tVAiring);
                                }
                            }
                        }
                    }
                }
            }
            return arrayList2;
        }

        public final Single<HashMap<String, List<TVAiring>>> updateData(HashMap<String, List<TVAiring>> airings) {
            Intrinsics.checkParameterIsNotNull(airings, "airings");
            if (this.airingPerChannelMap.size() > 0) {
                Single<HashMap<String, List<TVAiring>>> map = Observable.fromIterable(airings.entrySet()).map(new Function<T, R>() { // from class: com.dotscreen.portal.model.GuideViewModel$Data$updateData$1
                    @Override // io.reactivex.functions.Function
                    public /* bridge */ /* synthetic */ Object apply(Object obj) {
                        return Integer.valueOf(apply((Map.Entry<String, ? extends List<? extends TVAiring>>) obj));
                    }

                    public final int apply(Map.Entry<String, ? extends List<? extends TVAiring>> it_future) {
                        Intrinsics.checkParameterIsNotNull(it_future, "it_future");
                        if (this.this$0.getAiringPerChannelMap().containsKey(it_future.getKey()) && this.this$0.getAiringPerChannelMap().get(it_future.getKey()) != null) {
                            ArrayList arrayList = new ArrayList();
                            List<? extends TVAiring> value = it_future.getValue();
                            if (value == null) {
                                Intrinsics.throwNpe();
                            }
                            for (TVAiring tVAiring : value) {
                                List<TVAiring> list = this.this$0.getAiringPerChannelMap().get(it_future.getKey());
                                if (list == null) {
                                    Intrinsics.throwNpe();
                                }
                                int size = list.size() - 1;
                                if (size > 0) {
                                    long time = tVAiring.getStartDate().getTime();
                                    List<TVAiring> list2 = this.this$0.getAiringPerChannelMap().get(it_future.getKey());
                                    if (list2 == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    if (time > list2.get(size).getStartDate().getTime()) {
                                        arrayList.add(tVAiring);
                                    }
                                }
                            }
                            List<TVAiring> list3 = this.this$0.getAiringPerChannelMap().get(it_future.getKey());
                            if (list3 == null) {
                                Intrinsics.throwNpe();
                            }
                            list3.addAll(arrayList);
                        }
                        return 1;
                    }
                }).toList().map(new Function<T, R>() { // from class: com.dotscreen.portal.model.GuideViewModel$Data$updateData$2
                    @Override // io.reactivex.functions.Function
                    public final HashMap<String, List<TVAiring>> apply(List<Integer> it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        return this.this$0.getAiringPerChannelMap();
                    }
                });
                Intrinsics.checkExpressionValueIsNotNull(map, "Observable\n             …p { airingPerChannelMap }");
                return map;
            }
            this.airingPerChannelMap = airings;
            Single<HashMap<String, List<TVAiring>>> singleJust = Single.just(airings);
            Intrinsics.checkExpressionValueIsNotNull(singleJust, "Single.just(airingPerChannelMap)");
            return singleJust;
        }
    }

    public static /* synthetic */ LiveData getValue$default(GuideViewModel guideViewModel, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getValue");
        }
        if ((i3 & 2) != 0) {
            i2 = 48;
        }
        return guideViewModel.getValue(i, i2);
    }

    public final LiveData<Data> getValue(int time, int delta) {
        this.values.put(time, new MutableLiveData<>());
        MutableLiveData<Data> mutableLiveData = this.values.get(time);
        Intrinsics.checkExpressionValueIsNotNull(mutableLiveData, "values[time]");
        loadValue$MegaCable_Portal_0_75_1_396__prodRelease(time, delta, mutableLiveData);
        MutableLiveData<Data> mutableLiveData2 = this.values.get(time);
        Intrinsics.checkExpressionValueIsNotNull(mutableLiveData2, "values[time]");
        return mutableLiveData2;
    }

    public final void loadValue$MegaCable_Portal_0_75_1_396__prodRelease(int time, int delta, MutableLiveData<Data> data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        Log.v(GuideViewModelKt.TAG, "loadValue option= " + time);
        if (this.channels != null) {
            Log.d(GuideViewModelKt.TAG, "channel list is already available");
            loadAirings$MegaCable_Portal_0_75_1_396__prodRelease(time, delta, data);
        } else {
            loadChannels$MegaCable_Portal_0_75_1_396__prodRelease(time, delta, data);
        }
    }
}
