package tv.broadpeak.smartlib.engine.manager;

import com.hippo.quickjs.android.JSFunction;
import com.hippo.quickjs.android.JSObject;
import com.hippo.quickjs.android.JSValue;
import tv.broadpeak.smartlib.engine.CoreEngine;
import tv.broadpeak.smartlib.engine.executor.CoreJSCallback;
import tv.broadpeak.smartlib.engine.system.LoggerHandler;

/* JADX INFO: loaded from: classes3.dex */
public class LoggerManager {
    public static final int LOG_LEVEL_OUTPUT_BASIC = 0;
    public static final int LOG_LEVEL_OUTPUT_NONE = -1;
    public static final int LOG_LEVEL_OUTPUT_VERBOSE = 1;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static LoggerManager f204a;
    public LoggerHandler b = new LoggerHandler();
    public int c = 1;

    public static synchronized LoggerManager getInstance() {
        if (f204a == null) {
            f204a = new LoggerManager();
        }
        return f204a;
    }

    public int getLogLevel() {
        return this.c;
    }

    public void printDebugLogs(String str, String str2) {
        if (this.c < 1) {
            return;
        }
        printLogs(1, str, str2);
    }

    public void printErrorLogs(String str, String str2) {
        if (this.c <= -1) {
            return;
        }
        printLogs(4, str, str2);
    }

    public void printInfoLogs(String str, String str2) {
        if (this.c <= -1) {
            return;
        }
        printLogs(2, str, str2);
    }

    public void printLogs(int i, String str, String str2) {
        this.b.log(i, str, str2, null);
    }

    public void printVerboseLogs(String str, String str2) {
        if (this.c < 1) {
            return;
        }
        printLogs(0, str, str2);
    }

    public void printWarnLogs(String str, String str2) {
        if (this.c <= -1) {
            return;
        }
        printLogs(3, str, str2);
    }

    public void setLogLevel(final int i) {
        this.c = i;
        CoreEngine.getInstance().getSingleton("LoggerManager", new CoreJSCallback() { // from class: tv.broadpeak.smartlib.engine.manager.-$$Lambda$vBtTsNHCy_9QVMfzsU-IS2dsBtY
            @Override // tv.broadpeak.smartlib.engine.executor.CoreJSCallback
            public final void run(JSObject jSObject) {
                ((JSFunction) jSObject.getProperty("setLogLevel").cast(JSFunction.class)).invoke(jSObject, new JSValue[]{CoreEngine.getInstance().getJSContext().createJSNumber(i)});
            }
        });
    }
}
