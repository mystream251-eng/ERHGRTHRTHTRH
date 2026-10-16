package com.dotscreen.tv.common.bokit;

import android.util.Base64;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.dotscreen.bokit.model.Artwork;
import com.dotscreen.bokit.model.ArtworkFormat;
import com.dotscreen.bokit.model.ChannelCapabilities;
import com.dotscreen.bokit.model.Content;
import com.dotscreen.bokit.model.Media;
import com.dotscreen.bokit.model.Offer;
import com.dotscreen.bokit.model.ParentalRating;
import com.dotscreen.bokit.model.StaticArtwork;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.model.TVProgram;
import com.dotscreen.bokit.services.BOService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: MegacableUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u000e\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u000e\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001\u001a&\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0013\u001a\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0012\u001a\u00020\u0013\u001a\u0018\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u00012\b\b\u0002\u0010\u0018\u001a\u00020\r\u001a\u001c\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00010\u001e\u001a\n\u0010\u001f\u001a\u00020 *\u00020!¨\u0006\""}, d2 = {"decodeBase64", "", "id", "decodeID", "decodeOfferVideoId", "extractArtworks", "", "Lcom/dotscreen/bokit/model/Artwork;", "boService", "Lcom/dotscreen/bokit/services/BOService;", "extendedProgram", "Lorg/json/JSONObject;", "artworkFormat", "Lcom/dotscreen/bokit/model/ArtworkFormat;", "extractContentId", "Lcom/dotscreen/tv/common/bokit/RightvContentIdData;", "extractLiveOfferData", "Lcom/dotscreen/tv/common/bokit/RightvLiveOfferData;", "offer", "Lcom/dotscreen/bokit/model/Offer;", "extractVideoOfferData", "Lcom/dotscreen/tv/common/bokit/RightvVideoOfferData;", "getRightVArtworkFormat", "formatName", "defaultFormat", "rightVCapabilities", "Lcom/dotscreen/bokit/model/ChannelCapabilities;", "liveTVService", "Lcom/dotscreen/tv/common/bokit/MegacableLiveTVService;", "extraKeys", "", "parentalLevel", "", "Lcom/dotscreen/bokit/model/Media;", "common_prodRelease"}, k = 2, mv = {1, 1, 16})
public final class MegacableUtilsKt {
    public static final ChannelCapabilities rightVCapabilities(MegacableLiveTVService liveTVService, Set<String> extraKeys) {
        boolean z;
        boolean z2;
        boolean z3;
        Intrinsics.checkParameterIsNotNull(liveTVService, "liveTVService");
        Intrinsics.checkParameterIsNotNull(extraKeys, "extraKeys");
        try {
            String strBlockingGet = liveTVService.getModelExternalId().blockingGet();
            if (Intrinsics.areEqual(strBlockingGet, "Android")) {
                strBlockingGet = "PC";
            }
            z = false;
            z2 = false;
            z3 = false;
            for (String str : extraKeys) {
                try {
                    if (StringsKt.contains$default((CharSequence) str, (CharSequence) "rightv-tag", false, 2, (Object) null)) {
                        List listSplit$default = StringsKt.split$default((CharSequence) str, new String[]{":"}, false, 0, 6, (Object) null);
                        if (Intrinsics.areEqual((String) listSplit$default.get(1), strBlockingGet)) {
                            String str2 = (String) listSplit$default.get(2);
                            int iHashCode = str2.hashCode();
                            if (iHashCode != -1573282159) {
                                if (iHashCode == 3388414) {
                                    if (str2.equals("npvr")) {
                                        z3 = true;
                                    }
                                } else if (iHashCode == 48678559 && str2.equals("catch_up")) {
                                    z = true;
                                }
                            } else if (str2.equals("start_over")) {
                                z2 = true;
                            }
                        }
                    }
                } catch (Exception unused) {
                }
            }
        } catch (Exception unused2) {
            z = false;
            z2 = false;
            z3 = false;
        }
        return new ChannelCapabilities(z, z2, z3, false);
    }

    public static /* synthetic */ List extractArtworks$default(BOService bOService, JSONObject jSONObject, ArtworkFormat artworkFormat, int i, Object obj) {
        if ((i & 4) != 0) {
            artworkFormat = ArtworkFormat.Still;
        }
        return extractArtworks(bOService, jSONObject, artworkFormat);
    }

