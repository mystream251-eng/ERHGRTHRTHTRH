package com.dotscreen.portal.utils;

import android.os.Build;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: FeaturesManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/utils/FeaturesManager;", "", "()V", "featureNames", "", "", "isFeatureEnabled", "", "name", "loadConfig", "", "json", "Lorg/json/JSONObject;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class FeaturesManager {
    private final List<String> featureNames = new ArrayList();

    public final void loadConfig(JSONObject json) throws JSONException {
        String serial;
        Intrinsics.checkParameterIsNotNull(json, "json");
        try {
            serial = Build.getSerial();
        } catch (Exception unused) {
            serial = null;
        }
        this.featureNames.clear();
        Iterator<String> itKeys = json.keys();
        Intrinsics.checkExpressionValueIsNotNull(itKeys, "json.keys()");
        while (itKeys.hasNext()) {
            String key = itKeys.next();
            JSONObject jSONObject = json.getJSONObject(key);
            int i = 0;
            boolean zOptBoolean = jSONObject.optBoolean("enabled", false);
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("serials");
            if (jSONArrayOptJSONArray != null) {
                int length = jSONArrayOptJSONArray.length();
                int i2 = 0;
                while (i < length) {
                    if (Intrinsics.areEqual(jSONArrayOptJSONArray.getString(i), serial)) {
                        i2 = 1;
                    }
                    i++;
                }
                i = i2;
            }
            if (zOptBoolean && (jSONArrayOptJSONArray == null || i != 0)) {
                List<String> list = this.featureNames;
                Intrinsics.checkExpressionValueIsNotNull(key, "key");
                list.add(key);
            }
        }
        if (!this.featureNames.isEmpty()) {
            Log.d("FeaturesManager", "Config loaded. Enabled features:");
            Iterator<T> it = this.featureNames.iterator();
            while (it.hasNext()) {
                Log.d("FeaturesManager", " • " + ((String) it.next()));
            }
            return;
        }
        Log.d("FeaturesManager", "Config loaded. No feature enabled.");
    }

    public final boolean isFeatureEnabled(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return this.featureNames.contains(name);
    }
}
