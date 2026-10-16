package com.dotscreen.portal;

import android.content.Context;
import androidx.exifinterface.media.ExifInterface;
import androidx.leanback.widget.PresenterSelector;
import com.dotscreen.bokit.model.BrowsableItem;
import com.dotscreen.bokit.model.CatalogSection;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.Content;
import com.dotscreen.bokit.model.ItemCollection;
import com.dotscreen.bokit.model.ItemExternalIdScheme;
import com.dotscreen.bokit.model.ParentalRating;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.model.TVProgram;
import com.dotscreen.bokit.model.TVSeason;
import com.dotscreen.bokit.model.TVSeries;
import com.dotscreen.portal.ui.cards.HomePresenterSelector;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Constants.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u00100\u001a\u00020\u0010\"\b\b\u0000\u00101*\u0002022\f\u00103\u001a\b\u0012\u0004\u0012\u0002H104J\u001e\u00105\u001a\u00020\u0010\"\b\b\u0000\u00101*\u0002022\f\u00103\u001a\b\u0012\u0004\u0012\u0002H104J)\u00106\u001a\u0002072\u0006\u00108\u001a\u0002092\b\u0010:\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010<J\u000e\u0010=\u001a\u00020\u00042\u0006\u0010>\u001a\u000202J\u000e\u0010?\u001a\u00020\u00102\u0006\u0010>\u001a\u000202J\u0010\u0010@\u001a\u00020A2\b\u0010:\u001a\u0004\u0018\u00010\u0004J\u0010\u0010B\u001a\u00020A2\b\u0010C\u001a\u0004\u0018\u00010\u0004R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0006R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0006R\u0011\u0010\u0015\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0006R\u0011\u0010\u0017\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0006R\u0011\u0010\u0019\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0006R\u0011\u0010\u001b\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0006R\u0011\u0010\u001d\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0006R\u0011\u0010\u001f\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b \u0010\u0006R\u0011\u0010!\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\"\u0010\u0006R\u0011\u0010#\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b$\u0010\u0006R\u001c\u0010%\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0006\"\u0004\b'\u0010\fR\u001c\u0010(\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0006\"\u0004\b*\u0010\fR\u001c\u0010+\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0006\"\u0004\b-\u0010\fR\u0011\u0010.\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b/\u0010\u0006¨\u0006D"}, d2 = {"Lcom/dotscreen/portal/Constants;", "", "()V", "CATCHUP_CHANNELS_ROOT_CATALOG_ID", "", "getCATCHUP_CHANNELS_ROOT_CATALOG_ID", "()Ljava/lang/String;", "CATCHUP_ROOT_CATALOG_ID", "getCATCHUP_ROOT_CATALOG_ID", "HOME_HIGHLIGHT_CATALOG_ID", "getHOME_HIGHLIGHT_CATALOG_ID", "setHOME_HIGHLIGHT_CATALOG_ID", "(Ljava/lang/String;)V", "HOME_RECO_ROOT_CATALOG_ID", "getHOME_RECO_ROOT_CATALOG_ID", "INPUT_SERIAL_NUMER_AT_STARTUP", "", "getINPUT_SERIAL_NUMER_AT_STARTUP", "()Z", "LAUNCHER_CHANNELS_ROOT_CATALOG_ID", "getLAUNCHER_CHANNELS_ROOT_CATALOG_ID", "LAUNCHER_LIVE_ROOT_CATALOG_ID", "getLAUNCHER_LIVE_ROOT_CATALOG_ID", "LAUNCHER_VOD_ROOT_CATALOG_ID", "getLAUNCHER_VOD_ROOT_CATALOG_ID", "MY_CONTENTS_BOOKMARKS_CATALOG_ID", "getMY_CONTENTS_BOOKMARKS_CATALOG_ID", "MY_CONTENTS_CHANNELS_CATALOG_ID", "getMY_CONTENTS_CHANNELS_CATALOG_ID", "MY_CONTENTS_PURCHASED_CATALOG_ID", "getMY_CONTENTS_PURCHASED_CATALOG_ID", "MY_CONTENTS_RECORDING_CATALOG_ID", "getMY_CONTENTS_RECORDING_CATALOG_ID", "MY_CONTENTS_REMINDERS_CATALOG_ID", "getMY_CONTENTS_REMINDERS_CATALOG_ID", "MY_CONTENTS_VOD_CATALOG_ID", "getMY_CONTENTS_VOD_CATALOG_ID", "PROD_CATCHUP_ROOT_CATALOG_ID", "getPROD_CATCHUP_ROOT_CATALOG_ID", "setPROD_CATCHUP_ROOT_CATALOG_ID", "PROD_HOME_RECO_ROOT_CATALOG_ID", "getPROD_HOME_RECO_ROOT_CATALOG_ID", "setPROD_HOME_RECO_ROOT_CATALOG_ID", "PROD_VOD_ROOT_CATALOG_ID", "getPROD_VOD_ROOT_CATALOG_ID", "setPROD_VOD_ROOT_CATALOG_ID", "VOD_ROOT_CATALOG_ID", "getVOD_ROOT_CATALOG_ID", "collectionIsAdult", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/dotscreen/bokit/model/BrowsableItem;", "collection", "Lcom/dotscreen/bokit/model/ItemCollection;", "collectionIsHighlights", "createPresenter", "Landroidx/leanback/widget/PresenterSelector;", "context", "Landroid/content/Context;", "catalogId", "adultBrowsing", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Boolean;)Landroidx/leanback/widget/PresenterSelector;", "itemEngineType", "item", "itemIsAdult", "mosaicColumnsCount", "", "myContentsMosaicColumnsCount", "type", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class Constants {
    private static String PROD_CATCHUP_ROOT_CATALOG_ID;
    private static String PROD_HOME_RECO_ROOT_CATALOG_ID;
    private static String PROD_VOD_ROOT_CATALOG_ID;
    public static final Constants INSTANCE = new Constants();
    private static String HOME_HIGHLIGHT_CATALOG_ID = "Highlights02";
    private static final boolean INPUT_SERIAL_NUMER_AT_STARTUP = false;

    public final String getCATCHUP_CHANNELS_ROOT_CATALOG_ID() {
        return "Catchup_Channels";
    }

    public final String getLAUNCHER_CHANNELS_ROOT_CATALOG_ID() {
        return "Launcher_Channels";
    }

    public final String getLAUNCHER_LIVE_ROOT_CATALOG_ID() {
        return "Launcher_Live";
    }

    public final String getLAUNCHER_VOD_ROOT_CATALOG_ID() {
        return "VOD_VOD_Perso";
    }

    public final String getMY_CONTENTS_BOOKMARKS_CATALOG_ID() {
        return "my_contents_bookmarks";
    }

    public final String getMY_CONTENTS_CHANNELS_CATALOG_ID() {
        return "my_contents_channels";
    }

    public final String getMY_CONTENTS_PURCHASED_CATALOG_ID() {
        return "my_contents_purchased";
    }

    public final String getMY_CONTENTS_RECORDING_CATALOG_ID() {
        return "my_contents_recordings";
    }

    public final String getMY_CONTENTS_REMINDERS_CATALOG_ID() {
        return "my_contents_reminders";
    }

    public final String getMY_CONTENTS_VOD_CATALOG_ID() {
        return "my_contents_vod";
    }

    private Constants() {
    }

    public final String getPROD_CATCHUP_ROOT_CATALOG_ID() {
        return PROD_CATCHUP_ROOT_CATALOG_ID;
    }

    public final void setPROD_CATCHUP_ROOT_CATALOG_ID(String str) {
        PROD_CATCHUP_ROOT_CATALOG_ID = str;
    }

    public final String getPROD_VOD_ROOT_CATALOG_ID() {
        return PROD_VOD_ROOT_CATALOG_ID;
    }

    public final void setPROD_VOD_ROOT_CATALOG_ID(String str) {
        PROD_VOD_ROOT_CATALOG_ID = str;
    }

    public final String getPROD_HOME_RECO_ROOT_CATALOG_ID() {
        return PROD_HOME_RECO_ROOT_CATALOG_ID;
    }

    public final void setPROD_HOME_RECO_ROOT_CATALOG_ID(String str) {
        PROD_HOME_RECO_ROOT_CATALOG_ID = str;
    }

    public final String getHOME_HIGHLIGHT_CATALOG_ID() {
        return HOME_HIGHLIGHT_CATALOG_ID;
    }

    public final void setHOME_HIGHLIGHT_CATALOG_ID(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        HOME_HIGHLIGHT_CATALOG_ID = str;
    }

    public final String getCATCHUP_ROOT_CATALOG_ID() {
        String str = PROD_CATCHUP_ROOT_CATALOG_ID;
        return str != null ? str : "Catchup1";
    }

    public final String getVOD_ROOT_CATALOG_ID() {
        String str = PROD_VOD_ROOT_CATALOG_ID;
        return str != null ? str : "VOD01";
    }

    public final String getHOME_RECO_ROOT_CATALOG_ID() {
        String str = PROD_HOME_RECO_ROOT_CATALOG_ID;
        return str != null ? str : "Launcher";
    }

    public final boolean getINPUT_SERIAL_NUMER_AT_STARTUP() {
        return INPUT_SERIAL_NUMER_AT_STARTUP;
    }

    public static /* synthetic */ PresenterSelector createPresenter$default(Constants constants, Context context, String str, Boolean bool, int i, Object obj) {
        if ((i & 4) != 0) {
            bool = false;
        }
        return constants.createPresenter(context, str, bool);
    }

    public final PresenterSelector createPresenter(Context context, String catalogId, Boolean adultBrowsing) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (Intrinsics.areEqual(catalogId, getCATCHUP_ROOT_CATALOG_ID())) {
            return new HomePresenterSelector(Integer.valueOf(context.getResources().getDimensionPixelSize(com.dotscreen.megacable.portal.R.dimen.catchup_more_content_card_width)), Integer.valueOf(context.getResources().getDimensionPixelSize(com.dotscreen.megacable.portal.R.dimen.catchup_more_content_card_height)), Integer.valueOf(context.getResources().getDimensionPixelSize(com.dotscreen.megacable.portal.R.dimen.catchup_content_card_width)), Integer.valueOf(context.getResources().getDimensionPixelSize(com.dotscreen.megacable.portal.R.dimen.catchup_content_card_height)), null, adultBrowsing, true, 16, null);
        }
        if (Intrinsics.areEqual(catalogId, getVOD_ROOT_CATALOG_ID()) || Intrinsics.areEqual(catalogId, getLAUNCHER_VOD_ROOT_CATALOG_ID())) {
            return new HomePresenterSelector(Integer.valueOf(context.getResources().getDimensionPixelSize(com.dotscreen.megacable.portal.R.dimen.vod_more_content_card_width)), Integer.valueOf(context.getResources().getDimensionPixelSize(com.dotscreen.megacable.portal.R.dimen.vod_more_content_card_height)), Integer.valueOf(context.getResources().getDimensionPixelSize(com.dotscreen.megacable.portal.R.dimen.vod_content_card_width)), Integer.valueOf(context.getResources().getDimensionPixelSize(com.dotscreen.megacable.portal.R.dimen.vod_content_card_height)), null, adultBrowsing, null, 80, null);
        }
        if (Intrinsics.areEqual(catalogId, getHOME_RECO_ROOT_CATALOG_ID())) {
            return new HomePresenterSelector(Integer.valueOf(context.getResources().getDimensionPixelSize(com.dotscreen.megacable.portal.R.dimen.vod_more_content_card_width)), Integer.valueOf(context.getResources().getDimensionPixelSize(com.dotscreen.megacable.portal.R.dimen.vod_more_content_card_height)), Integer.valueOf(context.getResources().getDimensionPixelSize(com.dotscreen.megacable.portal.R.dimen.vod_content_card_width)), Integer.valueOf(context.getResources().getDimensionPixelSize(com.dotscreen.megacable.portal.R.dimen.vod_content_card_height)), null, adultBrowsing, null, 80, null);
        }
        if (Intrinsics.areEqual(catalogId, getMY_CONTENTS_BOOKMARKS_CATALOG_ID()) || Intrinsics.areEqual(catalogId, getMY_CONTENTS_VOD_CATALOG_ID()) || Intrinsics.areEqual(catalogId, getMY_CONTENTS_PURCHASED_CATALOG_ID())) {
            return new HomePresenterSelector(Integer.valueOf(context.getResources().getDimensionPixelSize(com.dotscreen.megacable.portal.R.dimen.vod_more_content_card_width)), Integer.valueOf(context.getResources().getDimensionPixelSize(com.dotscreen.megacable.portal.R.dimen.vod_more_content_card_height)), Integer.valueOf(context.getResources().getDimensionPixelSize(com.dotscreen.megacable.portal.R.dimen.vod_content_card_width)), Integer.valueOf(context.getResources().getDimensionPixelSize(com.dotscreen.megacable.portal.R.dimen.vod_content_card_height)), null, adultBrowsing, null, 80, null);
        }
        if (Intrinsics.areEqual(catalogId, getMY_CONTENTS_REMINDERS_CATALOG_ID())) {
            return new HomePresenterSelector(Integer.valueOf(context.getResources().getDimensionPixelSize(com.dotscreen.megacable.portal.R.dimen.my_contents_more_still_card_width)), Integer.valueOf(context.getResources().getDimensionPixelSize(com.dotscreen.megacable.portal.R.dimen.my_contents_more_still_card_height)), Integer.valueOf(context.getResources().getDimensionPixelSize(com.dotscreen.megacable.portal.R.dimen.my_contents_channel_card_width)), Integer.valueOf(context.getResources().getDimensionPixelSize(com.dotscreen.megacable.portal.R.dimen.my_contents_channel_card_height)), null, adultBrowsing, null, 80, null);
        }
        return new HomePresenterSelector(Integer.valueOf(context.getResources().getDimensionPixelSize(com.dotscreen.megacable.portal.R.dimen.catchup_more_content_card_width)), Integer.valueOf(context.getResources().getDimensionPixelSize(com.dotscreen.megacable.portal.R.dimen.catchup_more_content_card_height)), Integer.valueOf(context.getResources().getDimensionPixelSize(com.dotscreen.megacable.portal.R.dimen.catchup_content_card_width)), Integer.valueOf(context.getResources().getDimensionPixelSize(com.dotscreen.megacable.portal.R.dimen.catchup_content_card_height)), null, adultBrowsing, null, 80, null);
    }

    public final int mosaicColumnsCount(String catalogId) {
        if (!Intrinsics.areEqual(catalogId, getVOD_ROOT_CATALOG_ID()) && !Intrinsics.areEqual(catalogId, getHOME_RECO_ROOT_CATALOG_ID())) {
            if (Intrinsics.areEqual(catalogId, getLAUNCHER_LIVE_ROOT_CATALOG_ID())) {
                return 4;
            }
            if (!Intrinsics.areEqual(catalogId, getLAUNCHER_CHANNELS_ROOT_CATALOG_ID()) && !Intrinsics.areEqual(catalogId, getLAUNCHER_VOD_ROOT_CATALOG_ID()) && !Intrinsics.areEqual(catalogId, getCATCHUP_ROOT_CATALOG_ID()) && !Intrinsics.areEqual(catalogId, getMY_CONTENTS_BOOKMARKS_CATALOG_ID()) && !Intrinsics.areEqual(catalogId, getMY_CONTENTS_CHANNELS_CATALOG_ID())) {
                if (Intrinsics.areEqual(catalogId, getMY_CONTENTS_RECORDING_CATALOG_ID())) {
                    return 4;
                }
                if (!Intrinsics.areEqual(catalogId, getMY_CONTENTS_VOD_CATALOG_ID()) && !Intrinsics.areEqual(catalogId, getMY_CONTENTS_PURCHASED_CATALOG_ID())) {
                    Intrinsics.areEqual(catalogId, getMY_CONTENTS_REMINDERS_CATALOG_ID());
                    return 4;
                }
            }
        }
        return 8;
    }

    public final int myContentsMosaicColumnsCount(String type) {
        return (!Intrinsics.areEqual(type, getCATCHUP_ROOT_CATALOG_ID()) && (Intrinsics.areEqual(type, getVOD_ROOT_CATALOG_ID()) || Intrinsics.areEqual(type, getHOME_RECO_ROOT_CATALOG_ID()))) ? 8 : 4;
    }

    public final <T extends BrowsableItem> boolean collectionIsHighlights(ItemCollection<T> collection) {
        Intrinsics.checkParameterIsNotNull(collection, "collection");
        return Intrinsics.areEqual(collection.getTitle(), "Highlights");
    }

    public final <T extends BrowsableItem> boolean collectionIsAdult(ItemCollection<T> collection) {
        ParentalRating parentalLevel;
        Intrinsics.checkParameterIsNotNull(collection, "collection");
        return (collection instanceof CatalogSection) && (parentalLevel = ((CatalogSection) collection).getParentalLevel()) != null && parentalLevel.getIsAdult();
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0032  */
    /* JADX WARN: Code duplicated, block: B:31:? A[RETURN, SYNTHETIC] */
    public final boolean itemIsAdult(BrowsableItem item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        if (item instanceof Content) {
            Content content = (Content) item;
            ParentalRating parentalLevel = content.getMetadata().getParentalLevel();
            if ((parentalLevel != null ? parentalLevel.getLevel() : 6) <= 5) {
                ParentalRating parentalLevel2 = content.getMetadata().getParentalLevel();
                if ((parentalLevel2 != null ? parentalLevel2.getLevel() : 6) == 0) {
                    if (item.getExternalIds().get(ItemExternalIdScheme.Netflix) == null) {
                        return true;
                    }
                }
            } else if (item.getExternalIds().get(ItemExternalIdScheme.Netflix) == null) {
                return true;
            }
        } else if (item instanceof TVAiring) {
            TVAiring tVAiring = (TVAiring) item;
            ParentalRating parentalLevel3 = tVAiring.getMetadata().getParentalLevel();
            if ((parentalLevel3 != null ? parentalLevel3.getLevel() : 6) > 5) {
                return true;
            }
            ParentalRating parentalLevel4 = tVAiring.getMetadata().getParentalLevel();
            if ((parentalLevel4 != null ? parentalLevel4.getLevel() : 6) == 0) {
                return true;
            }
        }
        return false;
    }

    public final String itemEngineType(BrowsableItem item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        if (item instanceof TVSeries) {
            return "series_banner";
        }
        if (item instanceof TVSeason) {
            return "season_banner";
        }
        if (item instanceof TVProgram) {
            return "program_banner";
        }
        if (item instanceof Channel) {
            return "channel_banner";
        }
        return item instanceof Content ? "content_banner" : "vod_category_banner";
    }
}
