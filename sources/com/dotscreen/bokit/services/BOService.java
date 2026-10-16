package com.dotscreen.bokit.services;

import android.content.Context;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Input;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.Query;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.cache.http.HttpCachePolicy;
import com.apollographql.apollo.rx2.Rx2Apollo;
import com.dotscreen.bokit.BOKitKt;
import com.dotscreen.bokit.OnRequestErrorListener;
import com.dotscreen.bokit.OnRequestResponseListener;
import com.dotscreen.bokit.VODCatalogSectionQuery;
import com.dotscreen.bokit.VODContentDetailsListQuery;
import com.dotscreen.bokit.VODContentDetailsQuery;
import com.dotscreen.bokit.VODContentEpisodesQuery;
import com.dotscreen.bokit.VODContentOffersQuery;
import com.dotscreen.bokit.VODContentSearchQuery;
import com.dotscreen.bokit.VODContentSeasonsQuery;
import com.dotscreen.bokit.VODPlaybackInfosQuery;
import com.dotscreen.bokit.VODSearchAutocompleteQuery;
import com.dotscreen.bokit.VODSectionItemsQuery;
import com.dotscreen.bokit.VODSectionSubsectionsQuery;
import com.dotscreen.bokit.VODTopLevelServicesQuery;
import com.dotscreen.bokit.internal.network.RequestManager;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okio.Buffer;
import org.json.JSONObject;

