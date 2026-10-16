package com.dotscreen.portal;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.hardware.display.DisplayManager;
import android.media.tv.TvContract;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.work.Configuration;
import androidx.work.Data;
import androidx.work.ListenableWorker;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.dotscreen.bokit.BackendErrorException;
import com.dotscreen.bokit.OnRequestResponseListener;
import com.dotscreen.bokit.model.Lineup;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.model.TVAiringTimeOfDay;
import com.dotscreen.bokit.model.User;
import com.dotscreen.bokit.services.BOService;
import com.dotscreen.bokit.services.LiveTVService;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.bokit.services.UserStatus;
import com.dotscreen.farkit.analytics.AnalyticsManager;
import com.dotscreen.farkit.design.DesignManager;
import com.dotscreen.farkit.network.NetworkStateManager;
import com.dotscreen.farkit.network.NetworkStatus;
import com.dotscreen.farkit.network.RequestManager;
import com.dotscreen.farkit.network.internal.BitmapLruCache;
import com.dotscreen.farkit.network.internal.LocalImageCache;
import com.dotscreen.farkit.tools.DSLog;
import com.dotscreen.farkit.tools.ErrorManager;
import com.dotscreen.farkit.updater.UpdateManagerKt;
import com.dotscreen.farkit.updater.UpdaterInfo;
import com.dotscreen.megacable.portal.ClearedReceiver;
import com.dotscreen.portal.ads.Ad;
import com.dotscreen.portal.ads.AdsHelper;
import com.dotscreen.portal.ads.IFloatingAdListener;
import com.dotscreen.portal.ads.INativeAdListener;
import com.dotscreen.portal.ads.TargetAdvertisingConfig;
import com.dotscreen.portal.ads.TargetedAdsConfigProvider;
import com.dotscreen.portal.ui.screens.home.HomeActivity;
import com.dotscreen.portal.ui.views.AdBannerView;
import com.dotscreen.portal.ui.views.ConnectivityStatusView;
import com.dotscreen.portal.ui.views.DebugInfos;
import com.dotscreen.portal.ui.views.FloatingAdBannerView;
import com.dotscreen.portal.ui.views.InLayoutNativeAdBannerView;
import com.dotscreen.portal.utils.ActionBroadcastReceiver;
import com.dotscreen.portal.utils.ActionBroadcastReceiverKt;
import com.dotscreen.portal.utils.Activity_ExtensionKt;
import com.dotscreen.portal.utils.ApplicationsManager;
import com.dotscreen.portal.utils.AudioZTEManager;
import com.dotscreen.portal.utils.BootSequenceManager;
import com.dotscreen.portal.utils.FeaturesManager;
import com.dotscreen.portal.utils.LoginError;
import com.dotscreen.portal.utils.MegacableErrorManager;
import com.dotscreen.portal.utils.SentryEventExtentionsKt;
import com.dotscreen.portal.utils.String_ExtensionKt;
import com.dotscreen.portal.utils.analytics.DCAAnalyticsModule;
import com.dotscreen.portal.utils.probes.NoInternetProbesFeature;
import com.dotscreen.portal.utils.reporting.ConnectivityStatusReportManager;
import com.dotscreen.tv.common.IForegroudStateProvider;
import com.dotscreen.tv.common.IMediaPlayerStateProvider;
import com.dotscreen.tv.common.INetRequestProvider;
import com.dotscreen.tv.common.ISmartLibProvider;
import com.dotscreen.tv.common.IUserSuspendListener;
import com.dotscreen.tv.common.bokit.MegacableLiveTVService;
import com.dotscreen.tv.common.bokit.MegacableUserService;
import com.dotscreen.tv.common.bokit.MegacableUserServiceKt;
import com.dotscreen.tv.common.bokit.Profile;
import com.dotscreen.tv.common.bokit.RightVBackendErrorException;
import com.dotscreen.tv.common.bokit.RightVRequestManager;
import com.dotscreen.tv.common.bokit.RightVUser;
import com.dotscreen.tv.common.modules.CompassVODAnalyticsModule;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModule;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.dotscreen.tv.common.providers.ScanStatusProvider;
import com.dotscreen.tv.common.receiver.UserSuspendedBroadcastReceiver;
import com.dotscreen.tv.common.search.LocalSearchProvider;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.dotscreen.tv.common.utils.DCAInitComponent;
import com.dotscreen.tv.common.utils.DeviceInfo;
import com.dotscreen.tv.common.utils.OreganDevice;
import com.dotscreen.tv.common.utils.ScanStatusHandler;
import com.dotscreen.tv.common.utils.SentryRateLimitManagement;
import com.dotscreen.tv.common.utils.SentryReportFlags;
import com.dotscreen.tv.common.utils.SentryReportHint;
import com.dotscreen.tv.common.utils.TechnicolorDevice;
import com.dotscreen.tv.common.utils.TimeshiftingHelper;
import com.dotscreen.tv.common.utils.UnknownDevice;
import com.dotscreen.tv.common.utils.notifications.NotificationManager;
import com.dotscreen.tv.common.utils.notifications.NotificationsContract;
import com.dotscreen.tv.ui.dialog.PortalDialog;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.media.tv.companionlibrary.model.Channel;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.viaccessorca.analytics.fma.VoFmaController;
import com.viaccessorca.analytics.fma.VoFmaControllerListener;
import com.viaccessorca.analytics.fma.VoFmaEvent;
import com.viaccessorca.analytics.fma.VoFmaInitParameters;
import com.viaccessorca.exceptions.VOException;
import com.viaccessorca.voplayer.VOPlayer;
import com.viaccessorca.voplayer.bpksmartlib.VOPlayerBpkExtended;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import io.sentry.Sentry;
import io.sentry.SentryEvent;
import io.sentry.SentryOptions;
import io.sentry.android.core.SentryAndroid;
import io.sentry.android.core.SentryAndroidOptions;
import io.sentry.protocol.Device;
import io.sentry.protocol.Message;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.Serializable;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.random.Random;
import kotlin.ranges.LongRange;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.reactivestreams.Publisher;
import tv.broadpeak.smartlib.SmartLib;
import tv.broadpeak.smartlib.engine.CoreEngine;
import tv.broadpeak.smartlib.engine.manager.LoggerManager;

