package com.dotscreen.tv.common.recording;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.util.Log;
import com.android.tv.common.R;
import com.android.volley.NetworkError;
import com.dotscreen.bokit.BackendErrorException;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.model.TVRecording;
import com.dotscreen.bokit.services.PVRService;
import com.dotscreen.bokit.services.PVRStorageMode;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GlobalRecordingManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0012\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0002J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J>\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001a0 2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010!\u001a\u00020\u001e2\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010&\u001a\u0004\u0018\u00010%H\u0016J\u0010\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0011H\u0016R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006*"}, d2 = {"Lcom/dotscreen/tv/common/recording/GlobalRecordingManager;", "Lcom/dotscreen/tv/common/recording/RecordingInterface;", "context", "Landroid/content/Context;", "pvrService", "Lcom/dotscreen/bokit/services/PVRService;", "pvrType", "Lcom/dotscreen/bokit/services/PVRStorageMode;", "contentResolver", "Landroid/content/ContentResolver;", "(Landroid/content/Context;Lcom/dotscreen/bokit/services/PVRService;Lcom/dotscreen/bokit/services/PVRStorageMode;Landroid/content/ContentResolver;)V", "getPvrType", "()Lcom/dotscreen/bokit/services/PVRStorageMode;", "setPvrType", "(Lcom/dotscreen/bokit/services/PVRStorageMode;)V", "getAllRecordings", "Lio/reactivex/Observable;", "Lcom/dotscreen/bokit/model/TVRecording;", "getRecording", "Lio/reactivex/Maybe;", "recordingId", "", "getRecordingsForChannel", "channel", "Lcom/dotscreen/bokit/model/Channel;", "handleNetworkError", "Lcom/dotscreen/tv/common/recording/StartRecordingResponse;", "cause", "", "recordableChannel", "", "startRecording", "Lio/reactivex/Single;", "recordCurrentProgram", "program", "Lcom/dotscreen/bokit/model/TVAiring;", "startDate", "Ljava/util/Date;", "endDate", "stopRecording", "Lio/reactivex/Completable;", "recording", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public abstract class GlobalRecordingManager implements RecordingInterface {
    private final ContentResolver contentResolver;
    private final Context context;
    private final PVRService pvrService;
    private PVRStorageMode pvrType;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PVRStorageMode.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[PVRStorageMode.Network.ordinal()] = 1;
            $EnumSwitchMapping$0[PVRStorageMode.HardDiskDrive.ordinal()] = 2;
        }
    }

    public GlobalRecordingManager(Context context, PVRService pvrService, PVRStorageMode pvrType, ContentResolver contentResolver) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(pvrService, "pvrService");
        Intrinsics.checkParameterIsNotNull(pvrType, "pvrType");
        this.context = context;
        this.pvrService = pvrService;
        this.pvrType = pvrType;
        this.contentResolver = contentResolver;
    }

    public /* synthetic */ GlobalRecordingManager(Context context, PVRService pVRService, PVRStorageMode pVRStorageMode, ContentResolver contentResolver, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, pVRService, pVRStorageMode, (i & 8) != 0 ? (ContentResolver) null : contentResolver);
    }

    @Override // com.dotscreen.tv.common.recording.RecordingInterface
    public Maybe<TVRecording> getLiveRecordingForChannel(Channel channel) {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        return RecordingInterface.DefaultImpls.getLiveRecordingForChannel(this, channel);
    }

    public final PVRStorageMode getPvrType() {
        return this.pvrType;
    }

    @Override // com.dotscreen.tv.common.recording.RecordingInterface
    public Single<Boolean> hasLiveRecording(Channel channel) {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        return RecordingInterface.DefaultImpls.hasLiveRecording(this, channel);
    }

    @Override // com.dotscreen.tv.common.recording.RecordingInterface
    public boolean isLive(TVRecording recording) {
        Intrinsics.checkParameterIsNotNull(recording, "recording");
        return RecordingInterface.DefaultImpls.isLive(this, recording);
    }

    @Override // com.dotscreen.tv.common.recording.RecordingInterface
    public Single<Boolean> isRecordingLive(Channel channel) {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        return RecordingInterface.DefaultImpls.isRecordingLive(this, channel);
    }

    @Override // com.dotscreen.tv.common.recording.RecordingInterface
    public void registerEvent(ContentObserver contentObserver) {
        RecordingInterface.DefaultImpls.registerEvent(this, contentObserver);
    }

    public final void setPvrType(PVRStorageMode pVRStorageMode) {
        Intrinsics.checkParameterIsNotNull(pVRStorageMode, "<set-?>");
        this.pvrType = pVRStorageMode;
    }

    @Override // com.dotscreen.tv.common.recording.RecordingInterface
    public Maybe<TVRecording> getRecording(final String recordingId) {
        Intrinsics.checkParameterIsNotNull(recordingId, "recordingId");
        Maybe<TVRecording> maybeFirstElement = getAllRecordings().filter(new Predicate<TVRecording>() { // from class: com.dotscreen.tv.common.recording.GlobalRecordingManager.getRecording.1
            @Override // io.reactivex.functions.Predicate
            public final boolean test(TVRecording it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return Intrinsics.areEqual(it.getId(), recordingId);
            }
        }).firstElement();
        Intrinsics.checkExpressionValueIsNotNull(maybeFirstElement, "getAllRecordings().filte…ordingId }.firstElement()");
        return maybeFirstElement;
    }

    @Override // com.dotscreen.tv.common.recording.RecordingInterface
    public Single<StartRecordingResponse> startRecording(final Channel channel, final boolean recordCurrentProgram, TVAiring program, final Date startDate, final Date endDate) throws NoRecordableChannelException, InformationMissingException {
        Single singleScheduleAiring;
        if (startDate != null && endDate != null && channel != null) {
            singleScheduleAiring = this.pvrService.schedule(this.pvrType, startDate, endDate, channel);
        } else if (channel != null) {
            if (recordableChannel(channel)) {
                singleScheduleAiring = hasLiveRecording(channel).flatMap((Function) new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.recording.GlobalRecordingManager.startRecording.1
                    @Override // io.reactivex.functions.Function
                    public final Single<TVRecording> apply(Boolean hasRecording) throws isRecordingException, InformationMissingException {
                        Intrinsics.checkParameterIsNotNull(hasRecording, "hasRecording");
                        Log.v("RecordManager", " Already record this program ? " + hasRecording);
                        if (hasRecording.booleanValue()) {
                            throw new isRecordingException();
                        }
                        if (recordCurrentProgram) {
                            Log.v("RecordManager", "Launch recording");
                            Single<R> singleFlatMapSingle = channel.airing(new Date()).flatMapSingle(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.recording.GlobalRecordingManager.startRecording.1.1
                                @Override // io.reactivex.functions.Function
                                public final Single<TVRecording> apply(TVAiring it) {
                                    Intrinsics.checkParameterIsNotNull(it, "it");
                                    Log.v("RecordManager", "Will schedule the record for " + it);
                                    return GlobalRecordingManager.this.pvrService.scheduleAiring(GlobalRecordingManager.this.getPvrType(), it, Long.valueOf(GlobalRecordingManager.this.context.getResources().getInteger(R.integer.rte_order_recording_lead_duration)), Long.valueOf(GlobalRecordingManager.this.context.getResources().getInteger(R.integer.rte_order_recording_lag_duration)));
                                }
                            });
                            Intrinsics.checkExpressionValueIsNotNull(singleFlatMapSingle, "channel.airing(Date()).f…                        }");
                            return singleFlatMapSingle;
                        }
                        if (startDate != null && endDate != null) {
                            return GlobalRecordingManager.this.pvrService.schedule(GlobalRecordingManager.this.getPvrType(), startDate, endDate, channel);
                        }
                        throw new InformationMissingException();
                    }
                });
                Intrinsics.checkExpressionValueIsNotNull(singleScheduleAiring, "if(recordableChannel(cha…on()\n                   }");
            } else {
                throw new NoRecordableChannelException();
            }
        } else if (program != null) {
            singleScheduleAiring = this.pvrService.scheduleAiring(this.pvrType, program, Long.valueOf(this.context.getResources().getInteger(R.integer.rte_order_recording_lead_duration)), Long.valueOf(this.context.getResources().getInteger(R.integer.rte_order_recording_lag_duration)));
        } else {
            throw new InformationMissingException();
        }
        Single<StartRecordingResponse> singleOnErrorReturn = singleScheduleAiring.map(new Function<T, R>() { // from class: com.dotscreen.tv.common.recording.GlobalRecordingManager.startRecording.2
            @Override // io.reactivex.functions.Function
            public final StartRecordingResponse apply(TVRecording it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Log.v("RecordManager", "Recording " + it + " as start");
                return new StartRecordingResponse(it.getId(), null, 2, null);
            }
        }).onErrorReturn(new Function<Throwable, StartRecordingResponse>() { // from class: com.dotscreen.tv.common.recording.GlobalRecordingManager.startRecording.3
            @Override // io.reactivex.functions.Function
            public final StartRecordingResponse apply(Throwable it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Log.d("RecordManager", "Something goes wrong : " + it);
                if (it instanceof NoRecordableChannelException) {
                    return new StartRecordingResponse(null, Error.NOT_RECORDABLE, 1, null);
                }
                if (it instanceof isRecordingException) {
                    return new StartRecordingResponse(null, Error.IS_RECORDING, 1, null);
                }
                if (it instanceof InformationMissingException) {
                    return new StartRecordingResponse(null, Error.INFORMATION_MISSING, 1, null);
                }
                if (it instanceof NetworkError) {
                    return GlobalRecordingManager.this.handleNetworkError(it.getCause());
                }
                return new StartRecordingResponse(null, Error.UNKNOWN, 1, null);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleOnErrorReturn, "when {\n               st…          }\n            }");
        return singleOnErrorReturn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final StartRecordingResponse handleNetworkError(Throwable cause) {
        if (cause instanceof BackendErrorException) {
            Log.v("RecordManager", "Handle a backendErrorexception => " + cause);
            String backendCode = ((BackendErrorException) cause).getBackendCode();
            int iHashCode = backendCode.hashCode();
            if (iHashCode != -1837381347) {
                if (iHashCode != -1255107522) {
                    if (iHashCode == 646103279 && backendCode.equals("TICKET_EXISTS")) {
                        return new StartRecordingResponse(null, Error.IS_RECORDING);
                    }
                } else if (backendCode.equals("INVALID_PROGRAM_ID")) {
                    return new StartRecordingResponse(null, Error.INVALID_PROGRAM_ID);
                }
            } else if (backendCode.equals("SERVICE_UNSUBSCRIBED")) {
                return new StartRecordingResponse(null, Error.USER_NOT_SUBSCRIBE);
            }
            return new StartRecordingResponse(null, Error.UNKNOWN);
        }
        return new StartRecordingResponse(null, Error.UNKNOWN);
    }

    private final boolean recordableChannel(Channel channel) {
        int i = WhenMappings.$EnumSwitchMapping$0[this.pvrType.ordinal()];
        if (i == 1) {
            return channel.getCapabilities().getNetworkPVR();
        }
        if (i != 2) {
            return false;
        }
        return channel.getCapabilities().getHddPVR();
    }

    @Override // com.dotscreen.tv.common.recording.RecordingInterface
    public Completable stopRecording(TVRecording recording) {
        Intrinsics.checkParameterIsNotNull(recording, "recording");
        return this.pvrService.deleteRecording(recording);
    }

    @Override // com.dotscreen.tv.common.recording.RecordingInterface
    public Observable<TVRecording> getRecordingsForChannel(final Channel channel) {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        if (channel.getCapabilities().getHddPVR() || channel.getCapabilities().getNetworkPVR()) {
            Observable<TVRecording> observableFilter = getAllRecordings().filter(new Predicate<TVRecording>() { // from class: com.dotscreen.tv.common.recording.GlobalRecordingManager.getRecordingsForChannel.1
                @Override // io.reactivex.functions.Predicate
                public final boolean test(TVRecording it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return Intrinsics.areEqual(it.getChannel(), channel);
                }
            });
            Intrinsics.checkExpressionValueIsNotNull(observableFilter, "getAllRecordings().filte… == channel\n            }");
            return observableFilter;
        }
        Observable<TVRecording> observableEmpty = Observable.empty();
        Intrinsics.checkExpressionValueIsNotNull(observableEmpty, "Observable.empty()");
        return observableEmpty;
    }

    @Override // com.dotscreen.tv.common.recording.RecordingInterface
    public Observable<TVRecording> getAllRecordings() {
        return this.pvrService.recordings(PVRStorageMode.Network);
    }
}
