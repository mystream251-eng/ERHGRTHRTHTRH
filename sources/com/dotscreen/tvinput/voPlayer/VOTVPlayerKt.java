package com.dotscreen.tvinput.voPlayer;

import com.viaccessorca.voplayer.VOPlayer;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.SetsKt;

/* JADX INFO: compiled from: VOTVPlayer.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\" \u0010\u0000\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0004"}, d2 = {"RECOVERABLE_ERROR_CODES", "", "Lkotlin/Pair;", "", "tvinput_prodRelease"}, k = 2, mv = {1, 1, 16})
public final class VOTVPlayerKt {
    private static final Set<Pair<Integer, Integer>> RECOVERABLE_ERROR_CODES;

    static {
        Integer numValueOf = Integer.valueOf(VOPlayer.MEDIA_INFO_NETWORK);
        RECOVERABLE_ERROR_CODES = SetsKt.setOf((Object[]) new Pair[]{new Pair(1000, 1009), new Pair(3100, Integer.valueOf(VOPlayer.MEDIA_ERROR_DRM_LICENSE_EXPIRED)), new Pair(3100, Integer.valueOf(VOPlayer.MEDIA_ERROR_DRM_SERVER_NOT_REACHABLE)), new Pair(numValueOf, Integer.valueOf(VOPlayer.MEDIA_INFO_NETWORK_DOWN)), new Pair(numValueOf, Integer.valueOf(VOPlayer.MEDIA_INFO_NETWORK_NO_FILE)), new Pair(numValueOf, Integer.valueOf(VOPlayer.MEDIA_ERROR_NETWORK_BAD_URL)), new Pair(numValueOf, Integer.valueOf(VOPlayer.MEDIA_ERROR_NETWORK_HTTP_UNAUTHORIZED)), new Pair(numValueOf, Integer.valueOf(VOPlayer.MEDIA_ERROR_NETWORK_HTTP_FORBIDDEN)), new Pair(numValueOf, Integer.valueOf(VOPlayer.MEDIA_ERROR_NETWORK_HTTP_REQUEST_TIMEOUT)), new Pair(numValueOf, Integer.valueOf(VOPlayer.MEDIA_ERROR_NETWORK_HTTP_CONFLICT)), new Pair(numValueOf, Integer.valueOf(VOPlayer.MEDIA_ERROR_NETWORK_HTTP_GONE)), new Pair(numValueOf, Integer.valueOf(VOPlayer.MEDIA_ERROR_NETWORK_HTTP_INTERNAL_SERVER_ERROR)), new Pair(numValueOf, Integer.valueOf(VOPlayer.MEDIA_ERROR_NETWORK_HTTP_NOT_IMPLEMENTED)), new Pair(numValueOf, Integer.valueOf(VOPlayer.MEDIA_ERROR_NETWORK_HTTP_BAD_GATEWAY)), new Pair(numValueOf, Integer.valueOf(VOPlayer.MEDIA_ERROR_NETWORK_HTTP_SERVICE_UNAVAILABLE)), new Pair(numValueOf, Integer.valueOf(VOPlayer.MEDIA_ERROR_NETWORK_HTTP_GATEWAY_TIMEOUT)), new Pair(numValueOf, Integer.valueOf(VOPlayer.MEDIA_ERROR_NETWORK_HTTP_VERSION_NOT_SUPPORTED)), new Pair(1, Integer.valueOf((int) 2147483649L)), new Pair(1, Integer.valueOf((int) 2147483650L)), new Pair(1, Integer.valueOf((int) 2214592519L)), new Pair(1, Integer.valueOf((int) 2231369729L))});
    }
}
