package com.dotscreen.farkit.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;
import androidx.exifinterface.media.ExifInterface;
import com.android.volley.ExecutorDelivery;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BaseHttpStack;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.dotscreen.farkit.network.internal.BitmapLruCache;
import com.dotscreen.farkit.network.internal.LocalImageCache;
import com.dotscreen.farkit.network.internal.VolleyRequest;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.Cancellable;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: RequestManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0016\u0018\u0000 92\u00020\u0001:\u00059:;<=B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0012J\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$H\u0004JH\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'2\u0006\u0010%\u001a\u00020$2\u0006\u0010)\u001a\u00020*2\b\b\u0002\u0010+\u001a\u00020$2\b\b\u0002\u0010,\u001a\u00020$2\u0016\b\u0002\u0010-\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010.J4\u0010/\u001a\b\u0012\u0004\u0012\u0002000'2\u0006\u0010%\u001a\u00020$2\u0006\u0010)\u001a\u00020*2\u0016\b\u0002\u0010-\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010.J4\u00101\u001a\b\u0012\u0004\u0012\u0002020'2\u0006\u0010%\u001a\u00020$2\u0006\u0010)\u001a\u00020*2\u0016\b\u0002\u0010-\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010.J4\u00103\u001a\b\u0012\u0004\u0012\u0002040'2\u0006\u0010%\u001a\u00020$2\u0006\u0010)\u001a\u00020*2\u0016\b\u0002\u0010-\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010.J4\u00105\u001a\b\u0012\u0004\u0012\u00020\u00050'2\u0006\u0010%\u001a\u00020$2\u0006\u0010)\u001a\u00020*2\u0016\b\u0002\u0010-\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010.J\u000e\u00106\u001a\u00020!2\u0006\u0010)\u001a\u00020*J\u0010\u00107\u001a\u00020\u001c2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u000e\u00108\u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0012R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\"\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\u00020\u001cX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u0006>"}, d2 = {"Lcom/dotscreen/farkit/network/RequestManager;", "", "context", "Landroid/content/Context;", "cachePath", "", "sharedCookies", "", "(Landroid/content/Context;Ljava/lang/String;Z)V", "getContext", "()Landroid/content/Context;", "<set-?>", "Ljava/net/CookieManager;", "cookieManager", "getCookieManager", "()Ljava/net/CookieManager;", "errorListeners", "", "Lcom/dotscreen/farkit/network/RequestManager$OnRequestErrorListener;", "forceNoConnection", "getForceNoConnection", "()Z", "setForceNoConnection", "(Z)V", "imageLoader", "Lcom/android/volley/toolbox/ImageLoader;", "localImageLoader", "volleyImageRequestQueue", "Lcom/android/volley/RequestQueue;", "volleyRequestQueue", "getVolleyRequestQueue", "()Lcom/android/volley/RequestQueue;", "addOnRequestErrorListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "convertMethod", "", "method", "getBitmap", "Lio/reactivex/Single;", "Landroid/graphics/Bitmap;", ImagesContract.URL, "Ljava/net/URL;", ToolboxAnalyticsModuleKt.M_WIDTH, ToolboxAnalyticsModuleKt.M_HEIGHT, "params", "", "getBytes", "", "getJSONArray", "Lorg/json/JSONArray;", "getJSONObject", "Lorg/json/JSONObject;", "getString", "invalidateCache", "newRequestQueue", "removeOnRequestErrorListener", "Companion", "ForcedNoConnectionError", "MyCookieStore", "OnRequestErrorListener", "VolleyResponseObserverWrapper", "farkit_release"}, k = 1, mv = {1, 1, 16})
public class RequestManager {
    public static final int DELETE = 3;
    public static final int GET = 0;
    public static final int POST = 1;
    public static final int PUT = 2;
    private final String cachePath;
    private final Context context;
    private CookieManager cookieManager;
    private List<OnRequestErrorListener> errorListeners;
    private boolean forceNoConnection;
    private final ImageLoader imageLoader;
    private final ImageLoader localImageLoader;
    private final boolean sharedCookies;
    private final RequestQueue volleyImageRequestQueue;
    private final RequestQueue volleyRequestQueue;
    private static final String TAG = RequestManager.class.getSimpleName();

