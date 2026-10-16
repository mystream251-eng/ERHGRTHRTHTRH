package com.dotscreen.portal.player;

import com.viaccessorca.voplayer.VOPlayer;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.SetsKt;

/* JADX INFO: compiled from: VOPlayerAdapter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\" \u0010\u0000\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0004"}, d2 = {"RECOVERABLE_ERROR_CODES", "", "Lkotlin/Pair;", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 2, mv = {1, 1, 16})
public final class VOPlayerAdapterKt {
    private static final Set<Pair<Integer, Integer>> RECOVERABLE_ERROR_CODES = SetsKt.setOf((Object[]) new Pair[]{new Pair(Integer.valueOf(VOPlayer.MEDIA_INFO_NETWORK), Integer.valueOf(VOPlayer.MEDIA_INFO_NETWORK_DOWN)), new Pair(3100, Integer.valueOf(VOPlayer.MEDIA_ERROR_DRM_LICENSE_EXPIRED)), new Pair(1, Integer.valueOf((int) 2214592519L))});
}
