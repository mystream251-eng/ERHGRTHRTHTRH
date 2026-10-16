package com.dotscreen.farkit.design;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import com.dotscreen.farkit.network.RequestManager;
import io.reactivex.Maybe;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DesignManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\nJ\u0015\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0011\u001a\u00020\n¢\u0006\u0002\u0010\u0014J\u001c\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\nH\u0002J\u0016\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0011\u001a\u00020\nR\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/dotscreen/farkit/design/DesignManager;", "", "context", "Landroid/content/Context;", "json", "Lorg/json/JSONObject;", "requestManager", "Lcom/dotscreen/farkit/network/RequestManager;", "(Landroid/content/Context;Lorg/json/JSONObject;Lcom/dotscreen/farkit/network/RequestManager;)V", "TAG", "", "kotlin.jvm.PlatformType", "values", "", "getBitmap", "Lio/reactivex/Maybe;", "Landroid/graphics/Bitmap;", "path", "getColor", "", "(Ljava/lang/String;)Ljava/lang/Integer;", "parseJson", "", "parentKey", "tintDrawable", "Landroid/graphics/drawable/Drawable;", "drawable", "farkit_release"}, k = 1, mv = {1, 1, 16})
public final class DesignManager {
    private final String TAG;
    private final Context context;
    private final RequestManager requestManager;
    private final Map<String, String> values;

    public DesignManager(Context context, JSONObject json, RequestManager requestManager) throws JSONException {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(json, "json");
        Intrinsics.checkParameterIsNotNull(requestManager, "requestManager");
        this.context = context;
        this.requestManager = requestManager;
        this.TAG = DesignManager.class.getSimpleName();
        this.values = new LinkedHashMap();
        parseJson$default(this, json, null, 2, null);
    }

    public /* synthetic */ DesignManager(Context context, JSONObject jSONObject, RequestManager requestManager, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, jSONObject, (i & 4) != 0 ? new RequestManager(context, null, false, 6, null) : requestManager);
    }

    static /* synthetic */ void parseJson$default(DesignManager designManager, JSONObject jSONObject, String str, int i, Object obj) throws JSONException {
        if ((i & 2) != 0) {
            str = (String) null;
        }
        designManager.parseJson(jSONObject, str);
    }

    private final void parseJson(JSONObject json, String parentKey) throws JSONException {
        String path;
        Iterator<String> itKeys = json.keys();
        Intrinsics.checkExpressionValueIsNotNull(itKeys, "json.keys()");
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            if (parentKey != null) {
                path = parentKey + '.' + next;
            } else {
                path = next;
            }
            Object obj = json.get(next);
            if (obj instanceof JSONObject) {
                parseJson((JSONObject) obj, path);
            } else if (obj instanceof String) {
                Map<String, String> map = this.values;
                Intrinsics.checkExpressionValueIsNotNull(path, "path");
                map.put(path, (String) obj);
            }
        }
    }

    public final Integer getColor(String path) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        String str = this.values.get(path);
        if (str != null) {
            return Integer.valueOf(Color.parseColor(str));
        }
        return null;
    }

    public final Maybe<Bitmap> getBitmap(String path) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        String str = this.values.get(path);
        if (str != null) {
            Maybe<Bitmap> maybe = RequestManager.getBitmap$default(this.requestManager, 0, new URL(str), 0, 0, null, 28, null).toMaybe();
            Intrinsics.checkExpressionValueIsNotNull(maybe, "requestManager.getBitmap…r.GET, URL(it)).toMaybe()");
            return maybe;
        }
        Maybe<Bitmap> maybeEmpty = Maybe.empty();
        Intrinsics.checkExpressionValueIsNotNull(maybeEmpty, "Maybe.empty()");
        return maybeEmpty;
    }

    public final Drawable tintDrawable(Drawable drawable, String path) {
        Intrinsics.checkParameterIsNotNull(drawable, "drawable");
        Intrinsics.checkParameterIsNotNull(path, "path");
        Integer color = getColor(path);
        if (color == null) {
            return drawable;
        }
        int iIntValue = color.intValue();
        Drawable drawableMutate = drawable.getConstantState().newDrawable().mutate();
        Intrinsics.checkExpressionValueIsNotNull(drawableMutate, "drawable.constantState.newDrawable().mutate()");
        drawableMutate.setColorFilter(iIntValue, PorterDuff.Mode.SRC_IN);
        return drawableMutate;
    }
}
