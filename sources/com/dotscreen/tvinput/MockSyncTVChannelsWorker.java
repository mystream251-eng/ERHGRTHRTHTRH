package com.dotscreen.tvinput;

import android.content.ContentProviderOperation;
import android.content.Context;
import android.content.OperationApplicationException;
import android.media.tv.TvContract;
import android.os.RemoteException;
import android.util.Log;
import androidx.tvprovider.media.tv.TvContractCompat;
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

/* JADX INFO: compiled from: SyncTVChannelsWorker.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\u0016\u0010\u000b\u001a\u00020\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0016¨\u0006\u000f"}, d2 = {"Lcom/dotscreen/tvinput/MockSyncTVChannelsWorker;", "Lcom/dotscreen/tvinput/SyncTVChannelsWorker;", "context", "Landroid/content/Context;", "params", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "clearChannelDatabase", "", "installDVBChannels", "Lio/reactivex/Completable;", "installIPChannels", "channels", "", "Lcom/dotscreen/bokit/model/Channel;", "tvinput_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class MockSyncTVChannelsWorker extends SyncTVChannelsWorker {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MockSyncTVChannelsWorker(Context context, WorkerParameters params) {
        super(context, params);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(params, "params");
    }

    @Override // com.dotscreen.tvinput.SyncTVChannelsWorker
    public Completable installDVBChannels() {
        Completable completableError = Completable.error(new UnsupportedOperationException("DVB Scan"));
        Intrinsics.checkExpressionValueIsNotNull(completableError, "Completable.error(Unsupp…ionException(\"DVB Scan\"))");
        return completableError;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void clearChannelDatabase() throws RemoteException, OperationApplicationException {
        ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
        arrayList.add(ContentProviderOperation.newDelete(TvContract.Channels.CONTENT_URI).build());
        getContext().getContentResolver().applyBatch(TvContractCompat.AUTHORITY, arrayList);
    }

    @Override // com.dotscreen.tvinput.SyncTVChannelsWorker
    public Completable installIPChannels(final List<? extends Channel> channels) {
        Intrinsics.checkParameterIsNotNull(channels, "channels");
        Completable completableCreate = Completable.create(new CompletableOnSubscribe() { // from class: com.dotscreen.tvinput.MockSyncTVChannelsWorker.installIPChannels.1
            @Override // io.reactivex.CompletableOnSubscribe
            public final void subscribe(CompletableEmitter emitter) throws RemoteException, IOException, OperationApplicationException {
                Intrinsics.checkParameterIsNotNull(emitter, "emitter");
                Log.d(SyncTVChannelsWorkerKt.TAG, "installIPChannels(" + channels + ")...");
                MockSyncTVChannelsWorker.this.clearChannelDatabase();
                Context context = MockSyncTVChannelsWorker.this.getContext();
                String oTTLiveInputId = CommonUtils.getOTTLiveInputId();
                Intrinsics.checkExpressionValueIsNotNull(oTTLiveInputId, "CommonUtils.getOTTLiveInputId()");
                List<Channel> list = channels;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                for (Channel channel : list) {
                    if (channel == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVChannel");
                    }
                    arrayList.add(SyncTVChannelsWorkerKt.rightVChannelToTIFChannel((RightVChannel) channel, MockSyncTVChannelsWorker.this.getContext()));
                }
                SyncTVChannelsWorkerKt.updateChannels(context, oTTLiveInputId, arrayList, new Function1<Long, Unit>() { // from class: com.dotscreen.tvinput.MockSyncTVChannelsWorker.installIPChannels.1.2
                    public final void invoke(long j) {
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                        invoke(l.longValue());
                        return Unit.INSTANCE;
                    }
                });
                MockSyncTVChannelsWorker.this.makeAllChannelsBrowsable$tvinput_prodRelease();
                Log.d(SyncTVChannelsWorkerKt.TAG, "installIPChannels done");
                emitter.onComplete();
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(completableCreate, "Completable.create { emi…mitter.onComplete()\n    }");
        return completableCreate;
    }
}
