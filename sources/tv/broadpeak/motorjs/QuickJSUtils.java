package tv.broadpeak.motorjs;

import com.hippo.quickjs.android.JSArray;
import com.hippo.quickjs.android.JSBoolean;
import com.hippo.quickjs.android.JSFloat64;
import com.hippo.quickjs.android.JSFunction;
import com.hippo.quickjs.android.JSInt;
import com.hippo.quickjs.android.JSNumber;
import com.hippo.quickjs.android.JSObject;
import com.hippo.quickjs.android.JSString;
import com.hippo.quickjs.android.JSValue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class QuickJSUtils {
    public static JSValue[] toJSValueArray(Object[] objArr) {
        JSValue[] jSValueArr = new JSValue[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            jSValueArr[i] = (JSValue) objArr[i];
        }
        return jSValueArr;
    }

    public static JSValue toJS(JSContext jSContext, Object obj) {
        if (obj == null) {
            return jSContext.createJSNull();
        }
        if ((obj instanceof Integer) || (obj instanceof Byte) || (obj instanceof Short)) {
            return jSContext.createJSNumber(((Integer) obj).intValue());
        }
        if (obj instanceof Long) {
            return jSContext.createJSNumber(((Long) obj).doubleValue());
        }
        if (obj instanceof Float) {
            return jSContext.createJSNumber(((Float) obj).doubleValue());
        }
        if (obj instanceof Double) {
            return jSContext.createJSNumber(((Double) obj).doubleValue());
        }
        if (obj instanceof Boolean) {
            return jSContext.createJSBoolean(((Boolean) obj).booleanValue());
        }
        if (obj instanceof String) {
            return jSContext.createJSString((String) obj);
        }
        if (obj instanceof List) {
            List list = (List) obj;
            JSArray jSArrayCreateJSArray = jSContext.createJSArray();
            for (int i = 0; i < list.size(); i++) {
                jSArrayCreateJSArray.setProperty(i, toJS(jSContext, list.get(i)));
            }
            return jSArrayCreateJSArray;
        }
        if (!(obj instanceof Map)) {
            return null;
        }
        Map map = (Map) obj;
        JSObject jSObjectCreateJSObject = jSContext.createJSObject();
        for (Object obj2 : map.keySet()) {
            if (obj2 instanceof String) {
                jSObjectCreateJSObject.setProperty((String) obj2, toJS(jSContext, map.get(obj2)));
            }
        }
        return jSObjectCreateJSObject;
    }

    public static Object toJava(JSContext jSContext, JSValue jSValue) {
        if (jSValue instanceof JSInt) {
            return Long.valueOf(((JSNumber) jSValue.cast(JSNumber.class)).getLong());
        }
        if (jSValue instanceof JSFloat64) {
            return Double.valueOf(((JSNumber) jSValue.cast(JSNumber.class)).getDouble());
        }
        if (jSValue instanceof JSString) {
            return ((JSString) jSValue.cast(JSString.class)).getString();
        }
        if (jSValue instanceof JSBoolean) {
            return Boolean.valueOf(((JSBoolean) jSValue.cast(JSBoolean.class)).getBoolean());
        }
        if (!(jSValue instanceof JSArray)) {
            return jSValue instanceof JSObject ? toMap(jSContext, (JSObject) jSValue.cast(JSObject.class)) : jSValue;
        }
        JSArray jSArray = (JSArray) jSValue.cast(JSArray.class);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSArray.getLength(); i++) {
            arrayList.add(toJava(jSContext, jSArray.getProperty(i)));
        }
        return arrayList;
    }

    public static Map<String, Object> toMap(JSContext jSContext, JSObject jSObject) {
        HashMap map = new HashMap();
        JSArray jSArray = (JSArray) ((JSFunction) ((JSObject) jSContext.getGlobalObject().getProperty("Object").cast(JSObject.class)).getProperty("keys").cast(JSFunction.class)).invoke(null, new JSValue[]{jSObject}).cast(JSArray.class);
        for (int i = 0; i < jSArray.getLength(); i++) {
            String string = ((JSString) jSArray.getProperty(i).cast(JSString.class)).getString();
            map.put(string, toJava(jSContext, jSObject.getProperty(string)));
        }
        return map;
    }

    public static void executePendingJobs(JSContext jSContext) {
        boolean zExecutePendingJob = jSContext.executePendingJob();
        while (zExecutePendingJob) {
            zExecutePendingJob = jSContext.executePendingJob();
        }
    }

    public static JSObject create(JSContext jSContext, String str) {
        jSContext.evaluate("instance = new " + str + "();", "new.js");
        JSObject jSObject = (JSObject) jSContext.getGlobalObject().getProperty("instance").cast(JSObject.class);
        jSContext.evaluate("delete instance;", "delete.js");
        return jSObject;
    }
}
