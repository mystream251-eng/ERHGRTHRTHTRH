package com.dotscreen.tvinput;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.media.tv.TvContract;
import android.media.tv.TvInputManager;
import android.net.Uri;
import android.os.PowerManager;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.tvprovider.media.tv.TvContractCompat;
import androidx.work.WorkerParameters;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.tv.common.IForegroudStateProvider;
import com.dotscreen.tv.common.IMediaPlayerStateProvider;
import com.dotscreen.tv.common.bokit.ChannelExtraCaster;
import com.dotscreen.tv.common.bokit.RightVChannel;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.dotscreen.tv.common.utils.DeviceInfo;
import com.dotscreen.tv.common.utils.ScanStatusHandler;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.annotations.SchedulerSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: SyncTVChannelsWorker.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001#B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0016\u0010\u0012\u001a\u00020\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0016J\u0016\u0010\u0016\u001a\u00020\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0016J\b\u0010\u0017\u001a\u00020\bH\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0012\u0010\u001c\u001a\u00020\u00192\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\u0018\u0010\u001f\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020\u0019H\u0002R&\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8B@BX\u0082\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006$"}, d2 = {"Lcom/dotscreen/tvinput/TechnicolorSyncTVChannelsWorker;", "Lcom/dotscreen/tvinput/SyncTVChannelsWorker;", "context", "Landroid/content/Context;", "params", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "value", "", "transportTypeSet", "getTransportTypeSet", "()Z", "setTransportTypeSet", "(Z)V", "generateFakeUdpAdress", "", "installDVBChannels", "Lio/reactivex/Completable;", "installIPChannels", "channels", "", "Lcom/dotscreen/bokit/model/Channel;", "installOTTChannels", "isPhaseTwo", "performScan", "", "emitter", "Lio/reactivex/CompletableEmitter;", "scheduleNextScan", NotificationCompat.CATEGORY_ERROR, "", "sendStartScan", "json", "Lorg/json/JSONObject;", "waitForLiveSessionToEnd", "TechnicolorScanFailedError", "tvinput_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class TechnicolorSyncTVChannelsWorker extends SyncTVChannelsWorker {
    private boolean transportTypeSet;

    /* JADX INFO: compiled from: SyncTVChannelsWorker.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/dotscreen/tvinput/TechnicolorSyncTVChannelsWorker$TechnicolorScanFailedError;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "()V", "tvinput_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class TechnicolorScanFailedError extends Exception {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TechnicolorSyncTVChannelsWorker(Context context, WorkerParameters params) {
        super(context, params);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(params, "params");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getTransportTypeSet() {
        return getContext().getSharedPreferences(SyncTVChannelsWorkerKt.TAG, 0).getBoolean("transportTypeSet", false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTransportTypeSet(boolean z) {
        this.transportTypeSet = z;
        getContext().getSharedPreferences(SyncTVChannelsWorkerKt.TAG, 0).edit().putBoolean("transportTypeSet", z).apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendStartScan(final CompletableEmitter emitter, JSONObject json) {
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.dotscreen.tvinput.TechnicolorSyncTVChannelsWorker$sendStartScan$scanStatusReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                Intrinsics.checkParameterIsNotNull(context, "context");
                Intrinsics.checkParameterIsNotNull(intent, "intent");
                int intExtra = intent.getIntExtra("progress", -1);
                int intExtra2 = intent.getIntExtra("nb_channels", -1);
                String stringExtra = intent.getStringExtra(NotificationCompat.CATEGORY_STATUS);
                Log.d(SyncTVChannelsWorkerKt.TAG, "SCAN_STATUS: " + intent);
                Log.d(SyncTVChannelsWorkerKt.TAG, "    progress = " + intExtra);
                Log.d(SyncTVChannelsWorkerKt.TAG, "    nb_channels = " + intExtra2);
                Log.d(SyncTVChannelsWorkerKt.TAG, "    status = " + stringExtra);
                Intent intent2 = new Intent(CommonUtils.ACTION_SCAN_STATUS_CHANGED);
                intent2.putExtra(CommonUtils.BUNDLE_KEY_SCAN_STATUS, CommonUtils.SCAN_STATUS_SCANNED);
                context.sendBroadcast(intent2);
                ScanStatusHandler.INSTANCE.setCurrentScanStatus(context, ScanStatusHandler.ScanState.IN_PROGRESS);
                if (intExtra == 100) {
                    SyncTVChannelsWorkerKt.notifyDVBScanFinished(context);
                    context.unregisterReceiver(this);
                    if (stringExtra != null && stringExtra.hashCode() == -1281977283 && stringExtra.equals("failed")) {
                        emitter.onError(new TechnicolorSyncTVChannelsWorker.TechnicolorScanFailedError());
                        return;
                    }
                    this.this$0.makeAllChannelsBrowsable$tvinput_prodRelease();
                    SyncTVChannelsWorkerKt.saveLastDVBScanTime(context);
                    emitter.onComplete();
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.technicolor.android.dtvscan.SCAN_STATUS");
        getContext().registerReceiver(broadcastReceiver, intentFilter);
        Intent intent = new Intent("com.technicolor.android.dtvscan.action.ACTION_START_SCAN");
        Log.d(SyncTVChannelsWorkerKt.TAG, "sending intent " + intent);
        Log.d(SyncTVChannelsWorkerKt.TAG, "with params " + json.toString(2));
        intent.putExtra("params", json.toString());
        getContext().sendBroadcast(intent);
    }

    @Override // com.dotscreen.tvinput.SyncTVChannelsWorker
    public Completable installDVBChannels() {
        Completable completableCreate = Completable.create(new CompletableOnSubscribe() { // from class: com.dotscreen.tvinput.TechnicolorSyncTVChannelsWorker.installDVBChannels.1
            @Override // io.reactivex.CompletableOnSubscribe
            public final void subscribe(CompletableEmitter emitter) throws JSONException {
                Intrinsics.checkParameterIsNotNull(emitter, "emitter");
                Log.d(SyncTVChannelsWorkerKt.TAG, "installDVBChannels()...");
                Object systemService = TechnicolorSyncTVChannelsWorker.this.getContext().getSystemService("power");
                if (systemService != null) {
                    PowerManager powerManager = (PowerManager) systemService;
                    if (!TechnicolorSyncTVChannelsWorker.this.isPhaseTwo() && !powerManager.isInteractive()) {
                        TechnicolorSyncTVChannelsWorker.this.postponedScanToWakeUp$tvinput_prodRelease();
                        Log.d(SyncTVChannelsWorkerKt.TAG, "Postponed");
                        emitter.onError(new Exception("Postponed to out of standby mode"));
                        return;
                    }
                    Object applicationContext = TechnicolorSyncTVChannelsWorker.this.getContext().getApplicationContext();
                    if (applicationContext != null) {
                        IForegroudStateProvider iForegroudStateProvider = (IForegroudStateProvider) applicationContext;
                        Log.e(SyncTVChannelsWorkerKt.TAG, "Foreground app: " + iForegroudStateProvider.getPortalIsInForeground());
                        Object systemService2 = TechnicolorSyncTVChannelsWorker.this.getContext().getSystemService("tv_input");
                        if (systemService2 != null) {
                            TvInputManager tvInputManager = (TvInputManager) systemService2;
                            Log.d(SyncTVChannelsWorkerKt.TAG, "isSingleSessionActive = " + tvInputManager.isSingleSessionActive());
                            if (iForegroudStateProvider.getPortalIsInForeground() || tvInputManager.isSingleSessionActive() || (TechnicolorSyncTVChannelsWorker.this.isPhaseTwo() && !powerManager.isInteractive())) {
                                if (!(iForegroudStateProvider instanceof IMediaPlayerStateProvider) || !((IMediaPlayerStateProvider) iForegroudStateProvider).isActivePlaybackSession()) {
                                    TechnicolorSyncTVChannelsWorker.this.performScan(emitter);
                                    return;
                                }
                                TechnicolorSyncTVChannelsWorker.this.delayedScan$tvinput_prodRelease();
                                Log.d(SyncTVChannelsWorkerKt.TAG, "Postponed");
                                emitter.onError(new SyncTVChannelsWorker.PlaybackOnGoingException());
                                return;
                            }
                            TechnicolorSyncTVChannelsWorker.this.postponedScanToForegroundState$tvinput_prodRelease();
                            Log.d(SyncTVChannelsWorkerKt.TAG, "Postponed");
                            emitter.onError(new Exception("Postponed to foreground"));
                            return;
                        }
                        throw new TypeCastException("null cannot be cast to non-null type android.media.tv.TvInputManager");
                    }
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.IForegroudStateProvider");
                }
                throw new TypeCastException("null cannot be cast to non-null type android.os.PowerManager");
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(completableCreate, "Completable.create { emi…       }\n        }\n\n    }");
        return completableCreate;
    }

    @Override // com.dotscreen.tvinput.SyncTVChannelsWorker
    protected void scheduleNextScan(Throwable err) {
        if (err instanceof SyncTVChannelsWorker.PlaybackOnGoingException) {
            return;
        }
        super.scheduleNextScan(err);
    }

    private final void waitForLiveSessionToEnd() {
        Context context = getContext();
        Object systemService = context != null ? context.getSystemService("tv_input") : null;
        if (systemService != null) {
            TvInputManager tvInputManager = (TvInputManager) systemService;
            Log.d(SyncTVChannelsWorkerKt.TAG, "isSingleSessionActive = " + tvInputManager.isSingleSessionActive());
            while (tvInputManager.isSingleSessionActive()) {
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.media.tv.TvInputManager");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void performScan(CompletableEmitter emitter) throws JSONException {
        getContext().sendBroadcast(new Intent(CommonUtils.ACTION_DVB_SCAN_STARTED));
        waitForLiveSessionToEnd();
        if (getTransportTypeSet()) {
            Log.d(SyncTVChannelsWorkerKt.TAG, "\"technicolor.intent.action.TRANSPORT_TYPE\" broadcast already sent");
        } else {
            try {
                Intent intent = new Intent("technicolor.intent.action.TRANSPORT_TYPE");
                intent.setPackage("com.technicolor.stream.transport");
                intent.putExtra("transport_type", "dvbc");
                Log.d(SyncTVChannelsWorkerKt.TAG, "sending broadcast: " + intent);
                getContext().sendBroadcast(intent);
                setTransportTypeSet(true);
            } catch (Exception e) {
                Log.w(SyncTVChannelsWorkerKt.TAG, "Exception", e);
            }
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", "dvb-c");
        jSONObject.put("country", "mx");
        jSONObject.put("frequency", 411000000);
        jSONObject.put("qam-mode", "qam_256");
        jSONObject.put("symbol-rate", 5217000);
        jSONObject.put("qam-annex", "a");
        SyncTVChannelsWorkerKt.scheduleForceSyncCheck(getContext());
        sendStartScan(emitter, jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isPhaseTwo() {
        int i;
        List listSplit$default = StringsKt.split$default((CharSequence) DeviceInfo.INSTANCE.getFirmwareVersionName(), new char[]{'.'}, false, 0, 6, (Object) null);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSplit$default, 10));
        Iterator it = listSplit$default.iterator();
        while (it.hasNext()) {
            try {
                i = Integer.parseInt((String) it.next());
            } catch (NumberFormatException unused) {
                i = 0;
            }
            arrayList.add(Integer.valueOf(i));
        }
        ArrayList arrayList2 = arrayList;
        int iIntValue = ((Number) arrayList2.get(0)).intValue();
        int iIntValue2 = ((Number) arrayList2.get(1)).intValue();
        Log.d(SyncTVChannelsWorkerKt.TAG, "TCH: " + iIntValue + '.' + iIntValue2);
        return iIntValue >= 1 && iIntValue2 >= 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String generateFakeUdpAdress() {
        return "udp://" + Random.INSTANCE.nextInt(256) + '.' + Random.INSTANCE.nextInt(256) + '.' + Random.INSTANCE.nextInt(256) + '.' + Random.INSTANCE.nextInt(256) + ':' + Random.INSTANCE.nextInt(10000);
    }

    @Override // com.dotscreen.tvinput.SyncTVChannelsWorker
    public Completable installIPChannels(final List<? extends Channel> channels) {
        Intrinsics.checkParameterIsNotNull(channels, "channels");
        Completable completableConcatWith = Completable.create(new CompletableOnSubscribe() { // from class: com.dotscreen.tvinput.TechnicolorSyncTVChannelsWorker.installIPChannels.1
            @Override // io.reactivex.CompletableOnSubscribe
            public final void subscribe(CompletableEmitter emitter) throws JSONException {
                boolean z;
                Intrinsics.checkParameterIsNotNull(emitter, "emitter");
                Log.d(SyncTVChannelsWorkerKt.TAG, "installIPChannels...");
                List list = channels;
                ArrayList<Channel> arrayList = new ArrayList();
                for (Object obj : list) {
                    if (TechnicolorSyncTVChannelsWorker.this.isIPChannel((Channel) obj)) {
                        arrayList.add(obj);
                    }
                }
                for (Channel channel : arrayList) {
                    Log.d(SyncTVChannelsWorkerKt.TAG, "   " + channel);
                }
                List list2 = channels;
                if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                    Iterator it = list2.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (TechnicolorSyncTVChannelsWorker.this.isIPChannel((Channel) it.next())) {
                                z = false;
                                break;
                            }
                        } else {
                            z = true;
                            break;
                        }
                    }
                } else {
                    z = true;
                    break;
                }
                if (!z) {
                    if (TechnicolorSyncTVChannelsWorker.this.getTransportTypeSet()) {
                        Log.d(SyncTVChannelsWorkerKt.TAG, "\"technicolor.intent.action.TRANSPORT_TYPE\" broadcast already sent");
                    } else {
                        try {
                            Intent intent = new Intent("technicolor.intent.action.TRANSPORT_TYPE");
                            intent.setPackage("com.technicolor.stream.transport");
                            intent.putExtra("transport_type", "iptv");
                            Log.d(SyncTVChannelsWorkerKt.TAG, "sending broadcast: " + intent);
                            TechnicolorSyncTVChannelsWorker.this.getContext().sendBroadcast(intent);
                            TechnicolorSyncTVChannelsWorker.this.setTransportTypeSet(true);
                        } catch (Exception e) {
                            Log.w(SyncTVChannelsWorkerKt.TAG, "Exception", e);
                        }
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("type", "json");
                    LinkedHashSet linkedHashSet = new LinkedHashSet();
                    JSONArray jSONArray = new JSONArray();
                    for (Channel channel2 : channels) {
                        if (TechnicolorSyncTVChannelsWorker.this.isIPChannel(channel2)) {
                            if (channel2 != null) {
                                RightVChannel rightVChannel = (RightVChannel) channel2;
                                JSONObject jSONObject2 = new JSONObject();
                                ChannelExtraCaster channelExtraCaster = rightVChannel.getExtras().getCasters().get("multicast");
                                String src = channelExtraCaster != null ? channelExtraCaster.getSrc() : null;
                                if (!CommonUtils.isProdConfig()) {
                                    if (src == null) {
                                        src = TechnicolorSyncTVChannelsWorker.this.generateFakeUdpAdress();
                                    }
                                    while (CollectionsKt.contains(linkedHashSet, src)) {
                                        src = TechnicolorSyncTVChannelsWorker.this.generateFakeUdpAdress();
                                    }
                                    if (src == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    linkedHashSet.add(src);
                                }
                                jSONObject2.put("uri", src);
                                jSONObject2.put(TvContractCompat.Channels.COLUMN_DISPLAY_NAME, rightVChannel.getTitle());
                                jSONObject2.put(TvContractCompat.Channels.COLUMN_DISPLAY_NUMBER, String.valueOf(rightVChannel.getZappingNumber()));
                                jSONObject2.put("service-type", "TV");
                                jSONObject2.put("scrambled", true);
                                jSONObject2.put("browsable", 1);
                                jSONArray.put(jSONObject2);
                            } else {
                                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVChannel");
                            }
                        }
                    }
                    jSONObject.put("services", jSONArray);
                    TechnicolorSyncTVChannelsWorker.this.sendStartScan(emitter, jSONObject);
                    return;
                }
                emitter.onComplete();
            }
        }).concatWith(Completable.create(new CompletableOnSubscribe() { // from class: com.dotscreen.tvinput.TechnicolorSyncTVChannelsWorker.installIPChannels.2
            @Override // io.reactivex.CompletableOnSubscribe
            public final void subscribe(CompletableEmitter emitter) throws IOException {
                Intrinsics.checkParameterIsNotNull(emitter, "emitter");
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                String[] strArr = {"_id", TvContractCompat.Channels.COLUMN_DISPLAY_NUMBER};
                ContentResolver contentResolver = TechnicolorSyncTVChannelsWorker.this.getContext().getContentResolver();
                Cursor cursorQuery = contentResolver.query(TvContract.Channels.CONTENT_URI, strArr, "package_name = \"com.technicolor.android.dtvinput\"", null, null);
                if (cursorQuery != null) {
                    Cursor cursor = cursorQuery;
                    Throwable th = (Throwable) null;
                    try {
                        Cursor cursor2 = cursor;
                        while (cursor2.moveToNext()) {
                            long j = cursor2.getLong(0);
                            String displayNumber = cursor2.getString(1);
                            Intrinsics.checkExpressionValueIsNotNull(displayNumber, "displayNumber");
                            linkedHashMap.put(displayNumber, Long.valueOf(j));
                        }
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(cursor, th);
                    } catch (Throwable th2) {
                        try {
                            throw th2;
                        } catch (Throwable th3) {
                            CloseableKt.closeFinally(cursor, th2);
                            throw th3;
                        }
                    }
                }
                for (Channel channel : channels) {
                    Long l = (Long) linkedHashMap.get(String.valueOf(channel.getZappingNumber()));
                    if (l != null) {
                        Uri uriBuildChannelUri = TvContract.buildChannelUri(l.longValue());
                        try {
                            Cursor cursorQuery2 = contentResolver.query(uriBuildChannelUri, new String[]{"internal_provider_data"}, null, null, null);
                            if (cursorQuery2 != null) {
                                Cursor cursor3 = cursorQuery2;
                                Throwable th4 = (Throwable) null;
                                try {
                                    Cursor cursor4 = cursor3;
                                    if (cursor4.moveToNext()) {
                                        byte[] data = cursor4.getBlob(0);
                                        try {
                                            JSONObject jSONObject = new JSONObject();
                                            jSONObject.put(CommonUtils.KEY_OPTIMIZER_CHANNEL_ID, channel.getId());
                                            if (channel != null) {
                                                jSONObject.put(CommonUtils.KEY_RIGHTV_EXTERNAL_CHANNEL_ID, ((RightVChannel) channel).getExtras().getChannelExternalId());
                                                Intrinsics.checkExpressionValueIsNotNull(data, "data");
                                                JSONObject jSONObject2 = new JSONObject(new String(data, Charsets.UTF_8));
                                                jSONObject2.put(SchedulerSupport.CUSTOM, jSONObject);
                                                ContentValues contentValues = new ContentValues();
                                                contentValues.put("internal_provider_data", jSONObject2.toString());
                                                contentResolver.update(uriBuildChannelUri, contentValues, null, null);
                                                linkedHashMap.remove(String.valueOf(channel.getZappingNumber()));
                                            } else {
                                                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVChannel");
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        e.printStackTrace();
                                        Unit unit2 = Unit.INSTANCE;
                                    }
                                    Unit unit3 = Unit.INSTANCE;
                                    CloseableKt.closeFinally(cursor3, th4);
                                } catch (Throwable th5) {
                                    try {
                                        throw th5;
                                    } catch (Throwable th6) {
                                        CloseableKt.closeFinally(cursor3, th5);
                                        throw th6;
                                    }
                                }
                            } else {
                                continue;
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            Unit unit4 = Unit.INSTANCE;
                        }
                    }
                }
                emitter.onComplete();
            }
        }));
        Intrinsics.checkExpressionValueIsNotNull(completableConcatWith, "Completable.create { emi…ete()\n            }\n    )");
        return completableConcatWith;
    }

    @Override // com.dotscreen.tvinput.SyncTVChannelsWorker
    public Completable installOTTChannels(List<? extends Channel> channels) {
        Intrinsics.checkParameterIsNotNull(channels, "channels");
        Log.d(SyncTVChannelsWorkerKt.TAG, "installOTTChannels (TCH override)...");
        if (!channels.isEmpty()) {
            if (getTransportTypeSet()) {
                Log.d(SyncTVChannelsWorkerKt.TAG, "\"technicolor.intent.action.TRANSPORT_TYPE\" broadcast already sent");
            } else {
                try {
                    Intent intent = new Intent("technicolor.intent.action.TRANSPORT_TYPE");
                    intent.setPackage("com.technicolor.stream.transport");
                    intent.putExtra("transport_type", "iptv");
                    Log.d(SyncTVChannelsWorkerKt.TAG, "sending broadcast: " + intent);
                    getContext().sendBroadcast(intent);
                    setTransportTypeSet(true);
                } catch (Exception e) {
                    Log.w(SyncTVChannelsWorkerKt.TAG, "Exception", e);
                }
            }
        }
        return super.installOTTChannels(channels);
    }
}
