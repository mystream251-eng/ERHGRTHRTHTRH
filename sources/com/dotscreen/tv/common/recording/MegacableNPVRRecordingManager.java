package com.dotscreen.tv.common.recording;

import android.content.Context;
import com.dotscreen.bokit.model.TVRecording;
import com.dotscreen.bokit.services.PVRStorageMode;
import com.dotscreen.tv.common.bokit.MegacablePVRService;
import io.reactivex.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MegacableNPVRRecordingManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"Lcom/dotscreen/tv/common/recording/MegacableNPVRRecordingManager;", "Lcom/dotscreen/tv/common/recording/GlobalRecordingManager;", "context", "Landroid/content/Context;", "pvrService", "Lcom/dotscreen/tv/common/bokit/MegacablePVRService;", "(Landroid/content/Context;Lcom/dotscreen/tv/common/bokit/MegacablePVRService;)V", "getPvrService", "()Lcom/dotscreen/tv/common/bokit/MegacablePVRService;", "getAllRecordings", "Lio/reactivex/Observable;", "Lcom/dotscreen/bokit/model/TVRecording;", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class MegacableNPVRRecordingManager extends GlobalRecordingManager {
    private final MegacablePVRService pvrService;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MegacableNPVRRecordingManager(Context context, MegacablePVRService pvrService) {
        super(context, pvrService, PVRStorageMode.Network, null, 8, null);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(pvrService, "pvrService");
        this.pvrService = pvrService;
    }

    public final MegacablePVRService getPvrService() {
        return this.pvrService;
    }

    @Override // com.dotscreen.tv.common.recording.GlobalRecordingManager, com.dotscreen.tv.common.recording.RecordingInterface
    public Observable<TVRecording> getAllRecordings() {
        return this.pvrService.recordingTickets(PVRStorageMode.Network);
    }
}
