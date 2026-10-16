package com.dotscreen.tvinput;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Point;
import android.media.tv.TvContract;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import androidx.tvprovider.media.tv.TvContractCompat;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import com.dotscreen.bokit.model.Artwork;
import com.dotscreen.bokit.services.BOService;
import com.dotscreen.bokit.services.LiveTVService;
import com.dotscreen.farkit.updater.UpdateManagerKt;
import com.dotscreen.tv.common.bokit.ChannelExtraCaster;
import com.dotscreen.tv.common.bokit.ChannelExtras;
import com.dotscreen.tv.common.bokit.RightVChannel;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.dotscreen.tv.common.utils.DeviceInfo;
import com.dotscreen.tv.common.utils.ScanStatusHandler;
import com.dotscreen.tv.common.utils.TIFCompanionLibraryExtensionKt;
import com.google.android.media.tv.companionlibrary.model.Channel;
import com.google.android.media.tv.companionlibrary.model.InternalProviderData;
import com.google.android.media.tv.companionlibrary.utils.TvContractUtils;
import com.google.common.util.concurrent.ListenableFuture;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.rxkotlin.ObservableKt;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.oregan.sparq.dtvsupportlibrary.DVB;
import net.oregan.sparq.dtvsupportlibraryaidl.DVBTunerMetrics;

