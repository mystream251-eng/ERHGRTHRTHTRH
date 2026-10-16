package com.dotscreen.tvinput;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.tv.TvContract;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.Lineup;
import com.dotscreen.bokit.services.BOService;
import com.dotscreen.bokit.services.LiveTVService;
import com.dotscreen.farkit.updater.UpdateManagerKt;
import com.dotscreen.tv.common.bokit.MegacableLiveTVService;
import com.dotscreen.tv.common.bokit.RightVChannel;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.dotscreen.tv.common.utils.ScanStatusHandler;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SyncTVChannelsWorker.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0003\n\u0002\b\u0002\b'\u0018\u00002\u00020\u0001:\u0001$B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\r\u0010\t\u001a\u00020\nH\u0000¢\u0006\u0002\b\u000bJ\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH&J\u0016\u0010\u0010\u001a\u00020\u000f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H&J\u0016\u0010\u0014\u001a\u00020\u000f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0016J\u0016\u0010\u0015\u001a\u00020\u000f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0016J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0013H\u0004J\u0010\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0013H\u0004J\u0010\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0013H\u0004J\r\u0010\u001b\u001a\u00020\nH\u0000¢\u0006\u0002\b\u001cJ\r\u0010\u001d\u001a\u00020\nH\u0000¢\u0006\u0002\b\u001eJ\r\u0010\u001f\u001a\u00020\nH\u0000¢\u0006\u0002\b J\u0012\u0010!\u001a\u00020\n2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0014R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006%"}, d2 = {"Lcom/dotscreen/tvinput/SyncTVChannelsWorker;", "Landroidx/work/Worker;", "context", "Landroid/content/Context;", "params", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "getContext", "()Landroid/content/Context;", "delayedScan", "", "delayedScan$tvinput_prodRelease", "doWork", "Landroidx/work/ListenableWorker$Result;", "installDVBChannels", "Lio/reactivex/Completable;", "installIPChannels", "channels", "", "Lcom/dotscreen/bokit/model/Channel;", "installOTTChannels", "installOtherChannels", "isIPChannel", "", "channel", "isInteractiveChannel", "isOTTChannel", "makeAllChannelsBrowsable", "makeAllChannelsBrowsable$tvinput_prodRelease", "postponedScanToForegroundState", "postponedScanToForegroundState$tvinput_prodRelease", "postponedScanToWakeUp", "postponedScanToWakeUp$tvinput_prodRelease", "scheduleNextScan", NotificationCompat.CATEGORY_ERROR, "", "PlaybackOnGoingException", "tvinput_prodRelease"}, k = 1, mv = {1, 1, 16})
public abstract class SyncTVChannelsWorker extends Worker {
    private final Context context;

    public abstract Completable installDVBChannels();

    public abstract Completable installIPChannels(List<? extends Channel> channels);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SyncTVChannelsWorker(Context context, WorkerParameters params) {
        super(context, params);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(params, "params");
        this.context = context;
    }

    protected final Context getContext() {
        return this.context;
    }

