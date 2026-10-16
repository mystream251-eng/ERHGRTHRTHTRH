package com.dotscreen.portal.model;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.tv.TvContract;
import android.net.Uri;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.Lineup;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.PortalApplicationKt;
import com.dotscreen.tv.common.bokit.RightVChannel;
import com.dotscreen.tv.common.bokit.RightVUser;
import com.dotscreen.tv.common.providers.LastChannelsProvider;
import com.dotscreen.tv.common.utils.RxViewModel;
import com.dotscreen.tv.common.utils.TIFCompanionLibraryExtensionKt;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LastChannelsViewModel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001:\u0001\u0011B\u001b\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\u0014\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0007H\u0014R\u000e\u0010\n\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/dotscreen/portal/model/LastChannelsViewModel;", "Lcom/dotscreen/tv/common/utils/RxViewModel;", "", "Lcom/dotscreen/bokit/model/Channel;", "context", "Landroid/content/Context;", "lineupSingle", "Lio/reactivex/Single;", "Lcom/dotscreen/bokit/model/Lineup;", "(Landroid/content/Context;Lio/reactivex/Single;)V", "CHANNELS_COUNT", "", "TAG", "", "getTAG", "()Ljava/lang/String;", "getSingle", "Factory", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class LastChannelsViewModel extends RxViewModel<List<? extends Channel>> {
    private final int CHANNELS_COUNT;
    private final String TAG;
    private final Context context;
    private final Single<Lineup> lineupSingle;

    public LastChannelsViewModel(Context context, Single<Lineup> lineupSingle) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(lineupSingle, "lineupSingle");
        this.context = context;
        this.lineupSingle = lineupSingle;
        this.TAG = EPGViewModel.class.getSimpleName();
        this.CHANNELS_COUNT = 20;
    }

    public final String getTAG() {
        return this.TAG;
    }

    /* JADX INFO: compiled from: LastChannelsViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J'\u0010\u0005\u001a\u0002H\u0006\"\n\b\u0000\u0010\u0006*\u0004\u0018\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00060\tH\u0016¢\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/dotscreen/portal/model/LastChannelsViewModel$Factory;", "Landroidx/lifecycle/ViewModelProvider$NewInstanceFactory;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "create", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Factory extends ViewModelProvider.NewInstanceFactory {
        private final Context context;

        public Factory(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            this.context = context;
        }

        @Override // androidx.lifecycle.ViewModelProvider.NewInstanceFactory, androidx.lifecycle.ViewModelProvider.Factory
        public <T extends ViewModel> T create(Class<T> modelClass) {
            Intrinsics.checkParameterIsNotNull(modelClass, "modelClass");
            Context context = this.context;
            Single<Lineup> currentLineup = PortalApplication.INSTANCE.getSingleton(this.context).getCurrentLineup();
            Intrinsics.checkExpressionValueIsNotNull(currentLineup, "PortalApplication.getSin…on(context).currentLineup");
            return new LastChannelsViewModel(context, currentLineup);
        }
    }

    @Override // com.dotscreen.tv.common.utils.RxViewModel
    protected Single<List<? extends Channel>> getSingle() {
        Single<List<? extends Channel>> map = Observable.create(new ObservableOnSubscribe<T>() { // from class: com.dotscreen.portal.model.LastChannelsViewModel.getSingle.1
            @Override // io.reactivex.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter<Channel> emitter) throws IOException {
                Intrinsics.checkParameterIsNotNull(emitter, "emitter");
                LiveData<PortalApplication.Config> config = PortalApplication.INSTANCE.getSingleton(LastChannelsViewModel.this.context).getConfig();
                Intrinsics.checkExpressionValueIsNotNull(config, "PortalApplication.getSingleton(context).config");
                PortalApplication.Config value = config.getValue();
                if (value != null && value.getUser() != null) {
                    ContentResolver contentResolver = LastChannelsViewModel.this.context.getContentResolver();
                    Uri content_uri = LastChannelsProvider.INSTANCE.getCONTENT_URI();
                    String str = LastChannelsProvider.INSTANCE.getCOLUMN_PROFILE_ID() + "=?";
                    String[] strArr = new String[1];
                    RightVUser user = value.getUser();
                    if (user == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVUser");
                    }
                    strArr[0] = user.getCompassId();
                    Cursor cursorQuery = contentResolver.query(content_uri, null, str, strArr, null);
                    if (cursorQuery != null) {
                        Cursor cursor = cursorQuery;
                        Throwable th = (Throwable) null;
                        try {
                            Cursor cursor2 = cursor;
                            PortalApplicationKt.dumpColumnNames$default(cursor2, null, 1, null);
                            while (cursor2.moveToNext()) {
                                PortalApplicationKt.dumpValues$default(cursor2, null, 1, null);
                                Cursor cursorQuery2 = LastChannelsViewModel.this.context.getContentResolver().query(TvContract.buildChannelUri(cursor2.getLong(1)), com.google.android.media.tv.companionlibrary.model.Channel.PROJECTION, null, null, null);
                                if (cursorQuery2 != null) {
                                    Cursor cursor3 = cursorQuery2;
                                    Throwable th2 = (Throwable) null;
                                    try {
                                        Cursor cursor4 = cursor3;
                                        if (cursor4.moveToFirst()) {
                                            com.google.android.media.tv.companionlibrary.model.Channel tifChannel = com.google.android.media.tv.companionlibrary.model.Channel.fromCursor(cursor4);
                                            Intrinsics.checkExpressionValueIsNotNull(tifChannel, "tifChannel");
                                            Context applicationContext = LastChannelsViewModel.this.context.getApplicationContext();
                                            if (applicationContext == null) {
                                                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                                            }
                                            Channel optimizerChannel = TIFCompanionLibraryExtensionKt.getOptimizerChannel(tifChannel, ((PortalApplication) applicationContext).getBOService());
                                            if (!(optimizerChannel instanceof RightVChannel)) {
                                                optimizerChannel = null;
                                            }
                                            RightVChannel rightVChannel = (RightVChannel) optimizerChannel;
                                            if (rightVChannel != null) {
                                                emitter.onNext(rightVChannel);
                                            }
                                            try {
                                                throw th;
                                            } catch (Throwable th3) {
                                                CloseableKt.closeFinally(cursor, th);
                                                throw th3;
                                            }
                                        }
                                        Unit unit = Unit.INSTANCE;
                                        CloseableKt.closeFinally(cursor3, th2);
                                    } catch (Throwable th4) {
                                        try {
                                            throw th4;
                                        } catch (Throwable th5) {
                                            CloseableKt.closeFinally(cursor3, th4);
                                            throw th5;
                                        }
                                    }
                                }
                            }
                            Unit unit2 = Unit.INSTANCE;
                            CloseableKt.closeFinally(cursor, th);
                        } catch (Throwable th6) {
                            throw th6;
                        }
                    }
                }
                emitter.onComplete();
            }
        }).takeLast(this.CHANNELS_COUNT).toList().map(new Function<T, R>() { // from class: com.dotscreen.portal.model.LastChannelsViewModel.getSingle.2
            @Override // io.reactivex.functions.Function
            public final List<Channel> apply(List<Channel> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return CollectionsKt.reversed(it);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(map, "Observable.create<Channe…   .map { it.reversed() }");
        return map;
    }
}
