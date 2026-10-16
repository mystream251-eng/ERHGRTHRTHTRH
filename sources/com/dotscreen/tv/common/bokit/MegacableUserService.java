package com.dotscreen.tv.common.bokit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.android.tv.common.BuildConfig;
import com.android.volley.NoConnectionError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.dotscreen.bokit.BackendErrorException;
import com.dotscreen.bokit.model.ArtworkFormat;
import com.dotscreen.bokit.model.BrowsableItem;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.Content;
import com.dotscreen.bokit.model.Episode;
import com.dotscreen.bokit.model.ItemExternalIdScheme;
import com.dotscreen.bokit.model.Lineup;
import com.dotscreen.bokit.model.LiveStream;
import com.dotscreen.bokit.model.Media;
import com.dotscreen.bokit.model.MediaMetadata;
import com.dotscreen.bokit.model.Movie;
import com.dotscreen.bokit.model.Offer;
import com.dotscreen.bokit.model.OfferType;
import com.dotscreen.bokit.model.ParentalRating;
import com.dotscreen.bokit.model.PlaybackInfo;
import com.dotscreen.bokit.model.PlaybackRight;
import com.dotscreen.bokit.model.PurchasableItem;
import com.dotscreen.bokit.model.StaticArtwork;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.model.TVProgram;
import com.dotscreen.bokit.model.User;
import com.dotscreen.bokit.services.BOService;
import com.dotscreen.bokit.services.LiveTVService;
import com.dotscreen.bokit.services.PincodeType;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.bokit.services.UserStatus;
import com.dotscreen.farkit.network.BackendReachability;
import com.dotscreen.farkit.network.NetworkTools;
import com.dotscreen.farkit.network.RequestManager;
import com.dotscreen.farkit.rx.CachesKt;
import com.dotscreen.farkit.tools.OptionalCompat;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModule;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.dotscreen.tv.common.search.LocalSearchProvider;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.dotscreen.tv.common.utils.LastWatchedChannelsHelper;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.internal.ImagesContract;
import com.viaccessorca.voplayer.VOPlaybackSessionReport;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: MegacableUserService.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Ô\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\b\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\b\u0007*\u0002'0\u0018\u00002\u00020\u0001:\u0002â\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0015\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020FH\u0000¢\u0006\u0002\bGJG\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00160\r2\u0006\u0010I\u001a\u00020\n2\b\b\u0002\u0010J\u001a\u00020K2\n\b\u0002\u0010L\u001a\u0004\u0018\u00010K2\n\b\u0002\u0010M\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010N\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010OJ\u001d\u0010P\u001a\u00020D2\u0006\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020\nH\u0000¢\u0006\u0002\bTJ\f\u0010U\u001a\b\u0012\u0004\u0012\u00020V0\rJ\u0014\u0010W\u001a\b\u0012\u0004\u0012\u00020Y0X2\u0006\u0010Z\u001a\u00020\nJ\u0014\u0010[\u001a\b\u0012\u0004\u0012\u00020\\0\u001f2\u0006\u0010]\u001a\u00020%J\b\u0010^\u001a\u00020_H\u0002J\u0006\u0010`\u001a\u00020_J\f\u0010a\u001a\b\u0012\u0004\u0012\u00020\n0\rJ(\u0010b\u001a\b\u0012\u0004\u0012\u00020%0\r2\b\u0010Q\u001a\u0004\u0018\u00010c2\u0006\u0010d\u001a\u00020e2\u0006\u0010N\u001a\u00020\nH\u0016J\f\u0010f\u001a\b\u0012\u0004\u0012\u00020\n0\rJ\u0014\u0010g\u001a\b\u0012\u0004\u0012\u00020\n0\r2\u0006\u0010d\u001a\u00020\nJ\u000e\u0010h\u001a\u00020D2\u0006\u0010i\u001a\u00020\\J\f\u0010j\u001a\b\u0012\u0004\u0012\u00020\n0\rJ\f\u0010k\u001a\b\u0012\u0004\u0012\u00020R0\rJ\u0014\u0010l\u001a\b\u0012\u0004\u0012\u00020\u00160\r2\u0006\u0010m\u001a\u00020\nJ\f\u0010n\u001a\b\u0012\u0004\u0012\u00020\u00160\rJ\u0013\u0010o\u001a\b\u0012\u0004\u0012\u00020\n0\u001fH\u0000¢\u0006\u0002\bpJ\u0013\u0010q\u001a\b\u0012\u0004\u0012\u00020F0\u001fH\u0000¢\u0006\u0002\brJ\u0013\u0010s\u001a\b\u0012\u0004\u0012\u00020t0\u001fH\u0000¢\u0006\u0002\buJ\u0013\u0010v\u001a\b\u0012\u0004\u0012\u00020\n0\u001fH\u0000¢\u0006\u0002\bwJ\f\u0010x\u001a\b\u0012\u0004\u0012\u00020\u00180\rJ\u001c\u0010y\u001a\b\u0012\u0004\u0012\u00020\u00110\r2\f\u0010z\u001a\b\u0012\u0004\u0012\u00020\u00110\u001aH\u0002J\u0014\u0010{\u001a\b\u0012\u0004\u0012\u00020K0\r2\u0006\u0010i\u001a\u00020\\J1\u0010|\u001a\b\u0012\u0004\u0012\u00020\n0\r2\u0006\u0010}\u001a\u00020\n2\u0006\u0010~\u001a\u00020\n2\r\u0010\u007f\u001a\t\u0012\u0004\u0012\u00020\n0\u0080\u0001¢\u0006\u0003\u0010\u0081\u0001J\r\u0010\u0082\u0001\u001a\b\u0012\u0004\u0012\u00020\n0\rJ\u0016\u0010\u0083\u0001\u001a\t\u0012\u0005\u0012\u00030\u0084\u00010X2\u0006\u0010i\u001a\u00020\\J\u000f\u0010\u0085\u0001\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\rJ\r\u0010\u0086\u0001\u001a\b\u0012\u0004\u0012\u00020\n0\rJ\r\u0010\u0087\u0001\u001a\b\u0012\u0004\u0012\u00020\u00180\rJ\r\u0010\u0088\u0001\u001a\b\u0012\u0004\u0012\u00020 0\u001fJ\u000e\u0010\u0089\u0001\u001a\t\u0012\u0005\u0012\u00030\u008a\u00010\u001fJ\u0013\u0010\u008b\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u001a0\rJ\u000f\u0010\u008c\u0001\u001a\b\u0012\u0004\u0012\u00020?0\rH\u0002J\t\u0010\u008d\u0001\u001a\u00020?H\u0002J\u0012\u0010\u008e\u0001\u001a\u00020%2\u0007\u0010\u008f\u0001\u001a\u00020\u0011H\u0002J\u0012\u0010\u0090\u0001\u001a\u00020%2\u0007\u0010\u0091\u0001\u001a\u00020KH\u0016J\u0016\u0010\u0092\u0001\u001a\t\u0012\u0005\u0012\u00030\u0093\u00010X2\u0006\u0010Q\u001a\u00020RJ\u0016\u0010\u0094\u0001\u001a\t\u0012\u0005\u0012\u00030\u0093\u00010\u001f2\u0006\u0010Q\u001a\u00020RJ\u0018\u0010\u0095\u0001\u001a\b\u0012\u0004\u0012\u00020\u00160\r2\t\b\u0002\u0010\u0096\u0001\u001a\u00020%J'\u0010\u0095\u0001\u001a\b\u0012\u0004\u0012\u00020R0\r2\u0007\u0010\u0097\u0001\u001a\u00020\n2\u000f\b\u0002\u0010\u0098\u0001\u001a\b\u0012\u0004\u0012\u00020\n0\rJ*\u0010\u0095\u0001\u001a\b\u0012\u0004\u0012\u00020\u00160\r2\u0007\u0010\u0099\u0001\u001a\u00020\n2\u0007\u0010\u009a\u0001\u001a\u00020\n2\u0007\u0010\u009b\u0001\u001a\u00020%H\u0002J\r\u0010\u009c\u0001\u001a\b\u0012\u0004\u0012\u00020\u00160\rJ(\u0010\u009d\u0001\u001a\b\u0012\u0004\u0012\u00020\u00160\r2\u0007\u0010\u0099\u0001\u001a\u00020\n2\u0007\u0010\u009a\u0001\u001a\u00020\n2\u0007\u0010\u009b\u0001\u001a\u00020%J2\u0010\u009e\u0001\u001a\b\u0012\u0004\u0012\u00020R0\r2\u0007\u0010\u009f\u0001\u001a\u00020\n2\u0007\u0010\u0097\u0001\u001a\u00020\n2\u000f\b\u0002\u0010\u0098\u0001\u001a\b\u0012\u0004\u0012\u00020\n0\rH\u0002J\u000f\u0010 \u0001\u001a\b\u0012\u0004\u0012\u00020\u00160\rH\u0002J\r\u0010¡\u0001\u001a\b\u0012\u0004\u0012\u00020\u00110\u001fJ\u001e\u0010¢\u0001\u001a\b\u0012\u0004\u0012\u00020\\0\u001f2\u0007\u0010£\u0001\u001a\u00020\n2\u0006\u0010]\u001a\u00020%J\u0007\u0010¤\u0001\u001a\u00020DJ\u000f\u0010¥\u0001\u001a\u00020D2\u0006\u0010i\u001a\u00020\\J\u001d\u0010¦\u0001\u001a\u00020D2\b\u0010Q\u001a\u0004\u0018\u00010c2\b\u0010§\u0001\u001a\u00030¨\u0001H\u0016J\u001a\u0010©\u0001\u001a\t\u0012\u0005\u0012\u00030ª\u00010\u001f2\b\u0010Q\u001a\u0004\u0018\u00010cH\u0016J\u0013\u0010«\u0001\u001a\u00030¬\u00012\u0007\u0010\u00ad\u0001\u001a\u00020\u0016H\u0002J\u0016\u0010®\u0001\u001a\u0005\u0018\u00010¯\u00012\b\u0010°\u0001\u001a\u00030±\u0001H\u0002J%\u0010²\u0001\u001a\u00030³\u00012\u0007\u0010\u00ad\u0001\u001a\u00020\u00162\u0012\b\u0002\u0010´\u0001\u001a\u000b\u0012\u0005\u0012\u00030\u0093\u0001\u0018\u00010\u001aJ\u0018\u0010µ\u0001\u001a\u00020t2\u0007\u0010\u00ad\u0001\u001a\u00020\u0016H\u0000¢\u0006\u0003\b¶\u0001J6\u0010·\u0001\u001a\b\u0012\u0004\u0012\u00020Y0X2\b\u0010Q\u001a\u0004\u0018\u00010c2\u0006\u0010i\u001a\u00020\\2\n\u0010¸\u0001\u001a\u0005\u0018\u00010¹\u00012\u0007\u0010º\u0001\u001a\u00020%H\u0016J0\u0010¸\u0001\u001a\t\u0012\u0005\u0012\u00030¹\u00010X2\b\u0010Q\u001a\u0004\u0018\u00010c2\b\u0010»\u0001\u001a\u00030ª\u00012\n\u0010§\u0001\u001a\u0005\u0018\u00010¨\u0001H\u0016J\u0017\u0010z\u001a\b\u0012\u0004\u0012\u00020\u00110\u001f2\t\b\u0002\u0010\u0096\u0001\u001a\u00020%J%\u0010¼\u0001\u001a\b\u0012\u0004\u0012\u00020t0\u001f2\b\u0010Q\u001a\u0004\u0018\u00010c2\n\u0010½\u0001\u001a\u0005\u0018\u00010¾\u0001H\u0016J\u0016\u0010¿\u0001\u001a\b\u0012\u0004\u0012\u00020\u00160\r2\u0007\u0010À\u0001\u001a\u00020\nJ\u0011\u0010Á\u0001\u001a\u00020D2\b\u0010Â\u0001\u001a\u00030Ã\u0001J\u0019\u0010Á\u0001\u001a\u00020D2\u0007\u0010Ä\u0001\u001a\u00020\n2\u0007\u0010Å\u0001\u001a\u00020\nJ\u0016\u0010Æ\u0001\u001a\b\u0012\u0004\u0012\u00020\u00160\r2\u0007\u0010\u0099\u0001\u001a\u00020\nJ\u0017\u0010Ç\u0001\u001a\u00020D2\u0006\u0010E\u001a\u00020FH\u0000¢\u0006\u0003\bÈ\u0001J\u0015\u0010É\u0001\u001a\b\u0012\u0004\u0012\u00020\u00110\r2\u0006\u0010N\u001a\u00020\nJ\u0017\u0010Ê\u0001\u001a\u00020D2\u0006\u0010S\u001a\u00020\nH\u0000¢\u0006\u0003\bË\u0001J$\u0010Ì\u0001\u001a\u00020D2\b\u0010Q\u001a\u0004\u0018\u00010c2\u0006\u0010i\u001a\u00020\\2\u0007\u0010Í\u0001\u001a\u00020?H\u0016J\u0017\u0010Î\u0001\u001a\t\u0012\u0005\u0012\u00030¬\u00010\u001f2\u0007\u0010Ï\u0001\u001a\u00020\nJ\u0019\u0010Ð\u0001\u001a\u000b\u0012\u0007\u0012\u0005\u0018\u00010Ñ\u00010\r2\u0007\u0010Ò\u0001\u001a\u00020\nJ\u000e\u0010Ó\u0001\u001a\t\u0012\u0005\u0012\u00030Ô\u00010\u001fJ\u0015\u0010Õ\u0001\u001a\b\u0012\u0004\u0012\u00020\u00160\r2\u0006\u00106\u001a\u00020\nJ'\u0010Ö\u0001\u001a\b\u0012\u0004\u0012\u00020\u00160\r2\u0007\u0010×\u0001\u001a\u00020\n2\u000f\b\u0002\u0010Ø\u0001\u001a\b\u0012\u0004\u0012\u00020\n0\rJU\u0010Ù\u0001\u001a\b\u0012\u0004\u0012\u00020\u00110\r2\n\b\u0002\u0010I\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010J\u001a\u0004\u0018\u00010K2\n\b\u0002\u0010L\u001a\u0004\u0018\u00010K2\u000f\b\u0002\u0010Ø\u0001\u001a\b\u0012\u0004\u0012\u00020\n0\r2\u000b\b\u0002\u0010×\u0001\u001a\u0004\u0018\u00010\n¢\u0006\u0003\u0010Ú\u0001J%\u0010Ù\u0001\u001a\b\u0012\u0004\u0012\u00020\u00160\r2\u0014\u0010Û\u0001\u001a\u000f\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0Ü\u0001H\u0002J'\u0010Ý\u0001\u001a\b\u0012\u0004\u0012\u00020\u00160\r2\u0007\u0010×\u0001\u001a\u00020\n2\u000f\b\u0002\u0010Ø\u0001\u001a\b\u0012\u0004\u0012\u00020\n0\rJ'\u0010Þ\u0001\u001a\b\u0012\u0004\u0012\u00020\u00160\r2\u0007\u0010×\u0001\u001a\u00020\n2\u000f\b\u0002\u0010Ø\u0001\u001a\b\u0012\u0004\u0012\u00020\n0\rJ\u0016\u0010ß\u0001\u001a\b\u0012\u0004\u0012\u00020\u00160\r2\u0007\u0010à\u0001\u001a\u00020\nJ\r\u0010á\u0001\u001a\b\u0012\u0004\u0012\u00020\n0\rR\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\r8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0012\u0010\u000fR\u001c\u0010\u0015\u001a\u0010\u0012\f\u0012\n \u000b*\u0004\u0018\u00010\u00160\u00160\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00160\r¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u000fR\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u001c\u0010#\u001a\u0010\u0012\f\u0012\n \u000b*\u0004\u0018\u00010%0%0$X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0004\n\u0002\u0010(R\u001b\u0010)\u001a\u00020\n8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b,\u0010\u0014\u001a\u0004\b*\u0010+R\u0010\u0010-\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010.\u001a\u0010\u0012\f\u0012\n \u000b*\u0004\u0018\u00010%0%0$X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010/\u001a\u000200X\u0082\u0004¢\u0006\u0004\n\u0002\u00101R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u00103\u001a\u00020%2\u0006\u00102\u001a\u00020%@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u0011\u00106\u001a\u00020\n8G¢\u0006\u0006\u001a\u0004\b7\u0010+R\u0017\u00108\u001a\b\u0012\u0004\u0012\u0002090\u001f¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\"R!\u0010;\u001a\b\u0012\u0004\u0012\u00020\n0\r8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b=\u0010\u0014\u001a\u0004\b<\u0010\u000fR\u0012\u0010>\u001a\u0004\u0018\u00010?X\u0082\u000e¢\u0006\u0004\n\u0002\u0010@R\u0014\u0010A\u001a\b\u0012\u0004\u0012\u00020B0\u001fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006ã\u0001"}, d2 = {"Lcom/dotscreen/tv/common/bokit/MegacableUserService;", "Lcom/dotscreen/bokit/services/UserService;", "context", "Landroid/content/Context;", "boService", "Lcom/dotscreen/bokit/services/BOService;", "requestManager", "Lcom/dotscreen/tv/common/bokit/RightVRequestManager;", "(Landroid/content/Context;Lcom/dotscreen/bokit/services/BOService;Lcom/dotscreen/tv/common/bokit/RightVRequestManager;)V", "TAG", "", "kotlin.jvm.PlatformType", "accountId", "Lio/reactivex/Single;", "getAccountId", "()Lio/reactivex/Single;", "getCurrentProfile", "Lcom/dotscreen/tv/common/bokit/Profile;", "getGetCurrentProfile", "getCurrentProfile$delegate", "Lkotlin/Lazy;", "getHouseHold", "Lorg/json/JSONObject;", "getMemberHold", "Lorg/json/JSONArray;", "getParentalRatingList", "", "Lcom/dotscreen/tv/common/bokit/RightVParentalRating;", "getTimeshiftService", "getGetTimeshiftService", "getUserRightVPackages", "Lio/reactivex/Observable;", "Lcom/dotscreen/tv/common/bokit/RightVPackage;", "getGetUserRightVPackages", "()Lio/reactivex/Observable;", "householdCacheCanceler", "Lio/reactivex/subjects/PublishSubject;", "", "householdChangedReceiver", "com/dotscreen/tv/common/bokit/MegacableUserService$householdChangedReceiver$1", "Lcom/dotscreen/tv/common/bokit/MegacableUserService$householdChangedReceiver$1;", "macAddress", "getMacAddress", "()Ljava/lang/String;", "macAddress$delegate", "mobileSerialNumber", "profileCacheCanceler", "profileChangedReceiver", "com/dotscreen/tv/common/bokit/MegacableUserService$profileChangedReceiver$1", "Lcom/dotscreen/tv/common/bokit/MegacableUserService$profileChangedReceiver$1;", "<set-?>", "rightVLogged", "getRightVLogged$common_prodRelease", "()Z", "serialNumber", "getSerialNumber", "serviceSubscriptionsInfo", "Lcom/dotscreen/tv/common/bokit/RightVServiceSubscriptionInfo;", "getServiceSubscriptionsInfo", "toolboxToken", "getToolboxToken", "toolboxToken$delegate", "urlSessionId", "", "Ljava/lang/Long;", "videoTicketList", "Lcom/dotscreen/tv/common/bokit/RightVVideoTicket;", "addChannelToFavorites", "Lio/reactivex/Completable;", "channel", "Lcom/dotscreen/tv/common/bokit/RightVChannel;", "addChannelToFavorites$common_prodRelease", "addProfile", "name", "parentalLevel", "", "avatar", "externalId", "pinCode", "(Ljava/lang/String;ILjava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)Lio/reactivex/Single;", "addVodToFavorites", ToolboxAnalyticsModuleKt.KEY_USER, "Lcom/dotscreen/tv/common/bokit/RightVUser;", "contentId", "addVodToFavorites$common_prodRelease", "backendIsReachable", "Lcom/dotscreen/farkit/network/BackendReachability;", "basicLivePlaybackInfo", "Lio/reactivex/Maybe;", "Lcom/dotscreen/bokit/model/PlaybackInfo;", "channelExternalId", "bookmarks", "Lcom/dotscreen/bokit/model/Media;", "adultBrowsing", "cancelHouseholdCache", "", "cancelProfileCache", "checkCompassStatus", "checkPincode", "Lcom/dotscreen/bokit/model/User;", "type", "Lcom/dotscreen/bokit/services/PincodeType;", "checkRighTVStatus", "checkUIOptimizerStatus", "closeVODPlaybackSession", "media", "createMainProfile", "createMainProfileAndLogin", "deleteOtherProfile", ToolboxAnalyticsModuleKt.EVENT_PROFILE_ID, "deleteProfile", "favoritesChannelExternalIds", "favoritesChannelExternalIds$common_prodRelease", "favoritesChannels", "favoritesChannels$common_prodRelease", "favoritesVod", "Lcom/dotscreen/bokit/model/BrowsableItem;", "favoritesVod$common_prodRelease", "favoritesVodExternalIds", "favoritesVodExternalIds$common_prodRelease", "getBouquetList", "getMainProfile", "profiles", "getMediaPosition", "getNetflixPADIToken", "pdi", "esn", "intents", "", "(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lio/reactivex/Single;", "getNetflixPDI", "getNextEpisode", "Lcom/dotscreen/bokit/model/Episode;", "getPinCode", "getReminderString", "getReminders", "getRightVPackages", "getRightVServices", "Lcom/dotscreen/tv/common/bokit/RightVService;", "getSubscribedChannel", "getUrlSessionId", "initializeUrlSessionId", "isMainProfile", Scopes.PROFILE, "isParentalRatingLevelAdult", "parentalRatingLevel", "lastWatchedChannel", "Lcom/dotscreen/bokit/model/Channel;", "lastWatchedChannels", "login", "noCache", "profileExternalId", "pinCodeObs", "username", "password", "includeSerialNumber", "loginAnonymous", "loginOTT", "loginProfile", "userExternalId", "loginToolbox", "mainProfile", "nextEpisode", "serieId", "notifyApplicationsUpdated", "openVODPlaybackSession", "orderOffer", "offer", "Lcom/dotscreen/bokit/model/Offer;", "orderedItems", "Lcom/dotscreen/bokit/model/PurchasableItem;", "parseContentBookmark", "Lcom/dotscreen/bokit/model/Content;", "json", "parseNetflixPDIError", "Lcom/dotscreen/tv/common/bokit/MegacableUserService$NetflixPDIError;", "error", "", "parseReminderToTVAiring", "Lcom/dotscreen/bokit/model/TVAiring;", "channels", "parseVOD", "parseVOD$common_prodRelease", "playbackInfo", "playbackRight", "Lcom/dotscreen/bokit/model/PlaybackRight;", ToolboxAnalyticsModuleKt.EVENT_RESUME, "purchasable", "recommendations", "program", "Lcom/dotscreen/bokit/model/TVProgram;", "registerOMPToken", "token", "registerSearchEvent", "searchMedia", "Lcom/dotscreen/tv/common/bokit/SearchableContent;", "entityType", "searchResultId", "registerTerminal", "removeChannelFromFavorites", "removeChannelFromFavorites$common_prodRelease", "removeProfilePinCode", "removeVodFromFavorites", "removeVodFromFavorites$common_prodRelease", "saveMediaPosition", "position", "searchSuggestion", TvContractCompat.PARAM_INPUT, "sendSubscribeRequest", "Lcom/dotscreen/tv/common/bokit/SubscriptionResult;", "packageId", "terminals", "Lcom/dotscreen/tv/common/bokit/Terminal;", "unregisterTerminal", "updateParentalPinCode", "newPinCode", "currentPinCodeObs", "updateProfile", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Lio/reactivex/Single;Ljava/lang/String;)Lio/reactivex/Single;", "params", "", "updateProfilePinCode", "updatePurchasePinCode", "updateReminder", NotificationCompat.CATEGORY_REMINDER, "verifyVersion", "NetflixPDIError", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class MegacableUserService extends UserService {
    private final String TAG;
    private final Single<String> accountId;
    private final Context context;

    /* JADX INFO: renamed from: getCurrentProfile$delegate, reason: from kotlin metadata */
    private final Lazy getCurrentProfile;
    private final Single<JSONObject> getHouseHold;
    private final Single<JSONArray> getMemberHold;
    private final Single<List<RightVParentalRating>> getParentalRatingList;
    private final Single<JSONObject> getTimeshiftService;
    private final Observable<RightVPackage> getUserRightVPackages;
    private final PublishSubject<Boolean> householdCacheCanceler;
    private final MegacableUserService$householdChangedReceiver$1 householdChangedReceiver;

    /* JADX INFO: renamed from: macAddress$delegate, reason: from kotlin metadata */
    private final Lazy macAddress;
    private String mobileSerialNumber;
    private final PublishSubject<Boolean> profileCacheCanceler;
    private final MegacableUserService$profileChangedReceiver$1 profileChangedReceiver;
    private final RightVRequestManager requestManager;
    private boolean rightVLogged;
    private final Observable<RightVServiceSubscriptionInfo> serviceSubscriptionsInfo;

    /* JADX INFO: renamed from: toolboxToken$delegate, reason: from kotlin metadata */
    private final Lazy toolboxToken;
    private Long urlSessionId;
    private final Observable<RightVVideoTicket> videoTicketList;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;

        static {
            int[] iArr = new int[OfferType.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[OfferType.Transactional.ordinal()] = 1;
            $EnumSwitchMapping$0[OfferType.Subscription.ordinal()] = 2;
            $EnumSwitchMapping$0[OfferType.CatchupTV.ordinal()] = 3;
            int[] iArr2 = new int[OfferType.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[OfferType.Subscription.ordinal()] = 1;
            $EnumSwitchMapping$1[OfferType.Transactional.ordinal()] = 2;
            int[] iArr3 = new int[OfferType.values().length];
            $EnumSwitchMapping$2 = iArr3;
            iArr3[OfferType.CatchupTV.ordinal()] = 1;
            int[] iArr4 = new int[OfferType.values().length];
            $EnumSwitchMapping$3 = iArr4;
            iArr4[OfferType.Transactional.ordinal()] = 1;
            $EnumSwitchMapping$3[OfferType.Subscription.ordinal()] = 2;
            int[] iArr5 = new int[PincodeType.values().length];
            $EnumSwitchMapping$4 = iArr5;
            iArr5[PincodeType.Parental.ordinal()] = 1;
            $EnumSwitchMapping$4[PincodeType.Purchase.ordinal()] = 2;
            $EnumSwitchMapping$4[PincodeType.General.ordinal()] = 3;
        }
    }

    public final Single<Profile> getGetCurrentProfile() {
        return (Single) this.getCurrentProfile.getValue();
    }

    public final String getMacAddress() {
        return (String) this.macAddress.getValue();
    }

    public final Single<String> getToolboxToken() {
        return (Single) this.toolboxToken.getValue();
    }

    @Override // com.dotscreen.bokit.services.UserService
    public boolean isParentalRatingLevelAdult(int parentalRatingLevel) {
        return parentalRatingLevel >= 5;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r8v8, types: [com.dotscreen.tv.common.bokit.MegacableUserService$profileChangedReceiver$1] */
    /* JADX WARN: Type inference failed for: r8v9, types: [com.dotscreen.tv.common.bokit.MegacableUserService$householdChangedReceiver$1] */
    public MegacableUserService(Context context, BOService boService, RightVRequestManager requestManager) {
        super(boService);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(boService, "boService");
        Intrinsics.checkParameterIsNotNull(requestManager, "requestManager");
        this.context = context;
        this.requestManager = requestManager;
        this.TAG = MegacableUserService.class.getSimpleName();
        this.macAddress = LazyKt.lazy(new Function0<String>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$macAddress$2
            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                String mACAddress$default = NetworkTools.getMACAddress$default(NetworkTools.INSTANCE, "eth0", null, 2, null);
                return mACAddress$default != null ? mACAddress$default : "ERROR";
            }
        });
        PublishSubject<Boolean> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(publishSubjectCreate, "PublishSubject.create<Boolean>()");
        this.householdCacheCanceler = publishSubjectCreate;
        PublishSubject<Boolean> publishSubjectCreate2 = PublishSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(publishSubjectCreate2, "PublishSubject.create<Boolean>()");
        this.profileCacheCanceler = publishSubjectCreate2;
        this.profileChangedReceiver = new BroadcastReceiver() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$profileChangedReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                Log.d(this.this$0.TAG, "profileChangedReceiver: onReceive(" + intent + ')');
                if ((intent != null ? intent.getAction() : null) == null || !Intrinsics.areEqual(CommonUtils.ACTION_USER_CHANGED, intent.getAction())) {
                    return;
                }
                Log.d(this.this$0.TAG, "Cache: will reset cache on user changed");
                this.this$0.cancelProfileCache();
            }
        };
        this.householdChangedReceiver = new BroadcastReceiver() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$householdChangedReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                Log.d(this.this$0.TAG, "householdChangedReceiver: onReceive(" + intent + ')');
                if ((intent != null ? intent.getAction() : null) == null || !Intrinsics.areEqual(CommonUtils.ACTION_HOUSEHOLD_CHANGED, intent.getAction())) {
                    return;
                }
                Log.d(this.this$0.TAG, "Cache: will reset cache on user changed");
                this.this$0.cancelHouseholdCache();
            }
        };
        Single singleDoOnSuccess = getUrlSessionId().flatMap((Function) new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$getHouseHold$1
            @Override // io.reactivex.functions.Function
            public final Single<JSONObject> apply(Long it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return RightVRequestManager.getRTEJSONObject$default(this.this$0.requestManager, "GetHousehold", 0, MapsKt.mapOf(new Pair("url_session_id", it)), null, 10, null);
            }
        }).doOnSuccess(new Consumer<JSONObject>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$getHouseHold$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(JSONObject jSONObject) throws Exception {
                Log.v(this.this$0.TAG, "CurrentHousehold: " + jSONObject.toString(4));
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(CommonUtils.ACTION_HOUSEHOLD_CHANGED);
                this.this$0.context.registerReceiver(this.this$0.householdChangedReceiver, intentFilter);
                if (jSONObject.has("code")) {
                    throw new Exception(jSONObject.getString("code"));
                }
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleDoOnSuccess, "getUrlSessionId()\n      …)\n            }\n        }");
        Single<JSONObject> singleReturnCacheOnError = CachesKt.returnCacheOnError(singleDoOnSuccess, new Function1<Throwable, Boolean>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$getHouseHold$3
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(Throwable th) {
                return Boolean.valueOf(invoke2(th));
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(Throwable error) {
                Intrinsics.checkParameterIsNotNull(error, "error");
                return error instanceof NoConnectionError;
            }
        });
        this.getHouseHold = singleReturnCacheOnError;
        Single<R> map = singleReturnCacheOnError.map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$accountId$1
            @Override // io.reactivex.functions.Function
            public final String apply(JSONObject jsonObject) {
                Intrinsics.checkParameterIsNotNull(jsonObject, "jsonObject");
                return jsonObject.getString("externalId");
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(map, "getHouseHold\n        .ma…getString(\"externalId\") }");
        PublishSubject publishSubjectCreate3 = PublishSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(publishSubjectCreate3, "PublishSubject.create<Boolean>()");
        this.accountId = CachesKt.cacheUntil(map, publishSubjectCreate3);
        this.getCurrentProfile = LazyKt.lazy(new MegacableUserService$getCurrentProfile$2(this));
        this.getMemberHold = RightVRequestManager.getRTEJSONArray$default(this.requestManager, "GetMemberList", 0, null, 6, null);
        Single<List<RightVParentalRating>> map2 = RightVRequestManager.getRTEJSONArray$default(this.requestManager, "GetParentalRatingList", 0, null, 6, null).cache().map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$getParentalRatingList$1
            @Override // io.reactivex.functions.Function
            public final List<RightVParentalRating> apply(JSONArray it) throws JSONException {
                Intrinsics.checkParameterIsNotNull(it, "it");
                ArrayList arrayList = new ArrayList();
                int length = it.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObject = it.getJSONObject(i);
                    String string = jSONObject.getString("name");
                    Intrinsics.checkExpressionValueIsNotNull(string, "parentalRating.getString(\"name\")");
                    arrayList.add(new RightVParentalRating(string, jSONObject.getInt("level")));
                }
                return arrayList;
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(map2, "requestManager.getRTEJSO…parentalRatings\n        }");
        this.getParentalRatingList = map2;
        this.toolboxToken = LazyKt.lazy(new MegacableUserService$toolboxToken$2(this));
        Observable<RightVServiceSubscriptionInfo> observableFlatMapObservable = RightVRequestManager.getRTEJSONObject$default(this.requestManager, "GetHouseholdSubscriptions", 0, null, null, 14, null).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$serviceSubscriptionsInfo$1
            @Override // io.reactivex.functions.Function
            public final Observable<RightVServiceSubscriptionInfo> apply(final JSONObject json) {
                Intrinsics.checkParameterIsNotNull(json, "json");
                return Observable.create(new ObservableOnSubscribe<T>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$serviceSubscriptionsInfo$1.1
                    @Override // io.reactivex.ObservableOnSubscribe
                    public final void subscribe(ObservableEmitter<RightVServiceSubscriptionInfo> emitter) {
                        Intrinsics.checkParameterIsNotNull(emitter, "emitter");
                        try {
                            JSONArray jSONArray = json.getJSONArray("serviceSubscriptionInfoList");
                            int length = jSONArray.length();
                            for (int i = 0; i < length; i++) {
                                JSONObject jSONObject = jSONArray.getJSONObject(i);
                                if (Intrinsics.areEqual(jSONObject.getString("responseElementType"), "ServiceSubscriptionInfo")) {
                                    JSONObject jSONObject2 = jSONObject.getJSONObject(NotificationCompat.CATEGORY_SERVICE);
                                    String string = jSONObject2.getString("externalId");
                                    Intrinsics.checkExpressionValueIsNotNull(string, "service.getString(\"externalId\")");
                                    String string2 = jSONObject2.getString("model");
                                    Intrinsics.checkExpressionValueIsNotNull(string2, "service.getString(\"model\")");
                                    emitter.onNext(new RightVServiceSubscriptionInfo(string, string2));
                                }
                            }
                            emitter.onComplete();
                        } catch (Exception e) {
                            emitter.tryOnError(e);
                        }
                    }
                });
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(observableFlatMapObservable, "requestManager.getRTEJSO…          }\n            }");
        this.serviceSubscriptionsInfo = observableFlatMapObservable;
        Observable<RightVVideoTicket> observableFlatMapObservable2 = RightVRequestManager.getRTEJSONArray$default(this.requestManager, "GetVideoTicketList", 0, MapsKt.mapOf(new Pair("type", "transaction")), 2, null).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$videoTicketList$1
            @Override // io.reactivex.functions.Function
            public final Observable<RightVVideoTicket> apply(final JSONArray jsonArray) {
                Intrinsics.checkParameterIsNotNull(jsonArray, "jsonArray");
                return Observable.create(new ObservableOnSubscribe<T>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$videoTicketList$1.1
                    @Override // io.reactivex.ObservableOnSubscribe
                    public final void subscribe(ObservableEmitter<RightVVideoTicket> emitter) {
                        Date date;
                        Intrinsics.checkParameterIsNotNull(emitter, "emitter");
                        try {
                            int length = jsonArray.length();
                            for (int i = 0; i < length; i++) {
                                JSONObject jSONObject = jsonArray.getJSONObject(i);
                                Log.v(MegacableUserService$videoTicketList$1.this.this$0.TAG, "videoTicket: " + jSONObject.toString(4));
                                if (Intrinsics.areEqual(jSONObject.getString("responseElementType"), "VideoTicket")) {
                                    long jOptLong = jSONObject.optLong("windowEnd", -1L);
                                    long millis = jSONObject.getLong("rentalPeriod");
                                    int i2 = jSONObject.getInt("rentalPeriodUnit");
                                    long j = jSONObject.getLong("downloadWindowStart");
                                    if (jOptLong > 0) {
                                        date = new Date(jOptLong);
                                    } else {
                                        if (i2 == 2) {
                                            millis = TimeUnit.MINUTES.toMillis(millis);
                                        } else if (i2 == 3) {
                                            millis = TimeUnit.HOURS.toMillis(millis);
                                        } else if (i2 == 4) {
                                            millis = TimeUnit.DAYS.toMillis(millis);
                                        }
                                        date = new Date(j + millis);
                                    }
                                    Log.v(MegacableUserService$videoTicketList$1.this.this$0.TAG, "Window end : " + date);
                                    String string = jSONObject.getString("assetExternalId");
                                    Intrinsics.checkExpressionValueIsNotNull(string, "videoTicket.getString(\n …                        )");
                                    String string2 = jSONObject.getString("videoExternalId");
                                    Intrinsics.checkExpressionValueIsNotNull(string2, "videoTicket.getString(\"videoExternalId\")");
                                    emitter.onNext(new RightVVideoTicket(string, string2, date));
                                }
                            }
                            emitter.onComplete();
                        } catch (Exception e) {
                            emitter.tryOnError(e);
                        }
                    }
                });
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(observableFlatMapObservable2, "requestManager.getRTEJSO…          }\n            }");
        this.videoTicketList = observableFlatMapObservable2;
        Observable observableFlatMapObservable3 = RightVRequestManager.getRTEJSONObject$default(this.requestManager, "GetHouseholdSubscriptions", 0, null, null, 14, null).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$getUserRightVPackages$1
            @Override // io.reactivex.functions.Function
            public final Observable<RightVPackage> apply(final JSONObject response) {
                Intrinsics.checkParameterIsNotNull(response, "response");
                return Observable.create(new ObservableOnSubscribe<T>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$getUserRightVPackages$1.1
                    @Override // io.reactivex.ObservableOnSubscribe
                    public final void subscribe(ObservableEmitter<RightVPackage> emitter) {
                        Intrinsics.checkParameterIsNotNull(emitter, "emitter");
                        try {
                            JSONArray jSONArray = response.getJSONArray("channelPackageSubscriptionInfoList");
                            int length = jSONArray.length();
                            for (int i = 0; i < length; i++) {
                                try {
                                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                                    if (Intrinsics.areEqual(jSONObject.getString("responseElementType"), "ChannelPackageSubscriptionInfo")) {
                                        JSONObject jSONObject2 = jSONObject.getJSONObject("channelPackage");
                                        if (Intrinsics.areEqual(jSONObject2.getString("responseElementType"), "Package")) {
                                            HashMap map3 = new HashMap();
                                            JSONArray jSONArray2 = jSONObject2.getJSONArray("extrafields");
                                            if ((jSONArray2 != null ? jSONArray2.length() : 0) > 0) {
                                                int length2 = jSONArray2.length();
                                                for (int i2 = 0; i2 < length2; i2++) {
                                                    String string = jSONArray2.getJSONObject(i2).getString("name");
                                                    Intrinsics.checkExpressionValueIsNotNull(string, "extraTab.getJSONObject(j).getString(\"name\")");
                                                    String string2 = jSONArray2.getJSONObject(i2).getString("value");
                                                    Intrinsics.checkExpressionValueIsNotNull(string2, "extraTab.getJSONObject(j).getString(\"value\")");
                                                    map3.put(string, string2);
                                                }
                                            }
                                            double d = jSONObject2.getDouble("price");
                                            String string3 = jSONObject2.getString("name");
                                            Intrinsics.checkExpressionValueIsNotNull(string3, "packageTicket.getString(\"name\")");
                                            String string4 = jSONObject2.getString(TvContractCompat.Channels.COLUMN_DESCRIPTION);
                                            Intrinsics.checkExpressionValueIsNotNull(string4, "packageTicket.getString(\"description\")");
                                            String string5 = jSONObject2.getString("externalId");
                                            Intrinsics.checkExpressionValueIsNotNull(string5, "packageTicket.getString(\"externalId\")");
                                            String string6 = jSONObject2.getString("storeProductReferenceId");
                                            Intrinsics.checkExpressionValueIsNotNull(string6, "packageTicket.getString(\"storeProductReferenceId\")");
                                            emitter.onNext(new RightVPackage(d, string3, map3, string4, string5, jSONObject2.getInt("id"), string6));
                                        }
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            emitter.onComplete();
                        } catch (Exception e2) {
                            emitter.tryOnError(e2);
                        }
                    }
                });
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(observableFlatMapObservable3, "requestManager.getRTEJSO…          }\n            }");
        this.getUserRightVPackages = CachesKt.cacheWithTimeout$default(observableFlatMapObservable3, 2L, TimeUnit.SECONDS, (Scheduler) null, 4, (Object) null);
        this.getTimeshiftService = RightVRequestManager.getRTEJSONObject$default(this.requestManager, "GetTimeShiftingServicesConfig", 0, null, null, 14, null);
    }

    /* JADX INFO: renamed from: getRightVLogged$common_prodRelease, reason: from getter */
    public final boolean getRightVLogged() {
        return this.rightVLogged;
    }

    public final String getSerialNumber() {
        String string = this.mobileSerialNumber;
        if (string == null) {
            try {
                string = Build.getSerial();
                if (!CommonUtils.isProdConfig() && (string = this.context.createPackageContext("com.dotscreen.megacable.portal", 2).getSharedPreferences("debug", 0).getString("serialNumber", string)) == null) {
                    Intrinsics.throwNpe();
                }
                if (Intrinsics.areEqual("prod", "dev")) {
                    string = "123456";
                }
            } catch (Throwable unused) {
                string = Settings.Secure.getString(this.context.getContentResolver(), "android_id");
                if (string == null) {
                    string = "12345678";
                }
            }
            Intrinsics.checkExpressionValueIsNotNull(string, "try {\n                va… \"12345678\"\n            }");
        }
        return string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cancelHouseholdCache() {
        this.urlSessionId = Long.valueOf(new Date().getTime());
    }

    public final void cancelProfileCache() {
        Log.v(this.TAG, "Profile: invalidate app cache");
        this.profileCacheCanceler.onNext(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long initializeUrlSessionId() {
        Long lValueOf = Long.valueOf(new Date().getTime());
        this.urlSessionId = lValueOf;
        if (lValueOf != null) {
            return lValueOf.longValue();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Long");
    }

    private final Single<Long> getUrlSessionId() {
        Single<Long> singleCreate = Single.create(new SingleOnSubscribe<T>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.getUrlSessionId.1
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter<Long> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Long l = MegacableUserService.this.urlSessionId;
                it.onSuccess(Long.valueOf(l != null ? l.longValue() : MegacableUserService.this.initializeUrlSessionId()));
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleCreate, "Single.create<Long> {\n  …UrlSessionId())\n        }");
        return singleCreate;
    }

    public final Single<String> getAccountId() {
        return this.accountId;
    }

    public static /* synthetic */ Single login$default(MegacableUserService megacableUserService, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return megacableUserService.login(z);
    }

    public final Single<JSONObject> login(boolean noCache) {
        Log.d(this.TAG, "MAC: " + getMacAddress());
        Log.d(this.TAG, "Serial: " + getSerialNumber());
        Log.d(this.TAG, "Cache: will register");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(CommonUtils.ACTION_USER_CHANGED);
        this.context.registerReceiver(this.profileChangedReceiver, intentFilter);
        if (this.rightVLogged && !noCache) {
            Single<JSONObject> singleJust = Single.just(new JSONObject());
            Intrinsics.checkExpressionValueIsNotNull(singleJust, "Single.just(JSONObject())");
            return singleJust;
        }
        Single<JSONObject> singleDoAfterSuccess = RightVRequestManager.getRTEJSONObject$default(this.requestManager, ToolboxAnalyticsModuleKt.EVENT_LOGIN, 1, MapsKt.mapOf(new Pair("serial_number", getSerialNumber())), null, 8, null).doAfterSuccess(new Consumer<JSONObject>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.login.1
            @Override // io.reactivex.functions.Consumer
            public final void accept(JSONObject jSONObject) {
                MegacableUserService.this.rightVLogged = true;
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleDoAfterSuccess, "requestManager.getRTEJSO…gged = true\n            }");
        return singleDoAfterSuccess;
    }

    private final Single<JSONObject> login(String username, String password, boolean includeSerialNumber) {
        Log.d(this.TAG, "Username: " + username);
        Map mapMutableMapOf = MapsKt.mutableMapOf(new Pair("username", username), new Pair("password", password));
        String strSerialNumber = Terminal.INSTANCE.serialNumber(username, this.context);
        this.mobileSerialNumber = strSerialNumber;
        if (includeSerialNumber) {
            if (strSerialNumber == null) {
                Intrinsics.throwNpe();
            }
            mapMutableMapOf.put("serial_number", strSerialNumber);
        }
        return RightVRequestManager.getRTEJSONObject$default(this.requestManager, ToolboxAnalyticsModuleKt.EVENT_LOGIN, 1, mapMutableMapOf, null, 8, null);
    }

    public final Single<JSONObject> loginOTT(String username, String password, boolean includeSerialNumber) {
        Intrinsics.checkParameterIsNotNull(username, "username");
        Intrinsics.checkParameterIsNotNull(password, "password");
        Single singleFlatMap = login(username, password, includeSerialNumber).flatMap((Function) new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.loginOTT.1
            @Override // io.reactivex.functions.Function
            public final Single<JSONObject> apply(JSONObject it) throws Exception {
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (!it.has("code")) {
                    return RightVRequestManager.getCompassJSONObject$default(MegacableUserService.this.requestManager, ToolboxAnalyticsModuleKt.EVENT_LOGIN, 0, null, 6, null);
                }
                throw new Exception(it.getString("code"));
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleFlatMap, "login(username, password…Object(\"Login\")\n        }");
        return singleFlatMap;
    }

    public final Single<JSONArray> getBouquetList() {
        Single<JSONArray> singleFlatMap = login$default(this, false, 1, null).flatMap(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.getBouquetList.1
            @Override // io.reactivex.functions.Function
            public final Single<JSONArray> apply(JSONObject it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return RightVRequestManager.getRTEJSONArray$default(MegacableUserService.this.requestManager, "GetHouseholdBouquetList", 0, null, 6, null);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleFlatMap, "login().flatMap { reques…tHouseholdBouquetList\") }");
        return singleFlatMap;
    }

    public final Single<JSONObject> loginAnonymous() {
        Single<JSONObject> singleFlatMap = login$default(this, false, 1, null).flatMap(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.loginAnonymous.1
            @Override // io.reactivex.functions.Function
            public final Single<JSONObject> apply(JSONObject it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return RightVRequestManager.getCompassJSONObject$default(MegacableUserService.this.requestManager, ToolboxAnalyticsModuleKt.EVENT_LOGIN, 0, null, 6, null);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleFlatMap, "login()\n            .fla…ct(\"Login\")\n            }");
        return singleFlatMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Single login$default(MegacableUserService megacableUserService, String str, Single single, int i, Object obj) {
        if ((i & 2) != 0) {
            single = Single.just("");
            Intrinsics.checkExpressionValueIsNotNull(single, "Single.just(\"\")");
        }
        return megacableUserService.login(str, single);
    }

    public final Single<RightVUser> login(final String profileExternalId, final Single<String> pinCodeObs) {
        Intrinsics.checkParameterIsNotNull(profileExternalId, "profileExternalId");
        Intrinsics.checkParameterIsNotNull(pinCodeObs, "pinCodeObs");
        Single singleFlatMap = this.accountId.flatMap((Function) new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.login.2
            @Override // io.reactivex.functions.Function
            public final Single<RightVUser> apply(String accountId) {
                Intrinsics.checkParameterIsNotNull(accountId, "accountId");
                return MegacableUserService.this.loginProfile(accountId, profileExternalId, pinCodeObs);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleFlatMap, "accountId.flatMap { acco…Id, pinCodeObs)\n        }");
        return singleFlatMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Single loginProfile$default(MegacableUserService megacableUserService, String str, String str2, Single single, int i, Object obj) {
        if ((i & 4) != 0) {
            single = Single.just("");
            Intrinsics.checkExpressionValueIsNotNull(single, "Single.just(\"\")");
        }
        return megacableUserService.loginProfile(str, str2, single);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Single<RightVUser> loginProfile(final String userExternalId, final String profileExternalId, Single<String> pinCodeObs) {
        Single singleFlatMap = pinCodeObs.flatMap((Function) new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.loginProfile.1
            @Override // io.reactivex.functions.Function
            public final Single<RightVUser> apply(String pinCode) {
                Intrinsics.checkParameterIsNotNull(pinCode, "pinCode");
                Map mapMutableMapOf = MapsKt.mutableMapOf(new Pair("profile_id", profileExternalId));
                if (pinCode.length() > 0) {
                    mapMutableMapOf.put("pin_code", pinCode);
                }
                return RightVRequestManager.getCompassJSONObject$default(MegacableUserService.this.requestManager, ToolboxAnalyticsModuleKt.EVENT_LOGIN, 0, mapMutableMapOf, 2, null).doOnSuccess(new Consumer<JSONObject>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.loginProfile.1.1
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(JSONObject jSONObject) {
                        MegacableUserService.this.cancelProfileCache();
                    }
                }).flatMap(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.loginProfile.1.2
                    @Override // io.reactivex.functions.Function
                    public final Single<Profile> apply(JSONObject it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        return MegacableUserService.this.getGetCurrentProfile();
                    }
                }).map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.loginProfile.1.3
                    @Override // io.reactivex.functions.Function
                    public final RightVUser apply(Profile it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        MegacableUserService megacableUserService = MegacableUserService.this;
                        String str = userExternalId;
                        String name = it.getName();
                        String externalId = it.getExternalId();
                        String image = it.getImage();
                        boolean zIsMainProfile = it.isMainProfile();
                        ParentalRating parentalRatingForName = ParentalRating.INSTANCE.forName(MegacableUserService.this.getBoService(), "RATING:" + it.getParentalLevel());
                        if (parentalRatingForName == null) {
                            Intrinsics.throwNpe();
                        }
                        return new RightVUser(megacableUserService, str, name, externalId, image, zIsMainProfile, parentalRatingForName);
                    }
                }).doAfterSuccess(new Consumer<RightVUser>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.loginProfile.1.4
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(RightVUser user) {
                        Intrinsics.checkParameterIsNotNull(user, "user");
                        MegacableUserService.this.getStatus().onNext(new UserStatus.Authenticated(user));
                    }
                }).doOnError(new Consumer<Throwable>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.loginProfile.1.5
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Throwable th) {
                        RightVRequestManager.getCompassJSONObject$default(MegacableUserService.this.requestManager, ToolboxAnalyticsModuleKt.EVENT_LOGIN, 0, null, 6, null).subscribe(new BiConsumer<JSONObject, Throwable>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.loginProfile.1.5.1
                            @Override // io.reactivex.functions.BiConsumer
                            public final void accept(JSONObject jSONObject, Throwable th2) {
                            }
                        });
                    }
                });
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleFlatMap, "pinCodeObs.flatMap { pin…              }\n        }");
        return singleFlatMap;
    }

    private final Single<Profile> getMainProfile(List<Profile> profiles) {
        int size = profiles.size();
        for (int i = 0; i < size; i++) {
            Profile profile = profiles.get(i);
            if (profile.isMainProfile()) {
                Log.e(this.TAG, "main profile found");
                Single<Profile> singleJust = Single.just(profile);
                Intrinsics.checkExpressionValueIsNotNull(singleJust, "Single.just(profile)");
                return singleJust;
            }
        }
        Single<Profile> singleJust2 = Single.just(profiles.get(0));
        Intrinsics.checkExpressionValueIsNotNull(singleJust2, "Single.just(profiles[0])");
        return singleJust2;
    }

    private final boolean isMainProfile(Profile profile) {
        return profile.getProperties().containsKey("IsMain") && Intrinsics.areEqual(profile.getProperties().get("IsMain"), "true");
    }

    @Override // com.dotscreen.bokit.services.UserService
    public Maybe<PlaybackRight> playbackRight(User user, PurchasableItem purchasable, final Offer offer) {
        Maybe<PlaybackRight> maybeEmpty;
        final String videoExternalId;
        Intrinsics.checkParameterIsNotNull(purchasable, "purchasable");
        if (purchasable instanceof LiveStream) {
            Channel channel = ((LiveStream) purchasable).getChannel();
            if (channel == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVChannel");
            }
            final RightVChannel rightVChannel = (RightVChannel) channel;
            Maybe<PlaybackRight> maybeFirstElement = this.getUserRightVPackages.flatMapMaybe((Function) new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.playbackRight.1
                @Override // io.reactivex.functions.Function
                public final Maybe<PlaybackRight> apply(RightVPackage userPackage) {
                    Intrinsics.checkParameterIsNotNull(userPackage, "userPackage");
                    Log.d(MegacableUserService.this.TAG, "playbackRight: checking \"" + userPackage + "\"...");
                    for (String str : rightVChannel.getPackages()) {
                        if (Intrinsics.areEqual(userPackage.getExternalId(), str)) {
                            Log.d(MegacableUserService.this.TAG, "playbackRight: User is subscribed to \"" + str + Typography.quote);
                            return Maybe.just(new PlaybackRight(new Date(), null, null, null, offer, 14, null));
                        }
                    }
                    return Maybe.empty();
                }
            }).firstElement();
            Intrinsics.checkExpressionValueIsNotNull(maybeFirstElement, "getUserRightVPackages\n  …          .firstElement()");
            return maybeFirstElement;
        }
        if (purchasable instanceof PIPLiveStream) {
            final RightVChannel channel2 = ((PIPLiveStream) purchasable).getChannel();
            Maybe<PlaybackRight> maybeFirstElement2 = this.getUserRightVPackages.flatMapMaybe((Function) new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.playbackRight.2
                @Override // io.reactivex.functions.Function
                public final Maybe<PlaybackRight> apply(RightVPackage userPackage) {
                    Intrinsics.checkParameterIsNotNull(userPackage, "userPackage");
                    Log.d(MegacableUserService.this.TAG, "playbackRight: checking \"" + userPackage + "\"...");
                    for (String str : channel2.getPackages()) {
                        if (Intrinsics.areEqual(userPackage.getExternalId(), str)) {
                            Log.d(MegacableUserService.this.TAG, "playbackRight: User is subscribed to \"" + str + Typography.quote);
                            return Maybe.just(new PlaybackRight(new Date(), null, null, null, offer, 14, null));
                        }
                    }
                    return Maybe.empty();
                }
            }).firstElement();
            Intrinsics.checkExpressionValueIsNotNull(maybeFirstElement2, "getUserRightVPackages\n  …          .firstElement()");
            return maybeFirstElement2;
        }
        if (purchasable instanceof NPVRMedia) {
            Maybe<PlaybackRight> maybeJust = Maybe.just(new PlaybackRight(new Date(), null, null, null, offer, 14, null));
            Intrinsics.checkExpressionValueIsNotNull(maybeJust, "Maybe.just(PlaybackRight…= Date(), offer = offer))");
            return maybeJust;
        }
        if (purchasable instanceof StartOverMedia) {
            Maybe<PlaybackRight> maybeJust2 = Maybe.just(new PlaybackRight(new Date(), null, null, null, offer, 14, null));
            Intrinsics.checkExpressionValueIsNotNull(maybeJust2, "Maybe.just(PlaybackRight…= Date(), offer = offer))");
            return maybeJust2;
        }
        if (purchasable instanceof CatchupMedia) {
            Maybe<PlaybackRight> maybeJust3 = Maybe.just(new PlaybackRight(new Date(), null, null, null, offer, 14, null));
            Intrinsics.checkExpressionValueIsNotNull(maybeJust3, "Maybe.just(PlaybackRight…= Date(), offer = offer))");
            return maybeJust3;
        }
        if (purchasable instanceof Media) {
            if (offer != null) {
                int i = WhenMappings.$EnumSwitchMapping$0[offer.getType().ordinal()];
                if (i == 1) {
                    Log.v(this.TAG, "Transactional Offer " + MegacableUtilsKt.decodeBase64((String) StringsKt.split$default((CharSequence) offer.getId(), new String[]{":"}, false, 0, 6, (Object) null).get(1)));
                    RightvVideoOfferData rightvVideoOfferDataExtractVideoOfferData = MegacableUtilsKt.extractVideoOfferData(offer);
                    videoExternalId = rightvVideoOfferDataExtractVideoOfferData != null ? rightvVideoOfferDataExtractVideoOfferData.getVideoExternalId() : null;
                    maybeEmpty = this.videoTicketList.filter(new Predicate<RightVVideoTicket>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.playbackRight.3
                        @Override // io.reactivex.functions.Predicate
                        public final boolean test(RightVVideoTicket it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            Log.v(MegacableUserService.this.TAG, "ticketID : " + it.getVideoExternalId() + " | offerVideoExternalId : " + videoExternalId);
                            return Intrinsics.areEqual(it.getVideoExternalId(), videoExternalId);
                        }
                    }).firstElement().map((Function) new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.playbackRight.4
                        @Override // io.reactivex.functions.Function
                        public final PlaybackRight apply(RightVVideoTicket it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            return new PlaybackRight(new Date(), it.getEndDate(), null, null, offer, 12, null);
                        }
                    });
                } else if (i == 2) {
                    Log.v(this.TAG, "Subscription Offer");
                    RightvVideoOfferData rightvVideoOfferDataExtractVideoOfferData2 = MegacableUtilsKt.extractVideoOfferData(offer);
                    videoExternalId = rightvVideoOfferDataExtractVideoOfferData2 != null ? rightvVideoOfferDataExtractVideoOfferData2.getServiceExternalId() : null;
                    maybeEmpty = this.serviceSubscriptionsInfo.filter(new Predicate<RightVServiceSubscriptionInfo>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.playbackRight.5
                        @Override // io.reactivex.functions.Predicate
                        public final boolean test(RightVServiceSubscriptionInfo it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            return Intrinsics.areEqual(it.getExternalId(), videoExternalId);
                        }
                    }).firstElement().map((Function) new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.playbackRight.6
                        @Override // io.reactivex.functions.Function
                        public final PlaybackRight apply(RightVServiceSubscriptionInfo it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            return new PlaybackRight(new Date(), null, null, null, offer, 14, null);
                        }
                    });
                } else if (i == 3) {
                    Log.v(this.TAG, "Catchup TV Offer");
                    maybeEmpty = Maybe.just(new PlaybackRight(new Date(), null, null, null, offer, 14, null));
                } else {
                    maybeEmpty = Maybe.empty();
                }
            } else {
                RightvContentIdData rightvContentIdDataExtractContentId = MegacableUtilsKt.extractContentId(purchasable.getId());
                if (rightvContentIdDataExtractContentId != null && rightvContentIdDataExtractContentId.getContentType() != null && Intrinsics.areEqual(rightvContentIdDataExtractContentId.getContentType(), "trailer")) {
                    Maybe<PlaybackRight> maybeJust4 = Maybe.just(new PlaybackRight(new Date(), null, null, null, offer, 14, null));
                    Intrinsics.checkExpressionValueIsNotNull(maybeJust4, "Maybe.just(PlaybackRight…= Date(), offer = offer))");
                    return maybeJust4;
                }
                maybeEmpty = Maybe.empty();
            }
            Intrinsics.checkExpressionValueIsNotNull(maybeEmpty, "if (offer != null) {\n   …ight>()\n                }");
            return maybeEmpty;
        }
        Maybe<PlaybackRight> maybeEmpty2 = Maybe.empty();
        Intrinsics.checkExpressionValueIsNotNull(maybeEmpty2, "Maybe.empty<PlaybackRight>()");
        return maybeEmpty2;
    }

    public final Maybe<PlaybackInfo> basicLivePlaybackInfo(String channelExternalId) {
        Intrinsics.checkParameterIsNotNull(channelExternalId, "channelExternalId");
        Maybe<PlaybackInfo> maybeFlatMapMaybe = RightVRequestManager.getRTEJSONObject$default(this.requestManager, "GetLivePlayingInfo", 0, MapsKt.mapOf(new Pair("serial_number", getSerialNumber()), new Pair("channel_external_id", channelExternalId), new Pair("include_cas_token", true)), null, 10, null).flatMapMaybe(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.basicLivePlaybackInfo.1
            @Override // io.reactivex.functions.Function
            public final Maybe<? extends PlaybackInfo> apply(JSONObject json) {
                Intrinsics.checkParameterIsNotNull(json, "json");
                try {
                    String string = json.getString("playingUrl");
                    Intrinsics.checkExpressionValueIsNotNull(string, "json.getString(\"playingUrl\")");
                    String str = CommonUtils.DRM_LICENCE_SERVER_URL;
                    Intrinsics.checkExpressionValueIsNotNull(str, "CommonUtils.DRM_LICENCE_SERVER_URL");
                    return Maybe.just(new RightVPlaybackInfo(string, str, "dash", json.optString("casToken"), Long.valueOf(json.optLong("resumePosition") * ((long) 1000)), false, 32, null));
                } catch (Exception unused) {
                    return Maybe.empty();
                }
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(maybeFlatMapMaybe, "requestManager.getRTEJSO…          }\n            }");
        return maybeFlatMapMaybe;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [T, java.util.HashMap] */
    @Override // com.dotscreen.bokit.services.UserService
    public Maybe<PlaybackInfo> playbackInfo(User user, Media media, PlaybackRight playbackRight, boolean resume) {
        Maybe<PlaybackInfo> maybeEmpty;
        Intrinsics.checkParameterIsNotNull(media, "media");
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new HashMap();
        if (!Intrinsics.areEqual(getMacAddress(), "ERROR")) {
            ((HashMap) objectRef.element).put("Device-Mac-Address", getMacAddress());
        }
        ((HashMap) objectRef.element).put("Device-Serial-Number", getSerialNumber());
        if (media instanceof LiveStream) {
            if (playbackRight != null) {
                Channel channel = ((LiveStream) media).getChannel();
                if (channel != null) {
                    return basicLivePlaybackInfo(((RightVChannel) channel).getExtras().getChannelExternalId());
                }
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVChannel");
            }
            Maybe<PlaybackInfo> maybeEmpty2 = Maybe.empty();
            Intrinsics.checkExpressionValueIsNotNull(maybeEmpty2, "Maybe.empty()");
            return maybeEmpty2;
        }
        if (media instanceof PIPLiveStream) {
            if (playbackRight != null) {
                maybeEmpty = RightVRequestManager.getRTEJSONObject$default(this.requestManager, "GetLivePlayingInfo", 0, MapsKt.mapOf(new Pair("serial_number", getSerialNumber()), new Pair("channel_external_id", ((PIPLiveStream) media).getChannel().getExtras().getChannelExternalId()), new Pair("include_cas_token", true)), MapsKt.mapOf(TuplesKt.to("Live-Service", "PiP")), 2, null).flatMapMaybe(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.playbackInfo.1
                    @Override // io.reactivex.functions.Function
                    public final Maybe<? extends PlaybackInfo> apply(JSONObject json) {
                        Intrinsics.checkParameterIsNotNull(json, "json");
                        try {
                            String string = json.getString("playingUrl");
                            Intrinsics.checkExpressionValueIsNotNull(string, "json.getString(\"playingUrl\")");
                            String str = CommonUtils.DRM_LICENCE_SERVER_URL;
                            Intrinsics.checkExpressionValueIsNotNull(str, "CommonUtils.DRM_LICENCE_SERVER_URL");
                            return Maybe.just(new RightVPlaybackInfo(string, str, "dash", json.optString("casToken"), Long.valueOf(json.optLong("resumePosition") * ((long) 1000)), false, 32, null));
                        } catch (Exception unused) {
                            return Maybe.empty();
                        }
                    }
                });
            } else {
                maybeEmpty = Maybe.empty();
            }
            Intrinsics.checkExpressionValueIsNotNull(maybeEmpty, "if (playbackRight != nul…empty()\n                }");
            return maybeEmpty;
        }
        if ((media instanceof Movie) || (media instanceof Episode)) {
            LiveTVService liveTVService = getBoService().getLiveTVService();
            if (liveTVService == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableLiveTVService");
            }
            Maybe maybeFlatMap = ((MegacableLiveTVService) liveTVService).getModelExternalId().flatMap(new C05292(playbackRight, media, user, resume, objectRef));
            Intrinsics.checkExpressionValueIsNotNull(maybeFlatMap, "(boService.liveTVService…Info>()\n                }");
            return maybeFlatMap;
        }
        if (media instanceof CatchupMedia) {
            CatchupMedia catchupMedia = (CatchupMedia) media;
            Maybe<PlaybackInfo> maybeFlatMapMaybe = RightVRequestManager.getRTEJSONObject$default(this.requestManager, "GetLivePlayingInfo", 0, MapsKt.mapOf(new Pair("serial_number", getSerialNumber()), new Pair("time_shifting_service", "catchup"), new Pair("program_external_id", catchupMedia.getProgramExternalId()), new Pair("channel_external_id", catchupMedia.getChannelExternalId()), new Pair("include_cas_token", true)), null, 10, null).flatMapMaybe(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.playbackInfo.3
                @Override // io.reactivex.functions.Function
                public final Maybe<? extends PlaybackInfo> apply(JSONObject json) {
                    Intrinsics.checkParameterIsNotNull(json, "json");
                    try {
                        String string = json.getString("playingUrl");
                        Intrinsics.checkExpressionValueIsNotNull(string, "json.getString(\"playingUrl\")");
                        String str = CommonUtils.DRM_LICENCE_SERVER_URL;
                        Intrinsics.checkExpressionValueIsNotNull(str, "CommonUtils.DRM_LICENCE_SERVER_URL");
                        return Maybe.just(new RightVPlaybackInfo(string, str, "dash", json.optString("casToken"), Long.valueOf(json.optLong("resumePosition") * ((long) 1000)), false, 32, null));
                    } catch (Exception e) {
                        return Maybe.error(e);
                    }
                }
            });
            Intrinsics.checkExpressionValueIsNotNull(maybeFlatMapMaybe, "requestManager.getRTEJSO…  }\n                    }");
            return maybeFlatMapMaybe;
        }
        if (media instanceof StartOverMedia) {
            StartOverMedia startOverMedia = (StartOverMedia) media;
            Maybe<PlaybackInfo> maybeFlatMapMaybe2 = RightVRequestManager.getRTEJSONObject$default(this.requestManager, "GetLivePlayingInfo", 0, MapsKt.mapOf(new Pair("serial_number", getSerialNumber()), new Pair("time_shifting_service", "stov"), new Pair("program_external_id", startOverMedia.getProgramExternalId()), new Pair("channel_external_id", startOverMedia.getChannelExternalId()), new Pair("include_cas_token", true)), null, 10, null).flatMapMaybe(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.playbackInfo.4
                @Override // io.reactivex.functions.Function
                public final Maybe<? extends PlaybackInfo> apply(JSONObject json) {
                    Intrinsics.checkParameterIsNotNull(json, "json");
                    try {
                        String string = json.getString("playingUrl");
                        Intrinsics.checkExpressionValueIsNotNull(string, "json.getString(\"playingUrl\")");
                        String str = CommonUtils.DRM_LICENCE_SERVER_URL;
                        Intrinsics.checkExpressionValueIsNotNull(str, "CommonUtils.DRM_LICENCE_SERVER_URL");
                        return Maybe.just(new RightVPlaybackInfo(string, str, "dash", json.optString("casToken"), Long.valueOf(json.optLong("resumePosition") * ((long) 1000)), false, 32, null));
                    } catch (Exception e) {
                        return Maybe.error(e);
                    }
                }
            });
            Intrinsics.checkExpressionValueIsNotNull(maybeFlatMapMaybe2, "requestManager.getRTEJSO…  }\n                    }");
            return maybeFlatMapMaybe2;
        }
        if (media instanceof NPVRMedia) {
            NPVRMedia nPVRMedia = (NPVRMedia) media;
            Maybe<PlaybackInfo> maybeFlatMapMaybe3 = RightVRequestManager.getRTEJSONObject$default(this.requestManager, "GetLivePlayingInfo", 0, MapsKt.mapOf(TuplesKt.to("serial_number", getSerialNumber()), TuplesKt.to("time_shifting_service", "npvr"), TuplesKt.to("channel_external_id", nPVRMedia.getChannelExternalId()), TuplesKt.to("otf_recording_id", nPVRMedia.getOtfRecordingId()), TuplesKt.to("include_cas_token", true)), null, 8, null).flatMapMaybe(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.playbackInfo.5
                @Override // io.reactivex.functions.Function
                public final Maybe<? extends PlaybackInfo> apply(JSONObject json) {
                    Intrinsics.checkParameterIsNotNull(json, "json");
                    try {
                        String string = json.getString("playingUrl");
                        Intrinsics.checkExpressionValueIsNotNull(string, "json.getString(\"playingUrl\")");
                        String str = CommonUtils.DRM_LICENCE_SERVER_URL;
                        Intrinsics.checkExpressionValueIsNotNull(str, "CommonUtils.DRM_LICENCE_SERVER_URL");
                        return Maybe.just(new RightVPlaybackInfo(string, str, "dash", json.optString("casToken"), Long.valueOf(json.optLong("resumePosition") * ((long) 1000)), false, 32, null));
                    } catch (Exception e) {
                        return Maybe.error(e);
                    }
                }
            });
            Intrinsics.checkExpressionValueIsNotNull(maybeFlatMapMaybe3, "requestManager.getRTEJSO…  }\n                    }");
            return maybeFlatMapMaybe3;
        }
        final RightvContentIdData rightvContentIdDataExtractContentId = MegacableUtilsKt.extractContentId(media.getId());
        if (rightvContentIdDataExtractContentId != null && rightvContentIdDataExtractContentId.getContentType() != null && Intrinsics.areEqual(rightvContentIdDataExtractContentId.getContentType(), "trailer")) {
            LiveTVService liveTVService2 = getBoService().getLiveTVService();
            if (liveTVService2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableLiveTVService");
            }
            Maybe maybeFlatMap2 = ((MegacableLiveTVService) liveTVService2).getModelExternalId().flatMap((Function) new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$playbackInfo$$inlined$let$lambda$1
                @Override // io.reactivex.functions.Function
                public final Maybe<PlaybackInfo> apply(String modelExternalId) {
                    Intrinsics.checkParameterIsNotNull(modelExternalId, "modelExternalId");
                    RightVRequestManager rightVRequestManager = this.requestManager;
                    Pair[] pairArr = new Pair[4];
                    pairArr[0] = TuplesKt.to("serial_number", this.getSerialNumber());
                    pairArr[1] = TuplesKt.to("model_external_id", modelExternalId);
                    String externalPageId = rightvContentIdDataExtractContentId.getExternalPageId();
                    if (externalPageId == null) {
                        Intrinsics.throwNpe();
                    }
                    pairArr[2] = TuplesKt.to("external_page_id", externalPageId);
                    pairArr[3] = TuplesKt.to("attachment_name", "Trailer");
                    return RightVRequestManager.getRTEJSONObject$default(rightVRequestManager, "GetAttachmentPlayingInfo", 0, MapsKt.mapOf(pairArr), null, 8, null).flatMapMaybe(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$playbackInfo$6$1$1
                        @Override // io.reactivex.functions.Function
                        public final Maybe<? extends PlaybackInfo> apply(JSONObject json) {
                            Intrinsics.checkParameterIsNotNull(json, "json");
                            try {
                                String string = json.getString(ImagesContract.URL);
                                Intrinsics.checkExpressionValueIsNotNull(string, "json.getString(\"url\")");
                                String str = CommonUtils.DRM_LICENCE_SERVER_URL;
                                Intrinsics.checkExpressionValueIsNotNull(str, "CommonUtils.DRM_LICENCE_SERVER_URL");
                                return Maybe.just(new RightVPlaybackInfo(string, str, "dash", json.optString("token"), Long.valueOf(json.optLong("position") * ((long) 1000)), false, 32, null));
                            } catch (Exception e) {
                                return Maybe.error(e);
                            }
                        }
                    });
                }
            });
            Intrinsics.checkExpressionValueIsNotNull(maybeFlatMap2, "(boService.liveTVService…                        }");
            return maybeFlatMap2;
        }
        Maybe<PlaybackInfo> maybeEmpty3 = Maybe.empty();
        Intrinsics.checkExpressionValueIsNotNull(maybeEmpty3, "Maybe.empty()");
        return maybeEmpty3;
    }

    /* JADX INFO: renamed from: com.dotscreen.tv.common.bokit.MegacableUserService$playbackInfo$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MegacableUserService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a*\u0012\u000e\b\u0001\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002 \u0003*\u0014\u0012\u000e\b\u0001\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0018\u00010\u00010\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Lio/reactivex/Maybe;", "Lcom/dotscreen/bokit/model/PlaybackInfo;", "kotlin.jvm.PlatformType", "modelExternalId", "", "apply"}, k = 3, mv = {1, 1, 16})
    static final class C05292<T, R> implements Function<T, MaybeSource<? extends R>> {
        final /* synthetic */ Ref.ObjectRef $headers;
        final /* synthetic */ Media $media;
        final /* synthetic */ PlaybackRight $playbackRight;
        final /* synthetic */ boolean $resume;
        final /* synthetic */ User $user;

        C05292(PlaybackRight playbackRight, Media media, User user, boolean z, Ref.ObjectRef objectRef) {
            this.$playbackRight = playbackRight;
            this.$media = media;
            this.$user = user;
            this.$resume = z;
            this.$headers = objectRef;
        }

        @Override // io.reactivex.functions.Function
        public final Maybe<? extends PlaybackInfo> apply(final String modelExternalId) {
            Completable completableOrderOffer;
            Single<R> rTEJSONObject$default;
            Intrinsics.checkParameterIsNotNull(modelExternalId, "modelExternalId");
            final PlaybackRight playbackRight = this.$playbackRight;
            if (playbackRight != null) {
                Offer offer = playbackRight.getOffer();
                if (offer == null) {
                    Intrinsics.throwNpe();
                }
                if (WhenMappings.$EnumSwitchMapping$2[offer.getType().ordinal()] == 1) {
                    Offer offer2 = playbackRight.getOffer();
                    if (offer2 == null) {
                        Intrinsics.throwNpe();
                    }
                    RightvLiveOfferData rightvLiveOfferDataExtractLiveOfferData = MegacableUtilsKt.extractLiveOfferData(offer2);
                    if (rightvLiveOfferDataExtractLiveOfferData != null) {
                        return RightVRequestManager.getRTEJSONObject$default(MegacableUserService.this.requestManager, "GetLivePlayingInfo", 0, MapsKt.mapOf(new Pair("serial_number", MegacableUserService.this.getSerialNumber()), new Pair("time_shifting_service", "catchup"), new Pair("program_external_id", rightvLiveOfferDataExtractLiveOfferData.getProgramExternalId()), new Pair("channel_external_id", rightvLiveOfferDataExtractLiveOfferData.getChannelExternalId()), new Pair("include_cas_token", true)), null, 10, null).flatMapMaybe(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$playbackInfo$2$1$1$1
                            @Override // io.reactivex.functions.Function
                            public final Maybe<? extends PlaybackInfo> apply(JSONObject json) {
                                Intrinsics.checkParameterIsNotNull(json, "json");
                                try {
                                    String string = json.getString("playingUrl");
                                    Intrinsics.checkExpressionValueIsNotNull(string, "json.getString(\"playingUrl\")");
                                    String str = CommonUtils.DRM_LICENCE_SERVER_URL;
                                    Intrinsics.checkExpressionValueIsNotNull(str, "CommonUtils.DRM_LICENCE_SERVER_URL");
                                    return Maybe.just(new RightVPlaybackInfo(string, str, "dash", json.optString("casToken"), Long.valueOf(json.optLong("resumePosition") * ((long) 1000)), false, 32, null));
                                } catch (Exception unused) {
                                    return Maybe.empty();
                                }
                            }
                        });
                    }
                } else {
                    Offer offer3 = playbackRight.getOffer();
                    if (offer3 == null) {
                        Intrinsics.throwNpe();
                    }
                    final RightvVideoOfferData rightvVideoOfferDataExtractVideoOfferData = MegacableUtilsKt.extractVideoOfferData(offer3);
                    if (rightvVideoOfferDataExtractVideoOfferData != null) {
                        RightvContentIdData rightvContentIdDataExtractContentId = MegacableUtilsKt.extractContentId(this.$media.getId());
                        if (rightvContentIdDataExtractContentId == null) {
                        } else {
                            Offer offer4 = playbackRight.getOffer();
                            if (offer4 == null) {
                                Intrinsics.throwNpe();
                            }
                            int i = WhenMappings.$EnumSwitchMapping$1[offer4.getType().ordinal()];
                            if (i == 1) {
                                MegacableUserService megacableUserService = MegacableUserService.this;
                                User user = this.$user;
                                Offer offer5 = playbackRight.getOffer();
                                if (offer5 == null) {
                                    Intrinsics.throwNpe();
                                }
                                completableOrderOffer = megacableUserService.orderOffer(user, offer5);
                            } else if (i == 2) {
                                Offer offer6 = playbackRight.getOffer();
                                if (offer6 == null) {
                                    Intrinsics.throwNpe();
                                }
                                if (offer6.getPrice() == 0.0d) {
                                    MegacableUserService megacableUserService2 = MegacableUserService.this;
                                    User user2 = this.$user;
                                    Offer offer7 = playbackRight.getOffer();
                                    if (offer7 == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    completableOrderOffer = megacableUserService2.orderOffer(user2, offer7);
                                } else {
                                    completableOrderOffer = Completable.complete();
                                    Intrinsics.checkExpressionValueIsNotNull(completableOrderOffer, "Completable.complete()");
                                }
                            } else {
                                completableOrderOffer = Completable.complete();
                                Intrinsics.checkExpressionValueIsNotNull(completableOrderOffer, "Completable.complete()");
                            }
                            if (rightvVideoOfferDataExtractVideoOfferData.getToolboxContent()) {
                                rTEJSONObject$default = MegacableUserService.this.getToolboxToken().flatMap(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$playbackInfo$2$$special$$inlined$let$lambda$5
                                    @Override // io.reactivex.functions.Function
                                    public final Single<JSONObject> apply(String it) {
                                        Intrinsics.checkParameterIsNotNull(it, "it");
                                        return RightVRequestManager.getRTEJSONObject$default(MegacableUserService.this.requestManager, "GetVideoPlayingInfo", 0, MapsKt.mapOf(new Pair("serial_number", MegacableUserService.this.getSerialNumber()), new Pair("video_external_id", rightvVideoOfferDataExtractVideoOfferData.getVideoExternalId()), new Pair("model_external_id", modelExternalId), new Pair(ToolboxAnalyticsModuleKt.EVENT_RESUME, Boolean.valueOf(this.$resume))), MapsKt.plus(MapsKt.mapOf(TuplesKt.to("Authorization", "JWT " + it)), (HashMap) this.$headers.element), 2, null);
                                    }
                                });
                                Intrinsics.checkExpressionValueIsNotNull(rTEJSONObject$default, "toolboxToken.flatMap {\n …                        }");
                            } else {
                                rTEJSONObject$default = RightVRequestManager.getRTEJSONObject$default(MegacableUserService.this.requestManager, "GetVideoPlayingInfo", 0, MapsKt.mapOf(new Pair("serial_number", MegacableUserService.this.getSerialNumber()), new Pair("video_external_id", rightvVideoOfferDataExtractVideoOfferData.getVideoExternalId()), new Pair("model_external_id", modelExternalId), new Pair(ToolboxAnalyticsModuleKt.EVENT_RESUME, Boolean.valueOf(this.$resume))), (HashMap) this.$headers.element, 2, null);
                            }
                            RightVRequestManager rightVRequestManager = MegacableUserService.this.requestManager;
                            Pair[] pairArr = new Pair[2];
                            pairArr[0] = new Pair("viewType", "viewing-history");
                            String contentId = rightvContentIdDataExtractContentId.getContentId();
                            if (contentId == null) {
                                Intrinsics.throwNpe();
                            }
                            pairArr[1] = new Pair("contentId", contentId);
                            return completableOrderOffer.andThen(Single.zip(rTEJSONObject$default, RightVRequestManager.getPCWJSONArray$default(rightVRequestManager, "viewingPositions", 0, MapsKt.mapOf(pairArr), null, (HashMap) this.$headers.element, 10, null).onErrorReturnItem(new JSONArray()), new BiFunction<JSONObject, JSONArray, RightVPlaybackInfo>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$playbackInfo$2$$special$$inlined$let$lambda$6
                                @Override // io.reactivex.functions.BiFunction
                                public final RightVPlaybackInfo apply(JSONObject videoPlayingInfoJson, JSONArray bookmarkArrayJson) throws JSONException {
                                    JSONObject jSONObject;
                                    String drmLicenseUrl;
                                    String playbackUrl;
                                    String str;
                                    Intrinsics.checkParameterIsNotNull(videoPlayingInfoJson, "videoPlayingInfoJson");
                                    Intrinsics.checkParameterIsNotNull(bookmarkArrayJson, "bookmarkArrayJson");
                                    try {
                                        jSONObject = bookmarkArrayJson.getJSONObject(0);
                                    } catch (Exception unused) {
                                        jSONObject = new JSONObject();
                                    }
                                    int iOptInt = Intrinsics.areEqual(jSONObject.optString(NotificationCompat.CATEGORY_STATUS, ""), "Completed") ? 0 : jSONObject.optInt("viewDuration");
                                    String str2 = CommonUtils.DRM_LICENCE_SERVER_URL;
                                    String string = videoPlayingInfoJson.getString(ImagesContract.URL);
                                    String strOptString = videoPlayingInfoJson.optString("token");
                                    if (rightvVideoOfferDataExtractVideoOfferData.getToolboxContent()) {
                                        JSONObject jSONObject2 = new JSONObject(videoPlayingInfoJson.getString(ImagesContract.URL));
                                        String string2 = jSONObject2.getString(ImagesContract.URL);
                                        Log.d(MegacableUserService.this.TAG, "ToolboxPlay => " + string2);
                                        String tbxDRMLicenceUrl = jSONObject2.getString("licenseAcquisitionUrl");
                                        String tbxToken = jSONObject2.optString("token");
                                        Intrinsics.checkExpressionValueIsNotNull(tbxDRMLicenceUrl, "tbxDRMLicenceUrl");
                                        if (tbxDRMLicenceUrl.length() > 0) {
                                            str2 = tbxDRMLicenceUrl;
                                        }
                                        Intrinsics.checkExpressionValueIsNotNull(tbxToken, "tbxToken");
                                        playbackUrl = string2;
                                        drmLicenseUrl = str2;
                                        if (tbxToken.length() > 0) {
                                            str = tbxToken;
                                        }
                                        Intrinsics.checkExpressionValueIsNotNull(playbackUrl, "playbackUrl");
                                        Intrinsics.checkExpressionValueIsNotNull(drmLicenseUrl, "drmLicenseUrl");
                                        return new RightVPlaybackInfo(playbackUrl, drmLicenseUrl, "dash", str, Long.valueOf(((long) iOptInt) * 1000), rightvVideoOfferDataExtractVideoOfferData.getToolboxContent());
                                    }
                                    drmLicenseUrl = str2;
                                    playbackUrl = string;
                                    str = strOptString;
                                    Intrinsics.checkExpressionValueIsNotNull(playbackUrl, "playbackUrl");
                                    Intrinsics.checkExpressionValueIsNotNull(drmLicenseUrl, "drmLicenseUrl");
                                    return new RightVPlaybackInfo(playbackUrl, drmLicenseUrl, "dash", str, Long.valueOf(((long) iOptInt) * 1000), rightvVideoOfferDataExtractVideoOfferData.getToolboxContent());
                                }
                            }).toMaybe());
                        }
                    }
                }
            }
            return Maybe.empty();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [T, java.util.HashMap] */
    public final Single<Integer> getMediaPosition(final Media media) {
        Single singleFlatMap;
        Intrinsics.checkParameterIsNotNull(media, "media");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new HashMap();
        if (!Intrinsics.areEqual(getMacAddress(), "ERROR")) {
            ((HashMap) objectRef.element).put("Device-Mac-Address", getMacAddress());
        }
        ((HashMap) objectRef.element).put("Device-Serial-Number", getSerialNumber());
        if ((media instanceof Movie) || (media instanceof Episode)) {
            singleFlatMap = media.getOffers().toList().onErrorReturn(new Function<Throwable, List<Offer>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.getMediaPosition.1
                @Override // io.reactivex.functions.Function
                public final List<Offer> apply(Throwable it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return CollectionsKt.emptyList();
                }
            }).flatMap((Function) new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.getMediaPosition.2
                @Override // io.reactivex.functions.Function
                public final Single<Integer> apply(List<Offer> it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Offer offer = (Offer) CollectionsKt.first((List) it);
                    Log.i(MegacableUserService.this.TAG, "Rental: getMediaPosition: offer " + offer.getType());
                    if (offer != null && offer.getType() == OfferType.CatchupTV) {
                        RightvLiveOfferData rightvLiveOfferDataExtractLiveOfferData = MegacableUtilsKt.extractLiveOfferData(offer);
                        if (rightvLiveOfferDataExtractLiveOfferData != null) {
                            return RightVRequestManager.getRTEJSONObject$default(MegacableUserService.this.requestManager, "GetRecordingMarkedPosition", 0, MapsKt.mapOf(TuplesKt.to("channel_external_id", rightvLiveOfferDataExtractLiveOfferData.getChannelExternalId()), TuplesKt.to("program_external_id", rightvLiveOfferDataExtractLiveOfferData.getProgramExternalId())), null, 10, null).map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$getMediaPosition$2$1$1
                                @Override // io.reactivex.functions.Function
                                public /* bridge */ /* synthetic */ Object apply(Object obj) {
                                    return Integer.valueOf(apply((JSONObject) obj));
                                }

                                public final int apply(JSONObject json) {
                                    Intrinsics.checkParameterIsNotNull(json, "json");
                                    return json.optInt("position", 0) * 1000;
                                }
                            });
                        }
                        return null;
                    }
                    Intrinsics.checkExpressionValueIsNotNull(offer, "offer");
                    if (MegacableUtilsKt.extractVideoOfferData(offer) != null) {
                        RightvContentIdData rightvContentIdDataExtractContentId = MegacableUtilsKt.extractContentId(media.getId());
                        if (rightvContentIdDataExtractContentId != null) {
                            RightVRequestManager rightVRequestManager = MegacableUserService.this.requestManager;
                            Pair[] pairArr = new Pair[2];
                            pairArr[0] = new Pair("viewType", "viewing-history");
                            String contentId = rightvContentIdDataExtractContentId.getContentId();
                            if (contentId == null) {
                                Intrinsics.throwNpe();
                            }
                            pairArr[1] = new Pair("contentId", contentId);
                            return RightVRequestManager.getPCWJSONArray$default(rightVRequestManager, "viewingPositions", 0, MapsKt.mapOf(pairArr), null, (HashMap) objectRef.element, 10, null).map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$getMediaPosition$2$2$1$1
                                @Override // io.reactivex.functions.Function
                                public /* bridge */ /* synthetic */ Object apply(Object obj) {
                                    return Integer.valueOf(apply((JSONArray) obj));
                                }

                                public final int apply(JSONArray it2) {
                                    JSONObject jSONObject;
                                    Intrinsics.checkParameterIsNotNull(it2, "it");
                                    try {
                                        jSONObject = it2.getJSONObject(0);
                                    } catch (Exception unused) {
                                        jSONObject = new JSONObject();
                                    }
                                    if (Intrinsics.areEqual(jSONObject.optString(NotificationCompat.CATEGORY_STATUS), "Completed")) {
                                        return 0;
                                    }
                                    return jSONObject.optInt("viewDuration", 0) * 1000;
                                }
                            });
                        }
                    }
                    return Single.just(0);
                }
            });
        } else if (media instanceof CatchupMedia) {
            CatchupMedia catchupMedia = (CatchupMedia) media;
            singleFlatMap = RightVRequestManager.getRTEJSONObject$default(this.requestManager, "GetRecordingMarkedPosition", 0, MapsKt.mapOf(TuplesKt.to("channel_external_id", catchupMedia.getChannelExternalId()), TuplesKt.to("program_external_id", catchupMedia.getProgramExternalId())), null, 10, null).map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.getMediaPosition.3
                @Override // io.reactivex.functions.Function
                public /* bridge */ /* synthetic */ Object apply(Object obj) {
                    return Integer.valueOf(apply((JSONObject) obj));
                }

                public final int apply(JSONObject it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return it.optInt("position", 0) * 1000;
                }
            });
        } else if (media instanceof StartOverMedia) {
            StartOverMedia startOverMedia = (StartOverMedia) media;
            singleFlatMap = RightVRequestManager.getRTEJSONObject$default(this.requestManager, "GetRecordingMarkedPosition", 0, MapsKt.mapOf(TuplesKt.to("channel_external_id", startOverMedia.getChannelExternalId()), TuplesKt.to("program_external_id", startOverMedia.getProgramExternalId())), null, 10, null).map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.getMediaPosition.4
                @Override // io.reactivex.functions.Function
                public /* bridge */ /* synthetic */ Object apply(Object obj) {
                    return Integer.valueOf(apply((JSONObject) obj));
                }

                public final int apply(JSONObject it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return it.optInt("position", 0) * 1000;
                }
            });
        } else if (media instanceof NPVRMedia) {
            singleFlatMap = RightVRequestManager.getRTEJSONObject$default(this.requestManager, "GetRecordingMarkedPosition", 0, MapsKt.mapOf(TuplesKt.to("otf_recording_id", ((NPVRMedia) media).getOtfRecordingId())), null, 10, null).map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.getMediaPosition.5
                @Override // io.reactivex.functions.Function
                public /* bridge */ /* synthetic */ Object apply(Object obj) {
                    return Integer.valueOf(apply((JSONObject) obj));
                }

                public final int apply(JSONObject it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return it.optInt("position", 0) * 1000;
                }
            });
        } else {
            singleFlatMap = Single.just(0);
        }
        Single<Integer> singleOnErrorReturn = singleFlatMap.onErrorReturn(new Function<Throwable, Integer>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.getMediaPosition.6
            @Override // io.reactivex.functions.Function
            public /* bridge */ /* synthetic */ Integer apply(Throwable th) {
                return Integer.valueOf(apply2(th));
            }

            /* JADX INFO: renamed from: apply, reason: avoid collision after fix types in other method */
            public final int apply2(Throwable it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Log.e(MegacableUserService.this.TAG, "Error on PCW backend", it);
                return 0;
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleOnErrorReturn, "when (media) {\n         …ow the playback\n        }");
        return singleOnErrorReturn;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [T, java.util.HashMap] */
    @Override // com.dotscreen.bokit.services.UserService
    public Completable saveMediaPosition(User user, final Media media, final long position) {
        Intrinsics.checkParameterIsNotNull(media, "media");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new HashMap();
        if (!Intrinsics.areEqual(getMacAddress(), "ERROR")) {
            ((HashMap) objectRef.element).put("Device-Mac-Address", getMacAddress());
        }
        ((HashMap) objectRef.element).put("Device-Serial-Number", getSerialNumber());
        if ((media instanceof Movie) || (media instanceof Episode)) {
            Completable completableFlatMapCompletable = media.getOffers().toList().onErrorReturn(new Function<Throwable, List<Offer>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.saveMediaPosition.1
                @Override // io.reactivex.functions.Function
                public final List<Offer> apply(Throwable it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return CollectionsKt.emptyList();
                }
            }).flatMapCompletable(new Function<List<Offer>, CompletableSource>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.saveMediaPosition.2
                @Override // io.reactivex.functions.Function
                public final CompletableSource apply(List<Offer> it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Offer offer = (Offer) CollectionsKt.first((List) it);
                    Completable completableIgnoreElement = null;
                    if (offer != null && offer.getType() == OfferType.CatchupTV) {
                        RightvLiveOfferData rightvLiveOfferDataExtractLiveOfferData = MegacableUtilsKt.extractLiveOfferData(offer);
                        return rightvLiveOfferDataExtractLiveOfferData != null ? RightVRequestManager.getRTEJSONObject$default(MegacableUserService.this.requestManager, "MarkRecordingPosition", 0, MapsKt.mapOf(TuplesKt.to("position", Integer.valueOf((int) (position / ((long) 1000)))), TuplesKt.to("channel_external_id", rightvLiveOfferDataExtractLiveOfferData.getChannelExternalId()), TuplesKt.to("program_external_id", rightvLiveOfferDataExtractLiveOfferData.getProgramExternalId())), null, 10, null).ignoreElement() : null;
                    }
                    RightvContentIdData rightvContentIdDataExtractContentId = MegacableUtilsKt.extractContentId(media.getId());
                    if (rightvContentIdDataExtractContentId != null) {
                        completableIgnoreElement = RightVRequestManager.getPCWJSONObject$default(MegacableUserService.this.requestManager, "clientEvents", 1, null, new JSONObject("{\n                                \"type\" : \"VIEW_STOP\",\n                                \"properties\" :\n                                [\n                                    {\n                                        \"name\" : \"contentId\",\n                                        \"values\" : [\"" + rightvContentIdDataExtractContentId.getContentId() + "\"]\n                                    },\n                                    {\n                                        \"name\": \"viewDuration\",\n                                        \"values\": [\"" + (position / ((long) 1000)) + "\"]\n                                    }\n                                ]\n                            }"), (HashMap) objectRef.element, 4, null).ignoreElement();
                    }
                    return completableIgnoreElement;
                }
            });
            Intrinsics.checkExpressionValueIsNotNull(completableFlatMapCompletable, "media.offers.toList().on…      }\n                }");
            return completableFlatMapCompletable;
        }
        if (media instanceof CatchupMedia) {
            CatchupMedia catchupMedia = (CatchupMedia) media;
            Completable completableIgnoreElement = RightVRequestManager.getRTEJSONObject$default(this.requestManager, "MarkRecordingPosition", 0, MapsKt.mapOf(TuplesKt.to("position", Integer.valueOf((int) (position / ((long) 1000)))), TuplesKt.to("channel_external_id", catchupMedia.getChannelExternalId()), TuplesKt.to("program_external_id", catchupMedia.getProgramExternalId())), null, 10, null).ignoreElement();
            Intrinsics.checkExpressionValueIsNotNull(completableIgnoreElement, "requestManager.getRTEJSO…        ).ignoreElement()");
            return completableIgnoreElement;
        }
        if (media instanceof StartOverMedia) {
            StartOverMedia startOverMedia = (StartOverMedia) media;
            Completable completableIgnoreElement2 = RightVRequestManager.getRTEJSONObject$default(this.requestManager, "MarkRecordingPosition", 0, MapsKt.mapOf(TuplesKt.to("position", Integer.valueOf((int) (position / ((long) 1000)))), TuplesKt.to("channel_external_id", startOverMedia.getChannelExternalId()), TuplesKt.to("program_external_id", startOverMedia.getProgramExternalId())), null, 10, null).ignoreElement();
            Intrinsics.checkExpressionValueIsNotNull(completableIgnoreElement2, "requestManager.getRTEJSO…        ).ignoreElement()");
            return completableIgnoreElement2;
        }
        if (media instanceof NPVRMedia) {
            Completable completableIgnoreElement3 = RightVRequestManager.getRTEJSONObject$default(this.requestManager, "MarkRecordingPosition", 0, MapsKt.mapOf(new Pair("position", Integer.valueOf((int) (position / ((long) 1000)))), new Pair("otf_recording_id", ((NPVRMedia) media).getOtfRecordingId())), null, 10, null).ignoreElement();
            Intrinsics.checkExpressionValueIsNotNull(completableIgnoreElement3, "requestManager.getRTEJSO…        ).ignoreElement()");
            return completableIgnoreElement3;
        }
        Completable completableComplete = Completable.complete();
        Intrinsics.checkExpressionValueIsNotNull(completableComplete, "Completable.complete()");
        return completableComplete;
    }

    public final Maybe<Episode> getNextEpisode(Media media) {
        Intrinsics.checkParameterIsNotNull(media, "media");
        final HashMap map = new HashMap();
        if (!Intrinsics.areEqual(getMacAddress(), "ERROR")) {
            map.put("Device-Mac-Address", getMacAddress());
        }
        map.put("Device-Serial-Number", getSerialNumber());
        Maybe<Episode> map2 = Maybe.just(media).map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.getNextEpisode.1
            @Override // io.reactivex.functions.Function
            public final String apply(Media it) {
                String assetExternalId;
                Intrinsics.checkParameterIsNotNull(it, "it");
                Log.d(MegacableUserService.this.TAG, String.valueOf(it));
                String strExternalId = it.externalId(ItemExternalIdScheme.DataProvider);
                if (strExternalId == null) {
                    return "";
                }
                RightvContentIdData rightvContentIdDataExtractContentId = MegacableUtilsKt.extractContentId(strExternalId);
                if (rightvContentIdDataExtractContentId == null || (assetExternalId = rightvContentIdDataExtractContentId.getContentId()) == null) {
                    assetExternalId = rightvContentIdDataExtractContentId != null ? rightvContentIdDataExtractContentId.getAssetExternalId() : null;
                }
                if (assetExternalId != null) {
                    return assetExternalId;
                }
                Log.e(MegacableUserService.this.TAG, "getNextEpisodeFailed:  " + rightvContentIdDataExtractContentId);
                return "";
            }
        }).flatMap(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.getNextEpisode.2
            @Override // io.reactivex.functions.Function
            public final Maybe<JSONArray> apply(String id) {
                Intrinsics.checkParameterIsNotNull(id, "id");
                if (!StringsKt.isBlank(id)) {
                    return RightVRequestManager.getPCWJSONArray$default(MegacableUserService.this.requestManager, "viewingPositions", 0, MapsKt.mapOf(new Pair("viewType", "next-episode"), new Pair("contentId", id)), null, map, 10, null).toMaybe();
                }
                return Maybe.empty();
            }
        }).flatMap(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.getNextEpisode.3
            @Override // io.reactivex.functions.Function
            public final Maybe<? extends BrowsableItem> apply(JSONArray array) {
                Intrinsics.checkParameterIsNotNull(array, "array");
                try {
                    MegacableUserService megacableUserService = MegacableUserService.this;
                    JSONObject jSONObject = array.getJSONObject(0).getJSONObject(ToolboxAnalyticsModuleKt.KEY_CONTENT);
                    Intrinsics.checkExpressionValueIsNotNull(jSONObject, "array.getJSONObject(0).getJSONObject(\"content\")");
                    return MegacableUserService.this.getBoService().getVodCatalogService().itemById(megacableUserService.parseContentBookmark(jSONObject).getId());
                } catch (JSONException e) {
                    Log.e(MegacableUserService.this.TAG, "Failed to parse: " + e);
                    return Maybe.empty();
                }
            }
        }).filter(new Predicate<BrowsableItem>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.getNextEpisode.4
            @Override // io.reactivex.functions.Predicate
            public final boolean test(BrowsableItem it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it instanceof Episode;
            }
        }).map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.getNextEpisode.5
            @Override // io.reactivex.functions.Function
            public final Episode apply(BrowsableItem it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return (Episode) it;
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(map2, "Maybe.just(media)\n      … as Episode\n            }");
        return map2;
    }

    public final Single<String> createMainProfile() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        Single<String> singleOnErrorReturn = this.accountId.flatMap((Function) new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.createMainProfile.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // io.reactivex.functions.Function
            public final Single<JSONObject> apply(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                objectRef.element = it;
                return MegacableUserService.addProfile$default(MegacableUserService.this, "Main", 0, null, it, null, 22, null);
            }
        }).map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.createMainProfile.2
            @Override // io.reactivex.functions.Function
            public final String apply(JSONObject it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return (String) objectRef.element;
            }
        }).onErrorReturn(new Function<Throwable, String>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.createMainProfile.3
            @Override // io.reactivex.functions.Function
            public final String apply(Throwable error) throws Throwable {
                Intrinsics.checkParameterIsNotNull(error, "error");
                Throwable cause = error.getCause();
                if (!(cause instanceof RightVBackendErrorException)) {
                    throw error;
                }
                RightVBackendErrorException rightVBackendErrorException = (RightVBackendErrorException) cause;
                if (rightVBackendErrorException.getErrorType() instanceof ProfileAlreadyExistError) {
                    Log.e(MegacableUserService.this.TAG, "Main profile already exist, message from backend: " + rightVBackendErrorException.getRightvMessage(), cause);
                    return (String) objectRef.element;
                }
                throw error;
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleOnErrorReturn, "accountId\n            .f…          }\n            }");
        return singleOnErrorReturn;
    }

    public final Single<RightVUser> createMainProfileAndLogin() {
        Single singleFlatMap = createMainProfile().flatMap((Function) new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.createMainProfileAndLogin.1
            @Override // io.reactivex.functions.Function
            public final Single<RightVUser> apply(String externalId) {
                Intrinsics.checkParameterIsNotNull(externalId, "externalId");
                MegacableUserService megacableUserService = MegacableUserService.this;
                Single singleJust = Single.just("");
                Intrinsics.checkExpressionValueIsNotNull(singleJust, "Single.just(\"\")");
                return megacableUserService.loginProfile(externalId, externalId, singleJust);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleFlatMap, "createMainProfile()\n    …e.just(\"\"))\n            }");
        return singleFlatMap;
    }

    public static /* synthetic */ Single addProfile$default(MegacableUserService megacableUserService, String str, int i, Integer num, String str2, String str3, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 1;
        }
        int i3 = i;
        if ((i2 & 4) != 0) {
            num = 0;
        }
        Integer num2 = num;
        if ((i2 & 8) != 0) {
            str2 = (String) null;
        }
        String str4 = str2;
        if ((i2 & 16) != 0) {
            str3 = (String) null;
        }
        return megacableUserService.addProfile(str, i3, num2, str4, str3);
    }

    public final Single<JSONObject> addProfile(String name, int parentalLevel, Integer avatar, String externalId, String pinCode) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Map<String, ? extends Object> mapMutableMapOf = MapsKt.mutableMapOf(new Pair("name", name));
        mapMutableMapOf.put(TtmlNode.TAG_IMAGE, CommonUtils.CONFIG_URL + "/img/avatars/" + avatar + ".png");
        if (externalId != null) {
            mapMutableMapOf.put("external_id", externalId);
            mapMutableMapOf.put("property", CollectionsKt.listOf((Object[]) new String[]{"IsMain:true", "PrLevel:5"}));
        } else {
            mapMutableMapOf.put("property", "PrLevel:" + parentalLevel);
        }
        if (pinCode != null) {
            mapMutableMapOf.put("pin_code", pinCode);
        }
        return this.requestManager.getCompassJSONObject("AddProfile", 1, mapMutableMapOf);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Single updateProfile$default(MegacableUserService megacableUserService, String str, Integer num, Integer num2, Single single, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            num = (Integer) null;
        }
        Integer num3 = num;
        if ((i & 4) != 0) {
            num2 = (Integer) null;
        }
        Integer num4 = num2;
        if ((i & 8) != 0) {
            single = Single.just("");
            Intrinsics.checkExpressionValueIsNotNull(single, "Single.just(\"\")");
        }
        Single single2 = single;
        if ((i & 16) != 0) {
            str2 = (String) null;
        }
        return megacableUserService.updateProfile(str, num3, num4, single2, str2);
    }

    /* JADX WARN: Type inference failed for: r4v2, types: [T, com.dotscreen.bokit.model.User] */
    public final Single<Profile> updateProfile(String name, Integer parentalLevel, Integer avatar, final Single<String> currentPinCodeObs, final String newPinCode) {
        Intrinsics.checkParameterIsNotNull(currentPinCodeObs, "currentPinCodeObs");
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (name != null) {
            linkedHashMap.put("name", name);
        }
        if (parentalLevel != null) {
            linkedHashMap.put("property", "PrLevel:" + parentalLevel);
        }
        if (avatar != null) {
            linkedHashMap.put(TtmlNode.TAG_IMAGE, CommonUtils.CONFIG_URL + "/img/avatars/" + avatar + ".png");
        }
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (User) 0;
        Single<Profile> singleDoAfterSuccess = getActiveUser().flatMapSingle((Function) new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.updateProfile.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // io.reactivex.functions.Function
            public final Single<JSONObject> apply(User it) {
                String str;
                Intrinsics.checkParameterIsNotNull(it, "it");
                objectRef.element = it;
                if (((RightVUser) it).getIsMain() && (str = newPinCode) != null) {
                    return MegacableUserService.updateParentalPinCode$default(MegacableUserService.this, str, null, 2, null);
                }
                String str2 = newPinCode;
                if (str2 != null) {
                    return MegacableUserService.this.updateProfilePinCode(str2, currentPinCodeObs);
                }
                Single<JSONObject> singleJust = Single.just(new JSONObject());
                Intrinsics.checkExpressionValueIsNotNull(singleJust, "Single.just(JSONObject())");
                return singleJust;
            }
        }).flatMap(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.updateProfile.2
            @Override // io.reactivex.functions.Function
            public final Single<JSONObject> apply(JSONObject it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                MegacableUserService.this.cancelProfileCache();
                return MegacableUserService.this.updateProfile(linkedHashMap);
            }
        }).flatMap(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.updateProfile.3
            @Override // io.reactivex.functions.Function
            public final Single<Profile> apply(JSONObject it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return MegacableUserService.this.getGetCurrentProfile();
            }
        }).doAfterSuccess(new Consumer<Profile>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.updateProfile.4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Profile profile) {
                MegacableUserService megacableUserService = MegacableUserService.this;
                User user = (User) objectRef.element;
                if (user == null) {
                    Intrinsics.throwNpe();
                }
                String id = user.getId();
                String name2 = profile.getName();
                String externalId = profile.getExternalId();
                String image = profile.getImage();
                boolean zIsMainProfile = profile.isMainProfile();
                ParentalRating parentalRatingForName = ParentalRating.INSTANCE.forName(MegacableUserService.this.getBoService(), "RATING:" + profile.getParentalLevel());
                if (parentalRatingForName == null) {
                    Intrinsics.throwNpe();
                }
                MegacableUserService.this.getStatus().onNext(new UserStatus.Authenticated(new RightVUser(megacableUserService, id, name2, externalId, image, zIsMainProfile, parentalRatingForName)));
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleDoAfterSuccess, "activeUser.flatMapSingle…datedUser))\n            }");
        return singleDoAfterSuccess;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Single<JSONObject> updateProfile(Map<String, String> params) {
        return this.requestManager.getCompassJSONObject("UpdateProfile", 1, params);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Single updateProfilePinCode$default(MegacableUserService megacableUserService, String str, Single single, int i, Object obj) {
        if ((i & 2) != 0) {
            single = Single.just("");
            Intrinsics.checkExpressionValueIsNotNull(single, "Single.just(\"\")");
        }
        return megacableUserService.updateProfilePinCode(str, single);
    }

    public final Single<JSONObject> updateProfilePinCode(final String newPinCode, Single<String> currentPinCodeObs) {
        Intrinsics.checkParameterIsNotNull(newPinCode, "newPinCode");
        Intrinsics.checkParameterIsNotNull(currentPinCodeObs, "currentPinCodeObs");
        Single singleFlatMap = currentPinCodeObs.flatMap((Function) new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.updateProfilePinCode.1
            @Override // io.reactivex.functions.Function
            public final Single<JSONObject> apply(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Map<String, ? extends Object> mapMutableMapOf = MapsKt.mutableMapOf(TuplesKt.to("new_pin_code", newPinCode));
                if (it.length() > 0) {
                    mapMutableMapOf.put("current_pin_code", it);
                }
                return MegacableUserService.this.requestManager.getCompassJSONObject("UpdateProfilePinCode", 1, mapMutableMapOf);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleFlatMap, "currentPinCodeObs.flatMa…s\n            )\n        }");
        return singleFlatMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Single updateParentalPinCode$default(MegacableUserService megacableUserService, String str, Single single, int i, Object obj) {
        if ((i & 2) != 0) {
            single = Single.just("");
            Intrinsics.checkExpressionValueIsNotNull(single, "Single.just(\"\")");
        }
        return megacableUserService.updateParentalPinCode(str, single);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [T, java.lang.String] */
    public final Single<JSONObject> updateParentalPinCode(final String newPinCode, Single<String> currentPinCodeObs) {
        Intrinsics.checkParameterIsNotNull(newPinCode, "newPinCode");
        Intrinsics.checkParameterIsNotNull(currentPinCodeObs, "currentPinCodeObs");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (String) 0;
        Single<JSONObject> singleFlatMap = currentPinCodeObs.flatMap((Function) new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.updateParentalPinCode.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // io.reactivex.functions.Function
            public final Single<Boolean> apply(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                objectRef.element = it;
                return MegacableUserService.this.checkPincode(null, PincodeType.Parental, it);
            }
        }).flatMap(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.updateParentalPinCode.2
            @Override // io.reactivex.functions.Function
            public final Single<JSONObject> apply(Boolean valid) throws WrongPinCodeException {
                Intrinsics.checkParameterIsNotNull(valid, "valid");
                if (valid.booleanValue()) {
                    Map mapMutableMapOf = MapsKt.mutableMapOf(TuplesKt.to("parental_pin", newPinCode));
                    String str = (String) objectRef.element;
                    if (str == null) {
                        Intrinsics.throwNpe();
                    }
                    mapMutableMapOf.put("pin_code", str);
                    return RightVRequestManager.getRTEJSONObject$default(MegacableUserService.this.requestManager, "UpdateParentalPin", 1, mapMutableMapOf, null, 8, null).doOnSuccess(new Consumer<JSONObject>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.updateParentalPinCode.2.1
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(JSONObject jSONObject) {
                            MegacableUserService.this.context.sendBroadcast(new Intent(CommonUtils.ACTION_HOUSEHOLD_CHANGED));
                        }
                    });
                }
                throw new WrongPinCodeException("Wrong pin code");
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleFlatMap, "currentPinCodeObs\n      …          }\n            }");
        return singleFlatMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Single updatePurchasePinCode$default(MegacableUserService megacableUserService, String str, Single single, int i, Object obj) {
        if ((i & 2) != 0) {
            single = Single.just("");
            Intrinsics.checkExpressionValueIsNotNull(single, "Single.just(\"\")");
        }
        return megacableUserService.updatePurchasePinCode(str, single);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [T, java.lang.String] */
    public final Single<JSONObject> updatePurchasePinCode(final String newPinCode, Single<String> currentPinCodeObs) {
        Intrinsics.checkParameterIsNotNull(newPinCode, "newPinCode");
        Intrinsics.checkParameterIsNotNull(currentPinCodeObs, "currentPinCodeObs");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (String) 0;
        Single<JSONObject> singleFlatMap = currentPinCodeObs.flatMap((Function) new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.updatePurchasePinCode.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // io.reactivex.functions.Function
            public final Single<Boolean> apply(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                objectRef.element = it;
                return MegacableUserService.this.checkPincode(null, PincodeType.Purchase, it);
            }
        }).flatMap(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.updatePurchasePinCode.2
            @Override // io.reactivex.functions.Function
            public final Single<JSONObject> apply(Boolean valid) throws WrongPinCodeException {
                Intrinsics.checkParameterIsNotNull(valid, "valid");
                Map mapMutableMapOf = MapsKt.mutableMapOf(TuplesKt.to("new_pin_code", newPinCode));
                if (valid.booleanValue()) {
                    String str = (String) objectRef.element;
                    if (str == null) {
                        Intrinsics.throwNpe();
                    }
                    mapMutableMapOf.put("pin_code", str);
                    return RightVRequestManager.getRTEJSONObject$default(MegacableUserService.this.requestManager, "UpdateMemberPin", 1, mapMutableMapOf, null, 8, null);
                }
                throw new WrongPinCodeException("Wrong pin code");
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleFlatMap, "currentPinCodeObs\n      …          }\n            }");
        return singleFlatMap;
    }

    public final Single<Profile> removeProfilePinCode(String pinCode) {
        Intrinsics.checkParameterIsNotNull(pinCode, "pinCode");
        Single singleFlatMap = this.requestManager.getCompassJSONObject("RemoveProfilePinCode", 1, MapsKt.mapOf(TuplesKt.to("current_pin_code", pinCode))).flatMap((Function) new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.removeProfilePinCode.1
            @Override // io.reactivex.functions.Function
            public final Single<Profile> apply(JSONObject it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                MegacableUserService.this.cancelProfileCache();
                return MegacableUserService.this.getGetCurrentProfile();
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleFlatMap, "requestManager.getCompas…tCurrentProfile\n        }");
        return singleFlatMap;
    }

    public final Single<JSONObject> deleteProfile() {
        return RightVRequestManager.getCompassJSONObject$default(this.requestManager, "DeleteProfile", 0, null, 4, null);
    }

    public final Single<JSONObject> deleteOtherProfile(String profileId) {
        Intrinsics.checkParameterIsNotNull(profileId, "profileId");
        return this.requestManager.getCompassJSONObject("DeleteProfile", 0, MapsKt.mapOf(new Pair("profile_id", profileId)));
    }

    public static /* synthetic */ Observable profiles$default(MegacableUserService megacableUserService, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return megacableUserService.profiles(z);
    }

    public final Observable<Profile> profiles(boolean noCache) {
        Observable<Profile> observableFlatMapObservable = RightVRequestManager.getCompassJSONArray$default(this.requestManager, "GetProfileList", 0, null, false, noCache, 14, null).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.profiles.1
            @Override // io.reactivex.functions.Function
            public final Observable<Profile> apply(JSONArray response) throws JSONException {
                Intrinsics.checkParameterIsNotNull(response, "response");
                ArrayList arrayList = new ArrayList();
                int length = response.length();
                for (int i = 0; i < length; i++) {
                    JSONObject item = response.getJSONObject(i);
                    Profile.Companion companion = Profile.INSTANCE;
                    Intrinsics.checkExpressionValueIsNotNull(item, "item");
                    arrayList.add(companion.parseProfile(item));
                }
                return Observable.fromIterable(arrayList);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(observableFlatMapObservable, "requestManager.getCompas…e(profiles)\n            }");
        return observableFlatMapObservable;
    }

    public final Observable<Profile> mainProfile() {
        Observable<Profile> observableFilter = profiles$default(this, false, 1, null).filter(new Predicate<Profile>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.mainProfile.1
            @Override // io.reactivex.functions.Predicate
            public final boolean test(Profile p) {
                Intrinsics.checkParameterIsNotNull(p, "p");
                return p.isMainProfile();
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(observableFilter, "profiles().filter { p -> p.isMainProfile() }");
        return observableFilter;
    }

    public final Observable<Terminal> terminals() {
        Observable<Terminal> observableFlatMapObservable = RightVRequestManager.getRTEJSONObject$default(this.requestManager, "GetTerminalList", 0, null, null, 14, null).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.terminals.1
            @Override // io.reactivex.functions.Function
            public final Observable<Terminal> apply(JSONObject response) throws JSONException {
                Intrinsics.checkParameterIsNotNull(response, "response");
                ArrayList arrayList = new ArrayList();
                JSONArray jSONArray = response.getJSONArray("terminals");
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject item = jSONArray.getJSONObject(i);
                    Terminal.Companion companion = Terminal.INSTANCE;
                    Intrinsics.checkExpressionValueIsNotNull(item, "item");
                    Terminal terminal = companion.parseTerminal(item);
                    if (Intrinsics.areEqual(terminal.getCategoryExternalId(), "OTT")) {
                        arrayList.add(terminal);
                    }
                }
                return Observable.fromIterable(arrayList);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(observableFlatMapObservable, "requestManager.getRTEJSO…able(terminals)\n        }");
        return observableFlatMapObservable;
    }

    public final Single<JSONObject> registerTerminal(String username) {
        Intrinsics.checkParameterIsNotNull(username, "username");
        String strSerialNumber = Terminal.INSTANCE.serialNumber(username, this.context);
        String strName = Terminal.INSTANCE.name(this.context);
        Log.d(this.TAG, "SerialNumber: " + strSerialNumber);
        Log.d(this.TAG, "Name: " + strName);
        return RightVRequestManager.getRTEJSONObject$default(this.requestManager, "RegisterTerminal", 1, MapsKt.mapOf(new Pair("model_external_id", "Android"), new Pair("serial_number", strSerialNumber), new Pair("name", strName)), null, 8, null);
    }

    public final Single<JSONObject> unregisterTerminal(String serialNumber) {
        Intrinsics.checkParameterIsNotNull(serialNumber, "serialNumber");
        Log.d(this.TAG, "SerialNumber: " + serialNumber);
        return RightVRequestManager.getRTEJSONObject$default(this.requestManager, "UnregisterTerminal", 1, MapsKt.mapOf(new Pair("serial_number", serialNumber)), null, 8, null);
    }

    @Override // com.dotscreen.bokit.services.UserService
    public Observable<BrowsableItem> recommendations(User user, TVProgram program) {
        return super.recommendations(user, program);
    }

    public final Observable<Channel> lastWatchedChannels(RightVUser user) {
        Intrinsics.checkParameterIsNotNull(user, "user");
        final ArrayList<String> channels = new LastWatchedChannelsHelper(this.context, user).getChannels();
        Observable<Channel> observableSorted = CommonUtils.getCurrentLineup(CommonUtils.getBOService(this.context)).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.lastWatchedChannels.1
            @Override // io.reactivex.functions.Function
            public final Observable<Channel> apply(Lineup it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getItems();
            }
        }).filter(new Predicate<Channel>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.lastWatchedChannels.2
            @Override // io.reactivex.functions.Predicate
            public final boolean test(Channel it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return channels.contains(it.getId());
            }
        }).sorted(new Comparator<Channel>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.lastWatchedChannels.3
            @Override // java.util.Comparator
            public final int compare(Channel channel, Channel channel2) {
                return channels.indexOf(channel.getId()) - channels.indexOf(channel2.getId());
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(observableSorted, "CommonUtils.getCurrentLi…exOf(c2.id)\n            }");
        return observableSorted;
    }

    public final Maybe<Channel> lastWatchedChannel(RightVUser user) {
        Intrinsics.checkParameterIsNotNull(user, "user");
        final String lastChannel = new LastWatchedChannelsHelper(this.context, user).getLastChannel();
        Maybe<Channel> maybeFirstElement = CommonUtils.getCurrentLineup(CommonUtils.getBOService(this.context)).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.lastWatchedChannel.1
            @Override // io.reactivex.functions.Function
            public final Observable<Channel> apply(Lineup it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getItems();
            }
        }).filter(new Predicate<Channel>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.lastWatchedChannel.2
            @Override // io.reactivex.functions.Predicate
            public final boolean test(Channel it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return Intrinsics.areEqual(lastChannel, it.getId());
            }
        }).firstElement();
        Intrinsics.checkExpressionValueIsNotNull(maybeFirstElement, "CommonUtils.getCurrentLi…          .firstElement()");
        return maybeFirstElement;
    }

    public final Observable<RightVServiceSubscriptionInfo> getServiceSubscriptionsInfo() {
        return this.serviceSubscriptionsInfo;
    }

    @Override // com.dotscreen.bokit.services.UserService
    public Observable<PurchasableItem> orderedItems(User user) {
        Observable<PurchasableItem> map = this.videoTicketList.flatMapMaybe((Function) new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.orderedItems.1
            @Override // io.reactivex.functions.Function
            public final Maybe<BrowsableItem> apply(RightVVideoTicket it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Log.v(MegacableUserService.this.TAG, "Ticket for content " + it.getAssetExternalId());
                return MegacableUserService.this.getBoService().getVodCatalogService().itemById("rightv:content:" + it.getAssetExternalId()).onErrorResumeNext(Maybe.empty());
            }
        }).map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.orderedItems.2
            @Override // io.reactivex.functions.Function
            public final PurchasableItem apply(BrowsableItem it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Log.v(MegacableUserService.this.TAG, "Ticket name => " + it.getTitle());
                return (PurchasableItem) it;
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(map, "videoTicketList\n        …hasableItem\n            }");
        return map;
    }

    @Override // com.dotscreen.bokit.services.UserService
    public Completable orderOffer(User user, Offer offer) {
        Intrinsics.checkParameterIsNotNull(offer, "offer");
        int i = WhenMappings.$EnumSwitchMapping$3[offer.getType().ordinal()];
        if (i == 1) {
            final RightvVideoOfferData rightvVideoOfferDataExtractVideoOfferData = MegacableUtilsKt.extractVideoOfferData(offer);
            if (rightvVideoOfferDataExtractVideoOfferData != null) {
                LiveTVService liveTVService = getBoService().getLiveTVService();
                if (liveTVService == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableLiveTVService");
                }
                Completable completableFlatMapCompletable = ((MegacableLiveTVService) liveTVService).getModelExternalId().flatMapCompletable(new Function<String, CompletableSource>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$orderOffer$$inlined$let$lambda$1
                    @Override // io.reactivex.functions.Function
                    public final Completable apply(String modelExternalId) {
                        Intrinsics.checkParameterIsNotNull(modelExternalId, "modelExternalId");
                        return RightVRequestManager.getRTEJSONObject$default(this.requestManager, "OrderVideo", 0, MapsKt.mapOf(TuplesKt.to("model_external_id", modelExternalId), TuplesKt.to("external_video_id", rightvVideoOfferDataExtractVideoOfferData.getVideoExternalId()), TuplesKt.to("pricing_id", rightvVideoOfferDataExtractVideoOfferData.getPricingOptionId()), TuplesKt.to("validateExistingRights", true)), null, 10, null).onErrorResumeNext(new Function<Throwable, SingleSource<? extends JSONObject>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$orderOffer$$inlined$let$lambda$1.1
                            @Override // io.reactivex.functions.Function
                            public final Single<JSONObject> apply(Throwable error) {
                                Intrinsics.checkParameterIsNotNull(error, "error");
                                Log.w(this.TAG, "error", error);
                                Throwable cause = error.getCause();
                                if (!(cause instanceof BackendErrorException)) {
                                    cause = null;
                                }
                                BackendErrorException backendErrorException = (BackendErrorException) cause;
                                if (backendErrorException != null && Intrinsics.areEqual(backendErrorException.getBackendCode(), "TICKET_EXISTS")) {
                                    return Single.just(new JSONObject());
                                }
                                return Single.error(error);
                            }
                        }).ignoreElement();
                    }
                });
                Intrinsics.checkExpressionValueIsNotNull(completableFlatMapCompletable, "(boService.liveTVService…t()\n                    }");
                return completableFlatMapCompletable;
            }
        } else if (i == 2) {
            final RightvVideoOfferData rightvVideoOfferDataExtractVideoOfferData2 = MegacableUtilsKt.extractVideoOfferData(offer);
            if (rightvVideoOfferDataExtractVideoOfferData2 != null) {
                LiveTVService liveTVService2 = getBoService().getLiveTVService();
                if (liveTVService2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableLiveTVService");
                }
                Completable completableFlatMapCompletable2 = ((MegacableLiveTVService) liveTVService2).getModelExternalId().flatMapCompletable(new Function<String, CompletableSource>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.orderOffer.2
                    @Override // io.reactivex.functions.Function
                    public final Completable apply(String modelExternalId) {
                        Intrinsics.checkParameterIsNotNull(modelExternalId, "modelExternalId");
                        return RightVRequestManager.getRTEJSONObject$default(MegacableUserService.this.requestManager, "OrderVideo", 0, MapsKt.mapOf(TuplesKt.to("model_external_id", modelExternalId), TuplesKt.to("external_video_id", rightvVideoOfferDataExtractVideoOfferData2.getVideoExternalId()), TuplesKt.to("subscription", true), TuplesKt.to("validateExistingRights", true)), null, 10, null).onErrorResumeNext(new Function<Throwable, SingleSource<? extends JSONObject>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.orderOffer.2.1
                            @Override // io.reactivex.functions.Function
                            public final Single<JSONObject> apply(Throwable error) {
                                Intrinsics.checkParameterIsNotNull(error, "error");
                                Log.w(MegacableUserService.this.TAG, "error", error);
                                Throwable cause = error.getCause();
                                if (!(cause instanceof BackendErrorException)) {
                                    cause = null;
                                }
                                BackendErrorException backendErrorException = (BackendErrorException) cause;
                                if (backendErrorException != null && Intrinsics.areEqual(backendErrorException.getBackendCode(), "TICKET_EXISTS")) {
                                    return Single.just(new JSONObject());
                                }
                                return Single.error(error);
                            }
                        }).ignoreElement();
                    }
                });
                Intrinsics.checkExpressionValueIsNotNull(completableFlatMapCompletable2, "(boService.liveTVService…t()\n                    }");
                return completableFlatMapCompletable2;
            }
            Completable completableError = Completable.error(new Exception("Unsupported offer"));
            Intrinsics.checkExpressionValueIsNotNull(completableError, "Completable.error(Exception(\"Unsupported offer\"))");
            return completableError;
        }
        Completable completableError2 = Completable.error(new Exception("Unsupported offer"));
        Intrinsics.checkExpressionValueIsNotNull(completableError2, "Completable.error(Exception(\"Unsupported offer\"))");
        return completableError2;
    }

    public final Observable<String> favoritesChannelExternalIds$common_prodRelease() {
        Observable<String> observableFlatMapObservable = RightVRequestManager.getCompassJSONArray$default(this.requestManager, "GetProfileChannelList", 0, null, false, false, 30, null).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$favoritesChannelExternalIds$1
            @Override // io.reactivex.functions.Function
            public final Observable<String> apply(final JSONArray jsonArray) {
                Intrinsics.checkParameterIsNotNull(jsonArray, "jsonArray");
                return Observable.create(new ObservableOnSubscribe<T>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$favoritesChannelExternalIds$1.1
                    @Override // io.reactivex.ObservableOnSubscribe
                    public final void subscribe(ObservableEmitter<String> emitter) {
                        Intrinsics.checkParameterIsNotNull(emitter, "emitter");
                        int length = jsonArray.length();
                        for (int i = 0; i < length; i++) {
                            emitter.onNext(jsonArray.getJSONObject(i).getString("externalId"));
                        }
                        emitter.onComplete();
                    }
                });
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(observableFlatMapObservable, "requestManager.getCompas…          }\n            }");
        return observableFlatMapObservable;
    }

    public final Observable<RightVChannel> favoritesChannels$common_prodRelease() {
        Observable<RightVChannel> observableSorted = Observable.combineLatest(CommonUtils.getCurrentLineup(CommonUtils.getBOService(this.context)).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$favoritesChannels$lineup$1
            @Override // io.reactivex.functions.Function
            public final Observable<Channel> apply(Lineup it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getItems();
            }
        }).toMap(new Function<T, K>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$favoritesChannels$lineup$2
            @Override // io.reactivex.functions.Function
            public final String apply(Channel it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return ((RightVChannel) it).getExtras().getChannelExternalId();
            }
        }).toObservable(), favoritesChannelExternalIds$common_prodRelease(), new BiFunction<Map<String, ? extends Channel>, String, OptionalCompat<RightVChannel>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$favoritesChannels$1
            @Override // io.reactivex.functions.BiFunction
            public final OptionalCompat<RightVChannel> apply(Map<String, ? extends Channel> channelsMap, String channelExternalId) {
                Intrinsics.checkParameterIsNotNull(channelsMap, "channelsMap");
                Intrinsics.checkParameterIsNotNull(channelExternalId, "channelExternalId");
                Channel channel = channelsMap.get(channelExternalId);
                if (!(channel instanceof RightVChannel)) {
                    channel = null;
                }
                RightVChannel rightVChannel = (RightVChannel) channel;
                if (rightVChannel != null) {
                    return OptionalCompat.of(rightVChannel);
                }
                return OptionalCompat.empty();
            }
        }).filter(new Predicate<OptionalCompat<RightVChannel>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$favoritesChannels$2
            @Override // io.reactivex.functions.Predicate
            public final boolean test(OptionalCompat<RightVChannel> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.isPresent();
            }
        }).map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$favoritesChannels$3
            @Override // io.reactivex.functions.Function
            public final RightVChannel apply(OptionalCompat<RightVChannel> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.get();
            }
        }).sorted(new Comparator<RightVChannel>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$favoritesChannels$4
            @Override // java.util.Comparator
            public final int compare(RightVChannel rightVChannel, RightVChannel rightVChannel2) {
                return rightVChannel.getZappingNumber() - rightVChannel2.getZappingNumber();
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(observableSorted, "Observable.combineLatest…mber - c2.zappingNumber }");
        return observableSorted;
    }

    public final Completable addChannelToFavorites$common_prodRelease(RightVChannel channel) {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        Completable completableIgnoreElement = favoritesChannelExternalIds$common_prodRelease().concatWith(Observable.just(channel.getExtras().getChannelExternalId())).toList().map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$addChannelToFavorites$1
            @Override // io.reactivex.functions.Function
            public final String apply(List<String> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return CollectionsKt.joinToString$default(it, ",", null, null, 0, null, null, 62, null);
            }
        }).flatMap(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$addChannelToFavorites$2
            @Override // io.reactivex.functions.Function
            public final Single<JSONObject> apply(String channelIds) {
                Intrinsics.checkParameterIsNotNull(channelIds, "channelIds");
                return RightVRequestManager.getCompassJSONObject$default(this.this$0.requestManager, "UpdateProfileChannelList", 0, MapsKt.mapOf(new Pair("channels", channelIds)), 2, null);
            }
        }).ignoreElement();
        Intrinsics.checkExpressionValueIsNotNull(completableIgnoreElement, "favoritesChannelExternal…         .ignoreElement()");
        return completableIgnoreElement;
    }

    public final Completable removeChannelFromFavorites$common_prodRelease(final RightVChannel channel) {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        Completable completableIgnoreElement = favoritesChannelExternalIds$common_prodRelease().filter(new Predicate<String>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$removeChannelFromFavorites$1
            @Override // io.reactivex.functions.Predicate
            public final boolean test(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return !Intrinsics.areEqual(it, channel.getExtras().getChannelExternalId());
            }
        }).toList().map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$removeChannelFromFavorites$2
            @Override // io.reactivex.functions.Function
            public final String apply(List<String> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return CollectionsKt.joinToString$default(it, ",", null, null, 0, null, null, 62, null);
            }
        }).flatMap(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$removeChannelFromFavorites$3
            @Override // io.reactivex.functions.Function
            public final Single<JSONObject> apply(String channelIds) {
                Intrinsics.checkParameterIsNotNull(channelIds, "channelIds");
                return RightVRequestManager.getCompassJSONObject$default(this.this$0.requestManager, "UpdateProfileChannelList", 0, MapsKt.mapOf(new Pair("channels", channelIds)), 2, null);
            }
        }).ignoreElement();
        Intrinsics.checkExpressionValueIsNotNull(completableIgnoreElement, "favoritesChannelExternal…         .ignoreElement()");
        return completableIgnoreElement;
    }

    public final Observable<String> favoritesVodExternalIds$common_prodRelease() {
        Observable<String> observableFlatMapObservable = RightVRequestManager.getCompassJSONArray$default(this.requestManager, "GetProfileWishList", 0, null, false, false, 30, null).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$favoritesVodExternalIds$1
            @Override // io.reactivex.functions.Function
            public final Observable<String> apply(final JSONArray jsonArray) {
                Intrinsics.checkParameterIsNotNull(jsonArray, "jsonArray");
                return Observable.create(new ObservableOnSubscribe<T>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$favoritesVodExternalIds$1.1
                    @Override // io.reactivex.ObservableOnSubscribe
                    public final void subscribe(ObservableEmitter<String> emitter) {
                        Intrinsics.checkParameterIsNotNull(emitter, "emitter");
                        int length = jsonArray.length();
                        for (int i = 0; i < length; i++) {
                            emitter.onNext(jsonArray.getJSONObject(i).getString("externalContentId"));
                        }
                        emitter.onComplete();
                    }
                });
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(observableFlatMapObservable, "requestManager.getCompas…          }\n            }");
        return observableFlatMapObservable;
    }

    public final Observable<BrowsableItem> favoritesVod$common_prodRelease() {
        Observable<BrowsableItem> observableFlatMapObservable = RightVRequestManager.getCompassJSONArray$default(this.requestManager, "GetProfileWishList", 0, null, false, false, 30, null).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$favoritesVod$1
            @Override // io.reactivex.functions.Function
            public final Observable<BrowsableItem> apply(final JSONArray jsonArray) {
                Intrinsics.checkParameterIsNotNull(jsonArray, "jsonArray");
                return Observable.create(new ObservableOnSubscribe<T>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$favoritesVod$1.1
                    @Override // io.reactivex.ObservableOnSubscribe
                    public final void subscribe(ObservableEmitter<BrowsableItem> emitter) throws JSONException {
                        Intrinsics.checkParameterIsNotNull(emitter, "emitter");
                        int length = jsonArray.length();
                        for (int i = 0; i < length; i++) {
                            MegacableUserService megacableUserService = MegacableUserService$favoritesVod$1.this.this$0;
                            JSONObject jSONObject = jsonArray.getJSONObject(i);
                            Intrinsics.checkExpressionValueIsNotNull(jSONObject, "jsonArray.getJSONObject(i)");
                            emitter.onNext(megacableUserService.parseVOD$common_prodRelease(jSONObject));
                        }
                        emitter.onComplete();
                    }
                });
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(observableFlatMapObservable, "requestManager.getCompas…          }\n            }");
        return observableFlatMapObservable;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x00b6, code lost:
    
        if (r3.equals("Program") == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x015a, code lost:
    
        if (r3.equals("Movie") != false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0162, code lost:
    
        if (r39.has("contentSeq") == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0164, code lost:
    
        r3 = getBoService();
        r5 = r39.getString("name");
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, "json.getString(\"name\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x01dc, code lost:
    
        return new com.dotscreen.bokit.model.Episode(r3, r4, r5, null, r1, null, r39.getInt("contentSeq"), new com.dotscreen.bokit.model.MediaMetadata(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, com.dotscreen.bokit.model.ParentalRating.INSTANCE.forName(getBoService(), "RATING:" + r39.optInt("prLevel", 5)), 32767, null), null, null, false, null, null, 7936, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x01dd, code lost:
    
        r3 = getBoService();
        r5 = r39.getString("name");
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, "json.getString(\"name\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0248, code lost:
    
        return new com.dotscreen.bokit.model.Content(r3, r4, r5, null, r1, null, new com.dotscreen.bokit.model.MediaMetadata(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, com.dotscreen.bokit.model.ParentalRating.INSTANCE.forName(getBoService(), "RATING:" + r39.optInt("prLevel", 5)), 32767, null), false, null, null, 896, null);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.dotscreen.bokit.model.BrowsableItem parseVOD$common_prodRelease(org.json.JSONObject r39) throws org.json.JSONException {
        /*
            Method dump skipped, instruction units count: 617
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dotscreen.tv.common.bokit.MegacableUserService.parseVOD$common_prodRelease(org.json.JSONObject):com.dotscreen.bokit.model.BrowsableItem");
    }

    public final Completable addVodToFavorites$common_prodRelease(RightVUser user, String contentId) {
        Intrinsics.checkParameterIsNotNull(user, "user");
        Intrinsics.checkParameterIsNotNull(contentId, "contentId");
        Completable completableIgnoreElement = RightVRequestManager.getCompassJSONObject$default(this.requestManager, "AddToWishList", 0, MapsKt.mapOf(new Pair("external_content_id", contentId)), 2, null).ignoreElement();
        Intrinsics.checkExpressionValueIsNotNull(completableIgnoreElement, "requestManager.getCompas…         .ignoreElement()");
        return completableIgnoreElement;
    }

    public final Completable removeVodFromFavorites$common_prodRelease(String contentId) {
        Intrinsics.checkParameterIsNotNull(contentId, "contentId");
        Completable completableIgnoreElement = RightVRequestManager.getCompassJSONObject$default(this.requestManager, "RemoveFromWishList", 0, MapsKt.mapOf(new Pair("external_content_id", contentId)), 2, null).ignoreElement();
        Intrinsics.checkExpressionValueIsNotNull(completableIgnoreElement, "requestManager.getCompas…         .ignoreElement()");
        return completableIgnoreElement;
    }

    public final Observable<RightVPackage> getRightVPackages() {
        Observable<RightVPackage> observableFlatMapObservable = RightVRequestManager.getRTEJSONArray$default(this.requestManager, "GetPackageList", 0, null, 6, null).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.getRightVPackages.1
            @Override // io.reactivex.functions.Function
            public final Observable<RightVPackage> apply(final JSONArray jsonArray) {
                Intrinsics.checkParameterIsNotNull(jsonArray, "jsonArray");
                return Observable.create(new ObservableOnSubscribe<T>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.getRightVPackages.1.1
                    @Override // io.reactivex.ObservableOnSubscribe
                    public final void subscribe(ObservableEmitter<RightVPackage> emitter) {
                        Intrinsics.checkParameterIsNotNull(emitter, "emitter");
                        try {
                            int length = jsonArray.length();
                            for (int i = 0; i < length; i++) {
                                JSONObject jSONObject = jsonArray.getJSONObject(i);
                                if (Intrinsics.areEqual(jSONObject.getString("responseElementType"), "Package")) {
                                    HashMap map = new HashMap();
                                    JSONArray jSONArray = jSONObject.getJSONArray("extrafields");
                                    if ((jSONArray != null ? jSONArray.length() : 0) > 0) {
                                        int length2 = jSONArray.length();
                                        for (int i2 = 0; i2 < length2; i2++) {
                                            String string = jSONArray.getJSONObject(i2).getString("name");
                                            Intrinsics.checkExpressionValueIsNotNull(string, "extraTab.getJSONObject(j).getString(\"name\")");
                                            String string2 = jSONArray.getJSONObject(i2).getString("value");
                                            Intrinsics.checkExpressionValueIsNotNull(string2, "extraTab.getJSONObject(j).getString(\"value\")");
                                            map.put(string, string2);
                                        }
                                    }
                                    double d = jSONObject.getDouble("price");
                                    String string3 = jSONObject.getString("name");
                                    Intrinsics.checkExpressionValueIsNotNull(string3, "packageTicket.getString(\"name\")");
                                    String string4 = jSONObject.getString(TvContractCompat.Channels.COLUMN_DESCRIPTION);
                                    Intrinsics.checkExpressionValueIsNotNull(string4, "packageTicket.getString(\"description\")");
                                    String string5 = jSONObject.getString("externalId");
                                    Intrinsics.checkExpressionValueIsNotNull(string5, "packageTicket.getString(\"externalId\")");
                                    String string6 = jSONObject.getString("storeProductReferenceId");
                                    Intrinsics.checkExpressionValueIsNotNull(string6, "packageTicket.getString(\"storeProductReferenceId\")");
                                    emitter.onNext(new RightVPackage(d, string3, map, string4, string5, jSONObject.getInt("id"), string6));
                                }
                            }
                            emitter.onComplete();
                        } catch (Exception e) {
                            emitter.tryOnError(e);
                        }
                    }
                });
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(observableFlatMapObservable, "requestManager.getRTEJSO…          }\n            }");
        return observableFlatMapObservable;
    }

    public final Observable<RightVPackage> getGetUserRightVPackages() {
        return this.getUserRightVPackages;
    }

    public final Observable<RightVService> getRightVServices() {
        Observable<RightVService> observableFlatMapObservable = RightVRequestManager.getRTEJSONArray$default(this.requestManager, "GetServiceList", 0, null, 6, null).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.getRightVServices.1
            @Override // io.reactivex.functions.Function
            public final Observable<RightVService> apply(final JSONArray jsonArray) {
                Intrinsics.checkParameterIsNotNull(jsonArray, "jsonArray");
                return Observable.create(new ObservableOnSubscribe<T>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.getRightVServices.1.1
                    @Override // io.reactivex.ObservableOnSubscribe
                    public final void subscribe(ObservableEmitter<RightVService> emitter) {
                        C01671<T> c01671 = this;
                        String str = "packageTicket.optString(…                        )";
                        Intrinsics.checkParameterIsNotNull(emitter, "emitter");
                        try {
                            int length = jsonArray.length();
                            int i = 0;
                            while (i < length) {
                                JSONObject jSONObject = jsonArray.getJSONObject(i);
                                if (Intrinsics.areEqual(jSONObject.optString("responseElementType", ""), "Service")) {
                                    HashMap map = new HashMap();
                                    JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("extrafields");
                                    if ((jSONArrayOptJSONArray != null ? jSONArrayOptJSONArray.length() : 0) > 0) {
                                        int length2 = jSONArrayOptJSONArray.length();
                                        for (int i2 = 0; i2 < length2; i2++) {
                                            String string = jSONArrayOptJSONArray.getJSONObject(i2).getString("name");
                                            Intrinsics.checkExpressionValueIsNotNull(string, "extraTab.getJSONObject(j).getString(\"name\")");
                                            String string2 = jSONArrayOptJSONArray.getJSONObject(i2).getString("value");
                                            Intrinsics.checkExpressionValueIsNotNull(string2, "extraTab.getJSONObject(j).getString(\"value\")");
                                            map.put(string, string2);
                                        }
                                    }
                                    HashMap map2 = new HashMap();
                                    JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("serviceCatalogFolders");
                                    if ((jSONArrayOptJSONArray2 != null ? jSONArrayOptJSONArray2.length() : 0) > 0) {
                                        int length3 = jSONArrayOptJSONArray2.length();
                                        for (int i3 = 0; i3 < length3; i3++) {
                                            String string3 = jSONArrayOptJSONArray2.getJSONObject(i3).getString("externalId");
                                            Intrinsics.checkExpressionValueIsNotNull(string3, "serviceCatalogTab.getJSO… .getString(\"externalId\")");
                                            map2.put(string3, Integer.valueOf(jSONArrayOptJSONArray2.getJSONObject(i3).getInt("id")));
                                        }
                                    }
                                    double dOptDouble = jSONObject.optDouble("price", 0.0d);
                                    String strOptString = jSONObject.optString("name", "");
                                    Intrinsics.checkExpressionValueIsNotNull(strOptString, "packageTicket.optString(\"name\", \"\")");
                                    String strOptString2 = jSONObject.optString("quotaInHours", "");
                                    Intrinsics.checkExpressionValueIsNotNull(strOptString2, "packageTicket.optString(\"quotaInHours\", \"\")");
                                    String strOptString3 = jSONObject.optString("concurrentRecordingsLimit", "");
                                    Intrinsics.checkExpressionValueIsNotNull(strOptString3, str);
                                    String strOptString4 = jSONObject.optString("simultaneousViewsLimit", "");
                                    Intrinsics.checkExpressionValueIsNotNull(strOptString4, str);
                                    String strOptString5 = jSONObject.optString("model", "");
                                    Intrinsics.checkExpressionValueIsNotNull(strOptString5, "packageTicket.optString(\"model\", \"\")");
                                    String strOptString6 = jSONObject.optString(TvContractCompat.Channels.COLUMN_DESCRIPTION, "");
                                    Intrinsics.checkExpressionValueIsNotNull(strOptString6, "packageTicket.optString(\"description\", \"\")");
                                    String strOptString7 = jSONObject.optString("externalId", "");
                                    Intrinsics.checkExpressionValueIsNotNull(strOptString7, "packageTicket.optString(\"externalId\", \"\")");
                                    String strOptString8 = jSONObject.optString("storeProductReferenceId", "");
                                    Intrinsics.checkExpressionValueIsNotNull(strOptString8, str);
                                    emitter.onNext(new RightVService(dOptDouble, strOptString, map, strOptString6, strOptString7, jSONObject.optInt("id", -1), strOptString8, map2, strOptString2, strOptString3, strOptString4, strOptString5));
                                }
                                i++;
                                c01671 = this;
                                length = length;
                                str = str;
                            }
                            emitter.onComplete();
                        } catch (Exception e) {
                            emitter.tryOnError(e);
                        }
                    }
                });
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(observableFlatMapObservable, "requestManager.getRTEJSO…          }\n            }");
        return observableFlatMapObservable;
    }

    @Override // com.dotscreen.bokit.services.UserService
    public Single<Boolean> checkPincode(User user, PincodeType type, final String pinCode) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(pinCode, "pinCode");
        int i = WhenMappings.$EnumSwitchMapping$4[type.ordinal()];
        if (i == 1) {
            Single map = this.getHouseHold.map((Function) new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.checkPincode.1
                @Override // io.reactivex.functions.Function
                public /* bridge */ /* synthetic */ Object apply(Object obj) {
                    return Boolean.valueOf(apply((JSONObject) obj));
                }

                public final boolean apply(JSONObject json) {
                    Intrinsics.checkParameterIsNotNull(json, "json");
                    return Intrinsics.areEqual(json.getString("parentalPin"), pinCode);
                }
            });
            Intrinsics.checkExpressionValueIsNotNull(map, "getHouseHold.map { json …arentalPin\") == pinCode }");
            return map;
        }
        if (i == 2) {
            Single map2 = this.getMemberHold.map((Function) new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.checkPincode.2
                @Override // io.reactivex.functions.Function
                public /* bridge */ /* synthetic */ Object apply(Object obj) {
                    return Boolean.valueOf(apply((JSONArray) obj));
                }

                public final boolean apply(JSONArray json) {
                    Intrinsics.checkParameterIsNotNull(json, "json");
                    try {
                        return json.optJSONObject(0) != null && Intrinsics.areEqual(json.optJSONObject(0).getString("pin"), pinCode);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                }
            });
            Intrinsics.checkExpressionValueIsNotNull(map2, "getMemberHold.map { json…n@map false\n            }");
            return map2;
        }
        if (i == 3) {
            Single<Boolean> map3 = RequestManager.getJSONObject$default(this.requestManager, 0, new URL(CommonUtils.CONFIG_URL + "/config_tv.json"), null, 4, null).map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.checkPincode.3
                @Override // io.reactivex.functions.Function
                public /* bridge */ /* synthetic */ Object apply(Object obj) {
                    return Boolean.valueOf(apply((JSONObject) obj));
                }

                public final boolean apply(JSONObject json) {
                    Intrinsics.checkParameterIsNotNull(json, "json");
                    return Intrinsics.areEqual(json.optString("updatePinCode", "1234"), pinCode);
                }
            });
            Intrinsics.checkExpressionValueIsNotNull(map3, "requestManager.getJSONOb… == pinCode\n            }");
            return map3;
        }
        return super.checkPincode(user, type, pinCode);
    }

    public final Single<String> getPinCode() {
        Single map = this.getHouseHold.map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.getPinCode.1
            @Override // io.reactivex.functions.Function
            public final String apply(JSONObject json) {
                Intrinsics.checkParameterIsNotNull(json, "json");
                return json.getString("parentalPin");
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(map, "getHouseHold.map { json …etString(\"parentalPin\") }");
        return map;
    }

    public final Observable<Media> bookmarks(boolean adultBrowsing) {
        HashMap map = new HashMap();
        if (!Intrinsics.areEqual(getMacAddress(), "ERROR")) {
            map.put("Device-Mac-Address", getMacAddress());
        }
        map.put("Device-Serial-Number", getSerialNumber());
        RightVRequestManager rightVRequestManager = this.requestManager;
        Pair[] pairArr = new Pair[2];
        pairArr[0] = new Pair("viewType", "next-to-watch");
        pairArr[1] = adultBrowsing ? new Pair("minPrLevel", 6) : new Pair("maxPrLevel", 5);
        Observable<Media> observableFlatMapObservable = RightVRequestManager.getPCWJSONArray$default(rightVRequestManager, "viewingPositions", 0, MapsKt.mapOf(pairArr), null, map, 10, null).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.bookmarks.1
            @Override // io.reactivex.functions.Function
            public final Observable<Media> apply(JSONArray bookmarks) throws JSONException {
                Intrinsics.checkParameterIsNotNull(bookmarks, "bookmarks");
                ArrayList arrayList = new ArrayList();
                int length = bookmarks.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObject = bookmarks.getJSONObject(i);
                    if (!Intrinsics.areEqual(jSONObject.getString(NotificationCompat.CATEGORY_STATUS), "Completed")) {
                        MegacableUserService megacableUserService = MegacableUserService.this;
                        JSONObject jSONObject2 = jSONObject.getJSONObject(ToolboxAnalyticsModuleKt.KEY_CONTENT);
                        Intrinsics.checkExpressionValueIsNotNull(jSONObject2, "bookmark.getJSONObject(\"content\")");
                        arrayList.add(megacableUserService.parseContentBookmark(jSONObject2));
                    }
                }
                final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                return Observable.fromIterable(arrayList).flatMapMaybe(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.bookmarks.1.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.reactivex.functions.Function
                    public final Maybe<BrowsableItem> apply(Media it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        objectRef.element = it;
                        return MegacableUserService.this.getBoService().getVodCatalogService().itemById(it.getId()).onErrorReturnItem((Media) objectRef.element);
                    }
                }).map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.bookmarks.1.2
                    @Override // io.reactivex.functions.Function
                    public final Media apply(BrowsableItem it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        return (Media) it;
                    }
                });
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(observableFlatMapObservable, "requestManager.getPCWJSO…{ it as Media }\n        }");
        return observableFlatMapObservable;
    }

    public final Observable<Media> nextEpisode(final String serieId, boolean adultBrowsing) {
        Intrinsics.checkParameterIsNotNull(serieId, "serieId");
        HashMap map = new HashMap();
        if (!Intrinsics.areEqual(getMacAddress(), "ERROR")) {
            map.put("Device-Mac-Address", getMacAddress());
        }
        map.put("Device-Serial-Number", getSerialNumber());
        RightVRequestManager rightVRequestManager = this.requestManager;
        Pair[] pairArr = new Pair[3];
        pairArr[0] = new Pair("viewType", "next-to-watch");
        pairArr[1] = new Pair("contentType", "episode");
        pairArr[2] = adultBrowsing ? new Pair("minPrLevel", 6) : new Pair("maxPrLevel", 5);
        Observable<Media> observableFlatMapObservable = RightVRequestManager.getPCWJSONArray$default(rightVRequestManager, "viewingPositions", 0, MapsKt.mapOf(pairArr), null, map, 10, null).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.nextEpisode.1
            @Override // io.reactivex.functions.Function
            public final Observable<Media> apply(JSONArray episodes) throws JSONException {
                Intrinsics.checkParameterIsNotNull(episodes, "episodes");
                ArrayList arrayList = new ArrayList();
                int length = episodes.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObject = episodes.getJSONObject(i);
                    if (Intrinsics.areEqual(jSONObject.getJSONObject(ToolboxAnalyticsModuleKt.KEY_CONTENT).getString("seriesName"), serieId)) {
                        MegacableUserService megacableUserService = MegacableUserService.this;
                        JSONObject jSONObject2 = jSONObject.getJSONObject(ToolboxAnalyticsModuleKt.KEY_CONTENT);
                        Intrinsics.checkExpressionValueIsNotNull(jSONObject2, "bookmark.getJSONObject(\"content\")");
                        arrayList.add(megacableUserService.parseContentBookmark(jSONObject2));
                    }
                }
                return Observable.fromIterable(arrayList).flatMapMaybe(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.nextEpisode.1.1
                    @Override // io.reactivex.functions.Function
                    public final Maybe<BrowsableItem> apply(Media it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        return MegacableUserService.this.getBoService().getVodCatalogService().itemById(it.getId());
                    }
                }).map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.nextEpisode.1.2
                    @Override // io.reactivex.functions.Function
                    public final Media apply(BrowsableItem it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        return (Media) it;
                    }
                });
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(observableFlatMapObservable, "requestManager.getPCWJSO…a\n            }\n        }");
        return observableFlatMapObservable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final Content parseContentBookmark(JSONObject json) throws JSONException {
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArrayOptJSONArray = json.optJSONArray("customProperties");
        if (jSONArrayOptJSONArray != null) {
            int length = jSONArrayOptJSONArray.length();
            for (int i = 0; i < length; i++) {
                Object obj = jSONArrayOptJSONArray.get(i);
                if (obj == null) {
                    throw new TypeCastException("null cannot be cast to non-null type org.json.JSONObject");
                }
                JSONObject jSONObject = (JSONObject) obj;
                String string = jSONObject.getString("key");
                Intrinsics.checkExpressionValueIsNotNull(string, "image.getString(\"key\")");
                ArtworkFormat rightVArtworkFormat$default = MegacableUtilsKt.getRightVArtworkFormat$default(string, null, 2, null);
                String url = jSONObject.getString("value");
                BOService boService = getBoService();
                Intrinsics.checkExpressionValueIsNotNull(url, "url");
                arrayList.add(new StaticArtwork(boService, url, rightVArtworkFormat$default));
            }
        }
        if (Intrinsics.areEqual(json.getString("type"), "Episode")) {
            BOService boService2 = getBoService();
            String str = "rightv:movie:" + json.getString("id");
            String string2 = json.getString("name");
            Intrinsics.checkExpressionValueIsNotNull(string2, "json.getString(\"name\")");
            return new Episode(boService2, str, string2, null, arrayList, null, json.getInt("episodeNumber"), new MediaMetadata(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, ParentalRating.INSTANCE.forName(getBoService(), "RATING:" + json.optInt("prLevel", 5)), 32767, null), null, null, false, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 7936, 0 == true ? 1 : 0);
        }
        BOService boService3 = getBoService();
        String str2 = "rightv:movie:" + json.getString("id");
        String string3 = json.getString("name");
        Intrinsics.checkExpressionValueIsNotNull(string3, "json.getString(\"name\")");
        return new Content(boService3, str2, string3, null, arrayList, null, new MediaMetadata(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, ParentalRating.INSTANCE.forName(getBoService(), "RATING:" + json.optInt("prLevel", 5)), 32767, null), false, null, null, 896, 0 == true ? 1 : 0);
    }

    public final Single<SubscriptionResult> sendSubscribeRequest(final String packageId) {
        Intrinsics.checkParameterIsNotNull(packageId, "packageId");
        Single<SubscriptionResult> map = this.accountId.flatMap((Function) new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.sendSubscribeRequest.1
            @Override // io.reactivex.functions.Function
            public final Single<JSONObject> apply(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return MegacableUserService.this.requestManager.megacablePackageSubscribe(it, MegacableUserService.this.getMacAddress(), packageId);
            }
        }).map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.sendSubscribeRequest.2
            @Override // io.reactivex.functions.Function
            public final SubscriptionResult apply(JSONObject it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                String strOptString = it.optString("resultCode");
                Intrinsics.checkExpressionValueIsNotNull(strOptString, "it.optString(\"resultCode\")");
                String strOptString2 = it.optJSONObject(TvContractCompat.Channels.COLUMN_DESCRIPTION).optString("sp");
                Intrinsics.checkExpressionValueIsNotNull(strOptString2, "it.optJSONObject(\"description\").optString(\"sp\")");
                String strOptString3 = it.optJSONObject(TvContractCompat.Channels.COLUMN_DESCRIPTION).optString("en");
                Intrinsics.checkExpressionValueIsNotNull(strOptString3, "it.optJSONObject(\"description\").optString(\"en\")");
                return new SubscriptionResult(strOptString, strOptString2, strOptString3);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(map, "accountId.flatMap {\n    …)\n            )\n        }");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Single<JSONObject> loginToolbox() {
        Single<JSONObject> map = this.accountId.flatMap((Function) new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.loginToolbox.1
            @Override // io.reactivex.functions.Function
            public final Single<Pair<String, String>> apply(final String accountId) {
                Intrinsics.checkParameterIsNotNull(accountId, "accountId");
                LiveTVService liveTVService = MegacableUserService.this.getBoService().getLiveTVService();
                if (liveTVService == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableLiveTVService");
                }
                return ((MegacableLiveTVService) liveTVService).getModelExternalId().toSingle().map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.loginToolbox.1.1
                    @Override // io.reactivex.functions.Function
                    public final Pair<String, String> apply(String modelExternalId) {
                        Intrinsics.checkParameterIsNotNull(modelExternalId, "modelExternalId");
                        return new Pair<>(accountId, modelExternalId);
                    }
                }).onErrorReturn(new Function<Throwable, Pair<? extends String, ? extends String>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.loginToolbox.1.2
                    @Override // io.reactivex.functions.Function
                    public final Pair<String, String> apply(Throwable it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        return new Pair<>(accountId, "Android");
                    }
                });
            }
        }).flatMap(new C05162()).doOnError(new Consumer<Throwable>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.loginToolbox.3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Log.e(MegacableUserService.this.TAG, "Error on token retrieve => " + th.getMessage());
            }
        }).map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.loginToolbox.4
            @Override // io.reactivex.functions.Function
            public final JSONObject apply(JSONObject response) throws BackendErrorException, JSONException {
                Intrinsics.checkParameterIsNotNull(response, "response");
                String string = response.getString("result");
                Intrinsics.checkExpressionValueIsNotNull(string, "response.getString(\"result\")");
                if (!StringsKt.contains$default((CharSequence) string, (CharSequence) "error", false, 2, (Object) null)) {
                    return response;
                }
                String errorMessage = response.getString("result");
                Pattern patternCompile = Pattern.compile("-?\\d+");
                Intrinsics.checkExpressionValueIsNotNull(patternCompile, "Pattern.compile(\"-?\\\\d+\")");
                Matcher matcher = patternCompile.matcher(errorMessage);
                Intrinsics.checkExpressionValueIsNotNull(matcher, "p.matcher(errorMessage)");
                String errorCode = matcher.find() ? matcher.group() : LocalSearchProvider.NO_LIVE_CONTENTS;
                Intrinsics.checkExpressionValueIsNotNull(errorMessage, "errorMessage");
                Intrinsics.checkExpressionValueIsNotNull(errorCode, "errorCode");
                String str = CommonUtils.TBX_EVENT_URL;
                Intrinsics.checkExpressionValueIsNotNull(str, "CommonUtils.TBX_EVENT_URL");
                throw new BackendErrorException(errorMessage, "", errorCode, str, null, 16, null);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(map, "accountId\n            .f…   response\n            }");
        return map;
    }

    /* JADX INFO: renamed from: com.dotscreen.tv.common.bokit.MegacableUserService$loginToolbox$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MegacableUserService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u0010\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u00020\u00012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "Lorg/json/JSONObject;", "kotlin.jvm.PlatformType", "it", "Lkotlin/Pair;", "", "apply"}, k = 3, mv = {1, 1, 16})
    static final class C05162<T, R> implements Function<T, SingleSource<? extends R>> {
        C05162() {
        }

        @Override // io.reactivex.functions.Function
        public final Single<JSONObject> apply(Pair<String, String> it) throws JSONException {
            Intrinsics.checkParameterIsNotNull(it, "it");
            String first = it.getFirst();
            Intrinsics.checkExpressionValueIsNotNull(first, "it.first");
            final String str = first;
            JSONObject jSONObject = new JSONObject(MapsKt.mapOf(new Pair("account", str)));
            if (Intrinsics.areEqual(it.getSecond(), "Android")) {
                jSONObject.put("deviceType", "ANDR");
            } else {
                jSONObject.put("deviceType", "STB");
                jSONObject.put("macAddress", MegacableUserService.this.getMacAddress());
            }
            return RightVRequestManager.getTbxJSONObject$default(MegacableUserService.this.requestManager, "auv2", 0, null, jSONObject, 6, null).doAfterSuccess(new Consumer<JSONObject>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.loginToolbox.2.1
                @Override // io.reactivex.functions.Consumer
                public final void accept(JSONObject jSONObject2) throws JSONException {
                    if (Intrinsics.areEqual(jSONObject2.getString("result"), "success")) {
                        RightVRequestManager rightVRequestManager = MegacableUserService.this.requestManager;
                        Map<String, Object> eventsParams = ToolboxAnalyticsModule.ToolboxEvents.LOGIN.getEventsParams(MapsKt.mutableMapOf(new Pair(ToolboxAnalyticsModuleKt.EVENT_PROFILE_ID, str)));
                        String string = jSONObject2.getString("accessToken");
                        Intrinsics.checkExpressionValueIsNotNull(string, "toolboxResponse.getString(\"accessToken\")");
                        RightVRequestManager.getTbxEventJSONObject$default(rightVRequestManager, null, 0, eventsParams, string, 3, null).subscribe(new BiConsumer<JSONObject, Throwable>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.loginToolbox.2.1.1
                            @Override // io.reactivex.functions.BiConsumer
                            public final void accept(JSONObject jSONObject3, Throwable th) {
                                if (th == null) {
                                    Log.d(MegacableUserService.this.TAG, "TbxEvents => " + jSONObject3);
                                    return;
                                }
                                th.printStackTrace();
                            }
                        });
                    }
                }
            });
        }
    }

    public final Single<JSONObject> registerOMPToken(final String token) {
        Intrinsics.checkParameterIsNotNull(token, "token");
        Single singleFlatMap = this.accountId.flatMap((Function) new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.registerOMPToken.1
            @Override // io.reactivex.functions.Function
            public final Single<JSONObject> apply(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return RightVRequestManager.getRTEJSONObject$default(MegacableUserService.this.requestManager, "RegisterExternalToken", 0, MapsKt.mapOf(new Pair("gcm_reg_id", token), new Pair("from_serial_number", it), new Pair("gcm_app_id", MegacableUserService.this.context.getPackageName())), null, 10, null);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleFlatMap, "accountId.flatMap {\n    …)\n            )\n        }");
        return singleFlatMap;
    }

    public final Single<String> getReminderString() {
        Single map = getGetCurrentProfile().map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.getReminderString.1
            @Override // io.reactivex.functions.Function
            public final String apply(Profile it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (!it.getProperties().containsKey("Reminders")) {
                    return "";
                }
                Object obj = it.getProperties().get("Reminders");
                if (obj != null) {
                    return (String) obj;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(map, "getCurrentProfile.map {\n… String else \"\"\n        }");
        return map;
    }

    public final Single<JSONArray> getReminders() {
        Single<JSONArray> map = getReminderString().map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.getReminders.1
            @Override // io.reactivex.functions.Function
            public final String apply(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                long time = new Date().getTime();
                try {
                    JSONArray jSONArray = new JSONArray(it);
                    JSONArray jSONArray2 = new JSONArray(it);
                    int length = jSONArray.length();
                    for (int i = 0; i < length; i++) {
                        Object obj = jSONArray.get(i);
                        if (obj == null) {
                            throw new TypeCastException("null cannot be cast to non-null type org.json.JSONObject");
                        }
                        if (((JSONObject) obj).getLong("startDate") * ((long) 1000) < time) {
                            jSONArray2.remove(i);
                        }
                    }
                    return jSONArray2.toString();
                } catch (Exception unused) {
                    return "[]";
                }
            }
        }).map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.getReminders.2
            @Override // io.reactivex.functions.Function
            public final JSONArray apply(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return new JSONArray(it);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(map, "getReminderString()\n    …ONArray(it)\n            }");
        return map;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TVAiring parseReminderToTVAiring$default(MegacableUserService megacableUserService, JSONObject jSONObject, List list, int i, Object obj) {
        if ((i & 2) != 0) {
            list = (List) null;
        }
        return megacableUserService.parseReminderToTVAiring(jSONObject, list);
    }

    public final TVAiring parseReminderToTVAiring(JSONObject json, List<? extends Channel> channels) throws JSONException {
        Object next;
        Intrinsics.checkParameterIsNotNull(json, "json");
        ArrayList arrayList = new ArrayList();
        BOService boService = getBoService();
        String strOptString = json.optString("imgUrl");
        Intrinsics.checkExpressionValueIsNotNull(strOptString, "json.optString(\"imgUrl\")");
        arrayList.add(new StaticArtwork(boService, strOptString, ArtworkFormat.Still));
        LiveTVService liveTVService = getBoService().getLiveTVService();
        String string = json.getString("channelId");
        Intrinsics.checkExpressionValueIsNotNull(string, "json.getString(\"channelId\")");
        Channel channel = new Channel(liveTVService, string, "", "", null, null, null, json.getInt("zapNumber"), null, null, false, null, 3952, null);
        if (channels != null) {
            Iterator<T> it = channels.iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!Intrinsics.areEqual(((Channel) next).getId(), json.getString("channelId")));
            Channel channel2 = (Channel) next;
            if (channel2 != null) {
                channel = channel2;
            }
        }
        Channel channel3 = channel;
        BOService boService2 = getBoService();
        String string2 = json.getString("channelId");
        Intrinsics.checkExpressionValueIsNotNull(string2, "json.getString(\"channelId\")");
        String string3 = json.getString("title");
        Intrinsics.checkExpressionValueIsNotNull(string3, "json.getString(\"title\")");
        long j = 1000;
        Date date = new Date(json.getLong("startDate") * j);
        Date date2 = new Date(json.getLong("endDate") * j);
        return new TVAiring(boService2, string2, string3, null, arrayList, "megacable://com.dotscreen.megacable.portal/epg/details?channel_id=" + json.getString("channelId") + "&start=" + (json.getLong("startDate") * j), new MediaMetadata(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, ParentalRating.INSTANCE.forName(getBoService(), "RATING:" + json.optInt("prLevel", 5)), 32767, null), date, date2, channel3, null, false, false, false, 15360, null);
    }

    public final Single<JSONObject> updateReminder(String reminder) {
        Intrinsics.checkParameterIsNotNull(reminder, "reminder");
        Single<JSONObject> singleDoAfterSuccess = this.requestManager.getCompassJSONObject("UpdateProfile", 1, MapsKt.mapOf(new Pair("property", "Reminders: " + reminder))).doAfterSuccess(new Consumer<JSONObject>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.updateReminder.1
            @Override // io.reactivex.functions.Consumer
            public final void accept(JSONObject jSONObject) {
                MegacableUserService.this.cancelProfileCache();
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleDoAfterSuccess, "requestManager\n         …fileCache()\n            }");
        return singleDoAfterSuccess;
    }

    public final Completable openVODPlaybackSession(final Media media) {
        Intrinsics.checkParameterIsNotNull(media, "media");
        if ((media instanceof Movie) || (media instanceof Episode)) {
            Completable completableFlatMapCompletable = media.getOffers().toList().onErrorReturn(new Function<Throwable, List<Offer>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.openVODPlaybackSession.1
                @Override // io.reactivex.functions.Function
                public final List<Offer> apply(Throwable it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return CollectionsKt.emptyList();
                }
            }).flatMapCompletable(new Function<List<Offer>, CompletableSource>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.openVODPlaybackSession.2
                @Override // io.reactivex.functions.Function
                public final CompletableSource apply(List<Offer> it) {
                    T t;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Offer offer = (Offer) CollectionsKt.first((List) it);
                    if (offer != null && offer.getType() == OfferType.CatchupTV) {
                        return Completable.complete();
                    }
                    final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                    try {
                        RightvContentIdData rightvContentIdDataExtractContentId = MegacableUtilsKt.extractContentId(media.getId());
                        if (rightvContentIdDataExtractContentId == null || (t = (T) rightvContentIdDataExtractContentId.getContentId()) == null) {
                            t = (T) media.getId();
                        }
                    } catch (Exception unused) {
                        t = (T) media.getId();
                    }
                    objectRef.element = t;
                    LiveTVService liveTVService = MegacableUserService.this.getBoService().getLiveTVService();
                    if (liveTVService != null) {
                        return ((MegacableLiveTVService) liveTVService).getModelExternalId().flatMapCompletable(new Function<String, CompletableSource>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.openVODPlaybackSession.2.1
                            @Override // io.reactivex.functions.Function
                            public final Completable apply(String modelExternalId) {
                                Intrinsics.checkParameterIsNotNull(modelExternalId, "modelExternalId");
                                return RightVRequestManager.getSessionManagementJSONObject$default(MegacableUserService.this.requestManager, "openSession", 0, MapsKt.mapOf(TuplesKt.to("type", VOPlaybackSessionReport.SESSION_TYPE_VOD), TuplesKt.to("deviceId", MegacableUserService.this.getSerialNumber()), TuplesKt.to("contentId", (String) objectRef.element), TuplesKt.to("deviceModelId", modelExternalId)), 2, null).flatMapCompletable(new Function<JSONObject, CompletableSource>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.openVODPlaybackSession.2.1.1
                                    @Override // io.reactivex.functions.Function
                                    public final CompletableSource apply(JSONObject it2) {
                                        Intrinsics.checkParameterIsNotNull(it2, "it");
                                        Exception exc = (Exception) null;
                                        try {
                                            try {
                                                it2.getString("id");
                                            } catch (Exception unused2) {
                                                exc = new Exception(it2.getString("errorCode"));
                                            }
                                        } catch (Exception e) {
                                            exc = e;
                                        }
                                        if (exc == null) {
                                            return Completable.complete();
                                        }
                                        return Completable.error(exc);
                                    }
                                });
                            }
                        });
                    }
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableLiveTVService");
                }
            });
            Intrinsics.checkExpressionValueIsNotNull(completableFlatMapCompletable, "media.offers.toList().on…      }\n                }");
            return completableFlatMapCompletable;
        }
        Completable completableComplete = Completable.complete();
        Intrinsics.checkExpressionValueIsNotNull(completableComplete, "Completable.complete()");
        return completableComplete;
    }

    public final Completable closeVODPlaybackSession(final Media media) {
        Intrinsics.checkParameterIsNotNull(media, "media");
        if ((media instanceof Movie) || (media instanceof Episode)) {
            Completable completableFlatMapCompletable = media.getOffers().toList().onErrorReturn(new Function<Throwable, List<Offer>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.closeVODPlaybackSession.1
                @Override // io.reactivex.functions.Function
                public final List<Offer> apply(Throwable it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return CollectionsKt.emptyList();
                }
            }).flatMapCompletable(new Function<List<Offer>, CompletableSource>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.closeVODPlaybackSession.2
                @Override // io.reactivex.functions.Function
                public final CompletableSource apply(List<Offer> it) {
                    T t;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Offer offer = (Offer) CollectionsKt.first((List) it);
                    if (offer != null && offer.getType() == OfferType.CatchupTV) {
                        return Completable.complete();
                    }
                    final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                    try {
                        RightvContentIdData rightvContentIdDataExtractContentId = MegacableUtilsKt.extractContentId(media.getId());
                        if (rightvContentIdDataExtractContentId == null || (t = (T) rightvContentIdDataExtractContentId.getContentId()) == null) {
                            t = (T) media.getId();
                        }
                    } catch (Exception unused) {
                        t = (T) media.getId();
                    }
                    objectRef.element = t;
                    LiveTVService liveTVService = MegacableUserService.this.getBoService().getLiveTVService();
                    if (liveTVService != null) {
                        return ((MegacableLiveTVService) liveTVService).getModelExternalId().flatMapCompletable(new Function<String, CompletableSource>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.closeVODPlaybackSession.2.1
                            @Override // io.reactivex.functions.Function
                            public final Completable apply(String modelExternalId) {
                                Intrinsics.checkParameterIsNotNull(modelExternalId, "modelExternalId");
                                return RightVRequestManager.getSessionManagementJSONObject$default(MegacableUserService.this.requestManager, "closeSession", 0, MapsKt.mapOf(TuplesKt.to("type", VOPlaybackSessionReport.SESSION_TYPE_VOD), TuplesKt.to("deviceId", MegacableUserService.this.getSerialNumber()), TuplesKt.to("contentId", (String) objectRef.element), TuplesKt.to("deviceModelId", modelExternalId)), 2, null).ignoreElement();
                            }
                        });
                    }
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableLiveTVService");
                }
            });
            Intrinsics.checkExpressionValueIsNotNull(completableFlatMapCompletable, "media.offers.toList().on…     }\n\n                }");
            return completableFlatMapCompletable;
        }
        Completable completableComplete = Completable.complete();
        Intrinsics.checkExpressionValueIsNotNull(completableComplete, "Completable.complete()");
        return completableComplete;
    }

    public final Observable<Content> searchSuggestion(final String input) {
        Intrinsics.checkParameterIsNotNull(input, "input");
        Observable<Content> observableFlatMapObservable = getActiveUser().flatMapSingle((Function) new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.searchSuggestion.1
            @Override // io.reactivex.functions.Function
            public final Single<JSONArray> apply(User it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return RightVRequestManager.getSearchJSONArray$default(MegacableUserService.this.requestManager, "SearchSuggest", 0, MapsKt.mapOf(TuplesKt.to("text", input), TuplesKt.to("max_results", 100), TuplesKt.to("entity_type", ToolboxAnalyticsModuleKt.KEY_CONTENT), TuplesKt.to("max_pr_level", 5)), 2, null);
            }
        }).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.searchSuggestion.2
            @Override // io.reactivex.functions.Function
            public final Observable<Content> apply(final JSONArray suggestions) {
                Intrinsics.checkParameterIsNotNull(suggestions, "suggestions");
                return Observable.create(new ObservableOnSubscribe<T>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.searchSuggestion.2.1
                    /* JADX WARN: Code duplicated, block: B:27:0x015f  */
                    @Override // io.reactivex.ObservableOnSubscribe
                    public final void subscribe(ObservableEmitter<Content> it) throws JSONException {
                        Content searchMovieContent;
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        int length = suggestions.length();
                        for (int i = 0; i < length; i++) {
                            JSONObject jSONObject = suggestions.getJSONObject(i);
                            String string = jSONObject.getString("contentType");
                            final String str = !Intrinsics.areEqual(string, "program") ? "rightv:" + string + ':' + jSONObject.getString("externalId") : jSONObject.optString("externalChannelId") + '#' + jSONObject.optString("broadcastStartTime");
                            final String title = jSONObject.getString("name");
                            String string2 = jSONObject.getString("entityType");
                            final String searchId = jSONObject.getString("id");
                            MediaMetadata mediaMetadata = new MediaMetadata(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, ParentalRating.INSTANCE.forName(MegacableUserService.this.getBoService(), "RATING:" + jSONObject.optInt("prLevel", 5)), 32767, null);
                            if (string2 == null) {
                                BOService boService = MegacableUserService.this.getBoService();
                                Intrinsics.checkExpressionValueIsNotNull(title, "title");
                                Intrinsics.checkExpressionValueIsNotNull(searchId, "searchId");
                                searchMovieContent = new SearchMovieContent(boService, str, title, mediaMetadata, searchId, null, 32, null);
                            } else {
                                int iHashCode = string2.hashCode();
                                if (iHashCode != -1544438277) {
                                    if (iHashCode != -906335517) {
                                        if (iHashCode == -309387644 && string2.equals("program")) {
                                            final BOService boService2 = MegacableUserService.this.getBoService();
                                            Intrinsics.checkExpressionValueIsNotNull(title, "title");
                                            searchMovieContent = new LightSearchTVProgram(boService2, str, title) { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$searchSuggestion$2$1$content$1
                                                @Override // com.dotscreen.tv.common.bokit.LightSearchTVProgram, com.dotscreen.tv.common.bokit.SearchableContent
                                                /* JADX INFO: renamed from: getSearchId */
                                                public String getContentSearchId() {
                                                    String searchId2 = searchId;
                                                    Intrinsics.checkExpressionValueIsNotNull(searchId2, "searchId");
                                                    return searchId2;
                                                }
                                            };
                                        } else {
                                            BOService boService3 = MegacableUserService.this.getBoService();
                                            Intrinsics.checkExpressionValueIsNotNull(title, "title");
                                            Intrinsics.checkExpressionValueIsNotNull(searchId, "searchId");
                                            searchMovieContent = new SearchMovieContent(boService3, str, title, mediaMetadata, searchId, null, 32, null);
                                        }
                                    } else if (string2.equals("season")) {
                                        BOService boService4 = MegacableUserService.this.getBoService();
                                        Intrinsics.checkExpressionValueIsNotNull(title, "title");
                                        Intrinsics.checkExpressionValueIsNotNull(searchId, "searchId");
                                        searchMovieContent = new SearchSeriesContent(boService4, str, title, mediaMetadata, searchId, null, 32, null);
                                    } else {
                                        BOService boService5 = MegacableUserService.this.getBoService();
                                        Intrinsics.checkExpressionValueIsNotNull(title, "title");
                                        Intrinsics.checkExpressionValueIsNotNull(searchId, "searchId");
                                        searchMovieContent = new SearchMovieContent(boService5, str, title, mediaMetadata, searchId, null, 32, null);
                                    }
                                } else if (string2.equals("episode")) {
                                    BOService boService6 = MegacableUserService.this.getBoService();
                                    Intrinsics.checkExpressionValueIsNotNull(title, "title");
                                    Intrinsics.checkExpressionValueIsNotNull(searchId, "searchId");
                                    searchMovieContent = new SearchEpisodeContent(boService6, str, title, mediaMetadata, searchId, null, 32, null);
                                } else {
                                    BOService boService7 = MegacableUserService.this.getBoService();
                                    Intrinsics.checkExpressionValueIsNotNull(title, "title");
                                    Intrinsics.checkExpressionValueIsNotNull(searchId, "searchId");
                                    searchMovieContent = new SearchMovieContent(boService7, str, title, mediaMetadata, searchId, null, 32, null);
                                }
                            }
                            it.onNext(searchMovieContent);
                        }
                        it.onComplete();
                    }
                });
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(observableFlatMapObservable, "activeUser.flatMapSingle…)\n            }\n        }");
        return observableFlatMapObservable;
    }

    public final Completable registerSearchEvent(SearchableContent searchMedia) {
        Intrinsics.checkParameterIsNotNull(searchMedia, "searchMedia");
        Completable completableDoOnError = RightVRequestManager.getSearchJSONObject$default(this.requestManager, "RegisterSearchEvent", 0, MapsKt.mapOf(TuplesKt.to("entity_type", searchMedia.getEntityType()), TuplesKt.to("search_result_id", searchMedia.getContentSearchId())), 2, null).ignoreElement().doOnTerminate(new Action() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.registerSearchEvent.1
            @Override // io.reactivex.functions.Action
            public final void run() {
                Log.d("CompassAnalytics", "RegisterSearchEvent Done");
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.registerSearchEvent.2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Log.d("CompassAnalytics", "RegisterSearchEvent Failed " + th.getMessage());
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(completableDoOnError, "requestManager.getSearch… Failed ${it.message}\") }");
        return completableDoOnError;
    }

    public final Completable registerSearchEvent(String entityType, String searchResultId) {
        Intrinsics.checkParameterIsNotNull(entityType, "entityType");
        Intrinsics.checkParameterIsNotNull(searchResultId, "searchResultId");
        Completable completableDoOnError = RightVRequestManager.getSearchJSONObject$default(this.requestManager, "RegisterSearchEvent", 0, MapsKt.mapOf(TuplesKt.to("entity_type", entityType), TuplesKt.to("search_result_id", searchResultId)), 2, null).ignoreElement().doOnTerminate(new Action() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.registerSearchEvent.3
            @Override // io.reactivex.functions.Action
            public final void run() {
                Log.d("CompassAnalytics", "RegisterSearchEvent Done");
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.registerSearchEvent.4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Log.d("CompassAnalytics", "RegisterSearchEvent Failed " + th.getMessage());
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(completableDoOnError, "requestManager.getSearch… Failed ${it.message}\") }");
        return completableDoOnError;
    }

    public final Single<String> checkUIOptimizerStatus(String type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        return this.requestManager.getUIOPStatus(type);
    }

    public final Single<String> checkRighTVStatus() {
        return this.requestManager.getRTEStatus();
    }

    public final Single<String> checkCompassStatus() {
        return this.requestManager.getCompassStatus();
    }

    public final Single<BackendReachability> backendIsReachable() {
        Single<BackendReachability> singleDelaySubscription = Single.concat(checkUIOptimizerStatus("readiness").map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.backendIsReachable.1
            @Override // io.reactivex.functions.Function
            public final BackendReachability.BackendStatus apply(String response) {
                Intrinsics.checkParameterIsNotNull(response, "response");
                return new BackendReachability.BackendStatus("uiop", response, null, 4, null);
            }
        }).onErrorReturn(new Function<Throwable, BackendReachability.BackendStatus>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.backendIsReachable.2
            @Override // io.reactivex.functions.Function
            public final BackendReachability.BackendStatus apply(Throwable err) {
                Intrinsics.checkParameterIsNotNull(err, "err");
                return new BackendReachability.BackendStatus("uiop", null, err, 2, null);
            }
        }), checkRighTVStatus().map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.backendIsReachable.3
            @Override // io.reactivex.functions.Function
            public final BackendReachability.BackendStatus apply(String response) {
                Intrinsics.checkParameterIsNotNull(response, "response");
                return new BackendReachability.BackendStatus("rightv", response, null, 4, null);
            }
        }).onErrorReturn(new Function<Throwable, BackendReachability.BackendStatus>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.backendIsReachable.4
            @Override // io.reactivex.functions.Function
            public final BackendReachability.BackendStatus apply(Throwable err) {
                Intrinsics.checkParameterIsNotNull(err, "err");
                return new BackendReachability.BackendStatus("rightv", null, err, 2, null);
            }
        }), checkCompassStatus().map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.backendIsReachable.5
            @Override // io.reactivex.functions.Function
            public final BackendReachability.BackendStatus apply(String response) {
                Intrinsics.checkParameterIsNotNull(response, "response");
                return new BackendReachability.BackendStatus("compass", response, null, 4, null);
            }
        }).onErrorReturn(new Function<Throwable, BackendReachability.BackendStatus>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.backendIsReachable.6
            @Override // io.reactivex.functions.Function
            public final BackendReachability.BackendStatus apply(Throwable err) {
                Intrinsics.checkParameterIsNotNull(err, "err");
                return new BackendReachability.BackendStatus("compass", null, err, 2, null);
            }
        })).takeUntil(new Predicate<BackendReachability.BackendStatus>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.backendIsReachable.7
            @Override // io.reactivex.functions.Predicate
            public final boolean test(BackendReachability.BackendStatus it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getError() != null;
            }
        }).toList().map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.backendIsReachable.8
            @Override // io.reactivex.functions.Function
            public final BackendReachability apply(List<BackendReachability.BackendStatus> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Log.v(MegacableUserService.this.TAG, "BackendReachability: " + it);
                return new BackendReachability(it);
            }
        }).delaySubscription(2L, TimeUnit.SECONDS);
        Intrinsics.checkExpressionValueIsNotNull(singleDelaySubscription, "Single.concat(\n         …tion(2, TimeUnit.SECONDS)");
        return singleDelaySubscription;
    }

    public final Completable notifyApplicationsUpdated() {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        Completable notifyRightVCompletable = login$default(this, false, 1, null).flatMap(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$notifyApplicationsUpdated$notifyRightVCompletable$1
            @Override // io.reactivex.functions.Function
            public final Single<JSONObject> apply(JSONObject it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return RightVRequestManager.getRTEJSONObject$default(this.this$0.requestManager, "SetClientData", 0, MapsKt.mapOf(TuplesKt.to("client_code", this.this$0.getSerialNumber()), TuplesKt.to("key", "Apk_Version"), TuplesKt.to("value", MegacableUserServiceKt.getAppsVersion(this.this$0.context))), null, 10, null).onErrorReturn(new Function<Throwable, JSONObject>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService$notifyApplicationsUpdated$notifyRightVCompletable$1.1
                    @Override // io.reactivex.functions.Function
                    public final JSONObject apply(Throwable it2) {
                        Intrinsics.checkParameterIsNotNull(it2, "it");
                        return new JSONObject();
                    }
                });
            }
        }).ignoreElement();
        try {
            str = this.context.getPackageManager().getPackageInfo("com.dotscreen.megacable.portal", 0).versionName;
        } catch (Exception unused) {
            str = null;
        }
        try {
            str2 = this.context.getPackageManager().getPackageInfo("com.dotscreen.megacable.livetv", 0).versionName;
        } catch (Exception unused2) {
            str2 = null;
        }
        try {
            str3 = this.context.getPackageManager().getPackageInfo(BuildConfig.UPDATER_PACKAGE_NAME, 0).versionName;
        } catch (Exception unused3) {
            str3 = null;
        }
        try {
            str4 = this.context.getPackageManager().getPackageInfo(BuildConfig.CORE_PACKAGE_NAME, 0).versionName;
        } catch (Exception unused4) {
            str4 = null;
        }
        try {
            str5 = this.context.getPackageManager().getPackageInfo("tv.broadpeak.nanocdnwrapper", 0).versionName;
        } catch (Exception unused5) {
            str5 = null;
        }
        final String str6 = str;
        final String str7 = str2;
        final String str8 = str3;
        final String str9 = str4;
        final String str10 = str5;
        RequestManager.getJSONObject$default(this.requestManager, 0, getBoService().resolveUrl("api/megacable/v1/stb/register/" + getSerialNumber()), null, 4, null).onErrorReturn(new Function<Throwable, JSONObject>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.notifyApplicationsUpdated.1
            @Override // io.reactivex.functions.Function
            public final JSONObject apply(Throwable it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return new JSONObject();
            }
        }).flatMap(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.notifyApplicationsUpdated.2
            @Override // io.reactivex.functions.Function
            public final Single<JSONObject> apply(JSONObject json) {
                JSONObject jSONObjectOptJSONObject;
                JSONObject jSONObjectOptJSONObject2;
                JSONObject jSONObjectOptJSONObject3;
                JSONObject jSONObjectOptJSONObject4;
                JSONObject jSONObjectOptJSONObject5;
                Intrinsics.checkParameterIsNotNull(json, "json");
                JSONObject jSONObjectOptJSONObject6 = json.optJSONObject("info");
                String strOptString = null;
                String strOptString2 = (jSONObjectOptJSONObject6 == null || (jSONObjectOptJSONObject5 = jSONObjectOptJSONObject6.optJSONObject("portal")) == null) ? null : jSONObjectOptJSONObject5.optString("version", null);
                JSONObject jSONObjectOptJSONObject7 = json.optJSONObject("info");
                String strOptString3 = (jSONObjectOptJSONObject7 == null || (jSONObjectOptJSONObject4 = jSONObjectOptJSONObject7.optJSONObject("live_tv")) == null) ? null : jSONObjectOptJSONObject4.optString("version", null);
                JSONObject jSONObjectOptJSONObject8 = json.optJSONObject("info");
                String strOptString4 = (jSONObjectOptJSONObject8 == null || (jSONObjectOptJSONObject3 = jSONObjectOptJSONObject8.optJSONObject("updater")) == null) ? null : jSONObjectOptJSONObject3.optString("version", null);
                JSONObject jSONObjectOptJSONObject9 = json.optJSONObject("info");
                String strOptString5 = (jSONObjectOptJSONObject9 == null || (jSONObjectOptJSONObject2 = jSONObjectOptJSONObject9.optJSONObject("core")) == null) ? null : jSONObjectOptJSONObject2.optString("version", null);
                JSONObject jSONObjectOptJSONObject10 = json.optJSONObject("info");
                if (jSONObjectOptJSONObject10 != null && (jSONObjectOptJSONObject = jSONObjectOptJSONObject10.optJSONObject("nano_cdn")) != null) {
                    strOptString = jSONObjectOptJSONObject.optString("version", null);
                }
                Log.d(MegacableUserService.this.TAG, "lastPortalVersion = " + strOptString2);
                Log.d(MegacableUserService.this.TAG, "lastLiveTvVersion = " + strOptString3);
                Log.d(MegacableUserService.this.TAG, "lastUpdaterVersion = " + strOptString4);
                Log.d(MegacableUserService.this.TAG, "lastCoreVersion = " + strOptString5);
                Log.d(MegacableUserService.this.TAG, "lastNanoCdnVersion = " + strOptString);
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                String str11 = str6;
                if (str11 != null && (!Intrinsics.areEqual(str11, strOptString2))) {
                    linkedHashMap.put("portal_version", str6);
                }
                String str12 = str7;
                if (str12 != null && (!Intrinsics.areEqual(str12, strOptString3))) {
                    linkedHashMap.put("livetv_version", str7);
                }
                String str13 = str8;
                if (str13 != null && (!Intrinsics.areEqual(str13, strOptString4))) {
                    linkedHashMap.put("updater_version", str8);
                }
                String str14 = str9;
                if (str14 != null && (!Intrinsics.areEqual(str14, strOptString5))) {
                    linkedHashMap.put("core_version", str9);
                }
                String str15 = str10;
                if (str15 != null && (!Intrinsics.areEqual(str15, strOptString))) {
                    linkedHashMap.put("nanocdn_version", str10);
                }
                if (linkedHashMap.size() == 0) {
                    Log.d(MegacableUserService.this.TAG, "nothing to notify");
                    return Single.just(new JSONObject());
                }
                linkedHashMap.put("serial_number", MegacableUserService.this.getSerialNumber());
                Log.d(MegacableUserService.this.TAG, "params = " + linkedHashMap);
                return MegacableUserService.this.requestManager.getJSONObject(1, MegacableUserService.this.getBoService().resolveUrl("api/megacable/v1/stb/register"), linkedHashMap).onErrorReturn(new Function<Throwable, JSONObject>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.notifyApplicationsUpdated.2.1
                    @Override // io.reactivex.functions.Function
                    public final JSONObject apply(Throwable it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        return new JSONObject();
                    }
                });
            }
        }).ignoreElement();
        Intrinsics.checkExpressionValueIsNotNull(notifyRightVCompletable, "notifyRightVCompletable");
        return notifyRightVCompletable;
    }

    public final Single<String> verifyVersion() {
        return RightVRequestManager.getRTEString$default(this.requestManager, "GetClientData", 0, MapsKt.mapOf(TuplesKt.to("client_code", getSerialNumber()), TuplesKt.to("key", "Apk_Version")), 2, null);
    }

    public final Single<JSONObject> getGetTimeshiftService() {
        return this.getTimeshiftService;
    }

    public final Single<List<String>> getSubscribedChannel() {
        Single<List<String>> map = RightVRequestManager.getRTEJSONArray$default(this.requestManager, "GetHouseholdSubscribedChannelList", 0, null, 6, null).map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.getSubscribedChannel.1
            @Override // io.reactivex.functions.Function
            public final ArrayList<String> apply(JSONArray it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                ArrayList<String> arrayList = new ArrayList<>();
                int length = it.length();
                for (int i = 0; i < length; i++) {
                    if (Intrinsics.areEqual(it.getJSONObject(i).optString("responseElementType", ""), "ChannelReference")) {
                        arrayList.add(it.getJSONObject(i).optString("externalChannelId", ""));
                    }
                }
                return arrayList;
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(map, "requestManager\n         …ap retArray\n            }");
        return map;
    }

    /* JADX INFO: compiled from: MegacableUserService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/dotscreen/tv/common/bokit/MegacableUserService$NetflixPDIError;", "Ljava/lang/Exception;", "code", "", TvContractCompat.Channels.COLUMN_DESCRIPTION, "(Ljava/lang/String;Ljava/lang/String;)V", "getCode", "()Ljava/lang/String;", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class NetflixPDIError extends Exception {
        private final String code;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NetflixPDIError(String code, String description) {
            super(description);
            Intrinsics.checkParameterIsNotNull(code, "code");
            Intrinsics.checkParameterIsNotNull(description, "description");
            this.code = code;
        }

        public final String getCode() {
            return this.code;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final NetflixPDIError parseNetflixPDIError(Throwable error) throws JSONException {
        JSONObject jSONObject;
        if (!(error instanceof VolleyError) || ((VolleyError) error).networkResponse == null) {
            return null;
        }
        try {
            byte[] bArr = ((VolleyError) error).networkResponse.data;
            Intrinsics.checkExpressionValueIsNotNull(bArr, "error.networkResponse.data");
            Charset charsetForName = Charset.forName(HttpHeaderParser.parseCharset(((VolleyError) error).networkResponse.headers));
            Intrinsics.checkExpressionValueIsNotNull(charsetForName, "Charset.forName(HttpHead…networkResponse.headers))");
            jSONObject = new JSONObject(new String(bArr, charsetForName));
        } catch (Exception unused) {
            jSONObject = null;
        }
        if (jSONObject == null) {
            return null;
        }
        Log.w(this.TAG, "response = " + jSONObject);
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("Error");
        if (jSONObjectOptJSONObject == null) {
            return null;
        }
        String string = jSONObjectOptJSONObject.getString("ErrorCode");
        Intrinsics.checkExpressionValueIsNotNull(string, "jsonError.getString(\"ErrorCode\")");
        String string2 = jSONObjectOptJSONObject.getString("ErrorDescription");
        Intrinsics.checkExpressionValueIsNotNull(string2, "jsonError.getString(\"ErrorDescription\")");
        return new NetflixPDIError(string, string2);
    }

    public final Single<String> getNetflixPDI() {
        Single<String> singleOnErrorResumeNext = this.accountId.flatMap((Function) new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.getNetflixPDI.1
            @Override // io.reactivex.functions.Function
            public final Single<JSONObject> apply(String householdId) {
                Intrinsics.checkParameterIsNotNull(householdId, "householdId");
                return MegacableUserService.this.requestManager.getPADIJSONObject("getPDI", MapsKt.mapOf(TuplesKt.to("SerialNumber", MegacableUserService.this.getSerialNumber()), TuplesKt.to("HouseHold", householdId)));
            }
        }).map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.getNetflixPDI.2
            @Override // io.reactivex.functions.Function
            public final String apply(JSONObject json) {
                Intrinsics.checkParameterIsNotNull(json, "json");
                return json.getString("PDI");
            }
        }).onErrorResumeNext(new Function<Throwable, SingleSource<? extends String>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.getNetflixPDI.3
            @Override // io.reactivex.functions.Function
            public final Single<String> apply(Throwable error) throws JSONException {
                Intrinsics.checkParameterIsNotNull(error, "error");
                NetflixPDIError netflixPDIError = MegacableUserService.this.parseNetflixPDIError(error);
                if (netflixPDIError != null) {
                    error = netflixPDIError;
                }
                return Single.error(error);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleOnErrorResumeNext, "accountId.flatMap { hous…rror) ?: error)\n        }");
        return singleOnErrorResumeNext;
    }

    public final Single<String> getNetflixPADIToken(final String pdi, final String esn, final String[] intents) {
        Intrinsics.checkParameterIsNotNull(pdi, "pdi");
        Intrinsics.checkParameterIsNotNull(esn, "esn");
        Intrinsics.checkParameterIsNotNull(intents, "intents");
        Single<String> singleOnErrorResumeNext = this.accountId.flatMap((Function) new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.getNetflixPADIToken.1
            @Override // io.reactivex.functions.Function
            public final Single<JSONObject> apply(String householdId) {
                Intrinsics.checkParameterIsNotNull(householdId, "householdId");
                return MegacableUserService.this.requestManager.getPADIJSONObject("getPADIToken", MapsKt.mapOf(TuplesKt.to("PartnerDeviceIdentifier", pdi), TuplesKt.to("ESN", esn), TuplesKt.to("Intents", intents), TuplesKt.to("HouseHold", householdId)));
            }
        }).map(new Function<T, R>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.getNetflixPADIToken.2
            @Override // io.reactivex.functions.Function
            public final String apply(JSONObject json) {
                Intrinsics.checkParameterIsNotNull(json, "json");
                return json.getString("Token");
            }
        }).onErrorResumeNext(new Function<Throwable, SingleSource<? extends String>>() { // from class: com.dotscreen.tv.common.bokit.MegacableUserService.getNetflixPADIToken.3
            @Override // io.reactivex.functions.Function
            public final Single<String> apply(Throwable error) throws JSONException {
                Intrinsics.checkParameterIsNotNull(error, "error");
                NetflixPDIError netflixPDIError = MegacableUserService.this.parseNetflixPDIError(error);
                if (netflixPDIError != null) {
                    error = netflixPDIError;
                }
                return Single.error(error);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleOnErrorResumeNext, "accountId.flatMap { hous…rror) ?: error)\n        }");
        return singleOnErrorResumeNext;
    }
}
