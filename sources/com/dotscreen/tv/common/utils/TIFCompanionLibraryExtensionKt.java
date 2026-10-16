package com.dotscreen.tv.common.utils;

import android.util.Log;
import com.dotscreen.bokit.model.Lineup;
import com.dotscreen.bokit.services.BOService;
import com.dotscreen.tv.common.bokit.ChannelExtraCaster;
import com.dotscreen.tv.common.bokit.RightVChannel;
import com.google.android.media.tv.companionlibrary.model.Channel;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.json.JSONObject;

/* JADX INFO: compiled from: TIFCompanionLibraryExtension.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010%\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0006\u0010\u0007\u001a\u00020\b\u001a\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u0001H\u0000\u001a\u0014\u0010\r\u001a\u0004\u0018\u00010\u000e*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u0014\u0010\u0012\u001a\u0004\u0018\u00010\u0001*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082D¢\u0006\u0002\n\u0000\"\u001c\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001c\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"TAG", "", "__ChannelOptimizerIdMap", "", "", "sDVBToChannelIdMap", "", "clearCache", "", "dvbAreEquals", "", "dvb1", "dvb2", "getOptimizerChannel", "Lcom/dotscreen/bokit/model/Channel;", "Lcom/google/android/media/tv/companionlibrary/model/Channel;", "boService", "Lcom/dotscreen/bokit/services/BOService;", "getOptimizerChannelId", "common_prodRelease"}, k = 2, mv = {1, 1, 16})
public final class TIFCompanionLibraryExtensionKt {
    private static final String TAG = "TIFCLE";
    private static final Map<Integer, String> __ChannelOptimizerIdMap = new LinkedHashMap();
    private static Map<String, String> sDVBToChannelIdMap;

    public static final boolean dvbAreEquals(String dvb1, String dvb2) {
        Intrinsics.checkParameterIsNotNull(dvb1, "dvb1");
        Intrinsics.checkParameterIsNotNull(dvb2, "dvb2");
        if (!StringsKt.startsWith$default(dvb1, "dvb://", false, 2, (Object) null) || !StringsKt.startsWith$default(dvb2, "dvb://", false, 2, (Object) null)) {
            return false;
        }
        try {
            String strSubstring = dvb1.substring(6);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
            Object[] array = StringsKt.split$default((CharSequence) strSubstring, new String[]{"."}, false, 0, 6, (Object) null).toArray(new String[0]);
            if (array != null) {
                String[] strArr = (String[]) array;
                String strSubstring2 = dvb2.substring(6);
                Intrinsics.checkNotNullExpressionValue(strSubstring2, "(this as java.lang.String).substring(startIndex)");
                Object[] array2 = StringsKt.split$default((CharSequence) strSubstring2, new String[]{"."}, false, 0, 6, (Object) null).toArray(new String[0]);
                if (array2 != null) {
                    String[] strArr2 = (String[]) array2;
                    int[] iArr = new int[3];
                    int[] iArr2 = new int[3];
                    for (int i = 0; i <= 2; i++) {
                        try {
                            iArr[i] = Integer.parseInt(strArr[i], CharsKt.checkRadix(16));
                        } catch (Exception unused) {
                            iArr[i] = -1;
                        }
                        try {
                            iArr2[i] = Integer.parseInt(strArr2[i], CharsKt.checkRadix(16));
                        } catch (Exception unused2) {
                            iArr2[i] = -2;
                        }
                    }
                    return iArr[2] == iArr2[2];
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        } catch (Exception e) {
            Log.w("dvbAreEquals", "[dvbAreEquals]: exception", e);
            return false;
        }
    }

    public static final void clearCache() {
        Log.v(TAG, "sDVBToChannelIdMap cleared");
        sDVBToChannelIdMap = (Map) null;
    }

    public static final String getOptimizerChannelId(Channel getOptimizerChannelId, BOService boService) {
        String str;
        Intrinsics.checkParameterIsNotNull(getOptimizerChannelId, "$this$getOptimizerChannelId");
        Intrinsics.checkParameterIsNotNull(boService, "boService");
        if (__ChannelOptimizerIdMap.get(Integer.valueOf(getOptimizerChannelId.hashCode())) != null) {
            String str2 = __ChannelOptimizerIdMap.get(Integer.valueOf(getOptimizerChannelId.hashCode()));
            if (str2 == null) {
                Intrinsics.throwNpe();
            }
            return str2;
        }
        try {
            Object obj = getOptimizerChannelId.getInternalProviderData().get(CommonUtils.KEY_OPTIMIZER_CHANNEL_ID);
            if (obj == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
            }
            str = (String) obj;
            __ChannelOptimizerIdMap.put(Integer.valueOf(getOptimizerChannelId.hashCode()), str);
            return str;
        } catch (Exception unused) {
            str = null;
            try {
                String str3 = (String) null;
                int currentPlatform = CommonUtils.getCurrentPlatform();
                if (currentPlatform == 1) {
                    byte[] internalProviderDataByteArray = getOptimizerChannelId.getInternalProviderDataByteArray();
                    Intrinsics.checkExpressionValueIsNotNull(internalProviderDataByteArray, "internalProviderDataByteArray");
                    str3 = new String(internalProviderDataByteArray, Charsets.UTF_8);
                } else if (currentPlatform == 2) {
                    byte[] internalProviderDataByteArray2 = getOptimizerChannelId.getInternalProviderDataByteArray();
                    Intrinsics.checkExpressionValueIsNotNull(internalProviderDataByteArray2, "internalProviderDataByteArray");
                    str3 = new JSONObject(new String(internalProviderDataByteArray2, Charsets.UTF_8)).getString("uri");
                }
                if (str3 != null && StringsKt.startsWith$default(str3, "dvb://", false, 2, (Object) null)) {
                    if (sDVBToChannelIdMap == null) {
                        Log.v(TAG, "sDVBToChannelIdMap was cleared need to be recreate");
                        sDVBToChannelIdMap = (Map) CommonUtils.getCurrentLineup(boService).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.tv.common.utils.TIFCompanionLibraryExtensionKt$getOptimizerChannelId$id$1
                            @Override // io.reactivex.functions.Function
                            public final Observable<com.dotscreen.bokit.model.Channel> apply(Lineup it) {
                                Intrinsics.checkParameterIsNotNull(it, "it");
                                return it.getItems();
                            }
                        }).toMap(new Function<com.dotscreen.bokit.model.Channel, String>() { // from class: com.dotscreen.tv.common.utils.TIFCompanionLibraryExtensionKt$getOptimizerChannelId$id$2
                            @Override // io.reactivex.functions.Function
                            public final String apply(com.dotscreen.bokit.model.Channel channel) {
                                String src;
                                Intrinsics.checkParameterIsNotNull(channel, "channel");
                                ChannelExtraCaster channelExtraCaster = ((RightVChannel) channel).getExtras().getCasters().get("dvb");
                                return (channelExtraCaster == null || (src = channelExtraCaster.getSrc()) == null) ? "" : src;
                            }
                        }, new Function<com.dotscreen.bokit.model.Channel, String>() { // from class: com.dotscreen.tv.common.utils.TIFCompanionLibraryExtensionKt$getOptimizerChannelId$id$3
                            @Override // io.reactivex.functions.Function
                            public final String apply(com.dotscreen.bokit.model.Channel channel) {
                                Intrinsics.checkParameterIsNotNull(channel, "channel");
                                return channel.getId();
                            }
                        }).blockingGet();
                    }
                    String str4 = (String) null;
                    Map<String, String> map = sDVBToChannelIdMap;
                    if (map == null) {
                        Intrinsics.throwNpe();
                    }
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        if (dvbAreEquals(key, str3)) {
                            str = value;
                        }
                    }
                    str = str4;
                }
            } catch (Exception unused2) {
            }
        }
    }

    public static final com.dotscreen.bokit.model.Channel getOptimizerChannel(Channel getOptimizerChannel, BOService boService) {
        Intrinsics.checkParameterIsNotNull(getOptimizerChannel, "$this$getOptimizerChannel");
        Intrinsics.checkParameterIsNotNull(boService, "boService");
        final String optimizerChannelId = getOptimizerChannelId(getOptimizerChannel, boService);
        if (optimizerChannelId == null) {
            return null;
        }
        try {
            return (com.dotscreen.bokit.model.Channel) CommonUtils.getCurrentLineup(boService).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.tv.common.utils.TIFCompanionLibraryExtensionKt$getOptimizerChannel$1$1
                @Override // io.reactivex.functions.Function
                public final Observable<com.dotscreen.bokit.model.Channel> apply(Lineup it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return it.getItems();
                }
            }).filter(new Predicate<com.dotscreen.bokit.model.Channel>() { // from class: com.dotscreen.tv.common.utils.TIFCompanionLibraryExtensionKt$getOptimizerChannel$1$2
                @Override // io.reactivex.functions.Predicate
                public final boolean test(com.dotscreen.bokit.model.Channel it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return Intrinsics.areEqual(it.getId(), optimizerChannelId);
                }
            }).blockingFirst();
        } catch (Throwable th) {
            Log.w(TAG, "getOptimizerChannel exception: " + th);
            return null;
        }
    }
}
