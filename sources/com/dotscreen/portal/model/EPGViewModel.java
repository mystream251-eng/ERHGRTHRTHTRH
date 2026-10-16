package com.dotscreen.portal.model;

import android.util.Log;
import android.util.SparseArray;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.TVAiring;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EPGViewModel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\b&\u0018\u00002\u00020\u0001:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0018\u00010\rR\u00020\u00000\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J)\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u00112\u0012\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0018\u00010\rR\u00020\u00000\fH ¢\u0006\u0002\b\u0015J)\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u00112\u0012\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0018\u00010\rR\u00020\u00000\fH ¢\u0006\u0002\b\u0017J)\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u00112\u0012\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0018\u00010\rR\u00020\u00000\fH\u0000¢\u0006\u0002\b\u0019R\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR \u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0018\u00010\rR\u00020\u00000\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/dotscreen/portal/model/EPGViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "channels", "", "Lcom/dotscreen/bokit/model/Channel;", "getChannels", "()Ljava/util/List;", "setChannels", "(Ljava/util/List;)V", "values", "Landroid/util/SparseArray;", "Landroidx/lifecycle/MutableLiveData;", "Lcom/dotscreen/portal/model/EPGViewModel$Data;", "getValue", "Landroidx/lifecycle/LiveData;", "time", "", "loadAirings", "", "data", "loadAirings$MegaCable_Portal_0_75_1_396__prodRelease", "loadChannels", "loadChannels$MegaCable_Portal_0_75_1_396__prodRelease", "loadValue", "loadValue$MegaCable_Portal_0_75_1_396__prodRelease", "Data", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public abstract class EPGViewModel extends ViewModel {
    private List<? extends Channel> channels;
    private final SparseArray<MutableLiveData<Data>> values = new SparseArray<>();

    public abstract void loadAirings$MegaCable_Portal_0_75_1_396__prodRelease(int time, MutableLiveData<Data> data);

    public abstract void loadChannels$MegaCable_Portal_0_75_1_396__prodRelease(int time, MutableLiveData<Data> data);

    protected final List<Channel> getChannels() {
        return this.channels;
    }

    protected final void setChannels(List<? extends Channel> list) {
        this.channels = list;
    }

    /* JADX INFO: compiled from: EPGViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0004\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0002\u0010\u0007R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/dotscreen/portal/model/EPGViewModel$Data;", "", "channels", "", "Lcom/dotscreen/bokit/model/Channel;", "airings", "Lcom/dotscreen/bokit/model/TVAiring;", "(Lcom/dotscreen/portal/model/EPGViewModel;Ljava/util/List;Ljava/util/List;)V", "getAirings", "()Ljava/util/List;", "setAirings", "(Ljava/util/List;)V", "getChannels", "setChannels", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public final class Data {
        private List<? extends TVAiring> airings;
        private List<? extends Channel> channels;
        final /* synthetic */ EPGViewModel this$0;

        public Data(EPGViewModel ePGViewModel, List<? extends Channel> channels, List<? extends TVAiring> airings) {
            Intrinsics.checkParameterIsNotNull(channels, "channels");
            Intrinsics.checkParameterIsNotNull(airings, "airings");
            this.this$0 = ePGViewModel;
            this.channels = channels;
            this.airings = airings;
        }

        public final List<TVAiring> getAirings() {
            return this.airings;
        }

        public final List<Channel> getChannels() {
            return this.channels;
        }

        public final void setAirings(List<? extends TVAiring> list) {
            Intrinsics.checkParameterIsNotNull(list, "<set-?>");
            this.airings = list;
        }

        public final void setChannels(List<? extends Channel> list) {
            Intrinsics.checkParameterIsNotNull(list, "<set-?>");
            this.channels = list;
        }
    }

    public final LiveData<Data> getValue(int time) {
        this.values.put(time, new MutableLiveData<>());
        MutableLiveData<Data> mutableLiveData = this.values.get(time);
        Intrinsics.checkExpressionValueIsNotNull(mutableLiveData, "values[time]");
        loadValue$MegaCable_Portal_0_75_1_396__prodRelease(time, mutableLiveData);
        MutableLiveData<Data> mutableLiveData2 = this.values.get(time);
        Intrinsics.checkExpressionValueIsNotNull(mutableLiveData2, "values[time]");
        return mutableLiveData2;
    }

    public final void loadValue$MegaCable_Portal_0_75_1_396__prodRelease(int time, MutableLiveData<Data> data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        Log.v(EPGViewModelKt.TAG, "loadValue option= " + time);
        if (this.channels != null) {
            Log.d(EPGViewModelKt.TAG, "channel list is already available");
            loadAirings$MegaCable_Portal_0_75_1_396__prodRelease(time, data);
        } else {
            loadChannels$MegaCable_Portal_0_75_1_396__prodRelease(time, data);
        }
    }
}
