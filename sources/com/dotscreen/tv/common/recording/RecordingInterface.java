package com.dotscreen.tv.common.recording;

import android.database.ContentObserver;
import android.util.Log;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.model.TVRecording;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GlobalRecordingManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u00062\u0006\u0010\n\u001a\u00020\u000bH&J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0004H\u0016J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J@\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u000e2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u0019\u001a\u00020\u000f2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001dH&J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010\u0011\u001a\u00020\u0004H&¨\u0006!"}, d2 = {"Lcom/dotscreen/tv/common/recording/RecordingInterface;", "", "getAllRecordings", "Lio/reactivex/Observable;", "Lcom/dotscreen/bokit/model/TVRecording;", "getLiveRecordingForChannel", "Lio/reactivex/Maybe;", "channel", "Lcom/dotscreen/bokit/model/Channel;", "getRecording", "recordingId", "", "getRecordingsForChannel", "hasLiveRecording", "Lio/reactivex/Single;", "", "isLive", "recording", "isRecordingLive", "registerEvent", "", "callback", "Landroid/database/ContentObserver;", "startRecording", "Lcom/dotscreen/tv/common/recording/StartRecordingResponse;", "recordCurrentProgram", "program", "Lcom/dotscreen/bokit/model/TVAiring;", "startDate", "Ljava/util/Date;", "endDate", "stopRecording", "Lio/reactivex/Completable;", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public interface RecordingInterface {
    Observable<TVRecording> getAllRecordings();

    Maybe<TVRecording> getLiveRecordingForChannel(Channel channel);

    Maybe<TVRecording> getRecording(String recordingId);

    Observable<TVRecording> getRecordingsForChannel(Channel channel);

    Single<Boolean> hasLiveRecording(Channel channel);

    boolean isLive(TVRecording recording);

    Single<Boolean> isRecordingLive(Channel channel);

    void registerEvent(ContentObserver callback);

    Single<StartRecordingResponse> startRecording(Channel channel, boolean recordCurrentProgram, TVAiring program, Date startDate, Date endDate);

    Completable stopRecording(TVRecording recording);

    /* JADX INFO: compiled from: GlobalRecordingManager.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public static final class DefaultImpls {
        public static void registerEvent(RecordingInterface recordingInterface, ContentObserver contentObserver) {
        }

        public static /* synthetic */ Single startRecording$default(RecordingInterface recordingInterface, Channel channel, boolean z, TVAiring tVAiring, Date date, Date date2, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: startRecording");
            }
            if ((i & 2) != 0) {
                z = false;
            }
            return recordingInterface.startRecording(channel, z, tVAiring, date, date2);
        }

        public static Maybe<TVRecording> getLiveRecordingForChannel(final RecordingInterface recordingInterface, final Channel channel) {
            Intrinsics.checkParameterIsNotNull(channel, "channel");
            Maybe<TVRecording> maybeFirstElement = recordingInterface.getRecordingsForChannel(channel).filter(new Predicate<TVRecording>() { // from class: com.dotscreen.tv.common.recording.RecordingInterface.getLiveRecordingForChannel.1
                @Override // io.reactivex.functions.Predicate
                public final boolean test(TVRecording it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Log.v("RecordManager", "Record " + it + " | for channel " + channel);
                    return RecordingInterface.this.isLive(it);
                }
            }).firstElement();
            Intrinsics.checkExpressionValueIsNotNull(maybeFirstElement, "getRecordingsForChannel(…\n        }.firstElement()");
            return maybeFirstElement;
        }

        public static Single<Boolean> hasLiveRecording(RecordingInterface recordingInterface, Channel channel) {
            Intrinsics.checkParameterIsNotNull(channel, "channel");
            Single map = recordingInterface.getLiveRecordingForChannel(channel).isEmpty().map(new Function<T, R>() { // from class: com.dotscreen.tv.common.recording.RecordingInterface.hasLiveRecording.1
                @Override // io.reactivex.functions.Function
                public /* bridge */ /* synthetic */ Object apply(Object obj) {
                    return Boolean.valueOf(apply((Boolean) obj));
                }

                public final boolean apply(Boolean it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return !it.booleanValue();
                }
            });
            Intrinsics.checkExpressionValueIsNotNull(map, "getLiveRecordingForChann…nnel).isEmpty.map { !it }");
            return map;
        }

        public static boolean isLive(RecordingInterface recordingInterface, TVRecording recording) {
            Intrinsics.checkParameterIsNotNull(recording, "recording");
            Date date = new Date();
            Log.v("RecordManager", "IsLive ? start: " + recording.getStartDate() + " end: " + recording.getEndDate() + " now: " + date);
            return recording.getStartDate().compareTo(date) <= 0 && recording.getEndDate().compareTo(date) > 0;
        }

        public static Single<Boolean> isRecordingLive(final RecordingInterface recordingInterface, Channel channel) {
            Intrinsics.checkParameterIsNotNull(channel, "channel");
            Single<Boolean> single = recordingInterface.getLiveRecordingForChannel(channel).map((Function) new Function<T, R>() { // from class: com.dotscreen.tv.common.recording.RecordingInterface.isRecordingLive.1
                @Override // io.reactivex.functions.Function
                public /* bridge */ /* synthetic */ Object apply(Object obj) {
                    return Boolean.valueOf(apply((TVRecording) obj));
                }

                public final boolean apply(TVRecording it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return RecordingInterface.this.isLive(it);
                }
            }).toSingle();
            Intrinsics.checkExpressionValueIsNotNull(single, "getLiveRecordingForChann…{ isLive(it) }.toSingle()");
            return single;
        }
    }
}