/* JADX INFO: compiled from: PortalApplication.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¸\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0000\n\u0002\b5\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u000e*\u0004\u0091\u0001\u0098\u0001\u0018\u0000 ²\u00022\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\b:\u0014®\u0002¯\u0002°\u0002±\u0002²\u0002³\u0002´\u0002µ\u0002¶\u0002·\u0002B\u0005¢\u0006\u0002\u0010\tJ\u0011\u0010\u00ad\u0001\u001a\u00030®\u00012\u0007\u0010¯\u0001\u001a\u00020FJ\u0011\u0010°\u0001\u001a\u00030®\u00012\u0007\u0010¯\u0001\u001a\u00020JJ\u0013\u0010±\u0001\u001a\u00030®\u00012\u0007\u0010²\u0001\u001a\u00020HH\u0002J\u001c\u0010³\u0001\u001a\u00030®\u00012\u0007\u0010´\u0001\u001a\u00020\u000e2\u0007\u0010²\u0001\u001a\u00020HH\u0002J\b\u0010µ\u0001\u001a\u00030®\u0001J\b\u00100\u001a\u00020\u0016H\u0016J\u0014\u0010¶\u0001\u001a\u00030®\u00012\b\u0010·\u0001\u001a\u00030¸\u0001H\u0002J\u0010\u0010¹\u0001\u001a\u00020\u00162\u0007\u0010º\u0001\u001a\u00020\u000eJ\b\u0010»\u0001\u001a\u00030®\u0001J\n\u0010¼\u0001\u001a\u00030®\u0001H\u0002J\u0013\u0010½\u0001\u001a\u00030®\u00012\u0007\u0010¾\u0001\u001a\u00020\u0016H\u0002J\u001c\u0010¿\u0001\u001a\u00030®\u00012\u0007\u0010À\u0001\u001a\u00020\u000e2\u0007\u0010Á\u0001\u001a\u00020\u000eH\u0002J\u001a\u0010Â\u0001\u001a\u00030®\u00012\u0007\u0010Ã\u0001\u001a\u00020\u000e2\u0007\u0010Ä\u0001\u001a\u00020\u000bJ\n\u0010Å\u0001\u001a\u00030®\u0001H\u0016J\u0012\u0010Æ\u0001\u001a\u0004\u0018\u00010H2\u0007\u0010´\u0001\u001a\u00020\u000eJ\t\u0010Ç\u0001\u001a\u00020 H\u0016J\t\u0010È\u0001\u001a\u00020$H\u0016J\t\u0010É\u0001\u001a\u00020)H\u0016J\t\u0010Ê\u0001\u001a\u00020/H\u0016J\u0014\u0010Ë\u0001\u001a\r\u0012\b\u0012\u000609R\u00020\u00000Ì\u0001H\u0016J\t\u0010Í\u0001\u001a\u00020]H\u0016J\u000e\u0010Î\u0001\u001a\t\u0012\u0004\u0012\u00020H0Ï\u0001J\u0011\u0010Ð\u0001\u001a\n\u0012\u0005\u0012\u00030Ò\u00010Ñ\u0001H\u0016J\u000e\u0010Ó\u0001\u001a\t\u0012\u0004\u0012\u00020H0Ï\u0001J\t\u0010Ô\u0001\u001a\u00020eH\u0016J\t\u0010Õ\u0001\u001a\u00020hH\u0016J\t\u0010Ö\u0001\u001a\u00020pH\u0016J\t\u0010×\u0001\u001a\u00020pH\u0016J\n\u0010Ø\u0001\u001a\u00030\u0089\u0001H\u0016J\n\u0010Ù\u0001\u001a\u00030\u008e\u0001H\u0016J\n\u0010Ú\u0001\u001a\u00030\u0094\u0001H\u0016J\n\u0010Û\u0001\u001a\u00030\u0096\u0001H\u0016J\f\u0010Ü\u0001\u001a\u0005\u0018\u00010Ý\u0001H\u0016J\f\u0010Þ\u0001\u001a\u0005\u0018\u00010Ý\u0001H\u0016J\n\u0010ß\u0001\u001a\u00030¢\u0001H\u0016J\n\u0010à\u0001\u001a\u00030§\u0001H\u0016J\u001c\u0010á\u0001\u001a\u00030®\u00012\u0007\u0010´\u0001\u001a\u00020\u000e2\u0007\u0010²\u0001\u001a\u00020HH\u0002J\u0013\u0010â\u0001\u001a\u00030®\u00012\u0007\u0010ã\u0001\u001a\u00020_H\u0002J\u0011\u0010ä\u0001\u001a\u00030®\u00012\u0007\u0010²\u0001\u001a\u00020HJ\u001c\u0010å\u0001\u001a\u00030®\u00012\u0007\u0010´\u0001\u001a\u00020\u000e2\u0007\u0010²\u0001\u001a\u00020HH\u0002J\u001c\u0010æ\u0001\u001a\u00030®\u00012\u0007\u0010´\u0001\u001a\u00020\u000e2\u0007\u0010²\u0001\u001a\u00020HH\u0002J\u0011\u0010ç\u0001\u001a\u00030®\u00012\u0007\u0010è\u0001\u001a\u00020HJ\u0011\u0010é\u0001\u001a\u00030®\u00012\u0007\u0010è\u0001\u001a\u00020HJ\b\u0010ê\u0001\u001a\u00030®\u0001J\b\u0010ë\u0001\u001a\u00030®\u0001J\u0015\u0010ì\u0001\u001a\u00030®\u00012\t\b\u0002\u0010í\u0001\u001a\u00020'H\u0002J\n\u0010î\u0001\u001a\u00030®\u0001H\u0002J\u0006\u0010n\u001a\u00020\u0016J\n\u0010ï\u0001\u001a\u00030®\u0001H\u0002J\u0015\u0010ð\u0001\u001a\u00030®\u00012\u000b\b\u0002\u0010ñ\u0001\u001a\u0004\u0018\u00010\u000eJ\n\u0010ò\u0001\u001a\u00030®\u0001H\u0002J\u0019\u0010ó\u0001\u001a\u00030®\u00012\r\u0010ô\u0001\u001a\b\u0018\u000109R\u00020\u0000H\u0007J\n\u0010õ\u0001\u001a\u00030®\u0001H\u0002J\b\u0010ö\u0001\u001a\u00030®\u0001J\t\u0010÷\u0001\u001a\u00020\u0016H\u0016J\u001b\u0010ø\u0001\u001a\u00020\u00162\u0007\u0010ù\u0001\u001a\u00020\u000e2\u0007\u0010ú\u0001\u001a\u00020\u000bH\u0002J\t\u0010û\u0001\u001a\u00020\u0016H\u0016J\u0012\u0010ü\u0001\u001a\u00020\u00162\u0007\u0010ý\u0001\u001a\u00020MH\u0002J\t\u0010þ\u0001\u001a\u00020\u0016H\u0016J\u0007\u0010ÿ\u0001\u001a\u00020\u0016J\u0007\u0010\u0080\u0002\u001a\u00020\u0016J\u0012\u0010\u0081\u0002\u001a\u00020\u00162\u0007\u0010²\u0001\u001a\u00020HH\u0002J\n\u0010\u0082\u0002\u001a\u00030®\u0001H\u0007J\b\u0010\u0083\u0002\u001a\u00030®\u0001J\n\u0010\u0084\u0002\u001a\u00030®\u0001H\u0016J\n\u0010\u0085\u0002\u001a\u00030®\u0001H\u0016J\n\u0010\u0086\u0002\u001a\u00030®\u0001H\u0016J\n\u0010\u0087\u0002\u001a\u00030®\u0001H\u0016J\n\u0010\u0088\u0002\u001a\u00030®\u0001H\u0016J\u0013\u0010\u0089\u0002\u001a\u00030®\u00012\u0007\u0010\u008a\u0002\u001a\u00020\u000bH\u0016J\n\u0010\u008b\u0002\u001a\u00030®\u0001H\u0016J\b\u0010\u008c\u0002\u001a\u00030®\u0001J\n\u0010\u008d\u0002\u001a\u00030®\u0001H\u0002J\n\u0010\u008e\u0002\u001a\u00030®\u0001H\u0002J\u0015\u0010\u008f\u0002\u001a\u00030®\u00012\t\u0010Á\u0001\u001a\u0004\u0018\u00010\u000eH\u0016J\n\u0010\u0090\u0002\u001a\u00030®\u0001H\u0003J \u0010\u0091\u0002\u001a\u00030®\u00012\b\u0010\u0092\u0002\u001a\u00030\u0093\u00022\n\u0010\u0094\u0002\u001a\u0005\u0018\u00010Ý\u0001H\u0002J\n\u0010\u0095\u0002\u001a\u00030®\u0001H\u0002J\n\u0010\u0096\u0002\u001a\u00030®\u0001H\u0002J\u001c\u0010\u0097\u0002\u001a\u00020\u00162\u0007\u0010ã\u0001\u001a\u00020_2\n\u0010º\u0001\u001a\u0005\u0018\u00010\u0098\u0002J\n\u0010\u0099\u0002\u001a\u00030®\u0001H\u0002J\u0013\u0010\u009a\u0002\u001a\u00030®\u00012\u0007\u0010Á\u0001\u001a\u00020\u000eH\u0007J\u001d\u0010\u009b\u0002\u001a\u00030®\u00012\u0007\u0010ý\u0001\u001a\u00020M2\b\u0010\u009c\u0002\u001a\u00030\u009d\u0002H\u0002J\u0013\u0010\u009e\u0002\u001a\u00030®\u00012\u0007\u0010ý\u0001\u001a\u00020MH\u0002J\u001e\u0010\u009f\u0002\u001a\u00030®\u00012\u0007\u0010 \u0002\u001a\u00020\u000b2\t\b\u0002\u0010¡\u0002\u001a\u00020\u000bH\u0007J\u001e\u0010\u009f\u0002\u001a\u00030®\u00012\u0007\u0010Á\u0001\u001a\u00020\u000e2\t\b\u0002\u0010¡\u0002\u001a\u00020\u000bH\u0007J,\u0010¢\u0002\u001a\u00030®\u00012\u0007\u0010£\u0002\u001a\u00020\u000e2\u0007\u0010¤\u0002\u001a\u00020\u000e2\u0007\u0010¥\u0002\u001a\u00020\u000b2\u0007\u0010¦\u0002\u001a\u00020'J\n\u0010§\u0002\u001a\u00030®\u0001H\u0002J\b\u0010¨\u0002\u001a\u00030®\u0001J\u0013\u0010©\u0002\u001a\u00020\u00162\b\u0010ã\u0001\u001a\u00030ª\u0002H\u0002J\u0014\u0010«\u0002\u001a\u00030®\u00012\b\u0010·\u0001\u001a\u00030¸\u0001H\u0002J\u001a\u0010¬\u0002\u001a\u00030®\u00012\u0007\u0010\u00ad\u0002\u001a\u00020\u000e2\u0007\u0010º\u0001\u001a\u00020\u000eR\u000e\u0010\n\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u00118F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0012\u0010\u001b\u001a\u00060\u001cR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010+\u001a\u00020\u00162\u0006\u0010*\u001a\u00020\u00168F@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0018\"\u0004\b-\u0010\u001aR\u0010\u0010.\u001a\u0004\u0018\u00010/X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00101\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u0018\"\u0004\b3\u0010\u001aR\u0010\u00104\u001a\u0004\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u00107\u001a\f\u0012\b\u0012\u000609R\u00020\u000008X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010;\u001a\u00020<¢\u0006\b\n\u0000\u001a\u0004\b=\u0010>R\u001a\u0010?\u001a\u00020@X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR\u0014\u0010E\u001a\b\u0012\u0004\u0012\u00020F0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010G\u001a\b\u0012\u0004\u0012\u00020H0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010I\u001a\b\u0012\u0004\u0012\u00020J0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010K\u001a\b\u0012\u0004\u0012\u00020H0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010L\u001a\b\u0012\u0004\u0012\u00020M0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010N\u001a\b\u0012\u0004\u0012\u00020O0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010P\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010Q\u001a\b\u0012\u0004\u0012\u00020M0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010R\u001a\b\u0012\u0004\u0012\u00020M0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010S\u001a\b\u0012\u0004\u0012\u00020M0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010T\u001a\b\u0012\u0004\u0012\u00020M0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010U\u001a\u0004\u0018\u00010VX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010X\"\u0004\bY\u0010ZR\u000e\u0010[\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\\\u001a\u00020]X\u0082.¢\u0006\u0002\n\u0000R\u001c\u0010^\u001a\u0004\u0018\u00010_X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b`\u0010a\"\u0004\bb\u0010cR\u0010\u0010d\u001a\u0004\u0018\u00010eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010f\u001a\u0004\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010g\u001a\u00020hX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010i\u001a\u0004\u0018\u00010jX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010k\u001a\u00020lX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010m\u001a\u00020OX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010n\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010o\u001a\u0004\u0018\u00010pX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010q\u001a\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\br\u0010\u0018R\u000e\u0010s\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010t\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bt\u0010\u0018\"\u0004\bu\u0010\u001aR\u000e\u0010v\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010w\u001a\u0004\u0018\u00010xX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\by\u0010z\"\u0004\b{\u0010|R\u0010\u0010}\u001a\u0004\u0018\u00010pX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010~\u001a\u0004\u0018\u00010\u007fX\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0080\u0001\u0010\u0081\u0001\"\u0006\b\u0082\u0001\u0010\u0083\u0001R\u001d\u0010\u0084\u0001\u001a\u00020\u0016X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0085\u0001\u0010\u0018\"\u0005\b\u0086\u0001\u0010\u001aR\u0011\u0010\u0087\u0001\u001a\u0004\u0018\u00010xX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0088\u0001\u001a\u00030\u0089\u0001X\u0082.¢\u0006\u0002\n\u0000R\u000f\u0010\u008a\u0001\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u008b\u0001\u001a\u00030\u008c\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u008d\u0001\u001a\u0005\u0018\u00010\u008e\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u008f\u0001\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\u0090\u0001\u001a\u00030\u0091\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u0092\u0001R\u0012\u0010\u0093\u0001\u001a\u0005\u0018\u00010\u0094\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0095\u0001\u001a\u0005\u0018\u00010\u0096\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\u0097\u0001\u001a\u00030\u0098\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u0099\u0001R\u000f\u0010\u009a\u0001\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u009b\u0001\u001a\u0005\u0018\u00010\u009c\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u009d\u0001\u001a\u0005\u0018\u00010\u009e\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u009f\u0001\u001a\u0005\u0018\u00010 \u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010¡\u0001\u001a\u00030¢\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010£\u0001\u001a\u0004\u0018\u00010VX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¤\u0001\u0010X\"\u0005\b¥\u0001\u0010ZR\u0012\u0010¦\u0001\u001a\u0005\u0018\u00010§\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R!\u0010¨\u0001\u001a\u0014\u0012\u0004\u0012\u00020\u000e0©\u0001j\t\u0012\u0004\u0012\u00020\u000e`ª\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010«\u0001\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010¬\u0001\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006¸\u0002"}, d2 = {"Lcom/dotscreen/portal/PortalApplication;", "Landroid/app/Application;", "Lcom/dotscreen/portal/PortalApplicationSingletons;", "Lcom/dotscreen/tv/common/INetRequestProvider;", "Lcom/dotscreen/tv/common/ISmartLibProvider;", "Lcom/dotscreen/tv/common/IForegroudStateProvider;", "Lcom/dotscreen/tv/common/IUserSuspendListener;", "Lcom/dotscreen/tv/common/IMediaPlayerStateProvider;", "Lcom/viaccessorca/analytics/fma/VoFmaControllerListener;", "()V", "MAX_JOB_ID_VALUE", "", "MIN_JOB_ID_VALUE", "TAG", "", "kotlin.jvm.PlatformType", "_activeActivity", "Landroid/app/Activity;", "activeActivity", "getActiveActivity", "()Landroid/app/Activity;", "activeOnDemandPlaybackSession", "", "getActiveOnDemandPlaybackSession", "()Z", "setActiveOnDemandPlaybackSession", "(Z)V", "activityLifecycleCallbacks", "Lcom/dotscreen/portal/PortalApplication$ActivityLifecycleCallbacks;", "alreadyShownOnSelectFloatingAdViews", "", "analyticsManager", "Lcom/dotscreen/farkit/analytics/AnalyticsManager;", "appRestartIsPlanned", "appShouldDegradeOnResume", "applicationsManager", "Lcom/dotscreen/portal/utils/ApplicationsManager;", "backendAvailable", "backendCheckInitialDelay", "", "boService", "Lcom/dotscreen/bokit/services/BOService;", "value", "bootComplete", "getBootComplete", "setBootComplete", "bootSequenceManager", "Lcom/dotscreen/portal/utils/BootSequenceManager;", "bootWithoutInternet", "channelScanning", "getChannelScanning", "setChannelScanning", "checkNetworkJob", "Lkotlinx/coroutines/Job;", "configIsAlreadyLoading", "configLiveData", "Landroidx/lifecycle/MutableLiveData;", "Lcom/dotscreen/portal/PortalApplication$Config;", "connectedToInternet", "connectivityLock", "Ljava/util/concurrent/locks/ReentrantLock;", "getConnectivityLock", "()Ljava/util/concurrent/locks/ReentrantLock;", "connectivityStatusReportManager", "Lcom/dotscreen/portal/utils/reporting/ConnectivityStatusReportManager;", "getConnectivityStatusReportManager", "()Lcom/dotscreen/portal/utils/reporting/ConnectivityStatusReportManager;", "setConnectivityStatusReportManager", "(Lcom/dotscreen/portal/utils/reporting/ConnectivityStatusReportManager;)V", "currentFloatingAdListeners", "Lcom/dotscreen/portal/ads/IFloatingAdListener;", "currentFloatingAds", "Lcom/dotscreen/portal/ads/Ad;", "currentNativeAdListeners", "Lcom/dotscreen/portal/ads/INativeAdListener;", "currentNativeAds", "currentOnBrowseFloatingAdBannerViews", "Lcom/dotscreen/portal/ui/views/FloatingAdBannerView;", "currentOnOffsetFloatingAdBannerViewRunnables", "Ljava/lang/Runnable;", "currentPendingToDisappearAds", "currentlyDisplayedDismissableFloatingAdBannerViews", "currentlyDisplayedFloatingAdBannerViews", "currentlyDisplayedOnClickFloatingAdBannerViews", "currentlyDisplayedOnSelectFloatingAdBannerViews", "debugToast", "Landroid/widget/Toast;", "getDebugToast", "()Landroid/widget/Toast;", "setDebugToast", "(Landroid/widget/Toast;)V", "degradedMode", Device.TYPE, "Lcom/dotscreen/tv/common/utils/Device;", "errorDuringBootSequence", "", "getErrorDuringBootSequence", "()Ljava/lang/Throwable;", "setErrorDuringBootSequence", "(Ljava/lang/Throwable;)V", "errorManager", "Lcom/dotscreen/portal/utils/MegacableErrorManager;", "featuresConfigReloadJob", "featuresManager", "Lcom/dotscreen/portal/utils/FeaturesManager;", "floatingConnectivityStatusView", "Lcom/dotscreen/portal/ui/views/ConnectivityStatusView;", "handler", "Landroid/os/Handler;", "hideFloatingConnectivityStatusViewRunnable", "ignoreRequestErrors", "imageLoader", "Lcom/android/volley/toolbox/ImageLoader;", "instantConnectedToInternetFlag", "getInstantConnectedToInternetFlag", "isDegradedModeFeatureEnabled", "isSartupScreenAlreadyDisplayed", "setSartupScreenAlreadyDisplayed", "liveTvIsInForeground", "loadDisposable", "Lio/reactivex/disposables/Disposable;", "getLoadDisposable", "()Lio/reactivex/disposables/Disposable;", "setLoadDisposable", "(Lio/reactivex/disposables/Disposable;)V", "localImageLoader", "loginErrorReason", "Lcom/dotscreen/portal/utils/LoginError;", "getLoginErrorReason", "()Lcom/dotscreen/portal/utils/LoginError;", "setLoginErrorReason", "(Lcom/dotscreen/portal/utils/LoginError;)V", "mustShowChannelScanning", "getMustShowChannelScanning", "setMustShowChannelScanning", "networkStateDisposable", "networkStateManager", "Lcom/dotscreen/farkit/network/NetworkStateManager;", "networkStatusUpdateReceived", "noInternetProbesFeature", "Lcom/dotscreen/portal/utils/probes/NoInternetProbesFeature;", "notificationManager", "Lcom/dotscreen/tv/common/utils/notifications/NotificationManager;", "portalIsInForeground", "requestErrorListener", "com/dotscreen/portal/PortalApplication$requestErrorListener$1", "Lcom/dotscreen/portal/PortalApplication$requestErrorListener$1;", "requestManager", "Lcom/dotscreen/farkit/network/RequestManager;", "requestQueue", "Lcom/android/volley/RequestQueue;", "requestResponseListener", "com/dotscreen/portal/PortalApplication$requestResponseListener$1", "Lcom/dotscreen/portal/PortalApplication$requestResponseListener$1;", "shouldRefreshView", "smartLibCoreEngineInstance", "Ltv/broadpeak/smartlib/engine/CoreEngine;", "smartLibInstance", "Ltv/broadpeak/smartlib/SmartLib;", "staticVoPlayer", "Lcom/viaccessorca/voplayer/VOPlayer;", "timeshiftingHelper", "Lcom/dotscreen/tv/common/utils/TimeshiftingHelper;", "toast", "getToast", "setToast", "updaterInfo", "Lcom/dotscreen/farkit/updater/UpdaterInfo;", "visitedAdTrackingURLForCurrentPage", "Ljava/util/LinkedHashSet;", "Lkotlin/collections/LinkedHashSet;", "voPlayerUniqueId", "warningNoInternetCount", "addFloatingAdListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "addNativeAdAddedListener", "addToFloatingAdList", "ad", "addToNativeAdList", "dsAdUnitId", "allowNetworkCalls", "broadcastNewNetworkStatus", "networkStatus", "Lcom/dotscreen/farkit/network/NetworkStatus;", "checkIfAdTrackingURLWasVisited", ImagesContract.URL, "clearFloatingAds", "clearNativeAds", "closeLiveApplication", "shouldClose", "dropBreadcrumb", NotificationsContract.COLUMN_TAG, "message", "fetchAdsForPage", "className", "classHashCode", "fetchCurrentDeviceInfo", "getAdFromCurrentNativeAdList", "getAnalyticsManager", "getApplicationsManager", "getBOService", "getBootSequenceManager", "getConfig", "Landroidx/lifecycle/LiveData;", "getCurrentDevice", "getCurrentFloatingAdList", "", "getCurrentLineup", "Lio/reactivex/Single;", "Lcom/dotscreen/bokit/model/Lineup;", "getCurrentNativeAdList", "getErrorManager", "getFeaturesManager", "getImageLoader", "getLocalImageLoader", "getNetworkStateManager", "getNotificationManager", "getRequestManager", "getRequestQueue", "getSmartLib", "", "getSmartLibCoreEngine", "getTimeshiftingHelper", "getUpdaterInfo", "handleCustomNativeAd", "handleError", "error", "handleFloatingAd", "handleInLayoutNativeAd", "handleInSwimlaneNativeAd", "handleInSwimlaneNativeAdClick", "nativeAd", "handleInSwimlaneNativeAdSelection", "hideAllDismissibleFloatingAds", "hideDisplayedFloatingAds", "hideFloatingConnectivityStatusView", "delay", "hidePendingToDisappearAds", "initAnalyticsModules", "initDcaLib", "configUrl", "initFirebase", "initSentry", "config", "initTimeshift", "initVOFMA", "isActivePlaybackSession", "isActivityReferenceOutdated", "activityName", "activityHashCode", "isDegradedMode", "isFloatingAdPlacementValid", "floatingAdBannerView", "isInForeground", "isScreenOn", "isShowingDismissableFloatingAds", "isValidNativeAd", "loadConfig", "onBrowseActionInActivity", "onConfigurationReloaded", "onConfigurationReloadedFailed", "onCreate", "onInitCompleted", "onInitFailed", "onTrimMemory", "level", "onUserSuspend", "preventNetworkCalls", "recreateActivity", "refreshHome", "reinitSmartLibIfNeeded", "reloadFeaturesConfig", "reportSentryEventToVO", NotificationCompat.CATEGORY_EVENT, "Lio/sentry/SentryEvent;", "hint", "restartApplicationWithPopupOrPlanForLater", "restartOrKillXView", "shouldTriggerLimitedMode", "Ljava/net/URL;", "showDebugFloatingPanel", "showDebugToastMessage", "showDismissibleFloatingAd", "displayType", "Lcom/dotscreen/portal/ads/Ad$DisplayTrigger;", "showFloatingAd", "showToastMessage", "messageResId", "length", "startBannerHidingCountDown", "runtimeTag", "owningActivityName", "owningActivityHashcode", "whenResourceDisappearsInSec", "startFeaturesConfigReloadJob", "toggleNetworkCalls", "unknownError", "Lcom/android/volley/VolleyError;", "updateFloatingStatusView", "visitAdTrackingURL", "adUnitId", "ActivityLifecycleCallbacks", "ApplicationStateBroadcastReceiver", "ApplicationSwimLane", "AvatarsConfig", "Companion", "Config", "QuiescentRebootWorker", "RequestErrorBroadcastReceiver", "ScreenOnOffBroadcastReceiver", "SwimLane", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class PortalApplication extends Application implements PortalApplicationSingletons, INetRequestProvider, ISmartLibProvider, IForegroudStateProvider, IUserSuspendListener, IMediaPlayerStateProvider, VoFmaControllerListener {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private Activity _activeActivity;
    private boolean activeOnDemandPlaybackSession;
    private AnalyticsManager analyticsManager;
    private boolean appRestartIsPlanned;
    private boolean appShouldDegradeOnResume;
    private ApplicationsManager applicationsManager;
    private boolean backendAvailable;
    private BOService boService;
    private boolean bootComplete;
    private BootSequenceManager bootSequenceManager;
    private boolean channelScanning;
    private Job checkNetworkJob;
    private boolean configIsAlreadyLoading;
    private boolean connectedToInternet;
    public ConnectivityStatusReportManager connectivityStatusReportManager;
    private Toast debugToast;
    private boolean degradedMode;
    private com.dotscreen.tv.common.utils.Device device;
    private Throwable errorDuringBootSequence;
    private MegacableErrorManager errorManager;
    private Job featuresConfigReloadJob;
    private ConnectivityStatusView floatingConnectivityStatusView;
    private boolean ignoreRequestErrors;
    private ImageLoader imageLoader;
    private boolean isSartupScreenAlreadyDisplayed;
    private boolean liveTvIsInForeground;
    private Disposable loadDisposable;
    private ImageLoader localImageLoader;
    private LoginError loginErrorReason;
    private Disposable networkStateDisposable;
    private NetworkStateManager networkStateManager;
    private NotificationManager notificationManager;
    private RequestManager requestManager;
    private RequestQueue requestQueue;
    private boolean shouldRefreshView;
    private CoreEngine smartLibCoreEngineInstance;
    private SmartLib smartLibInstance;
    private VOPlayer staticVoPlayer;
    private Toast toast;
    private UpdaterInfo updaterInfo;
    private String voPlayerUniqueId;
    private int warningNoInternetCount;
    private final String TAG = PortalApplication.class.getSimpleName();
    private final int MIN_JOB_ID_VALUE = 3;
    private final int MAX_JOB_ID_VALUE = 1000000;
    private long backendCheckInitialDelay = 10000;
    private boolean bootWithoutInternet = true;
    private final Handler handler = new Handler();
    private final ActivityLifecycleCallbacks activityLifecycleCallbacks = new ActivityLifecycleCallbacks();
    private final TimeshiftingHelper timeshiftingHelper = new TimeshiftingHelper();
    private boolean mustShowChannelScanning = true;
    private boolean portalIsInForeground = true;
    private boolean networkStatusUpdateReceived = true;
    private final FeaturesManager featuresManager = new FeaturesManager();
    private final NoInternetProbesFeature noInternetProbesFeature = new NoInternetProbesFeature(this);
    private boolean isDegradedModeFeatureEnabled = true;
    private final Set<FloatingAdBannerView> currentlyDisplayedFloatingAdBannerViews = new LinkedHashSet();
    private final Set<FloatingAdBannerView> currentlyDisplayedDismissableFloatingAdBannerViews = new LinkedHashSet();
    private final Set<Runnable> currentOnOffsetFloatingAdBannerViewRunnables = new LinkedHashSet();
    private final Set<FloatingAdBannerView> currentOnBrowseFloatingAdBannerViews = new LinkedHashSet();
    private final Set<Ad> currentNativeAds = new LinkedHashSet();
    private final Set<Ad> currentFloatingAds = new LinkedHashSet();
    private final Set<INativeAdListener> currentNativeAdListeners = new LinkedHashSet();
    private final Set<IFloatingAdListener> currentFloatingAdListeners = new LinkedHashSet();
    private final Set<String> alreadyShownOnSelectFloatingAdViews = new LinkedHashSet();
    private final Set<FloatingAdBannerView> currentlyDisplayedOnSelectFloatingAdBannerViews = new LinkedHashSet();
    private final Set<FloatingAdBannerView> currentlyDisplayedOnClickFloatingAdBannerViews = new LinkedHashSet();
    private final LinkedHashSet<String> visitedAdTrackingURLForCurrentPage = new LinkedHashSet<>(100);
    private final Set<String> currentPendingToDisappearAds = new LinkedHashSet();
    private final MutableLiveData<Config> configLiveData = new MutableLiveData<>();
    private final Runnable hideFloatingConnectivityStatusViewRunnable = new Runnable() { // from class: com.dotscreen.portal.PortalApplication$hideFloatingConnectivityStatusViewRunnable$1
        @Override // java.lang.Runnable
        public final void run() {
            Log.i(this.this$0.TAG, "hideFloatingConnectivityStatusViewRunnable");
            WindowManager windowManager = (WindowManager) this.this$0.getSystemService(WindowManager.class);
            ConnectivityStatusView connectivityStatusView = this.this$0.floatingConnectivityStatusView;
            if (connectivityStatusView != null) {
                try {
                    Log.w(this.this$0.TAG, "removing floatingConnectivityStatusView: " + connectivityStatusView);
                    if (windowManager != null) {
                        windowManager.removeView(connectivityStatusView);
                    }
                    this.this$0.floatingConnectivityStatusView = (ConnectivityStatusView) null;
                    Unit unit = Unit.INSTANCE;
                } catch (Exception e) {
                    Integer.valueOf(Log.w(this.this$0.TAG, "Exception", e));
                }
            }
        }
    };
    private final ReentrantLock connectivityLock = new ReentrantLock();
    private final PortalApplication$requestErrorListener$1 requestErrorListener = new PortalApplication$requestErrorListener$1(this);
    private final PortalApplication$requestResponseListener$1 requestResponseListener = new OnRequestResponseListener() { // from class: com.dotscreen.portal.PortalApplication$requestResponseListener$1
        @Override // com.dotscreen.bokit.OnRequestResponseListener
        public void onNetworkResponse(String url, boolean cacheHit) {
            Intrinsics.checkParameterIsNotNull(url, "url");
            if (this.this$0.degradedMode) {
                Log.e(this.this$0.TAG, "onNetworkResponse (degradedMode): in Portal Application - cacheHit=" + cacheHit + "; url=" + url);
            } else {
                Log.v(this.this$0.TAG, "onNetworkResponse: in Portal Application - cacheHit=" + cacheHit + "; url=" + url);
            }
            DSLog.INSTANCE.r(url, cacheHit);
        }
    };

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Ad.DisplayTrigger.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Ad.DisplayTrigger.Auto.ordinal()] = 1;
            $EnumSwitchMapping$0[Ad.DisplayTrigger.OnOffset.ordinal()] = 2;
            $EnumSwitchMapping$0[Ad.DisplayTrigger.OnBrowse.ordinal()] = 3;
        }
    }

    public final void showDebugToastMessage(String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
    }

    public static final /* synthetic */ NetworkStateManager access$getNetworkStateManager$p(PortalApplication portalApplication) {
        NetworkStateManager networkStateManager = portalApplication.networkStateManager;
        if (networkStateManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("networkStateManager");
        }
        return networkStateManager;
    }

    /* JADX INFO: renamed from: isSartupScreenAlreadyDisplayed, reason: from getter */
    public final boolean getIsSartupScreenAlreadyDisplayed() {
        return this.isSartupScreenAlreadyDisplayed;
    }

    public final void setSartupScreenAlreadyDisplayed(boolean z) {
        this.isSartupScreenAlreadyDisplayed = z;
    }

    public final boolean getBootComplete() {
        return this.bootComplete;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setBootComplete(boolean z) {
        this.bootComplete = z;
    }

    public final LoginError getLoginErrorReason() {
        return this.loginErrorReason;
    }

    public final void setLoginErrorReason(LoginError loginError) {
        this.loginErrorReason = loginError;
    }

    public final Throwable getErrorDuringBootSequence() {
        return this.errorDuringBootSequence;
    }

    public final void setErrorDuringBootSequence(Throwable th) {
        this.errorDuringBootSequence = th;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getInstantConnectedToInternetFlag() {
        NetworkStateManager networkStateManager = this.networkStateManager;
        if (networkStateManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("networkStateManager");
        }
        return networkStateManager.hasInternetCapability();
    }

    public final boolean getActiveOnDemandPlaybackSession() {
        return this.activeOnDemandPlaybackSession;
    }

    public final void setActiveOnDemandPlaybackSession(boolean z) {
        this.activeOnDemandPlaybackSession = z;
    }

    /* JADX INFO: renamed from: getActiveActivity, reason: from getter */
    public final Activity get_activeActivity() {
        return this._activeActivity;
    }

    public final boolean getMustShowChannelScanning() {
        return this.mustShowChannelScanning;
    }

    public final void setMustShowChannelScanning(boolean z) {
        this.mustShowChannelScanning = z;
    }

    public final boolean getChannelScanning() {
        return this.channelScanning;
    }

    public final void setChannelScanning(boolean z) {
        this.channelScanning = z;
    }

    public final ConnectivityStatusReportManager getConnectivityStatusReportManager() {
        ConnectivityStatusReportManager connectivityStatusReportManager = this.connectivityStatusReportManager;
        if (connectivityStatusReportManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("connectivityStatusReportManager");
        }
        return connectivityStatusReportManager;
    }

    public final void setConnectivityStatusReportManager(ConnectivityStatusReportManager connectivityStatusReportManager) {
        Intrinsics.checkParameterIsNotNull(connectivityStatusReportManager, "<set-?>");
        this.connectivityStatusReportManager = connectivityStatusReportManager;
    }

    /* JADX INFO: compiled from: PortalApplication.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/dotscreen/portal/PortalApplication$AvatarsConfig;", "", "path", "", "total", "", "(Ljava/lang/String;I)V", "getPath", "()Ljava/lang/String;", "getTotal", "()I", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class AvatarsConfig {
        private final String path;
        private final int total;

        public AvatarsConfig(String path, int i) {
            Intrinsics.checkParameterIsNotNull(path, "path");
            this.path = path;
            this.total = i;
        }

        public final String getPath() {
            return this.path;
        }

        public final int getTotal() {
            return this.total;
        }
    }

    /* JADX INFO: compiled from: PortalApplication.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/dotscreen/portal/PortalApplication$SwimLane;", "", "type", "", "(Ljava/lang/String;)V", "getType", "()Ljava/lang/String;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static class SwimLane {
        private final String type;

        public SwimLane(String type) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            this.type = type;
        }

        public final String getType() {
            return this.type;
        }
    }

    /* JADX INFO: compiled from: PortalApplication.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\nB\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000b"}, d2 = {"Lcom/dotscreen/portal/PortalApplication$ApplicationSwimLane;", "Lcom/dotscreen/portal/PortalApplication$SwimLane;", "type", "", "items", "", "Lcom/dotscreen/portal/PortalApplication$ApplicationSwimLane$Item;", "(Ljava/lang/String;Ljava/util/List;)V", "getItems", "()Ljava/util/List;", "Item", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class ApplicationSwimLane extends SwimLane {
        private final List<Item> items;

        /* JADX INFO: compiled from: PortalApplication.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/dotscreen/portal/PortalApplication$ApplicationSwimLane$Item;", "", "name", "", "packageName", "bannerUrl", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBannerUrl", "()Ljava/lang/String;", "getName", "getPackageName", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
        public static final class Item {
            private final String bannerUrl;
            private final String name;
            private final String packageName;

            public Item(String str, String str2, String str3) {
                this.name = str;
                this.packageName = str2;
                this.bannerUrl = str3;
            }

            public final String getBannerUrl() {
                return this.bannerUrl;
            }

            public final String getName() {
                return this.name;
            }

            public final String getPackageName() {
                return this.packageName;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ApplicationSwimLane(String type, List<Item> items) {
            super(type);
            Intrinsics.checkParameterIsNotNull(type, "type");
            Intrinsics.checkParameterIsNotNull(items, "items");
            this.items = items;
        }

        public final List<Item> getItems() {
            return this.items;
        }
    }

    /* JADX INFO: compiled from: PortalApplication.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u0006\u0010N\u001a\u000203R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u001d\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u001e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001a\u0010'\u001a\u00020\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010$\"\u0004\b)\u0010&R\u0011\u0010*\u001a\u00020+¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-RX\u00104\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b0\u0012\b\b1\u0012\u0004\b\b(2\u0012\u0004\u0012\u0002030/2#\u0010.\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b0\u0012\b\b1\u0012\u0004\b\b(2\u0012\u0004\u0012\u0002030/@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R\u0011\u00107\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\u000eR\u0011\u00109\u001a\u00020:¢\u0006\b\n\u0000\u001a\u0004\b;\u0010<R\u0011\u0010=\u001a\u00020>¢\u0006\b\n\u0000\u001a\u0004\b?\u0010@R\u0011\u0010A\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\bB\u0010\u0018R\u0011\u0010C\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\bD\u0010\u0018R\u0017\u0010E\u001a\b\u0012\u0004\u0012\u00020G0F¢\u0006\b\n\u0000\u001a\u0004\bH\u0010IR\"\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010.\u001a\u0004\u0018\u00010\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010KR\u0010\u0010L\u001a\u0004\u0018\u00010MX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006O"}, d2 = {"Lcom/dotscreen/portal/PortalApplication$Config;", "", "json", "Lorg/json/JSONObject;", ToolboxAnalyticsModuleKt.KEY_USER, "Lcom/dotscreen/tv/common/bokit/RightVUser;", "loginFailed", "", "loginFailedReason", "Lcom/dotscreen/portal/utils/LoginError;", "(Lcom/dotscreen/portal/PortalApplication;Lorg/json/JSONObject;Lcom/dotscreen/tv/common/bokit/RightVUser;ZLcom/dotscreen/portal/utils/LoginError;)V", "actorBaseUrl", "", "getActorBaseUrl", "()Ljava/lang/String;", "avatarsConfig", "Lcom/dotscreen/portal/PortalApplication$AvatarsConfig;", "getAvatarsConfig", "()Lcom/dotscreen/portal/PortalApplication$AvatarsConfig;", "designManager", "Lcom/dotscreen/farkit/design/DesignManager;", "getDesignManager", "()Lcom/dotscreen/farkit/design/DesignManager;", "getLoginFailed", "()Z", "setLoginFailed", "(Z)V", "getLoginFailedReason", "()Lcom/dotscreen/portal/utils/LoginError;", "providers", "", "getProviders", "()Ljava/util/Map;", "quiescentRebootMaxTime", "", "getQuiescentRebootMaxTime", "()J", "setQuiescentRebootMaxTime", "(J)V", "quiescentRebootMinTime", "getQuiescentRebootMinTime", "setQuiescentRebootMinTime", "quiescentRebootMinUptimeHours", "", "getQuiescentRebootMinUptimeHours", "()I", "<set-?>", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "message", "", "reinitSmartLibIfNeeded", "getReinitSmartLibIfNeeded", "()Lkotlin/jvm/functions/Function1;", "sentryFilter", "getSentryFilter", "sentryRateLimitManagement", "Lcom/dotscreen/tv/common/utils/SentryRateLimitManagement;", "getSentryRateLimitManagement", "()Lcom/dotscreen/tv/common/utils/SentryRateLimitManagement;", "sentryReportFlags", "Lcom/dotscreen/tv/common/utils/SentryReportFlags;", "getSentryReportFlags", "()Lcom/dotscreen/tv/common/utils/SentryReportFlags;", "shouldClearMemory", "getShouldClearMemory", "shouldLogLowMemory", "getShouldLogLowMemory", "swimLanes", "", "Lcom/dotscreen/portal/PortalApplication$SwimLane;", "getSwimLanes", "()Ljava/util/List;", "getUser", "()Lcom/dotscreen/tv/common/bokit/RightVUser;", "userStatusDisposable", "Lio/reactivex/disposables/Disposable;", "startListeners", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public final class Config {
        private final String actorBaseUrl;
        private final AvatarsConfig avatarsConfig;
        private final DesignManager designManager;
        private boolean loginFailed;
        private final LoginError loginFailedReason;
        private final Map<String, String> providers;
        private long quiescentRebootMaxTime;
        private long quiescentRebootMinTime;
        private final int quiescentRebootMinUptimeHours;
        private Function1<? super String, Unit> reinitSmartLibIfNeeded;
        private final String sentryFilter;
        private final SentryRateLimitManagement sentryRateLimitManagement;
        private final SentryReportFlags sentryReportFlags;
        private final boolean shouldClearMemory;
        private final boolean shouldLogLowMemory;
        private final List<SwimLane> swimLanes;
        final /* synthetic */ PortalApplication this$0;
        private RightVUser user;
        private Disposable userStatusDisposable;

        /* JADX WARN: Code duplicated, block: B:74:0x02f8  */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r8v0, types: [java.util.List, kotlin.jvm.internal.DefaultConstructorMarker] */
        /* JADX WARN: Type inference failed for: r8v10 */
        /* JADX WARN: Type inference failed for: r8v11 */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        public Config(PortalApplication portalApplication, JSONObject json, RightVUser rightVUser, boolean z, LoginError loginError) throws JSONException {
            AvatarsConfig avatarsConfig;
            JSONArray jSONArray;
            int i;
            ApplicationSwimLane swimLane;
            Intrinsics.checkParameterIsNotNull(json, "json");
            this.this$0 = portalApplication;
            this.loginFailed = z;
            this.loginFailedReason = loginError;
            this.providers = new LinkedHashMap();
            this.reinitSmartLibIfNeeded = new Function1<String, Unit>() { // from class: com.dotscreen.portal.PortalApplication$Config$reinitSmartLibIfNeeded$1
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String str) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str) {
                    invoke2(str);
                    return Unit.INSTANCE;
                }
            };
            this.user = rightVUser;
            this.sentryFilter = LocalSearchProvider.NO_LIVE_CONTENTS;
            PortalApplication portalApplication2 = portalApplication;
            JSONObject jSONObject = json.getJSONObject("design");
            Intrinsics.checkExpressionValueIsNotNull(jSONObject, "json.getJSONObject(\"design\")");
            RequestManager requestManager = portalApplication.requestManager;
            if (requestManager == null) {
                Intrinsics.throwNpe();
            }
            this.designManager = new DesignManager(portalApplication2, jSONObject, requestManager);
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArrayOptJSONArray = json.optJSONArray("swimlanes");
            if (jSONArrayOptJSONArray != null) {
                int length = jSONArrayOptJSONArray.length();
                int i2 = 0;
                while (i2 < length) {
                    JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i2);
                    String type = jSONObject2.optString("type", "");
                    if (Intrinsics.areEqual(type, "featured_apps")) {
                        JSONArray jSONArray2 = jSONObject2.getJSONArray(ToolboxAnalyticsModuleKt.KEY_CONTENT);
                        ArrayList arrayList2 = new ArrayList();
                        int length2 = jSONArray2.length();
                        int i3 = 0;
                        while (i3 < length2) {
                            JSONObject jSONObject3 = jSONArray2.getJSONObject(i3);
                            arrayList2.add(new ApplicationSwimLane.Item(jSONObject3.optString("name"), jSONObject3.optString("package"), jSONObject3.optString("banner")));
                            i3++;
                            jSONArrayOptJSONArray = jSONArrayOptJSONArray;
                            length = length;
                            jSONArray2 = jSONArray2;
                        }
                        jSONArray = jSONArrayOptJSONArray;
                        i = length;
                        swimLane = new ApplicationSwimLane(type, CollectionsKt.toList(arrayList2));
                    } else {
                        jSONArray = jSONArrayOptJSONArray;
                        i = length;
                        Intrinsics.checkExpressionValueIsNotNull(type, "type");
                        swimLane = new SwimLane(type);
                    }
                    arrayList.add(swimLane);
                    i2++;
                    jSONArrayOptJSONArray = jSONArray;
                    length = i;
                }
                Unit unit = Unit.INSTANCE;
            }
            this.swimLanes = CollectionsKt.toList(arrayList);
            if (json.has("target_advertising")) {
                String voAdUnitConfig = json.getString("target_advertising");
                TargetedAdsConfigProvider.Companion provider = TargetedAdsConfigProvider.INSTANCE;
                Intrinsics.checkExpressionValueIsNotNull(voAdUnitConfig, "voAdUnitConfig");
                provider.initTargetAdvertisingConfig(voAdUnitConfig);
            } else {
                DSLog.INSTANCE.adLogE("[INIT CONFIG] Config was not found");
            }
            if (json.has("backend_check_delay")) {
                portalApplication.backendCheckInitialDelay = ((long) 1000) * json.getLong("backend_check_delay");
            }
            Log.i(portalApplication.TAG, "backendCheckDelay = " + portalApplication.backendCheckInitialDelay);
            int i4 = 1;
            String string = 0;
            string = 0;
            if (json.has("analytics")) {
                JSONObject jSONObject4 = json.getJSONObject("analytics");
                if (jSONObject4.has("smartLib") && CommonUtils.getCurrentPlatform() != 16) {
                    JSONObject jSONObjectOptJSONObject = jSONObject4.optJSONObject("smartLib");
                    new LoggerManager().setLogLevel(4);
                    final String strOptString = jSONObjectOptJSONObject.optString("analyticsAddress", "");
                    final String strOptString2 = jSONObjectOptJSONObject.optString("nanoCDNHost", "");
                    final String strOptString3 = jSONObjectOptJSONObject.optString("broadpeakDomainNames", "");
                    Function1<String, Unit> function1 = new Function1<String, Unit>() { // from class: com.dotscreen.portal.PortalApplication.Config.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(String str) {
                            invoke2(str);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(String str) {
                            CoreEngine coreEngine = Config.this.this$0.smartLibCoreEngineInstance;
                            if (coreEngine == null || !coreEngine.isInitialized()) {
                                if (str != null) {
                                    Log.e("SmartLib", str);
                                }
                                Log.d("SmartLib", "CoreEngine not initialized, initializing SmartLib...");
                                VOPlayerBpkExtended.initSmartLib(Config.this.this$0, strOptString, strOptString2, strOptString3);
                                Config.this.this$0.smartLibInstance = SmartLib.getInstance();
                                Config.this.this$0.smartLibCoreEngineInstance = CoreEngine.getInstance();
                                StringBuilder sb = new StringBuilder();
                                sb.append("CoreEngine.getInstance().isInitialized = ");
                                CoreEngine coreEngine2 = CoreEngine.getInstance();
                                Intrinsics.checkExpressionValueIsNotNull(coreEngine2, "CoreEngine.getInstance()");
                                sb.append(coreEngine2.isInitialized());
                                Log.d("SmartLib", sb.toString());
                            }
                        }
                    };
                    this.reinitSmartLibIfNeeded = function1;
                    function1.invoke(null);
                }
                if (jSONObject4.has("sentry")) {
                    JSONObject jSONObject5 = jSONObject4.getJSONObject("sentry");
                    this.sentryReportFlags = new SentryReportFlags(jSONObject5.optDouble("voPlayer", 1.0d), jSONObject5.optDouble("uiOptimizer", 1.0d), jSONObject5.optDouble("rightv", 1.0d), jSONObject5.optDouble("updater", 1.0d), jSONObject5.optDouble("degraded", 1.0d), jSONObject5.optDouble("internet", 1.0d));
                    ArrayList arrayList3 = new ArrayList();
                    if (!jSONObject5.has("rateLimit") || !jSONObject5.getJSONObject("rateLimit").has("\"snPatterns\"")) {
                        Log.e(portalApplication.TAG, "Issue when parsing sentry rate limit section");
                    } else {
                        JSONArray jSONArray3 = jSONObject5.getJSONObject("rateLimit").getJSONArray("snPatterns");
                        int length3 = jSONArray3.length();
                        for (int i5 = 0; i5 < length3; i5++) {
                            String string2 = jSONArray3.getString(i5);
                            Intrinsics.checkExpressionValueIsNotNull(string2, "snPatternsJson.getString(i)");
                            arrayList3.add(string2);
                        }
                    }
                    this.sentryRateLimitManagement = arrayList3.isEmpty() ^ true ? new SentryRateLimitManagement(arrayList3) : new SentryRateLimitManagement(string, i4, string);
                } else {
                    this.sentryReportFlags = new SentryReportFlags(1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d);
                    this.sentryRateLimitManagement = new SentryRateLimitManagement(string, i4, string);
                }
            } else {
                this.sentryReportFlags = new SentryReportFlags(1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d);
                this.sentryRateLimitManagement = new SentryRateLimitManagement(string, i4, string);
            }
            if (json.has("analytics")) {
                JSONObject jSONObject6 = json.getJSONObject("analytics");
                if (jSONObject6.has("dca") && jSONObject6.getJSONObject("dca").optBoolean("enabled", false)) {
                    try {
                        String string3 = jSONObject6.getJSONObject("dca").getString(ImagesContract.URL);
                        if (string3 == null) {
                            string3 = CommonUtils.OTA_SERVER_URL;
                        }
                        portalApplication.initDcaLib(string3);
                    } catch (Exception unused) {
                        Log.w(portalApplication.TAG, "DCA init failed with crash");
                    }
                }
            }
            if (json.has("analytics")) {
                JSONObject jSONObject7 = json.getJSONObject("analytics");
                if (jSONObject7.has("compass")) {
                    JSONObject jSONObject8 = jSONObject7.getJSONObject("compass");
                    CommonUtils.ZAP_EVENT_DELAY = jSONObject8.optLong("zapEventDelay", 30L);
                    CommonUtils.CONTENT_END_EVENT_PERCENT = jSONObject8.optInt("contentEndEventPercent", 95);
                }
            }
            if (json.has("avatars")) {
                JSONObject jSONObject9 = json.getJSONObject("avatars");
                if (jSONObject9.has("path") && jSONObject9.has("total")) {
                    String string4 = jSONObject9.getString("path");
                    Intrinsics.checkExpressionValueIsNotNull(string4, "jsonAvatars.getString(\"path\")");
                    avatarsConfig = new AvatarsConfig(string4, jSONObject9.getInt("total"));
                } else {
                    avatarsConfig = null;
                }
            } else {
                avatarsConfig = null;
            }
            this.avatarsConfig = avatarsConfig;
            if (json.has("provider")) {
                JSONObject jSONObject10 = json.getJSONObject("provider");
                if (jSONObject10.has("path") && jSONObject10.has(ToolboxAnalyticsModuleKt.KEY_CONTENT)) {
                    String string5 = jSONObject10.getString("path");
                    JSONArray jSONArray4 = jSONObject10.getJSONArray(ToolboxAnalyticsModuleKt.KEY_CONTENT);
                    int length4 = jSONArray4.length();
                    for (int i6 = 0; i6 < length4; i6++) {
                        JSONObject jSONObject11 = jSONArray4.getJSONObject(i6);
                        Map<String, String> map = this.providers;
                        String string6 = jSONObject11.getString("id");
                        Intrinsics.checkExpressionValueIsNotNull(string6, "provider.getString(\"id\")");
                        map.put(string6, string5 + jSONObject11.getString(TtmlNode.TAG_IMAGE));
                    }
                }
            }
            if (!json.has("quiescentReboot")) {
                Log.w(portalApplication.TAG, "No quiescent reboot config");
                this.quiescentRebootMinUptimeHours = 72;
                this.quiescentRebootMinTime = 3600000L;
                this.quiescentRebootMaxTime = WorkRequest.MAX_BACKOFF_MILLIS;
            } else {
                JSONObject jSONObject12 = json.getJSONObject("quiescentReboot");
                this.quiescentRebootMinUptimeHours = jSONObject12.optInt("minUptimeHours", 72);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mma", Locale.US);
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                try {
                    Date date = simpleDateFormat.parse(jSONObject12.getString("minTime"));
                    Intrinsics.checkExpressionValueIsNotNull(date, "formatter.parse(json.getString(\"minTime\"))");
                    this.quiescentRebootMinTime = date.getTime();
                    Date date2 = simpleDateFormat.parse(jSONObject12.getString("maxTime"));
                    Intrinsics.checkExpressionValueIsNotNull(date2, "formatter.parse(json.getString(\"maxTime\"))");
                    this.quiescentRebootMaxTime = date2.getTime();
                } catch (Exception e) {
                    Log.w(portalApplication.TAG, "Error parsing quiescent reboot config", e);
                    this.quiescentRebootMinTime = 3600000L;
                    this.quiescentRebootMaxTime = WorkRequest.MAX_BACKOFF_MILLIS;
                }
                Log.v(portalApplication.TAG, "quiescentRebootMinUptimeHours = " + this.quiescentRebootMinUptimeHours);
                Log.v(portalApplication.TAG, "quiescentRebootMinTime = " + this.quiescentRebootMinTime);
                Log.v(portalApplication.TAG, "quiescentRebootMaxTime = " + this.quiescentRebootMaxTime);
            }
            this.actorBaseUrl = (json.has("actors") && json.getJSONObject("actors").has("path")) ? json.getJSONObject("actors").getString("path") : null;
            Constants.INSTANCE.setPROD_CATCHUP_ROOT_CATALOG_ID((json.has("catalogs") && json.getJSONObject("catalogs").has("catchup")) ? json.getJSONObject("catalogs").getString("catchup") : null);
            Constants.INSTANCE.setPROD_VOD_ROOT_CATALOG_ID((json.has("catalogs") && json.getJSONObject("catalogs").has("vod")) ? json.getJSONObject("catalogs").getString("vod") : null);
            Constants constants = Constants.INSTANCE;
            if (json.has("catalogs") && json.getJSONObject("catalogs").has("homeReco")) {
                string = json.getJSONObject("catalogs").getString("homeReco");
            }
            constants.setPROD_HOME_RECO_ROOT_CATALOG_ID(string);
            if (json.has("bootSequence")) {
                JSONObject jSONObject13 = json.getJSONObject("bootSequence");
                BootSequenceManager bootSequenceManager = portalApplication.bootSequenceManager;
                if (bootSequenceManager == null) {
                    Intrinsics.throwNpe();
                }
                bootSequenceManager.setMinTimeoutRetry(jSONObject13.optInt("minTimeoutRetry", 10));
                BootSequenceManager bootSequenceManager2 = portalApplication.bootSequenceManager;
                if (bootSequenceManager2 == null) {
                    Intrinsics.throwNpe();
                }
                bootSequenceManager2.setMaxTimeoutRetry(jSONObject13.optInt("maxTimeoutRetry", 30));
            }
            this.shouldClearMemory = json.optBoolean("freeMemory", false);
            this.shouldLogLowMemory = json.optBoolean("logLowMemory", false);
        }

        public /* synthetic */ Config(PortalApplication portalApplication, JSONObject jSONObject, RightVUser rightVUser, boolean z, LoginError loginError, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(portalApplication, jSONObject, rightVUser, (i & 4) != 0 ? false : z, (i & 8) != 0 ? (LoginError) null : loginError);
        }

        public final boolean getLoginFailed() {
            return this.loginFailed;
        }

        public final LoginError getLoginFailedReason() {
            return this.loginFailedReason;
        }

        public final void setLoginFailed(boolean z) {
            this.loginFailed = z;
        }

        public final RightVUser getUser() {
            return this.user;
        }

        public final DesignManager getDesignManager() {
            return this.designManager;
        }

        public final List<SwimLane> getSwimLanes() {
            return this.swimLanes;
        }

        public final AvatarsConfig getAvatarsConfig() {
            return this.avatarsConfig;
        }

        public final Map<String, String> getProviders() {
            return this.providers;
        }

        public final String getActorBaseUrl() {
            return this.actorBaseUrl;
        }

        public final Function1<String, Unit> getReinitSmartLibIfNeeded() {
            return this.reinitSmartLibIfNeeded;
        }

        public final SentryReportFlags getSentryReportFlags() {
            return this.sentryReportFlags;
        }

        public final SentryRateLimitManagement getSentryRateLimitManagement() {
            return this.sentryRateLimitManagement;
        }

        public final boolean getShouldClearMemory() {
            return this.shouldClearMemory;
        }

        public final int getQuiescentRebootMinUptimeHours() {
            return this.quiescentRebootMinUptimeHours;
        }

        public final long getQuiescentRebootMinTime() {
            return this.quiescentRebootMinTime;
        }

        public final void setQuiescentRebootMinTime(long j) {
            this.quiescentRebootMinTime = j;
        }

        public final long getQuiescentRebootMaxTime() {
            return this.quiescentRebootMaxTime;
        }

        public final void setQuiescentRebootMaxTime(long j) {
            this.quiescentRebootMaxTime = j;
        }

        public final boolean getShouldLogLowMemory() {
            return this.shouldLogLowMemory;
        }

        public final String getSentryFilter() {
            return this.sentryFilter;
        }

        public final void startListeners() {
            UserService userService;
            BehaviorSubject<UserStatus> status;
            Observable<UserStatus> observableSubscribeOn;
            Observable<UserStatus> observableObserveOn;
            BOService bOService = this.this$0.boService;
            this.userStatusDisposable = (bOService == null || (userService = bOService.getUserService()) == null || (status = userService.getStatus()) == null || (observableSubscribeOn = status.subscribeOn(Schedulers.io())) == null || (observableObserveOn = observableSubscribeOn.observeOn(AndroidSchedulers.mainThread())) == null) ? null : observableObserveOn.subscribe(new Consumer<UserStatus>() { // from class: com.dotscreen.portal.PortalApplication$Config$startListeners$1
                @Override // io.reactivex.functions.Consumer
                public final void accept(UserStatus userStatus) {
                    Log.d(this.this$0.this$0.TAG, "Config: new status: " + userStatus);
                    if (userStatus instanceof UserStatus.Anonymous) {
                        this.this$0.user = (RightVUser) null;
                    } else if (userStatus instanceof UserStatus.Authenticated) {
                        PortalApplication.Config config = this.this$0;
                        User user = ((UserStatus.Authenticated) userStatus).getUser();
                        if (user == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVUser");
                        }
                        config.user = (RightVUser) user;
                    }
                    Log.d(this.this$0.this$0.TAG, "Config: post new Config " + this.this$0);
                    this.this$0.this$0.configLiveData.postValue(this.this$0);
                }
            }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.PortalApplication$Config$startListeners$2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                    if (th != null) {
                        th.printStackTrace();
                    }
                }
            });
        }
    }

    /* JADX INFO: compiled from: PortalApplication.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"Lcom/dotscreen/portal/PortalApplication$ApplicationStateBroadcastReceiver;", "Landroid/content/BroadcastReceiver;", "(Lcom/dotscreen/portal/PortalApplication;)V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public final class ApplicationStateBroadcastReceiver extends BroadcastReceiver {
        public ApplicationStateBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String stringExtra;
            if (intent == null || (stringExtra = intent.getStringExtra(UpdateManagerKt.EXTRA_PACKAGE_NAME)) == null) {
                stringExtra = "";
            }
            if (context != null) {
                String action = intent != null ? intent.getAction() : null;
                if (action == null) {
                    return;
                }
                int iHashCode = action.hashCode();
                if (iHashCode != -1231747523) {
                    if (iHashCode == -253431256 && action.equals(UpdateManagerKt.ACTION_APPLICATION_GOES_BACKGROUND) && Intrinsics.areEqual(stringExtra, "com.dotscreen.megacable.livetv")) {
                        PortalApplication.this.liveTvIsInForeground = false;
                        return;
                    }
                    return;
                }
                if (action.equals(UpdateManagerKt.ACTION_APPLICATION_GOES_FOREGROUND) && Intrinsics.areEqual(stringExtra, "com.dotscreen.megacable.livetv")) {
                    PortalApplication.this.liveTvIsInForeground = true;
                }
            }
        }
    }

    /* JADX INFO: compiled from: PortalApplication.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"Lcom/dotscreen/portal/PortalApplication$RequestErrorBroadcastReceiver;", "Landroid/content/BroadcastReceiver;", "(Lcom/dotscreen/portal/PortalApplication;)V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public final class RequestErrorBroadcastReceiver extends BroadcastReceiver {
        public RequestErrorBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent != null ? intent.getAction() : null;
            if (action != null && action.hashCode() == 1255387676 && action.equals(CommonUtils.ACTION_REQUEST_EXCEPTION)) {
                Serializable serializableExtra = intent.getSerializableExtra(CommonUtils.BUNDLE_KEY_REQUEST_EXCEPTION);
                if (!(serializableExtra instanceof Throwable)) {
                    serializableExtra = null;
                }
                Throwable th = (Throwable) serializableExtra;
                Serializable serializableExtra2 = intent.getSerializableExtra(CommonUtils.BUNDLE_KEY_REQUEST_URL);
                URL url = (URL) (serializableExtra2 instanceof URL ? serializableExtra2 : null);
                if (th != null) {
                    PortalApplication.this.requestErrorListener.onRequestError(th, url);
                }
            }
        }
    }

    /* JADX INFO: compiled from: PortalApplication.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/dotscreen/portal/PortalApplication$QuiescentRebootWorker;", "Landroidx/work/Worker;", "context", "Landroid/content/Context;", "params", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class QuiescentRebootWorker extends Worker {
        private final Context context;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public QuiescentRebootWorker(Context context, WorkerParameters params) {
            super(context, params);
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(params, "params");
            this.context = context;
        }

        @Override // androidx.work.Worker
        public ListenableWorker.Result doWork() {
            Object systemService = this.context.getApplicationContext().getSystemService("power");
            if (systemService == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.os.PowerManager");
            }
            final PowerManager powerManager = (PowerManager) systemService;
            String string = getInputData().getString("quiescentRebootMinUpTime");
            if (string == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(string, "inputData.getString(\"quiescentRebootMinUpTime\")!!");
            long j = Long.parseLong(string);
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            Log.d(getClass().getSimpleName(), "systemUpTime:" + jElapsedRealtime + " ; quiescentRebootMinUpTime:" + j);
            if (jElapsedRealtime >= j) {
                getApplicationContext().getSharedPreferences(PortalApplication.INSTANCE.getClass().getSimpleName() + "-quiescent", 0).edit().putLong("last_quiescent_reboot_time", new Date().getTime()).apply();
                Log.d(getClass().getSimpleName(), "quiescent reboot in 100 ms");
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.dotscreen.portal.PortalApplication$QuiescentRebootWorker$doWork$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        powerManager.reboot("quiescent");
                    }
                }, 100L);
                ListenableWorker.Result resultSuccess = ListenableWorker.Result.success();
                Intrinsics.checkExpressionValueIsNotNull(resultSuccess, "Result.success()");
                return resultSuccess;
            }
            Log.e(getClass().getSimpleName(), "quiescent reboot cancelled, systemUpTime < minUptime");
            ListenableWorker.Result resultFailure = ListenableWorker.Result.failure();
            Intrinsics.checkExpressionValueIsNotNull(resultFailure, "Result.failure()");
            return resultFailure;
        }
    }

    /* JADX INFO: compiled from: PortalApplication.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0017¨\u0006\t"}, d2 = {"Lcom/dotscreen/portal/PortalApplication$ScreenOnOffBroadcastReceiver;", "Landroid/content/BroadcastReceiver;", "(Lcom/dotscreen/portal/PortalApplication;)V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public final class ScreenOnOffBroadcastReceiver extends BroadcastReceiver {
        public ScreenOnOffBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.v(PortalApplication.this.TAG, "ScreenOnOffBroadcastReceiver.onReceive: " + intent);
            SharedPreferences sharedPreferences = PortalApplication.this.getApplicationContext().getSharedPreferences(PortalApplication.this.TAG + "-quiescent", 0);
            String action = intent != null ? intent.getAction() : null;
            if (action == null) {
                return;
            }
            int iHashCode = action.hashCode();
            final String str = "quiescent_reboot_schedule";
            if (iHashCode == -2128145023) {
                if (action.equals("android.intent.action.SCREEN_OFF")) {
                    Log.v(PortalApplication.this.TAG, "should schedule reboot job ?");
                    long jElapsedRealtime = SystemClock.elapsedRealtime();
                    Log.v(PortalApplication.this.TAG, "realTime = " + jElapsedRealtime + " (" + TimeUnit.MILLISECONDS.toMinutes(jElapsedRealtime) + " min / " + TimeUnit.MILLISECONDS.toHours(jElapsedRealtime) + " hours)");
                    Calendar currentCal = Calendar.getInstance();
                    final Calendar cal = Calendar.getInstance();
                    long j = sharedPreferences.getLong("last_quiescent_reboot_time", -1L);
                    cal.add(14, -((int) jElapsedRealtime));
                    Config value = PortalApplication.this.getConfig().getValue();
                    if (value == null) {
                        Intrinsics.throwNpe();
                    }
                    cal.add(6, (int) Math.floor(((double) value.getQuiescentRebootMinUptimeHours()) / 24.0d));
                    cal.set(11, 0);
                    cal.set(12, 0);
                    cal.set(13, 0);
                    cal.set(14, 0);
                    Config value2 = PortalApplication.this.getConfig().getValue();
                    if (value2 == null) {
                        Intrinsics.throwNpe();
                    }
                    long quiescentRebootMinTime = value2.getQuiescentRebootMinTime();
                    Config value3 = PortalApplication.this.getConfig().getValue();
                    if (value3 == null) {
                        Intrinsics.throwNpe();
                    }
                    cal.add(14, (int) RangesKt.random(new LongRange(quiescentRebootMinTime, value3.getQuiescentRebootMaxTime()), Random.INSTANCE));
                    String str2 = PortalApplication.this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("cal        = ");
                    Intrinsics.checkExpressionValueIsNotNull(cal, "cal");
                    sb.append(cal.getTime());
                    Log.v(str2, sb.toString());
                    String str3 = PortalApplication.this.TAG;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("currentCal = ");
                    Intrinsics.checkExpressionValueIsNotNull(currentCal, "currentCal");
                    sb2.append(currentCal.getTime());
                    Log.v(str3, sb2.toString());
                    if (cal.getTime().compareTo(currentCal.getTime()) < 0) {
                        Calendar calStartOfDay = Calendar.getInstance();
                        calStartOfDay.set(11, 0);
                        calStartOfDay.set(12, 0);
                        calStartOfDay.set(13, 0);
                        calStartOfDay.set(14, 0);
                        if (j != -1) {
                            Config value4 = PortalApplication.this.getConfig().getValue();
                            if (value4 == null) {
                                Intrinsics.throwNpe();
                            }
                            long quiescentRebootMinUptimeHours = j + ((long) (value4.getQuiescentRebootMinUptimeHours() * 60 * 60 * 1000));
                            Date time = currentCal.getTime();
                            Intrinsics.checkExpressionValueIsNotNull(time, "currentCal.time");
                            if (quiescentRebootMinUptimeHours <= time.getTime()) {
                                Config value5 = PortalApplication.this.getConfig().getValue();
                                if (value5 == null) {
                                    Intrinsics.throwNpe();
                                }
                                long quiescentRebootMinTime2 = value5.getQuiescentRebootMinTime();
                                Config value6 = PortalApplication.this.getConfig().getValue();
                                if (value6 == null) {
                                    Intrinsics.throwNpe();
                                }
                                long quiescentRebootMaxTime = value6.getQuiescentRebootMaxTime();
                                Date time2 = currentCal.getTime();
                                Intrinsics.checkExpressionValueIsNotNull(time2, "currentCal.time");
                                long time3 = time2.getTime();
                                Intrinsics.checkExpressionValueIsNotNull(calStartOfDay, "calStartOfDay");
                                Date time4 = calStartOfDay.getTime();
                                Intrinsics.checkExpressionValueIsNotNull(time4, "calStartOfDay.time");
                                long time5 = time3 - time4.getTime();
                                if (quiescentRebootMinTime2 <= time5 && quiescentRebootMaxTime >= time5) {
                                    Log.v(PortalApplication.this.TAG, "reboot now !");
                                    Object systemService = PortalApplication.this.getApplicationContext().getSystemService("power");
                                    if (systemService == null) {
                                        throw new TypeCastException("null cannot be cast to non-null type android.os.PowerManager");
                                    }
                                    sharedPreferences.edit().putLong("last_quiescent_reboot_time", new Date().getTime()).apply();
                                    ((PowerManager) systemService).reboot("quiescent");
                                    return;
                                }
                            }
                        }
                        while (cal.getTime().compareTo(currentCal.getTime()) < 0) {
                            cal.add(6, 1);
                        }
                    }
                    Log.v(PortalApplication.this.TAG, "reboot at " + cal.getTime());
                    if (context == null) {
                        Intrinsics.throwNpe();
                    }
                    final WorkManager workManager = WorkManager.getInstance(context);
                    Intrinsics.checkExpressionValueIsNotNull(workManager, "WorkManager.getInstance(context!!)");
                    workManager.pruneWork();
                    final ListenableFuture<List<WorkInfo>> workInfosByTag = workManager.getWorkInfosByTag("quiescent_reboot_schedule");
                    Intrinsics.checkExpressionValueIsNotNull(workInfosByTag, "manager.getWorkInfosByTag(jobTag)");
                    workInfosByTag.addListener(new Runnable() { // from class: com.dotscreen.portal.PortalApplication$ScreenOnOffBroadcastReceiver$onReceive$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            for (WorkInfo workInfo : (List) workInfosByTag.get()) {
                            }
                            workManager.cancelAllWorkByTag(str);
                            workManager.pruneWork();
                            Calendar cal2 = cal;
                            Intrinsics.checkExpressionValueIsNotNull(cal2, "cal");
                            Duration durationOfMillis = Duration.ofMillis(cal2.getTimeInMillis() - new Date().getTime());
                            OneTimeWorkRequest.Builder builder = new OneTimeWorkRequest.Builder(PortalApplication.QuiescentRebootWorker.class);
                            Data.Builder builder2 = new Data.Builder();
                            builder2.putString("quiescentRebootMinUpTime", String.valueOf(durationOfMillis.toMillis()));
                            builder.setInputData(builder2.build());
                            builder.setInitialDelay(durationOfMillis);
                            builder.addTag(str);
                            OneTimeWorkRequest oneTimeWorkRequestBuild = builder.build();
                            Intrinsics.checkExpressionValueIsNotNull(oneTimeWorkRequestBuild, "builder.build()");
                            workManager.enqueue(oneTimeWorkRequestBuild);
                        }
                    }, new Executor() { // from class: com.dotscreen.portal.PortalApplication$ScreenOnOffBroadcastReceiver$onReceive$2
                        @Override // java.util.concurrent.Executor
                        public final void execute(Runnable runnable) {
                            Executors.newCachedThreadPool().execute(runnable);
                        }
                    });
                    return;
                }
                return;
            }
            if (iHashCode == -1454123155 && action.equals("android.intent.action.SCREEN_ON")) {
                Log.v(PortalApplication.this.TAG, "cancel reboot job");
                if (context == null) {
                    Intrinsics.throwNpe();
                }
                WorkManager workManager2 = WorkManager.getInstance(context);
                Intrinsics.checkExpressionValueIsNotNull(workManager2, "WorkManager.getInstance(context!!)");
                workManager2.cancelAllWorkByTag("quiescent_reboot_schedule");
                Intrinsics.checkExpressionValueIsNotNull(workManager2.pruneWork(), "manager.pruneWork()");
            }
        }
    }

    static /* synthetic */ void hideFloatingConnectivityStatusView$default(PortalApplication portalApplication, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 0;
        }
        portalApplication.hideFloatingConnectivityStatusView(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideFloatingConnectivityStatusView(long delay) {
        Log.i(this.TAG, "hideFloatingConnectivityStatusView(" + delay + ')');
        if (this.floatingConnectivityStatusView != null) {
            if (delay == 0) {
                this.hideFloatingConnectivityStatusViewRunnable.run();
            } else {
                this.handler.postDelayed(this.hideFloatingConnectivityStatusViewRunnable, delay);
            }
        }
    }

    public final Toast getDebugToast() {
        return this.debugToast;
    }

    public final void setDebugToast(Toast toast) {
        this.debugToast = toast;
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.PortalApplication$showDebugToastMessage$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PortalApplication.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 16})
    static final class RunnableC02991 implements Runnable {
        final /* synthetic */ String $message;

        RunnableC02991(String str) {
            this.$message = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            PortalApplication portalApplication = PortalApplication.this;
            portalApplication.setDebugToast(Toast.makeText(portalApplication.getApplicationContext(), this.$message, 0));
            Toast debugToast = PortalApplication.this.getDebugToast();
            if (debugToast == null) {
                Intrinsics.throwNpe();
            }
            View viewFindViewById = debugToast.getView().findViewById(android.R.id.message);
            if (viewFindViewById == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
            }
            ((TextView) viewFindViewById).setGravity(17);
            Toast debugToast2 = PortalApplication.this.getDebugToast();
            if (debugToast2 == null) {
                Intrinsics.throwNpe();
            }
            debugToast2.show();
        }
    }

    public final Toast getToast() {
        return this.toast;
    }

    public final void setToast(Toast toast) {
        this.toast = toast;
    }

    public static /* synthetic */ void showToastMessage$default(PortalApplication portalApplication, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 1;
        }
        portalApplication.showToastMessage(i, i2);
    }

    public final void showToastMessage(int messageResId, int length) {
        String string = getApplicationContext().getString(messageResId);
        Intrinsics.checkExpressionValueIsNotNull(string, "applicationContext.getString(messageResId)");
        showToastMessage(string, length);
    }

    public static /* synthetic */ void showToastMessage$default(PortalApplication portalApplication, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 1;
        }
        portalApplication.showToastMessage(str, i);
    }

    public final void showToastMessage(final String message, final int length) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        Toast toast = this.toast;
        if (toast != null) {
            if (toast == null) {
                Intrinsics.throwNpe();
            }
            toast.cancel();
        }
        ContextCompat.getMainExecutor(getApplicationContext()).execute(new Runnable() { // from class: com.dotscreen.portal.PortalApplication.showToastMessage.1
            @Override // java.lang.Runnable
            public final void run() {
                PortalApplication portalApplication = PortalApplication.this;
                portalApplication.setToast(Toast.makeText(portalApplication.getApplicationContext(), message, length));
                Toast toast2 = PortalApplication.this.getToast();
                if (toast2 == null) {
                    Intrinsics.throwNpe();
                }
                View viewFindViewById = toast2.getView().findViewById(android.R.id.message);
                if (viewFindViewById == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
                }
                ((TextView) viewFindViewById).setGravity(17);
                Toast toast3 = PortalApplication.this.getToast();
                if (toast3 == null) {
                    Intrinsics.throwNpe();
                }
                toast3.show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateFloatingStatusView(final NetworkStatus networkStatus) {
        Log.i(this.TAG, "displayInternetStatus()");
        ContextCompat.getMainExecutor(getApplicationContext()).execute(new Runnable() { // from class: com.dotscreen.portal.PortalApplication.updateFloatingStatusView.1
            @Override // java.lang.Runnable
            public final void run() {
                boolean z = !(PortalApplication.this.backendAvailable && PortalApplication.this.connectedToInternet) && (PortalApplication.this.portalIsInForeground || PortalApplication.this.liveTvIsInForeground);
                Object systemService = PortalApplication.this.getApplicationContext().getSystemService("power");
                if (systemService == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.os.PowerManager");
                }
                final WindowManager windowManager = (WindowManager) PortalApplication.this.getSystemService(WindowManager.class);
                Log.i(PortalApplication.this.TAG, "    backendAvailable: " + PortalApplication.this.backendAvailable);
                Log.i(PortalApplication.this.TAG, "    connectedToInternet: " + PortalApplication.this.connectedToInternet);
                Log.i(PortalApplication.this.TAG, "    portalIsInForeground: " + PortalApplication.this.portalIsInForeground);
                Log.i(PortalApplication.this.TAG, "    liveTvIsInForeground: " + PortalApplication.this.liveTvIsInForeground);
                Log.i(PortalApplication.this.TAG, "    stbIsInteractive: " + ((PowerManager) systemService).isInteractive());
                Log.i(PortalApplication.this.TAG, "    degradedMode: " + PortalApplication.this.degradedMode);
                Log.i(PortalApplication.this.TAG, "    networkStatus: " + networkStatus);
                Log.i(PortalApplication.this.TAG, "    checkJob: " + PortalApplication.this.checkNetworkJob);
                Log.i(PortalApplication.this.TAG, "    appShouldDegradeOnResume: " + PortalApplication.this.appShouldDegradeOnResume);
                Log.i(PortalApplication.this.TAG, "    appRestartIsPlanned: " + PortalApplication.this.appRestartIsPlanned);
                Log.i(PortalApplication.this.TAG, "    shouldRefreshView: " + PortalApplication.this.shouldRefreshView);
                Log.i(PortalApplication.this.TAG, "    -> mustShowView: " + z);
                PortalApplication.this.handler.removeCallbacks(PortalApplication.this.hideFloatingConnectivityStatusViewRunnable);
                if (z) {
                    Log.i(PortalApplication.this.TAG, "    floatingConnectivityStatusView: " + PortalApplication.this.floatingConnectivityStatusView);
                    String str = PortalApplication.this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("    floatingConnectivityStatusView?.networkStatus: ");
                    ConnectivityStatusView connectivityStatusView = PortalApplication.this.floatingConnectivityStatusView;
                    sb.append(connectivityStatusView != null ? connectivityStatusView.getNetworkStatus() : null);
                    Log.i(str, sb.toString());
                    final ConnectivityStatusView connectivityStatusView2 = PortalApplication.this.floatingConnectivityStatusView;
                    PortalApplication.this.floatingConnectivityStatusView = new ConnectivityStatusView(PortalApplication.this, null, 0, 0, networkStatus, 14, null);
                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-2, -2, 2038, 8, -3);
                    layoutParams.gravity = 49;
                    Resources resources = PortalApplication.this.getResources();
                    Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
                    layoutParams.y = (int) TypedValue.applyDimension(1, 32.0f, resources.getDisplayMetrics());
                    if (windowManager != null) {
                        windowManager.addView(PortalApplication.this.floatingConnectivityStatusView, layoutParams);
                    }
                    Log.w(PortalApplication.this.TAG, "adding new floatingConnectivityStatusView: " + PortalApplication.this.floatingConnectivityStatusView);
                    PortalApplication.this.handler.postDelayed(new Runnable() { // from class: com.dotscreen.portal.PortalApplication.updateFloatingStatusView.1.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            ConnectivityStatusView connectivityStatusView3 = connectivityStatusView2;
                            if (connectivityStatusView3 != null) {
                                try {
                                    Log.w(PortalApplication.this.TAG, "removing floatingConnectivityStatusView: " + connectivityStatusView3);
                                    WindowManager windowManager2 = windowManager;
                                    if (windowManager2 != null) {
                                        windowManager2.removeView(connectivityStatusView3);
                                        Unit unit = Unit.INSTANCE;
                                    }
                                } catch (Exception e) {
                                    Integer.valueOf(Log.w(PortalApplication.this.TAG, "Exception", e));
                                }
                            }
                        }
                    }, 100L);
                    if (PortalApplication.this.liveTvIsInForeground) {
                        PortalApplication.this.hideFloatingConnectivityStatusView(10000L);
                        return;
                    }
                    return;
                }
                PortalApplication.this.hideFloatingConnectivityStatusView(0L);
            }
        });
    }

    public final boolean ignoreRequestErrors() {
        Log.w(this.TAG, "ignoreRequestErrors: ignoreRequestErrors=" + this.ignoreRequestErrors + " bootComplete=" + getBootComplete() + ' ');
        return this.ignoreRequestErrors || !getBootComplete();
    }

    public final ReentrantLock getConnectivityLock() {
        return this.connectivityLock;
    }

    public final void fetchAdsForPage(final String className, final int classHashCode) {
        Intrinsics.checkParameterIsNotNull(className, "className");
        if (isActivityReferenceOutdated(className, classHashCode)) {
            DSLog.INSTANCE.adLogE("[GET ADS FOR PAGE][" + className + "] Cancelled - Calling activity is no longer active");
            return;
        }
        TargetAdvertisingConfig.AdUnitsOfPage adUnitIdsForPage = TargetedAdsConfigProvider.INSTANCE.getAdUnitIdsForPage(className);
        if (adUnitIdsForPage != null) {
            Context applicationContext = getApplicationContext();
            Intrinsics.checkExpressionValueIsNotNull(applicationContext, "applicationContext");
            AdsHelper adsHelper = new AdsHelper(applicationContext);
            TargetAdvertisingConfig.NativeAdsConfig nativeAds = adUnitIdsForPage.getNativeAds();
            adsHelper.getNativeAdsOfPage(className, classHashCode, nativeAds != null ? nativeAds.getInLayout() : null, new Function3<String, Ad, String, Unit>() { // from class: com.dotscreen.portal.PortalApplication.fetchAdsForPage.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(String str, Ad ad, String str2) {
                    invoke2(str, ad, str2);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String dsAdUnitId, Ad ad, String message) {
                    Intrinsics.checkParameterIsNotNull(dsAdUnitId, "dsAdUnitId");
                    Intrinsics.checkParameterIsNotNull(ad, "ad");
                    Intrinsics.checkParameterIsNotNull(message, "message");
                    if (PortalApplication.this.isActivityReferenceOutdated(className, classHashCode)) {
                        DSLog.INSTANCE.adLogE("[GET ADS FOR PAGE (onSuccess)][" + className + "] Cancelled - Calling activity is no longer active");
                        return;
                    }
                    ad.setDsIsSelectable(false);
                    DSLog.INSTANCE.adLogM(message);
                    PortalApplication.this.handleInLayoutNativeAd(dsAdUnitId, ad);
                }
            }, new Function1<Throwable, Unit>() { // from class: com.dotscreen.portal.PortalApplication.fetchAdsForPage.2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                    invoke2(th);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Throwable error) {
                    Intrinsics.checkParameterIsNotNull(error, "error");
                    DSLog.INSTANCE.adLogE(String.valueOf(error.getMessage()));
                }
            });
            Context applicationContext2 = getApplicationContext();
            Intrinsics.checkExpressionValueIsNotNull(applicationContext2, "applicationContext");
            AdsHelper adsHelper2 = new AdsHelper(applicationContext2);
            TargetAdvertisingConfig.NativeAdsConfig nativeAds2 = adUnitIdsForPage.getNativeAds();
            adsHelper2.getNativeAdsOfPage(className, classHashCode, nativeAds2 != null ? nativeAds2.getInSwimlane() : null, new Function3<String, Ad, String, Unit>() { // from class: com.dotscreen.portal.PortalApplication.fetchAdsForPage.3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(String str, Ad ad, String str2) {
                    invoke2(str, ad, str2);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String dsAdUnitId, Ad ad, String message) {
                    Intrinsics.checkParameterIsNotNull(dsAdUnitId, "dsAdUnitId");
                    Intrinsics.checkParameterIsNotNull(ad, "ad");
                    Intrinsics.checkParameterIsNotNull(message, "message");
                    if (PortalApplication.this.isActivityReferenceOutdated(className, classHashCode)) {
                        DSLog.INSTANCE.adLogE("[GET ADS FOR PAGE (onSuccess)][" + className + "] Cancelled - Calling activity is no longer active");
                        return;
                    }
                    ad.setDsIsSelectable(true);
                    DSLog.INSTANCE.adLogM(message);
                    PortalApplication.this.handleInSwimlaneNativeAd(dsAdUnitId, ad);
                }
            }, new Function1<Throwable, Unit>() { // from class: com.dotscreen.portal.PortalApplication.fetchAdsForPage.4
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                    invoke2(th);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Throwable error) {
                    Intrinsics.checkParameterIsNotNull(error, "error");
                    DSLog.INSTANCE.adLogE(String.valueOf(error.getMessage()));
                }
            });
            Context applicationContext3 = getApplicationContext();
            Intrinsics.checkExpressionValueIsNotNull(applicationContext3, "applicationContext");
            AdsHelper adsHelper3 = new AdsHelper(applicationContext3);
            TargetAdvertisingConfig.NativeAdsConfig nativeAds3 = adUnitIdsForPage.getNativeAds();
            adsHelper3.getNativeAdsOfPage(className, classHashCode, nativeAds3 != null ? nativeAds3.getCustom() : null, new Function3<String, Ad, String, Unit>() { // from class: com.dotscreen.portal.PortalApplication.fetchAdsForPage.5
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(String str, Ad ad, String str2) {
                    invoke2(str, ad, str2);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String dsAdUnitId, Ad ad, String message) {
                    Intrinsics.checkParameterIsNotNull(dsAdUnitId, "dsAdUnitId");
                    Intrinsics.checkParameterIsNotNull(ad, "ad");
                    Intrinsics.checkParameterIsNotNull(message, "message");
                    if (PortalApplication.this.isActivityReferenceOutdated(className, classHashCode)) {
                        DSLog.INSTANCE.adLogE("[GET ADS FOR PAGE (onSuccess)][" + className + "] Cancelled - Calling activity is no longer active");
                        return;
                    }
                    ad.setDsIsSelectable(false);
                    DSLog.INSTANCE.adLogM(message);
                    PortalApplication.this.handleCustomNativeAd(dsAdUnitId, ad);
                }
            }, new Function1<Throwable, Unit>() { // from class: com.dotscreen.portal.PortalApplication.fetchAdsForPage.6
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                    invoke2(th);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Throwable error) {
                    Intrinsics.checkParameterIsNotNull(error, "error");
                    DSLog.INSTANCE.adLogE(String.valueOf(error.getMessage()));
                }
            });
            Context applicationContext4 = getApplicationContext();
            Intrinsics.checkExpressionValueIsNotNull(applicationContext4, "applicationContext");
            new AdsHelper(applicationContext4).getFloatingAdsOfPage(className, classHashCode, adUnitIdsForPage.getFloatingAds(), new Function2<Ad, String, Unit>() { // from class: com.dotscreen.portal.PortalApplication.fetchAdsForPage.7
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Ad ad, String str) {
                    invoke2(ad, str);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Ad ad, String message) {
                    Intrinsics.checkParameterIsNotNull(ad, "ad");
                    Intrinsics.checkParameterIsNotNull(message, "message");
                    if (PortalApplication.this.isActivityReferenceOutdated(className, classHashCode)) {
                        DSLog.INSTANCE.adLogE("[GET ADS FOR PAGE (onSuccess)][" + className + "] Cancelled - Calling activity is no longer active");
                        return;
                    }
                    ad.setDsIsSelectable(false);
                    DSLog.INSTANCE.adLogM(message);
                    PortalApplication.this.addToFloatingAdList(ad);
                    if (PortalApplication.this.currentFloatingAdListeners.size() > 0 && ((IFloatingAdListener) CollectionsKt.first(PortalApplication.this.currentFloatingAdListeners)).overrideFloatingAdHandling()) {
                        DSLog.INSTANCE.adLogE("[GET ADS FOR PAGE (onSuccess)][" + className + "] will delagate floating ads");
                        return;
                    }
                    PortalApplication.this.handleFloatingAd(ad);
                }
            }, new Function1<Throwable, Unit>() { // from class: com.dotscreen.portal.PortalApplication.fetchAdsForPage.8
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                    invoke2(th);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Throwable error) {
                    Intrinsics.checkParameterIsNotNull(error, "error");
                    DSLog.INSTANCE.adLogE(String.valueOf(error.getMessage()));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isActivityReferenceOutdated(String activityName, int activityHashCode) {
        Activity activity = get_activeActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        boolean zAreEqual = Intrinsics.areEqual(activityName, activity.getClass().getSimpleName());
        Activity activity2 = get_activeActivity();
        if (activity2 == null) {
            Intrinsics.throwNpe();
        }
        boolean z = activityHashCode == activity2.hashCode();
        if (zAreEqual) {
            return zAreEqual && !z;
        }
        return true;
    }

    public final void startBannerHidingCountDown(final String runtimeTag, final String owningActivityName, final int owningActivityHashcode, long whenResourceDisappearsInSec) {
        Intrinsics.checkParameterIsNotNull(runtimeTag, "runtimeTag");
        Intrinsics.checkParameterIsNotNull(owningActivityName, "owningActivityName");
        Activity activity = get_activeActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        boolean zAreEqual = Intrinsics.areEqual(owningActivityName, activity.getClass().getSimpleName());
        Activity activity2 = get_activeActivity();
        if (activity2 == null) {
            Intrinsics.throwNpe();
        }
        boolean z = owningActivityHashcode == activity2.hashCode();
        if (zAreEqual && z && this.currentPendingToDisappearAds.contains(runtimeTag)) {
            DSLog.INSTANCE.adLogE("[HIDE BANNER][" + runtimeTag + "] already planned for this tag");
            return;
        }
        new Handler().postDelayed(new Runnable() { // from class: com.dotscreen.portal.PortalApplication.startBannerHidingCountDown.1
            @Override // java.lang.Runnable
            public final void run() {
                Window window;
                String str = owningActivityName;
                Activity activity3 = PortalApplication.this.get_activeActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                boolean zAreEqual2 = Intrinsics.areEqual(str, activity3.getClass().getSimpleName());
                int i = owningActivityHashcode;
                Activity activity4 = PortalApplication.this.get_activeActivity();
                if (activity4 == null) {
                    Intrinsics.throwNpe();
                }
                boolean z2 = i == activity4.hashCode();
                if (!zAreEqual2 || (zAreEqual2 && !z2)) {
                    PortalApplication.this.showDebugToastMessage("[HIDE BANNER][" + runtimeTag + "] Activity reference is outdated, ad has already been hidden");
                    DSLog.INSTANCE.adLogE("[HIDE BANNER][" + runtimeTag + "] Activity reference is outdated, ad has already been hidden");
                    return;
                }
                ArrayList<View> arrayList = new ArrayList<>();
                Activity activity5 = PortalApplication.this.get_activeActivity();
                ViewGroup viewGroup = (ViewGroup) ((activity5 == null || (window = activity5.getWindow()) == null) ? null : window.getDecorView());
                if (viewGroup != null) {
                    viewGroup.findViewsWithText(arrayList, runtimeTag, 2);
                }
                if (arrayList.isEmpty()) {
                    DSLog.INSTANCE.adLogE("[HIDE BANNER][" + runtimeTag + "] adBannerView not found. Can't hide ad at the moment. Will try to do so on user navigation.");
                    PortalApplication.this.currentPendingToDisappearAds.add(runtimeTag);
                    return;
                }
                for (View view : arrayList) {
                    if (view instanceof AdBannerView) {
                        ((AdBannerView) view).hideBanner();
                    } else {
                        DSLog.INSTANCE.adLogE("[HIDE BANNER][" + runtimeTag + "] Strange error... adBannerView is not AdBannerView");
                    }
                }
            }
        }, whenResourceDisappearsInSec * ((long) 1000));
        DSLog.INSTANCE.adLogM("[HIDE BANNER][" + runtimeTag + "] Countdown started successfully");
    }

    private final void hidePendingToDisappearAds() {
        Window window;
        for (String str : this.currentPendingToDisappearAds) {
            ArrayList<View> arrayList = new ArrayList<>();
            Activity activity = get_activeActivity();
            ViewGroup viewGroup = (ViewGroup) ((activity == null || (window = activity.getWindow()) == null) ? null : window.getDecorView());
            if (viewGroup != null) {
                viewGroup.findViewsWithText(arrayList, str, 2);
            }
            if (!arrayList.isEmpty()) {
                for (View view : arrayList) {
                    if (view instanceof AdBannerView) {
                        DSLog.INSTANCE.adLogE("[HIDE BANNER][" + str + "] -> Pending banner successfully hidden");
                        ((AdBannerView) view).hideBanner();
                    } else {
                        DSLog.INSTANCE.adLogE("[HIDE BANNER][" + str + "] Strange error... adBannerView is not AdBannerView");
                    }
                }
            }
        }
        this.currentPendingToDisappearAds.clear();
    }

    public final void visitAdTrackingURL(String adUnitId, String url) {
        Intrinsics.checkParameterIsNotNull(adUnitId, "adUnitId");
        Intrinsics.checkParameterIsNotNull(url, "url");
        if (checkIfAdTrackingURLWasVisited(url)) {
            DSLog.INSTANCE.adLogE("[AD-TRACKING][VO-" + adUnitId + "] Warning, duplicate tracking urls detected");
        }
        this.visitedAdTrackingURLForCurrentPage.add(url);
    }

    public final boolean checkIfAdTrackingURLWasVisited(String url) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        return this.visitedAdTrackingURLForCurrentPage.contains(url);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleInLayoutNativeAd(String dsAdUnitId, Ad ad) {
        Window window;
        if (!isValidNativeAd(ad)) {
            DSLog.INSTANCE.adLogM("[HANDLE AD][NATIVE][IN LAYOUT][VO-" + ad.getAdUnitId() + "] -> [DS-" + dsAdUnitId + "] - KO was expecting a native ad");
            return;
        }
        ad.setDsNativeAdType(Ad.DSNativeAdType.IN_LAYOUT);
        addToNativeAdList(dsAdUnitId, ad);
        Activity activity = get_activeActivity();
        ViewGroup viewGroup = (ViewGroup) ((activity == null || (window = activity.getWindow()) == null) ? null : window.getDecorView());
        InLayoutNativeAdBannerView inLayoutNativeAdBannerView = viewGroup != null ? (InLayoutNativeAdBannerView) viewGroup.findViewWithTag(dsAdUnitId) : null;
        if (inLayoutNativeAdBannerView != null && (!ad.getImageResources().isEmpty())) {
            DSLog.INSTANCE.adLogM("[HANDLE AD][NATIVE][IN LAYOUT][VO-" + ad.getAdUnitId() + "] -> [DS-" + dsAdUnitId + "] Ad resource is set");
            Activity activity2 = get_activeActivity();
            if (activity2 == null) {
                Intrinsics.throwNpe();
            }
            String simpleName = activity2.getClass().getSimpleName();
            Intrinsics.checkExpressionValueIsNotNull(simpleName, "activeActivity!!.javaClass.simpleName");
            Activity activity3 = get_activeActivity();
            if (activity3 == null) {
                Intrinsics.throwNpe();
            }
            inLayoutNativeAdBannerView.setAdImageResource(simpleName, activity3.hashCode(), ad, (Ad.ImageResource) CollectionsKt.first((List) ad.getImageResources()), 0, new Function4<String, String, Integer, Long, Unit>() { // from class: com.dotscreen.portal.PortalApplication.handleInLayoutNativeAd.1
                {
                    super(4);
                }

                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Unit invoke(String str, String str2, Integer num, Long l) {
                    invoke(str, str2, num.intValue(), l.longValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(String runtimeTag, String owningActivityName, int i, long j) {
                    Intrinsics.checkParameterIsNotNull(runtimeTag, "runtimeTag");
                    Intrinsics.checkParameterIsNotNull(owningActivityName, "owningActivityName");
                    PortalApplication.this.startBannerHidingCountDown(runtimeTag, owningActivityName, i, j);
                }
            });
            return;
        }
        DSLog.INSTANCE.adLogE("[HANDLE AD][NATIVE][IN LAYOUT][VO-" + ad.getAdUnitId() + "] -> [DS-" + dsAdUnitId + "] dsAdUnitId not found in the XML layout, ad will be delegated or won't be shown");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleInSwimlaneNativeAd(String dsAdUnitId, Ad ad) {
        if (!isValidNativeAd(ad)) {
            DSLog.INSTANCE.adLogM("[HANDLE AD][NATIVE][IN SWIMLANE][VO-" + ad.getAdUnitId() + "] -> [DS-" + dsAdUnitId + "] - KO was expecting a native ad");
            return;
        }
        ad.setDsNativeAdType(Ad.DSNativeAdType.IN_SWIMLANE);
        DSLog.INSTANCE.adLogM("[HANDLE AD][NATIVE][IN SWIMLANE][VO-" + ad.getAdUnitId() + "] -> [DS-" + dsAdUnitId + "] display delegated");
        addToNativeAdList(dsAdUnitId, ad);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleCustomNativeAd(String dsAdUnitId, Ad ad) {
        if (!isValidNativeAd(ad)) {
            DSLog.INSTANCE.adLogM("[HANDLE AD][NATIVE][CUSTOM][VO-" + ad.getAdUnitId() + "] -> [DS-" + dsAdUnitId + "] - KO was expecting a native ad");
            return;
        }
        ad.setDsNativeAdType(Ad.DSNativeAdType.CUSTOM);
        DSLog.INSTANCE.adLogM("[HANDLE AD][NATIVE][CUSTOM][VO-" + ad.getAdUnitId() + "] -> [DS-" + dsAdUnitId + ']');
        addToNativeAdList(dsAdUnitId, ad);
    }

    private final void addToNativeAdList(String dsAdUnitId, Ad ad) {
        ad.setDsAdUnitId(dsAdUnitId);
        this.currentNativeAds.add(ad);
        Iterator<INativeAdListener> it = this.currentNativeAdListeners.iterator();
        while (it.hasNext()) {
            it.next().onNativeAdReceived();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addToFloatingAdList(Ad ad) {
        this.currentFloatingAds.add(ad);
        Iterator<IFloatingAdListener> it = this.currentFloatingAdListeners.iterator();
        while (it.hasNext()) {
            it.next().onFloatingAdReceived();
        }
    }

    private final boolean isValidNativeAd(Ad ad) {
        return ((Ad.ImageResource) CollectionsKt.first((List) ad.getImageResources())).getResourceTemplate().getPosition() == Ad.PositionType.f0native;
    }

    public final List<Ad> getCurrentNativeAdList() {
        return CollectionsKt.toList(this.currentNativeAds);
    }

    public final List<Ad> getCurrentFloatingAdList() {
        return CollectionsKt.toList(this.currentFloatingAds);
    }

    public final Ad getAdFromCurrentNativeAdList(String dsAdUnitId) {
        Intrinsics.checkParameterIsNotNull(dsAdUnitId, "dsAdUnitId");
        for (Ad ad : this.currentNativeAds) {
            if (Intrinsics.areEqual(ad.getDsAdUnitId(), dsAdUnitId)) {
                return ad;
            }
        }
        return null;
    }

    public final boolean isShowingDismissableFloatingAds() {
        return !this.currentlyDisplayedDismissableFloatingAdBannerViews.isEmpty();
    }

    public final void handleFloatingAd(Ad ad) {
        Intrinsics.checkParameterIsNotNull(ad, "ad");
        int size = ad.getImageResources().size();
        DSLog.INSTANCE.adLogM("[HANDLE AD][FLOATING][VO-" + ad.getAdUnitId() + "] " + size + " image resource to handle");
        int i = 0;
        for (Ad.ImageResource imageResource : ad.getImageResources()) {
            if (imageResource.getResourceTemplate().getPosition() == Ad.PositionType.floating) {
                final FloatingAdBannerView floatingAdBannerView = new FloatingAdBannerView(this);
                Activity activity = get_activeActivity();
                if (activity == null) {
                    Intrinsics.throwNpe();
                }
                String simpleName = activity.getClass().getSimpleName();
                Intrinsics.checkExpressionValueIsNotNull(simpleName, "activeActivity!!.javaClass.simpleName");
                Activity activity2 = get_activeActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                floatingAdBannerView.setAdImageResource(simpleName, activity2.hashCode(), ad, imageResource, i, new Function4<String, String, Integer, Long, Unit>() { // from class: com.dotscreen.portal.PortalApplication.handleFloatingAd.1
                    {
                        super(4);
                    }

                    @Override // kotlin.jvm.functions.Function4
                    public /* bridge */ /* synthetic */ Unit invoke(String str, String str2, Integer num, Long l) {
                        invoke(str, str2, num.intValue(), l.longValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(String runtimeTag, String owningActivityName, int i2, long j) {
                        Intrinsics.checkParameterIsNotNull(runtimeTag, "runtimeTag");
                        Intrinsics.checkParameterIsNotNull(owningActivityName, "owningActivityName");
                        PortalApplication.this.startBannerHidingCountDown(runtimeTag, owningActivityName, i2, j);
                    }
                });
                int i2 = WhenMappings.$EnumSwitchMapping$0[imageResource.getWhenResourceIsDisplayed().ordinal()];
                if (i2 == 1) {
                    DSLog.INSTANCE.adLogM("[HANDLE AD][FLOATING][VO-" + ad.getAdUnitId() + "] imageResource[" + i + "] : Auto -- OK");
                    showFloatingAd(floatingAdBannerView);
                } else if (i2 == 2) {
                    DSLog.INSTANCE.adLogM("[HANDLE AD][FLOATING][VO-" + ad.getAdUnitId() + "] imageResource[" + i + "] : OnOffset(" + imageResource.getWhenResourceIsDisplayedOffset() + " seconds) -- OK");
                    Runnable runnable = new Runnable() { // from class: com.dotscreen.portal.PortalApplication$handleFloatingAd$onOffsetRunnable$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.this$0.showFloatingAd(floatingAdBannerView);
                        }
                    };
                    this.currentOnOffsetFloatingAdBannerViewRunnables.add(runnable);
                    this.handler.postDelayed(runnable, imageResource.getWhenResourceIsDisplayedOffset() * ((long) 1000));
                } else if (i2 == 3) {
                    DSLog.INSTANCE.adLogM("[HANDLE AD][FLOATING][VO-" + ad.getAdUnitId() + "] imageResource[" + i + "] : OnBrowse -- OK");
                    this.currentOnBrowseFloatingAdBannerViews.add(floatingAdBannerView);
                } else {
                    DSLog.INSTANCE.adLogE("[HANDLE AD][FLOATING][VO-" + ad.getAdUnitId() + "] imageResource[" + i + "] : " + imageResource.getWhenResourceIsDisplayed().name() + " -- KO not supported / ignored");
                }
            } else {
                DSLog.INSTANCE.adLogE("[HANDLE AD][FLOATING][VO-" + ad.getAdUnitId() + "] imageResource[" + i + "] : Error was expecting floating imageResource; found " + imageResource.getResourceTemplate().getPosition());
            }
            i++;
        }
    }

    public final void onBrowseActionInActivity() {
        hidePendingToDisappearAds();
        List mutableList = CollectionsKt.toMutableList((Collection) this.currentOnBrowseFloatingAdBannerViews);
        if (!mutableList.isEmpty()) {
            DSLog.INSTANCE.adLogM("[HANDLE AD][FLOATING][VO-<all>][ON OFFSET] " + this.currentOnBrowseFloatingAdBannerViews.size() + " banners to be shown");
            Iterator it = mutableList.iterator();
            while (it.hasNext()) {
                showFloatingAd((FloatingAdBannerView) it.next());
            }
        }
    }

    private final boolean isFloatingAdPlacementValid(FloatingAdBannerView floatingAdBannerView) {
        Ad.ResourceTemplate resourceTemplate;
        Ad.ResourceTemplate resourceTemplate2;
        Ad.ResourceTemplate resourceTemplate3;
        Ad.ResourceTemplate resourceTemplate4;
        Ad.ImageResource resource = floatingAdBannerView.getResource();
        long horizontalOffset = (resource == null || (resourceTemplate4 = resource.getResourceTemplate()) == null) ? 0L : resourceTemplate4.getHorizontalOffset();
        long verticalOffset = (resource == null || (resourceTemplate3 = resource.getResourceTemplate()) == null) ? 0L : resourceTemplate3.getVerticalOffset();
        long width = (resource == null || (resourceTemplate2 = resource.getResourceTemplate()) == null) ? 0L : resourceTemplate2.getWidth();
        long height = (resource == null || (resourceTemplate = resource.getResourceTemplate()) == null) ? 0L : resourceTemplate.getHeight();
        return (resource == null || width == 0 || height == 0 || horizontalOffset + width > 1920 || verticalOffset + height > 1080) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showFloatingAd(FloatingAdBannerView floatingAdBannerView) {
        if (!isFloatingAdPlacementValid(floatingAdBannerView)) {
            DSLog dSLog = DSLog.INSTANCE;
            StringBuilder sb = new StringBuilder();
            sb.append("[DISPLAY FLOATING AD][VO-");
            Ad ad = floatingAdBannerView.getAd();
            sb.append(ad != null ? ad.getAdUnitId() : null);
            sb.append("] KO - Coordinates and/or size invalid");
            dSLog.adLogM(sb.toString());
            return;
        }
        WindowManager windowManager = (WindowManager) getSystemService(WindowManager.class);
        this.currentOnBrowseFloatingAdBannerViews.remove(floatingAdBannerView);
        Ad.ImageResource imageResource = floatingAdBannerView.getResource();
        if (imageResource == null) {
            Intrinsics.throwNpe();
        }
        long j = 1;
        int width = (int) (imageResource.getResourceTemplate().getWidth() / j);
        Ad.ImageResource imageResource2 = floatingAdBannerView.getResource();
        if (imageResource2 == null) {
            Intrinsics.throwNpe();
        }
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(width, (int) (imageResource2.getResourceTemplate().getHeight() / j), 2038, 8, -3);
        layoutParams.gravity = 51;
        Ad.ImageResource imageResource3 = floatingAdBannerView.getResource();
        if (imageResource3 == null) {
            Intrinsics.throwNpe();
        }
        layoutParams.x = (int) (imageResource3.getResourceTemplate().getHorizontalOffset() / j);
        Ad.ImageResource imageResource4 = floatingAdBannerView.getResource();
        if (imageResource4 == null) {
            Intrinsics.throwNpe();
        }
        layoutParams.y = (int) (imageResource4.getResourceTemplate().getVerticalOffset() / j);
        if (windowManager != null) {
            windowManager.addView(floatingAdBannerView, layoutParams);
        }
        this.currentlyDisplayedFloatingAdBannerViews.add(floatingAdBannerView);
        DSLog dSLog2 = DSLog.INSTANCE;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("[DISPLAY FLOATING AD][VO-");
        Ad ad2 = floatingAdBannerView.getAd();
        sb2.append(ad2 != null ? ad2.getAdUnitId() : null);
        sb2.append("] OK");
        dSLog2.adLogM(sb2.toString());
    }

    private final void showDismissibleFloatingAd(FloatingAdBannerView floatingAdBannerView, Ad.DisplayTrigger displayType) {
        if (!isFloatingAdPlacementValid(floatingAdBannerView)) {
            if (displayType == Ad.DisplayTrigger.OnClick) {
                this.currentlyDisplayedOnClickFloatingAdBannerViews.add(floatingAdBannerView);
                DSLog dSLog = DSLog.INSTANCE;
                StringBuilder sb = new StringBuilder();
                sb.append("[DISPLAY DISMISSIBLE FLOATING AD][VO-");
                Ad ad = floatingAdBannerView.getAd();
                sb.append(ad != null ? ad.getAdUnitId() : null);
                sb.append("][ON CLICK] KO - Coordinates and/or size invalid");
                dSLog.adLogM(sb.toString());
                return;
            }
            this.currentlyDisplayedOnSelectFloatingAdBannerViews.add(floatingAdBannerView);
            DSLog dSLog2 = DSLog.INSTANCE;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("[DISPLAY DISMISSIBLE FLOATING AD][VO-");
            Ad ad2 = floatingAdBannerView.getAd();
            sb2.append(ad2 != null ? ad2.getAdUnitId() : null);
            sb2.append("][ON SELECT] KO - Coordinates and/or size invalid");
            dSLog2.adLogM(sb2.toString());
            return;
        }
        WindowManager windowManager = (WindowManager) getSystemService(WindowManager.class);
        Ad.ImageResource imageResource = floatingAdBannerView.getResource();
        if (imageResource == null) {
            Intrinsics.throwNpe();
        }
        long j = 1;
        int width = (int) (imageResource.getResourceTemplate().getWidth() / j);
        Ad.ImageResource imageResource2 = floatingAdBannerView.getResource();
        if (imageResource2 == null) {
            Intrinsics.throwNpe();
        }
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(width, (int) (imageResource2.getResourceTemplate().getHeight() / j), 2038, 8, -3);
        layoutParams.gravity = 51;
        Ad.ImageResource imageResource3 = floatingAdBannerView.getResource();
        if (imageResource3 == null) {
            Intrinsics.throwNpe();
        }
        layoutParams.x = (int) (imageResource3.getResourceTemplate().getHorizontalOffset() / j);
        Ad.ImageResource imageResource4 = floatingAdBannerView.getResource();
        if (imageResource4 == null) {
            Intrinsics.throwNpe();
        }
        layoutParams.y = (int) (imageResource4.getResourceTemplate().getVerticalOffset() / j);
        if (windowManager != null) {
            windowManager.addView(floatingAdBannerView, layoutParams);
        }
        this.currentlyDisplayedDismissableFloatingAdBannerViews.add(floatingAdBannerView);
        if (displayType == Ad.DisplayTrigger.OnClick) {
            this.currentlyDisplayedOnClickFloatingAdBannerViews.add(floatingAdBannerView);
            DSLog dSLog3 = DSLog.INSTANCE;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("[DISPLAY DISMISSIBLE FLOATING AD][VO-");
            Ad ad3 = floatingAdBannerView.getAd();
            sb3.append(ad3 != null ? ad3.getAdUnitId() : null);
            sb3.append("][ON CLICK] OK");
            dSLog3.adLogM(sb3.toString());
            return;
        }
        this.currentlyDisplayedOnSelectFloatingAdBannerViews.add(floatingAdBannerView);
        DSLog dSLog4 = DSLog.INSTANCE;
        StringBuilder sb4 = new StringBuilder();
        sb4.append("[DISPLAY DISMISSIBLE FLOATING AD][VO-");
        Ad ad4 = floatingAdBannerView.getAd();
        sb4.append(ad4 != null ? ad4.getAdUnitId() : null);
        sb4.append("][ON SELECT] OK");
        dSLog4.adLogM(sb4.toString());
    }

    public final void hideAllDismissibleFloatingAds() {
        DSLog.INSTANCE.adLogM("[HIDE ALL DISMISSIBLE FLOATING ADS]");
        for (FloatingAdBannerView floatingAdBannerView : this.currentlyDisplayedDismissableFloatingAdBannerViews) {
            Set<Ad> set = this.currentFloatingAds;
            Ad ad = floatingAdBannerView.getAd();
            if (set == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableCollection<T>");
            }
            TypeIntrinsics.asMutableCollection(set).remove(ad);
            WindowManager windowManager = (WindowManager) getSystemService(WindowManager.class);
            if (windowManager != null) {
                try {
                    windowManager.removeView(floatingAdBannerView);
                    Unit unit = Unit.INSTANCE;
                } catch (Exception e) {
                    DSLog dSLog = DSLog.INSTANCE;
                    StringBuilder sb = new StringBuilder();
                    sb.append("[HIDE ALL DISMISSIBLE FLOATING ADS][VO-");
                    Ad ad2 = floatingAdBannerView.getAd();
                    sb.append(ad2 != null ? ad2.getAdUnitId() : null);
                    sb.append("] KO : ");
                    sb.append(e);
                    dSLog.adLogE(sb.toString());
                    Unit unit2 = Unit.INSTANCE;
                }
            }
        }
        this.currentlyDisplayedDismissableFloatingAdBannerViews.clear();
        this.currentlyDisplayedOnSelectFloatingAdBannerViews.clear();
        this.currentlyDisplayedOnClickFloatingAdBannerViews.clear();
    }

    public final void hideDisplayedFloatingAds() {
        DSLog.INSTANCE.adLogM("[HIDE ALL DISPLAYED FLOATING ADS]");
        for (FloatingAdBannerView floatingAdBannerView : this.currentlyDisplayedFloatingAdBannerViews) {
            this.currentlyDisplayedDismissableFloatingAdBannerViews.remove(floatingAdBannerView);
            this.currentlyDisplayedOnSelectFloatingAdBannerViews.remove(floatingAdBannerView);
            this.currentlyDisplayedOnClickFloatingAdBannerViews.remove(floatingAdBannerView);
            WindowManager windowManager = (WindowManager) getSystemService(WindowManager.class);
            if (windowManager != null) {
                try {
                    windowManager.removeView(floatingAdBannerView);
                    Unit unit = Unit.INSTANCE;
                } catch (Exception e) {
                    DSLog dSLog = DSLog.INSTANCE;
                    StringBuilder sb = new StringBuilder();
                    sb.append("[HIDE ALL DISPLAYED FLOATING ADS][VO-");
                    Ad ad = floatingAdBannerView.getAd();
                    sb.append(ad != null ? ad.getAdUnitId() : null);
                    sb.append("] KO : ");
                    sb.append(e);
                    dSLog.adLogE(sb.toString());
                    Unit unit2 = Unit.INSTANCE;
                }
            }
        }
        this.currentlyDisplayedFloatingAdBannerViews.clear();
    }

    public final void clearFloatingAds() {
        DSLog.INSTANCE.adLogM("[HIDE ALL FLOATING ADS]");
        Iterator<Runnable> it = this.currentOnOffsetFloatingAdBannerViewRunnables.iterator();
        while (it.hasNext()) {
            this.handler.removeCallbacks(it.next());
        }
        this.currentOnOffsetFloatingAdBannerViewRunnables.clear();
        this.currentPendingToDisappearAds.clear();
        this.currentFloatingAdListeners.clear();
        this.currentOnBrowseFloatingAdBannerViews.clear();
        this.alreadyShownOnSelectFloatingAdViews.clear();
        hideAllDismissibleFloatingAds();
        for (FloatingAdBannerView floatingAdBannerView : this.currentlyDisplayedFloatingAdBannerViews) {
            WindowManager windowManager = (WindowManager) getSystemService(WindowManager.class);
            if (windowManager != null) {
                try {
                    windowManager.removeView(floatingAdBannerView);
                    Unit unit = Unit.INSTANCE;
                } catch (Exception e) {
                    DSLog dSLog = DSLog.INSTANCE;
                    StringBuilder sb = new StringBuilder();
                    sb.append("[HIDE ALL FLOATING ADS][VO-");
                    Ad ad = floatingAdBannerView.getAd();
                    sb.append(ad != null ? ad.getAdUnitId() : null);
                    sb.append("] KO : ");
                    sb.append(e);
                    dSLog.adLogE(sb.toString());
                    Unit unit2 = Unit.INSTANCE;
                }
            }
        }
        this.currentFloatingAds.clear();
        this.currentlyDisplayedFloatingAdBannerViews.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void clearNativeAds() {
        Window window;
        for (Ad ad : this.currentNativeAds) {
            if (ad.getDsNativeAdType() == Ad.DSNativeAdType.IN_LAYOUT && ad.getDsAdUnitId() != null) {
                Activity activity = get_activeActivity();
                ViewGroup viewGroup = (ViewGroup) ((activity == null || (window = activity.getWindow()) == null) ? null : window.getDecorView());
                InLayoutNativeAdBannerView inLayoutNativeAdBannerView = viewGroup != null ? (InLayoutNativeAdBannerView) viewGroup.findViewWithTag(ad.getDsAdUnitId()) : null;
                if (inLayoutNativeAdBannerView != null) {
                    inLayoutNativeAdBannerView.hideBanner();
                }
            }
        }
        this.currentNativeAds.clear();
        Iterator<INativeAdListener> it = this.currentNativeAdListeners.iterator();
        while (it.hasNext()) {
            it.next().clearSwimlaneNativeAds();
        }
        this.currentNativeAdListeners.clear();
    }

    public final void addNativeAdAddedListener(INativeAdListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (this.currentNativeAdListeners.contains(listener)) {
            return;
        }
        this.currentNativeAdListeners.add(listener);
    }

    public final void addFloatingAdListener(IFloatingAdListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (this.currentFloatingAdListeners.contains(listener)) {
            return;
        }
        this.currentFloatingAdListeners.add(listener);
    }

    public final void handleInSwimlaneNativeAdClick(Ad nativeAd) {
        int i;
        Intrinsics.checkParameterIsNotNull(nativeAd, "nativeAd");
        if (!this.currentlyDisplayedOnSelectFloatingAdBannerViews.isEmpty()) {
            DSLog.INSTANCE.adLogM(nativeAd.getAdUnitId() + " -> OnClick: ignored; OnSelect still visible");
            showDebugToastMessage(nativeAd.getAdUnitId() + " -> OnClick: ignored; OnSelect still visible");
            return;
        }
        List<Ad.ImageResource> imageResources = nativeAd.getImageResources();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = imageResources.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if ((((Ad.ImageResource) next).getWhenResourceIsDisplayed() == Ad.DisplayTrigger.OnClick ? 1 : 0) != 0) {
                arrayList.add(next);
            }
        }
        int size = arrayList.size();
        if (size == 0) {
            return;
        }
        DSLog.INSTANCE.adLogM(nativeAd.getAdUnitId() + " -> OnClick: " + size + " banners to show");
        showDebugToastMessage(nativeAd.getAdUnitId() + " -> OnClick: " + size + " banners to show");
        for (Ad.ImageResource imageResource : nativeAd.getImageResources().subList(1, nativeAd.getImageResources().size())) {
            if (imageResource.getWhenResourceIsDisplayed() == Ad.DisplayTrigger.OnClick) {
                Context applicationContext = getApplicationContext();
                Intrinsics.checkExpressionValueIsNotNull(applicationContext, "applicationContext");
                FloatingAdBannerView floatingAdBannerView = new FloatingAdBannerView(applicationContext);
                Activity activity = get_activeActivity();
                if (activity == null) {
                    Intrinsics.throwNpe();
                }
                String simpleName = activity.getClass().getSimpleName();
                Intrinsics.checkExpressionValueIsNotNull(simpleName, "activeActivity!!.javaClass.simpleName");
                Activity activity2 = get_activeActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                floatingAdBannerView.setAdImageResource(simpleName, activity2.hashCode(), nativeAd, imageResource, i, new Function4<String, String, Integer, Long, Unit>() { // from class: com.dotscreen.portal.PortalApplication.handleInSwimlaneNativeAdClick.1
                    {
                        super(4);
                    }

                    @Override // kotlin.jvm.functions.Function4
                    public /* bridge */ /* synthetic */ Unit invoke(String str, String str2, Integer num, Long l) {
                        invoke(str, str2, num.intValue(), l.longValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(String runtimeTag, String owningActivityName, int i2, long j) {
                        Intrinsics.checkParameterIsNotNull(runtimeTag, "runtimeTag");
                        Intrinsics.checkParameterIsNotNull(owningActivityName, "owningActivityName");
                        PortalApplication.this.startBannerHidingCountDown(runtimeTag, owningActivityName, i2, j);
                    }
                });
                showDismissibleFloatingAd(floatingAdBannerView, imageResource.getWhenResourceIsDisplayed());
            }
            i++;
        }
    }

    public final void handleInSwimlaneNativeAdSelection(Ad nativeAd) {
        int i;
        Intrinsics.checkParameterIsNotNull(nativeAd, "nativeAd");
        if (this.alreadyShownOnSelectFloatingAdViews.contains(nativeAd.getAdUnitId())) {
            DSLog.INSTANCE.adLogM(nativeAd.getAdUnitId() + " -> OnSelect: DISABLED; banners have already been shown");
            showDebugToastMessage(nativeAd.getAdUnitId() + " -> OnSelect: DISABLED; banners have already been shown");
            return;
        }
        List<Ad.ImageResource> imageResources = nativeAd.getImageResources();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = imageResources.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if ((((Ad.ImageResource) next).getWhenResourceIsDisplayed() == Ad.DisplayTrigger.OnSelect ? 1 : 0) != 0) {
                arrayList.add(next);
            }
        }
        int size = arrayList.size();
        if (size == 0) {
            return;
        }
        DSLog.INSTANCE.adLogM(nativeAd.getAdUnitId() + " -> OnSelect: " + size + " banners to show");
        showDebugToastMessage(nativeAd.getAdUnitId() + " -> OnSelect: " + size + " banners to show");
        this.alreadyShownOnSelectFloatingAdViews.add(nativeAd.getAdUnitId());
        for (Ad.ImageResource imageResource : nativeAd.getImageResources().subList(1, nativeAd.getImageResources().size())) {
            if (imageResource.getWhenResourceIsDisplayed() == Ad.DisplayTrigger.OnSelect) {
                Context applicationContext = getApplicationContext();
                Intrinsics.checkExpressionValueIsNotNull(applicationContext, "applicationContext");
                FloatingAdBannerView floatingAdBannerView = new FloatingAdBannerView(applicationContext);
                Activity activity = get_activeActivity();
                if (activity == null) {
                    Intrinsics.throwNpe();
                }
                String simpleName = activity.getClass().getSimpleName();
                Intrinsics.checkExpressionValueIsNotNull(simpleName, "activeActivity!!.javaClass.simpleName");
                Activity activity2 = get_activeActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                floatingAdBannerView.setAdImageResource(simpleName, activity2.hashCode(), nativeAd, imageResource, i, new Function4<String, String, Integer, Long, Unit>() { // from class: com.dotscreen.portal.PortalApplication.handleInSwimlaneNativeAdSelection.1
                    {
                        super(4);
                    }

                    @Override // kotlin.jvm.functions.Function4
                    public /* bridge */ /* synthetic */ Unit invoke(String str, String str2, Integer num, Long l) {
                        invoke(str, str2, num.intValue(), l.longValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(String runtimeTag, String owningActivityName, int i2, long j) {
                        Intrinsics.checkParameterIsNotNull(runtimeTag, "runtimeTag");
                        Intrinsics.checkParameterIsNotNull(owningActivityName, "owningActivityName");
                        PortalApplication.this.startBannerHidingCountDown(runtimeTag, owningActivityName, i2, j);
                    }
                });
                showDismissibleFloatingAd(floatingAdBannerView, imageResource.getWhenResourceIsDisplayed());
            }
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void restartApplicationWithPopupOrPlanForLater() {
        if (!this.portalIsInForeground) {
            String TAG = this.TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
            dropBreadcrumb(TAG, "PortalApplication is not in foreground. App will restart the next time it it.");
            this.appRestartIsPlanned = true;
            return;
        }
        Activity activity = get_activeActivity();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.dotscreen.portal.PortalApplication.restartApplicationWithPopupOrPlanForLater.1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        Activity activity2 = PortalApplication.this.get_activeActivity();
                        if (activity2 == null) {
                            Intrinsics.throwNpe();
                        }
                        new PortalDialog.Builder(activity2).setTitle(com.dotscreen.megacable.portal.R.string.connectivity_back_dialog_title).setMessage(com.dotscreen.megacable.portal.R.string.connectivity_back_dialog_message).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.dotscreen.portal.PortalApplication.restartApplicationWithPopupOrPlanForLater.1.1
                            @Override // android.content.DialogInterface.OnDismissListener
                            public final void onDismiss(DialogInterface dialogInterface) {
                                Log.d(PortalApplication.this.TAG, "DialogInterface.OnDismissListener");
                                PortalApplication.this.restartOrKillXView();
                            }
                        }).setBackMessage(false).setTimeOut(5000L).create().show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private final void recreateActivity() {
        Activity activity = get_activeActivity();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.dotscreen.portal.PortalApplication.recreateActivity.1
                @Override // java.lang.Runnable
                public final void run() {
                    Activity activity2 = PortalApplication.this.get_activeActivity();
                    if (activity2 != null) {
                        activity2.recreate();
                    }
                    PortalApplication.this.showDebugToastMessage("Refreshing active activity");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void restartOrKillXView() {
        closeLiveApplication(!CommonUtils.isDVBCSTB(getApplicationContext()));
        if (this.portalIsInForeground || (this.liveTvIsInForeground && !CommonUtils.isDVBCSTB(getApplicationContext()))) {
            Intent intent = new Intent(this, (Class<?>) HomeActivity.class);
            intent.addFlags(268468224);
            startActivity(intent);
        }
        System.exit(0);
        throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void closeLiveApplication(boolean shouldClose) {
        if (shouldClose) {
            Intent intent = new Intent();
            intent.setAction(CommonUtils.ACTION_CLOSE_LIVE_APP);
            sendBroadcast(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshHome() {
        Activity activity = get_activeActivity();
        if (activity != null) {
            Activity_ExtensionKt.launchHome(activity, 268468224);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void dropBreadcrumb(String tag, String message) {
        Sentry.addBreadcrumb('(' + tag + ") " + message);
        Log.e(tag, message);
    }

    public final boolean shouldTriggerLimitedMode(Throwable error, URL url) {
        boolean z;
        String string;
        Intrinsics.checkParameterIsNotNull(error, "error");
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("CheckJob(");
        Job job = this.checkNetworkJob;
        sb.append(job != null ? Boolean.valueOf(job.isActive()) : null);
        sb.append("): ");
        sb.append(this.checkNetworkJob);
        Log.e(str, sb.toString());
        String str2 = CommonUtils.COMPASS_URL;
        Intrinsics.checkExpressionValueIsNotNull(str2, "CommonUtils.COMPASS_URL");
        List listMutableListOf = CollectionsKt.mutableListOf(str2, CommonUtils.CONFIG_URL + "/features_tv.json", CommonUtils.CONFIG_URL + "/channel-scan.json", CommonUtils.OPTIMIZER_URL + "/api/graphql/", CommonUtils.OPTIMIZER_URL + "/api/mobile/v1/artworks", CommonUtils.OPTIMIZER_URL + "/api/mobile/v1/programs/", CommonUtils.OPTIMIZER_URL + "/config/img/avatars/", CommonUtils.OPTIMIZER_URL + "/personalization/v1/clientEvents", CommonUtils.OPTIMIZER_URL + "/personalization/v1/viewingPositions", CommonUtils.OPTIMIZER_URL + "/RTEFacade/GetClientData", CommonUtils.OPTIMIZER_URL + "/RTEFacade/SetClientData", CommonUtils.OPTIMIZER_URL + "/RTEFacade/GetHousehold", CommonUtils.OPTIMIZER_URL + "/RTEFacade/GetRecordingMarkedPosition", CommonUtils.OPTIMIZER_URL + "/RTEFacade/MarkRecordingPosition");
        if (TargetedAdsConfigProvider.INSTANCE.getAdProviderBaseURL() != null) {
            String adProviderBaseURL = TargetedAdsConfigProvider.INSTANCE.getAdProviderBaseURL();
            if (adProviderBaseURL == null) {
                Intrinsics.throwNpe();
            }
            listMutableListOf.add(adProviderBaseURL);
        }
        Iterator<String> it = TargetedAdsConfigProvider.INSTANCE.whitelistErrorsFromTheseURLs().iterator();
        while (it.hasNext()) {
            listMutableListOf.add(it.next());
        }
        Iterator it2 = listMutableListOf.iterator();
        loop1: while (true) {
            z = false;
            while (true) {
                if (!it2.hasNext()) {
                    break loop1;
                }
                String str3 = (String) it2.next();
                if (!z) {
                    if ((url == null || (string = url.toString()) == null) ? false : StringsKt.startsWith$default(string, str3, false, 2, (Object) null)) {
                    }
                }
                z = true;
            }
        }
        Log.d(this.TAG, "canIgnoreURL=" + z + " | " + url);
        if (z) {
            return false;
        }
        if ((!(error instanceof VolleyError) || !unknownError((VolleyError) error)) && !(error instanceof SocketTimeoutException) && !(error instanceof InterruptedIOException) && !(error instanceof IOException)) {
            return false;
        }
        Job job2 = this.checkNetworkJob;
        if (job2 != null) {
            Boolean boolValueOf = job2 != null ? Boolean.valueOf(job2.isActive()) : null;
            if (boolValueOf == null) {
                Intrinsics.throwNpe();
            }
            if (boolValueOf.booleanValue()) {
                return false;
            }
        }
        return true;
    }

    private final boolean unknownError(VolleyError error) {
        Throwable cause = error.getCause();
        int httpStatusCodes = cause instanceof RightVBackendErrorException ? ((RightVBackendErrorException) cause).getHttpStatusCodes() : ErrorManager.INSTANCE.getErrorStatusCode(error);
        Log.w(this.TAG, "OnRequestErrorListener: VolleyError error " + httpStatusCodes + " and cause => ", cause);
        if (httpStatusCodes == 404) {
            Log.w(this.TAG, "OnRequestErrorListener: ignored error 404");
        } else {
            if (cause == null || !(cause instanceof BackendErrorException)) {
                return true;
            }
            Throwable cause2 = error.getCause();
            if (cause2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.bokit.BackendErrorException");
            }
            String backendCode = ((BackendErrorException) cause2).getBackendCode();
            Log.w(this.TAG, "OnRequestErrorListener: with " + backendCode);
            if (backendCode.equals("PLUGIN_GENERAL_ERROR")) {
                return true;
            }
            Log.w(this.TAG, "OnRequestErrorListener: ignored error " + backendCode);
        }
        return false;
    }

    public final void initVOFMA() throws VOException {
        PortalApplication portalApplication = this;
        String uniqueIdentifier = VOPlayer.getUniqueIdentifier(portalApplication);
        Intrinsics.checkExpressionValueIsNotNull(uniqueIdentifier, "VOPlayer.getUniqueIdentifier(this)");
        this.voPlayerUniqueId = String_ExtensionKt.hash256(uniqueIdentifier);
        VOPlayer.setEnabledReports(portalApplication, VOPlayer.VOPlayerEnabledReports.REPORT_CRASHES);
        VoFmaInitParameters voFmaInitParameters = new VoFmaInitParameters();
        voFmaInitParameters.mListener = this;
        voFmaInitParameters.mConfigurationUrl = "https://api-megacable-prod.vo-maas.services/monitoring/1.0/configuration";
        voFmaInitParameters.mApplicationName = "com.dotscreen.megacable.portal";
        voFmaInitParameters.mApplicationVersion = BuildConfig.VERSION_NAME;
        voFmaInitParameters.mApplicationContext = getApplicationContext();
        voFmaInitParameters.mDeviceID = Build.DISPLAY;
        voFmaInitParameters.mDeviceCategory = Build.BRAND + " - " + Build.MANUFACTURER + " - " + Build.MODEL + " - " + Build.TYPE;
        voFmaInitParameters.mPlayerId = this.voPlayerUniqueId;
        voFmaInitParameters.mFirmwareVersion = DeviceInfo.INSTANCE.getFirmwareVersionName();
        voFmaInitParameters.mSerialNumber = Build.getSerial();
        VoFmaController.VoFmaReturnCode voFmaReturnCodeInitialize = VoFmaController.initialize(voFmaInitParameters);
        Intrinsics.checkExpressionValueIsNotNull(voFmaReturnCodeInitialize, "VoFmaController.initialize(voFmaInitParameters)");
        if (voFmaReturnCodeInitialize == VoFmaController.VoFmaReturnCode.Success) {
            VoFmaController.getFmaController().reloadConfiguration("https://api-megacable-prod.vo-maas.services/monitoring/1.0/configuration");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.app.Application
    public void onCreate() throws VOException {
        String sdkVersion;
        super.onCreate();
        PortalApplication portalApplication = this;
        this.staticVoPlayer = new VOPlayer(portalApplication);
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("VOPlayer version ");
        try {
            sdkVersion = VOPlayer.getSdkVersion(this);
        } catch (Exception unused) {
            sdkVersion = "***ERROR***";
        }
        sb.append(sdkVersion);
        Log.d(str, sb.toString());
        initVOFMA();
        boolean z = false;
        this.isSartupScreenAlreadyDisplayed = false;
        DSLog.INSTANCE.appStartTimeStamp(getClass().getSimpleName() + '@' + hashCode(), false);
        Log.d(this.TAG, "onCreate() version , elapsedRealtime = " + SystemClock.elapsedRealtime());
        Configuration configurationBuild = new Configuration.Builder().setJobSchedulerJobIdRange(this.MIN_JOB_ID_VALUE, this.MAX_JOB_ID_VALUE).build();
        Intrinsics.checkExpressionValueIsNotNull(configurationBuild, "Configuration.Builder()\n…\n                .build()");
        fetchCurrentDeviceInfo();
        WorkManager.initialize(portalApplication, configurationBuild);
        if ((AudioZTEManager.INSTANCE.isZtePlatform() && AudioZTEManager.INSTANCE.isZtePhase2()) || AudioZTEManager.INSTANCE.isZtePlatform2()) {
            Context applicationContext = getApplicationContext();
            Intrinsics.checkExpressionValueIsNotNull(applicationContext, "applicationContext");
            new AudioZTEManager(applicationContext).connectToAudioService();
        }
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() { // from class: com.dotscreen.portal.PortalApplication.onCreate.1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Log.w(PortalApplication.this.TAG, "RxJava Uncaught Exception", th);
            }
        });
        BOService bOService = CommonUtils.getBOService(portalApplication);
        this.boService = bOService;
        if (bOService != null) {
            bOService.addOnRequestErrorListener(this.requestErrorListener);
        }
        BOService bOService2 = this.boService;
        if (bOService2 != null) {
            bOService2.addOnRequestResponseListener(this.requestResponseListener);
        }
        this.requestQueue = Volley.newRequestQueue(portalApplication);
        this.imageLoader = new ImageLoader(Volley.newRequestQueue(portalApplication), new BitmapLruCache(portalApplication)) { // from class: com.dotscreen.portal.PortalApplication.onCreate.2
            @Override // com.android.volley.toolbox.ImageLoader
            public ImageLoader.ImageContainer get(String requestUrl, ImageLoader.ImageListener imageListener, int maxWidth, int maxHeight, ImageView.ScaleType scaleType) {
                if (requestUrl != null && StringsKt.contains$default((CharSequence) requestUrl, (CharSequence) com.android.tv.common.BuildConfig.IGNORE_RESIZE_URL, false, 2, (Object) null)) {
                    ImageLoader.ImageContainer imageContainer = super.get(requestUrl, imageListener, maxWidth, maxHeight, scaleType);
                    Intrinsics.checkExpressionValueIsNotNull(imageContainer, "super.get(requestUrl, im…th, maxHeight, scaleType)");
                    return imageContainer;
                }
                ImageLoader.ImageContainer imageContainer2 = super.get(CommonUtils.getResizedImageUrl(requestUrl, maxWidth, maxHeight), imageListener, maxWidth, maxHeight, scaleType);
                Intrinsics.checkExpressionValueIsNotNull(imageContainer2, "super.get(newUrl, imageL…th, maxHeight, scaleType)");
                return imageContainer2;
            }
        };
        this.localImageLoader = new ImageLoader(Volley.newRequestQueue(portalApplication), new LocalImageCache(portalApplication));
        FirebaseApp.initializeApp(portalApplication);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ActionBroadcastReceiverKt.ACTION_REBOOT);
        intentFilter.addAction(CommonUtils.ACTION_CLOSE_PORTAL_APP);
        intentFilter.addAction(UpdateManagerKt.ACTION_NOTIFY_UPDATE_CHECK);
        intentFilter.addAction(UpdateManagerKt.ACTION_NOTIFY_NEXT_CHECK);
        intentFilter.addAction(UpdateManagerKt.ACTION_NOTIFY_PERIODIC_CHECK_VALUE);
        intentFilter.addAction(UpdateManagerKt.ACTION_NOTIFY_FIRST_CHECK);
        intentFilter.addAction(UpdateManagerKt.ACTION_NOTIFY_CHECK_RESULT);
        intentFilter.addAction(UpdateManagerKt.ACTION_NOTIFY_CHECK_FAILED);
        registerReceiver(new ActionBroadcastReceiver(), intentFilter);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction(UpdateManagerKt.ACTION_APPLICATION_GOES_BACKGROUND);
        intentFilter2.addAction(UpdateManagerKt.ACTION_APPLICATION_GOES_FOREGROUND);
        registerReceiver(new ApplicationStateBroadcastReceiver(), intentFilter2);
        IntentFilter intentFilter3 = new IntentFilter();
        intentFilter3.addAction(CommonUtils.ACTION_REQUEST_EXCEPTION);
        registerReceiver(new RequestErrorBroadcastReceiver(), intentFilter3);
        registerReceiver(new UserSuspendedBroadcastReceiver(this), new IntentFilter(CommonUtils.ACTION_USER_SUSPENDED));
        IntentFilter intentFilter4 = new IntentFilter();
        intentFilter4.addAction("android.intent.action.PACKAGE_DATA_CLEARED");
        registerReceiver(new ClearedReceiver(), intentFilter4);
        IntentFilter intentFilter5 = new IntentFilter();
        intentFilter5.addAction("android.intent.action.SCREEN_ON");
        intentFilter5.addAction("android.intent.action.SCREEN_OFF");
        intentFilter5.addAction("android.intent.action.DREAMING_STARTED");
        intentFilter5.addAction("android.intent.action.DREAMING_STOPPED");
        registerReceiver(new ScreenOnOffBroadcastReceiver(), intentFilter5);
        NotificationManager notificationManager = new NotificationManager(portalApplication);
        this.notificationManager = notificationManager;
        if (notificationManager == null) {
            Intrinsics.throwNpe();
        }
        notificationManager.start();
        RightVRequestManager rightVRequestManager = CommonUtils.getRightVRequestManager(portalApplication);
        this.requestManager = rightVRequestManager;
        if (rightVRequestManager != null) {
            rightVRequestManager.addOnRequestErrorListener(this.requestErrorListener);
        }
        this.analyticsManager = new AnalyticsManager(null, null == true ? 1 : 0, 3, null == true ? 1 : 0);
        this.errorManager = new MegacableErrorManager(portalApplication);
        this.updaterInfo = new UpdaterInfo(portalApplication);
        initAnalyticsModules();
        this.networkStateManager = new NetworkStateManager(portalApplication, z, 2, null == true ? 1 : 0);
        this.bootSequenceManager = new BootSequenceManager(portalApplication);
        this.applicationsManager = new ApplicationsManager(portalApplication);
        registerActivityLifecycleCallbacks(this.activityLifecycleCallbacks);
        if (!Constants.INSTANCE.getINPUT_SERIAL_NUMER_AT_STARTUP()) {
            loadConfig();
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C02933(null), 2, null);
        LifecycleOwner lifecycleOwner = ProcessLifecycleOwner.get();
        Intrinsics.checkExpressionValueIsNotNull(lifecycleOwner, "ProcessLifecycleOwner.get()");
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        Intrinsics.checkExpressionValueIsNotNull(lifecycle, "ProcessLifecycleOwner.get().lifecycle");
        lifecycle.getCurrentState();
        this.noInternetProbesFeature.start();
        Log.d(this.TAG, "Debug view should not be visible");
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.PortalApplication$onCreate$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PortalApplication.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.dotscreen.portal.PortalApplication$onCreate$3", f = "PortalApplication.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C02933 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C02933(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C02933 c02933 = PortalApplication.this.new C02933(completion);
            c02933.p$ = (CoroutineScope) obj;
            return c02933;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02933) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws IOException {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Cursor cursorQuery = PortalApplication.this.getContentResolver().query(TvContract.Channels.CONTENT_URI, Channel.PROJECTION, null, null, null);
            if (cursorQuery != null) {
                Cursor cursor = cursorQuery;
                Throwable th = (Throwable) null;
                try {
                    Cursor cursor2 = cursor;
                    PortalApplicationKt.dumpColumnNames(cursor2, TvContract.Channels.CONTENT_URI);
                    while (cursor2.moveToNext()) {
                        PortalApplicationKt.dumpValues(cursor2, TvContract.Channels.CONTENT_URI);
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
            Cursor cursorQuery2 = PortalApplication.this.getContentResolver().query(ScanStatusProvider.INSTANCE.getURI(), new String[]{ScanStatusProvider.COLUMN_STATUS}, null, null, null);
            if (cursorQuery2 != null) {
                Cursor cursor3 = cursorQuery2;
                Throwable th4 = (Throwable) null;
                try {
                    Cursor cursor4 = cursor3;
                    PortalApplicationKt.dumpColumnNames(cursor4, ScanStatusProvider.INSTANCE.getURI());
                    while (cursor4.moveToNext()) {
                        PortalApplicationKt.dumpValues(cursor4, ScanStatusProvider.INSTANCE.getURI());
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
            ScanStatusHandler.ScanState currentScanStatus = ScanStatusHandler.INSTANCE.getCurrentScanStatus(PortalApplication.this);
            if (currentScanStatus == ScanStatusHandler.ScanState.IN_PROGRESS || currentScanStatus == ScanStatusHandler.ScanState.STARTED) {
                Log.v(PortalApplication.this.TAG, "latest ScanStatus : " + currentScanStatus);
                ScanStatusHandler.INSTANCE.setCurrentScanStatus(PortalApplication.this, ScanStatusHandler.ScanState.WAITING);
            }
            return Unit.INSTANCE;
        }
    }

    private final void showDebugFloatingPanel() {
        WindowManager windowManager = (WindowManager) getSystemService(WindowManager.class);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-2, -2, 2038, 8, -3);
        layoutParams.gravity = 49;
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        layoutParams.y = (int) TypedValue.applyDimension(1, 10.0f, resources.getDisplayMetrics());
        if (windowManager != null) {
            windowManager.addView(new DebugInfos(this), layoutParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void broadcastNewNetworkStatus(NetworkStatus networkStatus) {
        Intent intent = new Intent();
        intent.setAction(CommonUtils.UPDATE_NETWORK_STATUS);
        intent.putExtra(CommonUtils.NETWORK_STATUS_EXTRA_KEY, networkStatus.getClass().getSimpleName());
        sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void reportSentryEventToVO(SentryEvent event, Object hint) {
        Config value;
        if (hint == SentryReportHint.Updater) {
            VOPlayer.VOPlayerSeverity vOPlayerSeverity = VOPlayer.VOPlayerSeverity.SEVERITY_ERROR;
            VoFmaEvent.VOPlayerSeverity vOPlayerSeverity2 = VoFmaEvent.VOPlayerSeverity.SEVERITY_ERROR;
            String str = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").withZone(ZoneOffset.UTC).format(Instant.now());
            Pair[] pairArr = new Pair[8];
            pairArr[0] = TuplesKt.to("VO_PLAYER_ID", this.voPlayerUniqueId);
            pairArr[1] = TuplesKt.to("TIMESTAMP", str);
            pairArr[2] = TuplesKt.to("APP_VERSION", BuildConfig.VERSION_NAME);
            NetworkStateManager networkStateManager = this.networkStateManager;
            if (networkStateManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("networkStateManager");
            }
            pairArr[3] = TuplesKt.to("NETWORK_TYPE", networkStateManager.getActiveNetworkType().getVoName());
            pairArr[4] = TuplesKt.to("FIRMWARE_VERSION", DeviceInfo.INSTANCE.getFirmwareVersionName());
            pairArr[5] = TuplesKt.to("DEVICE_MODEL", DeviceInfo.INSTANCE.getDeviceModel());
            BOService bOService = this.boService;
            UserService userService = bOService != null ? bOService.getUserService() : null;
            if (userService == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
            }
            pairArr[6] = TuplesKt.to("SERIAL_NUMBER", ((MegacableUserService) userService).getSerialNumber());
            pairArr[7] = TuplesKt.to("ROOT_CAUSE_ANALYSIS", SentryEventExtentionsKt.getOTARootCauseAnalysis(event));
            Map mapMutableMapOf = MapsKt.mutableMapOf(pairArr);
            Message message = event.getMessage();
            String message2 = message != null ? message.getMessage() : null;
            if (message2 != null) {
                switch (message2.hashCode()) {
                    case -2038222464:
                        if (message2.equals(CommonUtils.OTA_CHECK_FAILED_SENTRY_TITLE)) {
                            mapMutableMapOf.put("ERROR_TYPE", "OTA_CHECK_FAILED");
                        }
                        break;
                    case -575520242:
                        if (message2.equals(CommonUtils.OTA_DOWNLOAD_WARNING_SENTRY_TITLE)) {
                            mapMutableMapOf.put("ERROR_TYPE", "OTA_DOWNLOAD_WARNING");
                        }
                        break;
                    case -439657292:
                        if (message2.equals(CommonUtils.OTA_DOWNLOAD_FAILED_SENTRY_TITLE)) {
                            mapMutableMapOf.put("ERROR_TYPE", "OTA_DOWNLOAD_FAILED");
                        }
                        break;
                    case 650402799:
                        if (message2.equals(CommonUtils.OTA_INSTALLATION_FAILED_SENTRY_TITLE)) {
                            mapMutableMapOf.put("ERROR_TYPE", "OTA_INSTALLATION_FAILED");
                        }
                        break;
                }
            }
            String string = new JSONObject(mapMutableMapOf).toString();
            Intrinsics.checkExpressionValueIsNotNull(string, "JSONObject(jsonMap).toString()");
            Log.v(this.TAG, "Send to VO Frontend Monitoring Agent : \n " + new JSONObject(string).toString(4));
            Message message3 = event.getMessage();
            VOPlayer.logEvent(vOPlayerSeverity, message3 != null ? message3.getMessage() : null, string);
            VoFmaController fmaController = VoFmaController.getFmaController();
            Message message4 = event.getMessage();
            fmaController.logEvent(vOPlayerSeverity2, message4 != null ? message4.getMessage() : null, string);
            return;
        }
        if (hint == SentryReportHint.Memory && (value = getConfig().getValue()) != null && value.getShouldLogLowMemory()) {
            VOPlayer.VOPlayerSeverity vOPlayerSeverity3 = VOPlayer.VOPlayerSeverity.SEVERITY_ERROR;
            VoFmaEvent.VOPlayerSeverity vOPlayerSeverity4 = VoFmaEvent.VOPlayerSeverity.SEVERITY_ERROR;
            String str2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").withZone(ZoneOffset.UTC).format(Instant.now());
            Pair[] pairArr2 = new Pair[9];
            pairArr2[0] = TuplesKt.to("VO_PLAYER_ID", this.voPlayerUniqueId);
            pairArr2[1] = TuplesKt.to("TIMESTAMP", str2);
            pairArr2[2] = TuplesKt.to("APP_VERSION", BuildConfig.VERSION_NAME);
            NetworkStateManager networkStateManager2 = this.networkStateManager;
            if (networkStateManager2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("networkStateManager");
            }
            pairArr2[3] = TuplesKt.to("NETWORK_TYPE", networkStateManager2.getActiveNetworkType().getVoName());
            pairArr2[4] = TuplesKt.to("FIRMWARE_VERSION", DeviceInfo.INSTANCE.getFirmwareVersionName());
            pairArr2[5] = TuplesKt.to("DEVICE_MODEL", DeviceInfo.INSTANCE.getDeviceModel());
            BOService bOService2 = this.boService;
            UserService userService2 = bOService2 != null ? bOService2.getUserService() : null;
            if (userService2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
            }
            pairArr2[6] = TuplesKt.to("SERIAL_NUMBER", ((MegacableUserService) userService2).getSerialNumber());
            pairArr2[7] = TuplesKt.to("EVENT_TYPE", String.valueOf(event.getTag("memory")));
            pairArr2[8] = TuplesKt.to("FREE_MEMORY", String.valueOf(event.getTag("freeMemory")));
            String string2 = new JSONObject(MapsKt.mutableMapOf(pairArr2)).toString();
            Intrinsics.checkExpressionValueIsNotNull(string2, "JSONObject(jsonMap).toString()");
            Log.v(this.TAG, "Send to VO Frontend Monitoring Agent : \n " + new JSONObject(string2).toString(4));
            Message message5 = event.getMessage();
            VOPlayer.logEvent(vOPlayerSeverity3, message5 != null ? message5.getMessage() : null, string2);
            VoFmaController fmaController2 = VoFmaController.getFmaController();
            Message message6 = event.getMessage();
            fmaController2.logEvent(vOPlayerSeverity4, message6 != null ? message6.getMessage() : null, string2);
        }
    }

    @Override // com.dotscreen.portal.PortalApplicationSingletons
    public BOService getBOService() {
        BOService bOService = this.boService;
        if (bOService == null) {
            Intrinsics.throwNpe();
        }
        return bOService;
    }

    @Override // com.dotscreen.tv.common.INetRequestProvider
    public RequestQueue getRequestQueue() {
        RequestQueue requestQueue = this.requestQueue;
        if (requestQueue == null) {
            Intrinsics.throwNpe();
        }
        return requestQueue;
    }

    @Override // com.dotscreen.tv.common.INetRequestProvider
    public ImageLoader getImageLoader() {
        ImageLoader imageLoader = this.imageLoader;
        if (imageLoader == null) {
            Intrinsics.throwNpe();
        }
        return imageLoader;
    }

    @Override // com.dotscreen.tv.common.INetRequestProvider
    public ImageLoader getLocalImageLoader() {
        ImageLoader imageLoader = this.localImageLoader;
        if (imageLoader == null) {
            Intrinsics.throwNpe();
        }
        return imageLoader;
    }

    @Override // com.dotscreen.portal.PortalApplicationSingletons
    public MegacableErrorManager getErrorManager() {
        MegacableErrorManager megacableErrorManager = this.errorManager;
        if (megacableErrorManager == null) {
            Intrinsics.throwNpe();
        }
        return megacableErrorManager;
    }

    @Override // com.dotscreen.portal.PortalApplicationSingletons
    public Single<Lineup> getCurrentLineup() {
        Single<Lineup> currentLineup = CommonUtils.getCurrentLineup(this.boService);
        Intrinsics.checkExpressionValueIsNotNull(currentLineup, "CommonUtils.getCurrentLineup(boService)");
        return currentLineup;
    }

    @Override // com.dotscreen.portal.PortalApplicationSingletons
    public UpdaterInfo getUpdaterInfo() {
        UpdaterInfo updaterInfo = this.updaterInfo;
        if (updaterInfo == null) {
            Intrinsics.throwNpe();
        }
        return updaterInfo;
    }

    @Override // com.dotscreen.portal.PortalApplicationSingletons
    public NotificationManager getNotificationManager() {
        NotificationManager notificationManager = this.notificationManager;
        if (notificationManager == null) {
            Intrinsics.throwNpe();
        }
        return notificationManager;
    }

    @Override // com.dotscreen.portal.PortalApplicationSingletons
    public NetworkStateManager getNetworkStateManager() {
        NetworkStateManager networkStateManager = this.networkStateManager;
        if (networkStateManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("networkStateManager");
        }
        if (networkStateManager == null) {
            Intrinsics.throwNpe();
        }
        return networkStateManager;
    }

    @Override // com.dotscreen.portal.PortalApplicationSingletons
    public BootSequenceManager getBootSequenceManager() {
        BootSequenceManager bootSequenceManager = this.bootSequenceManager;
        if (bootSequenceManager == null) {
            Intrinsics.throwNpe();
        }
        return bootSequenceManager;
    }

    @Override // com.dotscreen.portal.PortalApplicationSingletons
    public AnalyticsManager getAnalyticsManager() {
        AnalyticsManager analyticsManager = this.analyticsManager;
        if (analyticsManager == null) {
            Intrinsics.throwNpe();
        }
        return analyticsManager;
    }

    @Override // com.dotscreen.portal.PortalApplicationSingletons
    public LiveData<Config> getConfig() {
        return this.configLiveData;
    }

    @Override // com.dotscreen.portal.PortalApplicationSingletons
    public FeaturesManager getFeaturesManager() {
        return this.featuresManager;
    }

    @Override // com.dotscreen.tv.common.INetRequestProvider
    public RequestManager getRequestManager() {
        RequestManager requestManager = this.requestManager;
        if (requestManager == null) {
            Intrinsics.throwNpe();
        }
        return requestManager;
    }

    @Override // com.dotscreen.portal.PortalApplicationSingletons
    public ApplicationsManager getApplicationsManager() {
        ApplicationsManager applicationsManager = this.applicationsManager;
        if (applicationsManager == null) {
            Intrinsics.throwNpe();
        }
        return applicationsManager;
    }

    @Override // com.dotscreen.portal.PortalApplicationSingletons
    /* JADX INFO: renamed from: isDegradedMode, reason: from getter */
    public boolean getDegradedMode() {
        return this.degradedMode;
    }

    @Override // com.dotscreen.portal.PortalApplicationSingletons
    /* JADX INFO: renamed from: bootWithoutInternet, reason: from getter */
    public boolean getBootWithoutInternet() {
        return this.bootWithoutInternet;
    }

    @Override // com.dotscreen.portal.PortalApplicationSingletons
    public TimeshiftingHelper getTimeshiftingHelper() {
        return this.timeshiftingHelper;
    }

    public final Disposable getLoadDisposable() {
        return this.loadDisposable;
    }

    public final void setLoadDisposable(Disposable disposable) {
        this.loadDisposable = disposable;
    }

    public final void loadConfig() {
        if (this.configIsAlreadyLoading) {
            return;
        }
        this.configIsAlreadyLoading = true;
        final Single singleCreate = Single.create(new SingleOnSubscribe<T>() { // from class: com.dotscreen.portal.PortalApplication$loadConfig$localConfig$1
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter<JSONObject> observer) throws IOException {
                Intrinsics.checkParameterIsNotNull(observer, "observer");
                BootSequenceManager bootSequenceManager = this.this$0.bootSequenceManager;
                if (bootSequenceManager == null) {
                    Intrinsics.throwNpe();
                }
                BootSequenceManager.setStep$default(bootSequenceManager, BootSequenceManager.Steps.RETRIEVE_CONFIG, BootSequenceManager.State.WILL_START, false, 4, null);
                BootSequenceManager bootSequenceManager2 = this.this$0.bootSequenceManager;
                if (bootSequenceManager2 == null) {
                    Intrinsics.throwNpe();
                }
                BootSequenceManager.setStep$default(bootSequenceManager2, BootSequenceManager.Steps.RETRIEVE_CONFIG, BootSequenceManager.State.IN_PROGRESS, false, 4, null);
                InputStream inputStreamOpen = this.this$0.getAssets().open("config.json");
                byte[] bArr = new byte[inputStreamOpen.available()];
                inputStreamOpen.read(bArr);
                observer.onSuccess(new JSONObject(new String(bArr, Charsets.UTF_8)));
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleCreate, "Single.create<JSONObject…tring(buffer)))\n        }");
        RequestManager requestManager = this.requestManager;
        if (requestManager == null) {
            Intrinsics.throwNpe();
        }
        final Single singleOnErrorResumeNext = RequestManager.getJSONObject$default(requestManager, 0, new URL(CommonUtils.CONFIG_URL + "/config_tv.json"), null, 4, null).doAfterSuccess(new Consumer<JSONObject>() { // from class: com.dotscreen.portal.PortalApplication$loadConfig$configJsonSingle$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(JSONObject jSONObject) {
                Sentry.addBreadcrumb("Config download is OK");
                BootSequenceManager bootSequenceManager = this.this$0.bootSequenceManager;
                if (bootSequenceManager == null) {
                    Intrinsics.throwNpe();
                }
                BootSequenceManager.setStep$default(bootSequenceManager, BootSequenceManager.Steps.RETRIEVE_CONFIG, BootSequenceManager.State.FINISH, false, 4, null);
            }
        }).onErrorResumeNext(new Function<Throwable, SingleSource<? extends JSONObject>>() { // from class: com.dotscreen.portal.PortalApplication$loadConfig$configJsonSingle$2
            @Override // io.reactivex.functions.Function
            public final Single<JSONObject> apply(Throwable error) {
                Intrinsics.checkParameterIsNotNull(error, "error");
                Sentry.addBreadcrumb("Config download failed");
                Log.d(this.this$0.TAG, "loading Config error", error);
                error.printStackTrace();
                return singleCreate;
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleOnErrorResumeNext, "requestManager!!.getJSON…localConfig\n            }");
        Single singleDefer = Single.defer(new Callable<SingleSource<? extends T>>() { // from class: com.dotscreen.portal.PortalApplication$loadConfig$loginSingle$1
            @Override // java.util.concurrent.Callable
            public final Single<JSONObject> call() {
                Single<JSONObject> singleLoginAnonymous;
                BOService bOService = this.this$0.boService;
                MegacableUserService megacableUserService = (MegacableUserService) (bOService != null ? bOService.getUserService() : null);
                if (megacableUserService == null || (singleLoginAnonymous = megacableUserService.loginAnonymous()) == null) {
                    return null;
                }
                return singleLoginAnonymous.doOnError(new Consumer<Throwable>() { // from class: com.dotscreen.portal.PortalApplication$loadConfig$loginSingle$1.1
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Throwable it) {
                        Log.e(PortalApplication$loadConfig$loginSingle$1.this.this$0.TAG, "Error: " + it);
                        Sentry.addBreadcrumb("Login failed " + it);
                        PortalApplication portalApplication = PortalApplication$loadConfig$loginSingle$1.this.this$0;
                        Intrinsics.checkExpressionValueIsNotNull(it, "it");
                        portalApplication.handleError(it);
                    }
                });
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleDefer, "Single.defer {\n         …              }\n        }");
        RequestManager requestManager2 = this.requestManager;
        if (requestManager2 == null) {
            Intrinsics.throwNpe();
        }
        final Single singleOnErrorResumeNext2 = RequestManager.getJSONObject$default(requestManager2, 0, new URL(CommonUtils.CONFIG_URL + "/features_tv.json"), null, 4, null).doAfterSuccess(new Consumer<JSONObject>() { // from class: com.dotscreen.portal.PortalApplication$loadConfig$featuresJsonSingle$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(JSONObject jSONObject) {
                Sentry.addBreadcrumb("Features download is OK");
                BootSequenceManager bootSequenceManager = this.this$0.bootSequenceManager;
                if (bootSequenceManager == null) {
                    Intrinsics.throwNpe();
                }
                BootSequenceManager.setStep$default(bootSequenceManager, BootSequenceManager.Steps.RETRIEVE_FEATURES, BootSequenceManager.State.FINISH, false, 4, null);
            }
        }).onErrorResumeNext(new Function<Throwable, SingleSource<? extends JSONObject>>() { // from class: com.dotscreen.portal.PortalApplication$loadConfig$featuresJsonSingle$2
            @Override // io.reactivex.functions.Function
            public final Single<JSONObject> apply(Throwable error) {
                Intrinsics.checkParameterIsNotNull(error, "error");
                Sentry.addBreadcrumb("Features download failed");
                Log.d(this.this$0.TAG, "loading Features error", error);
                error.printStackTrace();
                return Single.create(new SingleOnSubscribe<T>() { // from class: com.dotscreen.portal.PortalApplication$loadConfig$featuresJsonSingle$2.1
                    @Override // io.reactivex.SingleOnSubscribe
                    public final void subscribe(SingleEmitter<JSONObject> observer) throws IOException {
                        Intrinsics.checkParameterIsNotNull(observer, "observer");
                        BootSequenceManager bootSequenceManager = PortalApplication$loadConfig$featuresJsonSingle$2.this.this$0.bootSequenceManager;
                        if (bootSequenceManager == null) {
                            Intrinsics.throwNpe();
                        }
                        BootSequenceManager.setStep$default(bootSequenceManager, BootSequenceManager.Steps.RETRIEVE_FEATURES, BootSequenceManager.State.WILL_START, false, 4, null);
                        BootSequenceManager bootSequenceManager2 = PortalApplication$loadConfig$featuresJsonSingle$2.this.this$0.bootSequenceManager;
                        if (bootSequenceManager2 == null) {
                            Intrinsics.throwNpe();
                        }
                        BootSequenceManager.setStep$default(bootSequenceManager2, BootSequenceManager.Steps.RETRIEVE_FEATURES, BootSequenceManager.State.IN_PROGRESS, false, 4, null);
                        InputStream inputStreamOpen = PortalApplication$loadConfig$featuresJsonSingle$2.this.this$0.getAssets().open("features.json");
                        byte[] bArr = new byte[inputStreamOpen.available()];
                        inputStreamOpen.read(bArr);
                        observer.onSuccess(new JSONObject(new String(bArr, Charsets.UTF_8)));
                    }
                });
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleOnErrorResumeNext2, "requestManager!!.getJSON…          }\n            }");
        Disposable disposable = this.loadDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        initTimeshift();
        BOService bOService = this.boService;
        UserService userService = bOService != null ? bOService.getUserService() : null;
        if (userService == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
        }
        ((MegacableUserService) userService).verifyVersion().subscribe(new Consumer<String>() { // from class: com.dotscreen.portal.PortalApplication.loadConfig.1
            @Override // io.reactivex.functions.Consumer
            public final void accept(String it) {
                DSLog.INSTANCE.m("VerifyVersion success = " + it);
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                Context applicationContext = PortalApplication.this.getApplicationContext();
                Intrinsics.checkExpressionValueIsNotNull(applicationContext, "applicationContext");
                if (MegacableUserServiceKt.matchAppsVersion(it, applicationContext)) {
                    return;
                }
                UserService userService2 = CommonUtils.getBOService(PortalApplication.this.getApplicationContext()).getUserService();
                if (userService2 == null) {
                    Intrinsics.throwNpe();
                }
                if (userService2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
                }
                ((MegacableUserService) userService2).notifyApplicationsUpdated().subscribe(new Action() { // from class: com.dotscreen.portal.PortalApplication.loadConfig.1.1
                    @Override // io.reactivex.functions.Action
                    public final void run() {
                    }
                }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.PortalApplication.loadConfig.1.2
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Throwable th) {
                    }
                });
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.PortalApplication.loadConfig.2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                DSLog.INSTANCE.m("VerifyVersion error = " + th.getMessage());
            }
        });
        this.degradedMode = false;
        Log.d(this.TAG, "forceNoConnection = false");
        RequestManager requestManager3 = this.requestManager;
        if (requestManager3 == null) {
            Intrinsics.throwNpe();
        }
        requestManager3.setForceNoConnection(false);
        final Completable completableCreate = Completable.create(new PortalApplication$loadConfig$scanChannelsIfNecessary$1(this));
        Intrinsics.checkExpressionValueIsNotNull(completableCreate, "Completable.create { emi…)\n            }\n        }");
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = false;
        Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
        booleanRef2.element = true;
        Log.d(this.TAG, "Start loading config...");
        NetworkStateManager networkStateManager = this.networkStateManager;
        if (networkStateManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("networkStateManager");
        }
        this.loadDisposable = networkStateManager.getDeviceStatus().takeUntil(new Predicate<NetworkStatus>() { // from class: com.dotscreen.portal.PortalApplication.loadConfig.3
            @Override // io.reactivex.functions.Predicate
            public final boolean test(NetworkStatus it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                boolean z = it instanceof NetworkStatus.Connected;
                if (z) {
                    PortalApplication.this.connectedToInternet = true;
                }
                return z;
            }
        }).flatMapMaybe((Function) new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.portal.PortalApplication.loadConfig.4
            @Override // io.reactivex.functions.Function
            public final Maybe<JSONObject> apply(NetworkStatus status) {
                Intrinsics.checkParameterIsNotNull(status, "status");
                Log.d(PortalApplication.this.TAG, "current network status: " + status);
                if (status instanceof NetworkStatus.Connected) {
                    Log.d(PortalApplication.this.TAG, "OK, continue loading...");
                    BootSequenceManager bootSequenceManager = PortalApplication.this.bootSequenceManager;
                    if (bootSequenceManager == null) {
                        Intrinsics.throwNpe();
                    }
                    BootSequenceManager.setStep$default(bootSequenceManager, BootSequenceManager.Steps.RETRIEVE_CONFIG, BootSequenceManager.State.IN_PROGRESS, false, 4, null);
                    return singleOnErrorResumeNext.toMaybe();
                }
                Sentry.addBreadcrumb("STB start without internet access");
                Log.d(PortalApplication.this.TAG, "KO, waiting for connected status...");
                return Maybe.empty();
            }
        }).timeout(20L, TimeUnit.SECONDS, singleCreate.toObservable()).flatMapSingle(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.PortalApplication.loadConfig.5
            @Override // io.reactivex.functions.Function
            public final Single<JSONObject> apply(JSONObject configJson) {
                Intrinsics.checkParameterIsNotNull(configJson, "configJson");
                Log.d(PortalApplication.this.TAG, "1. configJson: " + configJson);
                return completableCreate.andThen(Single.just(configJson));
            }
        }).flatMapSingle(new C02886(singleDefer, booleanRef, booleanRef2)).flatMapSingle(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.PortalApplication.loadConfig.7
            @Override // io.reactivex.functions.Function
            public final Single<Config> apply(Config config) {
                Intrinsics.checkParameterIsNotNull(config, "config");
                PortalApplication.this.initFirebase();
                BOService bOService2 = PortalApplication.this.boService;
                if (bOService2 == null) {
                    Intrinsics.throwNpe();
                }
                return CommonUtils.getCurrentLineup(bOService2).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.portal.PortalApplication.loadConfig.7.1
                    @Override // io.reactivex.functions.Function
                    public final Observable<com.dotscreen.bokit.model.Channel> apply(Lineup it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        return it.getItems();
                    }
                }).firstOrError().flatMapMaybe(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.portal.PortalApplication.loadConfig.7.2
                    @Override // io.reactivex.functions.Function
                    public final Maybe<TVAiring> apply(com.dotscreen.bokit.model.Channel it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        return it.airing(TVAiringTimeOfDay.Now);
                    }
                }).onErrorComplete().ignoreElement().andThen(Single.just(config));
            }
        }).flatMapSingle(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.PortalApplication.loadConfig.8
            @Override // io.reactivex.functions.Function
            public final Single<Config> apply(Config config) {
                Intrinsics.checkParameterIsNotNull(config, "config");
                BootSequenceManager bootSequenceManager = PortalApplication.this.bootSequenceManager;
                if (bootSequenceManager == null) {
                    Intrinsics.throwNpe();
                }
                BootSequenceManager.setStep$default(bootSequenceManager, BootSequenceManager.Steps.RETRIEVE_FEATURES, BootSequenceManager.State.WILL_START, false, 4, null);
                BootSequenceManager bootSequenceManager2 = PortalApplication.this.bootSequenceManager;
                if (bootSequenceManager2 == null) {
                    Intrinsics.throwNpe();
                }
                BootSequenceManager.setStep$default(bootSequenceManager2, BootSequenceManager.Steps.RETRIEVE_FEATURES, BootSequenceManager.State.IN_PROGRESS, false, 4, null);
                return singleOnErrorResumeNext2.doAfterSuccess(new Consumer<JSONObject>() { // from class: com.dotscreen.portal.PortalApplication.loadConfig.8.1
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(JSONObject featuresJson) throws JSONException {
                        FeaturesManager featuresManager = PortalApplication.this.featuresManager;
                        Intrinsics.checkExpressionValueIsNotNull(featuresJson, "featuresJson");
                        featuresManager.loadConfig(featuresJson);
                    }
                }).ignoreElement().andThen(Single.just(config));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Config>() { // from class: com.dotscreen.portal.PortalApplication.loadConfig.9
            @Override // io.reactivex.functions.Consumer
            public final void accept(Config config) {
                PortalApplication.this.initSentry(config);
                PortalApplication.this.setBootComplete(true);
                PortalApplication.this.configIsAlreadyLoading = false;
                NetworkStatus value = PortalApplication.access$getNetworkStateManager$p(PortalApplication.this).getDeviceStatus().getValue();
                if (value == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(value, "networkStateManager.deviceStatus.value!!");
                NetworkStatus networkStatus = value;
                PortalApplication.access$getNetworkStateManager$p(PortalApplication.this).getAppStatus().onNext(networkStatus);
                PortalApplication.this.broadcastNewNetworkStatus(networkStatus);
                config.startListeners();
                Log.d(PortalApplication.this.TAG, "config.sentryReportFlags = " + config.getSentryReportFlags());
                Log.d(PortalApplication.this.TAG, "config.sentryRateLimit =  " + config.getSentryRateLimitManagement());
                PortalApplication.this.configLiveData.postValue(config);
                PortalApplication portalApplication = PortalApplication.this;
                portalApplication.bootWithoutInternet = PortalApplication.access$getNetworkStateManager$p(portalApplication).hasInternetCapability() ^ true;
                if (PortalApplication.this.bootWithoutInternet) {
                    Sentry.addBreadcrumb("STB start without internet access");
                } else {
                    Sentry.addBreadcrumb("STB start successfully");
                }
                PortalApplication portalApplication2 = PortalApplication.this;
                NetworkStateManager networkStateManagerAccess$getNetworkStateManager$p = PortalApplication.access$getNetworkStateManager$p(PortalApplication.this);
                boolean z = PortalApplication.this.getLoginErrorReason() == null && PortalApplication.this.getErrorDuringBootSequence() == null;
                String str = PortalApplication.this.voPlayerUniqueId;
                if (str == null) {
                    Intrinsics.throwNpe();
                }
                BOService bOService2 = PortalApplication.this.boService;
                UserService userService2 = bOService2 != null ? bOService2.getUserService() : null;
                if (userService2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
                }
                portalApplication2.setConnectivityStatusReportManager(new ConnectivityStatusReportManager(networkStateManagerAccess$getNetworkStateManager$p, networkStatus, z, str, ((MegacableUserService) userService2).getSerialNumber()));
                if (PortalApplication.this.getLoginErrorReason() != null) {
                    String str2 = PortalApplication.this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Login failed for :");
                    LoginError loginErrorReason = PortalApplication.this.getLoginErrorReason();
                    sb.append(loginErrorReason != null ? loginErrorReason.getError() : null);
                    Log.d(str2, sb.toString());
                    PortalApplication$requestErrorListener$1 portalApplication$requestErrorListener$1 = PortalApplication.this.requestErrorListener;
                    LoginError loginErrorReason2 = PortalApplication.this.getLoginErrorReason();
                    if (loginErrorReason2 == null) {
                        Intrinsics.throwNpe();
                    }
                    portalApplication$requestErrorListener$1.onRequestError(loginErrorReason2.getError(), null);
                    return;
                }
                if (PortalApplication.this.getErrorDuringBootSequence() != null) {
                    PortalApplication$requestErrorListener$1 portalApplication$requestErrorListener$2 = PortalApplication.this.requestErrorListener;
                    Throwable errorDuringBootSequence = PortalApplication.this.getErrorDuringBootSequence();
                    if (errorDuringBootSequence == null) {
                        Intrinsics.throwNpe();
                    }
                    portalApplication$requestErrorListener$2.onRequestError(errorDuringBootSequence, null);
                    return;
                }
                PortalApplication.this.backendAvailable = true;
                PortalApplication portalApplication3 = PortalApplication.this;
                Activity activity = portalApplication3.get_activeActivity();
                if (activity == null) {
                    Intrinsics.throwNpe();
                }
                String simpleName = activity.getClass().getSimpleName();
                Intrinsics.checkExpressionValueIsNotNull(simpleName, "activeActivity!!.javaClass.simpleName");
                Activity activity2 = PortalApplication.this.get_activeActivity();
                portalApplication3.fetchAdsForPage(simpleName, activity2 != null ? activity2.hashCode() : 0);
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.PortalApplication.loadConfig.10
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Log.e(PortalApplication.this.TAG, "Error while loading config", th);
            }
        }, new Action() { // from class: com.dotscreen.portal.PortalApplication.loadConfig.11
            @Override // io.reactivex.functions.Action
            public final void run() {
                PortalApplication.this.initSentry(null);
                PortalApplication.this.setBootComplete(true);
                PortalApplication.this.noInternetProbesFeature.start();
            }
        });
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.PortalApplication$loadConfig$6, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PortalApplication.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0018\u0012\u0014\u0012\u0012 \u0004*\b\u0018\u00010\u0002R\u00020\u00030\u0002R\u00020\u00030\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "Lcom/dotscreen/portal/PortalApplication$Config;", "Lcom/dotscreen/portal/PortalApplication;", "kotlin.jvm.PlatformType", "configJson", "Lorg/json/JSONObject;", "apply"}, k = 3, mv = {1, 1, 16})
    static final class C02886<T, R> implements Function<T, SingleSource<? extends R>> {
        final /* synthetic */ Ref.BooleanRef $isIpConfigured;
        final /* synthetic */ Ref.BooleanRef $isSSLError;
        final /* synthetic */ Single $loginSingle;

        C02886(Single single, Ref.BooleanRef booleanRef, Ref.BooleanRef booleanRef2) {
            this.$loginSingle = single;
            this.$isSSLError = booleanRef;
            this.$isIpConfigured = booleanRef2;
        }

        @Override // io.reactivex.functions.Function
        public final Single<Config> apply(final JSONObject configJson) {
            Intrinsics.checkParameterIsNotNull(configJson, "configJson");
            Log.d(PortalApplication.this.TAG, "2. configJson: " + configJson);
            return this.$loginSingle.flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.portal.PortalApplication.loadConfig.6.1
                @Override // io.reactivex.functions.Function
                public final Observable<Profile> apply(JSONObject it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    BOService bOService = PortalApplication.this.boService;
                    MegacableUserService megacableUserService = (MegacableUserService) (bOService != null ? bOService.getUserService() : null);
                    if (megacableUserService != null) {
                        return MegacableUserService.profiles$default(megacableUserService, false, 1, null);
                    }
                    return null;
                }
            }).toList().flatMap(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.PortalApplication.loadConfig.6.2
                @Override // io.reactivex.functions.Function
                public final Single<Config> apply(List<Profile> it) {
                    UserService userService;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Log.d(PortalApplication.this.TAG, "BootSequenceManager: login RC OK will retrieve profiles => " + it.size());
                    Iterator<Profile> it2 = it.iterator();
                    int i = 0;
                    while (true) {
                        if (!it2.hasNext()) {
                            i = -1;
                            break;
                        }
                        if (it2.next().isMainProfile()) {
                            break;
                        }
                        i++;
                    }
                    boolean z = i != -1;
                    if (it.isEmpty()) {
                        BOService bOService = PortalApplication.this.boService;
                        userService = bOService != null ? bOService.getUserService() : null;
                        if (userService == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
                        }
                        return ((MegacableUserService) userService).createMainProfileAndLogin().map(new Function<T, R>() { // from class: com.dotscreen.portal.PortalApplication.loadConfig.6.2.1
                            @Override // io.reactivex.functions.Function
                            public final Config apply(RightVUser user) {
                                Intrinsics.checkParameterIsNotNull(user, "user");
                                PortalApplication portalApplication = PortalApplication.this;
                                JSONObject configJson2 = configJson;
                                Intrinsics.checkExpressionValueIsNotNull(configJson2, "configJson");
                                return new Config(portalApplication, configJson2, user, false, null, 12, null);
                            }
                        });
                    }
                    if (it.size() != 1) {
                        if (!z) {
                            BOService bOService2 = PortalApplication.this.boService;
                            userService = bOService2 != null ? bOService2.getUserService() : null;
                            if (userService == null) {
                                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
                            }
                            return ((MegacableUserService) userService).createMainProfile().map(new Function<T, R>() { // from class: com.dotscreen.portal.PortalApplication.loadConfig.6.2.4
                                @Override // io.reactivex.functions.Function
                                public final Config apply(String externalId) {
                                    Intrinsics.checkParameterIsNotNull(externalId, "externalId");
                                    Log.v(PortalApplication.this.TAG, "Main profile " + externalId + " was created");
                                    PortalApplication portalApplication = PortalApplication.this;
                                    JSONObject configJson2 = configJson;
                                    Intrinsics.checkExpressionValueIsNotNull(configJson2, "configJson");
                                    return new Config(portalApplication, configJson2, null, false, null, 12, null);
                                }
                            });
                        }
                        PortalApplication portalApplication = PortalApplication.this;
                        JSONObject configJson2 = configJson;
                        Intrinsics.checkExpressionValueIsNotNull(configJson2, "configJson");
                        return Single.just(new Config(portalApplication, configJson2, null, false, null, 12, null));
                    }
                    Sentry.addBreadcrumb("User has only one profile at portal launch");
                    Log.v(PortalApplication.this.TAG, "Only one profile");
                    if (!z) {
                        BOService bOService3 = PortalApplication.this.boService;
                        userService = bOService3 != null ? bOService3.getUserService() : null;
                        if (userService == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
                        }
                        return ((MegacableUserService) userService).createMainProfile().map(new Function<T, R>() { // from class: com.dotscreen.portal.PortalApplication.loadConfig.6.2.2
                            @Override // io.reactivex.functions.Function
                            public final Config apply(String it3) {
                                Intrinsics.checkParameterIsNotNull(it3, "it");
                                Log.v(PortalApplication.this.TAG, "mainProfile was created");
                                PortalApplication portalApplication2 = PortalApplication.this;
                                JSONObject configJson3 = configJson;
                                Intrinsics.checkExpressionValueIsNotNull(configJson3, "configJson");
                                return new Config(portalApplication2, configJson3, null, false, null, 12, null);
                            }
                        });
                    }
                    BOService bOService4 = PortalApplication.this.boService;
                    UserService userService2 = bOService4 != null ? bOService4.getUserService() : null;
                    if (userService2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
                    }
                    return MegacableUserService.login$default((MegacableUserService) userService2, ((Profile) CollectionsKt.first((List) it)).getExternalId(), null, 2, null).map(new Function<T, R>() { // from class: com.dotscreen.portal.PortalApplication.loadConfig.6.2.3
                        @Override // io.reactivex.functions.Function
                        public final Config apply(RightVUser user) {
                            Intrinsics.checkParameterIsNotNull(user, "user");
                            PortalApplication portalApplication2 = PortalApplication.this;
                            JSONObject configJson3 = configJson;
                            Intrinsics.checkExpressionValueIsNotNull(configJson3, "configJson");
                            return new Config(portalApplication2, configJson3, user, false, null, 12, null);
                        }
                    });
                }
            }).retryWhen(new Function<Flowable<Throwable>, Publisher<?>>() { // from class: com.dotscreen.portal.PortalApplication.loadConfig.6.3
                @Override // io.reactivex.functions.Function
                public final Flowable<Long> apply(Flowable<Throwable> errors) {
                    Intrinsics.checkParameterIsNotNull(errors, "errors");
                    final AtomicInteger atomicInteger = new AtomicInteger(0);
                    return errors.flatMapSingle(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.PortalApplication.loadConfig.6.3.1
                        @Override // io.reactivex.functions.Function
                        public final Single<Pair<Boolean, Throwable>> apply(final Throwable error) {
                            Intrinsics.checkParameterIsNotNull(error, "error");
                            Log.e("BootSequenceManager", "error: " + error);
                            Sentry.addBreadcrumb("Error during the boot sequence");
                            Log.v("BootSequenceManager", "connectedToInternet: " + PortalApplication.this.connectedToInternet + " | loginErrorReason: " + PortalApplication.this.getLoginErrorReason() + " | instantConnectedToInternetFlag: " + PortalApplication.this.getInstantConnectedToInternetFlag());
                            Ref.BooleanRef booleanRef = C02886.this.$isSSLError;
                            String message = error.getMessage();
                            booleanRef.element = message != null ? StringsKt.contains((CharSequence) message, (CharSequence) "SSL", true) : false;
                            C02886.this.$isIpConfigured.element = PortalApplication.access$getNetworkStateManager$p(PortalApplication.this).hasInternetCapability();
                            if (!C02886.this.$isSSLError.element && !C02886.this.$isIpConfigured.element && ((PortalApplication.this.connectedToInternet || PortalApplication.this.getInstantConnectedToInternetFlag()) && PortalApplication.this.getLoginErrorReason() == null)) {
                                BootSequenceManager bootSequenceManager = PortalApplication.this.bootSequenceManager;
                                if (bootSequenceManager == null) {
                                    Intrinsics.throwNpe();
                                }
                                return bootSequenceManager.checkServerReachable().map(new Function<T, R>() { // from class: com.dotscreen.portal.PortalApplication.loadConfig.6.3.1.1
                                    @Override // io.reactivex.functions.Function
                                    public final Pair<Boolean, Throwable> apply(Boolean it) {
                                        Intrinsics.checkParameterIsNotNull(it, "it");
                                        return new Pair<>(it, error);
                                    }
                                });
                            }
                            if (!C02886.this.$isIpConfigured.element) {
                                Log.v("BootSequenceManager", "Skipping retries -> No IP");
                                PortalApplication.this.setErrorDuringBootSequence(error);
                                return Single.just(new Pair(false, error));
                            }
                            if (C02886.this.$isSSLError.element) {
                                Log.v("BootSequenceManager", "Skipping retries -> SSL error");
                                PortalApplication.this.setErrorDuringBootSequence(error);
                                return Single.just(new Pair(false, error));
                            }
                            return Single.just(new Pair(false, error));
                        }
                    }).takeWhile(new Predicate<Pair<? extends Boolean, ? extends Throwable>>() { // from class: com.dotscreen.portal.PortalApplication.loadConfig.6.3.2
                        @Override // io.reactivex.functions.Predicate
                        public /* bridge */ /* synthetic */ boolean test(Pair<? extends Boolean, ? extends Throwable> pair) {
                            return test2((Pair<Boolean, ? extends Throwable>) pair);
                        }

                        /* JADX INFO: renamed from: test, reason: avoid collision after fix types in other method */
                        public final boolean test2(Pair<Boolean, ? extends Throwable> it) throws Throwable {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            Log.d("BootSequenceManager", "Retry count: " + atomicInteger);
                            Log.v("BootSequenceManager", "(takeWhile) isIpConfigured: " + C02886.this.$isIpConfigured.element + ", isSSLError: " + C02886.this.$isSSLError.element + ", connectedToInternet: " + PortalApplication.this.connectedToInternet + " | loginErrorReason: " + PortalApplication.this.getLoginErrorReason() + " | instantConnectedToInternetFlag: " + PortalApplication.this.getInstantConnectedToInternetFlag());
                            if (C02886.this.$isIpConfigured.element && !C02886.this.$isSSLError.element && (PortalApplication.this.connectedToInternet || PortalApplication.this.getInstantConnectedToInternetFlag()) && PortalApplication.this.getLoginErrorReason() == null && atomicInteger.getAndIncrement() != 3) {
                                return true;
                            }
                            Throwable second = it.getSecond();
                            Intrinsics.checkExpressionValueIsNotNull(second, "it.second");
                            throw second;
                        }
                    }).flatMap(new Function<T, Publisher<? extends R>>() { // from class: com.dotscreen.portal.PortalApplication.loadConfig.6.3.3
                        @Override // io.reactivex.functions.Function
                        public final Flowable<Long> apply(Pair<Boolean, ? extends Throwable> result) {
                            Intrinsics.checkParameterIsNotNull(result, "result");
                            Log.d("BootSequenceManager", "Server are reachable: " + result.getFirst());
                            if (!result.getFirst().booleanValue()) {
                                BootSequenceManager bootSequenceManager = PortalApplication.this.bootSequenceManager;
                                if (bootSequenceManager == null) {
                                    Intrinsics.throwNpe();
                                }
                                int waitingDelay = bootSequenceManager.getWaitingDelay();
                                Log.d("BootSequenceManager", "delay before retry: " + waitingDelay);
                                return Flowable.timer(waitingDelay, TimeUnit.SECONDS);
                            }
                            Log.d("BootSequenceManager", "Just retry");
                            return Flowable.timer(0L, TimeUnit.SECONDS);
                        }
                    });
                }
            }).onErrorResumeNext(new Function<Throwable, SingleSource<? extends Config>>() { // from class: com.dotscreen.portal.PortalApplication.loadConfig.6.4
                @Override // io.reactivex.functions.Function
                public final Single<Config> apply(Throwable it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Log.e(PortalApplication.this.TAG, "Login failed => " + it, it);
                    PortalApplication.this.setErrorDuringBootSequence(it);
                    PortalApplication portalApplication = PortalApplication.this;
                    JSONObject configJson2 = configJson;
                    Intrinsics.checkExpressionValueIsNotNull(configJson2, "configJson");
                    return Single.just(new Config(portalApplication, configJson2, null, true, PortalApplication.this.getLoginErrorReason()));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initFirebase() {
        FirebaseInstanceId firebaseInstanceId = FirebaseInstanceId.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(firebaseInstanceId, "FirebaseInstanceId.getInstance()");
        firebaseInstanceId.getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() { // from class: com.dotscreen.portal.PortalApplication.initFirebase.1
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task<InstanceIdResult> task) {
                Intrinsics.checkParameterIsNotNull(task, "task");
                if (!task.isSuccessful()) {
                    Log.w("MegaNotificationService", "getInstanceId failed", task.getException());
                    return;
                }
                InstanceIdResult result = task.getResult();
                String token = result != null ? result.getToken() : null;
                Log.d("MegaNotificationService", token);
                BOService bOService = PortalApplication.this.boService;
                if (bOService == null) {
                    Intrinsics.throwNpe();
                }
                UserService userService = bOService.getUserService();
                if (userService == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
                }
                MegacableUserService megacableUserService = (MegacableUserService) userService;
                if (token == null) {
                    Intrinsics.throwNpe();
                }
                megacableUserService.registerOMPToken(token).subscribe(new Consumer<JSONObject>() { // from class: com.dotscreen.portal.PortalApplication.initFirebase.1.1
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(JSONObject jSONObject) {
                        Log.v("MegaNotificationService", "success = " + jSONObject);
                    }
                }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.PortalApplication.initFirebase.1.2
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Throwable th) {
                        Log.e("MegaNotificationService", "error = " + th.getMessage());
                    }
                });
            }
        });
    }

    private final void initTimeshift() {
        BOService bOService = this.boService;
        if (bOService == null) {
            Intrinsics.throwNpe();
        }
        UserService userService = bOService.getUserService();
        if (userService == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
        }
        ((MegacableUserService) userService).getGetTimeshiftService().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<JSONObject>() { // from class: com.dotscreen.portal.PortalApplication.initTimeshift.1
            @Override // io.reactivex.functions.Consumer
            public final void accept(JSONObject jSONObject) {
                if (jSONObject != null) {
                    PortalApplication.this.timeshiftingHelper.parseData(jSONObject);
                }
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.PortalApplication.initTimeshift.2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Log.e(PortalApplication.this.TAG, "Error while loading timeshifting", th);
            }
        });
    }

    private final void initAnalyticsModules() {
        AnalyticsManager analyticsManager = this.analyticsManager;
        if (analyticsManager != null) {
            RightVRequestManager rightVRequestManager = CommonUtils.getRightVRequestManager(this);
            Intrinsics.checkExpressionValueIsNotNull(rightVRequestManager, "CommonUtils.getRightVRequestManager(this)");
            BOService bOService = this.boService;
            UserService userService = bOService != null ? bOService.getUserService() : null;
            if (userService == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
            }
            analyticsManager.registerAnalyticsModule(new ToolboxAnalyticsModule(rightVRequestManager, (MegacableUserService) userService));
        }
        AnalyticsManager analyticsManager2 = this.analyticsManager;
        if (analyticsManager2 != null) {
            PortalApplication portalApplication = this;
            RequestManager requestManager = this.requestManager;
            if (requestManager == null) {
                Intrinsics.throwNpe();
            }
            analyticsManager2.registerAnalyticsModule(new DCAAnalyticsModule(portalApplication, requestManager));
        }
        AnalyticsManager analyticsManager3 = this.analyticsManager;
        if (analyticsManager3 != null) {
            analyticsManager3.registerAnalyticsModule(new CompassVODAnalyticsModule(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleError(Throwable error) {
        LoginError.SUSPEND_ACCOUNT suspend_account;
        if (ErrorManager.INSTANCE.getErrorStatusCode(error) == 411) {
            this.loginErrorReason = new LoginError.GEOBLOKING(error);
            return;
        }
        Throwable cause = error instanceof BackendErrorException ? error : error.getCause();
        if (cause instanceof BackendErrorException) {
            String backendCode = ((BackendErrorException) cause).getBackendCode();
            if (backendCode.equals("INVALID_CREDENTIALS")) {
                suspend_account = new LoginError.NOT_PROVISIONED(error);
            } else if (backendCode.equals("INACTIVE_HOUSEHOLD") || backendCode.equals("SUSPENDED_HOUSEHOLD")) {
                suspend_account = new LoginError.SUSPEND_ACCOUNT(error);
            } else {
                suspend_account = backendCode.equals("LOGIN_NOT_ALLOWED_OUT_OF_MEGACABLE_NETWORK") ? new LoginError.GEOBLOKING(error) : new LoginError.UNKNOWN(error);
            }
        } else {
            suspend_account = null;
        }
        this.loginErrorReason = suspend_account;
    }

    /* JADX INFO: compiled from: PortalApplication.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/dotscreen/portal/PortalApplication$Companion;", "", "()V", "getSingleton", "Lcom/dotscreen/portal/PortalApplicationSingletons;", "context", "Landroid/content/Context;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final PortalApplicationSingletons getSingleton(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Object applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                return (PortalApplicationSingletons) applicationContext;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplicationSingletons");
        }
    }

    public final void initSentry(final Config config) {
        String serialNumber;
        String sentryFilter;
        BOService bOService = this.boService;
        MegacableUserService megacableUserService = (MegacableUserService) (bOService != null ? bOService.getUserService() : null);
        if (megacableUserService == null || (serialNumber = megacableUserService.getSerialNumber()) == null) {
            return;
        }
        if (config == null || (sentryFilter = config.getSentryFilter()) == null) {
            sentryFilter = LocalSearchProvider.NO_LIVE_CONTENTS;
        }
        if (StringsKt.endsWith$default(serialNumber, sentryFilter, false, 2, (Object) null)) {
            io.sentry.protocol.User user = new io.sentry.protocol.User();
            user.setId(Build.getSerial());
            SentryAndroid.init(this, new Sentry.OptionsConfiguration<SentryAndroidOptions>() { // from class: com.dotscreen.portal.PortalApplication.initSentry.1
                @Override // io.sentry.Sentry.OptionsConfiguration
                public final void configure(SentryAndroidOptions options) {
                    Intrinsics.checkParameterIsNotNull(options, "options");
                    options.setDsn("https://ec300f2adbd04c6f888b5a2eb74d6872@o207455.ingest.sentry.io/5269708");
                    options.setBeforeSend(new SentryOptions.BeforeSendCallback() { // from class: com.dotscreen.portal.PortalApplication.initSentry.1.1
                        @Override // io.sentry.SentryOptions.BeforeSendCallback
                        public final SentryEvent execute(SentryEvent event, Object obj) {
                            Intrinsics.checkParameterIsNotNull(event, "event");
                            PortalApplication.this.reportSentryEventToVO(event, obj);
                            Config config2 = config;
                            SentryRateLimitManagement sentryRateLimitManagement = config2 != null ? config2.getSentryRateLimitManagement() : null;
                            if (sentryRateLimitManagement == null) {
                                return event;
                            }
                            BOService bOService2 = PortalApplication.this.boService;
                            UserService userService = bOService2 != null ? bOService2.getUserService() : null;
                            if (userService != null) {
                                return sentryRateLimitManagement.filterEvent(((MegacableUserService) userService).getSerialNumber(), event);
                            }
                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
                        }
                    });
                }
            });
            Sentry.setUser(user);
        }
    }

    public static /* synthetic */ void initDcaLib$default(PortalApplication portalApplication, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        portalApplication.initDcaLib(str);
    }

    public final void initDcaLib(String configUrl) {
        UserService userService;
        BehaviorSubject<UserStatus> status;
        Observable<UserStatus> observableFilter;
        Maybe<UserStatus> maybeFirstElement;
        Maybe<R> maybeFlatMap;
        Maybe maybeSubscribeOn;
        Maybe maybeObserveOn;
        final String appName = getString(com.dotscreen.megacable.portal.R.string.app_name);
        final String url = configUrl != null ? configUrl : CommonUtils.OTA_SERVER_URL;
        Log.v(this.TAG, "initDCA toto");
        final String str = "(-1)";
        if (configUrl != null) {
            Log.v(this.TAG, "initDCA tata");
            BOService bOService = this.boService;
            if (bOService == null || (userService = bOService.getUserService()) == null || (status = userService.getStatus()) == null || (observableFilter = status.filter(new Predicate<UserStatus>() { // from class: com.dotscreen.portal.PortalApplication.initDcaLib.1
                @Override // io.reactivex.functions.Predicate
                public final boolean test(UserStatus it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return it instanceof UserStatus.Authenticated;
                }
            })) == null || (maybeFirstElement = observableFilter.firstElement()) == null || (maybeFlatMap = maybeFirstElement.flatMap((Function) new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.portal.PortalApplication.initDcaLib.2
                @Override // io.reactivex.functions.Function
                public final Maybe<User> apply(UserStatus it) {
                    UserService userService2;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    BOService bOService2 = PortalApplication.this.boService;
                    if (bOService2 == null || (userService2 = bOService2.getUserService()) == null) {
                        return null;
                    }
                    return userService2.getActiveUser();
                }
            })) == 0 || (maybeSubscribeOn = maybeFlatMap.subscribeOn(Schedulers.io())) == null || (maybeObserveOn = maybeSubscribeOn.observeOn(AndroidSchedulers.mainThread())) == null) {
                return;
            }
            maybeObserveOn.subscribe(new Consumer<User>() { // from class: com.dotscreen.portal.PortalApplication.initDcaLib.3
                @Override // io.reactivex.functions.Consumer
                public final void accept(User user) {
                    Log.v(PortalApplication.this.TAG, "initDCA delayed with user: " + user);
                    DCAInitComponent dCAInitComponent = new DCAInitComponent(PortalApplication.this);
                    String appName2 = appName;
                    Intrinsics.checkExpressionValueIsNotNull(appName2, "appName");
                    String str2 = str;
                    String url2 = url;
                    Intrinsics.checkExpressionValueIsNotNull(url2, "url");
                    String id = user.getId();
                    if (user == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVUser");
                    }
                    dCAInitComponent.initDCA(appName2, str2, url2, id, ((RightVUser) user).getCompassId());
                }
            }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.PortalApplication.initDcaLib.4
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                    Log.e(PortalApplication.this.TAG, "Error on initDCA observable", th);
                }
            });
            return;
        }
        DCAInitComponent dCAInitComponent = new DCAInitComponent(this);
        Intrinsics.checkExpressionValueIsNotNull(appName, "appName");
        Intrinsics.checkExpressionValueIsNotNull(url, "url");
        DCAInitComponent.initDCA$default(dCAInitComponent, appName, "(-1)", url, null, null, 24, null);
    }

    @Override // com.dotscreen.tv.common.IForegroudStateProvider
    /* JADX INFO: renamed from: isInForeground, reason: from getter */
    public boolean getPortalIsInForeground() {
        return this.portalIsInForeground;
    }

    public final boolean isScreenOn() {
        Object systemService = getSystemService("display");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.hardware.display.DisplayManager");
        }
        for (Display display : ((DisplayManager) systemService).getDisplays()) {
            if (display.getState() != 1) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: compiled from: PortalApplication.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010\t\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\n\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\u000b\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u001c\u0010\f\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\r\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010\u000e\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\u000f\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0010"}, d2 = {"Lcom/dotscreen/portal/PortalApplication$ActivityLifecycleCallbacks;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "(Lcom/dotscreen/portal/PortalApplication;)V", "onActivityCreated", "", "activity", "Landroid/app/Activity;", "savedInstanceState", "Landroid/os/Bundle;", "onActivityDestroyed", "onActivityPaused", "onActivityResumed", "onActivitySaveInstanceState", "outState", "onActivityStarted", "onActivityStopped", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public final class ActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
        public ActivityLifecycleCallbacks() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            Log.d(PortalApplication.this.TAG, "onActivityPaused(activity: " + activity + ')');
            UpdateManagerKt.notifyApplicationGoesBackground(PortalApplication.this);
            if (PortalApplication.this.getBootComplete() && (!PortalApplication.this.connectedToInternet || !PortalApplication.this.backendAvailable)) {
                PortalApplication portalApplication = PortalApplication.this;
                NetworkStatus value = PortalApplication.access$getNetworkStateManager$p(portalApplication).getAppStatus().getValue();
                if (value == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(value, "networkStateManager.appStatus.value!!");
                portalApplication.updateFloatingStatusView(value);
            }
            PortalApplication.this.portalIsInForeground = false;
            PortalApplication.this.clearFloatingAds();
            PortalApplication.this.clearNativeAds();
            while (PortalApplication.this.visitedAdTrackingURLForCurrentPage.size() > 100) {
                PortalApplication.this.visitedAdTrackingURLForCurrentPage.remove(CollectionsKt.first(PortalApplication.this.visitedAdTrackingURLForCurrentPage));
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            Log.d(PortalApplication.this.TAG, "onActivityResumed(activity: " + activity + ')');
            PortalApplication.this._activeActivity = activity;
            UpdateManagerKt.notifyApplicationGoesForeground(PortalApplication.this);
            PortalApplication.this.portalIsInForeground = true;
            PortalApplication.this.liveTvIsInForeground = false;
            if (PortalApplication.this.appShouldDegradeOnResume) {
                PortalApplication.this.appShouldDegradeOnResume = false;
                PortalApplication.this.refreshHome();
            }
            if (PortalApplication.this.getBootComplete() && (!PortalApplication.this.connectedToInternet || !PortalApplication.this.backendAvailable)) {
                PortalApplication portalApplication = PortalApplication.this;
                NetworkStatus value = PortalApplication.access$getNetworkStateManager$p(portalApplication).getAppStatus().getValue();
                if (value == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(value, "networkStateManager.appStatus.value!!");
                portalApplication.updateFloatingStatusView(value);
            }
            if (PortalApplication.this.appRestartIsPlanned) {
                PortalApplication.this.restartApplicationWithPopupOrPlanForLater();
            }
            if (!PortalApplication.this.getBootComplete() || PortalApplication.this.bootWithoutInternet) {
                return;
            }
            PortalApplication portalApplication2 = PortalApplication.this;
            Activity activity2 = portalApplication2.get_activeActivity();
            if (activity2 == null) {
                Intrinsics.throwNpe();
            }
            String simpleName = activity2.getClass().getSimpleName();
            Intrinsics.checkExpressionValueIsNotNull(simpleName, "activeActivity!!.javaClass.simpleName");
            Activity activity3 = PortalApplication.this.get_activeActivity();
            portalApplication2.fetchAdsForPage(simpleName, activity3 != null ? activity3.hashCode() : 0);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            Log.d(PortalApplication.this.TAG, "onActivityStarted(activity: " + activity + ')');
            PortalApplication.this._activeActivity = activity;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            Log.d(PortalApplication.this.TAG, "onActivityDestroyed(activity: " + activity + ')');
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            Log.d(PortalApplication.this.TAG, "onActivitySaveInstanceState(activity: " + activity + ", outState: " + outState + ')');
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            Log.d(PortalApplication.this.TAG, "onActivityStopped(activity: " + activity + ')');
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            Log.d(PortalApplication.this.TAG, "onActivityCreated(activity: " + activity + ')');
        }
    }

    @Override // com.dotscreen.tv.common.ISmartLibProvider
    public Object getSmartLib() {
        return this.smartLibInstance;
    }

    @Override // com.dotscreen.tv.common.ISmartLibProvider
    public Object getSmartLibCoreEngine() {
        return this.smartLibCoreEngineInstance;
    }

    @Override // com.dotscreen.tv.common.ISmartLibProvider
    public void reinitSmartLibIfNeeded(String message) {
        Function1<String, Unit> reinitSmartLibIfNeeded;
        Config value = getConfig().getValue();
        if (value == null || (reinitSmartLibIfNeeded = value.getReinitSmartLibIfNeeded()) == null) {
            return;
        }
        reinitSmartLibIfNeeded.invoke(message);
    }

    public final void preventNetworkCalls() {
        Log.d(this.TAG, "Prevent network Calls => forceNoConnection = true");
        RequestManager requestManager = this.requestManager;
        if (requestManager == null) {
            Intrinsics.throwNpe();
        }
        requestManager.setForceNoConnection(true);
        this.boService = CommonUtils.getBOServiceForcedNoConnection(this);
    }

    public final void allowNetworkCalls() {
        Log.d(this.TAG, "Allow network Calls => forceNoConnection = false");
        RequestManager requestManager = this.requestManager;
        if (requestManager == null) {
            Intrinsics.throwNpe();
        }
        requestManager.setForceNoConnection(false);
        this.boService = CommonUtils.getBOService(this);
    }

    public final void toggleNetworkCalls() {
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("toggleNetworkCalls(enabled=");
        RequestManager requestManager = this.requestManager;
        if (requestManager == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVRequestManager");
        }
        sb.append(((RightVRequestManager) requestManager).getMockRightv());
        sb.append(')');
        Log.e(str, sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("toggleNetworkCalls(enabled=");
        RequestManager requestManager2 = this.requestManager;
        if (requestManager2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVRequestManager");
        }
        sb2.append(((RightVRequestManager) requestManager2).getMockRightv());
        sb2.append(')');
        showToastMessage$default(this, sb2.toString(), 0, 2, (Object) null);
        RequestManager requestManager3 = this.requestManager;
        if (requestManager3 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVRequestManager");
        }
        ((RightVRequestManager) requestManager3).toggleRightVBackendState();
        RequestManager requestManager4 = this.requestManager;
        if (requestManager4 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVRequestManager");
        }
        ((RightVRequestManager) requestManager4).toggleCompassBackendState();
    }

    @Override // com.dotscreen.tv.common.IUserSuspendListener
    public void onUserSuspend() {
        preventNetworkCalls();
    }

    @Override // com.dotscreen.tv.common.IMediaPlayerStateProvider
    public boolean isActivePlaybackSession() {
        return this.activeOnDemandPlaybackSession;
    }

    @Override // com.dotscreen.portal.PortalApplicationSingletons
    public com.dotscreen.tv.common.utils.Device getCurrentDevice() {
        com.dotscreen.tv.common.utils.Device device = this.device;
        if (device == null) {
            Intrinsics.throwUninitializedPropertyAccessException(Device.TYPE);
        }
        return device;
    }

    @Override // com.dotscreen.portal.PortalApplicationSingletons
    public void fetchCurrentDeviceInfo() {
        OreganDevice oreganDevice;
        int currentPlatform = CommonUtils.getCurrentPlatform();
        if (currentPlatform == 1) {
            oreganDevice = new OreganDevice(this);
        } else if (currentPlatform == 2) {
            oreganDevice = new TechnicolorDevice(this);
        } else {
            oreganDevice = new UnknownDevice();
        }
        this.device = oreganDevice;
    }

    @Override // android.app.Application, android.content.ComponentCallbacks2
    public void onTrimMemory(int level) {
        Log.i(this.TAG, "onTrimMemory: " + level);
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        DSLog dSLog = DSLog.INSTANCE;
        Object systemService = getSystemService("activity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.app.ActivityManager");
        }
        dropBreadcrumb(TAG, dSLog.printCurrentRAMUsage((ActivityManager) systemService));
        if (level == 10 || level == 15 || level == 80) {
            SentryEvent sentryEvent = new SentryEvent();
            Message message = new Message();
            message.setMessage("MEMORY : onTriMemory event received");
            sentryEvent.setMessage(message);
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("memory", String.valueOf(level));
            Config value = getConfig().getValue();
            linkedHashMap.put("freeMemory", String.valueOf(value != null ? value.getShouldClearMemory() : false));
            sentryEvent.setTags(linkedHashMap);
            Sentry.captureEvent(sentryEvent, SentryReportHint.Memory);
            Config value2 = getConfig().getValue();
            if (value2 != null && value2.getShouldClearMemory() && level == 80) {
                Glide.get(getApplicationContext()).clearMemory();
                BOService bOService = this.boService;
                LiveTVService liveTVService = bOService != null ? bOService.getLiveTVService() : null;
                if (liveTVService == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableLiveTVService");
                }
                ((MegacableLiveTVService) liveTVService).cancelLineupCache(false);
                return;
            }
            super.onTrimMemory(level);
            return;
        }
        super.onTrimMemory(level);
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.PortalApplication$startFeaturesConfigReloadJob$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PortalApplication.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.dotscreen.portal.PortalApplication$startFeaturesConfigReloadJob$1", f = "PortalApplication.kt", i = {0}, l = {2702}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    static final class C03021 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;
        private CoroutineScope p$;

        C03021(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03021 c03021 = PortalApplication.this.new C03021(completion);
            c03021.p$ = (CoroutineScope) obj;
            return c03021;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03021) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:11:0x0029  */
        /* JADX WARN: Code duplicated, block: B:13:0x003b A[RETURN] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x0039 -> B:14:0x003c). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                r6 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r6.label
                r2 = 1
                if (r1 == 0) goto L1c
                if (r1 != r2) goto L14
                java.lang.Object r1 = r6.L$0
                kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                kotlin.ResultKt.throwOnFailure(r7)
                r7 = r6
                goto L3c
            L14:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r0)
                throw r7
            L1c:
                kotlin.ResultKt.throwOnFailure(r7)
                kotlinx.coroutines.CoroutineScope r7 = r6.p$
                r1 = r7
                r7 = r6
            L23:
                boolean r3 = kotlinx.coroutines.CoroutineScopeKt.isActive(r1)
                if (r3 == 0) goto L42
                java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MINUTES
                r4 = 10
                long r3 = r3.toMillis(r4)
                r7.L$0 = r1
                r7.label = r2
                java.lang.Object r3 = kotlinx.coroutines.DelayKt.delay(r3, r7)
                if (r3 != r0) goto L3c
                return r0
            L3c:
                com.dotscreen.portal.PortalApplication r3 = com.dotscreen.portal.PortalApplication.this
                com.dotscreen.portal.PortalApplication.access$reloadFeaturesConfig(r3)
                goto L23
            L42:
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.dotscreen.portal.PortalApplication.C03021.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    private final void startFeaturesConfigReloadJob() {
        Job job = this.featuresConfigReloadJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.featuresConfigReloadJob = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C03021(null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reloadFeaturesConfig() {
        Log.v(this.TAG, "reloadFeaturesConfig()");
        RequestManager requestManager = this.requestManager;
        if (requestManager == null) {
            Intrinsics.throwNpe();
        }
        RequestManager.getJSONObject$default(requestManager, 0, new URL(CommonUtils.CONFIG_URL + "/features_tv.json"), null, 4, null).onErrorResumeNext(new Function<Throwable, SingleSource<? extends JSONObject>>() { // from class: com.dotscreen.portal.PortalApplication.reloadFeaturesConfig.1
            @Override // io.reactivex.functions.Function
            public final Single<JSONObject> apply(Throwable error) {
                Intrinsics.checkParameterIsNotNull(error, "error");
                error.printStackTrace();
                return Single.create(new SingleOnSubscribe<T>() { // from class: com.dotscreen.portal.PortalApplication.reloadFeaturesConfig.1.1
                    @Override // io.reactivex.SingleOnSubscribe
                    public final void subscribe(SingleEmitter<JSONObject> observer) throws IOException {
                        Intrinsics.checkParameterIsNotNull(observer, "observer");
                        InputStream inputStreamOpen = PortalApplication.this.getAssets().open("features.json");
                        byte[] bArr = new byte[inputStreamOpen.available()];
                        inputStreamOpen.read(bArr);
                        observer.onSuccess(new JSONObject(new String(bArr, Charsets.UTF_8)));
                    }
                });
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<JSONObject>() { // from class: com.dotscreen.portal.PortalApplication.reloadFeaturesConfig.2
            @Override // io.reactivex.functions.Consumer
            public final void accept(JSONObject json) throws JSONException {
                FeaturesManager featuresManager = PortalApplication.this.featuresManager;
                Intrinsics.checkExpressionValueIsNotNull(json, "json");
                featuresManager.loadConfig(json);
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.PortalApplication.reloadFeaturesConfig.3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
            }
        });
    }

    @Override // com.viaccessorca.analytics.fma.VoFmaControllerListener
    public void onInitCompleted() {
        Log.v(this.TAG, "VO FMA -> initialization completed");
    }

    @Override // com.viaccessorca.analytics.fma.VoFmaControllerListener
    public void onInitFailed() {
        Log.v(this.TAG, "VO FMA -> initialization failed");
    }

    @Override // com.viaccessorca.analytics.fma.VoFmaControllerListener
    public void onConfigurationReloaded() {
        Log.v(this.TAG, "VO FMA -> config reloaded");
    }

    @Override // com.viaccessorca.analytics.fma.VoFmaControllerListener
    public void onConfigurationReloadedFailed() {
        Log.v(this.TAG, "VO FMA -> config reload failed");
    }
}
