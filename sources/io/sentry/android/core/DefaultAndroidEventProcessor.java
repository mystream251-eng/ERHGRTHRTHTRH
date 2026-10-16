package io.sentry.android.core;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.LocaleList;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.DisplayMetrics;
import androidx.core.os.EnvironmentCompat;
import io.sentry.DateUtils;
import io.sentry.EventProcessor;
import io.sentry.ILogger;
import io.sentry.SentryBaseEvent;
import io.sentry.SentryEvent;
import io.sentry.SentryLevel;
import io.sentry.android.core.internal.util.ConnectivityChecker;
import io.sentry.android.core.internal.util.DeviceOrientations;
import io.sentry.android.core.internal.util.MainThreadChecker;
import io.sentry.android.core.internal.util.RootChecker;
import io.sentry.protocol.App;
import io.sentry.protocol.Device;
import io.sentry.protocol.OperatingSystem;
import io.sentry.protocol.SentryThread;
import io.sentry.protocol.SentryTransaction;
import io.sentry.protocol.User;
import io.sentry.util.ApplyScopeUtils;
import io.sentry.util.Objects;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes2.dex */
final class DefaultAndroidEventProcessor implements EventProcessor {
    static final String EMULATOR = "emulator";
    static final String KERNEL_VERSION = "kernelVersion";
    static final String ROOTED = "rooted";
    static final String SIDE_LOADED = "sideLoaded";
    private final IBuildInfoProvider buildInfoProvider;
    final Context context;
    final Future<Map<String, Object>> contextData;
    private final ILogger logger;
    private final RootChecker rootChecker;

    public DefaultAndroidEventProcessor(Context context, ILogger iLogger, IBuildInfoProvider iBuildInfoProvider) {
        this(context, iLogger, iBuildInfoProvider, new RootChecker(context, iBuildInfoProvider, iLogger));
    }

    DefaultAndroidEventProcessor(Context context, ILogger iLogger, IBuildInfoProvider iBuildInfoProvider, RootChecker rootChecker) {
        this.context = (Context) Objects.requireNonNull(context, "The application context is required.");
        this.logger = (ILogger) Objects.requireNonNull(iLogger, "The Logger is required.");
        this.buildInfoProvider = (IBuildInfoProvider) Objects.requireNonNull(iBuildInfoProvider, "The BuildInfoProvider is required.");
        this.rootChecker = (RootChecker) Objects.requireNonNull(rootChecker, "The RootChecker is required.");
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        this.contextData = executorServiceNewSingleThreadExecutor.submit(new Callable() { // from class: io.sentry.android.core.-$$Lambda$DefaultAndroidEventProcessor$ZsPdDrAaOs30gov_5eSNgy_KYe8
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$new$0$DefaultAndroidEventProcessor();
            }
        });
        executorServiceNewSingleThreadExecutor.shutdown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: loadContextData, reason: merged with bridge method [inline-methods] */
    public Map<String, Object> lambda$new$0$DefaultAndroidEventProcessor() {
        HashMap map = new HashMap();
        map.put(ROOTED, Boolean.valueOf(this.rootChecker.isDeviceRooted()));
        String kernelVersion = getKernelVersion();
        if (kernelVersion != null) {
            map.put(KERNEL_VERSION, kernelVersion);
        }
        map.put(EMULATOR, isEmulator());
        Map<String, String> sideLoadedInfo = getSideLoadedInfo();
        if (sideLoadedInfo != null) {
            map.put(SIDE_LOADED, sideLoadedInfo);
        }
        return map;
    }

    @Override // io.sentry.EventProcessor
    public SentryEvent process(SentryEvent sentryEvent, Object obj) {
        boolean zShouldApplyScopeData = shouldApplyScopeData(sentryEvent, obj);
        if (zShouldApplyScopeData) {
            processNonCachedEvent(sentryEvent);
            setThreads(sentryEvent);
        }
        setCommons(sentryEvent, true, zShouldApplyScopeData);
        return sentryEvent;
    }

    private void setCommons(SentryBaseEvent sentryBaseEvent, boolean z, boolean z2) {
        mergeUser(sentryBaseEvent);
        setDevice(sentryBaseEvent, z, z2);
        mergeOS(sentryBaseEvent);
        setSideLoadedInfo(sentryBaseEvent);
    }