    /* JADX INFO: compiled from: RequestManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/dotscreen/farkit/network/RequestManager$ForcedNoConnectionError;", "Lcom/android/volley/NoConnectionError;", "()V", "farkit_release"}, k = 1, mv = {1, 1, 16})
    public static final class ForcedNoConnectionError extends NoConnectionError {
    }

    /* JADX INFO: compiled from: RequestManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\b"}, d2 = {"Lcom/dotscreen/farkit/network/RequestManager$OnRequestErrorListener;", "", "onRequestError", "", "error", "", ImagesContract.URL, "Ljava/net/URL;", "farkit_release"}, k = 1, mv = {1, 1, 16})
    public interface OnRequestErrorListener {
        void onRequestError(Throwable error, URL url);
    }

    public RequestManager(Context context) {
        this(context, null, false, 6, null);
    }

    public RequestManager(Context context, String str) {
        this(context, str, false, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int convertMethod(int method) {
        int i = 1;
        if (method != 1) {
            i = 2;
            if (method != 2) {
                i = 3;
                if (method != 3) {
                    return 0;
                }
            }
        }
        return i;
    }

    public RequestManager(Context context, String str, boolean z) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.cachePath = str;
        this.sharedCookies = z;
        CookieManager cookieManager = new CookieManager(new MyCookieStore(this.context, this.sharedCookies), CookiePolicy.ACCEPT_ALL);
        this.cookieManager = cookieManager;
        CookieHandler.setDefault(cookieManager);
        this.volleyRequestQueue = newRequestQueue(this.context);
        this.volleyImageRequestQueue = newRequestQueue(this.context);
        this.imageLoader = new ImageLoader(this.volleyImageRequestQueue, new BitmapLruCache(this.context));
        this.localImageLoader = new ImageLoader(this.volleyImageRequestQueue, new LocalImageCache(this.context));
    }

    public /* synthetic */ RequestManager(Context context, String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? (String) null : str, (i & 4) != 0 ? false : z);
    }

    public final Context getContext() {
        return this.context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final RequestQueue getVolleyRequestQueue() {
        return this.volleyRequestQueue;
    }

    public final CookieManager getCookieManager() {
        return this.cookieManager;
    }

    public final boolean getForceNoConnection() {
        return this.forceNoConnection;
    }

    public final void setForceNoConnection(boolean z) {
        this.forceNoConnection = z;
    }

    /* JADX INFO: compiled from: RequestManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001:\u0001\"B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001c\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0019\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u001c2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0096\u0002J\u000e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001a0\u001cH\u0016J\u000e\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00180\u001cH\u0016J\u001c\u0010\u001f\u001a\u00020\u00052\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010 \u001a\u00020\u0005H\u0016J\u0012\u0010!\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n \u000e*\u0004\u0018\u00010\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\u00100\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0013\u001a\n \u000e*\u0004\u0018\u00010\u00140\u0014X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/dotscreen/farkit/network/RequestManager$MyCookieStore;", "Ljava/net/CookieStore;", "ctx", "Landroid/content/Context;", "sharedCookies", "", "(Landroid/content/Context;Z)V", "ACTION_COOKIE_ADDED", "", "DEBUG", "KEY_EXTRA_COOKIES", "KEY_EXTRA_URI", "context", "cookieStore", "kotlin.jvm.PlatformType", "gson", "Lcom/google/gson/Gson;", "getSharedCookies", "()Z", "sharedPreferences", "Landroid/content/SharedPreferences;", "add", "", "uri", "Ljava/net/URI;", "cookie", "Ljava/net/HttpCookie;", "get", "", "getCookies", "getURIs", "remove", "removeAll", "updateCookies", "CookieChangedReceiver", "farkit_release"}, k = 1, mv = {1, 1, 16})
    private static final class MyCookieStore implements CookieStore {
        private final String ACTION_COOKIE_ADDED;
        private final boolean DEBUG;
        private final String KEY_EXTRA_COOKIES;
        private final String KEY_EXTRA_URI;
        private final Context context;
        private final CookieStore cookieStore;
        private final Gson gson;
        private final boolean sharedCookies;
        private final SharedPreferences sharedPreferences;

        public MyCookieStore(Context ctx, boolean z) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            this.sharedCookies = z;
            this.ACTION_COOKIE_ADDED = "com.dotscreen.farkit.network.ACTION_COOKIE_ADDED";
            this.KEY_EXTRA_URI = "extra_uri";
            this.KEY_EXTRA_COOKIES = "extra_cookie";
            this.context = ctx;
            this.cookieStore = new CookieManager().getCookieStore();
            this.sharedPreferences = this.context.getSharedPreferences("FARKit-RequestManager-Cookies", 0);
            this.gson = new GsonBuilder().create();
            if (this.DEBUG) {
                Log.v(RequestManager.TAG, "MyCookieStore::init, context = " + this.context + ", sharedCookies = " + this.sharedCookies, new Exception("stack"));
            }
            SharedPreferences sharedPreferences = this.sharedPreferences;
            Intrinsics.checkExpressionValueIsNotNull(sharedPreferences, "sharedPreferences");
            Map<String, ?> all = sharedPreferences.getAll();
            Intrinsics.checkExpressionValueIsNotNull(all, "sharedPreferences.all");
            for (Map.Entry<String, ?> entry : all.entrySet()) {
                if (entry.getValue() != null) {
                    try {
                        Object value = entry.getValue();
                        if (value == null) {
                            Intrinsics.throwNpe();
                        }
                        if (value == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Set<kotlin.String>");
                        }
                        Set set = (Set) value;
                        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(set, 10));
                        Iterator it = set.iterator();
                        while (it.hasNext()) {
                            arrayList.add((HttpCookie) this.gson.fromJson((String) it.next(), HttpCookie.class));
                        }
                        ArrayList arrayList2 = arrayList;
                        if (this.DEBUG) {
                            Log.v(RequestManager.TAG, "MyCookieStore::init, added saved cookies for \"" + entry.getKey() + "\": " + arrayList2);
                        }
                        Iterator it2 = arrayList2.iterator();
                        while (it2.hasNext()) {
                            this.cookieStore.add(new URI(entry.getKey()), (HttpCookie) it2.next());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (this.sharedCookies) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(this.ACTION_COOKIE_ADDED);
                if (this.DEBUG) {
                    Log.v(RequestManager.TAG, "MyCookieStore::init, registerReceiver with " + intentFilter);
                }
                this.context.registerReceiver(new CookieChangedReceiver(), intentFilter);
            }
        }

        public final boolean getSharedCookies() {
            return this.sharedCookies;
        }

        /* JADX INFO: compiled from: RequestManager.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"Lcom/dotscreen/farkit/network/RequestManager$MyCookieStore$CookieChangedReceiver;", "Landroid/content/BroadcastReceiver;", "(Lcom/dotscreen/farkit/network/RequestManager$MyCookieStore;)V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "farkit_release"}, k = 1, mv = {1, 1, 16})
        private final class CookieChangedReceiver extends BroadcastReceiver {
            public CookieChangedReceiver() {
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (MyCookieStore.this.DEBUG) {
                    Log.v(RequestManager.TAG, "MyCookieStore::CookieChangedReceiver, onReceive = " + intent);
                }
                if (intent == null || !Intrinsics.areEqual(intent.getAction(), MyCookieStore.this.ACTION_COOKIE_ADDED)) {
                    return;
                }
                String stringExtra = intent.getStringExtra(MyCookieStore.this.KEY_EXTRA_URI);
                String[] extraCookies = intent.getStringArrayExtra(MyCookieStore.this.KEY_EXTRA_COOKIES);
                if (MyCookieStore.this.DEBUG) {
                    Log.v(RequestManager.TAG, "MyCookieStore::CookieChangedReceiver, uri = " + stringExtra);
                }
                if (MyCookieStore.this.DEBUG) {
                    Log.v(RequestManager.TAG, "MyCookieStore::CookieChangedReceiver, cookies = " + extraCookies);
                }
                SharedPreferences.Editor editorEdit = MyCookieStore.this.sharedPreferences.edit();
                Intrinsics.checkExpressionValueIsNotNull(extraCookies, "extraCookies");
                editorEdit.putStringSet(stringExtra, ArraysKt.toSet(extraCookies)).apply();
                ArrayList<HttpCookie> arrayList = new ArrayList(extraCookies.length);
                for (String str : extraCookies) {
                    arrayList.add((HttpCookie) MyCookieStore.this.gson.fromJson(str, HttpCookie.class));
                }
                for (HttpCookie httpCookie : arrayList) {
                    if (MyCookieStore.this.DEBUG) {
                        Log.v(RequestManager.TAG, "MyCookieStore::CookieChangedReceiver, add \"" + stringExtra + "\" -> \"" + httpCookie + Typography.quote);
                    }
                    MyCookieStore.this.cookieStore.add(new URI(stringExtra), httpCookie);
                }
            }
        }

        private final void updateCookies(URI uri) {
            if (uri != null) {
                URI uri2 = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), null, null, null);
                List<HttpCookie> cookies = this.cookieStore.get(uri);
                Intrinsics.checkExpressionValueIsNotNull(cookies, "cookies");
                List<HttpCookie> list = cookies;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(this.gson.toJson((HttpCookie) it.next()));
                }
                Set<String> set = CollectionsKt.toSet(arrayList);
                if (this.DEBUG) {
                    Log.v(RequestManager.TAG, "MyCookieStore::updateCookies -> set(" + uri2 + ") = " + set);
                }
                this.sharedPreferences.edit().putStringSet(uri2.toString(), set).apply();
                if (this.sharedCookies) {
                    Intent intent = new Intent(this.ACTION_COOKIE_ADDED);
                    intent.putExtra(this.KEY_EXTRA_URI, uri2.toString());
                    String str = this.KEY_EXTRA_COOKIES;
                    Object[] array = set.toArray(new String[0]);
                    if (array != null) {
                        intent.putExtra(str, (String[]) array);
                        if (this.DEBUG) {
                            Log.v(RequestManager.TAG, "MyCookieStore::updateCookies -> sendBroadcast " + intent);
                        }
                        this.context.sendBroadcast(intent);
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                }
            }
        }

        @Override // java.net.CookieStore
        public List<HttpCookie> getCookies() {
            CookieStore cookieStore = this.cookieStore;
            Intrinsics.checkExpressionValueIsNotNull(cookieStore, "cookieStore");
            List<HttpCookie> ret = cookieStore.getCookies();
            if (this.DEBUG) {
                Log.v(RequestManager.TAG, "MyCookieStore:getCookies() -> " + ret);
            }
            Intrinsics.checkExpressionValueIsNotNull(ret, "ret");
            return ret;
        }

        @Override // java.net.CookieStore
        public List<URI> getURIs() {
            CookieStore cookieStore = this.cookieStore;
            Intrinsics.checkExpressionValueIsNotNull(cookieStore, "cookieStore");
            List<URI> ret = cookieStore.getURIs();
            if (this.DEBUG) {
                Log.v(RequestManager.TAG, "MyCookieStore:getURIs() -> " + ret);
            }
            Intrinsics.checkExpressionValueIsNotNull(ret, "ret");
            return ret;
        }

        @Override // java.net.CookieStore
        public List<HttpCookie> get(URI uri) {
            List<HttpCookie> ret = this.cookieStore.get(uri);
            if (this.DEBUG) {
                Log.v(RequestManager.TAG, "MyCookieStore:get(" + uri + ") -> " + ret);
            }
            Intrinsics.checkExpressionValueIsNotNull(ret, "ret");
            return ret;
        }

        @Override // java.net.CookieStore
        public void add(URI uri, HttpCookie cookie) {
            if (this.DEBUG) {
                Log.v(RequestManager.TAG, "MyCookieStore:add(" + uri + ", " + cookie + ')');
            }
            this.cookieStore.add(uri, cookie);
            updateCookies(uri);
        }

        @Override // java.net.CookieStore
        public boolean remove(URI uri, HttpCookie cookie) {
            boolean zRemove = this.cookieStore.remove(uri, cookie);
            if (this.DEBUG) {
                Log.v(RequestManager.TAG, "MyCookieStore:remove(" + uri + ", " + cookie + ") -> " + zRemove);
            }
            if (zRemove) {
                updateCookies(uri);
            }
            return zRemove;
        }

        @Override // java.net.CookieStore
        public boolean removeAll() {
            boolean zRemoveAll = this.cookieStore.removeAll();
            if (this.DEBUG) {
                Log.v(RequestManager.TAG, "MyCookieStore:removeAll() -> " + zRemoveAll);
            }
            if (zRemoveAll) {
                this.sharedPreferences.edit().clear().apply();
            }
            return zRemoveAll;
        }
    }

    public final void invalidateCache(URL url) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        this.volleyRequestQueue.getCache().invalidate(url.toString(), true);
    }

    /* JADX INFO: compiled from: RequestManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0004\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003B!\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0015\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0015R\"\u0010\u0004\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0016"}, d2 = {"Lcom/dotscreen/farkit/network/RequestManager$VolleyResponseObserverWrapper;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/android/volley/Response$Listener;", "Lcom/android/volley/Response$ErrorListener;", "observer", "Lio/reactivex/SingleEmitter;", ImagesContract.URL, "Ljava/net/URL;", "(Lcom/dotscreen/farkit/network/RequestManager;Lio/reactivex/SingleEmitter;Ljava/net/URL;)V", "getObserver$farkit_release", "()Lio/reactivex/SingleEmitter;", "setObserver$farkit_release", "(Lio/reactivex/SingleEmitter;)V", "getUrl", "()Ljava/net/URL;", "onErrorResponse", "", "error", "Lcom/android/volley/VolleyError;", "onResponse", "response", "(Ljava/lang/Object;)V", "farkit_release"}, k = 1, mv = {1, 1, 16})
    public final class VolleyResponseObserverWrapper<T> implements Response.Listener<T>, Response.ErrorListener {
        private SingleEmitter<T> observer;
        private final URL url;

        public VolleyResponseObserverWrapper(SingleEmitter<T> singleEmitter, URL url) {
            this.observer = singleEmitter;
            this.url = url;
        }

        public /* synthetic */ VolleyResponseObserverWrapper(RequestManager requestManager, SingleEmitter singleEmitter, URL url, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(singleEmitter, (i & 2) != 0 ? (URL) null : url);
        }

        public final SingleEmitter<T> getObserver$farkit_release() {
            return this.observer;
        }

        public final URL getUrl() {
            return this.url;
        }

        public final void setObserver$farkit_release(SingleEmitter<T> singleEmitter) {
            this.observer = singleEmitter;
        }

        @Override // com.android.volley.Response.ErrorListener
        public void onErrorResponse(VolleyError error) {
            Intrinsics.checkParameterIsNotNull(error, "error");
            SingleEmitter<T> singleEmitter = this.observer;
            if (singleEmitter != null) {
                if (singleEmitter == null) {
                    Intrinsics.throwNpe();
                }
                if (!singleEmitter.isDisposed()) {
                    SingleEmitter<T> singleEmitter2 = this.observer;
                    if (singleEmitter2 == null) {
                        Intrinsics.throwNpe();
                    }
                    singleEmitter2.onError(error);
                }
            }
            List list = RequestManager.this.errorListeners;
            if (list != null) {
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    ((OnRequestErrorListener) it.next()).onRequestError(error, this.url);
                }
            }
        }

        @Override // com.android.volley.Response.Listener
        public void onResponse(T response) {
            SingleEmitter<T> singleEmitter = this.observer;
            if (singleEmitter != null) {
                if (singleEmitter == null) {
                    Intrinsics.throwNpe();
                }
                if (singleEmitter.isDisposed()) {
                    return;
                }
                SingleEmitter<T> singleEmitter2 = this.observer;
                if (singleEmitter2 == null) {
                    Intrinsics.throwNpe();
                }
                singleEmitter2.onSuccess(response);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Single getJSONObject$default(RequestManager requestManager, int i, URL url, Map map, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getJSONObject");
        }
        if ((i2 & 4) != 0) {
            map = (Map) null;
        }
        return requestManager.getJSONObject(i, url, map);
    }

    public final Single<JSONObject> getJSONObject(final int method, final URL url, final Map<String, ? extends Object> params) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Log.d(TAG, "forceNoConnection == " + this.forceNoConnection);
        if (this.forceNoConnection) {
            Single<JSONObject> singleError = Single.error(new ForcedNoConnectionError());
            Intrinsics.checkExpressionValueIsNotNull(singleError, "Single.error(ForcedNoConnectionError())");
            return singleError;
        }
        Single<JSONObject> singleCreate = Single.create(new SingleOnSubscribe<T>() { // from class: com.dotscreen.farkit.network.RequestManager.getJSONObject.1
            /* JADX WARN: Type inference failed for: r0v2, types: [com.dotscreen.farkit.network.RequestManager$getJSONObject$1$req$1] */
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter<JSONObject> subscriber) {
                Intrinsics.checkParameterIsNotNull(subscriber, "subscriber");
                final VolleyResponseObserverWrapper volleyResponseObserverWrapper = RequestManager.this.new VolleyResponseObserverWrapper(subscriber, url);
                final Context context = RequestManager.this.getContext();
                final int iConvertMethod = RequestManager.this.convertMethod(method);
                final String string = url.toString();
                Intrinsics.checkExpressionValueIsNotNull(string, "url.toString()");
                final Map map = params;
                final VolleyResponseObserverWrapper volleyResponseObserverWrapper2 = volleyResponseObserverWrapper;
                final VolleyResponseObserverWrapper volleyResponseObserverWrapper3 = volleyResponseObserverWrapper;
                final ?? r0 = new VolleyRequest<JSONObject>(context, iConvertMethod, string, map, volleyResponseObserverWrapper2, volleyResponseObserverWrapper3) { // from class: com.dotscreen.farkit.network.RequestManager$getJSONObject$1$req$1
                    @Override // com.dotscreen.farkit.network.internal.VolleyRequest
                    public /* bridge */ /* synthetic */ JSONObject parseData(byte[] bArr, Map map2) {
                        return parseData2(bArr, (Map<String, String>) map2);
                    }

                    @Override // com.dotscreen.farkit.network.internal.VolleyRequest
                    /* JADX INFO: renamed from: parseData, reason: avoid collision after fix types in other method */
                    protected JSONObject parseData2(byte[] data, Map<String, String> headers) throws Exception {
                        Intrinsics.checkParameterIsNotNull(data, "data");
                        Intrinsics.checkParameterIsNotNull(headers, "headers");
                        Charset charsetForName = Charset.forName(HttpHeaderParser.parseCharset(headers));
                        Intrinsics.checkExpressionValueIsNotNull(charsetForName, "Charset.forName(HttpHead…er.parseCharset(headers))");
                        return new JSONObject(new String(data, charsetForName));
                    }
                };
                RequestManager.this.getVolleyRequestQueue().add((Request) r0);
                subscriber.setCancellable(new Cancellable() { // from class: com.dotscreen.farkit.network.RequestManager.getJSONObject.1.1
                    @Override // io.reactivex.functions.Cancellable
                    public final void cancel() {
                        cancel();
                    }
                });
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleCreate, "Single.create { subscrib… req.cancel() }\n        }");
        return singleCreate;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Single getJSONArray$default(RequestManager requestManager, int i, URL url, Map map, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getJSONArray");
        }
        if ((i2 & 4) != 0) {
            map = (Map) null;
        }
        return requestManager.getJSONArray(i, url, map);
    }

    public final Single<JSONArray> getJSONArray(final int method, final URL url, final Map<String, ? extends Object> params) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Log.d(TAG, "forceNoConnection == " + this.forceNoConnection);
        if (this.forceNoConnection) {
            Single<JSONArray> singleError = Single.error(new ForcedNoConnectionError());
            Intrinsics.checkExpressionValueIsNotNull(singleError, "Single.error(ForcedNoConnectionError())");
            return singleError;
        }
        Single<JSONArray> singleCreate = Single.create(new SingleOnSubscribe<T>() { // from class: com.dotscreen.farkit.network.RequestManager.getJSONArray.1
            /* JADX WARN: Type inference failed for: r0v2, types: [com.dotscreen.farkit.network.RequestManager$getJSONArray$1$req$1] */
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter<JSONArray> subscriber) {
                Intrinsics.checkParameterIsNotNull(subscriber, "subscriber");
                final VolleyResponseObserverWrapper volleyResponseObserverWrapper = RequestManager.this.new VolleyResponseObserverWrapper(subscriber, url);
                final Context context = RequestManager.this.getContext();
                final int iConvertMethod = RequestManager.this.convertMethod(method);
                final String string = url.toString();
                Intrinsics.checkExpressionValueIsNotNull(string, "url.toString()");
                final Map map = params;
                final VolleyResponseObserverWrapper volleyResponseObserverWrapper2 = volleyResponseObserverWrapper;
                final VolleyResponseObserverWrapper volleyResponseObserverWrapper3 = volleyResponseObserverWrapper;
                final ?? r0 = new VolleyRequest<JSONArray>(context, iConvertMethod, string, map, volleyResponseObserverWrapper2, volleyResponseObserverWrapper3) { // from class: com.dotscreen.farkit.network.RequestManager$getJSONArray$1$req$1
                    @Override // com.dotscreen.farkit.network.internal.VolleyRequest
                    public /* bridge */ /* synthetic */ JSONArray parseData(byte[] bArr, Map map2) {
                        return parseData2(bArr, (Map<String, String>) map2);
                    }

                    @Override // com.dotscreen.farkit.network.internal.VolleyRequest
                    /* JADX INFO: renamed from: parseData, reason: avoid collision after fix types in other method */
                    protected JSONArray parseData2(byte[] data, Map<String, String> headers) throws Exception {
                        Intrinsics.checkParameterIsNotNull(data, "data");
                        Intrinsics.checkParameterIsNotNull(headers, "headers");
                        Charset charsetForName = Charset.forName(HttpHeaderParser.parseCharset(headers));
                        Intrinsics.checkExpressionValueIsNotNull(charsetForName, "Charset.forName(HttpHead…er.parseCharset(headers))");
                        return new JSONArray(new String(data, charsetForName));
                    }
                };
                RequestManager.this.getVolleyRequestQueue().add((Request) r0);
                subscriber.setCancellable(new Cancellable() { // from class: com.dotscreen.farkit.network.RequestManager.getJSONArray.1.1
                    @Override // io.reactivex.functions.Cancellable
                    public final void cancel() {
                        cancel();
                    }
                });
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleCreate, "Single.create { subscrib… req.cancel() }\n        }");
        return singleCreate;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Single getBitmap$default(RequestManager requestManager, int i, URL url, int i2, int i3, Map map, int i4, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getBitmap");
        }
        int i5 = (i4 & 4) != 0 ? 0 : i2;
        int i6 = (i4 & 8) != 0 ? 0 : i3;
        if ((i4 & 16) != 0) {
            map = (Map) null;
        }
        return requestManager.getBitmap(i, url, i5, i6, map);
    }

    public final Single<Bitmap> getBitmap(int method, final URL url, final int width, final int height, final Map<String, ? extends Object> params) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Log.d(TAG, "forceNoConnection == " + this.forceNoConnection);
        if (this.forceNoConnection) {
            Single<Bitmap> singleError = Single.error(new ForcedNoConnectionError());
            Intrinsics.checkExpressionValueIsNotNull(singleError, "Single.error(ForcedNoConnectionError())");
            return singleError;
        }
        Single<Bitmap> singleCreate = Single.create(new SingleOnSubscribe<T>() { // from class: com.dotscreen.farkit.network.RequestManager.getBitmap.1
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter<Bitmap> subscriber) {
                Intrinsics.checkParameterIsNotNull(subscriber, "subscriber");
                String string = url.toString();
                Intrinsics.checkExpressionValueIsNotNull(string, "url.toString()");
                String strReplace$default = StringsKt.replace$default(string, " ", "%20", false, 4, (Object) null);
                try {
                    String strQueryString = VolleyRequest.INSTANCE.queryString(params, "utf-8");
                    if (strQueryString.length() > 0) {
                        strReplace$default = strReplace$default + '?' + strQueryString;
                    }
                    Log.v(RequestManager.TAG, "start downloading image \"" + strReplace$default + Typography.quote);
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C00791(strReplace$default, subscriber, null), 2, null);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    if (subscriber.isDisposed()) {
                        return;
                    }
                    subscriber.onError(e);
                }
            }

            /* JADX INFO: renamed from: com.dotscreen.farkit.network.RequestManager$getBitmap$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: RequestManager.kt */
            @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
            @DebugMetadata(c = "com.dotscreen.farkit.network.RequestManager$getBitmap$1$1", f = "RequestManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            static final class C00791 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ String $finalStrUrl;
                final /* synthetic */ SingleEmitter $subscriber;
                int label;
                private CoroutineScope p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C00791(String str, SingleEmitter singleEmitter, Continuation continuation) {
                    super(2, continuation);
                    this.$finalStrUrl = str;
                    this.$subscriber = singleEmitter;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C00791 c00791 = new C00791(this.$finalStrUrl, this.$subscriber, completion);
                    c00791.p$ = (CoroutineScope) obj;
                    return c00791;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C00791) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    ImageLoader imageLoader = (StringsKt.startsWith$default(this.$finalStrUrl, "android.resource", false, 2, (Object) null) || StringsKt.startsWith$default(this.$finalStrUrl, "file://", false, 2, (Object) null)) ? RequestManager.this.localImageLoader : RequestManager.this.imageLoader;
                    final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                    objectRef.element = (T) imageLoader.get(this.$finalStrUrl, new ImageLoader.ImageListener() { // from class: com.dotscreen.farkit.network.RequestManager$getBitmap$1$1$container$1
                        @Override // com.android.volley.Response.ErrorListener
                        public void onErrorResponse(VolleyError error) {
                            Intrinsics.checkParameterIsNotNull(error, "error");
                            SingleEmitter subscriber = this.this$0.$subscriber;
                            Intrinsics.checkExpressionValueIsNotNull(subscriber, "subscriber");
                            if (subscriber.isDisposed()) {
                                return;
                            }
                            VolleyError volleyError = error;
                            Log.w(RequestManager.TAG, "   error downloading \"" + this.this$0.$finalStrUrl + Typography.quote, volleyError);
                            this.this$0.$subscriber.onError(volleyError);
                        }

                        @Override // com.android.volley.toolbox.ImageLoader.ImageListener
                        public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                            Intrinsics.checkParameterIsNotNull(response, "response");
                            SingleEmitter subscriber = this.this$0.$subscriber;
                            Intrinsics.checkExpressionValueIsNotNull(subscriber, "subscriber");
                            if (subscriber.isDisposed() || response.getBitmap() == null) {
                                return;
                            }
                            Log.v(RequestManager.TAG, "   done downloading \"" + this.this$0.$finalStrUrl + Typography.quote);
                            this.this$0.$subscriber.onSuccess(response.getBitmap());
                        }
                    }, width, height, ImageView.ScaleType.CENTER_CROP);
                    this.$subscriber.setCancellable(new Cancellable() { // from class: com.dotscreen.farkit.network.RequestManager.getBitmap.1.1.1

                        /* JADX INFO: renamed from: com.dotscreen.farkit.network.RequestManager$getBitmap$1$1$1$1, reason: invalid class name and collision with other inner class name */
                        /* JADX INFO: compiled from: RequestManager.kt */
                        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
                        @DebugMetadata(c = "com.dotscreen.farkit.network.RequestManager$getBitmap$1$1$1$1", f = "RequestManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                        static final class C00811 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                            int label;
                            private CoroutineScope p$;

                            C00811(Continuation continuation) {
                                super(2, continuation);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                                Intrinsics.checkParameterIsNotNull(completion, "completion");
                                C00811 c00811 = C00801.this.new C00811(completion);
                                c00811.p$ = (CoroutineScope) obj;
                                return c00811;
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                return ((C00811) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object obj) {
                                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                if (this.label != 0) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                ResultKt.throwOnFailure(obj);
                                ((ImageLoader.ImageContainer) objectRef.element).cancelRequest();
                                return Unit.INSTANCE;
                            }
                        }

                        @Override // io.reactivex.functions.Cancellable
                        public final void cancel() {
                            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C00811(null), 2, null);
                        }
                    });
                    return Unit.INSTANCE;
                }
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleCreate, "Single.create { subscrib…}\n            }\n        }");
        return singleCreate;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Single getString$default(RequestManager requestManager, int i, URL url, Map map, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getString");
        }
        if ((i2 & 4) != 0) {
            map = (Map) null;
        }
        return requestManager.getString(i, url, map);
    }

    public final Single<String> getString(final int method, final URL url, final Map<String, ? extends Object> params) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Log.d(TAG, "forceNoConnection == " + this.forceNoConnection);
        if (this.forceNoConnection) {
            Single<String> singleError = Single.error(new ForcedNoConnectionError());
            Intrinsics.checkExpressionValueIsNotNull(singleError, "Single.error(ForcedNoConnectionError())");
            return singleError;
        }
        Single<String> singleCreate = Single.create(new SingleOnSubscribe<T>() { // from class: com.dotscreen.farkit.network.RequestManager.getString.1
            /* JADX WARN: Type inference failed for: r0v2, types: [com.dotscreen.farkit.network.RequestManager$getString$1$req$1] */
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter<String> subscriber) {
                Intrinsics.checkParameterIsNotNull(subscriber, "subscriber");
                final VolleyResponseObserverWrapper volleyResponseObserverWrapper = RequestManager.this.new VolleyResponseObserverWrapper(subscriber, url);
                final Context context = RequestManager.this.getContext();
                final int iConvertMethod = RequestManager.this.convertMethod(method);
                final String string = url.toString();
                Intrinsics.checkExpressionValueIsNotNull(string, "url.toString()");
                final Map map = params;
                final VolleyResponseObserverWrapper volleyResponseObserverWrapper2 = volleyResponseObserverWrapper;
                final VolleyResponseObserverWrapper volleyResponseObserverWrapper3 = volleyResponseObserverWrapper;
                final ?? r0 = new VolleyRequest<String>(context, iConvertMethod, string, map, volleyResponseObserverWrapper2, volleyResponseObserverWrapper3) { // from class: com.dotscreen.farkit.network.RequestManager$getString$1$req$1
                    @Override // com.dotscreen.farkit.network.internal.VolleyRequest
                    public /* bridge */ /* synthetic */ String parseData(byte[] bArr, Map map2) {
                        return parseData2(bArr, (Map<String, String>) map2);
                    }

                    @Override // com.dotscreen.farkit.network.internal.VolleyRequest
                    /* JADX INFO: renamed from: parseData, reason: avoid collision after fix types in other method */
                    protected String parseData2(byte[] data, Map<String, String> headers) throws Exception {
                        Intrinsics.checkParameterIsNotNull(data, "data");
                        Intrinsics.checkParameterIsNotNull(headers, "headers");
                        Charset charsetForName = Charset.forName(HttpHeaderParser.parseCharset(headers));
                        Intrinsics.checkExpressionValueIsNotNull(charsetForName, "Charset.forName(HttpHead…er.parseCharset(headers))");
                        return new String(data, charsetForName);
                    }
                };
                RequestManager.this.getVolleyRequestQueue().add((Request) r0);
                subscriber.setCancellable(new Cancellable() { // from class: com.dotscreen.farkit.network.RequestManager.getString.1.1
                    @Override // io.reactivex.functions.Cancellable
                    public final void cancel() {
                        cancel();
                    }
                });
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleCreate, "Single.create { subscrib… req.cancel() }\n        }");
        return singleCreate;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Single getBytes$default(RequestManager requestManager, int i, URL url, Map map, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getBytes");
        }
        if ((i2 & 4) != 0) {
            map = (Map) null;
        }
        return requestManager.getBytes(i, url, map);
    }

    public final Single<byte[]> getBytes(final int method, final URL url, final Map<String, ? extends Object> params) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Log.d(TAG, "forceNoConnection == " + this.forceNoConnection);
        if (this.forceNoConnection) {
            Single<byte[]> singleError = Single.error(new ForcedNoConnectionError());
            Intrinsics.checkExpressionValueIsNotNull(singleError, "Single.error(ForcedNoConnectionError())");
            return singleError;
        }
        Single<byte[]> singleCreate = Single.create(new SingleOnSubscribe<T>() { // from class: com.dotscreen.farkit.network.RequestManager.getBytes.1
            /* JADX WARN: Type inference failed for: r0v2, types: [com.dotscreen.farkit.network.RequestManager$getBytes$1$req$1] */
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter<byte[]> subscriber) {
                Intrinsics.checkParameterIsNotNull(subscriber, "subscriber");
                final VolleyResponseObserverWrapper volleyResponseObserverWrapper = RequestManager.this.new VolleyResponseObserverWrapper(subscriber, url);
                final Context context = RequestManager.this.getContext();
                final int iConvertMethod = RequestManager.this.convertMethod(method);
                final String string = url.toString();
                Intrinsics.checkExpressionValueIsNotNull(string, "url.toString()");
                final Map map = params;
                final VolleyResponseObserverWrapper volleyResponseObserverWrapper2 = volleyResponseObserverWrapper;
                final VolleyResponseObserverWrapper volleyResponseObserverWrapper3 = volleyResponseObserverWrapper;
                final ?? r0 = new VolleyRequest<byte[]>(context, iConvertMethod, string, map, volleyResponseObserverWrapper2, volleyResponseObserverWrapper3) { // from class: com.dotscreen.farkit.network.RequestManager$getBytes$1$req$1
                    @Override // com.dotscreen.farkit.network.internal.VolleyRequest
                    /* JADX INFO: renamed from: parseData, reason: avoid collision after fix types in other method */
                    protected byte[] parseData2(byte[] data, Map<String, String> headers) throws Exception {
                        Intrinsics.checkParameterIsNotNull(data, "data");
                        Intrinsics.checkParameterIsNotNull(headers, "headers");
                        return data;
                    }

                    @Override // com.dotscreen.farkit.network.internal.VolleyRequest
                    public /* bridge */ /* synthetic */ byte[] parseData(byte[] bArr, Map map2) {
                        return parseData2(bArr, (Map<String, String>) map2);
                    }
                };
                RequestManager.this.getVolleyRequestQueue().add((Request) r0);
                subscriber.setCancellable(new Cancellable() { // from class: com.dotscreen.farkit.network.RequestManager.getBytes.1.1
                    @Override // io.reactivex.functions.Cancellable
                    public final void cancel() {
                        cancel();
                    }
                });
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleCreate, "Single.create { subscrib… req.cancel() }\n        }");
        return singleCreate;
    }

    private final RequestQueue newRequestQueue(Context context) {
        String absolutePath = this.cachePath;
        if (absolutePath == null) {
            File cacheDir = context.getCacheDir();
            Intrinsics.checkExpressionValueIsNotNull(cacheDir, "context.cacheDir");
            absolutePath = cacheDir.getAbsolutePath();
        }
        File file = new File(absolutePath, "volley");
        RequestQueue requestQueue = new RequestQueue(new DiskBasedCache(file, 20971520), new BasicNetwork((BaseHttpStack) new HurlStack()), 4, new ExecutorDelivery(new Executor() { // from class: com.dotscreen.farkit.network.RequestManager$newRequestQueue$queue$1
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                runnable.run();
            }
        }));
        requestQueue.start();
        return requestQueue;
    }

    public final void addOnRequestErrorListener(OnRequestErrorListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (this.errorListeners == null) {
            this.errorListeners = new ArrayList();
        }
        List<OnRequestErrorListener> list = this.errorListeners;
        if (list == null) {
            Intrinsics.throwNpe();
        }
        list.add(listener);
    }

    public final void removeOnRequestErrorListener(OnRequestErrorListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        List<OnRequestErrorListener> list = this.errorListeners;
        if (list != null) {
            if (list == null) {
                Intrinsics.throwNpe();
            }
            list.remove(listener);
        }
    }
}
