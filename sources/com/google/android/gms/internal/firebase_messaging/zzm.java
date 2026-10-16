package com.google.android.gms.internal.firebase_messaging;

import java.io.PrintStream;

/* JADX INFO: compiled from: com.google.firebase:firebase-iid@@20.1.4 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzm {
    private static final zzp zza;
    private static final int zzb;

    /* JADX INFO: compiled from: com.google.firebase:firebase-iid@@20.1.4 */
    static final class zza extends zzp {
        zza() {
        }

        @Override // com.google.android.gms.internal.firebase_messaging.zzp
        public final void zza(Throwable th, Throwable th2) {
        }
    }

    public static void zza(Throwable th, Throwable th2) {
        zza.zza(th, th2);
    }

    private static Integer zza() {
        try {
            return (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
        } catch (Exception e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            e.printStackTrace(System.err);
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:10:0x001e A[Catch: all -> 0x002a, TryCatch #0 {all -> 0x002a, blocks: (B:5:0x0007, B:7:0x000f, B:8:0x0015, B:10:0x001e, B:11:0x0024), top: B:25:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:11:0x0024 A[Catch: all -> 0x002a, TRY_LEAVE, TryCatch #0 {all -> 0x002a, blocks: (B:5:0x0007, B:7:0x000f, B:8:0x0015, B:10:0x001e, B:11:0x0024), top: B:25:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:8:0x0015 A[Catch: all -> 0x002a, TryCatch #0 {all -> 0x002a, blocks: (B:5:0x0007, B:7:0x000f, B:8:0x0015, B:10:0x001e, B:11:0x0024), top: B:25:0x0007 }] */
    static {
        Integer numZza;
        zzp zzaVar;
        try {
            numZza = zza();
            if (numZza != null) {
                try {
                    if (numZza.intValue() >= 19) {
                        zzaVar = new zzs();
                    } else if (!Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic")) {
                        zzaVar = new zzq();
                    } else {
                        zzaVar = new zza();
                    }
                } catch (Throwable th) {
                    th = th;
                    PrintStream printStream = System.err;
                    String name = zza.class.getName();
                    StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 133);
                    sb.append("An error has occurred when initializing the try-with-resources desuguring strategy. The default strategy ");
                    sb.append(name);
                    sb.append("will be used. The error is: ");
                    printStream.println(sb.toString());
                    th.printStackTrace(System.err);
                    zzaVar = new zza();
                }
            } else if (!Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic")) {
                zzaVar = new zzq();
            } else {
                zzaVar = new zza();
            }
        } catch (Throwable th2) {
            th = th2;
            numZza = null;
        }
        zza = zzaVar;
        zzb = numZza != null ? numZza.intValue() : 1;
    }
}