    public static final List<Artwork> extractArtworks(BOService boService, JSONObject extendedProgram, ArtworkFormat artworkFormat) {
        String string;
        String string2;
        Intrinsics.checkParameterIsNotNull(boService, "boService");
        Intrinsics.checkParameterIsNotNull(extendedProgram, "extendedProgram");
        Intrinsics.checkParameterIsNotNull(artworkFormat, "artworkFormat");
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = extendedProgram.getJSONArray("attachments");
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                try {
                    if (Intrinsics.areEqual(jSONObject.getString("responseElementType"), "Attachment") && (string2 = jSONObject.getString("name")) != null) {
                        int iHashCode = string2.hashCode();
                        if (iHashCode != -1898583699) {
                            if (iHashCode == -333530267) {
                                if (string2.equals("VerticalImage")) {
                                    String string3 = jSONObject.getString("value");
                                    Intrinsics.checkExpressionValueIsNotNull(string3, "attachment.getString(\"value\")");
                                    arrayList.add(new RTEArtwork(boService, string3, ArtworkFormat.Poster));
                                }
                            } else if (iHashCode == 52091767 && string2.equals("HorizontalImage")) {
                                String string4 = jSONObject.getString("value");
                                Intrinsics.checkExpressionValueIsNotNull(string4, "attachment.getString(\"value\")");
                                arrayList.add(new RTEArtwork(boService, string4, ArtworkFormat.Still));
                            }
                        } else if (string2.equals("Poster")) {
                            String string5 = jSONObject.getString("value");
                            Intrinsics.checkExpressionValueIsNotNull(string5, "attachment.getString(\"value\")");
                            arrayList.add(new RTEArtwork(boService, string5, ArtworkFormat.Poster));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            JSONArray jSONArray2 = extendedProgram.getJSONArray("extrafields");
            int length2 = jSONArray2.length();
            for (int i2 = 0; i2 < length2; i2++) {
                JSONObject jSONObject2 = jSONArray2.getJSONObject(i2);
                try {
                    if (Intrinsics.areEqual(jSONObject2.getString("responseElementType"), "Extrafield") && (string = jSONObject2.getString("name")) != null && string.hashCode() == 1126113355 && string.equals("Image_Url")) {
                        String string6 = jSONObject2.getString("value");
                        Intrinsics.checkExpressionValueIsNotNull(string6, "extraField.getString(\"value\")");
                        arrayList.add(new StaticArtwork(boService, string6, artworkFormat));
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        return arrayList;
    }

    public static final RightvContentIdData extractContentId(String id) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        try {
            if (StringsKt.startsWith$default(id, "rightv:", false, 2, (Object) null)) {
                id = (String) StringsKt.split$default((CharSequence) id, new char[]{':'}, false, 0, 6, (Object) null).get(1);
            }
            byte[] bArrDecode = Base64.decode(id, 0);
            Intrinsics.checkExpressionValueIsNotNull(bArrDecode, "Base64.decode(idToDecode, Base64.DEFAULT)");
            JSONObject jSONObject = new JSONObject(new String(bArrDecode, Charsets.UTF_8));
            return new RightvContentIdData(jSONObject.optString("content_type", null), jSONObject.optString(TvContractCompat.PreviewProgramColumns.COLUMN_CONTENT_ID, null), jSONObject.optString("page_external_id", null), jSONObject.optString("asset_name", null), jSONObject.optString("video_external_id", null), jSONObject.optString("asset_external_id", null));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final RightvVideoOfferData extractVideoOfferData(Offer offer) {
        Intrinsics.checkParameterIsNotNull(offer, "offer");
        try {
            byte[] bArrDecode = Base64.decode((String) StringsKt.split$default((CharSequence) offer.getId(), new char[]{':'}, false, 0, 6, (Object) null).get(1), 0);
            Intrinsics.checkExpressionValueIsNotNull(bArrDecode, "Base64.decode(offer.id.s…(':')[1], Base64.DEFAULT)");
            JSONObject jSONObject = new JSONObject(new String(bArrDecode, Charsets.UTF_8));
            String strOptString = jSONObject.optString("service_external_id");
            String string = jSONObject.getString("video_external_id");
            Intrinsics.checkExpressionValueIsNotNull(string, "json.getString(\"video_external_id\")");
            String string2 = jSONObject.getString("pricing_option_id");
            Intrinsics.checkExpressionValueIsNotNull(string2, "json.getString(\"pricing_option_id\")");
            String string3 = jSONObject.getString("type");
            Intrinsics.checkExpressionValueIsNotNull(string3, "json.getString(\"type\")");
            return new RightvVideoOfferData(strOptString, string, string2, string3, jSONObject.getBoolean("toolbox"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final RightvLiveOfferData extractLiveOfferData(Offer offer) {
        Intrinsics.checkParameterIsNotNull(offer, "offer");
        try {
            byte[] bArrDecode = Base64.decode((String) StringsKt.split$default((CharSequence) offer.getId(), new char[]{':'}, false, 0, 6, (Object) null).get(1), 0);
            Intrinsics.checkExpressionValueIsNotNull(bArrDecode, "Base64.decode(offer.id.s…(':')[1], Base64.DEFAULT)");
            JSONObject jSONObject = new JSONObject(new String(bArrDecode, Charsets.UTF_8));
            if (!jSONObject.has("program_external_id") || !jSONObject.has("channel_external_id")) {
                return null;
            }
            String string = jSONObject.getString("program_external_id");
            Intrinsics.checkExpressionValueIsNotNull(string, "json.getString(\"program_external_id\")");
            String string2 = jSONObject.getString("channel_external_id");
            Intrinsics.checkExpressionValueIsNotNull(string2, "json.getString(\"channel_external_id\")");
            return new RightvLiveOfferData(string, string2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final String decodeID(String id) throws JSONException {
        Intrinsics.checkParameterIsNotNull(id, "id");
        byte[] bArrDecode = Base64.decode(id, 0);
        Intrinsics.checkExpressionValueIsNotNull(bArrDecode, "Base64.decode(id, Base64.DEFAULT)");
        String string = new JSONObject(new String(bArrDecode, Charsets.UTF_8)).getString(TvContractCompat.PreviewProgramColumns.COLUMN_CONTENT_ID);
        Intrinsics.checkExpressionValueIsNotNull(string, "JSONObject(String(Base64…).getString(\"content_id\")");
        return string;
    }

    public static final String decodeBase64(String id) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        byte[] bArrDecode = Base64.decode(id, 0);
        Intrinsics.checkExpressionValueIsNotNull(bArrDecode, "Base64.decode(id, Base64.DEFAULT)");
        String string = new JSONObject(new String(bArrDecode, Charsets.UTF_8)).toString();
        Intrinsics.checkExpressionValueIsNotNull(string, "JSONObject(String(Base64…e64.DEFAULT))).toString()");
        return string;
    }

    public static final String decodeOfferVideoId(String id) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        byte[] bArrDecode = Base64.decode(id, 0);
        Intrinsics.checkExpressionValueIsNotNull(bArrDecode, "Base64.decode(id, Base64.DEFAULT)");
        String string = new JSONObject(new String(bArrDecode, Charsets.UTF_8)).toString();
        Intrinsics.checkExpressionValueIsNotNull(string, "JSONObject(String(Base64…e64.DEFAULT))).toString()");
        return string;
    }

    public static /* synthetic */ ArtworkFormat getRightVArtworkFormat$default(String str, ArtworkFormat artworkFormat, int i, Object obj) {
        if ((i & 2) != 0) {
            artworkFormat = ArtworkFormat.Poster;
        }
        return getRightVArtworkFormat(str, artworkFormat);
    }

    public static final ArtworkFormat getRightVArtworkFormat(String formatName, ArtworkFormat defaultFormat) {
        Intrinsics.checkParameterIsNotNull(formatName, "formatName");
        Intrinsics.checkParameterIsNotNull(defaultFormat, "defaultFormat");
        switch (formatName.hashCode()) {
            case -136821420:
                return formatName.equals("Image_Url_H") ? ArtworkFormat.Still : defaultFormat;
            case -136821409:
                return formatName.equals("Image_Url_S") ? ArtworkFormat.Square : defaultFormat;
            case -136821406:
                return formatName.equals("Image_Url_V") ? ArtworkFormat.Poster : defaultFormat;
            case 814243129:
                return formatName.equals("Banner_Url_H_S") ? ArtworkFormat.Banner : defaultFormat;
            case 1126113355:
                return formatName.equals("Image_Url") ? ArtworkFormat.Still : defaultFormat;
            case 1153918629:
                return formatName.equals("Banner_Url_H") ? ArtworkFormat.Banner : defaultFormat;
            case 1658604584:
                return formatName.equals("Image_Url_H_S") ? ArtworkFormat.Still : defaultFormat;
            case 1658618038:
                return formatName.equals("Image_Url_V_S") ? ArtworkFormat.Poster : defaultFormat;
            default:
                return defaultFormat;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final int parentalLevel(Media parentalLevel) {
        ParentalRating parentalLevel2;
        Intrinsics.checkParameterIsNotNull(parentalLevel, "$this$parentalLevel");
        if (parentalLevel instanceof TVAiring) {
            ParentalRating parentalLevel3 = ((TVAiring) parentalLevel).getMetadata().getParentalLevel();
            if (parentalLevel3 != null) {
                return parentalLevel3.getLevel();
            }
            return 6;
        }
        if (parentalLevel instanceof Content) {
            Content content = (Content) parentalLevel;
            if (content.getMetadata().getParentalLevel() == null) {
                return 6;
            }
            ParentalRating parentalLevel4 = content.getMetadata().getParentalLevel();
            if (parentalLevel4 == null) {
                Intrinsics.throwNpe();
            }
            if (parentalLevel4.getLevel() == 0) {
                return 7;
            }
            ParentalRating parentalLevel5 = content.getMetadata().getParentalLevel();
            if (parentalLevel5 == null) {
                Intrinsics.throwNpe();
            }
            return parentalLevel5.getLevel();
        }
        if (parentalLevel instanceof TVProgram) {
            ParentalRating parentalLevel6 = ((TVProgram) parentalLevel).getMetadata().getParentalLevel();
            if (parentalLevel6 != null) {
                return parentalLevel6.getLevel();
            }
            return 6;
        }
        if (parentalLevel instanceof CatchupMedia) {
            ParentalRating parentalLevel7 = ((CatchupMedia) parentalLevel).getAiring().getMetadata().getParentalLevel();
            if (parentalLevel7 != null) {
                return parentalLevel7.getLevel();
            }
            return 6;
        }
        if (!(parentalLevel instanceof StartOverMedia) || (parentalLevel2 = ((StartOverMedia) parentalLevel).getAiring().getMetadata().getParentalLevel()) == null) {
            return 6;
        }
        return parentalLevel2.getLevel();
    }
}