/* JADX INFO: compiled from: BOService.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0094\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u00106\u001a\u0002072\u0006\u00108\u001a\u00020\u0012J\u000e\u00109\u001a\u0002072\u0006\u00108\u001a\u00020%JG\u0010:\u001a\b\u0012\u0004\u0012\u0002H<0;\"\b\b\u0000\u0010=*\u00020>\"\u0004\b\u0001\u0010<\"\b\b\u0002\u0010?*\u00020@2\u0018\u0010A\u001a\u0014\u0012\u0004\u0012\u0002H=\u0012\u0004\u0012\u0002H<\u0012\u0004\u0012\u0002H?0BH\u0010¢\u0006\u0002\bCJM\u0010D\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H<0E0;\"\b\b\u0000\u0010=*\u00020>\"\u0004\b\u0001\u0010<\"\b\b\u0002\u0010?*\u00020@2\u0018\u0010A\u001a\u0014\u0012\u0004\u0012\u0002H=\u0012\u0004\u0012\u0002H<\u0012\u0004\u0012\u0002H?0BH\u0010¢\u0006\u0002\bFJ\u0016\u0010G\u001a\b\u0012\u0004\u0012\u00020H0;2\u0006\u0010I\u001a\u00020\nH\u0016J/\u0010J\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010K0;2\u0006\u0010L\u001a\u00020\n2\u0006\u0010M\u001a\u00020NH\u0010¢\u0006\u0002\bOJ\u001f\u0010P\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010K0;H\u0010¢\u0006\u0002\bQJ'\u0010R\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010K0;2\u0006\u0010S\u001a\u00020\nH\u0010¢\u0006\u0002\bTJ\u001f\u0010U\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010K0;H\u0010¢\u0006\u0002\bVJ/\u0010W\u001a\b\u0012\u0004\u0012\u00020H0;2\u0006\u0010A\u001a\u00020\n2\u0010\b\u0002\u0010X\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010YH\u0010¢\u0006\u0004\bZ\u0010[J/\u0010\\\u001a\b\u0012\u0004\u0012\u00020H0;2\u0006\u0010A\u001a\u00020\n2\u0010\b\u0002\u0010X\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010YH\u0010¢\u0006\u0004\b]\u0010[J!\u0010^\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020_0E0;2\u0006\u0010`\u001a\u00020\nH\u0010¢\u0006\u0002\baJ)\u0010^\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020_0E0;2\u0006\u0010`\u001a\u00020\n2\u0006\u0010b\u001a\u00020\nH\u0010¢\u0006\u0002\baJ\u001d\u0010c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010d0;2\u0006\u0010e\u001a\u00020\nH\u0010¢\u0006\u0002\bfJ%\u0010g\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010h0;2\f\u0010i\u001a\b\u0012\u0004\u0012\u00020\n0YH\u0010¢\u0006\u0004\bj\u0010kJ\u001b\u0010l\u001a\b\u0012\u0004\u0012\u00020m0;2\u0006\u0010e\u001a\u00020\nH\u0000¢\u0006\u0002\bnJ\u001d\u0010o\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010p0;2\u0006\u0010e\u001a\u00020\nH\u0000¢\u0006\u0002\bqJ)\u0010r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020s0E0;2\u0006\u0010`\u001a\u00020\n2\u0006\u0010A\u001a\u00020\nH\u0010¢\u0006\u0002\btJ\u001b\u0010u\u001a\b\u0012\u0004\u0012\u00020v0;2\u0006\u0010e\u001a\u00020\nH\u0000¢\u0006\u0002\bwJ\u001b\u0010x\u001a\b\u0012\u0004\u0012\u00020y0;2\u0006\u0010z\u001a\u00020\nH\u0000¢\u0006\u0002\b{J)\u0010|\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020}0E0;2\u0006\u0010`\u001a\u00020\n2\u0006\u0010A\u001a\u00020\nH\u0010¢\u0006\u0002\b~J,\u0010\u007f\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u0080\u00010E0;2\u0006\u0010`\u001a\u00020\n2\u0007\u0010\u0081\u0001\u001a\u00020\nH\u0010¢\u0006\u0003\b\u0082\u0001J-\u0010\u0083\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u0084\u00010E0;2\u0006\u0010`\u001a\u00020\n2\u0007\u0010\u0081\u0001\u001a\u00020\nH\u0010¢\u0006\u0003\b\u0085\u0001J\u0016\u0010\u0086\u0001\u001a\t\u0012\u0005\u0012\u00030\u0087\u00010;H\u0010¢\u0006\u0003\b\u0088\u0001J\u0010\u0010\u0089\u0001\u001a\u0002072\u0007\u0010\u008a\u0001\u001a\u00020\u0005J\u0010\u0010\u008b\u0001\u001a\u00020\u00052\u0007\u0010\u008c\u0001\u001a\u00020\nR\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n \u000b*\u0004\u0018\u00010\r0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0013\u001a\u00020\u00148FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0019\u001a\u0004\u0018\u00010\u001a8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u0018\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001e\u001a\u00020\u001fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010'\u001a\u0004\u0018\u00010\n2\b\u0010&\u001a\u0004\u0018\u00010\n@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001d\u0010,\u001a\u0004\u0018\u00010-8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b0\u0010\u0018\u001a\u0004\b.\u0010/R\u001b\u00101\u001a\u0002028FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b5\u0010\u0018\u001a\u0004\b3\u00104¨\u0006\u008d\u0001"}, d2 = {"Lcom/dotscreen/bokit/services/BOService;", "", "context", "Landroid/content/Context;", "baseUrl", "Ljava/net/URL;", "plugin", "Lcom/dotscreen/bokit/services/BOServicePlugin;", "(Landroid/content/Context;Ljava/net/URL;Lcom/dotscreen/bokit/services/BOServicePlugin;)V", "TAG", "", "kotlin.jvm.PlatformType", "apolloClient", "Lcom/apollographql/apollo/ApolloClient;", "getContext$bokit_android_release", "()Landroid/content/Context;", "errorListeners", "", "Lcom/dotscreen/bokit/OnRequestErrorListener;", "liveTVService", "Lcom/dotscreen/bokit/services/LiveTVService;", "getLiveTVService", "()Lcom/dotscreen/bokit/services/LiveTVService;", "liveTVService$delegate", "Lkotlin/Lazy;", "pvrService", "Lcom/dotscreen/bokit/services/PVRService;", "getPvrService", "()Lcom/dotscreen/bokit/services/PVRService;", "pvrService$delegate", "requestManager", "Lcom/dotscreen/bokit/internal/network/RequestManager;", "getRequestManager$bokit_android_release", "()Lcom/dotscreen/bokit/internal/network/RequestManager;", "setRequestManager$bokit_android_release", "(Lcom/dotscreen/bokit/internal/network/RequestManager;)V", "responseListener", "Lcom/dotscreen/bokit/OnRequestResponseListener;", "value", "sharedCacheDirectory", "getSharedCacheDirectory$bokit_android_release", "()Ljava/lang/String;", "setSharedCacheDirectory$bokit_android_release", "(Ljava/lang/String;)V", "userService", "Lcom/dotscreen/bokit/services/UserService;", "getUserService", "()Lcom/dotscreen/bokit/services/UserService;", "userService$delegate", "vodCatalogService", "Lcom/dotscreen/bokit/services/VODCatalogService;", "getVodCatalogService", "()Lcom/dotscreen/bokit/services/VODCatalogService;", "vodCatalogService$delegate", "addOnRequestErrorListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "addOnRequestResponseListener", "fetchGraphQL", "Lio/reactivex/Single;", ExifInterface.GPS_DIRECTION_TRUE, "D", "Lcom/apollographql/apollo/api/Operation$Data;", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Lcom/apollographql/apollo/api/Operation$Variables;", SearchIntents.EXTRA_QUERY, "Lcom/apollographql/apollo/api/Query;", "fetchGraphQL$bokit_android_release", "fetchGraphQLRawResponse", "Lcom/apollographql/apollo/api/Response;", "fetchGraphQLRawResponse$bokit_android_release", "fetchLive", "Lorg/json/JSONObject;", "channelId", "fetchTVAiringSegment", "", "lineupId", "segmentId", "", "fetchTVAiringSegment$bokit_android_release", "fetchTVAllLineups", "fetchTVAllLineups$bokit_android_release", "fetchTVProgram", "programId", "fetchTVProgram$bokit_android_release", "fetchTVReferences", "fetchTVReferences$bokit_android_release", "fetchTVSearch", "lineupIds", "", "fetchTVSearch$bokit_android_release", "(Ljava/lang/String;[Ljava/lang/String;)Lio/reactivex/Single;", "fetchTVSearchAutocomplete", "fetchTVSearchAutocomplete$bokit_android_release", "fetchVODCatalogSection", "Lcom/dotscreen/bokit/VODCatalogSectionQuery$Data;", "catalogId", "fetchVODCatalogSection$bokit_android_release", "collectionId", "fetchVODContentDetails", "Lcom/dotscreen/bokit/VODContentDetailsQuery$Data;", "itemId", "fetchVODContentDetails$bokit_android_release", "fetchVODContentDetailsList", "Lcom/dotscreen/bokit/VODContentDetailsListQuery$Data;", "itemsIds", "fetchVODContentDetailsList$bokit_android_release", "([Ljava/lang/String;)Lio/reactivex/Single;", "fetchVODContentEpisodes", "Lcom/dotscreen/bokit/VODContentEpisodesQuery$Data;", "fetchVODContentEpisodes$bokit_android_release", "fetchVODContentOffers", "Lcom/dotscreen/bokit/VODContentOffersQuery$Data;", "fetchVODContentOffers$bokit_android_release", "fetchVODContentSearch", "Lcom/dotscreen/bokit/VODContentSearchQuery$Data;", "fetchVODContentSearch$bokit_android_release", "fetchVODContentSeasons", "Lcom/dotscreen/bokit/VODContentSeasonsQuery$Data;", "fetchVODContentSeasons$bokit_android_release", "fetchVODPlaybackInfos", "Lcom/dotscreen/bokit/VODPlaybackInfosQuery$Data;", "streamId", "fetchVODPlaybackInfos$bokit_android_release", "fetchVODSearchAutoComplete", "Lcom/dotscreen/bokit/VODSearchAutocompleteQuery$Data;", "fetchVODSearchAutoComplete$bokit_android_release", "fetchVODSectionItems", "Lcom/dotscreen/bokit/VODSectionItemsQuery$Data;", "sectionId", "fetchVODSectionItems$bokit_android_release", "fetchVODSectionSubSection", "Lcom/dotscreen/bokit/VODSectionSubsectionsQuery$Data;", "fetchVODSectionSubSection$bokit_android_release", "fetchVODTopLevelServices", "Lcom/dotscreen/bokit/VODTopLevelServicesQuery$Data;", "fetchVODTopLevelServices$bokit_android_release", "invalidateCache", ImagesContract.URL, "resolveUrl", "path", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public class BOService {
    private final String TAG;
    private final ApolloClient apolloClient;
    private final URL baseUrl;
    private final Context context;
    private final List<OnRequestErrorListener> errorListeners;

    /* JADX INFO: renamed from: liveTVService$delegate, reason: from kotlin metadata */
    private final Lazy liveTVService;
    private final BOServicePlugin plugin;

    /* JADX INFO: renamed from: pvrService$delegate, reason: from kotlin metadata */
    private final Lazy pvrService;
    private RequestManager requestManager;
    private final List<OnRequestResponseListener> responseListener;
    private String sharedCacheDirectory;

    /* JADX INFO: renamed from: userService$delegate, reason: from kotlin metadata */
    private final Lazy userService;

    /* JADX INFO: renamed from: vodCatalogService$delegate, reason: from kotlin metadata */
    private final Lazy vodCatalogService;

    public BOService(Context context, URL url) {
        this(context, url, null, 4, null);
    }

    public final LiveTVService getLiveTVService() {
        return (LiveTVService) this.liveTVService.getValue();
    }

    public final PVRService getPvrService() {
        return (PVRService) this.pvrService.getValue();
    }

    public final UserService getUserService() {
        return (UserService) this.userService.getValue();
    }

    public final VODCatalogService getVodCatalogService() {
        return (VODCatalogService) this.vodCatalogService.getValue();
    }

    public BOService(Context context, URL baseUrl, BOServicePlugin plugin) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
        Intrinsics.checkNotNullParameter(plugin, "plugin");
        this.context = context;
        this.baseUrl = baseUrl;
        this.plugin = plugin;
        this.TAG = BOService.class.getSimpleName();
        this.errorListeners = new ArrayList();
        this.responseListener = new ArrayList();
        this.apolloClient = ApolloClient.builder().serverUrl(this.baseUrl + "/api/graphql/v1/").defaultHttpCachePolicy(HttpCachePolicy.CACHE_FIRST).useHttpGetMethodForPersistedQueries(true).enableAutoPersistedQueries(true).okHttpClient(new OkHttpClient.Builder().eventListener(new EventListener() { // from class: com.dotscreen.bokit.services.BOService$apolloClient$1
            @Override // okhttp3.EventListener
            public void callStart(Call call) {
                Intrinsics.checkNotNullParameter(call, "call");
                super.callStart(call);
                Log.v(this.this$0.TAG, "start " + call.request());
                Buffer buffer = new Buffer();
                try {
                    RequestBody requestBodyBody = call.request().body();
                    if (requestBodyBody != null) {
                        requestBodyBody.writeTo(buffer);
                    }
                    if (buffer.size() > 0) {
                        String str = this.this$0.TAG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("   body = ");
                        Charset charsetDefaultCharset = Charset.defaultCharset();
                        Intrinsics.checkNotNullExpressionValue(charsetDefaultCharset, "Charset.defaultCharset()");
                        sb.append(buffer.readString(charsetDefaultCharset));
                        Log.v(str, sb.toString());
                    }
                } catch (Exception e) {
                    Log.w(this.this$0.TAG, "Exception", e);
                }
            }

            @Override // okhttp3.EventListener
            public void callEnd(Call call) {
                Intrinsics.checkNotNullParameter(call, "call");
                super.callEnd(call);
                Log.v(this.this$0.TAG, "end " + call.request());
                for (OnRequestResponseListener onRequestResponseListener : this.this$0.responseListener) {
                    try {
                        String string = call.request().url().toString();
                        Intrinsics.checkNotNullExpressionValue(string, "call.request().url().toString()");
                        onRequestResponseListener.onNetworkResponse(string, false);
                    } catch (Exception e) {
                        Log.w(this.this$0.TAG, "Log in CallEnd failed " + e);
                    }
                }
            }

            @Override // okhttp3.EventListener
            public void callFailed(Call call, IOException ioe) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(ioe, "ioe");
                super.callFailed(call, ioe);
                Log.w(this.this$0.TAG, "failed " + ioe + ' ' + call.request());
                Iterator it = this.this$0.errorListeners.iterator();
                while (it.hasNext()) {
                    ((OnRequestErrorListener) it.next()).onRequestError(ioe, call.request().url().url());
                }
            }
        }).connectTimeout(BOKitKt.getDefaultGraphQLConnectionTimeout(), TimeUnit.MILLISECONDS).readTimeout(BOKitKt.getDefaultGraphQLConnectionTimeout(), TimeUnit.MILLISECONDS).callTimeout(BOKitKt.getDefaultGraphQLConnectionTimeout(), TimeUnit.MILLISECONDS).build()).build();
        this.requestManager = new RequestManager(this.context, null);
        this.liveTVService = LazyKt.lazy(new Function0<LiveTVService>() { // from class: com.dotscreen.bokit.services.BOService$liveTVService$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LiveTVService invoke() {
                return this.this$0.plugin.createLiveTVService(this.this$0);
            }
        });
        this.userService = LazyKt.lazy(new Function0<UserService>() { // from class: com.dotscreen.bokit.services.BOService$userService$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final UserService invoke() {
                return this.this$0.plugin.createUserService(this.this$0);
            }
        });
        this.vodCatalogService = LazyKt.lazy(new Function0<VODCatalogService>() { // from class: com.dotscreen.bokit.services.BOService$vodCatalogService$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final VODCatalogService invoke() {
                return this.this$0.plugin.createVODCatalogService(this.this$0);
            }
        });
        this.pvrService = LazyKt.lazy(new Function0<PVRService>() { // from class: com.dotscreen.bokit.services.BOService$pvrService$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PVRService invoke() {
                return this.this$0.plugin.createPVRService(this.this$0);
            }
        });
    }

    /* JADX INFO: renamed from: getContext$bokit_android_release, reason: from getter */
    public final Context getContext() {
        return this.context;
    }

    public /* synthetic */ BOService(Context context, URL url, BOServicePlugin bOServicePlugin, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, url, (i & 4) != 0 ? new BOServicePlugin() { // from class: com.dotscreen.bokit.services.BOService.1
            @Override // com.dotscreen.bokit.services.BOServicePlugin
            public LiveTVService createLiveTVService(BOService boService) {
                Intrinsics.checkNotNullParameter(boService, "boService");
                return BOServicePlugin.DefaultImpls.createLiveTVService(this, boService);
            }

            @Override // com.dotscreen.bokit.services.BOServicePlugin
            public PVRService createPVRService(BOService boService) {
                Intrinsics.checkNotNullParameter(boService, "boService");
                return BOServicePlugin.DefaultImpls.createPVRService(this, boService);
            }

            @Override // com.dotscreen.bokit.services.BOServicePlugin
            public UserService createUserService(BOService boService) {
                Intrinsics.checkNotNullParameter(boService, "boService");
                return BOServicePlugin.DefaultImpls.createUserService(this, boService);
            }

            @Override // com.dotscreen.bokit.services.BOServicePlugin
            public VODCatalogService createVODCatalogService(BOService boService) {
                Intrinsics.checkNotNullParameter(boService, "boService");
                return BOServicePlugin.DefaultImpls.createVODCatalogService(this, boService);
            }
        } : bOServicePlugin);
    }

    /* JADX INFO: renamed from: getRequestManager$bokit_android_release, reason: from getter */
    public final RequestManager getRequestManager() {
        return this.requestManager;
    }

    public final void setRequestManager$bokit_android_release(RequestManager requestManager) {
        Intrinsics.checkNotNullParameter(requestManager, "<set-?>");
        this.requestManager = requestManager;
    }

    /* JADX INFO: renamed from: getSharedCacheDirectory$bokit_android_release, reason: from getter */
    public final String getSharedCacheDirectory() {
        return this.sharedCacheDirectory;
    }

    public final void setSharedCacheDirectory$bokit_android_release(String str) {
        this.sharedCacheDirectory = str;
        this.requestManager = new RequestManager(this.context, str);
    }

    public final URL resolveUrl(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        if (StringsKt.startsWith$default(path, "http", false, 2, (Object) null)) {
            return new URL(path);
        }
        if (!StringsKt.startsWith$default(path, "/", false, 2, (Object) null)) {
            path = '/' + path;
        }
        return new URL(this.baseUrl + path);
    }

    public final void invalidateCache(URL url) {
        Intrinsics.checkNotNullParameter(url, "url");
        this.requestManager.invalidateCache(url);
    }

    public Single<Map<Object, Object>> fetchTVAllLineups$bokit_android_release() {
        return RequestManager.getMessagePackMap$default(this.requestManager, 0, resolveUrl("api/mobile/v1/lineups/all.amtv"), null, 4, null);
    }

    public Single<Map<Object, Object>> fetchTVProgram$bokit_android_release(String programId) {
        Intrinsics.checkNotNullParameter(programId, "programId");
        return RequestManager.getMessagePackMap$default(this.requestManager, 0, resolveUrl("api/mobile/v1/programs/" + programId + ".amtv"), null, 4, null);
    }

    public Single<JSONObject> fetchLive(String channelId) {
        Intrinsics.checkNotNullParameter(channelId, "channelId");
        return RequestManager.getJSONObject$default(this.requestManager, 0, resolveUrl("api/tv/v1/channels/" + channelId + "/live.json"), null, 4, null);
    }

    public Single<Map<Object, Object>> fetchTVAiringSegment$bokit_android_release(String lineupId, long segmentId) {
        Intrinsics.checkNotNullParameter(lineupId, "lineupId");
        return RequestManager.getMessagePackMap$default(this.requestManager, 0, resolveUrl("api/mobile/v1/lineups/" + lineupId + "/schedules/" + segmentId + ".amtv"), null, 4, null);
    }

    public Single<Map<Object, Object>> fetchTVReferences$bokit_android_release() {
        return RequestManager.getMessagePackMap$default(this.requestManager, 0, resolveUrl("api/mobile/v1/reference.amtv"), null, 4, null);
    }

    public static /* synthetic */ Single fetchTVSearch$bokit_android_release$default(BOService bOService, String str, String[] strArr, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fetchTVSearch");
        }
        if ((i & 2) != 0) {
            strArr = (String[]) null;
        }
        return bOService.fetchTVSearch$bokit_android_release(str, strArr);
    }

    public Single<JSONObject> fetchTVSearch$bokit_android_release(String query, String[] lineupIds) {
        Intrinsics.checkNotNullParameter(query, "query");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("q", query);
        if (lineupIds != null) {
            if (!(lineupIds.length == 0)) {
                linkedHashMap.put("packages", ArraysKt.joinToString$default(lineupIds, ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
            }
        }
        return this.requestManager.getJSONObject(0, resolveUrl("api/tv/v1/search/matches.json"), linkedHashMap);
    }

    public static /* synthetic */ Single fetchTVSearchAutocomplete$bokit_android_release$default(BOService bOService, String str, String[] strArr, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fetchTVSearchAutocomplete");
        }
        if ((i & 2) != 0) {
            strArr = (String[]) null;
        }
        return bOService.fetchTVSearchAutocomplete$bokit_android_release(str, strArr);
    }

    public Single<JSONObject> fetchTVSearchAutocomplete$bokit_android_release(String query, String[] lineupIds) {
        Intrinsics.checkNotNullParameter(query, "query");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("q", query);
        if (lineupIds != null) {
            if (!(lineupIds.length == 0)) {
                linkedHashMap.put("packages", ArraysKt.joinToString$default(lineupIds, ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
            }
        }
        return this.requestManager.getJSONObject(0, resolveUrl("api/tv/v1/search/autocomplete.json"), linkedHashMap);
    }

    public Single<VODTopLevelServicesQuery.Data> fetchVODTopLevelServices$bokit_android_release() {
        Single<VODTopLevelServicesQuery.Data> singleDefer = Single.defer(new Callable<SingleSource<? extends VODTopLevelServicesQuery.Data>>() { // from class: com.dotscreen.bokit.services.BOService$fetchVODTopLevelServices$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public final SingleSource<? extends VODTopLevelServicesQuery.Data> call() {
                return this.this$0.fetchGraphQL$bokit_android_release(new VODTopLevelServicesQuery());
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleDefer, "Single.defer {\n         …ervicesQuery())\n        }");
        return singleDefer;
    }

    public Single<Response<VODCatalogSectionQuery.Data>> fetchVODCatalogSection$bokit_android_release(final String catalogId) {
        Intrinsics.checkNotNullParameter(catalogId, "catalogId");
        Single<Response<VODCatalogSectionQuery.Data>> singleDefer = Single.defer(new Callable<SingleSource<? extends Response<VODCatalogSectionQuery.Data>>>() { // from class: com.dotscreen.bokit.services.BOService$fetchVODCatalogSection$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public final SingleSource<? extends Response<VODCatalogSectionQuery.Data>> call() {
                return this.this$0.fetchGraphQLRawResponse$bokit_android_release(new VODCatalogSectionQuery(catalogId, Input.INSTANCE.absent()));
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleDefer, "Single.defer {\n         …nput.absent()))\n        }");
        return singleDefer;
    }

    public Single<Response<VODCatalogSectionQuery.Data>> fetchVODCatalogSection$bokit_android_release(final String catalogId, final String collectionId) {
        Intrinsics.checkNotNullParameter(catalogId, "catalogId");
        Intrinsics.checkNotNullParameter(collectionId, "collectionId");
        Single<Response<VODCatalogSectionQuery.Data>> singleDefer = Single.defer(new Callable<SingleSource<? extends Response<VODCatalogSectionQuery.Data>>>() { // from class: com.dotscreen.bokit.services.BOService$fetchVODCatalogSection$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public final SingleSource<? extends Response<VODCatalogSectionQuery.Data>> call() {
                return this.this$0.fetchGraphQLRawResponse$bokit_android_release(new VODCatalogSectionQuery(catalogId, Input.INSTANCE.optional(collectionId)));
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleDefer, "Single.defer {\n         …collectionId)))\n        }");
        return singleDefer;
    }

    public Single<Response<VODSectionSubsectionsQuery.Data>> fetchVODSectionSubSection$bokit_android_release(final String catalogId, final String sectionId) {
        Intrinsics.checkNotNullParameter(catalogId, "catalogId");
        Intrinsics.checkNotNullParameter(sectionId, "sectionId");
        Single<Response<VODSectionSubsectionsQuery.Data>> singleDefer = Single.defer(new Callable<SingleSource<? extends Response<VODSectionSubsectionsQuery.Data>>>() { // from class: com.dotscreen.bokit.services.BOService$fetchVODSectionSubSection$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public final SingleSource<? extends Response<VODSectionSubsectionsQuery.Data>> call() {
                return this.this$0.fetchGraphQLRawResponse$bokit_android_release(new VODSectionSubsectionsQuery(catalogId, sectionId));
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleDefer, "Single.defer {\n         …Id, sectionId))\n        }");
        return singleDefer;
    }

    public Single<Response<VODSectionItemsQuery.Data>> fetchVODSectionItems$bokit_android_release(final String catalogId, final String sectionId) {
        Intrinsics.checkNotNullParameter(catalogId, "catalogId");
        Intrinsics.checkNotNullParameter(sectionId, "sectionId");
        Single<Response<VODSectionItemsQuery.Data>> singleDefer = Single.defer(new Callable<SingleSource<? extends Response<VODSectionItemsQuery.Data>>>() { // from class: com.dotscreen.bokit.services.BOService$fetchVODSectionItems$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public final SingleSource<? extends Response<VODSectionItemsQuery.Data>> call() {
                return this.this$0.fetchGraphQLRawResponse$bokit_android_release(new VODSectionItemsQuery(catalogId, sectionId));
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleDefer, "Single.defer {\n         …Id, sectionId))\n        }");
        return singleDefer;
    }

    public Single<VODContentDetailsQuery.Data> fetchVODContentDetails$bokit_android_release(final String itemId) {
        Intrinsics.checkNotNullParameter(itemId, "itemId");
        Single<VODContentDetailsQuery.Data> singleDefer = Single.defer(new Callable<SingleSource<? extends VODContentDetailsQuery.Data>>() { // from class: com.dotscreen.bokit.services.BOService$fetchVODContentDetails$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public final SingleSource<? extends VODContentDetailsQuery.Data> call() {
                return this.this$0.fetchGraphQL$bokit_android_release(new VODContentDetailsQuery(itemId));
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleDefer, "Single.defer {\n         …sQuery(itemId))\n        }");
        return singleDefer;
    }

    public Single<VODContentDetailsListQuery.Data> fetchVODContentDetailsList$bokit_android_release(final String[] itemsIds) {
        Intrinsics.checkNotNullParameter(itemsIds, "itemsIds");
        Single<VODContentDetailsListQuery.Data> singleDefer = Single.defer(new Callable<SingleSource<? extends VODContentDetailsListQuery.Data>>() { // from class: com.dotscreen.bokit.services.BOService$fetchVODContentDetailsList$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public final SingleSource<? extends VODContentDetailsListQuery.Data> call() {
                return this.this$0.fetchGraphQL$bokit_android_release(new VODContentDetailsListQuery(ArraysKt.toList(itemsIds)));
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleDefer, "Single.defer {\n         …sIds.toList()))\n        }");
        return singleDefer;
    }

    public Single<Response<VODContentSearchQuery.Data>> fetchVODContentSearch$bokit_android_release(final String catalogId, final String query) {
        Intrinsics.checkNotNullParameter(catalogId, "catalogId");
        Intrinsics.checkNotNullParameter(query, "query");
        Single<Response<VODContentSearchQuery.Data>> singleDefer = Single.defer(new Callable<SingleSource<? extends Response<VODContentSearchQuery.Data>>>() { // from class: com.dotscreen.bokit.services.BOService$fetchVODContentSearch$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public final SingleSource<? extends Response<VODContentSearchQuery.Data>> call() {
                return this.this$0.fetchGraphQLRawResponse$bokit_android_release(new VODContentSearchQuery(catalogId, query));
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleDefer, "Single.defer {\n         …alogId, query))\n        }");
        return singleDefer;
    }

    public Single<Response<VODSearchAutocompleteQuery.Data>> fetchVODSearchAutoComplete$bokit_android_release(final String catalogId, final String query) {
        Intrinsics.checkNotNullParameter(catalogId, "catalogId");
        Intrinsics.checkNotNullParameter(query, "query");
        Single<Response<VODSearchAutocompleteQuery.Data>> singleDefer = Single.defer(new Callable<SingleSource<? extends Response<VODSearchAutocompleteQuery.Data>>>() { // from class: com.dotscreen.bokit.services.BOService$fetchVODSearchAutoComplete$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public final SingleSource<? extends Response<VODSearchAutocompleteQuery.Data>> call() {
                return this.this$0.fetchGraphQLRawResponse$bokit_android_release(new VODSearchAutocompleteQuery(catalogId, query));
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleDefer, "Single.defer {\n         …alogId, query))\n        }");
        return singleDefer;
    }

    public final Single<VODContentOffersQuery.Data> fetchVODContentOffers$bokit_android_release(final String itemId) {
        Intrinsics.checkNotNullParameter(itemId, "itemId");
        Single<VODContentOffersQuery.Data> singleDefer = Single.defer(new Callable<SingleSource<? extends VODContentOffersQuery.Data>>() { // from class: com.dotscreen.bokit.services.BOService$fetchVODContentOffers$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public final SingleSource<? extends VODContentOffersQuery.Data> call() {
                return this.this$0.fetchGraphQL$bokit_android_release(new VODContentOffersQuery(itemId));
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleDefer, "Single.defer {\n         …sQuery(itemId))\n        }");
        return singleDefer;
    }

    public final Single<VODContentSeasonsQuery.Data> fetchVODContentSeasons$bokit_android_release(final String itemId) {
        Intrinsics.checkNotNullParameter(itemId, "itemId");
        Single<VODContentSeasonsQuery.Data> singleDefer = Single.defer(new Callable<SingleSource<? extends VODContentSeasonsQuery.Data>>() { // from class: com.dotscreen.bokit.services.BOService$fetchVODContentSeasons$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public final SingleSource<? extends VODContentSeasonsQuery.Data> call() {
                return this.this$0.fetchGraphQL$bokit_android_release(new VODContentSeasonsQuery(itemId));
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleDefer, "Single.defer {\n         …sQuery(itemId))\n        }");
        return singleDefer;
    }

    public final Single<VODContentEpisodesQuery.Data> fetchVODContentEpisodes$bokit_android_release(final String itemId) {
        Intrinsics.checkNotNullParameter(itemId, "itemId");
        Single<VODContentEpisodesQuery.Data> singleDefer = Single.defer(new Callable<SingleSource<? extends VODContentEpisodesQuery.Data>>() { // from class: com.dotscreen.bokit.services.BOService$fetchVODContentEpisodes$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public final SingleSource<? extends VODContentEpisodesQuery.Data> call() {
                return this.this$0.fetchGraphQL$bokit_android_release(new VODContentEpisodesQuery(itemId));
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleDefer, "Single.defer {\n         …sQuery(itemId))\n        }");
        return singleDefer;
    }

    public final Single<VODPlaybackInfosQuery.Data> fetchVODPlaybackInfos$bokit_android_release(final String streamId) {
        Intrinsics.checkNotNullParameter(streamId, "streamId");
        Single<VODPlaybackInfosQuery.Data> singleDefer = Single.defer(new Callable<SingleSource<? extends VODPlaybackInfosQuery.Data>>() { // from class: com.dotscreen.bokit.services.BOService$fetchVODPlaybackInfos$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public final SingleSource<? extends VODPlaybackInfosQuery.Data> call() {
                return this.this$0.fetchGraphQL$bokit_android_release(new VODPlaybackInfosQuery(streamId, Input.INSTANCE.absent()));
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleDefer, "Single.defer {\n         …nput.absent()))\n        }");
        return singleDefer;
    }

    public <D extends Operation.Data, T, V extends Operation.Variables> Single<Response<T>> fetchGraphQLRawResponse$bokit_android_release(Query<D, T, V> query) {
        Intrinsics.checkNotNullParameter(query, "query");
        Single<Response<T>> singleFirstOrError = Rx2Apollo.from(this.apolloClient.query(query)).firstOrError();
        Intrinsics.checkNotNullExpressionValue(singleFirstOrError, "Rx2Apollo.from(apolloCli…          .firstOrError()");
        return singleFirstOrError;
    }

    public <D extends Operation.Data, T, V extends Operation.Variables> Single<T> fetchGraphQL$bokit_android_release(Query<D, T, V> query) {
        Intrinsics.checkNotNullParameter(query, "query");
        Single<T> single = (Single<T>) Rx2Apollo.from(this.apolloClient.query(query)).firstOrError().map(new Function<Response<T>, T>() { // from class: com.dotscreen.bokit.services.BOService$fetchGraphQL$1
            @Override // io.reactivex.functions.Function
            public final T apply(Response<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.data();
            }
        });
        Intrinsics.checkNotNullExpressionValue(single, "Rx2Apollo.from(apolloCli…       .map { it.data() }");
        return single;
    }

    public final void addOnRequestErrorListener(OnRequestErrorListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.errorListeners.add(listener);
        this.requestManager.addOnRequestErrorListener(listener);
    }

    public final void addOnRequestResponseListener(OnRequestResponseListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.responseListener.add(listener);
        this.requestManager.addOnRequestResponseListener(listener);
    }
}
