package com.dotscreen.tv.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SharedPreferencesHelper.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0006J\u0010\u0010\u0011\u001a\u00020\u00122\b\u0010\u0010\u001a\u0004\u0018\u00010\u0006J\u0010\u0010\u0013\u001a\u00020\u00142\b\u0010\u0010\u001a\u0004\u0018\u00010\u0006J\u0010\u0010\u0015\u001a\u00020\u00162\b\u0010\u0010\u001a\u0004\u0018\u00010\u0006J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\u0006J\u001a\u0010\u0018\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00062\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \b*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/dotscreen/tv/common/utils/SharedPreferencesHelper;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "PREF_NAME", "", "TAG", "kotlin.jvm.PlatformType", "editor", "Landroid/content/SharedPreferences$Editor;", "sharedPreferences", "Landroid/content/SharedPreferences;", "clearAllSharedPreferences", "", "remove", "key", "retrieveBoolean", "", "retrieveInt", "", "retrieveLong", "", "retrieveString", "store", "value", "Companion", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class SharedPreferencesHelper {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static SharedPreferencesHelper instance;
    private final String PREF_NAME;
    private final String TAG;
    private final SharedPreferences.Editor editor;
    private final SharedPreferences sharedPreferences;

    private SharedPreferencesHelper(Context context) {
        this.TAG = SharedPreferencesHelper.class.getSimpleName();
        this.PREF_NAME = "megacable-file";
        SharedPreferences sharedPreferences = context.getSharedPreferences("megacable-file", 0);
        Intrinsics.checkExpressionValueIsNotNull(sharedPreferences, "context.getSharedPrefere…ME, Context.MODE_PRIVATE)");
        this.sharedPreferences = sharedPreferences;
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        Intrinsics.checkExpressionValueIsNotNull(editorEdit, "sharedPreferences.edit()");
        this.editor = editorEdit;
    }

    public /* synthetic */ SharedPreferencesHelper(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    /* JADX INFO: compiled from: SharedPreferencesHelper.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/dotscreen/tv/common/utils/SharedPreferencesHelper$Companion;", "", "()V", "instance", "Lcom/dotscreen/tv/common/utils/SharedPreferencesHelper;", "getInstance", "context", "Landroid/content/Context;", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final SharedPreferencesHelper getInstance(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            if (SharedPreferencesHelper.instance == null) {
                SharedPreferencesHelper.instance = new SharedPreferencesHelper(context, null);
            }
            return SharedPreferencesHelper.instance;
        }
    }

    public final void store(String key, Object value) {
        if (value instanceof String) {
            this.editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            this.editor.putInt(key, ((Number) value).intValue());
        } else if (value instanceof Boolean) {
            this.editor.putBoolean(key, ((Boolean) value).booleanValue());
        } else if (value instanceof Date) {
            this.editor.putLong(key, ((Date) value).getTime());
        } else if (value instanceof Long) {
            this.editor.putLong(key, ((Number) value).longValue());
        }
        this.editor.apply();
    }

    public final String retrieveString(String key) {
        return this.sharedPreferences.getString(key, null);
    }

    public final int retrieveInt(String key) {
        return this.sharedPreferences.getInt(key, 0);
    }

    public final long retrieveLong(String key) {
        return this.sharedPreferences.getLong(key, -1L);
    }

    public final boolean retrieveBoolean(String key) {
        return this.sharedPreferences.getBoolean(key, false);
    }

    public final void remove(String key) {
        this.editor.remove(key);
        this.editor.apply();
    }

    public final void clearAllSharedPreferences() {
        this.sharedPreferences.edit().clear().commit();
    }
}