/* JADX INFO: compiled from: SyncTVChannelsWorker.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c\u001a\u000e\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u001c\u001a\u0016\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020!\u001a\u000e\u0010\"\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u001c\u001a\u000e\u0010#\u001a\u00020\u001f2\u0006\u0010\u001b\u001a\u00020\u001c\u001a\u0016\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010\u001b\u001a\u00020\u001c\u001a\u000e\u0010(\u001a\u00020\u001f2\u0006\u0010\u001b\u001a\u00020\u001c\u001a\u000e\u0010)\u001a\u00020\u001f2\u0006\u0010\u001b\u001a\u00020\u001c\u001a0\u0010*\u001a\u00020\u001f2\u0006\u0010\u001b\u001a\u00020\u001c2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010,\u001a\u00020\t2\b\b\u0002\u0010-\u001a\u00020\tH\u0007\u001a\u0018\u0010.\u001a\u00020\u001f2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010/\u001a\u00020\tH\u0002\u001aI\u00100\u001a\u00020\u001f2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u00101\u001a\u00020\u00012\f\u00102\u001a\b\u0012\u0004\u0012\u00020%032#\u00104\u001a\u001f\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(8\u0012\u0004\u0012\u00020\u001f\u0018\u000105\u001a\u001e\u00109\u001a\u0004\u0018\u00010'*\u00020%2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020!H\u0002\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0003\"\u0011\u0010\u0006\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0003\"\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000\"\u001e\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000b8BX\u0082\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u000e\u0010\u000f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000\" \u0010\u0010\u001a\n \f*\u0004\u0018\u00010\u000b0\u000b8B@\u0002X\u0083\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e\"\u0016\u0010\u0012\u001a\n \f*\u0004\u0018\u00010\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0011\u0010\u0017\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016¨\u0006:"}, d2 = {"BUNDLE_KEY_SCAN_CHANNEL_NAME", "", "getBUNDLE_KEY_SCAN_CHANNEL_NAME", "()Ljava/lang/String;", "BUNDLE_KEY_SCAN_CHANNEL_NUMBER", "getBUNDLE_KEY_SCAN_CHANNEL_NUMBER", "BUNDLE_KEY_SCAN_ERROR_REASON", "getBUNDLE_KEY_SCAN_ERROR_REASON", "DEBUG", "", "FORCE_SCAN_CHECK_DELAY", "Ljava/time/Duration;", "kotlin.jvm.PlatformType", "getFORCE_SCAN_CHECK_DELAY", "()Ljava/time/Duration;", "POSTPONE_DELAY_ON_VOD_PLAYBACK", "RESCAN_DELAY", "getRESCAN_DELAY", "TAG", "megacableForegroundReceiver", "Landroid/content/BroadcastReceiver;", "getMegacableForegroundReceiver", "()Landroid/content/BroadcastReceiver;", "wakeUpReceiver", "getWakeUpReceiver", "getLastDVBScanTime", "", "context", "Landroid/content/Context;", "getNetworkIdChanged", "insertDVBChannelLogosInTIF", "", "boService", "Lcom/dotscreen/bokit/services/BOService;", "isDVBCableIsConnected", "notifyDVBScanFinished", "rightVChannelToTIFChannel", "Lcom/google/android/media/tv/companionlibrary/model/Channel;", "rtvChannel", "Lcom/dotscreen/tv/common/bokit/RightVChannel;", "saveLastDVBScanTime", "scheduleForceSyncCheck", "scheduleSyncingTVChannels", "initialDelay", "forceUpdate", "afterWakeUp", "setNetworkIdChanged", "changed", "updateChannels", "inputId", "tifChannels", "", "onChannelDeletedCallback", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "rowId", "toRightVChannel", "tvinput_prodRelease"}, k = 2, mv = {1, 1, 16})
public final class SyncTVChannelsWorkerKt {
    private static final String BUNDLE_KEY_SCAN_CHANNEL_NAME;
    private static final String BUNDLE_KEY_SCAN_CHANNEL_NUMBER;
    private static final String BUNDLE_KEY_SCAN_ERROR_REASON;
    private static final boolean DEBUG = true;
    private static final Duration POSTPONE_DELAY_ON_VOD_PLAYBACK;
    private static final BroadcastReceiver megacableForegroundReceiver;
    private static final BroadcastReceiver wakeUpReceiver;
    private static final String TAG = SyncTVChannelsWorker.class.getSimpleName();
    private static Duration RESCAN_DELAY = Duration.ZERO;
    private static Duration FORCE_SCAN_CHECK_DELAY = Duration.ZERO;

    static {
        Duration durationOfMinutes = Duration.ofMinutes(5L);
        Intrinsics.checkExpressionValueIsNotNull(durationOfMinutes, "Duration.ofMinutes(5)");
        POSTPONE_DELAY_ON_VOD_PLAYBACK = durationOfMinutes;
        StringBuilder sb = new StringBuilder();
        Package r1 = SyncTVChannelsWorker.class.getPackage();
        if (r1 == null) {
            Intrinsics.throwNpe();
        }
        sb.append(r1.getName());
        sb.append(".bundle_key_scan_error_reason");
        BUNDLE_KEY_SCAN_ERROR_REASON = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        Package r2 = SyncTVChannelsWorker.class.getPackage();
        if (r2 == null) {
            Intrinsics.throwNpe();
        }
        sb2.append(r2.getName());
        sb2.append(".bundle_key_scan_channel_name");
        BUNDLE_KEY_SCAN_CHANNEL_NAME = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        Package r3 = SyncTVChannelsWorker.class.getPackage();
        if (r3 == null) {
            Intrinsics.throwNpe();
        }
        sb3.append(r3.getName());
        sb3.append(".bundle_key_scan_channel_zapnumber");
        BUNDLE_KEY_SCAN_CHANNEL_NUMBER = sb3.toString();
        wakeUpReceiver = new BroadcastReceiver() { // from class: com.dotscreen.tvinput.SyncTVChannelsWorkerKt$wakeUpReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context receiverContext, Intent intent) {
                String action = intent != null ? intent.getAction() : null;
                if (action != null && action.hashCode() == -1454123155 && action.equals("android.intent.action.SCREEN_ON")) {
                    Log.d(SyncTVChannelsWorkerKt.TAG, "   -> Intent.ACTION_SCREEN_ON, " + receiverContext);
                    if (receiverContext == null) {
                        Log.d(SyncTVChannelsWorkerKt.TAG, "no context");
                    } else {
                        SyncTVChannelsWorkerKt.scheduleSyncingTVChannels(receiverContext, Duration.ofMinutes(1L), true, true);
                        receiverContext.unregisterReceiver(this);
                    }
                }
            }
        };
        megacableForegroundReceiver = new BroadcastReceiver() { // from class: com.dotscreen.tvinput.SyncTVChannelsWorkerKt$megacableForegroundReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context receiverContext, Intent intent) {
                String action = intent != null ? intent.getAction() : null;
                if (action != null && action.hashCode() == -1231747523 && action.equals(UpdateManagerKt.ACTION_APPLICATION_GOES_FOREGROUND)) {
                    Log.d(SyncTVChannelsWorkerKt.TAG, "   -> ACTION_APPLICATION_GOES_FOREGROUND, " + receiverContext);
                    if (receiverContext == null) {
                        Log.d(SyncTVChannelsWorkerKt.TAG, "no context");
                    } else {
                        SyncTVChannelsWorkerKt.scheduleSyncingTVChannels(receiverContext, Duration.ofMinutes(1L), true, true);
                        receiverContext.unregisterReceiver(this);
                    }
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Duration getRESCAN_DELAY() {
        return Duration.ofMinutes(50L).plusMinutes((long) (Math.random() * ((double) 20)));
    }

    private static final Duration getFORCE_SCAN_CHECK_DELAY() {
        return Duration.ofMinutes(25L).plusMinutes((long) (Math.random() * ((double) 10)));
    }

    public static final String getBUNDLE_KEY_SCAN_ERROR_REASON() {
        return BUNDLE_KEY_SCAN_ERROR_REASON;
    }

    public static final String getBUNDLE_KEY_SCAN_CHANNEL_NAME() {
        return BUNDLE_KEY_SCAN_CHANNEL_NAME;
    }

    public static final String getBUNDLE_KEY_SCAN_CHANNEL_NUMBER() {
        return BUNDLE_KEY_SCAN_CHANNEL_NUMBER;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setNetworkIdChanged(Context context, boolean z) {
        context.getSharedPreferences("SyncTVChannelsWorker", 0).edit().putBoolean("network_id_changed", z).apply();
    }

    public static final boolean getNetworkIdChanged(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return context.getSharedPreferences("SyncTVChannelsWorker", 0).getBoolean("network_id_changed", false);
    }

    public static final Channel rightVChannelToTIFChannel(RightVChannel rtvChannel, Context context) {
        Intrinsics.checkParameterIsNotNull(rtvChannel, "rtvChannel");
        Intrinsics.checkParameterIsNotNull(context, "context");
        InternalProviderData internalProviderData = new InternalProviderData();
        internalProviderData.setVideoType(0);
        ChannelExtraCaster channelExtraCaster = rtvChannel.getExtras().getCasters().get("multicast");
        internalProviderData.setVideoUrl(channelExtraCaster != null ? channelExtraCaster.getSrc() : null);
        try {
            internalProviderData.put(CommonUtils.KEY_OPTIMIZER_CHANNEL_ID, rtvChannel.getId());
            internalProviderData.put(CommonUtils.KEY_RIGHTV_EXTERNAL_CHANNEL_ID, rtvChannel.getExtras().getChannelExternalId());
            ChannelExtraCaster channelExtraCaster2 = rtvChannel.getExtras().getCasters().get("interactive");
            if (channelExtraCaster2 != null) {
                internalProviderData.put(CommonUtils.KEY_INTERACTIVE_BANNER_URL, channelExtraCaster2.getBanner());
                internalProviderData.put(CommonUtils.KEY_INTERACTIVE_BACKGROUND_URL, channelExtraCaster2.getBackground());
                internalProviderData.put(CommonUtils.KEY_INTERACTIVE_LINK_URL, channelExtraCaster2.getSrc());
            }
        } catch (InternalProviderData.ParseException e) {
            e.printStackTrace();
        }
        Channel.Builder originalNetworkId = new Channel.Builder().setDisplayNumber("" + rtvChannel.getZappingNumber()).setDisplayName(rtvChannel.getTitle()).setServiceType(TvContractCompat.Channels.SERVICE_TYPE_AUDIO_VIDEO).setInternalProviderData(internalProviderData).setOriginalNetworkId(rtvChannel.getZappingNumber());
        if (CommonUtils.isDVBCSTB(context)) {
            Artwork squareArtwork = rtvChannel.getSquareArtwork();
            originalNetworkId.setChannelLogo(String.valueOf(squareArtwork != null ? Artwork.urlWithSize$default(squareArtwork, new Point(200, 200), 0.0d, 2, null) : null));
        }
        Channel channelBuild = originalNetworkId.build();
        Intrinsics.checkExpressionValueIsNotNull(channelBuild, "builder.build()");
        return channelBuild;
    }

    private static final RightVChannel toRightVChannel(Channel channel, Context context, BOService bOService) {
        if (!CommonUtils.isSupportedTvInputId(context, channel.getInputId())) {
            return null;
        }
        LiveTVService liveTVService = bOService.getLiveTVService();
        String strValueOf = String.valueOf(channel.getId());
        String displayName = channel.getDisplayName();
        Intrinsics.checkExpressionValueIsNotNull(displayName, "displayName");
        return new RightVChannel(context, new com.dotscreen.bokit.model.Channel(liveTVService, strValueOf, "", displayName, null, null, null, Integer.parseInt(channel.getDisplayNumber()), null, null, false, null, 3952, null), bOService, "", new ChannelExtras("", "", true, MapsKt.emptyMap(), true));
    }

    public static final void updateChannels(Context context, String str, List<Channel> tifChannels, Function1<? super Long, Unit> function1) throws IOException {
        Uri uriBuildChannelUri;
        String inputId = str;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(inputId, "inputId");
        Intrinsics.checkParameterIsNotNull(tifChannels, "tifChannels");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Uri uriBuildChannelsUriForInput = TvContract.buildChannelsUriForInput(str);
        String[] strArr = {"_id", TvContractCompat.Channels.COLUMN_ORIGINAL_NETWORK_ID};
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursorQuery = contentResolver.query(uriBuildChannelsUriForInput, strArr, null, null, null);
        int i = 0;
        int i2 = 1;
        if (cursorQuery != null) {
            Cursor cursor = cursorQuery;
            Throwable th = (Throwable) null;
            try {
                Cursor cursor2 = cursor;
                while (cursor2.moveToNext()) {
                    long j = cursor2.getLong(i);
                    long j2 = cursor2.getLong(i2);
                    if (!linkedHashMap.containsKey(Long.valueOf(j2))) {
                        linkedHashMap.put(Long.valueOf(j2), Long.valueOf(j));
                    } else {
                        Log.w(TAG, "originalNetworkId " + j2 + " already added -> duplicate");
                    }
                    i2 = 1;
                    i = 0;
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
        HashMap map = new HashMap();
        int i3 = 0;
        int i4 = 0;
        for (Iterator<Channel> it = tifChannels.iterator(); it.hasNext(); it = it) {
            Channel next = it.next();
            ContentValues contentValues = new ContentValues();
            contentValues.put("input_id", inputId);
            contentValues.putAll(next.toContentValues());
            if (next.getPackageName() == null) {
                contentValues.put("package_name", context.getPackageName());
            }
            if (next.getInputId() == null) {
                contentValues.put("input_id", inputId);
            }
            if (next.getType() == null) {
                contentValues.put("type", TvContractCompat.Channels.TYPE_OTHER);
            }
            Long l = (Long) linkedHashMap.get(Long.valueOf(next.getOriginalNetworkId()));
            Intent intent = new Intent(CommonUtils.ACTION_SCAN_STATUS_CHANGED);
            intent.putExtra(CommonUtils.BUNDLE_KEY_SCAN_STATUS, CommonUtils.SCAN_STATUS_SCANNED);
            intent.putExtra(BUNDLE_KEY_SCAN_CHANNEL_NAME, next.getDisplayName());
            intent.putExtra(BUNDLE_KEY_SCAN_CHANNEL_NUMBER, next.getDisplayNumber());
            context.sendBroadcast(intent);
            if (l == null) {
                uriBuildChannelUri = contentResolver.insert(TvContract.Channels.CONTENT_URI, contentValues);
                i3++;
                Log.d(TAG, "Adding channel " + next.getDisplayName() + " at " + uriBuildChannelUri);
            } else {
                contentValues.put("_id", l);
                uriBuildChannelUri = TvContract.buildChannelUri(l.longValue());
                Log.d(TAG, "Updating channel " + next.getDisplayName() + " at " + uriBuildChannelUri);
                contentResolver.update(uriBuildChannelUri, contentValues, null, null);
                i4++;
                linkedHashMap.remove(Long.valueOf(next.getOriginalNetworkId()));
                i3 = i3;
            }
            if (next.getChannelLogo() != null && !TextUtils.isEmpty(next.getChannelLogo())) {
                Uri uriBuildChannelLogoUri = TvContract.buildChannelLogoUri(uriBuildChannelUri);
                Intrinsics.checkExpressionValueIsNotNull(uriBuildChannelLogoUri, "TvContract.buildChannelLogoUri(uri)");
                String channelLogo = next.getChannelLogo();
                Intrinsics.checkExpressionValueIsNotNull(channelLogo, "tifChannel.channelLogo");
                map.put(uriBuildChannelLogoUri, channelLogo);
            }
            inputId = str;
        }
        int i5 = i3;
        if (!map.isEmpty()) {
            new TvContractUtils.InsertLogosTask(context).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, map);
        }
        Iterator it2 = linkedHashMap.values().iterator();
        while (it2.hasNext()) {
            long jLongValue = ((Number) it2.next()).longValue();
            Log.d(TAG, "Deleting non-existent channel " + jLongValue);
            contentResolver.delete(TvContract.buildChannelUri(jLongValue), null, null);
            if (function1 != null) {
                function1.invoke(Long.valueOf(jLongValue));
            }
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery2 = contentResolver.query(uriBuildChannelsUriForInput, strArr, null, null, null);
        if (cursorQuery2 != null) {
            Cursor cursor3 = cursorQuery2;
            Throwable th4 = (Throwable) null;
            try {
                Cursor cursor4 = cursor3;
                while (cursor4.moveToNext()) {
                    long j3 = cursor4.getLong(0);
                    long j4 = cursor4.getLong(1);
                    if (linkedHashSet.contains(Long.valueOf(j4))) {
                        arrayList.add(Long.valueOf(j3));
                    } else {
                        linkedHashSet.add(Long.valueOf(j4));
                    }
                }
                Unit unit2 = Unit.INSTANCE;
                CloseableKt.closeFinally(cursor3, th4);
            } catch (Throwable th5) {
                try {
                    throw th5;
                } catch (Throwable th6) {
                    CloseableKt.closeFinally(cursor3, th5);
                    throw th6;
                }
            }
        }
        Iterator it3 = arrayList.iterator();
        while (it3.hasNext()) {
            long jLongValue2 = ((Number) it3.next()).longValue();
            Log.d(TAG, "Deleting duplicate channel " + jLongValue2);
            contentResolver.delete(TvContract.buildChannelUri(jLongValue2), null, null);
            if (function1 != null) {
                function1.invoke(Long.valueOf(jLongValue2));
            }
        }
        Log.i(TAG, str + " : sync " + tifChannels.size() + " channels. Deleted " + (linkedHashMap.size() + arrayList.size()) + ", updated " + i4 + " added " + i5);
    }

    public static final boolean isDVBCableIsConnected(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (CommonUtils.getCurrentPlatform() == 1) {
            Log.d(TAG, "will get tuner metrics");
            DVBTunerMetrics tunerSignalMetrics = DVB.getInstance(context).getTunerSignalMetrics(0);
            if (tunerSignalMetrics != null && tunerSignalMetrics.signalLocked) {
                Log.d(TAG, "signal is locked");
            } else {
                if (!DVB.getInstance(context).tuneToHomeMux()) {
                    return false;
                }
                DVBTunerMetrics tunerSignalMetrics2 = DVB.getInstance(context).getTunerSignalMetrics(0);
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("tuner metrics signalLocked => ");
                sb.append(tunerSignalMetrics2 != null ? Boolean.valueOf(tunerSignalMetrics2.signalLocked) : null);
                Log.d(str, sb.toString());
                if (tunerSignalMetrics2 != null) {
                    return tunerSignalMetrics2.signalLocked;
                }
                return false;
            }
        }
        return true;
    }

    public static final void insertDVBChannelLogosInTIF(Context context, BOService boService) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(boService, "boService");
        Log.d(TAG, "(DVB) TIF update is finished !!!");
        HashMap map = new HashMap();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ContentResolver contentResolver = context.getContentResolver();
        Observable<R> map2 = CommonUtils.getCurrentLineup(boService).blockingGet().getItems().filter(new Predicate<com.dotscreen.bokit.model.Channel>() { // from class: com.dotscreen.tvinput.SyncTVChannelsWorkerKt$insertDVBChannelLogosInTIF$lineupChannelsList$1
            @Override // io.reactivex.functions.Predicate
            public final boolean test(com.dotscreen.bokit.model.Channel it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return true;
            }
        }).map(new Function<T, R>() { // from class: com.dotscreen.tvinput.SyncTVChannelsWorkerKt$insertDVBChannelLogosInTIF$lineupChannelsMap$1
            @Override // io.reactivex.functions.Function
            public final Pair<String, com.dotscreen.bokit.model.Channel> apply(com.dotscreen.bokit.model.Channel it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return TuplesKt.to(it.getId(), it);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(map2, "lineupChannelsList.map { it.id to it }");
        Map map3 = (Map) ObservableKt.toMap(map2).blockingGet();
        Cursor cursorQuery = contentResolver.query(TvContract.Channels.CONTENT_URI, Channel.PROJECTION, null, null, null);
        if (cursorQuery != null && cursorQuery.moveToFirst()) {
            do {
                Channel tifChannel = Channel.fromCursor(cursorQuery);
                arrayList.add(tifChannel);
                Intrinsics.checkExpressionValueIsNotNull(tifChannel, "tifChannel");
                arrayList2.add(toRightVChannel(tifChannel, context, boService));
            } while (cursorQuery.moveToNext());
            cursorQuery.close();
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Object obj = arrayList.get(i);
            Intrinsics.checkExpressionValueIsNotNull(obj, "tifChannels[i]");
            Channel channel = (Channel) obj;
            if (((RightVChannel) arrayList2.get(i)) != null) {
                com.dotscreen.bokit.model.Channel channel2 = (com.dotscreen.bokit.model.Channel) map3.get(TIFCompanionLibraryExtensionKt.getOptimizerChannelId(channel, boService));
                if (channel2 != null) {
                    Artwork squareArtwork = channel2.getSquareArtwork();
                    Uri uriBuildChannelUri = TvContract.buildChannelUri(channel.getId());
                    String strValueOf = String.valueOf(squareArtwork != null ? Artwork.urlWithSize$default(squareArtwork, new Point(200, 200), 0.0d, 2, null) : null);
                    Uri uriBuildChannelLogoUri = TvContract.buildChannelLogoUri(uriBuildChannelUri);
                    Intrinsics.checkExpressionValueIsNotNull(uriBuildChannelLogoUri, "TvContract.buildChannelLogoUri(uri)");
                    map.put(uriBuildChannelLogoUri, strValueOf);
                } else {
                    Log.e(TAG, "[Channel not in channels.json] skipping artwork for " + channel.getDisplayName() + " (" + channel.getDisplayNumber() + ')');
                }
            }
        }
        if (!map.isEmpty()) {
            new TvContractUtils.InsertLogosTask(context).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, map);
        }
    }

    public static final BroadcastReceiver getWakeUpReceiver() {
        return wakeUpReceiver;
    }

    public static final BroadcastReceiver getMegacableForegroundReceiver() {
        return megacableForegroundReceiver;
    }

    public static final void notifyDVBScanFinished(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        ScanStatusHandler.INSTANCE.setCurrentScanStatus(context, ScanStatusHandler.ScanState.FINISHED);
        context.sendBroadcast(new Intent(CommonUtils.ACTION_DVB_SCAN_FINISHED));
    }

    public static final void saveLastDVBScanTime(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        context.getSharedPreferences(TAG, 0).edit().putLong("lastDVBScanTime", System.currentTimeMillis()).apply();
    }

    public static final long getLastDVBScanTime(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return context.getSharedPreferences(TAG, 0).getLong("lastDVBScanTime", 0L);
    }

    public static final void scheduleForceSyncCheck(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Log.d("ForceSyncCheckWorker", "scheduleForceSyncCheck...");
        WorkManager workManager = WorkManager.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(workManager, "WorkManager.getInstance()");
        Constraints constraintsBuild = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
        Intrinsics.checkExpressionValueIsNotNull(constraintsBuild, "Constraints.Builder()\n  …TED)\n            .build()");
        OneTimeWorkRequest oneTimeWorkRequestBuild = new OneTimeWorkRequest.Builder(ForceSyncCheckWorker.class).setConstraints(constraintsBuild).addTag("force_sync_check").setInitialDelay(getFORCE_SCAN_CHECK_DELAY()).build();
        Intrinsics.checkExpressionValueIsNotNull(oneTimeWorkRequestBuild, "OneTimeWorkRequest.Build…LAY)\n            .build()");
        workManager.cancelAllWorkByTag("force_sync_check");
        workManager.enqueue(oneTimeWorkRequestBuild);
    }

    public static /* synthetic */ void scheduleSyncingTVChannels$default(Context context, Duration duration, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            duration = (Duration) null;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        if ((i & 8) != 0) {
            z2 = false;
        }
        scheduleSyncingTVChannels(context, duration, z, z2);
    }

    public static final void scheduleSyncingTVChannels(final Context context, final Duration duration, final boolean z, final boolean z2) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Log.d(TAG, "scheduleSyncingTVChannels(initialDelay: " + duration + ", forceUpdate: " + z + ", afterWakeUp: " + z2 + ")... ");
        if (Build.VERSION.SDK_INT >= 26) {
            final String str = "sync_tv_channels";
            final WorkManager workManager = WorkManager.getInstance(context);
            Intrinsics.checkExpressionValueIsNotNull(workManager, "WorkManager.getInstance(context)");
            workManager.pruneWork();
            final ListenableFuture<List<WorkInfo>> workInfosByTag = workManager.getWorkInfosByTag("sync_tv_channels");
            Intrinsics.checkExpressionValueIsNotNull(workInfosByTag, "manager.getWorkInfosByTag(tag)");
            workInfosByTag.addListener(new Runnable() { // from class: com.dotscreen.tvinput.SyncTVChannelsWorkerKt.scheduleSyncingTVChannels.1
                /* JADX WARN: Code duplicated, block: B:46:0x0101  */
                /* JADX WARN: Code duplicated, block: B:52:0x0162  */
                @Override // java.lang.Runnable
                public final void run() {
                    OneTimeWorkRequest.Builder builder;
                    boolean zIsDVBCSTB;
                    int seconds;
                    long time;
                    long time2;
                    List list = (List) workInfosByTag.get();
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        Log.d(SyncTVChannelsWorkerKt.TAG, ((WorkInfo) it.next()).toString());
                    }
                    if (list.size() == 0 || z) {
                        Log.d(SyncTVChannelsWorkerKt.TAG, "schedule new work");
                        int currentPlatform = CommonUtils.getCurrentPlatform();
                        if (currentPlatform != 16) {
                            switch (currentPlatform) {
                                case 1:
                                    builder = new OneTimeWorkRequest.Builder(OreganSyncTVChannelsWorker.class);
                                    break;
                                case 2:
                                    builder = new OneTimeWorkRequest.Builder(TechnicolorSyncTVChannelsWorker.class);
                                    break;
                                case 3:
                                    builder = new OneTimeWorkRequest.Builder(ZTESyncTVChannelsWorker.class);
                                    break;
                                case 4:
                                    builder = new OneTimeWorkRequest.Builder(ZTESyncTVChannelsWorker.class);
                                    break;
                                case 5:
                                    builder = new OneTimeWorkRequest.Builder(ZTESyncTVChannelsWorker.class);
                                    break;
                                case 6:
                                    builder = new OneTimeWorkRequest.Builder(ZTESyncTVChannelsWorker.class);
                                    break;
                                default:
                                    builder = new OneTimeWorkRequest.Builder(MockSyncTVChannelsWorker.class);
                                    break;
                            }
                        } else {
                            builder = new OneTimeWorkRequest.Builder(SimulatorSyncTVChannelsWorker.class);
                        }
                        try {
                            zIsDVBCSTB = CommonUtils.isDVBCSTB(context);
                        } catch (Exception e) {
                            e.printStackTrace();
                            zIsDVBCSTB = false;
                        }
                        if (zIsDVBCSTB && duration != null && !z2) {
                            Calendar refCal = Calendar.getInstance();
                            Calendar cal = Calendar.getInstance();
                            Intrinsics.checkExpressionValueIsNotNull(cal, "cal");
                            cal.setTime(new Date());
                            Intrinsics.checkExpressionValueIsNotNull(refCal, "refCal");
                            refCal.setTime(new Date());
                            if (CommonUtils.getCurrentPlatform() != 1) {
                                Duration durationOfSeconds = Duration.ofSeconds((long) (Math.random() * ((double) 300)));
                                int minutes = (int) durationOfSeconds.toMinutes();
                                try {
                                    Duration durationMinusMinutes = durationOfSeconds.minusMinutes(minutes);
                                    Intrinsics.checkExpressionValueIsNotNull(durationMinusMinutes, "randomDuration.minusMinutes(minutes.toLong())");
                                    seconds = (int) durationMinusMinutes.getSeconds();
                                } catch (ArithmeticException unused) {
                                    seconds = 0;
                                }
                                Log.v(SyncTVChannelsWorkerKt.TAG, minutes + " m and " + seconds + " s (3am) duration:" + durationOfSeconds);
                                cal.set(11, 3);
                                cal.set(12, minutes);
                                cal.set(13, seconds);
                                cal.set(14, 0);
                                if (refCal.get(11) >= 3) {
                                    cal.add(6, 1);
                                }
                                Date time3 = cal.getTime();
                                Intrinsics.checkExpressionValueIsNotNull(time3, "cal.time");
                                time = time3.getTime();
                                time2 = new Date().getTime();
                            } else {
                                if (CommonUtils.getCurrentPlatform() == 1) {
                                    Integer intOrNull = StringsKt.toIntOrNull(DeviceInfo.INSTANCE.getFirmwareVersionName());
                                    if ((intOrNull != null ? intOrNull.intValue() : 0) >= 809291) {
                                        Duration durationOfSeconds2 = Duration.ofSeconds((long) (Math.random() * ((double) 300)));
                                        int minutes2 = (int) durationOfSeconds2.toMinutes();
                                        Duration durationMinusMinutes2 = durationOfSeconds2.minusMinutes(minutes2);
                                        Intrinsics.checkExpressionValueIsNotNull(durationMinusMinutes2, "randomDuration.minusMinutes(minutes.toLong())");
                                        seconds = (int) durationMinusMinutes2.getSeconds();
                                        Log.v(SyncTVChannelsWorkerKt.TAG, minutes2 + " m and " + seconds + " s (3am) duration:" + durationOfSeconds2);
                                        cal.set(11, 3);
                                        cal.set(12, minutes2);
                                        cal.set(13, seconds);
                                        cal.set(14, 0);
                                        if (refCal.get(11) >= 3) {
                                            cal.add(6, 1);
                                        }
                                        Date time4 = cal.getTime();
                                        Intrinsics.checkExpressionValueIsNotNull(time4, "cal.time");
                                        time = time4.getTime();
                                        time2 = new Date().getTime();
                                    }
                                }
                                refCal.set(2030, 1, 1);
                                time = refCal.getTimeInMillis();
                                time2 = new Date().getTime();
                            }
                            builder.setInitialDelay(Duration.ofMillis(time - time2));
                        } else {
                            Duration duration2 = duration;
                            if (duration2 != null) {
                                builder.setInitialDelay(duration2);
                            }
                        }
                        Constraints constraintsBuild = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
                        Intrinsics.checkExpressionValueIsNotNull(constraintsBuild, "Constraints.Builder()\n  …                 .build()");
                        builder.setConstraints(constraintsBuild);
                        builder.addTag(str);
                        workManager.cancelAllWorkByTag(str);
                        workManager.pruneWork();
                        OneTimeWorkRequest oneTimeWorkRequestBuild = builder.build();
                        Intrinsics.checkExpressionValueIsNotNull(oneTimeWorkRequestBuild, "builder.build()");
                        OneTimeWorkRequest oneTimeWorkRequest = oneTimeWorkRequestBuild;
                        Log.d(SyncTVChannelsWorkerKt.TAG, "scheduleSyncingTVChannels: enqueue request " + oneTimeWorkRequest.getId());
                        Intrinsics.checkExpressionValueIsNotNull(workManager.enqueue(oneTimeWorkRequest), "manager.enqueue(request)");
                        return;
                    }
                    Log.d(SyncTVChannelsWorkerKt.TAG, "work already scheduled");
                }
            }, new Executor() { // from class: com.dotscreen.tvinput.SyncTVChannelsWorkerKt.scheduleSyncingTVChannels.2
                @Override // java.util.concurrent.Executor
                public final void execute(Runnable runnable) {
                    Executors.newCachedThreadPool().execute(runnable);
                }
            });
        }
    }
}
