package com.dotscreen.portal.model;

import android.content.Context;
import android.database.Cursor;
import android.media.tv.TvContract;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.MediaMetadata;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.services.BOService;
import com.dotscreen.bokit.services.LiveTVService;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.tv.common.bokit.ChannelExtras;
import com.dotscreen.tv.common.bokit.RightVChannel;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.google.android.media.tv.companionlibrary.model.Program;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: EPGViewModel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001 B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J)\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0018\u00010\u0011R\u00020\u00010\u0010H\u0010¢\u0006\u0002\b\u0012J)\u0010\u0013\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0018\u00010\u0011R\u00020\u00010\u0010H\u0010¢\u0006\u0002\b\u0014J*\u0010\u0015\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0017\u0018\u00010\u00162\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\tH\u0002J\u000e\u0010\u001b\u001a\u0004\u0018\u00010\u001c*\u00020\u001dH\u0002J\u000e\u0010\u001e\u001a\u0004\u0018\u00010\u001f*\u00020\u0017H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/dotscreen/portal/model/TIFEPGViewModel;", "Lcom/dotscreen/portal/model/EPGViewModel;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "boService", "Lcom/dotscreen/bokit/services/BOService;", "channelsMap", "", "", "Lcom/dotscreen/bokit/model/Channel;", "loadAirings", "", "time", "", "data", "Landroidx/lifecycle/MutableLiveData;", "Lcom/dotscreen/portal/model/EPGViewModel$Data;", "loadAirings$MegaCable_Portal_0_75_1_396__prodRelease", "loadChannels", "loadChannels$MegaCable_Portal_0_75_1_396__prodRelease", "queryPrograms", "", "Lcom/google/android/media/tv/companionlibrary/model/Program;", "channelId", "startTimeMs", "endTimeMs", "toRightVChannel", "Lcom/dotscreen/tv/common/bokit/RightVChannel;", "Lcom/google/android/media/tv/companionlibrary/model/Channel;", "toTVAiring", "Lcom/dotscreen/bokit/model/TVAiring;", "Factory", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class TIFEPGViewModel extends EPGViewModel {
    private final BOService boService;
    private Map<Long, ? extends Channel> channelsMap;
    private final Context context;

    public TIFEPGViewModel(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        this.boService = ((PortalApplication) applicationContext).getBOService();
    }

    /* JADX INFO: compiled from: EPGViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J'\u0010\u0005\u001a\u0002H\u0006\"\n\b\u0000\u0010\u0006*\u0004\u0018\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00060\tH\u0016¢\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/dotscreen/portal/model/TIFEPGViewModel$Factory;", "Landroidx/lifecycle/ViewModelProvider$NewInstanceFactory;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "create", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Factory extends ViewModelProvider.NewInstanceFactory {
        private final Context context;

        public Factory(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            this.context = context;
        }

        @Override // androidx.lifecycle.ViewModelProvider.NewInstanceFactory, androidx.lifecycle.ViewModelProvider.Factory
        public <T extends ViewModel> T create(Class<T> modelClass) {
            Intrinsics.checkParameterIsNotNull(modelClass, "modelClass");
            return new TIFEPGViewModel(this.context);
        }
    }

    private final List<Program> queryPrograms(long channelId, long startTimeMs, long endTimeMs) throws IOException {
        Cursor cursorQuery = this.context.getContentResolver().query(TvContract.buildProgramsUriForChannel(channelId, startTimeMs, endTimeMs), Program.PROJECTION, null, null, "start_time_utc_millis");
        Throwable th = (Throwable) null;
        try {
            Cursor cursor = cursorQuery;
            if (cursor == null) {
                List<Program> listEmptyList = CollectionsKt.emptyList();
                CloseableKt.closeFinally(cursorQuery, th);
                return listEmptyList;
            }
            ArrayList arrayList = new ArrayList();
            while (cursor.moveToNext()) {
                Program programFromCursor = Program.fromCursor(cursor);
                Intrinsics.checkExpressionValueIsNotNull(programFromCursor, "com.google.android.media…del.Program.fromCursor(c)");
                arrayList.add(programFromCursor);
            }
            CloseableKt.closeFinally(cursorQuery, th);
            return arrayList;
        } catch (Throwable th2) {
            try {
                throw th2;
            } catch (Throwable th3) {
                CloseableKt.closeFinally(cursorQuery, th2);
                throw th3;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final RightVChannel toRightVChannel(com.google.android.media.tv.companionlibrary.model.Channel channel) {
        if (!CommonUtils.isSupportedTvInputId(this.context, channel.getInputId())) {
            return null;
        }
        LiveTVService liveTVService = this.boService.getLiveTVService();
        String strValueOf = String.valueOf(channel.getId());
        String displayName = channel.getDisplayName();
        Intrinsics.checkExpressionValueIsNotNull(displayName, "displayName");
        return new RightVChannel(this.context, new Channel(liveTVService, strValueOf, "", displayName, null, null, null, Integer.parseInt(channel.getDisplayNumber()), null, null, false, null, 3952, null), this.boService, "", new ChannelExtras("", "", true, MapsKt.emptyMap(), true));
    }

    private final TVAiring toTVAiring(Program program) {
        Channel channel;
        Map<Long, ? extends Channel> map = this.channelsMap;
        if (map == null || (channel = map.get(Long.valueOf(program.getChannelId()))) == null) {
            return null;
        }
        BOService bOService = this.boService;
        String strValueOf = String.valueOf(program.getId());
        String title = program.getTitle();
        Intrinsics.checkExpressionValueIsNotNull(title, "title");
        return new TVAiring(bOService, strValueOf, title, program.getLongDescription(), null, null, new MediaMetadata(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 65535, null), new Date(program.getStartTimeUtcMillis()), new Date(program.getEndTimeUtcMillis()), channel, null, false, false, false, 15408, null);
    }

    @Override // com.dotscreen.portal.model.EPGViewModel
    public void loadChannels$MegaCable_Portal_0_75_1_396__prodRelease(int time, MutableLiveData<EPGViewModel.Data> data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new TIFEPGViewModel$loadChannels$1(this, time, data, null), 2, null);
    }

    @Override // com.dotscreen.portal.model.EPGViewModel
    public void loadAirings$MegaCable_Portal_0_75_1_396__prodRelease(int time, MutableLiveData<EPGViewModel.Data> data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new TIFEPGViewModel$loadAirings$1(this, data, null), 2, null);
    }
}
