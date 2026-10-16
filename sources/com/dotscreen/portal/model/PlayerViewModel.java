package com.dotscreen.portal.model;

import android.os.CountDownTimer;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.dotscreen.bokit.model.BrowsableItem;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.Content;
import com.dotscreen.bokit.model.Episode;
import com.dotscreen.bokit.model.Lineup;
import com.dotscreen.bokit.model.Media;
import com.dotscreen.bokit.model.PlaybackInfo;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.model.TVProgram;
import com.dotscreen.bokit.model.TVRecording;
import com.dotscreen.bokit.services.PVRService;
import com.dotscreen.bokit.services.PVRStorageMode;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.bokit.services.VODCatalogService;
import com.dotscreen.farkit.tools.LanguagePreferences;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.PortalApplicationSingletons;
import com.dotscreen.tv.common.bokit.CatchupMedia;
import com.dotscreen.tv.common.bokit.MegacableUserService;
import com.dotscreen.tv.common.bokit.MegacableUtilsKt;
import com.dotscreen.tv.common.bokit.MegacableVODCatalogService;
import com.dotscreen.tv.common.bokit.NPVRMedia;
import com.dotscreen.tv.common.bokit.RightVPlaybackInfo;
import com.dotscreen.tv.common.bokit.RightVUser;
import com.dotscreen.tv.common.bokit.StartOverMedia;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.viaccessorca.analytics.fma.FmaDatabaseHelper;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: PlayerViewModel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b%\u0018\u00002\u00020\u0001Bu\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014¢\u0006\u0002\u0010\u0015J\u0012\u0010r\u001a\u00030\u009a\u00012\u0007\u0010\u009b\u0001\u001a\u00020-H\u0002J\u001c\u0010\u009c\u0001\u001a\u00030\u009d\u00012\u0007\u0010\u009e\u0001\u001a\u00020\u00062\u0007\u0010\u009f\u0001\u001a\u00020\u0006H\u0002J\b\u0010 \u0001\u001a\u00030\u009a\u0001J\u0010\u0010¡\u0001\u001a\u00020\u000b2\u0007\u0010¢\u0001\u001a\u00020\u000bJ\b\u0010£\u0001\u001a\u00030\u009a\u0001J\b\u0010¤\u0001\u001a\u00030\u009a\u0001J\n\u0010¥\u0001\u001a\u00030\u009a\u0001H\u0002J\u001d\u0010¦\u0001\u001a\u00030\u009a\u00012\u0007\u0010§\u0001\u001a\u00020\u0003H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010¨\u0001J\n\u0010©\u0001\u001a\u00030\u009a\u0001H\u0002J\n\u0010ª\u0001\u001a\u00030\u009a\u0001H\u0014J\b\u0010«\u0001\u001a\u00030\u009a\u0001J\b\u0010¬\u0001\u001a\u00030\u009a\u0001J\u001a\u0010\u00ad\u0001\u001a\u00030\u009a\u00012\u0007\u0010\u009e\u0001\u001a\u00020\u00062\u0007\u0010\u009f\u0001\u001a\u00020\u0006J\t\u0010®\u0001\u001a\u00020\u000bH\u0002J\u0007\u0010¯\u0001\u001a\u00020\u000bJ\t\u0010°\u0001\u001a\u00020\u000bH\u0002J\u0011\u0010±\u0001\u001a\u00030\u009a\u00012\u0007\u0010²\u0001\u001a\u00020\u000bJ\u0011\u0010³\u0001\u001a\u00030\u009a\u00012\u0007\u0010²\u0001\u001a\u00020\u000eJ\u0011\u0010´\u0001\u001a\u00030\u009a\u00012\u0007\u0010µ\u0001\u001a\u00020\u000bJ\u0007\u0010¶\u0001\u001a\u00020\u000bJ\b\u0010·\u0001\u001a\u00030\u009a\u0001J\u001e\u0010¸\u0001\u001a\u00030\u009a\u00012\t\u0010¹\u0001\u001a\u0004\u0018\u00010\u00032\t\u0010º\u0001\u001a\u0004\u0018\u00010\u0003J\n\u0010»\u0001\u001a\u00030\u009a\u0001H\u0002J\u0013\u0010¼\u0001\u001a\u00030\u009a\u00012\t\u0010½\u0001\u001a\u0004\u0018\u00010\u0003J\u0013\u0010¾\u0001\u001a\u00030\u009a\u00012\t\u0010½\u0001\u001a\u0004\u0018\u00010\u0003J\u0011\u0010¿\u0001\u001a\u00030\u009a\u00012\u0007\u0010À\u0001\u001a\u00020%J\b\u0010\u0097\u0001\u001a\u00030\u009a\u0001J\b\u0010Á\u0001\u001a\u00030\u009a\u0001R\u000e\u0010\u0016\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00030\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00060\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010'\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00060\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00060\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010*\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020-0\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010.\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010/\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00100\u001a\b\u0012\u0004\u0012\u00020\u00030\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u00101\u001a\u00020\u000b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b4\u00105\u001a\u0004\b2\u00103R\u001b\u00106\u001a\u00020\u000b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b8\u00105\u001a\u0004\b7\u00103R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010;\u001a\b\u0012\u0004\u0012\u00020\u000b0<8F¢\u0006\u0006\u001a\u0004\b=\u0010>R\u0017\u0010?\u001a\b\u0012\u0004\u0012\u00020\u000b0<8F¢\u0006\u0006\u001a\u0004\b@\u0010>R\u0014\u0010A\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bB\u0010CR\u0011\u0010D\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\bE\u0010CR\u0017\u0010F\u001a\b\u0012\u0004\u0012\u00020\u001b0<8F¢\u0006\u0006\u001a\u0004\bG\u0010>R\u0019\u0010H\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030<8F¢\u0006\u0006\u001a\u0004\bI\u0010>R\u0017\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00030<8F¢\u0006\u0006\u001a\u0004\bK\u0010>R\u0017\u0010L\u001a\b\u0012\u0004\u0012\u00020\u001f0<8F¢\u0006\u0006\u001a\u0004\bM\u0010>R\u0017\u0010N\u001a\b\u0012\u0004\u0012\u00020!0<8F¢\u0006\u0006\u001a\u0004\bO\u0010>R\u0017\u0010P\u001a\b\u0012\u0004\u0012\u00020#0<8F¢\u0006\u0006\u001a\u0004\bQ\u0010>R\u0017\u0010R\u001a\b\u0012\u0004\u0012\u00020%0<8F¢\u0006\u0006\u001a\u0004\bS\u0010>R\u0017\u0010T\u001a\b\u0012\u0004\u0012\u00020\u00060<8F¢\u0006\u0006\u001a\u0004\bU\u0010>R\u0019\u0010V\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030<8F¢\u0006\u0006\u001a\u0004\bW\u0010>R\u0017\u0010X\u001a\b\u0012\u0004\u0012\u00020\u00060<8F¢\u0006\u0006\u001a\u0004\bY\u0010>R\u0010\u0010Z\u001a\u0004\u0018\u00010[X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\\\u001a\u0004\u0018\u00010[X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010]\u001a\u0004\u0018\u00010[X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010^\u001a\u00020_X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010`\u001a\b\u0012\u0004\u0012\u00020\u00060<8F¢\u0006\u0006\u001a\u0004\ba\u0010>R\u0011\u0010b\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\bc\u00103R\u000e\u0010d\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010e\u001a\u00020\u000bX\u0086D¢\u0006\b\n\u0000\u001a\u0004\bf\u00103R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\n\n\u0002\u0010i\u001a\u0004\bg\u0010hR\u001e\u0010k\u001a\u00020\u000e2\u0006\u0010j\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bl\u0010mR\u0017\u0010n\u001a\b\u0012\u0004\u0012\u00020\u000b0<8F¢\u0006\u0006\u001a\u0004\bo\u0010>R\u001c\u0010p\u001a\u0004\u0018\u00010qX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\br\u0010s\"\u0004\bt\u0010uR\u0017\u0010v\u001a\b\u0012\u0004\u0012\u00020\u000b0<8F¢\u0006\u0006\u001a\u0004\bw\u0010>R\u0016\u0010x\u001a\u0004\u0018\u00010-8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\by\u0010zR\u0017\u0010{\u001a\b\u0012\u0004\u0012\u00020-0<8F¢\u0006\u0006\u001a\u0004\b|\u0010>R!\u0010}\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0013\n\u0003\u0010\u0082\u0001\u001a\u0004\b~\u0010\u007f\"\u0006\b\u0080\u0001\u0010\u0081\u0001R\u0019\u0010\u0083\u0001\u001a\b\u0012\u0004\u0012\u00020\u000e0<8F¢\u0006\u0007\u001a\u0005\b\u0084\u0001\u0010>R\u0019\u0010\u0085\u0001\u001a\b\u0012\u0004\u0012\u00020\u000b0<8F¢\u0006\u0007\u001a\u0005\b\u0086\u0001\u0010>R\u0012\u0010\f\u001a\u00020\u000b¢\u0006\t\n\u0000\u001a\u0005\b\u0087\u0001\u00103R\u0014\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0088\u0001\u0010:R \u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0089\u0001\u0010\u008a\u0001\"\u0006\b\u008b\u0001\u0010\u008c\u0001R\u001d\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u0011\n\u0000\u001a\u0005\b\u008d\u0001\u00103\"\u0006\b\u008e\u0001\u0010\u008f\u0001R \u0010\u0090\u0001\u001a\u00020\u000b2\u0006\u0010j\u001a\u00020\u000b@BX\u0086\u000e¢\u0006\t\n\u0000\u001a\u0005\b\u0091\u0001\u00103R\u0010\u0010\u0092\u0001\u001a\u00030\u0093\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0094\u0001\u001a\b\u0012\u0004\u0012\u00020\u00030<8F¢\u0006\u0007\u001a\u0005\b\u0095\u0001\u0010>R\u000f\u0010\u0096\u0001\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u0097\u0001\u001a\u00020\u000b2\u0006\u0010j\u001a\u00020\u000b@BX\u0086\u000e¢\u0006\t\n\u0000\u001a\u0005\b\u0098\u0001\u00103R\u000f\u0010\u0099\u0001\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006Â\u0001"}, d2 = {"Lcom/dotscreen/portal/model/PlayerViewModel;", "Landroidx/lifecycle/ViewModel;", "contendId", "", "channelId", FmaDatabaseHelper.CONST_TIMESTAMP, "", "recordingId", "replayType", "Lcom/dotscreen/portal/model/PlaybackReplayType;", ToolboxAnalyticsModuleKt.EVENT_RESUME, "", "playingTrailer", "launchMode", "", "initialAudioLanguage", "initialSubtitleLanguage", "languagePreferences", "Lcom/dotscreen/farkit/tools/LanguagePreferences;", "applicationSingleton", "Lcom/dotscreen/portal/PortalApplicationSingletons;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Lcom/dotscreen/portal/model/PlaybackReplayType;ZZLjava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Lcom/dotscreen/farkit/tools/LanguagePreferences;Lcom/dotscreen/portal/PortalApplicationSingletons;)V", "TAG", "_countDownFinished", "Landroidx/lifecycle/MutableLiveData;", "_countDownIsOn", "_currentAiringLiveData", "Lcom/dotscreen/bokit/model/TVAiring;", "_currentAudioLiveData", "_currentContentIdLiveData", "_currentErrorLiveData", "", "_currentMediaLiveData", "Lcom/dotscreen/bokit/model/BrowsableItem;", "_currentPlaybackInfoLiveData", "Lcom/dotscreen/tv/common/bokit/RightVPlaybackInfo;", "_currentPlaybackStateLiveData", "Lcom/dotscreen/portal/model/PlaybackState;", "_currentProgramTimestampLiveData", "_currentSubtitleLiveData", "_currentTime", "_epgDurationLiveData", "_nextAiringLoading", "_nextEpisodeLoaded", "_nextReplayMedia", "Lcom/dotscreen/bokit/model/Media;", "_onAdsCanBeSkippedLiveData", "_onAdsDetectedLiveData", "_trailerTitleLiveData", "autoPlay", "getAutoPlay", "()Z", "autoPlay$delegate", "Lkotlin/Lazy;", "catchupPlayback", "getCatchupPlayback", "catchupPlayback$delegate", "getChannelId", "()Ljava/lang/String;", "countDownFinished", "Landroidx/lifecycle/LiveData;", "getCountDownFinished", "()Landroidx/lifecycle/LiveData;", "countDownIsOn", "getCountDownIsOn", "countdownTime", "getCountdownTime", "()J", "countdownTrigger", "getCountdownTrigger", "currentAiringLiveData", "getCurrentAiringLiveData", "currentAudioLiveData", "getCurrentAudioLiveData", "currentContentIdLiveData", "getCurrentContentIdLiveData", "currentErrorLiveData", "getCurrentErrorLiveData", "currentMediaLiveData", "getCurrentMediaLiveData", "currentPlaybackInfoLiveData", "getCurrentPlaybackInfoLiveData", "currentPlaybackStateLiveData", "getCurrentPlaybackStateLiveData", "currentProgramTimestampLiveData", "getCurrentProgramTimestampLiveData", "currentSubtitleLiveData", "getCurrentSubtitleLiveData", "currentTime", "getCurrentTime", "disposableCurrentMedia", "Lio/reactivex/disposables/Disposable;", "disposableCurrentPlaybackInfo", "disposableNextMedia", "disposables", "Lio/reactivex/disposables/CompositeDisposable;", "epgDurationLiveData", "getEpgDurationLiveData", "hasNextMediaToPlay", "getHasNextMediaToPlay", "hasTriedToLoadNextAiring", "hideStatusOnScreen", "getHideStatusOnScreen", "getLaunchMode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "<set-?>", "newEpisodeStartedCount", "getNewEpisodeStartedCount", "()I", "nextAiringLoading", "getNextAiringLoading", "nextEpisode", "Lcom/dotscreen/bokit/model/Episode;", "getNextEpisode", "()Lcom/dotscreen/bokit/model/Episode;", "setNextEpisode", "(Lcom/dotscreen/bokit/model/Episode;)V", "nextEpisodeLoaded", "getNextEpisodeLoaded", "nextMediaToPlay", "getNextMediaToPlay", "()Lcom/dotscreen/bokit/model/Media;", "nextReplayMedia", "getNextReplayMedia", "nextTVAiringTimeStamp", "getNextTVAiringTimeStamp", "()Ljava/lang/Long;", "setNextTVAiringTimeStamp", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "onAdsCanBeSkippedLiveData", "getOnAdsCanBeSkippedLiveData", "onAdsDetectedLiveData", "getOnAdsDetectedLiveData", "getPlayingTrailer", "getRecordingId", "getReplayType", "()Lcom/dotscreen/portal/model/PlaybackReplayType;", "setReplayType", "(Lcom/dotscreen/portal/model/PlaybackReplayType;)V", "getResume", "setResume", "(Z)V", "subtitleHasBeenSet", "getSubtitleHasBeenSet", "timer", "Landroid/os/CountDownTimer;", "trailerTitleLiveData", "getTrailerTitleLiveData", "updateReplayTypeForNextMedia", "userInteracted", "getUserInteracted", "userInterruptedCountDown", "", "media", "getPercentagePosition", "", "currentPosition", "duration", "invalidateCurrentPlaybackInfo", "isAllowedToPlay", "checkNextMedia", "loadMedia", "loadNextAiring", "loadPlaybackInfo", "loadPreferredLanguages", "currentChannelId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "newMediaStarted", "onCleared", "resetNewEpisodeStartedCount", "resetUserInteraction", "saveMediaPosition", "selectNextEpisode", "selectNextMedia", "selectNextProgram", "setAdsState", "value", "setCanSkipState", "setSubtitle", "isSet", "shouldDisplayStillAlive", "startCountDown", "storePreferredLanguage", "audioLanguage", "subtitleLanguage", "switchToStov", "updateCurrentAudioTrack", "trackName", "updateCurrentSubtitleTrack", "updatePlaybackState", "newState", "userInterruptedCountdown", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class PlayerViewModel extends ViewModel {
    private final String TAG;
    private final MutableLiveData<Boolean> _countDownFinished;
    private MutableLiveData<Boolean> _countDownIsOn;
    private final MutableLiveData<TVAiring> _currentAiringLiveData;
    private final MutableLiveData<String> _currentAudioLiveData;
    private final MutableLiveData<String> _currentContentIdLiveData;
    private final MutableLiveData<Throwable> _currentErrorLiveData;
    private final MutableLiveData<BrowsableItem> _currentMediaLiveData;
    private final MutableLiveData<RightVPlaybackInfo> _currentPlaybackInfoLiveData;
    private final MutableLiveData<PlaybackState> _currentPlaybackStateLiveData;
    private final MutableLiveData<Long> _currentProgramTimestampLiveData;
    private final MutableLiveData<String> _currentSubtitleLiveData;
    private final MutableLiveData<Long> _currentTime;
    private final MutableLiveData<Long> _epgDurationLiveData;
    private MutableLiveData<Boolean> _nextAiringLoading;
    private MutableLiveData<Boolean> _nextEpisodeLoaded;
    private MutableLiveData<Media> _nextReplayMedia;
    private MutableLiveData<Integer> _onAdsCanBeSkippedLiveData;
    private MutableLiveData<Boolean> _onAdsDetectedLiveData;
    private final MutableLiveData<String> _trailerTitleLiveData;
    private final PortalApplicationSingletons applicationSingleton;

    /* JADX INFO: renamed from: autoPlay$delegate, reason: from kotlin metadata */
    private final Lazy autoPlay;

    /* JADX INFO: renamed from: catchupPlayback$delegate, reason: from kotlin metadata */
    private final Lazy catchupPlayback;
    private final String channelId;
    private final String contendId;
    private final long countdownTrigger;
    private Disposable disposableCurrentMedia;
    private Disposable disposableCurrentPlaybackInfo;
    private Disposable disposableNextMedia;
    private CompositeDisposable disposables;
    private boolean hasTriedToLoadNextAiring;
    private final boolean hideStatusOnScreen;
    private final String initialAudioLanguage;
    private final LanguagePreferences languagePreferences;
    private final Integer launchMode;
    private int newEpisodeStartedCount;
    private Episode nextEpisode;
    private Long nextTVAiringTimeStamp;
    private final boolean playingTrailer;
    private final String recordingId;
    private PlaybackReplayType replayType;
    private boolean resume;
    private boolean subtitleHasBeenSet;
    private final CountDownTimer timer;
    private boolean updateReplayTypeForNextMedia;
    private boolean userInteracted;
    private boolean userInterruptedCountDown;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[PlaybackReplayType.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[PlaybackReplayType.CATCHUP.ordinal()] = 1;
            $EnumSwitchMapping$0[PlaybackReplayType.STOV.ordinal()] = 2;
            int[] iArr2 = new int[PlaybackReplayType.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[PlaybackReplayType.STOV.ordinal()] = 1;
            $EnumSwitchMapping$1[PlaybackReplayType.CATCHUP.ordinal()] = 2;
        }
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.model.PlayerViewModel$loadPreferredLanguages$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PlayerViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0082@"}, d2 = {"loadPreferredLanguages", "", "currentChannelId", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.dotscreen.portal.model.PlayerViewModel", f = "PlayerViewModel.kt", i = {0, 0}, l = {165}, m = "loadPreferredLanguages", n = {"this", "currentChannelId"}, s = {"L$0", "L$1"})
    static final class C03321 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C03321(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PlayerViewModel.this.loadPreferredLanguages(null, this);
        }
    }

    private final float getPercentagePosition(long currentPosition, long duration) {
        return (currentPosition * 100.0f) / duration;
    }

    public final boolean getAutoPlay() {
        return ((Boolean) this.autoPlay.getValue()).booleanValue();
    }

    public final boolean getCatchupPlayback() {
        return ((Boolean) this.catchupPlayback.getValue()).booleanValue();
    }

    public final String getChannelId() {
        return this.channelId;
    }

    public final String getRecordingId() {
        return this.recordingId;
    }

    public final PlaybackReplayType getReplayType() {
        return this.replayType;
    }

    public final void setReplayType(PlaybackReplayType playbackReplayType) {
        this.replayType = playbackReplayType;
    }

    public final boolean getResume() {
        return this.resume;
    }

    public final void setResume(boolean z) {
        this.resume = z;
    }

    public final boolean getPlayingTrailer() {
        return this.playingTrailer;
    }

    public final Integer getLaunchMode() {
        return this.launchMode;
    }

    public PlayerViewModel(String str, String str2, Long l, String str3, PlaybackReplayType playbackReplayType, boolean z, boolean z2, Integer num, String str4, String str5, LanguagePreferences languagePreferences, PortalApplicationSingletons applicationSingleton) {
        Intrinsics.checkParameterIsNotNull(languagePreferences, "languagePreferences");
        Intrinsics.checkParameterIsNotNull(applicationSingleton, "applicationSingleton");
        this.contendId = str;
        this.channelId = str2;
        this.recordingId = str3;
        this.replayType = playbackReplayType;
        this.resume = z;
        this.playingTrailer = z2;
        this.launchMode = num;
        this.initialAudioLanguage = str4;
        this.languagePreferences = languagePreferences;
        this.applicationSingleton = applicationSingleton;
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "this.javaClass.simpleName");
        this.TAG = simpleName;
        this._currentContentIdLiveData = new MutableLiveData<>();
        this._currentAudioLiveData = new MutableLiveData<>();
        this._currentSubtitleLiveData = new MutableLiveData<>();
        this._currentProgramTimestampLiveData = new MutableLiveData<>();
        this._onAdsDetectedLiveData = new MutableLiveData<>(false);
        this._onAdsCanBeSkippedLiveData = new MutableLiveData<>(10);
        Log.v(this.TAG, "contentId from params: " + this.contendId);
        String str6 = this.contendId;
        if (str6 != null) {
            Log.v(this.TAG, "contentId " + str6 + " will be attribute to currentContentId");
            this._currentContentIdLiveData.setValue(str6);
            Log.v(this.TAG + '(' + this + ')', "after change " + getCurrentContentIdLiveData().getValue());
        }
        if (l != null) {
            this._currentProgramTimestampLiveData.setValue(Long.valueOf(l.longValue()));
        }
        String str7 = this.initialAudioLanguage;
        if (str7 != null) {
            this._currentAudioLiveData.setValue(str7);
        }
        if (str5 != null) {
            this._currentSubtitleLiveData.setValue(str5);
        }
        if (this.channelId != null) {
            BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass5(null), 3, null);
        }
        this.catchupPlayback = LazyKt.lazy(new Function0<Boolean>() { // from class: com.dotscreen.portal.model.PlayerViewModel$catchupPlayback$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Boolean invoke() {
                return Boolean.valueOf(invoke2());
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2() {
                return this.this$0.getReplayType() != null && this.this$0.getReplayType() == PlaybackReplayType.CATCHUP;
            }
        });
        this.autoPlay = LazyKt.lazy(new Function0<Boolean>() { // from class: com.dotscreen.portal.model.PlayerViewModel$autoPlay$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Boolean invoke() {
                return Boolean.valueOf(invoke2());
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2() {
                Integer launchMode = this.this$0.getLaunchMode();
                if ((launchMode != null && launchMode.intValue() == 85) || launchMode == null) {
                    return true;
                }
                launchMode.intValue();
                return true;
            }
        });
        this.countdownTrigger = TimeUnit.SECONDS.toMillis(10L) + this.applicationSingleton.getTimeshiftingHelper().getAdditionnalDurationInSeconds();
        this.hideStatusOnScreen = true;
        this.disposables = new CompositeDisposable();
        this._currentTime = new MutableLiveData<>();
        this._countDownFinished = new MutableLiveData<>(false);
        this._countDownIsOn = new MutableLiveData<>(false);
        this._nextEpisodeLoaded = new MutableLiveData<>(false);
        this._currentAiringLiveData = new MutableLiveData<>();
        this._currentMediaLiveData = new MutableLiveData<>();
        this._currentPlaybackInfoLiveData = new MutableLiveData<>();
        this._trailerTitleLiveData = new MutableLiveData<>();
        this._epgDurationLiveData = new MutableLiveData<>();
        this._currentErrorLiveData = new MutableLiveData<>();
        this._currentPlaybackStateLiveData = new MutableLiveData<>(PlaybackState.IDLE);
        this._nextAiringLoading = new MutableLiveData<>(false);
        this._nextReplayMedia = new MutableLiveData<>();
        final long countdownTime = getCountdownTime();
        final long millis = TimeUnit.SECONDS.toMillis(1L);
        this.timer = new CountDownTimer(countdownTime, millis) { // from class: com.dotscreen.portal.model.PlayerViewModel$timer$1
            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                this.this$0._currentTime.setValue(Long.valueOf(millisUntilFinished / TimeUnit.SECONDS.toMillis(1L)));
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                Log.i(this.this$0.TAG, "onFinish: countdownFinished");
                if (this.this$0.userInterruptedCountDown) {
                    return;
                }
                this.this$0._currentTime.setValue(Long.valueOf(TimeUnit.SECONDS.toMillis(0L)));
                this.this$0._countDownFinished.setValue(true);
                this.this$0._countDownIsOn.setValue(false);
            }
        };
    }

    public final LiveData<String> getCurrentContentIdLiveData() {
        return this._currentContentIdLiveData;
    }

    public final LiveData<String> getCurrentAudioLiveData() {
        return this._currentAudioLiveData;
    }

    public final LiveData<String> getCurrentSubtitleLiveData() {
        return this._currentSubtitleLiveData;
    }

    public final LiveData<Long> getCurrentProgramTimestampLiveData() {
        return this._currentProgramTimestampLiveData;
    }

    public final LiveData<Boolean> getOnAdsDetectedLiveData() {
        return this._onAdsDetectedLiveData;
    }

    public final LiveData<Integer> getOnAdsCanBeSkippedLiveData() {
        return this._onAdsCanBeSkippedLiveData;
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.model.PlayerViewModel$5, reason: invalid class name */
    /* JADX INFO: compiled from: PlayerViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.dotscreen.portal.model.PlayerViewModel$5", f = "PlayerViewModel.kt", i = {0}, l = {150}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    static final class AnonymousClass5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;
        private CoroutineScope p$;

        AnonymousClass5(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass5 anonymousClass5 = PlayerViewModel.this.new AnonymousClass5(completion);
            anonymousClass5.p$ = (CoroutineScope) obj;
            return anonymousClass5;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass5) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                PlayerViewModel playerViewModel = PlayerViewModel.this;
                String channelId = playerViewModel.getChannelId();
                this.L$0 = coroutineScope;
                this.label = 1;
                if (playerViewModel.loadPreferredLanguages(channelId, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public final void updateCurrentAudioTrack(String trackName) {
        this._currentAudioLiveData.postValue(trackName);
    }

    public final void updateCurrentSubtitleTrack(String trackName) {
        this._currentSubtitleLiveData.postValue(trackName);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    final /* synthetic */ Object loadPreferredLanguages(String str, Continuation<? super Unit> continuation) {
        C03321 c03321;
        PlayerViewModel playerViewModel;
        if (continuation instanceof C03321) {
            c03321 = (C03321) continuation;
            if ((c03321.label & Integer.MIN_VALUE) != 0) {
                c03321.label -= Integer.MIN_VALUE;
            } else {
                c03321 = new C03321(continuation);
            }
        } else {
            c03321 = new C03321(continuation);
        }
        Object objWithContext = c03321.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c03321.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objWithContext);
            CoroutineDispatcher io2 = Dispatchers.getIO();
            PlayerViewModel$loadPreferredLanguages$preferredLanguages$1 playerViewModel$loadPreferredLanguages$preferredLanguages$1 = new PlayerViewModel$loadPreferredLanguages$preferredLanguages$1(this, str, null);
            c03321.L$0 = this;
            c03321.L$1 = str;
            c03321.label = 1;
            objWithContext = BuildersKt.withContext(io2, playerViewModel$loadPreferredLanguages$preferredLanguages$1, c03321);
            if (objWithContext == coroutine_suspended) {
                return coroutine_suspended;
            }
            playerViewModel = this;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            playerViewModel = (PlayerViewModel) c03321.L$0;
            ResultKt.throwOnFailure(objWithContext);
        }
        Pair pair = (Pair) objWithContext;
        if (!Intrinsics.areEqual((String) pair.getFirst(), playerViewModel.initialAudioLanguage)) {
            playerViewModel._currentAudioLiveData.postValue((String) pair.getFirst());
            playerViewModel._currentSubtitleLiveData.postValue((String) pair.getSecond());
            Log.i(playerViewModel.TAG, "getPreferredLanguages: " + playerViewModel.getCurrentSubtitleLiveData().getValue() + " and " + playerViewModel.getCurrentAudioLiveData().getValue() + ' ');
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.model.PlayerViewModel$storePreferredLanguage$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PlayerViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.dotscreen.portal.model.PlayerViewModel$storePreferredLanguage$1", f = "PlayerViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03331 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $audioLanguage;
        final /* synthetic */ String $subtitleLanguage;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03331(String str, String str2, Continuation continuation) {
            super(2, continuation);
            this.$audioLanguage = str;
            this.$subtitleLanguage = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03331 c03331 = PlayerViewModel.this.new C03331(this.$audioLanguage, this.$subtitleLanguage, completion);
            c03331.p$ = (CoroutineScope) obj;
            return c03331;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03331) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            PlayerViewModel.this.languagePreferences.storePrefLanguage(PlayerViewModel.this.getChannelId(), this.$audioLanguage, this.$subtitleLanguage);
            return Unit.INSTANCE;
        }
    }

    public final void storePreferredLanguage(String audioLanguage, String subtitleLanguage) {
        String str = this.channelId;
        if (str != null) {
            if (str.length() > 0) {
                BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C03331(audioLanguage, subtitleLanguage, null), 2, null);
            }
        }
    }

    public final Long getNextTVAiringTimeStamp() {
        return this.nextTVAiringTimeStamp;
    }

    public final void setNextTVAiringTimeStamp(Long l) {
        this.nextTVAiringTimeStamp = l;
    }

    public final Episode getNextEpisode() {
        return this.nextEpisode;
    }

    public final void setNextEpisode(Episode episode) {
        this.nextEpisode = episode;
    }

    public final boolean getUserInteracted() {
        return this.userInteracted;
    }

    public final boolean getHasNextMediaToPlay() {
        return (this.nextEpisode == null && this.nextTVAiringTimeStamp == null) ? false : true;
    }

    private final long getCountdownTime() {
        if (this.replayType != null) {
            return TimeUnit.SECONDS.toMillis(0L);
        }
        return TimeUnit.SECONDS.toMillis(7L);
    }

    public final long getCountdownTrigger() {
        return this.countdownTrigger;
    }

    public final boolean getSubtitleHasBeenSet() {
        return this.subtitleHasBeenSet;
    }

    public final boolean getHideStatusOnScreen() {
        return this.hideStatusOnScreen;
    }

    public final int getNewEpisodeStartedCount() {
        return this.newEpisodeStartedCount;
    }

    public final LiveData<Long> getCurrentTime() {
        return this._currentTime;
    }

    public final LiveData<Boolean> getCountDownFinished() {
        return this._countDownFinished;
    }

    public final LiveData<Boolean> getCountDownIsOn() {
        return this._countDownIsOn;
    }

    public final LiveData<Boolean> getNextEpisodeLoaded() {
        return this._nextEpisodeLoaded;
    }

    public final LiveData<TVAiring> getCurrentAiringLiveData() {
        return this._currentAiringLiveData;
    }

    public final LiveData<BrowsableItem> getCurrentMediaLiveData() {
        return this._currentMediaLiveData;
    }

    public final LiveData<RightVPlaybackInfo> getCurrentPlaybackInfoLiveData() {
        return this._currentPlaybackInfoLiveData;
    }

    public final LiveData<String> getTrailerTitleLiveData() {
        return this._trailerTitleLiveData;
    }

    public final LiveData<Long> getEpgDurationLiveData() {
        return this._epgDurationLiveData;
    }

    public final LiveData<Throwable> getCurrentErrorLiveData() {
        return this._currentErrorLiveData;
    }

    public final LiveData<PlaybackState> getCurrentPlaybackStateLiveData() {
        return this._currentPlaybackStateLiveData;
    }

    public final LiveData<Boolean> getNextAiringLoading() {
        return this._nextAiringLoading;
    }

    public final LiveData<Media> getNextReplayMedia() {
        return this._nextReplayMedia;
    }

    private final Media getNextMediaToPlay() {
        if (this.contendId != null) {
            return this.nextEpisode;
        }
        return getNextReplayMedia().getValue();
    }

    public final void resetNewEpisodeStartedCount() {
        this.newEpisodeStartedCount = 0;
    }

    public final void startCountDown() {
        if (this.nextEpisode != null && !this.userInterruptedCountDown && Intrinsics.areEqual((Object) getCountDownIsOn().getValue(), (Object) false) && Intrinsics.areEqual((Object) getCountDownFinished().getValue(), (Object) false) && this.newEpisodeStartedCount < 3) {
            Log.v(this.TAG, "start countdown episode");
            this._countDownIsOn.setValue(true);
            this.timer.start();
        } else {
            if (this.nextTVAiringTimeStamp == null || this.userInterruptedCountDown || !Intrinsics.areEqual((Object) getCountDownIsOn().getValue(), (Object) false) || !Intrinsics.areEqual((Object) getCountDownFinished().getValue(), (Object) false)) {
                return;
            }
            Log.v(this.TAG, "start countdown replay next");
            this._countDownIsOn.setValue(true);
            this.timer.start();
        }
    }

    public final void userInterruptedCountdown() {
        Log.i(this.TAG, "user interrupted countdown");
        this.userInterruptedCountDown = true;
        this._countDownIsOn.setValue(false);
        this.timer.cancel();
    }

    public final void setAdsState(boolean value) {
        this._onAdsDetectedLiveData.postValue(Boolean.valueOf(value));
    }

    public final void setCanSkipState(int value) {
        this._onAdsCanBeSkippedLiveData.postValue(Integer.valueOf(value));
    }

    private final void getNextEpisode(Media media) {
        UserService userService = this.applicationSingleton.getBOService().getUserService();
        if (userService == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
        }
        this.disposableNextMedia = ((MegacableUserService) userService).getNextEpisode(media).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Episode>() { // from class: com.dotscreen.portal.model.PlayerViewModel.getNextEpisode.1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Episode episode) {
                PlayerViewModel.this.setNextEpisode(episode);
                PlayerViewModel.this._nextEpisodeLoaded.setValue(true);
                Log.i(PlayerViewModel.this.TAG, "getNextEpisode succeed");
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.model.PlayerViewModel.getNextEpisode.2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Log.w(PlayerViewModel.this.TAG, "getNextEpisode failed", th);
            }
        }, new Action() { // from class: com.dotscreen.portal.model.PlayerViewModel.getNextEpisode.3
            @Override // io.reactivex.functions.Action
            public final void run() {
                Log.w(PlayerViewModel.this.TAG, "No next episode found, see upper logs to have more details");
            }
        });
    }

    public final void loadNextAiring() {
        TVAiring value = getCurrentAiringLiveData().getValue();
        if (value != null && this.nextTVAiringTimeStamp == null && !this.hasTriedToLoadNextAiring && Intrinsics.areEqual((Object) this._nextAiringLoading.getValue(), (Object) false)) {
            this._nextAiringLoading.setValue(true);
            this.disposableNextMedia = this.applicationSingleton.getCurrentLineup().flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.portal.model.PlayerViewModel.loadNextAiring.1
                @Override // io.reactivex.functions.Function
                public final Observable<Channel> apply(Lineup it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return it.getItems();
                }
            }).filter(new Predicate<Channel>() { // from class: com.dotscreen.portal.model.PlayerViewModel.loadNextAiring.2
                @Override // io.reactivex.functions.Predicate
                public final boolean test(Channel it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return Intrinsics.areEqual(it.getId(), PlayerViewModel.this.getChannelId());
                }
            }).firstElement().flatMap(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.portal.model.PlayerViewModel.loadNextAiring.3
                @Override // io.reactivex.functions.Function
                public final Maybe<TVAiring> apply(final Channel channel) {
                    Intrinsics.checkParameterIsNotNull(channel, "channel");
                    PlayerViewModel.this.hasTriedToLoadNextAiring = true;
                    Long value2 = PlayerViewModel.this.getCurrentProgramTimestampLiveData().getValue();
                    if (value2 == null) {
                        Intrinsics.throwNpe();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(value2, "currentProgramTimestampLiveData.value!!");
                    return channel.airing(new Date(value2.longValue())).flatMap(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.portal.model.PlayerViewModel.loadNextAiring.3.1
                        @Override // io.reactivex.functions.Function
                        public final Maybe<TVAiring> apply(TVAiring it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            return channel.airing(it.getEndDate());
                        }
                    });
                }
            }).flatMap(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.portal.model.PlayerViewModel.loadNextAiring.4
                @Override // io.reactivex.functions.Function
                public final Maybe<TVAiring> apply(final TVAiring airing) {
                    Intrinsics.checkParameterIsNotNull(airing, "airing");
                    PlaybackReplayType replayType = PlayerViewModel.this.getReplayType();
                    if (replayType != null) {
                        int i = WhenMappings.$EnumSwitchMapping$0[replayType.ordinal()];
                        if (i == 1) {
                            return airing.getTvProgram().flatMap(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.portal.model.PlayerViewModel.loadNextAiring.4.1
                                @Override // io.reactivex.functions.Function
                                public final Maybe<Media> apply(TVProgram it) {
                                    Intrinsics.checkParameterIsNotNull(it, "it");
                                    return it.getRelatedCatchupMedia();
                                }
                            }).doAfterSuccess(new Consumer<Media>() { // from class: com.dotscreen.portal.model.PlayerViewModel.loadNextAiring.4.2
                                @Override // io.reactivex.functions.Consumer
                                public final void accept(Media media) {
                                    Log.i(PlayerViewModel.this.TAG, "next catchup ok " + media);
                                }
                            }).switchIfEmpty(airing.getStartOverMedia().doAfterSuccess(new Consumer<Media>() { // from class: com.dotscreen.portal.model.PlayerViewModel.loadNextAiring.4.3
                                @Override // io.reactivex.functions.Consumer
                                public final void accept(Media media) {
                                    Log.i(PlayerViewModel.this.TAG, "(catchup case) next startover ok " + media);
                                    PlayerViewModel.this.switchToStov();
                                }
                            })).map(new Function<T, R>() { // from class: com.dotscreen.portal.model.PlayerViewModel.loadNextAiring.4.4
                                @Override // io.reactivex.functions.Function
                                public final TVAiring apply(Media it) {
                                    Intrinsics.checkParameterIsNotNull(it, "it");
                                    PlayerViewModel.this._nextReplayMedia.postValue(it);
                                    Log.i(PlayerViewModel.this.TAG, "next media ok " + it);
                                    return airing;
                                }
                            });
                        }
                        if (i == 2) {
                            return airing.getStartOverMedia().map(new Function<T, R>() { // from class: com.dotscreen.portal.model.PlayerViewModel.loadNextAiring.4.5
                                @Override // io.reactivex.functions.Function
                                public final TVAiring apply(Media it) {
                                    Intrinsics.checkParameterIsNotNull(it, "it");
                                    Log.i(PlayerViewModel.this.TAG, "next startover ok " + it);
                                    PlayerViewModel.this._nextReplayMedia.postValue(it);
                                    return airing;
                                }
                            });
                        }
                    }
                    return Maybe.empty();
                }
            }).doFinally(new Action() { // from class: com.dotscreen.portal.model.PlayerViewModel.loadNextAiring.5
                @Override // io.reactivex.functions.Action
                public final void run() {
                    PlayerViewModel.this._nextAiringLoading.postValue(false);
                }
            }).subscribe(new Consumer<TVAiring>() { // from class: com.dotscreen.portal.model.PlayerViewModel.loadNextAiring.6
                @Override // io.reactivex.functions.Consumer
                public final void accept(TVAiring tVAiring) {
                    PlayerViewModel.this.setNextTVAiringTimeStamp(Long.valueOf(tVAiring.getStartDate().getTime()));
                    Log.i(PlayerViewModel.this.TAG, "getNextAiring succeed " + tVAiring);
                }
            }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.model.PlayerViewModel.loadNextAiring.7
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                    Log.w(PlayerViewModel.this.TAG, "getNextAiring failed", th);
                }
            }, new Action() { // from class: com.dotscreen.portal.model.PlayerViewModel.loadNextAiring.8
                @Override // io.reactivex.functions.Action
                public final void run() {
                    Log.w(PlayerViewModel.this.TAG, "No next airing");
                }
            });
        } else {
            if (this.nextTVAiringTimeStamp != null || this.hasTriedToLoadNextAiring) {
                return;
            }
            String str = this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Load next airing failed ");
            sb.append(value);
            sb.append(" duration: ");
            sb.append(value != null ? Long.valueOf(PlayerViewModelKt.duration(value)) : null);
            Log.e(str, sb.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void switchToStov() {
        Log.e(this.TAG, "Will switch to STOV for next Media");
        this.updateReplayTypeForNextMedia = true;
    }

    private final void newMediaStarted() {
        this.newEpisodeStartedCount++;
        this.userInterruptedCountDown = false;
        this._countDownIsOn.setValue(false);
        this._countDownFinished.setValue(false);
        this._nextEpisodeLoaded.setValue(false);
        this._nextAiringLoading.setValue(false);
        this.hasTriedToLoadNextAiring = false;
        this._nextReplayMedia.setValue(null);
        this.nextEpisode = (Episode) null;
        this.nextTVAiringTimeStamp = (Long) null;
        this._currentPlaybackInfoLiveData.setValue(null);
    }

    private final boolean selectNextEpisode() {
        Episode episode = this.nextEpisode;
        if (episode == null) {
            return false;
        }
        MutableLiveData<String> mutableLiveData = this._currentContentIdLiveData;
        if (episode == null) {
            Intrinsics.throwNpe();
        }
        mutableLiveData.postValue(episode.getId());
        newMediaStarted();
        return true;
    }

    private final boolean selectNextProgram() {
        Long l = this.nextTVAiringTimeStamp;
        if (l == null) {
            return false;
        }
        this._currentProgramTimestampLiveData.postValue(l);
        if (this.updateReplayTypeForNextMedia) {
            this.replayType = PlaybackReplayType.STOV;
        }
        newMediaStarted();
        return true;
    }

    public final boolean selectNextMedia() {
        if (this.contendId != null) {
            return selectNextEpisode();
        }
        return selectNextProgram();
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        Disposable disposable = this.disposableNextMedia;
        if (disposable != null) {
            disposable.dispose();
        }
        Disposable disposable2 = this.disposableCurrentMedia;
        if (disposable2 != null) {
            disposable2.dispose();
        }
    }

    public final void loadMedia() {
        Maybe<BrowsableItem> maybeFirstElement;
        Maybe<BrowsableItem> maybeSubscribeOn;
        Maybe<BrowsableItem> maybeObserveOn;
        Maybe<BrowsableItem> maybeRetry;
        Log.v(this.TAG + '(' + this + ')', "contentId: " + this._currentContentIdLiveData.getValue() + " (" + getCurrentContentIdLiveData().getValue() + ')');
        Disposable disposableSubscribe = null;
        if (this._currentContentIdLiveData.getValue() != null) {
            VODCatalogService vodCatalogService = this.applicationSingleton.getBOService().getVodCatalogService();
            String value = getCurrentContentIdLiveData().getValue();
            if (value == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(value, "currentContentIdLiveData.value!!");
            maybeFirstElement = vodCatalogService.itemById(value).doAfterSuccess(new Consumer<BrowsableItem>() { // from class: com.dotscreen.portal.model.PlayerViewModel.loadMedia.1
                @Override // io.reactivex.functions.Consumer
                public final void accept(BrowsableItem it) {
                    String str = PlayerViewModel.this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("play: here success ");
                    String value2 = PlayerViewModel.this.getCurrentContentIdLiveData().getValue();
                    if (value2 == null) {
                        Intrinsics.throwNpe();
                    }
                    sb.append(value2);
                    Log.v(str, sb.toString());
                    VODCatalogService vodCatalogService2 = PlayerViewModel.this.applicationSingleton.getBOService().getVodCatalogService();
                    if (vodCatalogService2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableVODCatalogService");
                    }
                    MegacableVODCatalogService megacableVODCatalogService = (MegacableVODCatalogService) vodCatalogService2;
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    MegacableVODCatalogService.registerClick$default(megacableVODCatalogService, it, PlayerViewModel.this.getPlayingTrailer() ? "preview" : "order", null, null, 12, null).subscribe();
                }
            });
        } else if (this.channelId != null && getCurrentProgramTimestampLiveData().getValue() != null && this.replayType != null) {
            maybeFirstElement = this.applicationSingleton.getCurrentLineup().flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.portal.model.PlayerViewModel.loadMedia.2
                @Override // io.reactivex.functions.Function
                public final Observable<Channel> apply(Lineup it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return it.getItems();
                }
            }).filter(new Predicate<Channel>() { // from class: com.dotscreen.portal.model.PlayerViewModel.loadMedia.3
                @Override // io.reactivex.functions.Predicate
                public final boolean test(Channel it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return Intrinsics.areEqual(it.getId(), PlayerViewModel.this.getChannelId());
                }
            }).firstElement().flatMap(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.portal.model.PlayerViewModel.loadMedia.4
                @Override // io.reactivex.functions.Function
                public final Maybe<TVAiring> apply(Channel it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Long value2 = PlayerViewModel.this.getCurrentProgramTimestampLiveData().getValue();
                    if (value2 == null) {
                        Intrinsics.throwNpe();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(value2, "currentProgramTimestampLiveData.value!!");
                    return it.airing(new Date(value2.longValue()));
                }
            }).doAfterSuccess(new Consumer<TVAiring>() { // from class: com.dotscreen.portal.model.PlayerViewModel.loadMedia.5
                @Override // io.reactivex.functions.Consumer
                public final void accept(TVAiring tVAiring) {
                    Log.v(PlayerViewModel.this.TAG, "Will try to find and play " + PlayerViewModel.this.getReplayType() + " associated to " + tVAiring);
                }
            });
        } else if (this.recordingId != null) {
            PVRService pvrService = this.applicationSingleton.getBOService().getPvrService();
            if (pvrService == null) {
                Intrinsics.throwNpe();
            }
            maybeFirstElement = pvrService.recordings(PVRStorageMode.Network).filter(new Predicate<TVRecording>() { // from class: com.dotscreen.portal.model.PlayerViewModel.loadMedia.6
                @Override // io.reactivex.functions.Predicate
                public final boolean test(TVRecording it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return Intrinsics.areEqual(it.getId(), PlayerViewModel.this.getRecordingId());
                }
            }).firstElement();
        } else {
            maybeFirstElement = null;
        }
        if (maybeFirstElement != null && (maybeSubscribeOn = maybeFirstElement.subscribeOn(Schedulers.io())) != null && (maybeObserveOn = maybeSubscribeOn.observeOn(AndroidSchedulers.mainThread())) != null && (maybeRetry = maybeObserveOn.retry(1L)) != null) {
            disposableSubscribe = maybeRetry.subscribe(new Consumer<BrowsableItem>() { // from class: com.dotscreen.portal.model.PlayerViewModel.loadMedia.7
                @Override // io.reactivex.functions.Consumer
                public final void accept(BrowsableItem browsableItem) {
                    PlayerViewModel.this._currentMediaLiveData.setValue(browsableItem);
                    PlayerViewModel.this.loadPlaybackInfo();
                }
            }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.model.PlayerViewModel.loadMedia.8
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                    Log.e(PlayerViewModel.this.TAG, "Error while loading media ");
                    PlayerViewModel.this._currentErrorLiveData.postValue(th);
                    th.printStackTrace();
                }
            });
        }
        this.disposableCurrentMedia = disposableSubscribe;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:50:0x0138  */
    public final void loadPlaybackInfo() {
        Maybe maybeFlatMap;
        Maybe maybeSubscribeOn;
        Maybe maybeObserveOn;
        Maybe<Media> maybeEmpty;
        Log.v(this.TAG, "Load Playback info ");
        UserService userService = this.applicationSingleton.getBOService().getUserService();
        if (userService == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
        }
        final MegacableUserService megacableUserService = (MegacableUserService) userService;
        BrowsableItem value = getCurrentMediaLiveData().getValue();
        Disposable disposableSubscribe = null;
        if (value instanceof Media) {
            BrowsableItem value2 = getCurrentMediaLiveData().getValue();
            if (value2 == null) {
                Intrinsics.throwNpe();
            }
            if (value2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.bokit.model.Media");
            }
            Media media = (Media) value2;
            if (media instanceof Episode) {
                getNextEpisode(media);
            }
            if (this.playingTrailer) {
                if (media instanceof Content) {
                    Observable<Media> trailers = ((Content) media).getMetadata().getTrailers();
                    if (trailers == null || (maybeEmpty = trailers.firstElement()) == null) {
                        maybeEmpty = Maybe.empty();
                        Intrinsics.checkExpressionValueIsNotNull(maybeEmpty, "Maybe.empty()");
                    }
                    maybeFlatMap = maybeEmpty.doAfterSuccess(new Consumer<Media>() { // from class: com.dotscreen.portal.model.PlayerViewModel$loadPlaybackInfo$playbackInfo$1
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Media media2) {
                            this.this$0._trailerTitleLiveData.postValue(media2.getTitle());
                        }
                    }).flatMap((Function) new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.portal.model.PlayerViewModel$loadPlaybackInfo$playbackInfo$2
                        @Override // io.reactivex.functions.Function
                        public final Maybe<PlaybackInfo> apply(Media it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            return it.playbackInfo(this.this$0.getResume());
                        }
                    });
                } else {
                    maybeFlatMap = null;
                }
            } else {
                maybeFlatMap = megacableUserService.openVODPlaybackSession(media).andThen(media.playbackInfo(this.resume));
            }
        } else if (value instanceof TVRecording) {
            BrowsableItem value3 = getCurrentMediaLiveData().getValue();
            if (value3 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.bokit.model.TVRecording");
            }
            maybeFlatMap = ((TVRecording) value3).getMedia().doAfterSuccess(new Consumer<Media>() { // from class: com.dotscreen.portal.model.PlayerViewModel$loadPlaybackInfo$playbackInfo$3
                @Override // io.reactivex.functions.Consumer
                public final void accept(Media media2) {
                    this.this$0._currentMediaLiveData.postValue(media2);
                }
            }).flatMap((Function) new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.portal.model.PlayerViewModel$loadPlaybackInfo$playbackInfo$4
                @Override // io.reactivex.functions.Function
                public final Maybe<PlaybackInfo> apply(Media it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return megacableUserService.openVODPlaybackSession(it).andThen(it.playbackInfo(this.this$0.getResume()));
                }
            });
        } else if (value instanceof TVAiring) {
            BrowsableItem value4 = getCurrentMediaLiveData().getValue();
            if (value4 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.bokit.model.TVAiring");
            }
            final TVAiring tVAiring = (TVAiring) value4;
            this._currentAiringLiveData.setValue(tVAiring);
            PlaybackReplayType playbackReplayType = this.replayType;
            if (playbackReplayType != null) {
                int i = WhenMappings.$EnumSwitchMapping$1[playbackReplayType.ordinal()];
                if (i == 1) {
                    maybeFlatMap = tVAiring.getStartOverMedia().doAfterSuccess(new Consumer<Media>() { // from class: com.dotscreen.portal.model.PlayerViewModel$loadPlaybackInfo$playbackInfo$5
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Media media2) {
                            this.this$0._currentMediaLiveData.postValue(media2);
                        }
                    }).flatMap((Function) new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.portal.model.PlayerViewModel$loadPlaybackInfo$playbackInfo$6
                        @Override // io.reactivex.functions.Function
                        public final Maybe<PlaybackInfo> apply(Media it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            this.this$0._epgDurationLiveData.postValue(Long.valueOf(PlayerViewModelKt.duration(tVAiring) + (this.this$0.applicationSingleton.getTimeshiftingHelper().getAdditionnalDurationInSeconds() * ((long) 1000))));
                            return megacableUserService.openVODPlaybackSession(it).andThen(it.playbackInfo(this.this$0.getResume()));
                        }
                    });
                } else if (i != 2) {
                    maybeFlatMap = null;
                } else {
                    maybeFlatMap = tVAiring.getTvProgram().flatMap((Function) new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.portal.model.PlayerViewModel$loadPlaybackInfo$playbackInfo$7
                        @Override // io.reactivex.functions.Function
                        public final Maybe<Media> apply(TVProgram it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            Log.v(this.this$0.TAG, "Try to get catchup attached to " + it);
                            return it.getRelatedCatchupMedia();
                        }
                    }).doAfterSuccess(new Consumer<Media>() { // from class: com.dotscreen.portal.model.PlayerViewModel$loadPlaybackInfo$playbackInfo$8
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Media media2) {
                            Log.v(this.this$0.TAG, "Found catchup " + media2);
                            this.this$0._currentMediaLiveData.postValue(media2);
                        }
                    }).flatMap(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.portal.model.PlayerViewModel$loadPlaybackInfo$playbackInfo$9
                        @Override // io.reactivex.functions.Function
                        public final Maybe<PlaybackInfo> apply(Media it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            return megacableUserService.openVODPlaybackSession(it).andThen(it.playbackInfo(this.this$0.getResume()));
                        }
                    });
                }
            } else {
                maybeFlatMap = null;
            }
        } else {
            maybeFlatMap = null;
        }
        if (maybeFlatMap != null && (maybeSubscribeOn = maybeFlatMap.subscribeOn(Schedulers.io())) != null && (maybeObserveOn = maybeSubscribeOn.observeOn(AndroidSchedulers.mainThread())) != null) {
            disposableSubscribe = maybeObserveOn.subscribe(new Consumer<PlaybackInfo>() { // from class: com.dotscreen.portal.model.PlayerViewModel.loadPlaybackInfo.1
                @Override // io.reactivex.functions.Consumer
                public final void accept(PlaybackInfo playbackInfo) {
                    Log.d(PlayerViewModel.this.TAG, "Playback Infos: " + playbackInfo);
                    if (playbackInfo instanceof RightVPlaybackInfo) {
                        PlayerViewModel.this._currentPlaybackInfoLiveData.postValue(playbackInfo);
                    }
                }
            }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.model.PlayerViewModel.loadPlaybackInfo.2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                    Log.w(PlayerViewModel.this.TAG, "Playback Infos error", th);
                    PlayerViewModel.this._currentErrorLiveData.postValue(th);
                }
            }, new Action() { // from class: com.dotscreen.portal.model.PlayerViewModel.loadPlaybackInfo.3
                @Override // io.reactivex.functions.Action
                public final void run() {
                    Log.d(PlayerViewModel.this.TAG, "Playback Infos complete");
                }
            });
        }
        this.disposableCurrentPlaybackInfo = disposableSubscribe;
    }

    public final void invalidateCurrentPlaybackInfo() {
        this._currentPlaybackInfoLiveData.postValue(null);
    }

    public final void saveMediaPosition(final long currentPosition, long duration) {
        RightVUser user;
        Completable completableSaveMediaPosition;
        Disposable disposableSubscribe;
        BrowsableItem value = getCurrentMediaLiveData().getValue();
        if (value instanceof StartOverMedia) {
            return;
        }
        if ((value instanceof CatchupMedia) || (value instanceof NPVRMedia)) {
            if (getPercentagePosition(currentPosition, duration) > 95.0f) {
                currentPosition = 0;
            }
        } else if (getCurrentPlaybackStateLiveData().getValue() == PlaybackState.IS_FINISHED) {
            currentPosition = duration;
        }
        if (value != null) {
            if (value instanceof Media) {
                LiveData<PortalApplication.Config> config = this.applicationSingleton.getConfig();
                Intrinsics.checkExpressionValueIsNotNull(config, "applicationSingleton.config");
                PortalApplication.Config value2 = config.getValue();
                if (value2 != null && (user = value2.getUser()) != null && (completableSaveMediaPosition = user.saveMediaPosition((Media) value, currentPosition)) != null && (disposableSubscribe = completableSaveMediaPosition.subscribe(new Action() { // from class: com.dotscreen.portal.model.PlayerViewModel$saveMediaPosition$$inlined$apply$lambda$1
                    @Override // io.reactivex.functions.Action
                    public final void run() {
                        Log.d(this.this$0.TAG, "Media position saved !");
                    }
                }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.model.PlayerViewModel$saveMediaPosition$$inlined$apply$lambda$2
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Throwable th) {
                        Log.w(this.this$0.TAG, "Media position save error:", th);
                    }
                })) != null) {
                    PlayerViewModelKt.addTo(disposableSubscribe, this.disposables);
                }
                this.resume = true;
                return;
            }
            Log.e(this.TAG, "Can't save position for non media type: " + value.getClass().getSimpleName());
        }
    }

    public final void updatePlaybackState(PlaybackState newState) {
        Intrinsics.checkParameterIsNotNull(newState, "newState");
        this._currentPlaybackStateLiveData.postValue(newState);
    }

    public final void setSubtitle(boolean isSet) {
        this.subtitleHasBeenSet = isSet;
    }

    public final boolean isAllowedToPlay(boolean checkNextMedia) {
        LiveData<PortalApplication.Config> config = this.applicationSingleton.getConfig();
        Intrinsics.checkExpressionValueIsNotNull(config, "applicationSingleton.config");
        PortalApplication.Config value = config.getValue();
        RightVUser user = value != null ? value.getUser() : null;
        if (user == null) {
            return false;
        }
        Media nextMediaToPlay = checkNextMedia ? getNextMediaToPlay() : (Media) getCurrentMediaLiveData().getValue();
        Log.d(this.TAG, "will check PR of " + nextMediaToPlay);
        if (nextMediaToPlay == null) {
            return false;
        }
        Log.d(this.TAG, "PR of " + nextMediaToPlay.getTitle() + " (" + nextMediaToPlay + ") is " + MegacableUtilsKt.parentalLevel(nextMediaToPlay) + " user " + user.getParentalLevel().getLevel());
        return MegacableUtilsKt.parentalLevel(nextMediaToPlay) <= user.getParentalLevel().getLevel();
    }

    public final void userInteracted() {
        if (this.newEpisodeStartedCount >= 1) {
            this.userInteracted = true;
        }
    }

    public final void resetUserInteraction() {
        this.userInteracted = false;
    }

    public final boolean shouldDisplayStillAlive() {
        if (this.replayType == null || this.newEpisodeStartedCount >= 2) {
            if (this.replayType == null || this.newEpisodeStartedCount != 2) {
                return true;
            }
            this.newEpisodeStartedCount = 0;
            if (!this.userInteracted) {
                return true;
            }
        }
        return false;
    }
}