    private boolean shouldApplyScopeData(SentryBaseEvent sentryBaseEvent, Object obj) {
        if (ApplyScopeUtils.shouldApplyScopeData(obj)) {
            return true;
        }
        this.logger.log(SentryLevel.DEBUG, "Event was cached so not applying data relevant to the current app execution/version: %s", sentryBaseEvent.getEventId());
        return false;
    }

    private void mergeUser(SentryBaseEvent sentryBaseEvent) {
        User user = sentryBaseEvent.getUser();
        if (user == null) {
            sentryBaseEvent.setUser(getDefaultUser());
        } else if (user.getId() == null) {
            user.setId(getDeviceId());
        }
    }

    private void setDevice(SentryBaseEvent sentryBaseEvent, boolean z, boolean z2) {
        if (sentryBaseEvent.getContexts().getDevice() == null) {
            sentryBaseEvent.getContexts().setDevice(getDevice(z, z2));
        }
    }

    private void mergeOS(SentryBaseEvent sentryBaseEvent) {
        String str;
        OperatingSystem operatingSystem = sentryBaseEvent.getContexts().getOperatingSystem();
        sentryBaseEvent.getContexts().setOperatingSystem(getOperatingSystem());
        if (operatingSystem != null) {
            String name = operatingSystem.getName();
            if (name == null || name.isEmpty()) {
                str = "os_1";
            } else {
                str = "os_" + name.trim().toLowerCase(Locale.ROOT);
            }
            sentryBaseEvent.getContexts().put(str, operatingSystem);
        }
    }

    private void processNonCachedEvent(SentryBaseEvent sentryBaseEvent) {
        App app = sentryBaseEvent.getContexts().getApp();
        if (app == null) {
            app = new App();
        }
        setAppExtras(app);
        setPackageInfo(sentryBaseEvent, app);
        sentryBaseEvent.getContexts().setApp(app);
    }

    private void setThreads(SentryEvent sentryEvent) {
        if (sentryEvent.getThreads() != null) {
            for (SentryThread sentryThread : sentryEvent.getThreads()) {
                sentryThread.setCurrent(Boolean.valueOf(MainThreadChecker.isMainThread(sentryThread)));
            }
        }
    }

    private void setPackageInfo(SentryBaseEvent sentryBaseEvent, App app) {
        PackageInfo packageInfo = ContextUtils.getPackageInfo(this.context, this.logger);
        if (packageInfo != null) {
            setDist(sentryBaseEvent, ContextUtils.getVersionCode(packageInfo));
            setAppPackageInfo(app, packageInfo);
        }
    }

    private void setDist(SentryBaseEvent sentryBaseEvent, String str) {
        if (sentryBaseEvent.getDist() == null) {
            sentryBaseEvent.setDist(str);
        }
    }

    private void setAppExtras(App app) {
        app.setAppName(getApplicationName());
        app.setAppStartTime(AppStartState.getInstance().getAppStartTime());
    }

    private String getAbi() {
        return Build.CPU_ABI;
    }

    private String getAbi2() {
        return Build.CPU_ABI2;
    }

    private void setArchitectures(Device device) {
        if (Build.VERSION.SDK_INT >= 21) {
            device.setArchs(Build.SUPPORTED_ABIS);
        } else {
            device.setArchs(new String[]{getAbi(), getAbi2()});
        }
    }

    private Long getMemorySize(ActivityManager.MemoryInfo memoryInfo) {
        if (Build.VERSION.SDK_INT >= 16) {
            return Long.valueOf(memoryInfo.totalMem);
        }
        return Long.valueOf(Runtime.getRuntime().totalMemory());
    }

