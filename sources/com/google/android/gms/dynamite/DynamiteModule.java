package com.google.android.gms.dynamite;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import dalvik.system.DelegateLastClassLoader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class DynamiteModule {
    private static Boolean zza = null;
    private static zzk zzb = null;
    private static zzm zzc = null;
    private static String zzd = null;
    private static int zze = -1;
    private final Context zzi;
    private static final ThreadLocal<zza> zzf = new ThreadLocal<>();
    private static final VersionPolicy.zzb zzg = new com.google.android.gms.dynamite.zzb();
    public static final VersionPolicy PREFER_REMOTE = new com.google.android.gms.dynamite.zza();
    public static final VersionPolicy PREFER_LOCAL = new zzd();
    public static final VersionPolicy PREFER_REMOTE_VERSION_NO_FORCE_STAGING = new zzc();
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION = new zzf();
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new zze();
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION = new zzh();
    private static final VersionPolicy zzh = new zzg();

    /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
    public static class DynamiteLoaderClassLoader {
        public static ClassLoader sClassLoader;
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
    public interface VersionPolicy {

        /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
        public static class zza {
            public int zza = 0;
            public int zzb = 0;
            public int zzc = 0;
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
        public interface zzb {
            int zza(Context context, String str);

            int zza(Context context, String str, boolean z) throws LoadingException;
        }

        zza zza(Context context, String str, zzb zzbVar) throws LoadingException;
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
    private static class zza {
        public Cursor zza;

        private zza() {
        }

        /* synthetic */ zza(com.google.android.gms.dynamite.zzb zzbVar) {
            this();
        }
    }

    public static DynamiteModule load(Context context, VersionPolicy versionPolicy, String str) throws LoadingException {
        zza zzaVar = zzf.get();
        com.google.android.gms.dynamite.zzb zzbVar = null;
        zza zzaVar2 = new zza(zzbVar);
        zzf.set(zzaVar2);
        try {
            VersionPolicy.zza zzaVarZza = versionPolicy.zza(context, str, zzg);
            int i = zzaVarZza.zza;
            int i2 = zzaVarZza.zzb;
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 68 + String.valueOf(str).length());
            sb.append("Considering local module ");
            sb.append(str);
            sb.append(":");
            sb.append(i);
            sb.append(" and remote module ");
            sb.append(str);
            sb.append(":");
            sb.append(i2);
            Log.i("DynamiteModule", sb.toString());
            if (zzaVarZza.zzc == 0 || ((zzaVarZza.zzc == -1 && zzaVarZza.zza == 0) || (zzaVarZza.zzc == 1 && zzaVarZza.zzb == 0))) {
                int i3 = zzaVarZza.zza;
                int i4 = zzaVarZza.zzb;
                StringBuilder sb2 = new StringBuilder(91);
                sb2.append("No acceptable module found. Local version is ");
                sb2.append(i3);
                sb2.append(" and remote version is ");
                sb2.append(i4);
                sb2.append(".");
                throw new LoadingException(sb2.toString(), zzbVar);
            }
            if (zzaVarZza.zzc == -1) {
                DynamiteModule dynamiteModuleZza = zza(context, str);
                if (zzaVar2.zza != null) {
                    zzaVar2.zza.close();
                }
                zzf.set(zzaVar);
                return dynamiteModuleZza;
            }
            if (zzaVarZza.zzc != 1) {
                int i5 = zzaVarZza.zzc;
                StringBuilder sb3 = new StringBuilder(47);
                sb3.append("VersionPolicy returned invalid code:");
                sb3.append(i5);
                throw new LoadingException(sb3.toString(), zzbVar);
            }
            try {
                DynamiteModule dynamiteModuleZza2 = zza(context, str, zzaVarZza.zzb);
                if (zzaVar2.zza != null) {
                    zzaVar2.zza.close();
                }
                zzf.set(zzaVar);
                return dynamiteModuleZza2;
            } catch (LoadingException e) {
                String strValueOf = String.valueOf(e.getMessage());
                Log.w("DynamiteModule", strValueOf.length() != 0 ? "Failed to load remote module: ".concat(strValueOf) : new String("Failed to load remote module: "));
                if (zzaVarZza.zza == 0 || versionPolicy.zza(context, str, new zzb(zzaVarZza.zza, 0)).zzc != -1) {
                    throw new LoadingException("Remote load failed. No local fallback found.", e, zzbVar);
                }
                DynamiteModule dynamiteModuleZza3 = zza(context, str);
                if (zzaVar2.zza != null) {
                    zzaVar2.zza.close();
                }
                zzf.set(zzaVar);
                return dynamiteModuleZza3;
            }
        } catch (Throwable th) {
            if (zzaVar2.zza != null) {
                zzaVar2.zza.close();
            }
            zzf.set(zzaVar);
            throw th;
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
    public static class LoadingException extends Exception {
        private LoadingException(String str) {
            super(str);
        }

        private LoadingException(String str, Throwable th) {
            super(str, th);
        }

        /* synthetic */ LoadingException(String str, com.google.android.gms.dynamite.zzb zzbVar) {
            this(str);
        }

        /* synthetic */ LoadingException(String str, Throwable th, com.google.android.gms.dynamite.zzb zzbVar) {
            this(str, th);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
    private static class zzb implements VersionPolicy.zzb {
        private final int zza;
        private final int zzb = 0;

        public zzb(int i, int i2) {
            this.zza = i;
        }

        @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.zzb
        public final int zza(Context context, String str, boolean z) {
            return 0;
        }

        @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.zzb
        public final int zza(Context context, String str) {
            return this.zza;
        }
    }

    public static int getLocalVersion(Context context, String str) {
        try {
            ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 61);
            sb.append("com.google.android.gms.dynamite.descriptors.");
            sb.append(str);
            sb.append(".ModuleDescriptor");
            Class<?> clsLoadClass = classLoader.loadClass(sb.toString());
            Field declaredField = clsLoadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = clsLoadClass.getDeclaredField("MODULE_VERSION");
            if (!declaredField.get(null).equals(str)) {
                String strValueOf = String.valueOf(declaredField.get(null));
                StringBuilder sb2 = new StringBuilder(String.valueOf(strValueOf).length() + 51 + String.valueOf(str).length());
                sb2.append("Module descriptor id '");
                sb2.append(strValueOf);
                sb2.append("' didn't match expected id '");
                sb2.append(str);
                sb2.append("'");
                Log.e("DynamiteModule", sb2.toString());
                return 0;
            }
            return declaredField2.getInt(null);
        } catch (ClassNotFoundException unused) {
            StringBuilder sb3 = new StringBuilder(String.valueOf(str).length() + 45);
            sb3.append("Local module descriptor class for ");
            sb3.append(str);
            sb3.append(" not found.");
            Log.w("DynamiteModule", sb3.toString());
            return 0;
        } catch (Exception e) {
            String strValueOf2 = String.valueOf(e.getMessage());
            Log.e("DynamiteModule", strValueOf2.length() != 0 ? "Failed to load module descriptor class: ".concat(strValueOf2) : new String("Failed to load module descriptor class: "));
            return 0;
        }
    }

    public static int zza(Context context, String str, boolean z) {
        ClassLoader zziVar;
        try {
            synchronized (DynamiteModule.class) {
                Boolean bool = zza;
                if (bool == null) {
                    try {
                        Field declaredField = context.getApplicationContext().getClassLoader().loadClass(DynamiteLoaderClassLoader.class.getName()).getDeclaredField("sClassLoader");
                        synchronized (declaredField.getDeclaringClass()) {
                            ClassLoader classLoader = (ClassLoader) declaredField.get(null);
                            if (classLoader != null) {
                                if (classLoader == ClassLoader.getSystemClassLoader()) {
                                    bool = Boolean.FALSE;
                                } else {
                                    try {
                                        zza(classLoader);
                                    } catch (LoadingException unused) {
                                    }
                                    bool = Boolean.TRUE;
                                }
                            } else if ("com.google.android.gms".equals(context.getApplicationContext().getPackageName())) {
                                declaredField.set(null, ClassLoader.getSystemClassLoader());
                                bool = Boolean.FALSE;
                            } else {
                                try {
                                    int iZzc = zzc(context, str, z);
                                    if (zzd != null && !zzd.isEmpty()) {
                                        if (Build.VERSION.SDK_INT >= 29) {
                                            zziVar = new DelegateLastClassLoader(zzd, ClassLoader.getSystemClassLoader());
                                        } else {
                                            zziVar = new zzi(zzd, ClassLoader.getSystemClassLoader());
                                        }
                                        zza(zziVar);
                                        declaredField.set(null, zziVar);
                                        zza = Boolean.TRUE;
                                        return iZzc;
                                    }
                                    return iZzc;
                                } catch (LoadingException unused2) {
                                    declaredField.set(null, ClassLoader.getSystemClassLoader());
                                    bool = Boolean.FALSE;
                                }
                            }
                            zza = bool;
                        }
                    } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
                        String strValueOf = String.valueOf(e);
                        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 30);
                        sb.append("Failed to load module via V2: ");
                        sb.append(strValueOf);
                        Log.w("DynamiteModule", sb.toString());
                        bool = Boolean.FALSE;
                    }
                }
                if (bool.booleanValue()) {
                    try {
                        return zzc(context, str, z);
                    } catch (LoadingException e2) {
                        String strValueOf2 = String.valueOf(e2.getMessage());
                        Log.w("DynamiteModule", strValueOf2.length() != 0 ? "Failed to retrieve remote module version: ".concat(strValueOf2) : new String("Failed to retrieve remote module version: "));
                        return 0;
                    }
                }
                return zzb(context, str, z);
            }
        } catch (Throwable th) {
            CrashUtils.addDynamiteErrorToDropBox(context, th);
            throw th;
        }
    }

    private static int zzb(Context context, String str, boolean z) {
        zzk zzkVarZza = zza(context);
        if (zzkVarZza == null) {
            return 0;
        }
        try {
            if (zzkVarZza.zzb() >= 2) {
                return zzkVarZza.zzb(ObjectWrapper.wrap(context), str, z);
            }
            Log.w("DynamiteModule", "IDynamite loader version < 2, falling back to getModuleVersion2");
            return zzkVarZza.zza(ObjectWrapper.wrap(context), str, z);
        } catch (RemoteException e) {
            String strValueOf = String.valueOf(e.getMessage());
            Log.w("DynamiteModule", strValueOf.length() != 0 ? "Failed to retrieve remote module version: ".concat(strValueOf) : new String("Failed to retrieve remote module version: "));
            return 0;
        }
    }

    /* JADX WARN: Code duplicated, block: B:29:0x007c  */
    /* JADX WARN: Code duplicated, block: B:51:0x00b0  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.gms.dynamite.zzb] */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    private static int zzc(Context context, String str, boolean z) throws Throwable {
        Cursor cursor;
        ?? r0 = 0;
        Cursor cursor2 = null;
        try {
            ContentResolver contentResolver = context.getContentResolver();
            String str2 = z ? "api_force_staging" : "api";
            StringBuilder sb = new StringBuilder(str2.length() + 42 + String.valueOf(str).length());
            sb.append("content://com.google.android.gms.chimera/");
            sb.append(str2);
            sb.append("/");
            sb.append(str);
            Cursor cursorQuery = contentResolver.query(Uri.parse(sb.toString()), null, null, null, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst()) {
                        int i = cursorQuery.getInt(0);
                        if (i > 0) {
                            synchronized (DynamiteModule.class) {
                                zzd = cursorQuery.getString(2);
                                int columnIndex = cursorQuery.getColumnIndex("loaderVersion");
                                if (columnIndex >= 0) {
                                    zze = cursorQuery.getInt(columnIndex);
                                }
                            }
                            zza zzaVar = zzf.get();
                            if (zzaVar == null || zzaVar.zza != null) {
                                cursor2 = cursorQuery;
                            } else {
                                zzaVar.zza = cursorQuery;
                            }
                        } else {
                            cursor2 = cursorQuery;
                        }
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        return i;
                    }
                } catch (Exception e) {
                    cursor = cursorQuery;
                    e = e;
                    try {
                        if (e instanceof LoadingException) {
                            throw e;
                        }
                        throw new LoadingException("V2 version check failed", e, r0);
                    } catch (Throwable th) {
                        th = th;
                        r0 = cursor;
                        if (r0 != 0) {
                            r0.close();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    r0 = cursorQuery;
                    th = th2;
                    if (r0 != 0) {
                        r0.close();
                    }
                    throw th;
                }
            }
            Log.w("DynamiteModule", "Failed to retrieve remote module version.");
            throw new LoadingException("Failed to connect to dynamite module ContentResolver.", (com.google.android.gms.dynamite.zzb) r0);
        } catch (Exception e2) {
            e = e2;
            cursor = null;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public static int getRemoteVersion(Context context, String str) {
        return zza(context, str, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static DynamiteModule zza(Context context, String str) {
        String strValueOf = String.valueOf(str);
        Log.i("DynamiteModule", strValueOf.length() != 0 ? "Selected local version of ".concat(strValueOf) : new String("Selected local version of "));
        return new DynamiteModule(context.getApplicationContext());
    }

    private static DynamiteModule zza(Context context, String str, int i) throws LoadingException {
        Boolean bool;
        IObjectWrapper iObjectWrapperZza;
        com.google.android.gms.dynamite.zzb zzbVar = null;
        try {
            synchronized (DynamiteModule.class) {
                bool = zza;
            }
            if (bool == null) {
                throw new LoadingException("Failed to determine which loading route to use.", zzbVar);
            }
            if (bool.booleanValue()) {
                return zzb(context, str, i);
            }
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 51);
            sb.append("Selected remote version of ");
            sb.append(str);
            sb.append(", version >= ");
            sb.append(i);
            Log.i("DynamiteModule", sb.toString());
            zzk zzkVarZza = zza(context);
            if (zzkVarZza == null) {
                throw new LoadingException("Failed to create IDynamiteLoader.", zzbVar);
            }
            if (zzkVarZza.zzb() >= 2) {
                iObjectWrapperZza = zzkVarZza.zzb(ObjectWrapper.wrap(context), str, i);
            } else {
                Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to createModuleContext");
                iObjectWrapperZza = zzkVarZza.zza(ObjectWrapper.wrap(context), str, i);
            }
            if (ObjectWrapper.unwrap(iObjectWrapperZza) == null) {
                throw new LoadingException("Failed to load remote module.", zzbVar);
            }
            return new DynamiteModule((Context) ObjectWrapper.unwrap(iObjectWrapperZza));
        } catch (RemoteException e) {
            throw new LoadingException("Failed to load remote module.", e, zzbVar);
        } catch (LoadingException e2) {
            throw e2;
        } catch (Throwable th) {
            CrashUtils.addDynamiteErrorToDropBox(context, th);
            throw new LoadingException("Failed to load remote module.", th, zzbVar);
        }
    }

    private static zzk zza(Context context) {
        zzk zzjVar;
        synchronized (DynamiteModule.class) {
            if (zzb != null) {
                return zzb;
            }
            try {
                IBinder iBinder = (IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                if (iBinder == null) {
                    zzjVar = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                    if (iInterfaceQueryLocalInterface instanceof zzk) {
                        zzjVar = (zzk) iInterfaceQueryLocalInterface;
                    } else {
                        zzjVar = new zzj(iBinder);
                    }
                }
                if (zzjVar != null) {
                    zzb = zzjVar;
                    return zzjVar;
                }
            } catch (Exception e) {
                String strValueOf = String.valueOf(e.getMessage());
                Log.e("DynamiteModule", strValueOf.length() != 0 ? "Failed to load IDynamiteLoader from GmsCore: ".concat(strValueOf) : new String("Failed to load IDynamiteLoader from GmsCore: "));
            }
            return null;
        }
    }

    public final Context getModuleContext() {
        return this.zzi;
    }

    private static DynamiteModule zzb(Context context, String str, int i) throws RemoteException, LoadingException {
        zzm zzmVar;
        IObjectWrapper iObjectWrapperZza;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 51);
        sb.append("Selected remote version of ");
        sb.append(str);
        sb.append(", version >= ");
        sb.append(i);
        Log.i("DynamiteModule", sb.toString());
        synchronized (DynamiteModule.class) {
            zzmVar = zzc;
        }
        com.google.android.gms.dynamite.zzb zzbVar = null;
        if (zzmVar == null) {
            throw new LoadingException("DynamiteLoaderV2 was not cached.", zzbVar);
        }
        zza zzaVar = zzf.get();
        if (zzaVar == null || zzaVar.zza == null) {
            throw new LoadingException("No result cursor", zzbVar);
        }
        Context applicationContext = context.getApplicationContext();
        Cursor cursor = zzaVar.zza;
        ObjectWrapper.wrap(null);
        if (zza().booleanValue()) {
            Log.v("DynamiteModule", "Dynamite loader version >= 2, using loadModule2NoCrashUtils");
            iObjectWrapperZza = zzmVar.zzb(ObjectWrapper.wrap(applicationContext), str, i, ObjectWrapper.wrap(cursor));
        } else {
            Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to loadModule2");
            iObjectWrapperZza = zzmVar.zza(ObjectWrapper.wrap(applicationContext), str, i, ObjectWrapper.wrap(cursor));
        }
        Context context2 = (Context) ObjectWrapper.unwrap(iObjectWrapperZza);
        if (context2 == null) {
            throw new LoadingException("Failed to get module context", zzbVar);
        }
        return new DynamiteModule(context2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Boolean zza() {
        Boolean boolValueOf;
        synchronized (DynamiteModule.class) {
            boolValueOf = Boolean.valueOf(zze >= 2);
        }
        return boolValueOf;
    }

    private static void zza(ClassLoader classLoader) throws LoadingException {
        zzm zzlVar;
        com.google.android.gms.dynamite.zzb zzbVar = null;
        try {
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]);
            if (iBinder == null) {
                zzlVar = null;
            } else {
                IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                if (iInterfaceQueryLocalInterface instanceof zzm) {
                    zzlVar = (zzm) iInterfaceQueryLocalInterface;
                } else {
                    zzlVar = new zzl(iBinder);
                }
            }
            zzc = zzlVar;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new LoadingException("Failed to instantiate dynamite loader", e, zzbVar);
        }
    }

    public final IBinder instantiate(String str) throws LoadingException {
        try {
            return (IBinder) this.zzi.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            String strValueOf = String.valueOf(str);
            throw new LoadingException(strValueOf.length() != 0 ? "Failed to instantiate module class: ".concat(strValueOf) : new String("Failed to instantiate module class: "), e, null);
        }
    }

    private DynamiteModule(Context context) {
        this.zzi = (Context) Preconditions.checkNotNull(context);
    }
}
