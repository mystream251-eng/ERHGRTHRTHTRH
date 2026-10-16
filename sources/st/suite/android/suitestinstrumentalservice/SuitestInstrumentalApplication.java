package st.suite.android.suitestinstrumentalservice;

import android.app.Application;
import android.util.Log;
import android.view.View;
import st.suite.android.suitestinstrumentalservice.view.SuitestVideoController;

/* JADX INFO: loaded from: classes3.dex */
public class SuitestInstrumentalApplication extends Application {
    public static void init(Application application) {
        Log.d("SuitestInstrumentation", "stub");
    }

    public static void webViewInit(Application application) {
        Log.d("SuitestInstrumentation", "stub");
    }

    public static void addVideoControllerAndView(SuitestVideoController suitestVideoController, View view) {
        Log.d("SuitestInstrumentation", "stub");
    }

    public static void removeVideoController(View view) {
        Log.d("SuitestInstrumentation", "stub");
    }

    public static void setProcessName(String str) {
        Log.d("SuitestInstrumentation", "stub");
    }
}
