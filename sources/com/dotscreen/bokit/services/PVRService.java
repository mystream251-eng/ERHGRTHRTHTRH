package com.dotscreen.bokit.services;

import com.dotscreen.bokit.NotImplementedException;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.model.TVRecording;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PVRService.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\n0\u00122\u0006\u0010\r\u001a\u00020\u000eH\u0016J.\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\u00142\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J;\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\n0\u00142\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001cH\u0016¢\u0006\u0002\u0010\u001eJ\u000e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u0012H\u0016J\u0010\u0010!\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\""}, d2 = {"Lcom/dotscreen/bokit/services/PVRService;", "", "boService", "Lcom/dotscreen/bokit/services/BOService;", "(Lcom/dotscreen/bokit/services/BOService;)V", "getBoService", "()Lcom/dotscreen/bokit/services/BOService;", "deleteRecording", "Lio/reactivex/Completable;", "recording", "Lcom/dotscreen/bokit/model/TVRecording;", "recordingForAiring", "Lio/reactivex/Maybe;", "mode", "Lcom/dotscreen/bokit/services/PVRStorageMode;", "airing", "Lcom/dotscreen/bokit/model/TVAiring;", "recordings", "Lio/reactivex/Observable;", "schedule", "Lio/reactivex/Single;", "startDate", "Ljava/util/Date;", "endDate", "channel", "Lcom/dotscreen/bokit/model/Channel;", "scheduleAiring", "beforeDelay", "", "afterDelay", "(Lcom/dotscreen/bokit/services/PVRStorageMode;Lcom/dotscreen/bokit/model/TVAiring;Ljava/lang/Long;Ljava/lang/Long;)Lio/reactivex/Single;", "storageInfos", "Lcom/dotscreen/bokit/services/PVRStorageInfo;", "updateRecording", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public class PVRService {
    private final BOService boService;

    public PVRService(BOService boService) {
        Intrinsics.checkNotNullParameter(boService, "boService");
        this.boService = boService;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final BOService getBoService() {
        return this.boService;
    }

    public static /* synthetic */ Single scheduleAiring$default(PVRService pVRService, PVRStorageMode pVRStorageMode, TVAiring tVAiring, Long l, Long l2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: scheduleAiring");
        }
        if ((i & 4) != 0) {
            l = (Long) null;
        }
        if ((i & 8) != 0) {
            l2 = (Long) null;
        }
        return pVRService.scheduleAiring(pVRStorageMode, tVAiring, l, l2);
    }

    public Observable<PVRStorageInfo> storageInfos() {
        Observable<PVRStorageInfo> observableError = Observable.error(new NotImplementedException("An operation is not implemented: PVRService.storageInfos", null, 2, null));
        Intrinsics.checkNotNullExpressionValue(observableError, "Observable.error(NotImpl…t implemented: $reason\"))");
        return observableError;
    }

    public Observable<TVRecording> recordings(PVRStorageMode mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Observable<TVRecording> observableError = Observable.error(new NotImplementedException("An operation is not implemented: PVRService.recordings", null, 2, null));
        Intrinsics.checkNotNullExpressionValue(observableError, "Observable.error(NotImpl…t implemented: $reason\"))");
        return observableError;
    }

    public Maybe<TVRecording> recordingForAiring(PVRStorageMode mode, TVAiring airing) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(airing, "airing");
        Maybe<TVRecording> maybeError = Maybe.error(new NotImplementedException("An operation is not implemented: PVRService.recordingForAiring", null, 2, null));
        Intrinsics.checkNotNullExpressionValue(maybeError, "Maybe.error(NotImplement…t implemented: $reason\"))");
        return maybeError;
    }

    public Single<TVRecording> scheduleAiring(PVRStorageMode mode, TVAiring airing, Long beforeDelay, Long afterDelay) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(airing, "airing");
        Single<TVRecording> singleError = Single.error(new NotImplementedException("An operation is not implemented: PVRService.scheduleAiring", null, 2, null));
        Intrinsics.checkNotNullExpressionValue(singleError, "Single.error(NotImplemen…t implemented: $reason\"))");
        return singleError;
    }

    public Single<TVRecording> schedule(PVRStorageMode mode, Date startDate, Date endDate, Channel channel) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        Intrinsics.checkNotNullParameter(channel, "channel");
        Single<TVRecording> singleError = Single.error(new NotImplementedException("An operation is not implemented: PVRService.schedule", null, 2, null));
        Intrinsics.checkNotNullExpressionValue(singleError, "Single.error(NotImplemen…t implemented: $reason\"))");
        return singleError;
    }

    public Completable updateRecording(TVRecording recording) {
        Intrinsics.checkNotNullParameter(recording, "recording");
        Completable completableError = Completable.error(new NotImplementedException("An operation is not implemented: PVRService.updateRecording", null, 2, null));
        Intrinsics.checkNotNullExpressionValue(completableError, "Completable.error(NotImp…t implemented: $reason\"))");
        return completableError;
    }

    public Completable deleteRecording(TVRecording recording) {
        Intrinsics.checkNotNullParameter(recording, "recording");
        Completable completableError = Completable.error(new NotImplementedException("An operation is not implemented: PVRService.deleteRecording", null, 2, null));
        Intrinsics.checkNotNullExpressionValue(completableError, "Completable.error(NotImp…t implemented: $reason\"))");
        return completableError;
    }
}