    @Override // androidx.work.Worker
    public ListenableWorker.Result doWork() {
        Log.e(SyncTVChannelsWorkerKt.TAG, "=========== doWork()... ==========");
        try {
            Intent intent = new Intent(CommonUtils.ACTION_SCAN_STATUS_CHANGED);
            intent.putExtra(CommonUtils.BUNDLE_KEY_SCAN_STATUS, CommonUtils.SCAN_STATUS_STARTED);
            this.context.sendBroadcast(intent);
            final BOService bOService = CommonUtils.getBOService(getApplicationContext());
            LiveTVService liveTVService = bOService.getLiveTVService();
            if (liveTVService == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableLiveTVService");
            }
            Throwable thBlockingGet = ((MegacableLiveTVService) liveTVService).getModelExternalId().flatMapCompletable(new Function<String, CompletableSource>() { // from class: com.dotscreen.tvinput.SyncTVChannelsWorker$doWork$error$1
                @Override // io.reactivex.functions.Function
                public final Completable apply(final String modelExternalId) {
                    Completable completableComplete;
                    Intrinsics.checkParameterIsNotNull(modelExternalId, "modelExternalId");
                    Log.v(SyncTVChannelsWorkerKt.TAG, "modelExternalId = " + modelExternalId);
                    if (modelExternalId.hashCode() == 1001905799 && modelExternalId.equals("DVB-C STB")) {
                        if (SyncTVChannelsWorkerKt.isDVBCableIsConnected(this.this$0.getContext())) {
                            ScanStatusHandler.INSTANCE.setCurrentScanStatus(this.this$0.getContext(), ScanStatusHandler.ScanState.STARTED);
                            completableComplete = this.this$0.installDVBChannels().timeout(90L, TimeUnit.SECONDS).doOnComplete(new Action() { // from class: com.dotscreen.tvinput.SyncTVChannelsWorker$doWork$error$1.1
                                @Override // io.reactivex.functions.Action
                                public final void run() {
                                    Context context = SyncTVChannelsWorker$doWork$error$1.this.this$0.getContext();
                                    BOService boService = bOService;
                                    Intrinsics.checkExpressionValueIsNotNull(boService, "boService");
                                    SyncTVChannelsWorkerKt.insertDVBChannelLogosInTIF(context, boService);
                                }
                            });
                        } else {
                            completableComplete = Completable.error(new CableIsNotConnectedException(null, 1, null));
                        }
                    } else {
                        completableComplete = Completable.complete();
                    }
                    return completableComplete.andThen(CommonUtils.getCurrentLineup(bOService).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.tvinput.SyncTVChannelsWorker$doWork$error$1.2
                        @Override // io.reactivex.functions.Function
                        public final Observable<Channel> apply(Lineup it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            return it.getItems();
                        }
                    }).toList().flatMapCompletable(new Function<List<Channel>, CompletableSource>() { // from class: com.dotscreen.tvinput.SyncTVChannelsWorker$doWork$error$1.3
                        @Override // io.reactivex.functions.Function
                        public final CompletableSource apply(List<Channel> it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            String str = modelExternalId;
                            int iHashCode = str.hashCode();
                            if (iHashCode != -1152706742) {
                                if (iHashCode == 1001905799 && str.equals("DVB-C STB")) {
                                    return SyncTVChannelsWorker$doWork$error$1.this.this$0.installOtherChannels(it);
                                }
                            } else if (str.equals("IPTV STB")) {
                                return Completable.concatArray(SyncTVChannelsWorker$doWork$error$1.this.this$0.installIPChannels(it), SyncTVChannelsWorker$doWork$error$1.this.this$0.installOTTChannels(it), SyncTVChannelsWorker$doWork$error$1.this.this$0.installOtherChannels(it));
                            }
                            return Completable.concatArray(SyncTVChannelsWorker$doWork$error$1.this.this$0.installOTTChannels(it), SyncTVChannelsWorker$doWork$error$1.this.this$0.installOtherChannels(it));
                        }
                    }));
                }
            }).blockingGet();
            scheduleNextScan(thBlockingGet);
            if (thBlockingGet != null) {
                Log.w(SyncTVChannelsWorkerKt.TAG, "... error: " + thBlockingGet);
                if (thBlockingGet instanceof CableIsNotConnectedException) {
                    SyncTVChannelsWorkerKt.scheduleForceSyncCheck(this.context);
                }
                ScanStatusHandler.INSTANCE.setCurrentScanStatus(this.context, ScanStatusHandler.ScanState.FINISHED);
                Intent intent2 = new Intent(CommonUtils.ACTION_SCAN_STATUS_CHANGED);
                intent2.putExtra(CommonUtils.BUNDLE_KEY_SCAN_STATUS, CommonUtils.SCAN_STATUS_ERROR);
                intent2.putExtra(SyncTVChannelsWorkerKt.getBUNDLE_KEY_SCAN_ERROR_REASON(), thBlockingGet.getMessage());
                this.context.sendBroadcast(intent2);
                ListenableWorker.Result resultFailure = ListenableWorker.Result.failure();
                Intrinsics.checkExpressionValueIsNotNull(resultFailure, "Result.failure()");
                return resultFailure;
            }
            Log.d(SyncTVChannelsWorkerKt.TAG, "... work done !");
            Intent intent3 = new Intent(CommonUtils.ACTION_SCAN_STATUS_CHANGED);
            intent3.putExtra(CommonUtils.BUNDLE_KEY_SCAN_STATUS, CommonUtils.SCAN_STATUS_FINISHED);
            this.context.sendBroadcast(intent3);
            ListenableWorker.Result resultSuccess = ListenableWorker.Result.success();
            Intrinsics.checkExpressionValueIsNotNull(resultSuccess, "Result.success()");
            return resultSuccess;
        } catch (Throwable th) {
            Log.w(SyncTVChannelsWorkerKt.TAG, "Error", th);
            Intent intent4 = new Intent(CommonUtils.ACTION_SCAN_STATUS_CHANGED);
            intent4.putExtra(CommonUtils.BUNDLE_KEY_SCAN_STATUS, CommonUtils.SCAN_STATUS_FINISHED);
            intent4.putExtra(SyncTVChannelsWorkerKt.getBUNDLE_KEY_SCAN_ERROR_REASON(), th.getMessage());
            this.context.sendBroadcast(intent4);
            SyncTVChannelsWorkerKt.scheduleSyncingTVChannels$default(this.context, SyncTVChannelsWorkerKt.getRESCAN_DELAY(), true, false, 8, null);
            ListenableWorker.Result resultFailure2 = ListenableWorker.Result.failure();
            Intrinsics.checkExpressionValueIsNotNull(resultFailure2, "Result.failure()");
            return resultFailure2;
        }
    }

    protected void scheduleNextScan(Throwable err) {
        SyncTVChannelsWorkerKt.scheduleSyncingTVChannels$default(this.context, SyncTVChannelsWorkerKt.getRESCAN_DELAY(), true, false, 8, null);
    }

    protected final boolean isIPChannel(Channel channel) {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        return (channel instanceof RightVChannel) && ((RightVChannel) channel).getExtras().getCasters().containsKey("multicast");
    }

    protected final boolean isOTTChannel(Channel channel) {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        if (channel instanceof RightVChannel) {
            RightVChannel rightVChannel = (RightVChannel) channel;
            if (!rightVChannel.getExtras().getCasters().containsKey("multicast") && !rightVChannel.getExtras().getCasters().containsKey("dvb") && (rightVChannel.getExtras().getCasters().containsKey("dash") || rightVChannel.getExtras().getCasters().containsKey("multicast-abr"))) {
                return true;
            }
        }
        return false;
    }

    protected final boolean isInteractiveChannel(Channel channel) {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        return (channel instanceof RightVChannel) && ((RightVChannel) channel).getIsInteracive();
    }

    public Completable installOTTChannels(final List<? extends Channel> channels) {
        Intrinsics.checkParameterIsNotNull(channels, "channels");
        Completable completableCreate = Completable.create(new CompletableOnSubscribe() { // from class: com.dotscreen.tvinput.SyncTVChannelsWorker.installOTTChannels.1
            @Override // io.reactivex.CompletableOnSubscribe
            public final void subscribe(CompletableEmitter emitter) throws IOException {
                Intrinsics.checkParameterIsNotNull(emitter, "emitter");
                Log.d(SyncTVChannelsWorkerKt.TAG, "installOTTChannels...");
                List list = channels;
                ArrayList<Channel> arrayList = new ArrayList();
                for (Object obj : list) {
                    if (SyncTVChannelsWorker.this.isOTTChannel((Channel) obj)) {
                        arrayList.add(obj);
                    }
                }
                for (Channel channel : arrayList) {
                    Log.d(SyncTVChannelsWorkerKt.TAG, "   " + channel);
                }
                Context context = SyncTVChannelsWorker.this.getContext();
                String oTTLiveInputId = CommonUtils.getOTTLiveInputId();
                Intrinsics.checkExpressionValueIsNotNull(oTTLiveInputId, "CommonUtils.getOTTLiveInputId()");
                List list2 = channels;
                ArrayList arrayList2 = new ArrayList();
                for (Object obj2 : list2) {
                    if (SyncTVChannelsWorker.this.isOTTChannel((Channel) obj2)) {
                        arrayList2.add(obj2);
                    }
                }
                ArrayList<Channel> arrayList3 = arrayList2;
                ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
                for (Channel channel2 : arrayList3) {
                    if (channel2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVChannel");
                    }
                    arrayList4.add(SyncTVChannelsWorkerKt.rightVChannelToTIFChannel((RightVChannel) channel2, SyncTVChannelsWorker.this.getContext()));
                }
                SyncTVChannelsWorkerKt.updateChannels(context, oTTLiveInputId, arrayList4, new Function1<Long, Unit>() { // from class: com.dotscreen.tvinput.SyncTVChannelsWorker.installOTTChannels.1.5
                    public final void invoke(long j) {
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                        invoke(l.longValue());
                        return Unit.INSTANCE;
                    }
                });
                SyncTVChannelsWorker.this.makeAllChannelsBrowsable$tvinput_prodRelease();
                Log.d(SyncTVChannelsWorkerKt.TAG, "installOTTChannels done");
                emitter.onComplete();
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(completableCreate, "Completable.create { emi…mitter.onComplete()\n    }");
        return completableCreate;
    }

    public Completable installOtherChannels(final List<? extends Channel> channels) {
        Intrinsics.checkParameterIsNotNull(channels, "channels");
        Completable completableCreate = Completable.create(new CompletableOnSubscribe() { // from class: com.dotscreen.tvinput.SyncTVChannelsWorker.installOtherChannels.1
            @Override // io.reactivex.CompletableOnSubscribe
            public final void subscribe(CompletableEmitter emitter) throws IOException {
                Intrinsics.checkParameterIsNotNull(emitter, "emitter");
                Log.d(SyncTVChannelsWorkerKt.TAG, "installOtherChannels...");
                List list = channels;
                ArrayList<Channel> arrayList = new ArrayList();
                for (Object obj : list) {
                    if (SyncTVChannelsWorker.this.isInteractiveChannel((Channel) obj)) {
                        arrayList.add(obj);
                    }
                }
                for (Channel channel : arrayList) {
                    Log.d(SyncTVChannelsWorkerKt.TAG, "   " + channel);
                }
                Context context = SyncTVChannelsWorker.this.getContext();
                String interctiveLiveInputId = CommonUtils.getInterctiveLiveInputId();
                Intrinsics.checkExpressionValueIsNotNull(interctiveLiveInputId, "CommonUtils.getInterctiveLiveInputId()");
                List list2 = channels;
                ArrayList arrayList2 = new ArrayList();
                for (Object obj2 : list2) {
                    if (SyncTVChannelsWorker.this.isInteractiveChannel((Channel) obj2)) {
                        arrayList2.add(obj2);
                    }
                }
                ArrayList<Channel> arrayList3 = arrayList2;
                ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
                for (Channel channel2 : arrayList3) {
                    if (channel2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVChannel");
                    }
                    arrayList4.add(SyncTVChannelsWorkerKt.rightVChannelToTIFChannel((RightVChannel) channel2, SyncTVChannelsWorker.this.getContext()));
                }
                SyncTVChannelsWorkerKt.updateChannels(context, interctiveLiveInputId, arrayList4, new Function1<Long, Unit>() { // from class: com.dotscreen.tvinput.SyncTVChannelsWorker.installOtherChannels.1.5
                    public final void invoke(long j) {
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                        invoke(l.longValue());
                        return Unit.INSTANCE;
                    }
                });
                SyncTVChannelsWorker.this.makeAllChannelsBrowsable$tvinput_prodRelease();
                Log.d(SyncTVChannelsWorkerKt.TAG, "installOtherChannels done");
                emitter.onComplete();
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(completableCreate, "Completable.create { emi…mitter.onComplete()\n    }");
        return completableCreate;
    }

    public final void makeAllChannelsBrowsable$tvinput_prodRelease() {
        try {
            ContentResolver contentResolver = this.context.getContentResolver();
            ContentValues contentValues = new ContentValues();
            contentValues.put("browsable", (Integer) 1);
            int iUpdate = contentResolver.update(TvContract.Channels.CONTENT_URI, contentValues, null, null);
            Log.v(SyncTVChannelsWorkerKt.TAG, "Updated channel count: " + iUpdate);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void postponedScanToWakeUp$tvinput_prodRelease() {
        try {
            this.context.getApplicationContext().unregisterReceiver(SyncTVChannelsWorkerKt.getWakeUpReceiver());
        } catch (Exception unused) {
            Log.v(SyncTVChannelsWorkerKt.TAG, "No listener register for wakeup");
        }
        Intent intentRegisterReceiver = this.context.getApplicationContext().registerReceiver(SyncTVChannelsWorkerKt.getWakeUpReceiver(), new IntentFilter("android.intent.action.SCREEN_ON"));
        Log.d(SyncTVChannelsWorkerKt.TAG, "Postponed - to wakeup : " + intentRegisterReceiver);
    }

    public final void postponedScanToForegroundState$tvinput_prodRelease() {
        try {
            this.context.getApplicationContext().unregisterReceiver(SyncTVChannelsWorkerKt.getMegacableForegroundReceiver());
        } catch (Exception unused) {
            Log.v(SyncTVChannelsWorkerKt.TAG, "No listener register for foreground state");
        }
        Intent intentRegisterReceiver = this.context.getApplicationContext().registerReceiver(SyncTVChannelsWorkerKt.getMegacableForegroundReceiver(), new IntentFilter(UpdateManagerKt.ACTION_APPLICATION_GOES_FOREGROUND));
        Log.d(SyncTVChannelsWorkerKt.TAG, "Postponed - to foreground(+1 minute) : " + intentRegisterReceiver);
    }

    public final void delayedScan$tvinput_prodRelease() {
        SyncTVChannelsWorkerKt.scheduleSyncingTVChannels(this.context, SyncTVChannelsWorkerKt.POSTPONE_DELAY_ON_VOD_PLAYBACK, true, true);
        Log.d(SyncTVChannelsWorkerKt.TAG, "Delay scan - because of on going playback (+" + SyncTVChannelsWorkerKt.POSTPONE_DELAY_ON_VOD_PLAYBACK + " minute)");
    }

    /* JADX INFO: compiled from: SyncTVChannelsWorker.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/dotscreen/tvinput/SyncTVChannelsWorker$PlaybackOnGoingException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "()V", "tvinput_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class PlaybackOnGoingException extends Exception {
        public PlaybackOnGoingException() {
            super("on going playback session");
        }
    }
}
