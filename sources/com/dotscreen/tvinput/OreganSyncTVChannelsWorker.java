package com.dotscreen.tvinput;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.media.tv.TvContract;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.work.WorkerParameters;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.tv.common.bokit.RightVChannel;
import com.dotscreen.tv.common.utils.CommonUtils;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.oregan.sparq.dtvsupportlibrary.CAS;
import net.oregan.sparq.dtvsupportlibrary.DVB;
import net.oregan.sparq.dtvsupportlibrary.IPTV;
import net.oregan.sparq.dtvsupportlibrary.TV;
import net.oregan.sparq.dtvsupportlibraryaidl.ICleanUpCompleteListener;
import net.oregan.sparq.dtvsupportlibraryaidl.IDVBNetworkIdChangeListener;

/* JADX INFO: compiled from: SyncTVChannelsWorker.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0014\u001a\u00020\u0015J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0016\u0010\u0018\u001a\u00020\u00172\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0016J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n \t*\u0004\u0018\u00010\u000f0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n \t*\u0004\u0018\u00010\u00110\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\n \t*\u0004\u0018\u00010\u00130\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/dotscreen/tvinput/OreganSyncTVChannelsWorker;", "Lcom/dotscreen/tvinput/SyncTVChannelsWorker;", "context", "Landroid/content/Context;", "params", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "cas", "Lnet/oregan/sparq/dtvsupportlibrary/CAS;", "kotlin.jvm.PlatformType", "handler", "Landroid/os/Handler;", "mCleanUpCompleteListener", "Lnet/oregan/sparq/dtvsupportlibraryaidl/ICleanUpCompleteListener;", "oreganDVB", "Lnet/oregan/sparq/dtvsupportlibrary/DVB;", "oreganIPTV", "Lnet/oregan/sparq/dtvsupportlibrary/IPTV;", "oreganTV", "Lnet/oregan/sparq/dtvsupportlibrary/TV;", "cleanUpStaleChannels", "", "installDVBChannels", "Lio/reactivex/Completable;", "installIPChannels", "channels", "", "Lcom/dotscreen/bokit/model/Channel;", "setVcasConfiguration", "", "streamerType", "", "tvinput_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class OreganSyncTVChannelsWorker extends SyncTVChannelsWorker {
    private final CAS cas;
    private final Handler handler;
    private final ICleanUpCompleteListener mCleanUpCompleteListener;
    private final DVB oreganDVB;
    private final IPTV oreganIPTV;
    private final TV oreganTV;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OreganSyncTVChannelsWorker(Context context, WorkerParameters params) {
        super(context, params);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(params, "params");
        this.oreganTV = TV.getInstance(context);
        this.oreganIPTV = IPTV.getInstance(context);
        this.oreganDVB = DVB.getInstance(context);
        this.cas = CAS.getInstance(context);
        this.handler = new Handler(Looper.getMainLooper());
        this.mCleanUpCompleteListener = new ICleanUpCompleteListener.Stub() { // from class: com.dotscreen.tvinput.OreganSyncTVChannelsWorker$mCleanUpCompleteListener$1
            @Override // net.oregan.sparq.dtvsupportlibraryaidl.ICleanUpCompleteListener
            public void onCleanUpComplete(int result, String inputId) {
                Log.d(SyncTVChannelsWorkerKt.TAG, "Got CleanUpComplete: result:" + result);
            }
        };
    }

    @Override // com.dotscreen.tvinput.SyncTVChannelsWorker
    public Completable installDVBChannels() {
        Completable completableCreate = Completable.create(new AnonymousClass1());
        Intrinsics.checkExpressionValueIsNotNull(completableCreate, "Completable.create { emi…ilable\"))\n        }\n    }");
        return completableCreate;
    }

    /* JADX INFO: renamed from: com.dotscreen.tvinput.OreganSyncTVChannelsWorker$installDVBChannels$1, reason: invalid class name */
    /* JADX INFO: compiled from: SyncTVChannelsWorker.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "emitter", "Lio/reactivex/CompletableEmitter;", "subscribe"}, k = 3, mv = {1, 1, 16})
    static final class AnonymousClass1 implements CompletableOnSubscribe {
        AnonymousClass1() {
        }

        @Override // io.reactivex.CompletableOnSubscribe
        public final void subscribe(CompletableEmitter emitter) {
            Intrinsics.checkParameterIsNotNull(emitter, "emitter");
            OreganSyncTVChannelsWorker.this.cleanUpStaleChannels();
            Log.d(SyncTVChannelsWorkerKt.TAG, "installDVBChannels()...");
            String str = SyncTVChannelsWorkerKt.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("using SparQ DTV Support Library version: ");
            TV oreganTV = OreganSyncTVChannelsWorker.this.oreganTV;
            Intrinsics.checkExpressionValueIsNotNull(oreganTV, "oreganTV");
            sb.append(oreganTV.getVersion());
            Log.d(str, sb.toString());
            OreganSyncTVChannelsWorker.this.getContext().sendBroadcast(new Intent(CommonUtils.ACTION_DVB_SCAN_STARTED));
            try {
                OreganSyncTVChannelsWorker.this.setVcasConfiguration(1);
            } catch (Throwable th) {
                Log.w(SyncTVChannelsWorkerKt.TAG, "Error setting \"persist.omb.vcas_configured_streamer_type\"", th);
            }
            SyncTVChannelsWorkerKt.setNetworkIdChanged(OreganSyncTVChannelsWorker.this.getContext(), false);
            IDVBNetworkIdChangeListener.Stub stub = new IDVBNetworkIdChangeListener.Stub() { // from class: com.dotscreen.tvinput.OreganSyncTVChannelsWorker$installDVBChannels$1$networkIdChangedListener$1
                @Override // net.oregan.sparq.dtvsupportlibraryaidl.IDVBNetworkIdChangeListener
                public void onNetworkIdChange(int expectedNetworkId, int foundNetworkId) {
                    Log.d(SyncTVChannelsWorkerKt.TAG, "  onNetworkIdChange(expectedNetworkId:" + expectedNetworkId + ", foundNetworkId:" + foundNetworkId + ')');
                }
            };
            OreganSyncTVChannelsWorker$installDVBChannels$1$scanProgressListener$1 oreganSyncTVChannelsWorker$installDVBChannels$1$scanProgressListener$1 = new OreganSyncTVChannelsWorker$installDVBChannels$1$scanProgressListener$1(this, emitter);
            OreganSyncTVChannelsWorker.this.oreganDVB.setNetworkIdChangeListener(stub);
            if (OreganSyncTVChannelsWorker.this.oreganDVB.startScan(oreganSyncTVChannelsWorker$installDVBChannels$1$scanProgressListener$1)) {
                return;
            }
            emitter.onError(new UnsupportedOperationException("Oregan DVB-C service unavailable"));
        }
    }

    public final void cleanUpStaleChannels() {
        int iCleanUpStaleChannels = this.oreganTV.cleanUpStaleChannels(this.mCleanUpCompleteListener);
        Log.w(SyncTVChannelsWorkerKt.TAG, "cleanUpStaleChannels() - cleanUpStaleChannels request initiated - return value:" + iCleanUpStaleChannels + '\n');
    }

    @Override // com.dotscreen.tvinput.SyncTVChannelsWorker
    public Completable installIPChannels(final List<? extends Channel> channels) {
        Intrinsics.checkParameterIsNotNull(channels, "channels");
        Completable completableCreate = Completable.create(new CompletableOnSubscribe() { // from class: com.dotscreen.tvinput.OreganSyncTVChannelsWorker.installIPChannels.1
            @Override // io.reactivex.CompletableOnSubscribe
            public final void subscribe(CompletableEmitter emitter) throws IOException {
                Intrinsics.checkParameterIsNotNull(emitter, "emitter");
                OreganSyncTVChannelsWorker.this.cleanUpStaleChannels();
                Log.d(SyncTVChannelsWorkerKt.TAG, "installIPChannels...");
                List list = channels;
                ArrayList<Channel> arrayList = new ArrayList();
                for (Object obj : list) {
                    if (OreganSyncTVChannelsWorker.this.isIPChannel((Channel) obj)) {
                        arrayList.add(obj);
                    }
                }
                for (Channel channel : arrayList) {
                    Log.d(SyncTVChannelsWorkerKt.TAG, "   " + channel);
                }
                try {
                    if (OreganSyncTVChannelsWorker.this.setVcasConfiguration(0)) {
                        Log.d(SyncTVChannelsWorkerKt.TAG, "VcasConfiguration success");
                    } else {
                        Log.w(SyncTVChannelsWorkerKt.TAG, "VcasConfiguration failed");
                    }
                } catch (Throwable th) {
                    Log.w(SyncTVChannelsWorkerKt.TAG, "Error setting \"persist.omb.vcas_configured_streamer_type\"", th);
                }
                Context context = OreganSyncTVChannelsWorker.this.getContext();
                IPTV oreganIPTV = OreganSyncTVChannelsWorker.this.oreganIPTV;
                Intrinsics.checkExpressionValueIsNotNull(oreganIPTV, "oreganIPTV");
                String iptvTvInputId = oreganIPTV.getIptvTvInputId();
                Intrinsics.checkExpressionValueIsNotNull(iptvTvInputId, "oreganIPTV.iptvTvInputId");
                List list2 = channels;
                ArrayList arrayList2 = new ArrayList();
                for (Object obj2 : list2) {
                    if (OreganSyncTVChannelsWorker.this.isIPChannel((Channel) obj2)) {
                        arrayList2.add(obj2);
                    }
                }
                ArrayList<Channel> arrayList3 = arrayList2;
                ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
                for (Channel channel2 : arrayList3) {
                    if (channel2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVChannel");
                    }
                    arrayList4.add(SyncTVChannelsWorkerKt.rightVChannelToTIFChannel((RightVChannel) channel2, OreganSyncTVChannelsWorker.this.getContext()));
                }
                SyncTVChannelsWorkerKt.updateChannels(context, iptvTvInputId, arrayList4, new Function1<Long, Unit>() { // from class: com.dotscreen.tvinput.OreganSyncTVChannelsWorker.installIPChannels.1.5
                    public final void invoke(long j) {
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                        invoke(l.longValue());
                        return Unit.INSTANCE;
                    }
                });
                ContentResolver contentResolver = OreganSyncTVChannelsWorker.this.getContext().getContentResolver();
                ContentValues contentValues = new ContentValues();
                contentValues.put("browsable", (Integer) 1);
                IPTV oreganIPTV2 = OreganSyncTVChannelsWorker.this.oreganIPTV;
                Intrinsics.checkExpressionValueIsNotNull(oreganIPTV2, "oreganIPTV");
                String iptvTvInputId2 = oreganIPTV2.getIptvTvInputId();
                Intrinsics.checkExpressionValueIsNotNull(iptvTvInputId2, "oreganIPTV.iptvTvInputId");
                contentValues.put("package_name", (String) CollectionsKt.first(StringsKt.split$default((CharSequence) iptvTvInputId2, new String[]{"/"}, false, 0, 6, (Object) null)));
                Uri uri = TvContract.Channels.CONTENT_URI;
                IPTV oreganIPTV3 = OreganSyncTVChannelsWorker.this.oreganIPTV;
                Intrinsics.checkExpressionValueIsNotNull(oreganIPTV3, "oreganIPTV");
                contentResolver.update(uri, contentValues, "input_id = ?", new String[]{oreganIPTV3.getIptvTvInputId()});
                Log.d(SyncTVChannelsWorkerKt.TAG, "installIPChannels done");
                emitter.onComplete();
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(completableCreate, "Completable.create { emi…mitter.onComplete()\n    }");
        return completableCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean setVcasConfiguration(int streamerType) {
        return this.cas.setVcasConfiguration(streamerType, CommonUtils.VRMX_IP, CommonUtils.VRMX_PORT);
    }
}
