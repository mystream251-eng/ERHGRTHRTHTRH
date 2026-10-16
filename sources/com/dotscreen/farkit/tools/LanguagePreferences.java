package com.dotscreen.farkit.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: LanguagePreferences.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u001e\u0010\u000f\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00102\u0006\u0010\u0011\u001a\u00020\u0005J$\u0010\u0012\u001a\u00020\u00132\b\u0010\u0011\u001a\u0004\u0018\u00010\u00052\b\u0010\u0014\u001a\u0004\u0018\u00010\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u0005R\u0016\u0010\u0007\u001a\n \b*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Lcom/dotscreen/farkit/tools/LanguagePreferences;", "", "context", "Landroid/content/Context;", ToolboxAnalyticsModuleKt.EVENT_PROFILE_ID, "", "(Landroid/content/Context;Ljava/lang/String;)V", "TAG", "kotlin.jvm.PlatformType", "getContext", "()Landroid/content/Context;", "getProfileId", "()Ljava/lang/String;", "setProfileId", "(Ljava/lang/String;)V", "retrieveAudioAndSubtitleLanguages", "Lkotlin/Pair;", "channelId", "storePrefLanguage", "", "audioLanguage", "subtitleLanguage", "Companion", "farkit_release"}, k = 1, mv = {1, 1, 16})
public final class LanguagePreferences {
    public static final String COLUMN_CHANNEL_ID = "channel_id";
    public static final String COLUMN_PREFERRED_AUDIO = "pref_audio";
    public static final String COLUMN_PREFERRED_SUBTITLE = "pref_subtitle";
    public static final String COLUMN_PROFILE_ID = "profile_id";
    public static final String _ID = "_id";
    private final String TAG;
    private final Context context;
    private String profileId;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String URL = "content://com.dotscreen.tv.ChannelLanguageProvider/language";
    private static final Uri CONTENT_PROVIDER_URI = Uri.parse("content://com.dotscreen.tv.ChannelLanguageProvider/language");

    public LanguagePreferences(Context context, String str) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.profileId = str;
        String simpleName = getClass().getSimpleName();
        this.TAG = simpleName;
        Log.i(simpleName, "init ");
        if (this.profileId == null) {
            this.profileId = "generic_id";
        }
    }

    public /* synthetic */ LanguagePreferences(Context context, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? "generic_id" : str);
    }

    public final Context getContext() {
        return this.context;
    }

    public final String getProfileId() {
        return this.profileId;
    }

    public final void setProfileId(String str) {
        this.profileId = str;
    }

    /* JADX INFO: compiled from: LanguagePreferences.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0019\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/dotscreen/farkit/tools/LanguagePreferences$Companion;", "", "()V", "COLUMN_CHANNEL_ID", "", "COLUMN_PREFERRED_AUDIO", "COLUMN_PREFERRED_SUBTITLE", "COLUMN_PROFILE_ID", "CONTENT_PROVIDER_URI", "Landroid/net/Uri;", "kotlin.jvm.PlatformType", "getCONTENT_PROVIDER_URI", "()Landroid/net/Uri;", "URL", "getURL", "()Ljava/lang/String;", "_ID", "farkit_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getURL() {
            return LanguagePreferences.URL;
        }

        public final Uri getCONTENT_PROVIDER_URI() {
            return LanguagePreferences.CONTENT_PROVIDER_URI;
        }
    }

    public final void storePrefLanguage(String channelId, String audioLanguage, String subtitleLanguage) {
        if (channelId != null && audioLanguage != null) {
            this.context.getContentResolver().delete(CONTENT_PROVIDER_URI, "channel_id=" + channelId + " and profile_id=\"" + this.profileId + "\"", null);
            ContentValues contentValues = new ContentValues();
            contentValues.put("channel_id", channelId);
            contentValues.put("pref_audio", audioLanguage);
            contentValues.put("profile_id", this.profileId);
            contentValues.put("pref_subtitle", subtitleLanguage != null ? subtitleLanguage : "");
            this.context.getContentResolver().insert(CONTENT_PROVIDER_URI, contentValues);
            Log.i(this.TAG, "storePrefLanguage: preferred language stored for channelId = " + channelId + " audioLanguage = " + audioLanguage + " subtitle = " + subtitleLanguage + " for profilId = " + this.profileId);
            return;
        }
        Log.e(this.TAG, "storePrefLanguage: preferred language not stored for channelId = " + channelId);
    }

    public final Pair<String, String> retrieveAudioAndSubtitleLanguages(String channelId) throws IOException {
        String str;
        String str2;
        Intrinsics.checkParameterIsNotNull(channelId, "channelId");
        String string = null;
        String str3 = (String) null;
        boolean z = true;
        Cursor cursorQuery = this.context.getContentResolver().query(CONTENT_PROVIDER_URI, new String[]{"channel_id", "pref_audio", "pref_subtitle", "profile_id"}, "channel_id=?", new String[]{channelId}, null);
        String string2 = "es";
        if (cursorQuery != null) {
            Cursor cursor = cursorQuery;
            Throwable th = (Throwable) null;
            try {
                Cursor cursor2 = cursor;
                String str4 = str3;
                while (true) {
                    if (!cursor2.moveToNext()) {
                        string = str3;
                        str2 = str4;
                        break;
                    }
                    String string3 = cursor2.getString(0);
                    String string4 = cursor2.getString(3);
                    if (Intrinsics.areEqual(string3, channelId) && Intrinsics.areEqual(string4, this.profileId)) {
                        String string5 = cursor2.getString(1);
                        string2 = string5 == null || StringsKt.isBlank(string5) ? null : cursor2.getString(1);
                        String string6 = cursor2.getString(2);
                        if (string6 != null && !StringsKt.isBlank(string6)) {
                            z = false;
                        }
                        if (!z) {
                            string = cursor2.getString(2);
                        }
                        str2 = string4;
                        break;
                    }
                    str4 = string4;
                }
                cursor2.close();
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(cursor, th);
                String str5 = str2;
                str3 = string;
                str = str5;
            } catch (Throwable th2) {
                try {
                    throw th2;
                } catch (Throwable th3) {
                    CloseableKt.closeFinally(cursor, th2);
                    throw th3;
                }
            }
        } else {
            str = str3;
        }
        Pair<String, String> pair = new Pair<>(string2, str3);
        Log.i(this.TAG, "retrieveAudio: " + string2 + " and subtitle " + str3 + " for channel " + channelId + " for " + str);
        return pair;
    }
}
