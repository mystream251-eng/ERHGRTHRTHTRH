package tv.broadpeak.smartlib.engine.system;

import android.util.Log;
import com.hippo.quickjs.android.JSObject;
import com.hippo.quickjs.android.JSValue;
import com.hippo.quickjs.android.Method;
import tv.broadpeak.motorjs.JSContext;

/* JADX INFO: loaded from: classes3.dex */
public class LoggerHandler {
    public JSObject createJSObject(JSContext jSContext) {
        JSObject jSObjectCreateJSObject = jSContext.createJSObject();
        try {
            jSObjectCreateJSObject.setProperty("log", jSContext.createJSFunction(this, Method.create(Void.class, LoggerHandler.class.getMethod("log", Integer.TYPE, String.class, String.class, JSValue.class))));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return jSObjectCreateJSObject;
    }

    public void log(int i, String str, String str2, JSValue jSValue) {
        if (i == 0) {
            Log.v(str, str2);
            return;
        }
        if (i == 1) {
            Log.d(str, str2);
            return;
        }
        if (i == 2) {
            Log.i(str, str2);
        } else if (i == 3) {
            Log.w(str, str2);
        } else {
            if (i != 4) {
                return;
            }
            Log.e(str, str2);
        }
    }
}
