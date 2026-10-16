package com.dotscreen.farkit.tools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: json.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u001c\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0000¨\u0006\n"}, d2 = {"jsonArrayToList", "", "", "jsonArray", "Lorg/json/JSONArray;", "jsonObjectToMap", "", "", "jsonObject", "Lorg/json/JSONObject;", "farkit_release"}, k = 2, mv = {1, 1, 16})
public final class JsonKt {
    public static final Map<String, Object> jsonObjectToMap(JSONObject jsonObject) throws JSONException {
        Intrinsics.checkParameterIsNotNull(jsonObject, "jsonObject");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<String> itKeys = jsonObject.keys();
        Intrinsics.checkExpressionValueIsNotNull(itKeys, "jsonObject.keys()");
        while (itKeys.hasNext()) {
            String key = itKeys.next();
            Object objOpt = jsonObject.opt(key);
            if (objOpt instanceof JSONObject) {
                objOpt = jsonObjectToMap((JSONObject) objOpt);
            } else if (objOpt instanceof JSONArray) {
                objOpt = jsonArrayToList((JSONArray) objOpt);
            }
            if (objOpt != null) {
                Intrinsics.checkExpressionValueIsNotNull(key, "key");
                linkedHashMap.put(key, objOpt);
            }
        }
        return MapsKt.toMap(linkedHashMap);
    }

    public static final List<Object> jsonArrayToList(JSONArray jsonArray) throws JSONException {
        Intrinsics.checkParameterIsNotNull(jsonArray, "jsonArray");
        ArrayList arrayList = new ArrayList();
        int length = jsonArray.length();
        for (int i = 0; i < length; i++) {
            Object objOpt = jsonArray.opt(i);
            if (objOpt instanceof JSONObject) {
                objOpt = jsonObjectToMap((JSONObject) objOpt);
            } else if (objOpt instanceof JSONArray) {
                objOpt = jsonArrayToList((JSONArray) objOpt);
            }
            if (objOpt != null) {
                arrayList.add(objOpt);
            }
        }
        return CollectionsKt.toList(arrayList);
    }
}