    private Device getDevice(boolean z, boolean z2) {
        Device device = new Device();
        device.setName(getDeviceName());
        device.setManufacturer(Build.MANUFACTURER);
        device.setBrand(Build.BRAND);
        device.setFamily(getFamily());
        device.setModel(Build.MODEL);
        device.setModelId(Build.ID);
        setArchitectures(device);
        if (z) {
            setDeviceIO(device, z2);
        }
        device.setOrientation(getOrientation());
        try {
            Object obj = this.contextData.get().get(EMULATOR);
            if (obj != null) {
                device.setSimulator((Boolean) obj);
            }
        } catch (Throwable th) {
            this.logger.log(SentryLevel.ERROR, "Error getting emulator.", th);
        }
        DisplayMetrics displayMetrics = getDisplayMetrics();
        if (displayMetrics != null) {
            device.setScreenWidthPixels(Integer.valueOf(displayMetrics.widthPixels));
            device.setScreenHeightPixels(Integer.valueOf(displayMetrics.heightPixels));
            device.setScreenDensity(Float.valueOf(displayMetrics.density));
            device.setScreenDpi(Integer.valueOf(displayMetrics.densityDpi));
        }
        device.setBootTime(getBootTime());
        device.setTimezone(getTimeZone());
        if (device.getId() == null) {
            device.setId(getDeviceId());
        }
        Locale locale = Locale.getDefault();
        if (device.getLanguage() == null) {
            device.setLanguage(locale.getLanguage());
        }
        if (device.getLocale() == null) {
            device.setLocale(locale.toString());
        }
        return device;
    }

    private void setDeviceIO(Device device, boolean z) {
        Boolean bool;
        Intent batteryIntent = getBatteryIntent();
        if (batteryIntent != null) {
            device.setBatteryLevel(getBatteryLevel(batteryIntent));
            device.setCharging(isCharging(batteryIntent));
            device.setBatteryTemperature(getBatteryTemperature(batteryIntent));
        }
        int i = AnonymousClass1.$SwitchMap$io$sentry$android$core$internal$util$ConnectivityChecker$Status[ConnectivityChecker.getConnectionStatus(this.context, this.logger).ordinal()];
        if (i == 1) {
            bool = false;
        } else {
            bool = i != 2 ? null : true;
        }
        device.setOnline(bool);
        ActivityManager.MemoryInfo memInfo = getMemInfo();
        if (memInfo != null) {
            device.setMemorySize(getMemorySize(memInfo));
            if (z) {
                device.setFreeMemory(Long.valueOf(memInfo.availMem));
                device.setLowMemory(Boolean.valueOf(memInfo.lowMemory));
            }
        }
        File externalFilesDir = this.context.getExternalFilesDir(null);
        if (externalFilesDir != null) {
            StatFs statFs = new StatFs(externalFilesDir.getPath());
            device.setStorageSize(getTotalInternalStorage(statFs));
            device.setFreeStorage(getUnusedInternalStorage(statFs));
        }
        StatFs externalStorageStat = getExternalStorageStat(externalFilesDir);
        if (externalStorageStat != null) {
            device.setExternalStorageSize(getTotalExternalStorage(externalStorageStat));
            device.setExternalFreeStorage(getUnusedExternalStorage(externalStorageStat));
        }
        if (device.getConnectionType() == null) {
            device.setConnectionType(ConnectivityChecker.getConnectionType(this.context, this.logger, this.buildInfoProvider));
        }
    }

