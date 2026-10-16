package com.dotscreen.portal.model;

import android.content.Context;
import android.content.Intent;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.ui.screens.details.DetailsActionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HomeWidget.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \r2\u00020\u0001:\u0001\rB+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/dotscreen/portal/model/HomeWidget;", "Lcom/dotscreen/portal/model/BaseWidget;", "title", "", "icon", "", "intent", "Landroid/content/Intent;", "colorforced", "(Ljava/lang/String;ILandroid/content/Intent;I)V", "getColorforced", "()I", "getIcon", "Companion", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class HomeWidget extends BaseWidget {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int colorforced;
    private final int icon;

    /* JADX INFO: compiled from: HomeWidget.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/dotscreen/portal/model/HomeWidget$Companion;", "", "()V", "getAction", "Lcom/dotscreen/portal/model/HomeWidget;", "context", "Landroid/content/Context;", "actionName", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code duplicated, block: B:68:0x01ff  */
        public final HomeWidget getAction(Context context, String actionName) {
            String string;
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(actionName, "actionName");
            Intent intent = new Intent(actionName);
            int i = R.drawable.ic_startover;
            int i2 = R.color.red;
            switch (actionName) {
                case "com.dotscreen.megacable.portal.details.PLAYBACK_VOD":
                    string = context.getString(R.string.details_action_play);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.details_action_play)");
                    i = R.drawable.ic_picto_pg_play;
                    i2 = -1;
                    break;
                case "com.dotscreen.megacable.portal.details.PLAYBACK_TRAILER":
                    string = context.getString(R.string.details_action_trailer);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.details_action_trailer)");
                    i = R.drawable.ic_picto_pg_trailer;
                case "com.dotscreen.megacable.portal.details.PLAYBACK_VOD_FREE":
                    string = context.getString(R.string.details_action_rent_free);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.details_action_rent_free)");
                    i = R.drawable.ic_picto_pg_play;
                    i2 = -1;
                    break;
                case "com.dotscreen.megacable.portal.details.WATCH_CHANNEL":
                    string = context.getString(R.string.details_action_live);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.details_action_live)");
                    i = R.drawable.ic_remote_ver_canal;
                case "com.dotscreen.megacable.portal.details.AUDIO":
                    string = context.getString(R.string.details_action_audio);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.details_action_audio)");
                    i = R.drawable.ic_pictos_pg_lan;
                case "com.dotscreen.megacable.portal.details.PLAYBACK_RECORDING":
                    string = context.getString(R.string.details_action_play);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.details_action_play)");
                    intent = new Intent(DetailsActionsKt.ACTION_PLAYBACK_RECORDING);
                    i = R.drawable.ic_picto_pg_play;
                    i2 = -1;
                    break;
                case "com.dotscreen.megacable.portal.details.FAVORITES_ADD":
                    string = context.getString(R.string.details_action_add_VOD_to_fav);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.details_action_add_VOD_to_fav)");
                    i2 = R.color.white;
                    i = R.drawable.ic_remove_from_favorites;
                    break;
                case "com.dotscreen.megacable.portal.details.FAVORITES_CHANNEL_ADD":
                    string = context.getString(R.string.details_action_add_channel_to_fav);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.detai…ction_add_channel_to_fav)");
                    i = R.drawable.ic_add_to_favorite;
                    i2 = R.color.white;
                    break;
                case "com.dotscreen.megacable.portal.details.ORDER_OFFER":
                    string = context.getString(R.string.details_action_rent);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.details_action_rent)");
                    i = R.drawable.ic_picto_rent;
                case "com.dotscreen.megacable.portal.details.PLAYBACK_CATCHUP":
                    string = context.getString(R.string.details_action_replay_catchup);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.details_action_replay_catchup)");
                case "com.dotscreen.megacable.portal.details.PLAYBACK_STARTOVER":
                    string = context.getString(R.string.details_action_replay_startover);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.detai…_action_replay_startover)");
                case "com.dotscreen.megacable.portal.details.FAVORITES_CHANNEL_REMOVE":
                    string = context.getString(R.string.details_action_remove_channel_to_fav);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.detai…on_remove_channel_to_fav)");
                    i = R.drawable.ic_remove_from_favorites;
                    break;
                case "com.dotscreen.megacable.portal.details.DELETE_RECORDING":
                    string = context.getString(R.string.details_action_record_delete);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.details_action_record_delete)");
                case "com.dotscreen.megacable.portal.details.REMINDER_ADD":
                    string = context.getString(R.string.details_action_reminder);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.details_action_reminder)");
                    i = R.drawable.reminder;
                    i2 = R.color.white;
                    break;
                case "com.dotscreen.megacable.portal.details.SUBTITLES":
                    string = context.getString(R.string.details_action_subtitle);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.details_action_subtitle)");
                    i = R.drawable.ic_picto_pg_sub;
                case "com.dotscreen.megacable.portal.details.FAVORITES_REMOVE":
                    string = context.getString(R.string.details_action_remove_VOD_to_fav);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.detai…action_remove_VOD_to_fav)");
                    i = R.drawable.ic_remove_from_favorites;
                    break;
                case "com.dotscreen.megacable.portal.details.RECORDING_SCHEDULE":
                    string = context.getString(R.string.details_action_record_schedule);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.details_action_record_schedule)");
                    i = R.drawable.ic_record;
                case "com.dotscreen.megacable.portal.details.RECORDING_CANCEL":
                    string = context.getString(R.string.details_action_record_cancel);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.details_action_record_cancel)");
                case "com.dotscreen.megacable.portal.details.REMINDER_REMOVE":
                    string = context.getString(R.string.details_remove_action_reminder);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.details_remove_action_reminder)");
                    i = R.drawable.reminder_disable;
                    break;
                case "com.dotscreen.megacable.portal.details.SYNOPSIS":
                    string = context.getString(R.string.details_action_synopsis);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.details_action_synopsis)");
                    i = R.drawable.ic_synopsis;
                default:
                    if (actionName.equals(DetailsActionsKt.ACTION_PLAYBACK_VOD)) {
                        string = context.getString(R.string.details_action_play);
                        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.details_action_play)");
                        i = R.drawable.ic_picto_pg_play;
                        i2 = -1;
                        break;
                    }
                    string = "UnknownAction";
                    i = R.drawable.ic_record_cancel;
                    i2 = -1;
                    break;
            }
            return new HomeWidget(string, i, intent, i2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeWidget(String title, int i, Intent intent, int i2) {
        super(title, intent, false, null, 12, null);
        Intrinsics.checkParameterIsNotNull(title, "title");
        this.icon = i;
        this.colorforced = i2;
    }

    public /* synthetic */ HomeWidget(String str, int i, Intent intent, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i3 & 2) != 0 ? -1 : i, intent, (i3 & 8) != 0 ? -1 : i2);
    }

    public final int getColorforced() {
        return this.colorforced;
    }

    public final int getIcon() {
        return this.icon;
    }
}
