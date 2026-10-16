package com.dotscreen.portal.ui.screens.guide;

import android.util.ArraySet;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.portal.model.GuideViewModel;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: compiled from: ProgramManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b(\u0018\u00002\u00020\u0001:\u0004efghB\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010#\u001a\u00020$J\u000e\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020\u0019J\u000e\u0010'\u001a\u00020$2\u0006\u0010&\u001a\u00020\u001fJ\u000e\u0010(\u001a\u00020$2\u0006\u0010&\u001a\u00020!J \u0010)\u001a\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000e2\u0006\u0010*\u001a\u00020\u0011H\u0002J\u000e\u0010+\u001a\u00020$2\u0006\u0010,\u001a\u00020\u0006J\u0010\u0010-\u001a\u0004\u0018\u00010\u00112\u0006\u0010.\u001a\u00020\u001bJ\u0006\u0010/\u001a\u00020\u001bJ\u0010\u00100\u001a\u00020\u001b2\b\u0010*\u001a\u0004\u0018\u00010\u0011J\u000e\u00100\u001a\u00020\u001b2\u0006\u00101\u001a\u00020\u0006J\u0018\u00102\u001a\u00020\u001b2\u0006\u00103\u001a\u00020\u001b2\b\b\u0002\u00104\u001a\u00020\u0004J\u000e\u00105\u001a\u00020\u001b2\u0006\u00106\u001a\u00020\rJ\u0006\u00107\u001a\u00020\u0013J\u0016\u00108\u001a\u00020\u001b2\u0006\u00101\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u0006J\u0016\u0010:\u001a\u00020\u001b2\u0006\u00101\u001a\u00020\u00062\u0006\u0010;\u001a\u00020\u0013J\b\u0010<\u001a\u0004\u0018\u00010\u0011J\b\u0010=\u001a\u0004\u0018\u00010\rJ\u0006\u0010>\u001a\u00020\u0013J\u0006\u0010?\u001a\u00020\u0013J\u001a\u0010@\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001b0A2\u0006\u00101\u001a\u00020\u0006J\u0018\u0010B\u001a\u0004\u0018\u00010\r2\u0006\u00101\u001a\u00020\u00062\u0006\u0010C\u001a\u00020\u001bJ\u001a\u0010B\u001a\u0004\u0018\u00010\r2\u0006\u00101\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u0006H\u0002J\u000e\u0010D\u001a\u00020\u001b2\u0006\u00101\u001a\u00020\u0006J\u0006\u0010E\u001a\u00020\u0013J\b\u0010F\u001a\u00020$H\u0002J\b\u0010G\u001a\u00020$H\u0002J\b\u0010H\u001a\u00020$H\u0002J\u0010\u0010I\u001a\u00020$2\u0006\u0010J\u001a\u00020\rH\u0002J\b\u0010K\u001a\u00020$H\u0002J\u0010\u0010L\u001a\u00020$2\b\u0010&\u001a\u0004\u0018\u00010\u0019J\u0010\u0010M\u001a\u00020$2\b\u0010&\u001a\u0004\u0018\u00010\u001fJ\u0012\u0010N\u001a\u00020$2\n\u0010O\u001a\u00060\bR\u00020\tJ\u0010\u0010P\u001a\u00020$2\b\u0010Q\u001a\u0004\u0018\u00010\rJ\u0018\u0010R\u001a\u00020$2\u0006\u0010S\u001a\u00020\u00132\u0006\u0010T\u001a\u00020\u0013H\u0002J\u000e\u0010U\u001a\u00020$2\u0006\u0010V\u001a\u00020\u0013J\u0018\u0010W\u001a\u00020$2\u0006\u0010X\u001a\u00020\u00042\u0006\u0010Y\u001a\u00020\u0004H\u0002J\u0012\u0010Z\u001a\u00020$2\n\u0010O\u001a\u00060\bR\u00020\tJ\u0018\u0010[\u001a\u00020$2\u0006\u0010\\\u001a\u00020\r2\u0006\u0010]\u001a\u00020\rH\u0002J\u001e\u0010^\u001a\u00020$2\u0006\u0010_\u001a\u00020\u00132\u0006\u0010`\u001a\u00020\u00132\u0006\u0010a\u001a\u00020\u0013J\u0018\u0010b\u001a\u00020$2\u0006\u0010X\u001a\u00020\u00042\u0006\u0010c\u001a\u00020\u0004H\u0002J\u0016\u0010d\u001a\u00020$2\u0006\u0010_\u001a\u00020\u00132\u0006\u0010`\u001a\u00020\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0018\u00010\bR\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000e0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0014\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006i"}, d2 = {"Lcom/dotscreen/portal/ui/screens/guide/ProgramManager;", "", "()V", "DEBUG", "", "TAG", "", "current_data", "Lcom/dotscreen/portal/model/GuideViewModel$Data;", "Lcom/dotscreen/portal/model/GuideViewModel;", "mChannelIdEntriesMap", "", "Ljava/util/ArrayList;", "Lcom/dotscreen/portal/ui/screens/guide/TableEntry;", "Lkotlin/collections/ArrayList;", "mChannels", "", "Lcom/dotscreen/bokit/model/Channel;", "mEndUtcMillis", "", "mFilteredChannels", "", "mFromUtcMillis", "mListeners", "", "Lcom/dotscreen/portal/ui/screens/guide/ProgramManager$Listener;", "mSelectedGenreId", "", "mSelectedProgram", "mStartUtcMillis", "mTableEntriesUpdatedListeners", "Lcom/dotscreen/portal/ui/screens/guide/ProgramManager$TableEntriesUpdatedListener;", "mTableEntryChangedListeners", "Lcom/dotscreen/portal/ui/screens/guide/ProgramManager$TableEntryChangedListener;", "mToUtcMillis", "ProgramManager", "", "addListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "addTableEntriesUpdatedListener", "addTableEntryChangedListener", "createProgramEntries", "channel", "filterChannel", "filter", "getChannel", "channelIndex", "getChannelCount", "getChannelIndex", "channelId", "getChannelPositionByZappingNumber", "zappingNumber", "use_approx", "getCurrentChannelIndexByTableEntry", "table", "getFromUtcMillis", "getProgramIdIndex", "entryId", "getProgramIndexAtTime", "time", "getSelectedChannel", "getSelectedProgram", "getShiftedTime", "getStartTime", "getSurroundChannelsZappingNumber", "Lkotlin/Pair;", "getTableEntry", "index", "getTableEntryCount", "getToUtcMillis", "notifyChannelsUpdated", "notifyGenresUpdated", "notifyTableEntriesUpdated", "notifyTableEntryUpdated", "entry", "notifyTimeRangeUpdated", "removeListener", "removeTableEntriesUpdatedListener", "setContent", "new_data", "setSelectedProgram", "selectecProgram", "setTimeRange", "fromUtcMillis", "toUtcMillis", "shiftTime", "timeMillisToScroll", "updateChannels", "notify", "clearPreviousTableEntries", "updateContent", "updateEntry", "old", "newEntry", "updateInitialTimeRange", "startUtcMillis", "endUtcMillis", "delta", "updateTableEntries", "clear", "updateTimeRange", "Listener", "ListenerAdapter", "TableEntriesUpdatedListener", "TableEntryChangedListener", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class ProgramManager {
    private final boolean DEBUG;
    private GuideViewModel.Data current_data;
    private long mEndUtcMillis;
    private long mFromUtcMillis;
    private int mSelectedGenreId;
    private TableEntry mSelectedProgram;
    private long mStartUtcMillis;
    private long mToUtcMillis;
    private final String TAG = "ProgramManager";
    private List<Channel> mChannels = new ArrayList();
    private final Map<String, ArrayList<TableEntry>> mChannelIdEntriesMap = new HashMap();
    private List<? extends Channel> mFilteredChannels = this.mChannels;
    private final Set<Listener> mListeners = new ArraySet();
    private final Set<TableEntriesUpdatedListener> mTableEntriesUpdatedListeners = new ArraySet();
    private final Set<TableEntryChangedListener> mTableEntryChangedListeners = new ArraySet();

    /* JADX INFO: compiled from: ProgramManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&¨\u0006\u0006"}, d2 = {"Lcom/dotscreen/portal/ui/screens/guide/ProgramManager$Listener;", "", "onChannelsUpdated", "", "onGenresUpdated", "onTimeRangeUpdated", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public interface Listener {
        void onChannelsUpdated();

        void onGenresUpdated();

        void onTimeRangeUpdated();
    }

    /* JADX INFO: compiled from: ProgramManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016¨\u0006\u0007"}, d2 = {"Lcom/dotscreen/portal/ui/screens/guide/ProgramManager$ListenerAdapter;", "Lcom/dotscreen/portal/ui/screens/guide/ProgramManager$Listener;", "()V", "onChannelsUpdated", "", "onGenresUpdated", "onTimeRangeUpdated", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static class ListenerAdapter implements Listener {
        @Override // com.dotscreen.portal.ui.screens.guide.ProgramManager.Listener
        public void onChannelsUpdated() {
        }

        @Override // com.dotscreen.portal.ui.screens.guide.ProgramManager.Listener
        public void onGenresUpdated() {
        }

        @Override // com.dotscreen.portal.ui.screens.guide.ProgramManager.Listener
        public void onTimeRangeUpdated() {
        }
    }

    /* JADX INFO: compiled from: ProgramManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/dotscreen/portal/ui/screens/guide/ProgramManager$TableEntriesUpdatedListener;", "", "onTableEntriesUpdated", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public interface TableEntriesUpdatedListener {
        void onTableEntriesUpdated();
    }

    /* JADX INFO: compiled from: ProgramManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/dotscreen/portal/ui/screens/guide/ProgramManager$TableEntryChangedListener;", "", "onTableEntryChanged", "", "entry", "Lcom/dotscreen/portal/ui/screens/guide/TableEntry;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public interface TableEntryChangedListener {
        void onTableEntryChanged(TableEntry entry);
    }

    public final void ProgramManager() {
    }

    public final void setContent(GuideViewModel.Data new_data) {
        Intrinsics.checkParameterIsNotNull(new_data, "new_data");
        this.current_data = new_data;
        updateChannels(true, true);
    }

    public final void updateContent(GuideViewModel.Data new_data) {
        Intrinsics.checkParameterIsNotNull(new_data, "new_data");
        GuideViewModel.Data data = this.current_data;
        if (data == null) {
            this.current_data = new_data;
            updateTableEntries(true, false);
        } else {
            Single<HashMap<String, List<TVAiring>>> singleUpdateData = data != null ? data.updateData(new_data.getAiringPerChannelMap()) : null;
            if (singleUpdateData == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(singleUpdateData.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<HashMap<String, List<TVAiring>>>() { // from class: com.dotscreen.portal.ui.screens.guide.ProgramManager.updateContent.1
                @Override // io.reactivex.functions.Consumer
                public final void accept(HashMap<String, List<TVAiring>> map) {
                    ProgramManager.this.updateTableEntries(true, false);
                }
            }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.guide.ProgramManager.updateContent.2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                    th.printStackTrace();
                }
            }), "current_data?.updateData…                       })");
        }
    }

    public final void addListener(Listener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.mListeners.add(listener);
    }

    public final void addTableEntriesUpdatedListener(TableEntriesUpdatedListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.mTableEntriesUpdatedListeners.add(listener);
    }

    public final void addTableEntryChangedListener(TableEntryChangedListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.mTableEntryChangedListeners.add(listener);
    }

    public final void removeListener(Listener listener) {
        Set<Listener> set = this.mListeners;
        if (set == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableCollection<T>");
        }
        TypeIntrinsics.asMutableCollection(set).remove(listener);
    }

    public final void removeTableEntriesUpdatedListener(TableEntriesUpdatedListener listener) {
        Set<TableEntriesUpdatedListener> set = this.mTableEntriesUpdatedListeners;
        if (set == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableCollection<T>");
        }
        TypeIntrinsics.asMutableCollection(set).remove(listener);
    }

    private final void updateChannels(boolean notify, boolean clearPreviousTableEntries) {
        GuideViewModel.Data data = this.current_data;
        if (data == null) {
            Intrinsics.throwNpe();
        }
        List<Channel> mutableList = CollectionsKt.toMutableList((Collection) data.getChannels());
        this.mChannels = mutableList;
        this.mSelectedGenreId = 0;
        this.mFilteredChannels = mutableList;
        if (notify) {
            notifyChannelsUpdated();
        }
        updateTableEntries(notify, clearPreviousTableEntries);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateTableEntries(boolean notify, boolean clear) {
        int i;
        int i2;
        if (clear) {
            this.mChannelIdEntriesMap.clear();
        }
        for (Channel channel : this.mChannels) {
            String id = channel.getId();
            ArrayList<TableEntry> arrayListCreateProgramEntries = createProgramEntries(channel);
            ArrayList<TableEntry> arrayList = this.mChannelIdEntriesMap.get(id);
            long j = Long.MAX_VALUE;
            int i3 = 1;
            if ((arrayList != null ? arrayList.size() : 0) > 0) {
                if (arrayListCreateProgramEntries.size() > 0) {
                    Object[] array = arrayListCreateProgramEntries.toArray();
                    int length = array.length;
                    int i4 = 0;
                    while (i4 < length) {
                        Object obj = array[i4];
                        ArrayList<TableEntry> arrayList2 = this.mChannelIdEntriesMap.get(id);
                        if (arrayList2 == null) {
                            Intrinsics.throwNpe();
                        }
                        ArrayList<TableEntry> arrayList3 = arrayList2;
                        ArrayList<TableEntry> arrayList4 = this.mChannelIdEntriesMap.get(id);
                        if (arrayList4 == null) {
                            Intrinsics.throwNpe();
                        }
                        TableEntry tableEntry = arrayList3.get(arrayList4.size() - i3);
                        Intrinsics.checkExpressionValueIsNotNull(tableEntry, "mChannelIdEntriesMap[cha…ap[channelId]!!.size - 1]");
                        TableEntry tableEntry2 = tableEntry;
                        if (obj != null) {
                            TableEntry tableEntry3 = (TableEntry) obj;
                            if (tableEntry3.getEntryStartUtcMillis() != tableEntry2.getEntryStartUtcMillis() || tableEntry3.getEntryEndUtcMillis() == tableEntry2.getEntryEndUtcMillis()) {
                                if (tableEntry3.getEntryStartUtcMillis() == 0) {
                                    String str = id;
                                    if (tableEntry2.getEntryStartUtcMillis() == this.mStartUtcMillis && tableEntry3.getEntryEndUtcMillis() == j && tableEntry2.getEntryEndUtcMillis() == this.mEndUtcMillis) {
                                        id = str;
                                        ArrayList arrayList5 = this.mChannelIdEntriesMap.get(id);
                                        if (arrayList5 == null) {
                                            Intrinsics.throwNpe();
                                        }
                                        ArrayList arrayList6 = arrayList5;
                                        ArrayList<TableEntry> arrayList7 = this.mChannelIdEntriesMap.get(id);
                                        if (arrayList7 == null) {
                                            Intrinsics.throwNpe();
                                        }
                                        i2 = 1;
                                        arrayList6.set(arrayList7.size() - 1, obj);
                                    } else {
                                        id = str;
                                        i2 = 1;
                                    }
                                } else {
                                    i2 = i3;
                                }
                                if (tableEntry3.getEntryStartUtcMillis() > tableEntry2.getEntryStartUtcMillis()) {
                                    ArrayList arrayList8 = this.mChannelIdEntriesMap.get(id);
                                    if (arrayList8 == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    arrayList8.add(obj);
                                }
                            } else {
                                ArrayList arrayList9 = this.mChannelIdEntriesMap.get(id);
                                if (arrayList9 == null) {
                                    Intrinsics.throwNpe();
                                }
                                ArrayList arrayList10 = arrayList9;
                                ArrayList<TableEntry> arrayList11 = this.mChannelIdEntriesMap.get(id);
                                if (arrayList11 == null) {
                                    Intrinsics.throwNpe();
                                }
                                arrayList10.set(arrayList11.size() - i3, obj);
                                i2 = i3;
                            }
                            i4++;
                            i3 = i2;
                            j = Long.MAX_VALUE;
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.guide.TableEntry");
                        }
                    }
                }
            } else {
                this.mChannelIdEntriesMap.put(id, new ArrayList<>());
                if (arrayListCreateProgramEntries.size() > 0) {
                    for (Object obj2 : arrayListCreateProgramEntries.toArray()) {
                        if (obj2 != null) {
                            TableEntry tableEntry4 = (TableEntry) obj2;
                            long entryStartUtcMillis = tableEntry4.getEntryStartUtcMillis();
                            long j2 = this.mStartUtcMillis;
                            if (entryStartUtcMillis < j2 && j2 < tableEntry4.getEntryEndUtcMillis() && tableEntry4.getEntryStartUtcMillis() != 0) {
                                tableEntry4.setEntryStartUtcMillis(this.mStartUtcMillis);
                            }
                            String str2 = id;
                            if (tableEntry4.getEntryStartUtcMillis() >= this.mStartUtcMillis) {
                                id = str2;
                                ArrayList arrayList12 = this.mChannelIdEntriesMap.get(id);
                                if (arrayList12 == null) {
                                    Intrinsics.throwNpe();
                                }
                                arrayList12.add(obj2);
                            } else {
                                id = str2;
                            }
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.guide.TableEntry");
                        }
                    }
                }
            }
            if (arrayListCreateProgramEntries.size() == 0) {
                i = 1;
                arrayListCreateProgramEntries.add(new TableEntry(channel, 0L, Long.MAX_VALUE, false));
            } else {
                i = 1;
            }
            ArrayList<TableEntry> arrayList13 = this.mChannelIdEntriesMap.get(id);
            if (arrayList13 != null && arrayList13.isEmpty() == i) {
                ArrayList<TableEntry> arrayList14 = this.mChannelIdEntriesMap.get(id);
                if (arrayList14 != null) {
                    arrayList14.add(new TableEntry(channel, this.mStartUtcMillis, this.mEndUtcMillis));
                }
            } else {
                ArrayList<TableEntry> arrayList15 = this.mChannelIdEntriesMap.get(id);
                if ((arrayList15 != null ? arrayList15.size() : 0) == i) {
                    ArrayList<TableEntry> arrayList16 = this.mChannelIdEntriesMap.get(id);
                    if (arrayList16 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (arrayList16.get(0).get_isGap()) {
                        ArrayList<TableEntry> arrayList17 = this.mChannelIdEntriesMap.get(id);
                        if (arrayList17 != null) {
                            ArrayList<TableEntry> arrayList18 = this.mChannelIdEntriesMap.get(id);
                            if (arrayList18 == null) {
                                Intrinsics.throwNpe();
                            }
                            arrayList17.remove(arrayList18.size() - i);
                        }
                        ArrayList<TableEntry> arrayList19 = this.mChannelIdEntriesMap.get(id);
                        if (arrayList19 != null) {
                            arrayList19.add(new TableEntry(channel, this.mStartUtcMillis, this.mEndUtcMillis));
                        }
                    }
                }
                ArrayList<TableEntry> arrayList20 = this.mChannelIdEntriesMap.get(id);
                if ((arrayList20 != null ? arrayList20.size() : 0) == i) {
                    ArrayList<TableEntry> arrayList21 = this.mChannelIdEntriesMap.get(id);
                    if (arrayList21 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (arrayList21.get(0).getEntryStartUtcMillis() == 0) {
                        ArrayList<TableEntry> arrayList22 = this.mChannelIdEntriesMap.get(id);
                        if (arrayList22 == null) {
                            Intrinsics.throwNpe();
                        }
                        if (arrayList22.get(0).getEntryEndUtcMillis() == Long.MAX_VALUE) {
                            ArrayList<TableEntry> arrayList23 = this.mChannelIdEntriesMap.get(id);
                            if (arrayList23 != null) {
                                ArrayList<TableEntry> arrayList24 = this.mChannelIdEntriesMap.get(id);
                                if (arrayList24 == null) {
                                    Intrinsics.throwNpe();
                                }
                                arrayList23.remove(arrayList24.size() - i);
                            }
                            ArrayList<TableEntry> arrayList25 = this.mChannelIdEntriesMap.get(id);
                            if (arrayList25 != null) {
                                arrayList25.add(new TableEntry(channel, this.mStartUtcMillis, this.mEndUtcMillis));
                            }
                        }
                    }
                }
                if (this.mChannelIdEntriesMap.get(id) != null) {
                    ArrayList<TableEntry> arrayList26 = this.mChannelIdEntriesMap.get(id);
                    if (arrayList26 == null) {
                        Intrinsics.throwNpe();
                    }
                    ArrayList<TableEntry> arrayList27 = arrayList26;
                    ArrayList<TableEntry> arrayList28 = this.mChannelIdEntriesMap.get(id);
                    if (arrayList28 == null) {
                        Intrinsics.throwNpe();
                    }
                    TableEntry tableEntry5 = arrayList27.get(arrayList28.size() - i);
                    Intrinsics.checkExpressionValueIsNotNull(tableEntry5, "mChannelIdEntriesMap[cha…ap[channelId]!!.size - 1]");
                    TableEntry tableEntry6 = tableEntry5;
                    if (this.mEndUtcMillis > tableEntry6.getEntryEndUtcMillis()) {
                        ArrayList<TableEntry> arrayList29 = this.mChannelIdEntriesMap.get(id);
                        if (arrayList29 == null) {
                            Intrinsics.throwNpe();
                        }
                        arrayList29.add(new TableEntry(channel, tableEntry6.getEntryEndUtcMillis(), this.mEndUtcMillis));
                    } else if (this.mEndUtcMillis < tableEntry6.getEntryEndUtcMillis()) {
                        ArrayList<TableEntry> arrayList30 = this.mChannelIdEntriesMap.get(id);
                        if (arrayList30 == null) {
                            Intrinsics.throwNpe();
                        }
                        ArrayList<TableEntry> arrayList31 = arrayList30;
                        ArrayList<TableEntry> arrayList32 = this.mChannelIdEntriesMap.get(id);
                        if (arrayList32 == null) {
                            Intrinsics.throwNpe();
                        }
                        arrayList31.remove(arrayList32.size() - i);
                        ArrayList<TableEntry> arrayList33 = this.mChannelIdEntriesMap.get(id);
                        if (arrayList33 == null) {
                            Intrinsics.throwNpe();
                        }
                        arrayList33.add(new TableEntry(tableEntry6.getChannel(), tableEntry6.getProgram(), tableEntry6.getEntryStartUtcMillis(), this.mEndUtcMillis, false));
                    } else if (tableEntry6.getEntryEndUtcMillis() == Long.MAX_VALUE) {
                        ArrayList<TableEntry> arrayList34 = this.mChannelIdEntriesMap.get(id);
                        if (arrayList34 == null) {
                            Intrinsics.throwNpe();
                        }
                        ArrayList<TableEntry> arrayList35 = arrayList34;
                        ArrayList<TableEntry> arrayList36 = this.mChannelIdEntriesMap.get(id);
                        if (arrayList36 == null) {
                            Intrinsics.throwNpe();
                        }
                        arrayList35.remove(arrayList36.size() - i);
                        ArrayList<TableEntry> arrayList37 = this.mChannelIdEntriesMap.get(id);
                        if (arrayList37 == null) {
                            Intrinsics.throwNpe();
                        }
                        arrayList37.add(new TableEntry(tableEntry6.getChannel(), tableEntry6.getProgram(), this.mStartUtcMillis, this.mEndUtcMillis, false));
                    }
                }
            }
        }
        if (notify) {
            notifyTableEntriesUpdated();
        }
    }

    private final ArrayList<TableEntry> createProgramEntries(Channel channel) {
        ArrayList arrayList;
        TVAiring tVAiring;
        long time;
        ArrayList<TableEntry> arrayList2 = new ArrayList<>();
        Long lValueOf = (Long) null;
        if (CommonUtils.isNetflixChannel(channel) || CommonUtils.isAmazonPrimeChannel(channel)) {
            arrayList2.add(new TableEntry(channel, 0L, Long.MAX_VALUE, true));
            return arrayList2;
        }
        if (this.mChannelIdEntriesMap.containsKey(channel.getId())) {
            ArrayList<TableEntry> arrayList3 = this.mChannelIdEntriesMap.get(channel.getId());
            if ((arrayList3 != null ? arrayList3.size() : 0) > 0) {
                ArrayList<TableEntry> arrayList4 = this.mChannelIdEntriesMap.get(channel.getId());
                if (arrayList4 == null) {
                    Intrinsics.throwNpe();
                }
                ArrayList<TableEntry> arrayList5 = arrayList4;
                ArrayList<TableEntry> arrayList6 = this.mChannelIdEntriesMap.get(channel.getId());
                if (arrayList6 == null) {
                    Intrinsics.throwNpe();
                }
                lValueOf = Long.valueOf(arrayList5.get(arrayList6.size() - 1).getEntryStartUtcMillis());
            }
        }
        long jLongValue = lValueOf != null ? lValueOf.longValue() : this.mStartUtcMillis;
        GuideViewModel.Data data = this.current_data;
        if (data == null || (arrayList = GuideViewModel.Data.getAiringsByChannel$default(data, channel.getId(), lValueOf, null, 4, null)) == null) {
            arrayList = new ArrayList();
        }
        Iterator it = arrayList.iterator();
        loop0: while (true) {
            long j = jLongValue;
            while (true) {
                if (!it.hasNext()) {
                    break loop0;
                }
                tVAiring = (TVAiring) it.next();
                if (!Intrinsics.areEqual(tVAiring.getChannel().getId(), "-1")) {
                    long jMax = Math.max(tVAiring.getStartDate().getTime(), lValueOf != null ? lValueOf.longValue() : this.mStartUtcMillis);
                    jLongValue = tVAiring.getEndDate().getTime();
                    time = tVAiring.getEndDate().getTime() - jMax;
                    if (jMax > j) {
                        arrayList2.add(new TableEntry(channel, j, jMax));
                        j = jMax;
                    }
                    if (jLongValue > j) {
                        break;
                    }
                }
            }
            if (arrayList2.size() > 0 && time <= TimeUnit.MINUTES.toMillis(1L)) {
                arrayList2.get(arrayList2.size() - 1).setEntryEndUtcMillis(jLongValue);
            } else {
                arrayList2.add(new TableEntry(channel, tVAiring, j, jLongValue, false));
            }
        }
        if (arrayList2.size() > 0) {
            TableEntry tableEntry = arrayList2.get(0);
            Intrinsics.checkExpressionValueIsNotNull(tableEntry, "entries[0]");
            if (tableEntry.getEntryStartUtcMillis() < (lValueOf != null ? lValueOf.longValue() : this.mStartUtcMillis)) {
                arrayList2.get(0).setEntryStartUtcMillis(lValueOf != null ? lValueOf.longValue() : this.mStartUtcMillis);
            }
        } else if (arrayList2.size() > 1) {
            TableEntry tableEntry2 = arrayList2.get(1);
            Intrinsics.checkExpressionValueIsNotNull(tableEntry2, "entries[1]");
            if (tableEntry2.getEntryStartUtcMillis() < (lValueOf != null ? lValueOf.longValue() : this.mStartUtcMillis) + TimeUnit.MINUTES.toMillis(1L)) {
                arrayList2.remove(0);
                arrayList2.get(0).setEntryStartUtcMillis(lValueOf != null ? lValueOf.longValue() : this.mStartUtcMillis);
            }
        }
        return arrayList2;
    }

    private final void notifyGenresUpdated() {
        Iterator<Listener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onGenresUpdated();
        }
    }

    private final void notifyChannelsUpdated() {
        Iterator<Listener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onChannelsUpdated();
        }
    }

    private final void notifyTimeRangeUpdated() {
        Iterator<Listener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onTimeRangeUpdated();
        }
    }

    private final void notifyTableEntriesUpdated() {
        Iterator<TableEntriesUpdatedListener> it = this.mTableEntriesUpdatedListeners.iterator();
        while (it.hasNext()) {
            it.next().onTableEntriesUpdated();
        }
    }

    private final void notifyTableEntryUpdated(TableEntry entry) {
        Iterator<TableEntryChangedListener> it = this.mTableEntryChangedListeners.iterator();
        while (it.hasNext()) {
            it.next().onTableEntryChanged(entry);
        }
    }

    private final void updateEntry(TableEntry old, TableEntry newEntry) {
        ArrayList<TableEntry> arrayList = this.mChannelIdEntriesMap.get(old.getChannelId());
        if (arrayList == null) {
            Intrinsics.throwNpe();
        }
        arrayList.set(arrayList.indexOf(old), newEntry);
        notifyTableEntryUpdated(newEntry);
    }

    private final TableEntry getTableEntry(String channelId, String entryId) {
        ArrayList<TableEntry> arrayList = this.mChannelIdEntriesMap.get(channelId);
        if (arrayList == null) {
            return null;
        }
        for (TableEntry tableEntry : arrayList) {
            if (Intrinsics.areEqual(tableEntry.getId(), entryId)) {
                return tableEntry;
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: getFromUtcMillis, reason: from getter */
    public final long getMFromUtcMillis() {
        return this.mFromUtcMillis;
    }

    /* JADX INFO: renamed from: getToUtcMillis, reason: from getter */
    public final long getMToUtcMillis() {
        return this.mToUtcMillis;
    }

    public final void updateInitialTimeRange(long startUtcMillis, long endUtcMillis, long delta) {
        updateTimeRange(startUtcMillis, endUtcMillis);
        setTimeRange(startUtcMillis, delta + startUtcMillis);
    }

    public final void updateTimeRange(long startUtcMillis, long endUtcMillis) {
        this.mStartUtcMillis = startUtcMillis;
        if (endUtcMillis > this.mEndUtcMillis) {
            this.mEndUtcMillis = endUtcMillis;
        }
    }

    private final void setTimeRange(long fromUtcMillis, long toUtcMillis) {
        if (this.mFromUtcMillis == fromUtcMillis && this.mToUtcMillis == toUtcMillis) {
            return;
        }
        this.mFromUtcMillis = fromUtcMillis;
        this.mToUtcMillis = toUtcMillis;
        notifyTimeRangeUpdated();
    }

    public final int getChannelCount() {
        List<? extends Channel> list = this.mFilteredChannels;
        if (list == null) {
            Intrinsics.throwNpe();
        }
        return list.size();
    }

    public final Channel getChannel(int channelIndex) {
        if (channelIndex < 0 || channelIndex >= getChannelCount()) {
            return null;
        }
        List<? extends Channel> list = this.mFilteredChannels;
        if (list == null) {
            Intrinsics.throwNpe();
        }
        return list.get(channelIndex);
    }

    public static /* synthetic */ int getChannelPositionByZappingNumber$default(ProgramManager programManager, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        return programManager.getChannelPositionByZappingNumber(i, z);
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0053  */
    /* JADX WARN: Code duplicated, block: B:29:0x0059  */
    public final int getChannelPositionByZappingNumber(int zappingNumber, boolean use_approx) {
        Object obj;
        Channel channel = (Channel) null;
        List<Channel> list = this.mFilteredChannels;
        if (list == null) {
            list = this.mChannels;
        }
        for (Channel channel2 : list) {
            if (channel2 != null && channel2.getZappingNumber() == zappingNumber) {
                channel = channel2;
                break;
            }
        }
        if (channel != null || !use_approx) {
            if (channel != null) {
                return list.indexOf(channel);
            }
            return -1;
        }
        int size = list.size();
        int i = -1;
        int iAbs = 0;
        for (int i2 = 0; i2 < size; i2++) {
            if (list.get(i2) != null) {
                if (iAbs == 0) {
                    obj = list.get(i2);
                    if (obj == null) {
                        Intrinsics.throwNpe();
                    }
                    iAbs = Math.abs(zappingNumber - ((Channel) obj).getZappingNumber());
                    i = i2;
                } else {
                    Object obj2 = list.get(i2);
                    if (obj2 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (Math.abs(zappingNumber - ((Channel) obj2).getZappingNumber()) < iAbs) {
                        obj = list.get(i2);
                        if (obj == null) {
                            Intrinsics.throwNpe();
                        }
                        iAbs = Math.abs(zappingNumber - ((Channel) obj).getZappingNumber());
                        i = i2;
                    }
                }
            }
        }
        return i;
    }

    public final int getChannelIndex(Channel channel) {
        List<? extends Channel> list = this.mFilteredChannels;
        if (list == null) {
            Intrinsics.throwNpe();
        }
        return list.indexOf(channel);
    }

    public final int getCurrentChannelIndexByTableEntry(TableEntry table) {
        Intrinsics.checkParameterIsNotNull(table, "table");
        System.out.println((Object) ("GuideActivity " + table.getChannel()));
        List<? extends Channel> list = this.mFilteredChannels;
        if (list == null) {
            Intrinsics.throwNpe();
        }
        return list.indexOf(table.getChannel());
    }

    public final int getChannelIndex(String channelId) {
        List<Channel> channels;
        Intrinsics.checkParameterIsNotNull(channelId, "channelId");
        GuideViewModel.Data data = this.current_data;
        int iIndexOf = 0;
        if (data != null && (channels = data.getChannels()) != null) {
            for (Channel channel : channels) {
                if (Intrinsics.areEqual(channel.getId(), channelId)) {
                    GuideViewModel.Data data2 = this.current_data;
                    if (data2 == null) {
                        Intrinsics.throwNpe();
                    }
                    iIndexOf = data2.getChannels().indexOf(channel);
                }
            }
        }
        return iIndexOf;
    }

    public final int getTableEntryCount(String channelId) {
        Intrinsics.checkParameterIsNotNull(channelId, "channelId");
        ArrayList<TableEntry> arrayList = this.mChannelIdEntriesMap.get(channelId);
        if (arrayList == null) {
            Intrinsics.throwNpe();
        }
        return arrayList.size();
    }

    public final TableEntry getTableEntry(String channelId, int index) {
        Intrinsics.checkParameterIsNotNull(channelId, "channelId");
        ArrayList<TableEntry> arrayList = this.mChannelIdEntriesMap.get(channelId);
        if (arrayList == null) {
            Intrinsics.throwNpe();
        }
        return arrayList.get(index);
    }

    public final Pair<Integer, Integer> getSurroundChannelsZappingNumber(String channelId) {
        int zappingNumber;
        Intrinsics.checkParameterIsNotNull(channelId, "channelId");
        int zappingNumber2 = -1;
        if (this.mChannels.size() > 0) {
            for (Channel channel : this.mChannels) {
                if (Intrinsics.areEqual(channel.getId(), channelId)) {
                    if (this.mChannels.indexOf(channel) != 0) {
                        List<Channel> list = this.mChannels;
                        zappingNumber = list.get(list.indexOf(channel) - 1).getZappingNumber();
                    } else {
                        zappingNumber = -1;
                    }
                    if (this.mChannels.indexOf(channel) != this.mChannels.size() - 1) {
                        List<Channel> list2 = this.mChannels;
                        zappingNumber2 = list2.get(list2.indexOf(channel) + 1).getZappingNumber();
                    }
                    return new Pair<>(Integer.valueOf(zappingNumber), Integer.valueOf(zappingNumber2));
                }
            }
        }
        return new Pair<>(-1, -1);
    }

    public final Channel getSelectedChannel() {
        List<Channel> channels;
        GuideViewModel.Data data = this.current_data;
        if (data == null || (channels = data.getChannels()) == null) {
            return null;
        }
        return channels.get(0);
    }

    /* JADX INFO: renamed from: getSelectedProgram, reason: from getter */
    public final TableEntry getMSelectedProgram() {
        return this.mSelectedProgram;
    }

    public final void setSelectedProgram(TableEntry selectecProgram) {
        this.mSelectedProgram = selectecProgram;
    }

    public final void shiftTime(long timeMillisToScroll) {
        long j = this.mFromUtcMillis + timeMillisToScroll;
        long j2 = this.mToUtcMillis + timeMillisToScroll;
        long j3 = this.mStartUtcMillis;
        if (j < j3) {
            j2 += j3 - j3;
            j = j3;
        }
        long j4 = this.mEndUtcMillis;
        if (j2 > j4) {
            j -= j2 - j4;
            j2 = j4;
        }
        setTimeRange(j, j2);
    }

    public final long getShiftedTime() {
        return this.mFromUtcMillis - this.mStartUtcMillis;
    }

    /* JADX INFO: renamed from: getStartTime, reason: from getter */
    public final long getMStartUtcMillis() {
        return this.mStartUtcMillis;
    }

    public final int getProgramIdIndex(String channelId, String entryId) {
        Intrinsics.checkParameterIsNotNull(channelId, "channelId");
        Intrinsics.checkParameterIsNotNull(entryId, "entryId");
        ArrayList<TableEntry> arrayList = this.mChannelIdEntriesMap.get(channelId);
        if (arrayList == null) {
            return -1;
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            TVAiring program = arrayList.get(i).getProgram();
            if (Intrinsics.areEqual(program != null ? program.getId() : null, entryId)) {
                return i;
            }
        }
        return -1;
    }

    public final int getProgramIndexAtTime(String channelId, long time) {
        Intrinsics.checkParameterIsNotNull(channelId, "channelId");
        ArrayList<TableEntry> arrayList = this.mChannelIdEntriesMap.get(channelId);
        if (arrayList == null) {
            Intrinsics.throwNpe();
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            TableEntry tableEntry = arrayList.get(i);
            if (tableEntry.getEntryStartUtcMillis() <= time && time < tableEntry.getEntryEndUtcMillis()) {
                return i;
            }
        }
        return -1;
    }

    public final void filterChannel(String filter) {
        Intrinsics.checkParameterIsNotNull(filter, "filter");
        List<Channel> list = this.mChannels;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((Channel) obj).getGenres().contains(filter)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = arrayList;
        this.mFilteredChannels = arrayList2;
        if ((arrayList2 != null ? arrayList2.size() : 0) == 0) {
            this.mFilteredChannels = this.mChannels;
        }
        notifyChannelsUpdated();
    }
}
