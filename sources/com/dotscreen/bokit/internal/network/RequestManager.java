package com.dotscreen.bokit.internal.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;
import androidx.exifinterface.media.ExifInterface;
import com.android.volley.ExecutorDelivery;
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
import com.daveanthonythomas.moshipack.TypeReference;
import com.dotscreen.bokit.OnRequestErrorListener;
import com.dotscreen.bokit.OnRequestResponseListener;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.PatchedMsgpackReader;
import com.viaccessorca.common.VOUtils;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.Cancellable;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
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
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import okio.Buffer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.tukaani.xz.XZInputStream;

/* JADX INFO: compiled from: RequestManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000 ,2\u00020\u0001:\u0002,-B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000bJ\u000e\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\rJ\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0002JD\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u00162\u0016\b\u0002\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010 J4\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u00192\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001c2\u0016\b\u0002\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010 J4\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u00192\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001c2\u0016\b\u0002\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010 J4\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u00192\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001c2\u0016\b\u0002\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010 J@\u0010'\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010 0\u00192\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001c2\u0016\b\u0002\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010 J4\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00050\u00192\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001c2\u0016\b\u0002\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010 J\u000e\u0010)\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u001cJ\u0010\u0010*\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u000e\u0010+\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/dotscreen/bokit/internal/network/RequestManager;", "", "context", "Landroid/content/Context;", "mCachePath", "", "(Landroid/content/Context;Ljava/lang/String;)V", "mImageLoader", "Lcom/android/volley/toolbox/ImageLoader;", "mOnErrorListeners", "", "Lcom/dotscreen/bokit/OnRequestErrorListener;", "mOnResponseListeners", "Lcom/dotscreen/bokit/OnRequestResponseListener;", "mVolleyImageRequestQueue", "Lcom/android/volley/RequestQueue;", "mVolleyRequestQueue", "addOnRequestErrorListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "addOnRequestResponseListener", "convertMethod", "", "method", "getBitmap", "Lio/reactivex/Single;", "Landroid/graphics/Bitmap;", ImagesContract.URL, "Ljava/net/URL;", ToolboxAnalyticsModuleKt.M_WIDTH, ToolboxAnalyticsModuleKt.M_HEIGHT, "params", "", "getBytes", "", "getJSONArray", "Lorg/json/JSONArray;", "getJSONObject", "Lorg/json/JSONObject;", "getMessagePackMap", "getString", "invalidateCache", "newRequestQueue", "removeOnRequestErrorListener", "Companion", "VolleyResponseObserverWrapper", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public final class RequestManager {
    public static final int DELETE = 3;
    public static final int GET = 0;
    public static final int POST = 1;
    public static final int PUT = 2;
    private static RequestManager sInstance;
    private final Context context;
    private final String mCachePath;
    private final ImageLoader mImageLoader;
    private List<OnRequestErrorListener> mOnErrorListeners;
    private List<OnRequestResponseListener> mOnResponseListeners;
    private final RequestQueue mVolleyImageRequestQueue;
    private final RequestQueue mVolleyRequestQueue;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = RequestManager.class.getSimpleName();

    /* JADX INFO: Access modifiers changed from: private */
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

    public RequestManager(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.mCachePath = str;
        this.mVolleyRequestQueue = newRequestQueue(context);
        this.mVolleyImageRequestQueue = newRequestQueue(this.context);
        this.mImageLoader = new ImageLoader(this.mVolleyImageRequestQueue, new BitmapLruCache(this.context));
    }

    public final void invalidateCache(URL url) {
        Intrinsics.checkNotNullParameter(url, "url");
        this.mVolleyRequestQueue.getCache().invalidate(url.toString(), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: RequestManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003B/\b\u0000\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0015\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001aJ\u0014\u0010\u001b\u001a\u00020\u00152\n\u0010\u0016\u001a\u00060\u001cj\u0002`\u001dH\u0002R\u001c\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\"\u0010\u0004\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0014\u0010\t\u001a\u00020\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001e"}, d2 = {"Lcom/dotscreen/bokit/internal/network/RequestManager$VolleyResponseObserverWrapper;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/android/volley/Response$Listener;", "Lcom/android/volley/Response$ErrorListener;", "observer", "Lio/reactivex/SingleEmitter;", "errorListeners", "", "Lcom/dotscreen/bokit/OnRequestErrorListener;", ImagesContract.URL, "Ljava/net/URL;", "(Lio/reactivex/SingleEmitter;Ljava/util/List;Ljava/net/URL;)V", "getErrorListeners$bokit_android_release", "()Ljava/util/List;", "getObserver$bokit_android_release", "()Lio/reactivex/SingleEmitter;", "setObserver$bokit_android_release", "(Lio/reactivex/SingleEmitter;)V", "getUrl$bokit_android_release", "()Ljava/net/URL;", "onErrorResponse", "", "error", "Lcom/android/volley/VolleyError;", "onResponse", "response", "(Ljava/lang/Object;)V", "reportError", "Ljava/lang/Exception;", "Lkotlin/Exception;", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
    static final class VolleyResponseObserverWrapper<T> implements Response.Listener<T>, Response.ErrorListener {
        private final List<OnRequestErrorListener> errorListeners;
        private SingleEmitter<T> observer;
        private final URL url;

        /* JADX WARN: Multi-variable type inference failed */
        public VolleyResponseObserverWrapper(SingleEmitter<T> singleEmitter, List<? extends OnRequestErrorListener> list, URL url) {
            Intrinsics.checkNotNullParameter(url, "url");
            this.observer = singleEmitter;
            this.errorListeners = list;
            this.url = url;
        }

        public final List<OnRequestErrorListener> getErrorListeners$bokit_android_release() {
            return this.errorListeners;
        }

        public final SingleEmitter<T> getObserver$bokit_android_release() {
            return this.observer;
        }

        /* JADX INFO: renamed from: getUrl$bokit_android_release, reason: from getter */
        public final URL getUrl() {
            return this.url;
        }

        public final void setObserver$bokit_android_release(SingleEmitter<T> singleEmitter) {
            this.observer = singleEmitter;
        }

        private final void reportError(Exception error) {
            SingleEmitter<T> singleEmitter = this.observer;
            if (singleEmitter != null) {
                Intrinsics.checkNotNull(singleEmitter);
                if (singleEmitter.isDisposed()) {
                    return;
                }
                SingleEmitter<T> singleEmitter2 = this.observer;
                Intrinsics.checkNotNull(singleEmitter2);
                singleEmitter2.onError(error);
            }
        }

        @Override // com.android.volley.Response.ErrorListener
        public void onErrorResponse(VolleyError error) {
            Intrinsics.checkNotNullParameter(error, "error");
            reportError(error);
            List<OnRequestErrorListener> list = this.errorListeners;
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
                Intrinsics.checkNotNull(singleEmitter);
                if (singleEmitter.isDisposed()) {
                    return;
                }
                SingleEmitter<T> singleEmitter2 = this.observer;
                Intrinsics.checkNotNull(singleEmitter2);
                singleEmitter2.onSuccess(response);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Single getMessagePackMap$default(RequestManager requestManager, int i, URL url, Map map, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            map = (Map) null;
        }
        return requestManager.getMessagePackMap(i, url, map);
    }

    public final Single<Map<Object, Object>> getMessagePackMap(final int method, final URL url, final Map<String, ? extends Object> params) {
        Intrinsics.checkNotNullParameter(url, "url");
        Single<Map<Object, Object>> singleCreate = Single.create(new SingleOnSubscribe<Map<Object, ? extends Object>>() { // from class: com.dotscreen.bokit.internal.network.RequestManager.getMessagePackMap.1
            /* JADX WARN: Type inference failed for: r0v3, types: [com.dotscreen.bokit.internal.network.RequestManager$getMessagePackMap$1$req$1] */
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter<Map<Object, ? extends Object>> subscriber) {
                Intrinsics.checkNotNullParameter(subscriber, "subscriber");
                final VolleyResponseObserverWrapper volleyResponseObserverWrapper = new VolleyResponseObserverWrapper(subscriber, RequestManager.this.mOnErrorListeners, url);
                final Context context = RequestManager.this.context;
                final int iConvertMethod = RequestManager.this.convertMethod(method);
                final String string = url.toString();
                Intrinsics.checkNotNullExpressionValue(string, "url.toString()");
                final Map map = params;
                final VolleyResponseObserverWrapper volleyResponseObserverWrapper2 = volleyResponseObserverWrapper;
                final VolleyResponseObserverWrapper volleyResponseObserverWrapper3 = volleyResponseObserverWrapper;
                final List list = RequestManager.this.mOnResponseListeners;
                final ?? r0 = new VolleyRequest<Map<Object, ? extends Object>>(context, iConvertMethod, string, map, volleyResponseObserverWrapper2, volleyResponseObserverWrapper3, list) { // from class: com.dotscreen.bokit.internal.network.RequestManager$getMessagePackMap$1$req$1
                    @Override // com.dotscreen.bokit.internal.network.VolleyRequest
                    public /* bridge */ /* synthetic */ Map<Object, ? extends Object> parseData(byte[] bArr, Map map2) {
                        return parseData2(bArr, (Map<String, String>) map2);
                    }

                    @Override // com.dotscreen.bokit.internal.network.VolleyRequest
                    /* JADX INFO: renamed from: parseData, reason: avoid collision after fix types in other method */
                    protected Map<Object, ? extends Object> parseData2(byte[] data, Map<String, String> headers) throws Exception {
                        Intrinsics.checkNotNullParameter(data, "data");
                        Intrinsics.checkNotNullParameter(headers, "headers");
                        try {
                            String str = headers.get("Content-Type");
                            final Type type = new TypeReference<Object>() { // from class: com.dotscreen.bokit.internal.network.RequestManager$getMessagePackMap$1$req$1$parseData$type$1
                            }.getType();
                            JsonAdapter jsonAdapterAdapter = new Moshi.Builder().add(new JsonAdapter.Factory() { // from class: com.dotscreen.bokit.internal.network.RequestManager$getMessagePackMap$1$req$1$parseData$moshiAdapter$1
                                @Override // com.squareup.moshi.JsonAdapter.Factory
                                public final JsonAdapter<?> create(Type type2, Set<? extends Annotation> set, Moshi moshi) {
                                    if (!Intrinsics.areEqual(type2, type)) {
                                        return null;
                                    }
                                    Intrinsics.checkNotNullExpressionValue(moshi, "moshi");
                                    return new PatchedMsgpackReader.PatchedObjectJsonAdapter(moshi);
                                }
                            }).build().adapter(type);
                            if (Intrinsics.areEqual("application/x-amazintv-compressed", str)) {
                                XZInputStream xZInputStream = new XZInputStream(new ByteArrayInputStream(data), 1048576);
                                Buffer buffer = new Buffer();
                                buffer.readFrom(xZInputStream);
                                Object objFromJson = jsonAdapterAdapter.fromJson(new PatchedMsgpackReader(buffer));
                                if (objFromJson != null) {
                                    return (Map) objFromJson;
                                }
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<kotlin.Any, kotlin.Any>");
                            }
                            if (Intrinsics.areEqual("application/x-amazintv", str)) {
                                Buffer buffer2 = new Buffer();
                                buffer2.write(data);
                                Unit unit = Unit.INSTANCE;
                                Object objFromJson2 = jsonAdapterAdapter.fromJson(new PatchedMsgpackReader(buffer2));
                                if (objFromJson2 != null) {
                                    return (Map) objFromJson2;
                                }
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<kotlin.Any, kotlin.Any>");
                            }
                            throw new UnsupportedOperationException("Unexpected content type: " + str);
                        } catch (Throwable unused) {
                            return MapsKt.emptyMap();
                        }
                    }
                };
                RequestManager.this.mVolleyRequestQueue.add((Request) r0);
                subscriber.setCancellable(new Cancellable() { // from class: com.dotscreen.bokit.internal.network.RequestManager.getMessagePackMap.1.1
                    @Override // io.reactivex.functions.Cancellable
                    public final void cancel() {
                        cancel();
                    }
                });
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "Single.create { subscrib… req.cancel() }\n        }");
        return singleCreate;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Single getJSONObject$default(RequestManager requestManager, int i, URL url, Map map, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            map = (Map) null;
        }
        return requestManager.getJSONObject(i, url, map);
    }

    public final Single<JSONObject> getJSONObject(final int method, final URL url, final Map<String, ? extends Object> params) {
        Intrinsics.checkNotNullParameter(url, "url");
        Single<JSONObject> singleCreate = Single.create(new SingleOnSubscribe<JSONObject>() { // from class: com.dotscreen.bokit.internal.network.RequestManager.getJSONObject.1
            /* JADX WARN: Type inference failed for: r0v3, types: [com.dotscreen.bokit.internal.network.RequestManager$getJSONObject$1$req$1] */
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter<JSONObject> subscriber) {
                Intrinsics.checkNotNullParameter(subscriber, "subscriber");
                final VolleyResponseObserverWrapper volleyResponseObserverWrapper = new VolleyResponseObserverWrapper(subscriber, RequestManager.this.mOnErrorListeners, url);
                final Context context = RequestManager.this.context;
                final int iConvertMethod = RequestManager.this.convertMethod(method);
                final String string = url.toString();
                Intrinsics.checkNotNullExpressionValue(string, "url.toString()");
                final Map map = params;
                final VolleyResponseObserverWrapper volleyResponseObserverWrapper2 = volleyResponseObserverWrapper;
                final VolleyResponseObserverWrapper volleyResponseObserverWrapper3 = volleyResponseObserverWrapper;
                final List list = RequestManager.this.mOnResponseListeners;
                final ?? r0 = new VolleyRequest<JSONObject>(context, iConvertMethod, string, map, volleyResponseObserverWrapper2, volleyResponseObserverWrapper3, list) { // from class: com.dotscreen.bokit.internal.network.RequestManager$getJSONObject$1$req$1
                    @Override // com.dotscreen.bokit.internal.network.VolleyRequest
                    public /* bridge */ /* synthetic */ JSONObject parseData(byte[] bArr, Map map2) {
                        return parseData2(bArr, (Map<String, String>) map2);
                    }

                    @Override // com.dotscreen.bokit.internal.network.VolleyRequest
                    /* JADX INFO: renamed from: parseData, reason: avoid collision after fix types in other method */
                    protected JSONObject parseData2(byte[] data, Map<String, String> headers) throws Exception {
                        Intrinsics.checkNotNullParameter(data, "data");
                        Intrinsics.checkNotNullParameter(headers, "headers");
                        Charset charsetForName = Charset.forName(HttpHeaderParser.parseCharset(headers));
                        Intrinsics.checkNotNullExpressionValue(charsetForName, "Charset.forName(HttpHead…er.parseCharset(headers))");
                        return new JSONObject(new String(data, charsetForName));
                    }
                };
                RequestManager.this.mVolleyRequestQueue.add((Request) r0);
                subscriber.setCancellable(new Cancellable() { // from class: com.dotscreen.bokit.internal.network.RequestManager.getJSONObject.1.1
                    @Override // io.reactivex.functions.Cancellable
                    public final void cancel() {
                        cancel();
                    }
                });
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "Single.create { subscrib… req.cancel() }\n        }");
        return singleCreate;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Single getJSONArray$default(RequestManager requestManager, int i, URL url, Map map, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            map = (Map) null;
        }
        return requestManager.getJSONArray(i, url, map);
    }

    public final Single<JSONArray> getJSONArray(final int method, final URL url, final Map<String, ? extends Object> params) {
        Intrinsics.checkNotNullParameter(url, "url");
        Single<JSONArray> singleCreate = Single.create(new SingleOnSubscribe<JSONArray>() { // from class: com.dotscreen.bokit.internal.network.RequestManager.getJSONArray.1
            /* JADX WARN: Type inference failed for: r0v3, types: [com.dotscreen.bokit.internal.network.RequestManager$getJSONArray$1$req$1] */
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter<JSONArray> subscriber) {
                Intrinsics.checkNotNullParameter(subscriber, "subscriber");
                final VolleyResponseObserverWrapper volleyResponseObserverWrapper = new VolleyResponseObserverWrapper(subscriber, RequestManager.this.mOnErrorListeners, url);
                final Context context = RequestManager.this.context;
                final int iConvertMethod = RequestManager.this.convertMethod(method);
                final String string = url.toString();
                Intrinsics.checkNotNullExpressionValue(string, "url.toString()");
                final Map map = params;
                final VolleyResponseObserverWrapper volleyResponseObserverWrapper2 = volleyResponseObserverWrapper;
                final VolleyResponseObserverWrapper volleyResponseObserverWrapper3 = volleyResponseObserverWrapper;
                final List list = RequestManager.this.mOnResponseListeners;
                final ?? r0 = new VolleyRequest<JSONArray>(context, iConvertMethod, string, map, volleyResponseObserverWrapper2, volleyResponseObserverWrapper3, list) { // from class: com.dotscreen.bokit.internal.network.RequestManager$getJSONArray$1$req$1
                    @Override // com.dotscreen.bokit.internal.network.VolleyRequest
                    public /* bridge */ /* synthetic */ JSONArray parseData(byte[] bArr, Map map2) {
                        return parseData2(bArr, (Map<String, String>) map2);
                    }

                    @Override // com.dotscreen.bokit.internal.network.VolleyRequest
                    /* JADX INFO: renamed from: parseData, reason: avoid collision after fix types in other method */
                    protected JSONArray parseData2(byte[] data, Map<String, String> headers) throws Exception {
                        Intrinsics.checkNotNullParameter(data, "data");
                        Intrinsics.checkNotNullParameter(headers, "headers");
                        Charset charsetForName = Charset.forName(HttpHeaderParser.parseCharset(headers));
                        Intrinsics.checkNotNullExpressionValue(charsetForName, "Charset.forName(HttpHead…er.parseCharset(headers))");
                        return new JSONArray(new String(data, charsetForName));
                    }
                };
                RequestManager.this.mVolleyRequestQueue.add((Request) r0);
                subscriber.setCancellable(new Cancellable() { // from class: com.dotscreen.bokit.internal.network.RequestManager.getJSONArray.1.1
                    @Override // io.reactivex.functions.Cancellable
                    public final void cancel() {
                        cancel();
                    }
                });
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "Single.create { subscrib… req.cancel() }\n        }");
        return singleCreate;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Single getBitmap$default(RequestManager requestManager, int i, URL url, int i2, int i3, Map map, int i4, Object obj) {
        if ((i4 & 16) != 0) {
            map = (Map) null;
        }
        return requestManager.getBitmap(i, url, i2, i3, map);
    }

    public final Single<Bitmap> getBitmap(int method, final URL url, final int width, final int height, final Map<String, ? extends Object> params) {
        Intrinsics.checkNotNullParameter(url, "url");
        Single<Bitmap> singleCreate = Single.create(new SingleOnSubscribe<Bitmap>() { // from class: com.dotscreen.bokit.internal.network.RequestManager.getBitmap.1
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter<Bitmap> subscriber) {
                Intrinsics.checkNotNullParameter(subscriber, "subscriber");
                String string = url.toString();
                Intrinsics.checkNotNullExpressionValue(string, "url.toString()");
                String strReplace$default = StringsKt.replace$default(string, " ", "%20", false, 4, (Object) null);
                try {
                    String strQueryString = VolleyRequest.INSTANCE.queryString(params, "utf-8");
                    if (strQueryString.length() > 0) {
                        strReplace$default = strReplace$default + '?' + strQueryString;
                    }
                    Log.v(RequestManager.TAG, "start downloading image \"" + strReplace$default + Typography.quote);
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C00651(strReplace$default, subscriber, null), 2, null);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    if (subscriber.isDisposed()) {
                        return;
                    }
                    subscriber.onError(e);
                }
            }

            /* JADX INFO: renamed from: com.dotscreen.bokit.internal.network.RequestManager$getBitmap$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: RequestManager.kt */
            @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 4, 1})
            @DebugMetadata(c = "com.dotscreen.bokit.internal.network.RequestManager$getBitmap$1$1", f = "RequestManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            static final class C00651 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ String $finalStrUrl;
                final /* synthetic */ SingleEmitter $subscriber;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C00651(String str, SingleEmitter singleEmitter, Continuation continuation) {
                    super(2, continuation);
                    this.$finalStrUrl = str;
                    this.$subscriber = singleEmitter;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkNotNullParameter(completion, "completion");
                    return new C00651(this.$finalStrUrl, this.$subscriber, completion);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C00651) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    final ImageLoader.ImageContainer imageContainer = RequestManager.this.mImageLoader.get(this.$finalStrUrl, new ImageLoader.ImageListener() { // from class: com.dotscreen.bokit.internal.network.RequestManager$getBitmap$1$1$container$1
                        @Override // com.android.volley.Response.ErrorListener
                        public void onErrorResponse(VolleyError error) {
                            Intrinsics.checkNotNullParameter(error, "error");
                            SingleEmitter subscriber = this.this$0.$subscriber;
                            Intrinsics.checkNotNullExpressionValue(subscriber, "subscriber");
                            if (subscriber.isDisposed()) {
                                return;
                            }
                            VolleyError volleyError = error;
                            Log.w(RequestManager.TAG, "   error downloading \"" + this.this$0.$finalStrUrl + Typography.quote, volleyError);
                            this.this$0.$subscriber.onError(volleyError);
                        }

                        @Override // com.android.volley.toolbox.ImageLoader.ImageListener
                        public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                            Intrinsics.checkNotNullParameter(response, "response");
                            SingleEmitter subscriber = this.this$0.$subscriber;
                            Intrinsics.checkNotNullExpressionValue(subscriber, "subscriber");
                            if (subscriber.isDisposed() || response.getBitmap() == null) {
                                return;
                            }
                            Log.v(RequestManager.TAG, "   done downloading \"" + this.this$0.$finalStrUrl + Typography.quote);
                            this.this$0.$subscriber.onSuccess(response.getBitmap());
                        }
                    }, width, height, ImageView.ScaleType.CENTER_CROP);
                    this.$subscriber.setCancellable(new Cancellable() { // from class: com.dotscreen.bokit.internal.network.RequestManager.getBitmap.1.1.1

                        /* JADX INFO: renamed from: com.dotscreen.bokit.internal.network.RequestManager$getBitmap$1$1$1$1, reason: invalid class name and collision with other inner class name */
                        /* JADX INFO: compiled from: RequestManager.kt */
                        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 4, 1})
                        @DebugMetadata(c = "com.dotscreen.bokit.internal.network.RequestManager$getBitmap$1$1$1$1", f = "RequestManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                        static final class C00671 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                            int label;

                            C00671(Continuation continuation) {
                                super(2, continuation);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                                Intrinsics.checkNotNullParameter(completion, "completion");
                                return C00661.this.new C00671(completion);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                return ((C00671) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object obj) {
                                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                if (this.label != 0) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                ResultKt.throwOnFailure(obj);
                                imageContainer.cancelRequest();
                                return Unit.INSTANCE;
                            }
                        }

                        @Override // io.reactivex.functions.Cancellable
                        public final void cancel() {
                            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C00671(null), 2, null);
                        }
                    });
                    return Unit.INSTANCE;
                }
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "Single.create { subscrib…}\n            }\n        }");
        return singleCreate;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Single getString$default(RequestManager requestManager, int i, URL url, Map map, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            map = (Map) null;
        }
        return requestManager.getString(i, url, map);
    }

    public final Single<String> getString(final int method, final URL url, final Map<String, ? extends Object> params) {
        Intrinsics.checkNotNullParameter(url, "url");
        Single<String> singleCreate = Single.create(new SingleOnSubscribe<String>() { // from class: com.dotscreen.bokit.internal.network.RequestManager.getString.1
            /* JADX WARN: Type inference failed for: r0v3, types: [com.dotscreen.bokit.internal.network.RequestManager$getString$1$req$1] */
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter<String> subscriber) {
                Intrinsics.checkNotNullParameter(subscriber, "subscriber");
                final VolleyResponseObserverWrapper volleyResponseObserverWrapper = new VolleyResponseObserverWrapper(subscriber, RequestManager.this.mOnErrorListeners, url);
                final Context context = RequestManager.this.context;
                final int iConvertMethod = RequestManager.this.convertMethod(method);
                final String string = url.toString();
                Intrinsics.checkNotNullExpressionValue(string, "url.toString()");
                final Map map = params;
                final VolleyResponseObserverWrapper volleyResponseObserverWrapper2 = volleyResponseObserverWrapper;
                final VolleyResponseObserverWrapper volleyResponseObserverWrapper3 = volleyResponseObserverWrapper;
                final List list = RequestManager.this.mOnResponseListeners;
                final ?? r0 = new VolleyRequest<String>(context, iConvertMethod, string, map, volleyResponseObserverWrapper2, volleyResponseObserverWrapper3, list) { // from class: com.dotscreen.bokit.internal.network.RequestManager$getString$1$req$1
                    @Override // com.dotscreen.bokit.internal.network.VolleyRequest
                    public /* bridge */ /* synthetic */ String parseData(byte[] bArr, Map map2) {
                        return parseData2(bArr, (Map<String, String>) map2);
                    }

                    @Override // com.dotscreen.bokit.internal.network.VolleyRequest
                    /* JADX INFO: renamed from: parseData, reason: avoid collision after fix types in other method */
                    protected String parseData2(byte[] data, Map<String, String> headers) throws Exception {
                        Intrinsics.checkNotNullParameter(data, "data");
                        Intrinsics.checkNotNullParameter(headers, "headers");
                        Charset charsetForName = Charset.forName(HttpHeaderParser.parseCharset(headers));
                        Intrinsics.checkNotNullExpressionValue(charsetForName, "Charset.forName(HttpHead…er.parseCharset(headers))");
                        return new String(data, charsetForName);
                    }
                };
                RequestManager.this.mVolleyRequestQueue.add((Request) r0);
                subscriber.setCancellable(new Cancellable() { // from class: com.dotscreen.bokit.internal.network.RequestManager.getString.1.1
                    @Override // io.reactivex.functions.Cancellable
                    public final void cancel() {
                        cancel();
                    }
                });
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "Single.create { subscrib… req.cancel() }\n        }");
        return singleCreate;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Single getBytes$default(RequestManager requestManager, int i, URL url, Map map, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            map = (Map) null;
        }
        return requestManager.getBytes(i, url, map);
    }

    public final Single<byte[]> getBytes(final int method, final URL url, final Map<String, ? extends Object> params) {
        Intrinsics.checkNotNullParameter(url, "url");
        Single<byte[]> singleCreate = Single.create(new SingleOnSubscribe<byte[]>() { // from class: com.dotscreen.bokit.internal.network.RequestManager.getBytes.1
            /* JADX WARN: Type inference failed for: r0v3, types: [com.dotscreen.bokit.internal.network.RequestManager$getBytes$1$req$1] */
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter<byte[]> subscriber) {
                Intrinsics.checkNotNullParameter(subscriber, "subscriber");
                final VolleyResponseObserverWrapper volleyResponseObserverWrapper = new VolleyResponseObserverWrapper(subscriber, RequestManager.this.mOnErrorListeners, url);
                final Context context = RequestManager.this.context;
                final int iConvertMethod = RequestManager.this.convertMethod(method);
                final String string = url.toString();
                Intrinsics.checkNotNullExpressionValue(string, "url.toString()");
                final Map map = params;
                final VolleyResponseObserverWrapper volleyResponseObserverWrapper2 = volleyResponseObserverWrapper;
                final VolleyResponseObserverWrapper volleyResponseObserverWrapper3 = volleyResponseObserverWrapper;
                final List list = RequestManager.this.mOnResponseListeners;
                final ?? r0 = new VolleyRequest<byte[]>(context, iConvertMethod, string, map, volleyResponseObserverWrapper2, volleyResponseObserverWrapper3, list) { // from class: com.dotscreen.bokit.internal.network.RequestManager$getBytes$1$req$1
                    @Override // com.dotscreen.bokit.internal.network.VolleyRequest
                    /* JADX INFO: renamed from: parseData, reason: avoid collision after fix types in other method */
                    protected byte[] parseData2(byte[] data, Map<String, String> headers) throws Exception {
                        Intrinsics.checkNotNullParameter(data, "data");
                        Intrinsics.checkNotNullParameter(headers, "headers");
                        return data;
                    }

                    @Override // com.dotscreen.bokit.internal.network.VolleyRequest
                    public /* bridge */ /* synthetic */ byte[] parseData(byte[] bArr, Map map2) {
                        return parseData2(bArr, (Map<String, String>) map2);
                    }
                };
                RequestManager.this.mVolleyRequestQueue.add((Request) r0);
                subscriber.setCancellable(new Cancellable() { // from class: com.dotscreen.bokit.internal.network.RequestManager.getBytes.1.1
                    @Override // io.reactivex.functions.Cancellable
                    public final void cancel() {
                        cancel();
                    }
                });
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "Single.create { subscrib… req.cancel() }\n        }");
        return singleCreate;
    }

    private final RequestQueue newRequestQueue(Context context) {
        String absolutePath = this.mCachePath;
        if (absolutePath == null) {
            File cacheDir = context.getCacheDir();
            Intrinsics.checkNotNullExpressionValue(cacheDir, "context.cacheDir");
            absolutePath = cacheDir.getAbsolutePath();
        }
        File file = new File(absolutePath, "volley");
        RequestQueue requestQueue = new RequestQueue(new DiskBasedCache(file, 20971520), new BasicNetwork((BaseHttpStack) new HurlStack()), 4, new ExecutorDelivery(new Executor() { // from class: com.dotscreen.bokit.internal.network.RequestManager$newRequestQueue$queue$1
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                runnable.run();
            }
        }));
        requestQueue.start();
        return requestQueue;
    }

    public final void addOnRequestErrorListener(OnRequestErrorListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (this.mOnErrorListeners == null) {
            this.mOnErrorListeners = new ArrayList();
        }
        List<OnRequestErrorListener> list = this.mOnErrorListeners;
        Intrinsics.checkNotNull(list);
        list.add(listener);
    }

    public final void removeOnRequestErrorListener(OnRequestErrorListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        List<OnRequestErrorListener> list = this.mOnErrorListeners;
        if (list != null) {
            Intrinsics.checkNotNull(list);
            list.remove(listener);
        }
    }

    public final void addOnRequestResponseListener(OnRequestResponseListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (this.mOnResponseListeners == null) {
            this.mOnResponseListeners = new ArrayList();
        }
        List<OnRequestResponseListener> list = this.mOnResponseListeners;
        Intrinsics.checkNotNull(list);
        list.add(listener);
    }

    /* JADX INFO: compiled from: RequestManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/dotscreen/bokit/internal/network/RequestManager$Companion;", "", "()V", "DELETE", "", VOUtils.HTTP_GET, VOUtils.HTTP_POST, VOUtils.HTTP_PUT, "TAG", "", "kotlin.jvm.PlatformType", "sInstance", "Lcom/dotscreen/bokit/internal/network/RequestManager;", "getSInstance", "()Lcom/dotscreen/bokit/internal/network/RequestManager;", "setSInstance", "(Lcom/dotscreen/bokit/internal/network/RequestManager;)V", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        protected final RequestManager getSInstance() {
            return RequestManager.sInstance;
        }

        protected final void setSInstance(RequestManager requestManager) {
            RequestManager.sInstance = requestManager;
        }
    }
}
