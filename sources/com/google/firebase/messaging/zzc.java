package com.google.firebase.messaging;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.tasks.Tasks;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: com.google.firebase:firebase-messaging@@20.1.4 */
/* JADX INFO: loaded from: classes2.dex */
final class zzc {
    private final Executor zza;
    private final Context zzb;
    private final zzr zzc;

    public zzc(Context context, zzr zzrVar, Executor executor) {
        this.zza = executor;
        this.zzb = context;
        this.zzc = zzrVar;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0058 A[EDGE_INSN: B:20:0x0058->B:21:0x0059 BREAK  A[LOOP:0: B:13:0x0040->B:43:?]] */
    final boolean zza() {
        boolean z;
        if (this.zzc.zzb("gcm.n.noui")) {
            return true;
        }
        if (!((KeyguardManager) this.zzb.getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {
            if (!PlatformVersion.isAtLeastLollipop()) {
                SystemClock.sleep(10L);
            }
            int iMyPid = Process.myPid();
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) this.zzb.getSystemService("activity")).getRunningAppProcesses();
            if (runningAppProcesses == null) {
                z = false;
                break;
            }
            Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
            while (true) {
                if (it.hasNext()) {
                    ActivityManager.RunningAppProcessInfo next = it.next();
                    if (next.pid == iMyPid) {
                        if (next.importance == 100) {
                            z = true;
                            break;
                        }
                    }
                }
                z = false;
                break;
            }
        }
        z = false;
        break;
        if (z) {
            return false;
        }
        zzo zzoVarZza = zzo.zza(this.zzc.zza("gcm.n.image"));
        if (zzoVarZza != null) {
            zzoVarZza.zza(this.zza);
        }
        zza zzaVarZza = zzb.zza(this.zzb, this.zzc);
        NotificationCompat.Builder builder = zzaVarZza.zza;
        if (zzoVarZza != null) {
            try {
                Bitmap bitmap = (Bitmap) Tasks.await(zzoVarZza.zza(), 5L, TimeUnit.SECONDS);
                builder.setLargeIcon(bitmap);
                builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(null));
            } catch (InterruptedException unused) {
                Log.w("FirebaseMessaging", "Interrupted while downloading image, showing notification without it");
                zzoVarZza.close();
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                String strValueOf = String.valueOf(e.getCause());
                StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 26);
                sb.append("Failed to download image: ");
                sb.append(strValueOf);
                Log.w("FirebaseMessaging", sb.toString());
            } catch (TimeoutException unused2) {
                Log.w("FirebaseMessaging", "Failed to download image in time, showing notification without it");
                zzoVarZza.close();
            }
        }
        if (Log.isLoggable("FirebaseMessaging", 3)) {
            Log.d("FirebaseMessaging", "Showing notification");
        }
        ((NotificationManager) this.zzb.getSystemService("notification")).notify(zzaVarZza.zzb, 0, zzaVarZza.zza.build());
        return true;
    }
}
