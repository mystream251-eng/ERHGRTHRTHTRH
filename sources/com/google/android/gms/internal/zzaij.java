package com.google.android.gms.internal;

import android.R;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.KeyguardManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.google.android.exoplayer2.C;
import com.google.android.gms.ads.AdActivity;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.util.zzp;
import com.google.android.gms.common.util.zzs;
import com.viaccessorca.drm.impl.JniCDxJavaHttpAgent;
import io.sentry.protocol.Device;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaij {
    public static final Handler zza = new zzahx(Looper.getMainLooper());
    private String zzd;
    private Pattern zzg;
    private Pattern zzh;
    private final Object zzb = new Object();
    private boolean zzc = true;
    private boolean zze = false;
    private boolean zzf = false;

    public static Bitmap zza(View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return bitmapCreateBitmap;
    }

    public static Bundle zza(zzhm zzhmVar) {
        String strZzc;
        String strZze;
        String strZzc2;
        if (zzhmVar == null) {
            return null;
        }
        if (!((Boolean) zzlc.zzf().zza(zzoi.zzas)).booleanValue()) {
            if (!((Boolean) zzlc.zzf().zza(zzoi.zzau)).booleanValue()) {
                return null;
            }
        }
        if (zzbt.zzi().zzl().zzb() && zzbt.zzi().zzl().zzd()) {
            return null;
        }
        if (zzhmVar.zzd()) {
            zzhmVar.zzc();
        }
        zzhg zzhgVarZzb = zzhmVar.zzb();
        if (zzhgVarZzb != null) {
            strZzc = zzhgVarZzb.zzb();
            strZzc2 = zzhgVarZzb.zzc();
            strZze = zzhgVarZzb.zzd();
            if (strZzc != null) {
                zzbt.zzi().zzl().zza(strZzc);
            }
            if (strZze != null) {
                zzbt.zzi().zzl().zzb(strZze);
            }
        } else {
            strZzc = zzbt.zzi().zzl().zzc();
            strZze = zzbt.zzi().zzl().zze();
            strZzc2 = null;
        }
        Bundle bundle = new Bundle(1);
        if (strZze != null) {
            if (((Boolean) zzlc.zzf().zza(zzoi.zzau)).booleanValue() && !zzbt.zzi().zzl().zzd()) {
                bundle.putString("v_fp_vertical", strZze);
            }
        }
        if (strZzc != null) {
            if (((Boolean) zzlc.zzf().zza(zzoi.zzas)).booleanValue() && !zzbt.zzi().zzl().zzb()) {
                bundle.putString("fingerprint", strZzc);
                if (!strZzc.equals(strZzc2)) {
                    bundle.putString("v_fp", strZzc2);
                }
            }
        }
        if (bundle.isEmpty()) {
            return null;
        }
        return bundle;
    }

    public static DisplayMetrics zza(WindowManager windowManager) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static PopupWindow zza(View view, int i, int i2, boolean z) {
        return new PopupWindow(view, i, i2, false);
    }

    public static String zza() {
        return UUID.randomUUID().toString();
    }

    public static String zza(Context context, View view, zzko zzkoVar) {
        if (!((Boolean) zzlc.zzf().zza(zzoi.zzbg)).booleanValue()) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(ToolboxAnalyticsModuleKt.M_WIDTH, zzkoVar.zze);
            jSONObject2.put(ToolboxAnalyticsModuleKt.M_HEIGHT, zzkoVar.zzb);
            jSONObject.put("size", jSONObject2);
            jSONObject.put("activity", zzl(context));
            if (!zzkoVar.zzd) {
                JSONArray jSONArray = new JSONArray();
                while (view != null) {
                    Object parent = view.getParent();
                    if (parent != null) {
                        int iIndexOfChild = parent instanceof ViewGroup ? ((ViewGroup) parent).indexOfChild(view) : -1;
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put("type", parent.getClass().getName());
                        jSONObject3.put("index_of_child", iIndexOfChild);
                        jSONArray.put(jSONObject3);
                    }
                    view = (parent == null || !(parent instanceof View)) ? null : (View) parent;
                }
                if (jSONArray.length() > 0) {
                    jSONObject.put("parents", jSONArray);
                }
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            zzahw.zzc("Fail to get view hierarchy json", e);
            return null;
        }
    }

    public static String zza(InputStreamReader inputStreamReader) throws IOException {
        StringBuilder sb = new StringBuilder(8192);
        char[] cArr = new char[2048];
        while (true) {
            int i = inputStreamReader.read(cArr);
            if (i == -1) {
                return sb.toString();
            }
            sb.append(cArr, 0, i);
        }
    }

    public static String zza(String str) {
        return Uri.parse(str).buildUpon().query(null).build().toString();
    }

    public static Map<String, String> zza(Uri uri) {
        if (uri == null) {
            return null;
        }
        HashMap map = new HashMap();
        for (String str : zzbt.zzg().zza(uri)) {
            map.put(str, uri.getQueryParameter(str));
        }
        return map;
    }

    private final JSONArray zza(Collection<?> collection) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            zza(jSONArray, it.next());
        }
        return jSONArray;
    }

    private final JSONObject zza(Bundle bundle) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            zza(jSONObject, str, bundle.get(str));
        }
        return jSONObject;
    }

    public static void zza(Context context, Intent intent) {
        try {
            context.startActivity(intent);
        } catch (Throwable unused) {
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            context.startActivity(intent);
        }
    }

    public static void zza(Context context, Uri uri) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            Bundle bundle = new Bundle();
            intent.putExtras(bundle);
            if (((Boolean) zzlc.zzf().zza(zzoi.zzgb)).booleanValue()) {
                zzb(context, intent);
            }
            bundle.putString("com.android.browser.application_id", context.getPackageName());
            context.startActivity(intent);
            String string = uri.toString();
            StringBuilder sb = new StringBuilder(String.valueOf(string).length() + 26);
            sb.append("Opening ");
            sb.append(string);
            sb.append(" in a new browser.");
            zzahw.zzb(sb.toString());
        } catch (ActivityNotFoundException e) {
            zzahw.zzb("No browser is found.", e);
        }
    }

    public static void zza(Context context, String str, List<String> list) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            new zzakd(context, str, it.next()).zzc();
        }
    }

    public static void zza(Runnable runnable) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            runnable.run();
        } else {
            zzaid.zza(runnable);
        }
    }

    private final void zza(JSONArray jSONArray, Object obj) throws JSONException {
        Object objZza;
        if (obj instanceof Bundle) {
            objZza = zza((Bundle) obj);
        } else if (obj instanceof Map) {
            objZza = zza((Map<String, ?>) obj);
        } else {
            if (!(obj instanceof Collection)) {
                if (!(obj instanceof Object[])) {
                    jSONArray.put(obj);
                    return;
                }
                JSONArray jSONArray2 = new JSONArray();
                for (Object obj2 : (Object[]) obj) {
                    zza(jSONArray2, obj2);
                }
                jSONArray.put(jSONArray2);
                return;
            }
            objZza = zza((Collection<?>) obj);
        }
        jSONArray.put(objZza);
    }

    private final void zza(JSONObject jSONObject, String str, Object obj) throws JSONException {
        Collection<?> collectionAsList;
        Object objZza;
        if (obj instanceof Bundle) {
            objZza = zza((Bundle) obj);
        } else if (obj instanceof Map) {
            objZza = zza((Map<String, ?>) obj);
        } else {
            if (obj instanceof Collection) {
                if (str == null) {
                    str = "null";
                }
                collectionAsList = (Collection) obj;
            } else {
                if (!(obj instanceof Object[])) {
                    jSONObject.put(str, obj);
                    return;
                }
                collectionAsList = Arrays.asList((Object[]) obj);
            }
            objZza = zza(collectionAsList);
        }
        jSONObject.put(str, objZza);
    }

    private static boolean zza(int i, int i2, int i3) {
        return Math.abs(i - i2) <= i3;
    }

    public static boolean zza(Activity activity, Configuration configuration) {
        zzlc.zza();
        int iZza = zzako.zza(activity, configuration.screenHeightDp);
        int iZza2 = zzako.zza(activity, configuration.screenWidthDp);
        DisplayMetrics displayMetricsZza = zza((WindowManager) activity.getApplicationContext().getSystemService("window"));
        int i = displayMetricsZza.heightPixels;
        int i2 = displayMetricsZza.widthPixels;
        int identifier = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int dimensionPixelSize = identifier > 0 ? activity.getResources().getDimensionPixelSize(identifier) : 0;
        int iRound = ((int) Math.round(((double) activity.getResources().getDisplayMetrics().density) + 0.5d)) * ((Integer) zzlc.zzf().zza(zzoi.zzgl)).intValue();
        return zza(i, iZza + dimensionPixelSize, iRound) && zza(i2, iZza2, iRound);
    }

    public static boolean zza(Context context) {
        String str;
        boolean z;
        Intent intent = new Intent();
        intent.setClassName(context, AdActivity.CLASS_NAME);
        ResolveInfo resolveInfoResolveActivity = context.getPackageManager().resolveActivity(intent, 65536);
        if (resolveInfoResolveActivity == null || resolveInfoResolveActivity.activityInfo == null) {
            str = "Could not find com.google.android.gms.ads.AdActivity, please make sure it is declared in AndroidManifest.xml.";
        } else {
            if ((resolveInfoResolveActivity.activityInfo.configChanges & 16) == 0) {
                zzahw.zze(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "keyboard"));
                z = false;
            } else {
                z = true;
            }
            if ((resolveInfoResolveActivity.activityInfo.configChanges & 32) == 0) {
                zzahw.zze(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "keyboardHidden"));
                z = false;
            }
            if ((resolveInfoResolveActivity.activityInfo.configChanges & 128) == 0) {
                zzahw.zze(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "orientation"));
                z = false;
            }
            if ((resolveInfoResolveActivity.activityInfo.configChanges & 256) == 0) {
                zzahw.zze(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "screenLayout"));
                z = false;
            }
            if ((resolveInfoResolveActivity.activityInfo.configChanges & 512) == 0) {
                zzahw.zze(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "uiMode"));
                z = false;
            }
            if ((resolveInfoResolveActivity.activityInfo.configChanges & 1024) == 0) {
                zzahw.zze(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "screenSize"));
                z = false;
            }
            if ((resolveInfoResolveActivity.activityInfo.configChanges & 2048) != 0) {
                return z;
            }
            str = String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "smallestScreenSize");
        }
        zzahw.zze(str);
        return false;
    }

    public static boolean zza(Context context, String str, String str2) {
        return zzbih.zza(context).zza(str2, str) == 0;
    }

    public static boolean zza(ClassLoader classLoader, Class<?> cls, String str) {
        try {
            return cls.isAssignableFrom(Class.forName(str, false, classLoader));
        } catch (Throwable unused) {
            return false;
        }
    }

    public static int[] zza(Activity activity) {
        View viewFindViewById;
        Window window = activity.getWindow();
        return (window == null || (viewFindViewById = window.findViewById(R.id.content)) == null) ? zze() : new int[]{viewFindViewById.getWidth(), viewFindViewById.getHeight()};
    }

    public static int zzb(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            String strValueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 22);
            sb.append("Could not parse value:");
            sb.append(strValueOf);
            zzahw.zze(sb.toString());
            return 0;
        }
    }

    public static Bitmap zzb(View view) {
        if (view == null) {
            return null;
        }
        Bitmap bitmapZzf = zzf(view);
        return bitmapZzf == null ? zze(view) : bitmapZzf;
    }

    public static String zzb() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        if (str2.startsWith(str)) {
            return str2;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(str2).length());
        sb.append(str);
        sb.append(" ");
        sb.append(str2);
        return sb.toString();
    }

    public static String zzb(Context context, String str) {
        try {
            return new String(zzp.zza(context.openFileInput(str), true), "UTF-8");
        } catch (IOException unused) {
            zzahw.zzb("Error reading from internal storage.");
            return "";
        }
    }

    public static void zzb(Context context, Intent intent) {
        if (intent != null && zzs.zzd()) {
            Bundle extras = intent.getExtras() != null ? intent.getExtras() : new Bundle();
            extras.putBinder("android.support.customtabs.extra.SESSION", null);
            extras.putString("com.android.browser.application_id", context.getPackageName());
            intent.putExtras(extras);
        }
    }

    public static void zzb(Context context, String str, String str2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str2);
        zza(context, str, arrayList);
    }

    public static Bundle zzc() {
        Bundle bundle = new Bundle();
        try {
            if (((Boolean) zzlc.zzf().zza(zzoi.zzy)).booleanValue()) {
                Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
                Debug.getMemoryInfo(memoryInfo);
                bundle.putParcelable("debug_memory_info", memoryInfo);
            }
            if (((Boolean) zzlc.zzf().zza(zzoi.zzz)).booleanValue()) {
                Runtime runtime = Runtime.getRuntime();
                bundle.putLong("runtime_free_memory", runtime.freeMemory());
                bundle.putLong("runtime_max_memory", runtime.maxMemory());
                bundle.putLong("runtime_total_memory", runtime.totalMemory());
            }
            bundle.putInt("web_view_count", zzbt.zzi().zzk());
        } catch (Exception e) {
            zzahw.zzc("Unable to gather memory stats", e);
        }
        return bundle;
    }

    public static void zzc(Context context, String str, String str2) {
        try {
            FileOutputStream fileOutputStreamOpenFileOutput = context.openFileOutput(str, 0);
            fileOutputStreamOpenFileOutput.write(str2.getBytes("UTF-8"));
            fileOutputStreamOpenFileOutput.close();
        } catch (Exception e) {
            zzahw.zzb("Error writing to file in internal storage.", e);
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0012  */
    public static boolean zzc(View view) {
        Activity activity;
        View rootView = view.getRootView();
        if (rootView != null) {
            Context context = rootView.getContext();
            if (context instanceof Activity) {
                activity = (Activity) context;
            } else {
                activity = null;
            }
        } else {
            activity = null;
        }
        if (activity == null) {
            return false;
        }
        Window window = activity.getWindow();
        WindowManager.LayoutParams attributes = window != null ? window.getAttributes() : null;
        return (attributes == null || (attributes.flags & 524288) == 0) ? false : true;
    }

    public static boolean zzc(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.matches("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|webp))$)");
    }

    public static int zzd(View view) {
        if (view == null) {
            return -1;
        }
        ViewParent parent = view.getParent();
        while (parent != null && !(parent instanceof AdapterView)) {
            parent = parent.getParent();
        }
        if (parent == null) {
            return -1;
        }
        return ((AdapterView) parent).getPositionForView(view);
    }

    private static String zzd() {
        StringBuilder sb = new StringBuilder(256);
        sb.append("Mozilla/5.0 (Linux; U; Android");
        if (Build.VERSION.RELEASE != null) {
            sb.append(" ");
            sb.append(Build.VERSION.RELEASE);
        }
        sb.append("; ");
        sb.append(Locale.getDefault());
        if (Build.DEVICE != null) {
            sb.append("; ");
            sb.append(Build.DEVICE);
            if (Build.DISPLAY != null) {
                sb.append(" Build/");
                sb.append(Build.DISPLAY);
            }
        }
        sb.append(") AppleWebKit/533 Version/4.0 Safari/533");
        return sb.toString();
    }

    protected static String zzd(Context context) {
        try {
            return new WebView(context).getSettings().getUserAgentString();
        } catch (Throwable unused) {
            return zzd();
        }
    }

    public static AlertDialog.Builder zze(Context context) {
        return new AlertDialog.Builder(context);
    }

    private static Bitmap zze(View view) {
        try {
            int width = view.getWidth();
            int height = view.getHeight();
            if (width != 0 && height != 0) {
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.RGB_565);
                Canvas canvas = new Canvas(bitmapCreateBitmap);
                view.layout(0, 0, width, height);
                view.draw(canvas);
                return bitmapCreateBitmap;
            }
            zzahw.zze("Width or height of view is zero");
            return null;
        } catch (RuntimeException e) {
            zzahw.zzb("Fail to capture the webview", e);
            return null;
        }
    }

    private static int[] zze() {
        return new int[]{0, 0};
    }

    private static Bitmap zzf(View view) {
        Bitmap bitmapCreateBitmap = null;
        try {
            boolean zIsDrawingCacheEnabled = view.isDrawingCacheEnabled();
            view.setDrawingCacheEnabled(true);
            Bitmap drawingCache = view.getDrawingCache();
            bitmapCreateBitmap = drawingCache != null ? Bitmap.createBitmap(drawingCache) : null;
            view.setDrawingCacheEnabled(zIsDrawingCacheEnabled);
        } catch (RuntimeException e) {
            zzahw.zzb("Fail to capture the web view", e);
        }
        return bitmapCreateBitmap;
    }

    public static zznu zzf(Context context) {
        return new zznu(context);
    }

    public static String zzf(String str) {
        return TextUtils.isEmpty(str) ? "" : str.split(";")[0].trim();
    }

    public static String zzg(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String[] strArrSplit = str.split(";");
        if (strArrSplit.length == 1) {
            return "";
        }
        for (int i = 1; i < strArrSplit.length; i++) {
            if (strArrSplit[i].trim().startsWith("charset")) {
                String[] strArrSplit2 = strArrSplit[i].trim().split("=");
                if (strArrSplit2.length > 1) {
                    return strArrSplit2[1].trim();
                }
            }
        }
        return "";
    }

    public static boolean zzg(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            if (activityManager == null || keyguardManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null) {
                return false;
            }
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (Process.myPid() == runningAppProcessInfo.pid) {
                    if (runningAppProcessInfo.importance != 100 || keyguardManager.inKeyguardRestrictedInputMode()) {
                        break;
                        break;
                    }
                    PowerManager powerManager = (PowerManager) context.getSystemService("power");
                    if (powerManager == null ? false : powerManager.isScreenOn()) {
                        return true;
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    public static Bitmap zzh(Context context) {
        Bitmap bitmapZze = null;
        if (!(context instanceof Activity)) {
            return null;
        }
        try {
            if (((Boolean) zzlc.zzf().zza(zzoi.zzel)).booleanValue()) {
                Window window = ((Activity) context).getWindow();
                if (window != null) {
                    bitmapZze = zzf(window.getDecorView().getRootView());
                }
            } else {
                bitmapZze = zze(((Activity) context).getWindow().getDecorView());
            }
        } catch (RuntimeException e) {
            zzahw.zzb("Fail to capture screen shot", e);
        }
        return bitmapZze;
    }

    public static int zzi(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (applicationInfo == null) {
            return 0;
        }
        return applicationInfo.targetSdkVersion;
    }

    public static boolean zzj(Context context) {
        KeyguardManager keyguardManagerZzm;
        return context != null && zzs.zzb() && (keyguardManagerZzm = zzm(context)) != null && keyguardManagerZzm.isKeyguardLocked();
    }

    public static boolean zzk(Context context) {
        try {
            context.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi");
            return false;
        } catch (ClassNotFoundException unused) {
            return true;
        } catch (Throwable th) {
            zzahw.zzb("Error loading class.", th);
            zzbt.zzi().zza(th, "AdUtil.isLiteSdk");
            return false;
        }
    }

    private static String zzl(Context context) {
        List<ActivityManager.RunningTaskInfo> runningTasks;
        ActivityManager.RunningTaskInfo runningTaskInfo;
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager != null && (runningTasks = activityManager.getRunningTasks(1)) != null && !runningTasks.isEmpty() && (runningTaskInfo = runningTasks.get(0)) != null && runningTaskInfo.topActivity != null) {
                return runningTaskInfo.topActivity.getClassName();
            }
        } catch (Exception unused) {
        }
        return null;
    }

    private static KeyguardManager zzm(Context context) {
        Object systemService = context.getSystemService("keyguard");
        if (systemService == null || !(systemService instanceof KeyguardManager)) {
            return null;
        }
        return (KeyguardManager) systemService;
    }

    /* JADX WARN: Code duplicated, block: B:32:0x00a4 A[Catch: Exception -> 0x00b3, all -> 0x00cd, TRY_LEAVE, TryCatch #2 {Exception -> 0x00b3, blocks: (B:30:0x009a, B:32:0x00a4), top: B:47:0x009a, outer: #3 }] */
    public final String zza(Context context, String str) {
        synchronized (this.zzb) {
            if (this.zzd != null) {
                return this.zzd;
            }
            if (str == null) {
                return zzd();
            }
            try {
                this.zzd = zzbt.zzg().zza(context);
            } catch (Exception unused) {
            }
            if (!TextUtils.isEmpty(this.zzd)) {
                String strValueOf = String.valueOf(this.zzd);
                StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 10 + String.valueOf(str).length());
                sb.append(strValueOf);
                sb.append(" (Mobile; ");
                sb.append(str);
                this.zzd = sb.toString();
                if (zzbih.zza(context).zza()) {
                    this.zzd = String.valueOf(this.zzd).concat(";aia");
                }
                String strConcat = String.valueOf(this.zzd).concat(")");
                this.zzd = strConcat;
                return strConcat;
            }
            zzlc.zza();
            if (zzako.zzb()) {
                this.zzd = zzd(context);
            } else {
                this.zzd = null;
                zza.post(new zzail(this, context));
                while (this.zzd == null) {
                    try {
                        this.zzb.wait();
                    } catch (InterruptedException unused2) {
                        String strZzd = zzd();
                        this.zzd = strZzd;
                        String strValueOf2 = String.valueOf(strZzd);
                        zzahw.zze(strValueOf2.length() != 0 ? "Interrupted, use default user agent: ".concat(strValueOf2) : new String("Interrupted, use default user agent: "));
                    }
                }
            }
            String strValueOf3 = String.valueOf(this.zzd);
            StringBuilder sb2 = new StringBuilder(String.valueOf(strValueOf3).length() + 10 + String.valueOf(str).length());
            sb2.append(strValueOf3);
            sb2.append(" (Mobile; ");
            sb2.append(str);
            this.zzd = sb2.toString();
            try {
                if (zzbih.zza(context).zza()) {
                    this.zzd = String.valueOf(this.zzd).concat(";aia");
                }
            } catch (Exception e) {
                zzbt.zzi().zza(e, "AdUtil.getUserAgent");
            }
            String strConcat2 = String.valueOf(this.zzd).concat(")");
            this.zzd = strConcat2;
            return strConcat2;
            throw th;
        }
    }

    public final JSONObject zza(Bundle bundle, JSONObject jSONObject) {
        if (bundle != null) {
            try {
                return zza(bundle);
            } catch (JSONException e) {
                zzahw.zzb("Error converting Bundle to JSON", e);
            }
        }
        return null;
    }

    public final JSONObject zza(Map<String, ?> map) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            for (String str : map.keySet()) {
                zza(jSONObject, str, map.get(str));
            }
            return jSONObject;
        } catch (ClassCastException e) {
            String strValueOf = String.valueOf(e.getMessage());
            throw new JSONException(strValueOf.length() != 0 ? "Could not convert map to JSON: ".concat(strValueOf) : new String("Could not convert map to JSON: "));
        }
    }

    public final void zza(Context context, String str, WebSettings webSettings) {
        webSettings.setUserAgentString(zza(context, str));
    }

    public final void zza(Context context, String str, String str2, Bundle bundle, boolean z) {
        if (z) {
            zzbt.zze();
            bundle.putString(Device.TYPE, zzb());
            bundle.putString("eids", TextUtils.join(",", zzoi.zza()));
        }
        zzlc.zza();
        zzako.zza(context, str, str2, bundle, z, new zzaim(this, context, str));
    }

    public final void zza(Context context, String str, boolean z, HttpURLConnection httpURLConnection) {
        httpURLConnection.setConnectTimeout(60000);
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setReadTimeout(60000);
        httpURLConnection.setRequestProperty(JniCDxJavaHttpAgent.DX_USER_AGENT_HDR, zza(context, str));
        httpURLConnection.setUseCaches(false);
    }

    public final void zza(Context context, List<String> list) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (TextUtils.isEmpty(zzfxb.zza(activity))) {
                return;
            }
            if (list == null) {
                zzahw.zza("Cannot ping urls: empty list.");
            } else {
                if (!zzpf.zza(context)) {
                    zzahw.zza("Cannot ping url because custom tabs is not supported");
                    return;
                }
                zzpf zzpfVar = new zzpf();
                zzpfVar.zza(new zzaik(this, list, zzpfVar, context));
                zzpfVar.zzb(activity);
            }
        }
    }

    public final boolean zza(View view, Context context) {
        Context applicationContext = context.getApplicationContext();
        return zza(view, applicationContext != null ? (PowerManager) applicationContext.getSystemService("power") : null, zzm(context));
    }

    /* JADX WARN: Code duplicated, block: B:14:0x002f  */
    public final boolean zza(View view, PowerManager powerManager, KeyguardManager keyguardManager) {
        boolean z;
        if (zzbt.zze().zzc) {
            z = true;
        } else {
            if (keyguardManager == null ? false : keyguardManager.inKeyguardRestrictedInputMode()) {
                if (((Boolean) zzlc.zzf().zza(zzoi.zzdg)).booleanValue() && zzc(view)) {
                    z = true;
                } else {
                    z = false;
                }
            } else {
                z = true;
            }
        }
        if (view.getVisibility() == 0 && view.isShown()) {
            if ((powerManager == null || powerManager.isScreenOn()) && z) {
                if (!((Boolean) zzlc.zzf().zza(zzoi.zzde)).booleanValue() || view.getLocalVisibleRect(new Rect()) || view.getGlobalVisibleRect(new Rect())) {
                    return true;
                }
            }
        }
        return false;
    }

    public final void zzb(Context context, String str, String str2, Bundle bundle, boolean z) {
        if (((Boolean) zzlc.zzf().zza(zzoi.zzdj)).booleanValue()) {
            zza(context, str, str2, bundle, z);
        }
    }

    public final boolean zzb(Context context) {
        if (this.zze) {
            return false;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        context.getApplicationContext().registerReceiver(new zzaio(this, null), intentFilter);
        this.zze = true;
        return true;
    }

    public final int[] zzb(Activity activity) {
        int[] iArrZza = zza(activity);
        zzlc.zza();
        zzlc.zza();
        return new int[]{zzako.zzb(activity, iArrZza[0]), zzako.zzb(activity, iArrZza[1])};
    }

    public final boolean zzc(Context context) {
        if (this.zzf) {
            return false;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.google.android.ads.intent.DEBUG_LOGGING_ENABLEMENT_CHANGED");
        context.getApplicationContext().registerReceiver(new zzain(this, null), intentFilter);
        this.zzf = true;
        return true;
    }

    public final int[] zzc(Activity activity) {
        View viewFindViewById;
        Window window = activity.getWindow();
        int[] iArrZze = (window == null || (viewFindViewById = window.findViewById(R.id.content)) == null) ? zze() : new int[]{viewFindViewById.getTop(), viewFindViewById.getBottom()};
        zzlc.zza();
        zzlc.zza();
        return new int[]{zzako.zzb(activity, iArrZze[0]), zzako.zzb(activity, iArrZze[1])};
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0025 A[Catch: all -> 0x0043, TryCatch #0 {, blocks: (B:6:0x0009, B:8:0x000d, B:11:0x0037, B:12:0x0041, B:10:0x0025), top: B:18:0x0009, outer: #1 }] */
    public final boolean zzd(String str) {
        boolean zMatches;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            synchronized (this) {
                if (this.zzg != null) {
                    if (!((String) zzlc.zzf().zza(zzoi.zzbm)).equals(this.zzg.pattern())) {
                        this.zzg = Pattern.compile((String) zzlc.zzf().zza(zzoi.zzbm));
                    }
                } else {
                    this.zzg = Pattern.compile((String) zzlc.zzf().zza(zzoi.zzbm));
                }
                zMatches = this.zzg.matcher(str).matches();
            }
            return zMatches;
        } catch (PatternSyntaxException unused) {
            return false;
        }
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0025 A[Catch: all -> 0x0043, TryCatch #0 {, blocks: (B:6:0x0009, B:8:0x000d, B:11:0x0037, B:12:0x0041, B:10:0x0025), top: B:18:0x0009, outer: #1 }] */
    public final boolean zze(String str) {
        boolean zMatches;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            synchronized (this) {
                if (this.zzh != null) {
                    if (!((String) zzlc.zzf().zza(zzoi.zzbn)).equals(this.zzh.pattern())) {
                        this.zzh = Pattern.compile((String) zzlc.zzf().zza(zzoi.zzbn));
                    }
                } else {
                    this.zzh = Pattern.compile((String) zzlc.zzf().zza(zzoi.zzbn));
                }
                zMatches = this.zzh.matcher(str).matches();
            }
            return zMatches;
        } catch (PatternSyntaxException unused) {
            return false;
        }
    }
}
