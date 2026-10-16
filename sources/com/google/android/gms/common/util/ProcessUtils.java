package com.google.android.gms.common.util;

import android.os.Process;
import android.os.StrictMode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
public class ProcessUtils {
    private static String zza;
    private static int zzb;

    private ProcessUtils() {
    }

    @Nullable
    public static String getMyProcessName() {
        if (zza == null) {
            if (zzb == 0) {
                zzb = Process.myPid();
            }
            zza = zza(zzb);
        }
        return zza;
    }

    @Nullable
    private static String zza(int i) throws Throwable {
        Throwable th;
        BufferedReader bufferedReaderZza;
        String strTrim = null;
        if (i <= 0) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder(25);
            sb.append("/proc/");
            sb.append(i);
            sb.append("/cmdline");
            bufferedReaderZza = zza(sb.toString());
            try {
                strTrim = bufferedReaderZza.readLine().trim();
                IOUtils.closeQuietly(bufferedReaderZza);
            } catch (IOException unused) {
                IOUtils.closeQuietly(bufferedReaderZza);
            } catch (Throwable th2) {
                th = th2;
                IOUtils.closeQuietly(bufferedReaderZza);
                throw th;
            }
        } catch (IOException unused2) {
            bufferedReaderZza = null;
        } catch (Throwable th3) {
            th = th3;
            bufferedReaderZza = null;
        }
        return strTrim;
    }

    private static BufferedReader zza(String str) throws IOException {
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            return new BufferedReader(new FileReader(str));
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        }
    }
}