    /* JADX INFO: renamed from: io.sentry.android.core.DefaultAndroidEventProcessor$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$sentry$android$core$internal$util$ConnectivityChecker$Status;

        static {
            int[] iArr = new int[ConnectivityChecker.Status.values().length];
            $SwitchMap$io$sentry$android$core$internal$util$ConnectivityChecker$Status = iArr;
            try {
                iArr[ConnectivityChecker.Status.NOT_CONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$sentry$android$core$internal$util$ConnectivityChecker$Status[ConnectivityChecker.Status.CONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private String getDeviceName() {
        if (Build.VERSION.SDK_INT >= 17) {
            return Settings.Global.getString(this.context.getContentResolver(), "device_name");
        }
        return null;
    }

    private TimeZone getTimeZone() {
        if (Build.VERSION.SDK_INT >= 24) {
            LocaleList locales = this.context.getResources().getConfiguration().getLocales();
            if (!locales.isEmpty()) {
                return Calendar.getInstance(locales.get(0)).getTimeZone();
            }
        }
        return Calendar.getInstance().getTimeZone();
    }

    private Date getBootTime() {
        try {
            return DateUtils.getDateTime(System.currentTimeMillis() - SystemClock.elapsedRealtime());
        } catch (IllegalArgumentException e) {
            this.logger.log(SentryLevel.ERROR, e, "Error getting the device's boot time.", new Object[0]);
            return null;
        }
    }

    private ActivityManager.MemoryInfo getMemInfo() {
        try {
            ActivityManager activityManager = (ActivityManager) this.context.getSystemService("activity");
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            if (activityManager != null) {
                activityManager.getMemoryInfo(memoryInfo);
                return memoryInfo;
            }
            this.logger.log(SentryLevel.INFO, "Error getting MemoryInfo.", new Object[0]);
            return null;
        } catch (Throwable th) {
            this.logger.log(SentryLevel.ERROR, "Error getting MemoryInfo.", th);
            return null;
        }
    }

    private Intent getBatteryIntent() {
        return this.context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    }

    private String getFamily() {
        try {
            return Build.MODEL.split(" ", -1)[0];
        } catch (Throwable th) {
            this.logger.log(SentryLevel.ERROR, "Error getting device family.", th);
            return null;
        }
    }

    private Float getBatteryLevel(Intent intent) {
        try {
            int intExtra = intent.getIntExtra("level", -1);
            int intExtra2 = intent.getIntExtra("scale", -1);
            if (intExtra != -1 && intExtra2 != -1) {
                return Float.valueOf((intExtra / intExtra2) * 100.0f);
            }
            return null;
        } catch (Throwable th) {
            this.logger.log(SentryLevel.ERROR, "Error getting device battery level.", th);
            return null;
        }
    }

    private Boolean isCharging(Intent intent) {
        try {
            int intExtra = intent.getIntExtra("plugged", -1);
            boolean z = true;
            if (intExtra != 1 && intExtra != 2) {
                z = false;
            }
            return Boolean.valueOf(z);
        } catch (Throwable th) {
            this.logger.log(SentryLevel.ERROR, "Error getting device charging state.", th);
            return null;
        }
    }

    private Float getBatteryTemperature(Intent intent) {
        try {
            int intExtra = intent.getIntExtra("temperature", -1);
            if (intExtra != -1) {
                return Float.valueOf(intExtra / 10.0f);
            }
            return null;
        } catch (Throwable th) {
            this.logger.log(SentryLevel.ERROR, "Error getting battery temperature.", th);
            return null;
        }
    }

    private Device.DeviceOrientation getOrientation() {
        Device.DeviceOrientation orientation;
        Throwable th;
        try {
            orientation = DeviceOrientations.getOrientation(this.context.getResources().getConfiguration().orientation);
            if (orientation == null) {
                try {
                    this.logger.log(SentryLevel.INFO, "No device orientation available (ORIENTATION_SQUARE|ORIENTATION_UNDEFINED)", new Object[0]);
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    this.logger.log(SentryLevel.ERROR, "Error getting device orientation.", th);
                    return orientation;
                }
            }
        } catch (Throwable th3) {
            orientation = null;
            th = th3;
        }
        return orientation;
    }

    private Boolean isEmulator() {
        try {
            return Boolean.valueOf((Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.startsWith(EnvironmentCompat.MEDIA_UNKNOWN) || Build.HARDWARE.contains("goldfish") || Build.HARDWARE.contains("ranchu") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || Build.PRODUCT.contains("sdk_google") || Build.PRODUCT.contains("google_sdk") || Build.PRODUCT.contains("sdk") || Build.PRODUCT.contains("sdk_x86") || Build.PRODUCT.contains("vbox86p") || Build.PRODUCT.contains(EMULATOR) || Build.PRODUCT.contains("simulator"));
        } catch (Throwable th) {
            this.logger.log(SentryLevel.ERROR, "Error checking whether application is running in an emulator.", th);
            return null;
        }
    }

    private Long getTotalInternalStorage(StatFs statFs) {
        try {
            return Long.valueOf(getBlockCountLong(statFs) * getBlockSizeLong(statFs));
        } catch (Throwable th) {
            this.logger.log(SentryLevel.ERROR, "Error getting total internal storage amount.", th);
            return null;
        }
    }

    private long getBlockSizeLong(StatFs statFs) {
        if (Build.VERSION.SDK_INT >= 18) {
            return statFs.getBlockSizeLong();
        }
        return getBlockSizeDep(statFs);
    }

    private int getBlockSizeDep(StatFs statFs) {
        return statFs.getBlockSize();
    }

    private long getBlockCountLong(StatFs statFs) {
        if (Build.VERSION.SDK_INT >= 18) {
            return statFs.getBlockCountLong();
        }
        return getBlockCountDep(statFs);
    }

    private int getBlockCountDep(StatFs statFs) {
        return statFs.getBlockCount();
    }

    private long getAvailableBlocksLong(StatFs statFs) {
        if (Build.VERSION.SDK_INT >= 18) {
            return statFs.getAvailableBlocksLong();
        }
        return getAvailableBlocksDep(statFs);
    }

    private int getAvailableBlocksDep(StatFs statFs) {
        return statFs.getAvailableBlocks();
    }

    private Long getUnusedInternalStorage(StatFs statFs) {
        try {
            return Long.valueOf(getAvailableBlocksLong(statFs) * getBlockSizeLong(statFs));
        } catch (Throwable th) {
            this.logger.log(SentryLevel.ERROR, "Error getting unused internal storage amount.", th);
            return null;
        }
    }

    private StatFs getExternalStorageStat(File file) {
        if (!isExternalStorageMounted()) {
            File externalStorageDep = getExternalStorageDep(file);
            if (externalStorageDep != null) {
                return new StatFs(externalStorageDep.getPath());
            }
            this.logger.log(SentryLevel.INFO, "Not possible to read external files directory", new Object[0]);
            return null;
        }
        this.logger.log(SentryLevel.INFO, "External storage is not mounted or emulated.", new Object[0]);
        return null;
    }

    private File[] getExternalFilesDirs() {
        if (Build.VERSION.SDK_INT >= 19) {
            return this.context.getExternalFilesDirs(null);
        }
        File externalFilesDir = this.context.getExternalFilesDir(null);
        if (externalFilesDir != null) {
            return new File[]{externalFilesDir};
        }
        return null;
    }

    private File getExternalStorageDep(File file) {
        File[] externalFilesDirs = getExternalFilesDirs();
        if (externalFilesDirs != null) {
            String absolutePath = file != null ? file.getAbsolutePath() : null;
            for (File file2 : externalFilesDirs) {
                if (file2 != null && (absolutePath == null || absolutePath.isEmpty() || !file2.getAbsolutePath().contains(absolutePath))) {
                    return file2;
                }
            }
        } else {
            this.logger.log(SentryLevel.INFO, "Not possible to read getExternalFilesDirs", new Object[0]);
        }
        return null;
    }

    private Long getTotalExternalStorage(StatFs statFs) {
        try {
            return Long.valueOf(getBlockCountLong(statFs) * getBlockSizeLong(statFs));
        } catch (Throwable th) {
            this.logger.log(SentryLevel.ERROR, "Error getting total external storage amount.", th);
            return null;
        }
    }

    private boolean isExternalStorageMounted() {
        String externalStorageState = Environment.getExternalStorageState();
        return ("mounted".equals(externalStorageState) || "mounted_ro".equals(externalStorageState)) && !Environment.isExternalStorageEmulated();
    }

    private Long getUnusedExternalStorage(StatFs statFs) {
        try {
            return Long.valueOf(getAvailableBlocksLong(statFs) * getBlockSizeLong(statFs));
        } catch (Throwable th) {
            this.logger.log(SentryLevel.ERROR, "Error getting unused external storage amount.", th);
            return null;
        }
    }

    private DisplayMetrics getDisplayMetrics() {
        try {
            return this.context.getResources().getDisplayMetrics();
        } catch (Throwable th) {
            this.logger.log(SentryLevel.ERROR, "Error getting DisplayMetrics.", th);
            return null;
        }
    }

    private OperatingSystem getOperatingSystem() {
        OperatingSystem operatingSystem = new OperatingSystem();
        operatingSystem.setName("Android");
        operatingSystem.setVersion(Build.VERSION.RELEASE);
        operatingSystem.setBuild(Build.DISPLAY);
        try {
            Object obj = this.contextData.get().get(KERNEL_VERSION);
            if (obj != null) {
                operatingSystem.setKernelVersion((String) obj);
            }
            Object obj2 = this.contextData.get().get(ROOTED);
            if (obj2 != null) {
                operatingSystem.setRooted((Boolean) obj2);
            }
        } catch (Throwable th) {
            this.logger.log(SentryLevel.ERROR, "Error getting OperatingSystem.", th);
        }
        return operatingSystem;
    }

    private void setAppPackageInfo(App app, PackageInfo packageInfo) {
        app.setAppIdentifier(packageInfo.packageName);
        app.setAppVersion(packageInfo.versionName);
        app.setAppBuild(ContextUtils.getVersionCode(packageInfo));
    }

    private String getKernelVersion() {
        String property = System.getProperty("os.version");
        File file = new File("/proc/version");
        if (!file.canRead()) {
            return property;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            try {
                String line = bufferedReader.readLine();
                bufferedReader.close();
                return line;
            } catch (Throwable th) {
                try {
                    bufferedReader.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException e) {
            this.logger.log(SentryLevel.ERROR, "Exception while attempting to read kernel information", e);
            return property;
        }
    }

    private String getApplicationName() {
        try {
            ApplicationInfo applicationInfo = this.context.getApplicationInfo();
            int i = applicationInfo.labelRes;
            if (i == 0) {
                if (applicationInfo.nonLocalizedLabel != null) {
                    return applicationInfo.nonLocalizedLabel.toString();
                }
                return this.context.getPackageManager().getApplicationLabel(applicationInfo).toString();
            }
            return this.context.getString(i);
        } catch (Throwable th) {
            this.logger.log(SentryLevel.ERROR, "Error getting application name.", th);
            return null;
        }
    }

    public User getDefaultUser() {
        User user = new User();
        user.setId(getDeviceId());
        return user;
    }

    private String getDeviceId() {
        try {
            return Installation.id(this.context);
        } catch (Throwable th) {
            this.logger.log(SentryLevel.ERROR, "Error getting installationId.", th);
            return null;
        }
    }

    private Map<String, String> getSideLoadedInfo() {
        String str;
        try {
            PackageInfo packageInfo = ContextUtils.getPackageInfo(this.context, this.logger);
            PackageManager packageManager = this.context.getPackageManager();
            if (packageInfo != null && packageManager != null) {
                str = packageInfo.packageName;
                try {
                    String installerPackageName = packageManager.getInstallerPackageName(str);
                    HashMap map = new HashMap();
                    if (installerPackageName != null) {
                        map.put("isSideLoaded", "false");
                        map.put("installerStore", installerPackageName);
                    } else {
                        map.put("isSideLoaded", "true");
                    }
                    return map;
                } catch (IllegalArgumentException unused) {
                    this.logger.log(SentryLevel.DEBUG, "%s package isn't installed.", str);
                    return null;
                }
            }
        } catch (IllegalArgumentException unused2) {
            str = null;
        }
        return null;
    }

    private void setSideLoadedInfo(SentryBaseEvent sentryBaseEvent) {
        try {
            Object obj = this.contextData.get().get(SIDE_LOADED);
            if (obj instanceof Map) {
                for (Map.Entry entry : ((Map) obj).entrySet()) {
                    sentryBaseEvent.setTag((String) entry.getKey(), (String) entry.getValue());
                }
            }
        } catch (Throwable th) {
            this.logger.log(SentryLevel.ERROR, "Error getting side loaded info.", th);
        }
    }

    @Override // io.sentry.EventProcessor
    public SentryTransaction process(SentryTransaction sentryTransaction, Object obj) {
        boolean zShouldApplyScopeData = shouldApplyScopeData(sentryTransaction, obj);
        if (zShouldApplyScopeData) {
            processNonCachedEvent(sentryTransaction);
        }
        setCommons(sentryTransaction, false, zShouldApplyScopeData);
        return sentryTransaction;
    }
}
