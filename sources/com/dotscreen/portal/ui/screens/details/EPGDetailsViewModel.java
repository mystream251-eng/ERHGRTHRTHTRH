package com.dotscreen.portal.ui.screens.details;

import android.content.Context;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.Lineup;
import com.dotscreen.bokit.model.Media;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.model.TVProgram;
import com.dotscreen.bokit.model.TVRecording;
import com.dotscreen.bokit.model.User;
import com.dotscreen.bokit.services.BOService;
import com.dotscreen.bokit.services.PVRService;
import com.dotscreen.bokit.services.PVRStorageMode;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.tv.common.bokit.CatchupMedia;
import com.dotscreen.tv.common.bokit.MegacablePVRService;
import com.dotscreen.tv.common.bokit.RightVChannel;
import com.dotscreen.tv.common.bokit.RightVUser;
import com.dotscreen.tv.common.bokit.StartOverMedia;
import com.dotscreen.tv.common.utils.RxViewModel;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Function6;
import io.reactivex.functions.Predicate;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: EPGDetailsViewModel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u000e\u000fB+\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\nJ\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0014R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000b¨\u0006\u0010"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/EPGDetailsViewModel;", "Lcom/dotscreen/tv/common/utils/RxViewModel;", "Lcom/dotscreen/portal/ui/screens/details/EPGDetailsViewModel$Result;", "context", "Landroid/content/Context;", "channelId", "", "startDate", "", "recordingId", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;)V", "Ljava/lang/Long;", "getSingle", "Lio/reactivex/Single;", "Factory", "Result", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class EPGDetailsViewModel extends RxViewModel<Result> {
    private final String channelId;
    private final Context context;
    private final String recordingId;
    private final Long startDate;

    public EPGDetailsViewModel(Context context, String str, Long l, String str2) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.channelId = str;
        this.startDate = l;
        this.recordingId = str2;
    }

    /* JADX INFO: compiled from: EPGDetailsViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u0006\u0010\u000e\u001a\u00020\u000b¢\u0006\u0002\u0010\u000fJ\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0005HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010&\u001a\u00020\u000bHÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\rHÆ\u0003J\t\u0010(\u001a\u00020\u000bHÆ\u0003JU\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000bHÆ\u0001J\u0013\u0010*\u001a\u00020\u000b2\b\u0010+\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010,\u001a\u00020-HÖ\u0001J\t\u0010.\u001a\u00020/HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u000e\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b \u0010!¨\u00060"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/EPGDetailsViewModel$Result;", "", "airing", "Lcom/dotscreen/bokit/model/TVAiring;", "program", "Lcom/dotscreen/bokit/model/TVProgram;", "catchupMedia", "Lcom/dotscreen/tv/common/bokit/CatchupMedia;", "startOverMedia", "Lcom/dotscreen/tv/common/bokit/StartOverMedia;", "isRecordable", "", "recording", "Lcom/dotscreen/bokit/model/TVRecording;", "channelIsInFavorites", "(Lcom/dotscreen/bokit/model/TVAiring;Lcom/dotscreen/bokit/model/TVProgram;Lcom/dotscreen/tv/common/bokit/CatchupMedia;Lcom/dotscreen/tv/common/bokit/StartOverMedia;ZLcom/dotscreen/bokit/model/TVRecording;Z)V", "getAiring", "()Lcom/dotscreen/bokit/model/TVAiring;", "setAiring", "(Lcom/dotscreen/bokit/model/TVAiring;)V", "getCatchupMedia", "()Lcom/dotscreen/tv/common/bokit/CatchupMedia;", "getChannelIsInFavorites", "()Z", "setChannelIsInFavorites", "(Z)V", "getProgram", "()Lcom/dotscreen/bokit/model/TVProgram;", "getRecording", "()Lcom/dotscreen/bokit/model/TVRecording;", "setRecording", "(Lcom/dotscreen/bokit/model/TVRecording;)V", "getStartOverMedia", "()Lcom/dotscreen/tv/common/bokit/StartOverMedia;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class Result {
        private TVAiring airing;
        private final CatchupMedia catchupMedia;
        private boolean channelIsInFavorites;
        private final boolean isRecordable;
        private final TVProgram program;
        private TVRecording recording;
        private final StartOverMedia startOverMedia;

        public static /* synthetic */ Result copy$default(Result result, TVAiring tVAiring, TVProgram tVProgram, CatchupMedia catchupMedia, StartOverMedia startOverMedia, boolean z, TVRecording tVRecording, boolean z2, int i, Object obj) {
            if ((i & 1) != 0) {
                tVAiring = result.airing;
            }
            if ((i & 2) != 0) {
                tVProgram = result.program;
            }
            TVProgram tVProgram2 = tVProgram;
            if ((i & 4) != 0) {
                catchupMedia = result.catchupMedia;
            }
            CatchupMedia catchupMedia2 = catchupMedia;
            if ((i & 8) != 0) {
                startOverMedia = result.startOverMedia;
            }
            StartOverMedia startOverMedia2 = startOverMedia;
            if ((i & 16) != 0) {
                z = result.isRecordable;
            }
            boolean z3 = z;
            if ((i & 32) != 0) {
                tVRecording = result.recording;
            }
            TVRecording tVRecording2 = tVRecording;
            if ((i & 64) != 0) {
                z2 = result.channelIsInFavorites;
            }
            return result.copy(tVAiring, tVProgram2, catchupMedia2, startOverMedia2, z3, tVRecording2, z2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final TVAiring getAiring() {
            return this.airing;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final TVProgram getProgram() {
            return this.program;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final CatchupMedia getCatchupMedia() {
            return this.catchupMedia;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final StartOverMedia getStartOverMedia() {
            return this.startOverMedia;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final boolean getIsRecordable() {
            return this.isRecordable;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final TVRecording getRecording() {
            return this.recording;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final boolean getChannelIsInFavorites() {
            return this.channelIsInFavorites;
        }

        public final Result copy(TVAiring airing, TVProgram program, CatchupMedia catchupMedia, StartOverMedia startOverMedia, boolean isRecordable, TVRecording recording, boolean channelIsInFavorites) {
            Intrinsics.checkParameterIsNotNull(airing, "airing");
            Intrinsics.checkParameterIsNotNull(program, "program");
            return new Result(airing, program, catchupMedia, startOverMedia, isRecordable, recording, channelIsInFavorites);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Result)) {
                return false;
            }
            Result result = (Result) other;
            return Intrinsics.areEqual(this.airing, result.airing) && Intrinsics.areEqual(this.program, result.program) && Intrinsics.areEqual(this.catchupMedia, result.catchupMedia) && Intrinsics.areEqual(this.startOverMedia, result.startOverMedia) && this.isRecordable == result.isRecordable && Intrinsics.areEqual(this.recording, result.recording) && this.channelIsInFavorites == result.channelIsInFavorites;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v10, types: [int] */
        /* JADX WARN: Type inference failed for: r0v14, types: [int] */
        /* JADX WARN: Type inference failed for: r2v10, types: [int] */
        /* JADX WARN: Type inference failed for: r2v12 */
        /* JADX WARN: Type inference failed for: r2v16 */
        /* JADX WARN: Type inference failed for: r3v0 */
        /* JADX WARN: Type inference failed for: r3v1, types: [int] */
        /* JADX WARN: Type inference failed for: r3v2 */
        public int hashCode() {
            TVAiring tVAiring = this.airing;
            int iHashCode = (tVAiring != null ? tVAiring.hashCode() : 0) * 31;
            TVProgram tVProgram = this.program;
            int iHashCode2 = (iHashCode + (tVProgram != null ? tVProgram.hashCode() : 0)) * 31;
            CatchupMedia catchupMedia = this.catchupMedia;
            int iHashCode3 = (iHashCode2 + (catchupMedia != null ? catchupMedia.hashCode() : 0)) * 31;
            StartOverMedia startOverMedia = this.startOverMedia;
            int iHashCode4 = (iHashCode3 + (startOverMedia != null ? startOverMedia.hashCode() : 0)) * 31;
            boolean z = this.isRecordable;
            ?? r2 = z;
            if (z) {
                r2 = 1;
            }
            int i = (iHashCode4 + r2) * 31;
            TVRecording tVRecording = this.recording;
            int iHashCode5 = (i + (tVRecording != null ? tVRecording.hashCode() : 0)) * 31;
            boolean z2 = this.channelIsInFavorites;
            return iHashCode5 + (z2 ? 1 : z2);
        }

        public String toString() {
            return "Result(airing=" + this.airing + ", program=" + this.program + ", catchupMedia=" + this.catchupMedia + ", startOverMedia=" + this.startOverMedia + ", isRecordable=" + this.isRecordable + ", recording=" + this.recording + ", channelIsInFavorites=" + this.channelIsInFavorites + ")";
        }

        public Result(TVAiring airing, TVProgram program, CatchupMedia catchupMedia, StartOverMedia startOverMedia, boolean z, TVRecording tVRecording, boolean z2) {
            Intrinsics.checkParameterIsNotNull(airing, "airing");
            Intrinsics.checkParameterIsNotNull(program, "program");
            this.airing = airing;
            this.program = program;
            this.catchupMedia = catchupMedia;
            this.startOverMedia = startOverMedia;
            this.isRecordable = z;
            this.recording = tVRecording;
            this.channelIsInFavorites = z2;
        }

        public final TVAiring getAiring() {
            return this.airing;
        }

        public final void setAiring(TVAiring tVAiring) {
            Intrinsics.checkParameterIsNotNull(tVAiring, "<set-?>");
            this.airing = tVAiring;
        }

        public final TVProgram getProgram() {
            return this.program;
        }

        public final CatchupMedia getCatchupMedia() {
            return this.catchupMedia;
        }

        public final StartOverMedia getStartOverMedia() {
            return this.startOverMedia;
        }

        public final boolean isRecordable() {
            return this.isRecordable;
        }

        public final TVRecording getRecording() {
            return this.recording;
        }

        public final void setRecording(TVRecording tVRecording) {
            this.recording = tVRecording;
        }

        public final boolean getChannelIsInFavorites() {
            return this.channelIsInFavorites;
        }

        public final void setChannelIsInFavorites(boolean z) {
            this.channelIsInFavorites = z;
        }
    }

    /* JADX INFO: compiled from: EPGDetailsViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\tJ'\u0010\u000b\u001a\u0002H\f\"\n\b\u0000\u0010\f*\u0004\u0018\u00010\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\f0\u000fH\u0016¢\u0006\u0002\u0010\u0010R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\n¨\u0006\u0011"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/EPGDetailsViewModel$Factory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "context", "Landroid/content/Context;", "channelId", "", "startDate", "", "recordingId", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;)V", "Ljava/lang/Long;", "create", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Factory implements ViewModelProvider.Factory {
        private final String channelId;
        private final Context context;
        private final String recordingId;
        private final Long startDate;

        public Factory(Context context, String str, Long l, String str2) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            this.context = context;
            this.channelId = str;
            this.startDate = l;
            this.recordingId = str2;
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public <T extends ViewModel> T create(Class<T> modelClass) {
            Intrinsics.checkParameterIsNotNull(modelClass, "modelClass");
            return new EPGDetailsViewModel(this.context, this.channelId, this.startDate, this.recordingId);
        }
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [T, com.dotscreen.bokit.model.Channel] */
    @Override // com.dotscreen.tv.common.utils.RxViewModel
    protected Single<Result> getSingle() {
        final BOService bOService = PortalApplication.INSTANCE.getSingleton(this.context).getBOService();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (Channel) 0;
        if (this.channelId != null && this.startDate != null) {
            Single<Result> singleFirstOrError = PortalApplication.INSTANCE.getSingleton(this.context).getCurrentLineup().flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsViewModel.getSingle.1
                @Override // io.reactivex.functions.Function
                public final Observable<Channel> apply(Lineup lineup) {
                    Intrinsics.checkParameterIsNotNull(lineup, "lineup");
                    return lineup.getItems();
                }
            }).filter(new Predicate<Channel>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsViewModel.getSingle.2
                @Override // io.reactivex.functions.Predicate
                public final boolean test(Channel channel) {
                    Intrinsics.checkParameterIsNotNull(channel, "channel");
                    return Intrinsics.areEqual(channel.getId(), EPGDetailsViewModel.this.channelId);
                }
            }).flatMapMaybe(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsViewModel.getSingle.3
                /* JADX WARN: Multi-variable type inference failed */
                @Override // io.reactivex.functions.Function
                public final Maybe<TVAiring> apply(Channel channel) {
                    Intrinsics.checkParameterIsNotNull(channel, "channel");
                    objectRef.element = channel;
                    return channel.airing(new Date(EPGDetailsViewModel.this.startDate.longValue()));
                }
            }).flatMapSingle(new AnonymousClass4(bOService, objectRef)).firstOrError();
            Intrinsics.checkExpressionValueIsNotNull(singleFirstOrError, "PortalApplication.getSin…         }.firstOrError()");
            return singleFirstOrError;
        }
        if (this.recordingId != null) {
            PVRService pvrService = PortalApplication.INSTANCE.getSingleton(this.context).getBOService().getPvrService();
            if (pvrService == null) {
                Intrinsics.throwNpe();
            }
            if (pvrService == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacablePVRService");
            }
            Single<Result> singleFirstOrError2 = ((MegacablePVRService) pvrService).recordings(PVRStorageMode.Network).filter(new Predicate<TVRecording>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsViewModel.getSingle.5
                @Override // io.reactivex.functions.Predicate
                public final boolean test(TVRecording it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return Intrinsics.areEqual(it.getId(), EPGDetailsViewModel.this.recordingId);
                }
            }).flatMapSingle((Function) new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsViewModel.getSingle.6
                @Override // io.reactivex.functions.Function
                public final Single<Result> apply(final TVRecording recording) {
                    Intrinsics.checkParameterIsNotNull(recording, "recording");
                    return recording.getTvProgram().flatMapSingle(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsViewModel.getSingle.6.1
                        @Override // io.reactivex.functions.Function
                        public final Single<Result> apply(final TVProgram tvProgram) {
                            Intrinsics.checkParameterIsNotNull(tvProgram, "tvProgram");
                            UserService userService = bOService.getUserService();
                            if (userService == null) {
                                Intrinsics.throwNpe();
                            }
                            return userService.getActiveUser().map(new Function<T, R>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsViewModel.getSingle.6.1.1
                                @Override // io.reactivex.functions.Function
                                public final RightVUser apply(User it) {
                                    Intrinsics.checkParameterIsNotNull(it, "it");
                                    return (RightVUser) it;
                                }
                            }).flatMapSingle(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsViewModel.getSingle.6.1.2
                                @Override // io.reactivex.functions.Function
                                public final Single<Result> apply(RightVUser user) {
                                    Intrinsics.checkParameterIsNotNull(user, "user");
                                    Single singleJust = Single.just(recording);
                                    Single singleJust2 = Single.just(tvProgram);
                                    Channel channel = recording.getChannel();
                                    if (channel != null) {
                                        return Single.zip(singleJust, singleJust2, user.isChannelInFavorites((RightVChannel) channel).onErrorReturnItem(false), new Function3<TVRecording, TVProgram, Boolean, Result>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsViewModel.getSingle.6.1.2.1
                                            @Override // io.reactivex.functions.Function3
                                            public final Result apply(TVRecording recording2, TVProgram program, Boolean channelIsInFavorites) {
                                                Intrinsics.checkParameterIsNotNull(recording2, "recording");
                                                Intrinsics.checkParameterIsNotNull(program, "program");
                                                Intrinsics.checkParameterIsNotNull(channelIsInFavorites, "channelIsInFavorites");
                                                return new Result(recording2, program, null, null, false, recording2, channelIsInFavorites.booleanValue());
                                            }
                                        });
                                    }
                                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVChannel");
                                }
                            });
                        }
                    });
                }
            }).firstOrError();
            Intrinsics.checkExpressionValueIsNotNull(singleFirstOrError2, "(PortalApplication.getSi…         }.firstOrError()");
            return singleFirstOrError2;
        }
        Single<Result> singleError = Single.error(new Exception("Invalid data"));
        Intrinsics.checkExpressionValueIsNotNull(singleError, "Single.error(Exception(\"Invalid data\"))");
        return singleError;
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.ui.screens.details.EPGDetailsViewModel$getSingle$4, reason: invalid class name */
    /* JADX INFO: compiled from: EPGDetailsViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0010\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "Lcom/dotscreen/portal/ui/screens/details/EPGDetailsViewModel$Result;", "kotlin.jvm.PlatformType", "airing", "Lcom/dotscreen/bokit/model/TVAiring;", "apply"}, k = 3, mv = {1, 1, 16})
    static final class AnonymousClass4<T, R> implements Function<T, SingleSource<? extends R>> {
        final /* synthetic */ BOService $boService;
        final /* synthetic */ Ref.ObjectRef $currentChannel;

        AnonymousClass4(BOService bOService, Ref.ObjectRef objectRef) {
            this.$boService = bOService;
            this.$currentChannel = objectRef;
        }

        /* JADX INFO: renamed from: com.dotscreen.portal.ui.screens.details.EPGDetailsViewModel$getSingle$4$1, reason: invalid class name */
        /* JADX INFO: compiled from: EPGDetailsViewModel.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0010\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "Lcom/dotscreen/portal/ui/screens/details/EPGDetailsViewModel$Result;", "kotlin.jvm.PlatformType", "tvProgram", "Lcom/dotscreen/bokit/model/TVProgram;", "apply"}, k = 3, mv = {1, 1, 16})
        static final class AnonymousClass1<T, R> implements Function<T, SingleSource<? extends R>> {
            final /* synthetic */ TVAiring $airing;

            AnonymousClass1(TVAiring tVAiring) {
                this.$airing = tVAiring;
            }

            @Override // io.reactivex.functions.Function
            public final Single<Result> apply(final TVProgram tvProgram) {
                Intrinsics.checkParameterIsNotNull(tvProgram, "tvProgram");
                UserService userService = AnonymousClass4.this.$boService.getUserService();
                if (userService == null) {
                    Intrinsics.throwNpe();
                }
                return userService.getActiveUser().map(new Function<T, R>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsViewModel.getSingle.4.1.1
                    @Override // io.reactivex.functions.Function
                    public final RightVUser apply(User it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        return (RightVUser) it;
                    }
                }).flatMapSingle(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsViewModel.getSingle.4.1.2
                    @Override // io.reactivex.functions.Function
                    public final Single<Result> apply(RightVUser user) {
                        Intrinsics.checkParameterIsNotNull(user, "user");
                        Single singleJust = Single.just(AnonymousClass1.this.$airing);
                        Single singleJust2 = Single.just(tvProgram);
                        Single<List<Media>> singleOnErrorReturnItem = tvProgram.getRelatedCatchupMedia().toObservable().toList().onErrorReturnItem(CollectionsKt.emptyList());
                        Single<List<Media>> singleOnErrorReturnItem2 = AnonymousClass1.this.$airing.getStartOverMedia().toObservable().toList().onErrorReturnItem(CollectionsKt.emptyList());
                        Channel channel = (Channel) AnonymousClass4.this.$currentChannel.element;
                        if (channel == null) {
                            Intrinsics.throwNpe();
                        }
                        Single<R> singleFlatMap = Single.just(Boolean.valueOf(channel.getCapabilities().getNetworkPVR())).flatMap(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsViewModel.getSingle.4.1.2.1
                            /* JADX WARN: Code duplicated, block: B:9:0x0044  */
                            @Override // io.reactivex.functions.Function
                            public final Single<Pair<Boolean, List<TVRecording>>> apply(Boolean recordable) {
                                Maybe<TVRecording> maybeEmpty;
                                Intrinsics.checkParameterIsNotNull(recordable, "recordable");
                                if (recordable.booleanValue()) {
                                    Single<Boolean> singleOnErrorReturnItem3 = AnonymousClass1.this.$airing.isRecordable(PVRStorageMode.Network).onErrorReturnItem(false);
                                    PVRService pvrService = AnonymousClass4.this.$boService.getPvrService();
                                    if (pvrService != null) {
                                        PVRStorageMode pVRStorageMode = PVRStorageMode.Network;
                                        TVAiring airing = AnonymousClass1.this.$airing;
                                        Intrinsics.checkExpressionValueIsNotNull(airing, "airing");
                                        maybeEmpty = pvrService.recordingForAiring(pVRStorageMode, airing);
                                        if (maybeEmpty == null) {
                                            maybeEmpty = Maybe.empty();
                                            Intrinsics.checkExpressionValueIsNotNull(maybeEmpty, "Maybe.empty()");
                                        }
                                    } else {
                                        maybeEmpty = Maybe.empty();
                                        Intrinsics.checkExpressionValueIsNotNull(maybeEmpty, "Maybe.empty()");
                                    }
                                    return Single.zip(singleOnErrorReturnItem3, maybeEmpty.toObservable().toList().onErrorReturnItem(CollectionsKt.emptyList()), new BiFunction<Boolean, List<TVRecording>, Pair<? extends Boolean, ? extends List<? extends TVRecording>>>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsViewModel.getSingle.4.1.2.1.1
                                        @Override // io.reactivex.functions.BiFunction
                                        public /* bridge */ /* synthetic */ Pair<? extends Boolean, ? extends List<? extends TVRecording>> apply(Boolean bool, List<TVRecording> list) {
                                            return apply(bool.booleanValue(), list);
                                        }

                                        public final Pair<Boolean, List<TVRecording>> apply(boolean z, List<? extends TVRecording> recordings) {
                                            Intrinsics.checkParameterIsNotNull(recordings, "recordings");
                                            return new Pair<>(Boolean.valueOf(z), recordings);
                                        }
                                    });
                                }
                                return Single.just(new Pair(false, CollectionsKt.emptyList()));
                            }
                        });
                        Channel channel2 = AnonymousClass1.this.$airing.getChannel();
                        if (channel2 != null) {
                            return Single.zip(singleJust, singleJust2, singleOnErrorReturnItem, singleOnErrorReturnItem2, singleFlatMap, user.isChannelInFavorites((RightVChannel) channel2).onErrorReturnItem(false), new Function6<TVAiring, TVProgram, List<? extends Media>, List<? extends Media>, Pair<? extends Boolean, ? extends List<? extends TVRecording>>, Boolean, Result>() { // from class: com.dotscreen.portal.ui.screens.details.EPGDetailsViewModel.getSingle.4.1.2.2
                                @Override // io.reactivex.functions.Function6
                                public /* bridge */ /* synthetic */ Result apply(TVAiring tVAiring, TVProgram tVProgram, List<? extends Media> list, List<? extends Media> list2, Pair<? extends Boolean, ? extends List<? extends TVRecording>> pair, Boolean bool) {
                                    return apply2(tVAiring, tVProgram, list, list2, (Pair<Boolean, ? extends List<? extends TVRecording>>) pair, bool);
                                }

                                /* JADX INFO: renamed from: apply, reason: avoid collision after fix types in other method */
                                public final Result apply2(TVAiring airing, TVProgram program, List<? extends Media> catchup, List<? extends Media> startOver, Pair<Boolean, ? extends List<? extends TVRecording>> recordingPair, Boolean channelIsInFavorites) {
                                    CatchupMedia catchupMedia;
                                    StartOverMedia startOverMedia;
                                    Intrinsics.checkParameterIsNotNull(airing, "airing");
                                    Intrinsics.checkParameterIsNotNull(program, "program");
                                    Intrinsics.checkParameterIsNotNull(catchup, "catchup");
                                    Intrinsics.checkParameterIsNotNull(startOver, "startOver");
                                    Intrinsics.checkParameterIsNotNull(recordingPair, "recordingPair");
                                    Intrinsics.checkParameterIsNotNull(channelIsInFavorites, "channelIsInFavorites");
                                    List<? extends TVRecording> second = recordingPair.getSecond();
                                    if (!catchup.isEmpty()) {
                                        Media media = catchup.get(0);
                                        if (media == null) {
                                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.CatchupMedia");
                                        }
                                        catchupMedia = (CatchupMedia) media;
                                    } else {
                                        catchupMedia = null;
                                    }
                                    if (!startOver.isEmpty()) {
                                        Media media2 = startOver.get(0);
                                        if (media2 == null) {
                                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.StartOverMedia");
                                        }
                                        startOverMedia = (StartOverMedia) media2;
                                    } else {
                                        startOverMedia = null;
                                    }
                                    return new Result(airing, program, catchupMedia, startOverMedia, recordingPair.getFirst().booleanValue(), second.isEmpty() ^ true ? second.get(0) : null, channelIsInFavorites.booleanValue());
                                }
                            });
                        }
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVChannel");
                    }
                });
            }
        }

        @Override // io.reactivex.functions.Function
        public final Single<Result> apply(TVAiring airing) {
            Intrinsics.checkParameterIsNotNull(airing, "airing");
            return airing.getTvProgram().flatMapSingle(new AnonymousClass1(airing));
        }
    }
}
