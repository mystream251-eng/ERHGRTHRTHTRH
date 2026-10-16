package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PowerManager;
import android.util.Log;
import com.google.firebase.FirebaseApp;
import java.io.IOException;

/* JADX INFO: compiled from: com.google.firebase:firebase-iid@@20.1.4 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbb implements Runnable {
    private final long zza;
    private final PowerManager.WakeLock zzb;
    private final FirebaseInstanceId zzc;

    zzbb(FirebaseInstanceId firebaseInstanceId, long j) {
        this.zzc = firebaseInstanceId;
        this.zza = j;
        PowerManager.WakeLock wakeLockNewWakeLock = ((PowerManager) zza().getSystemService("power")).newWakeLock(1, "fiid-sync");
        this.zzb = wakeLockNewWakeLock;
        wakeLockNewWakeLock.setReferenceCounted(false);
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (zzaw.zza().zza(zza())) {
            this.zzb.acquire();
        }
        try {
            this.zzc.zza(true);
            if (!this.zzc.zzf()) {
                this.zzc.zza(false);
            } else if (zzaw.zza().zzb(zza()) && !zzb()) {
                new zzba(this).zza();
            } else {
                if (zzc()) {
                    this.zzc.zza(false);
                } else {
                    this.zzc.zza(this.zza);
                }
            }
        } catch (IOException e) {
            String message = e.getMessage();
            StringBuilder sb = new StringBuilder(String.valueOf(message).length() + 93);
            sb.append("Topic sync or token retrieval failed on hard failure exceptions: ");
            sb.append(message);
            sb.append(". Won't retry the operation.");
            Log.e("FirebaseInstanceId", sb.toString());
            this.zzc.zza(false);
        } finally {
            if (zzaw.zza().zza(zza())) {
                this.zzb.release();
            }
        }
    }

    private final boolean zzc() throws IOException {
        zzay zzayVarZzb = this.zzc.zzb();
        boolean z = true;
        if (!this.zzc.zza(zzayVarZzb)) {
            return true;
        }
        try {
            String strZzc = this.zzc.zzc();
            if (strZzc == null) {
                Log.e("FirebaseInstanceId", "Token retrieval failed: null");
                return false;
            }
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                Log.d("FirebaseInstanceId", "Token successfully retrieved");
            }
            if ((zzayVarZzb == null || (zzayVarZzb != null && !strZzc.equals(zzayVarZzb.zza))) && FirebaseApp.DEFAULT_APP_NAME.equals(this.zzc.zza().getName())) {
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    String strValueOf = String.valueOf(this.zzc.zza().getName());
                    Log.d("FirebaseInstanceId", strValueOf.length() != 0 ? "Invoking onNewToken for app: ".concat(strValueOf) : new String("Invoking onNewToken for app: "));
                }
                Intent intent = new Intent("com.google.firebase.messaging.NEW_TOKEN");
                intent.putExtra("token", strZzc);
                Context contextZza = zza();
                Intent intent2 = new Intent(contextZza, (Class<?>) FirebaseInstanceIdReceiver.class);
                intent2.setAction("com.google.firebase.MESSAGING_EVENT");
                intent2.putExtra("wrapped_intent", intent);
                contextZza.sendBroadcast(intent2);
            }
            return true;
        } catch (IOException e) {
            String message = e.getMessage();
            if (!"SERVICE_NOT_AVAILABLE".equals(message) && !"INTERNAL_SERVER_ERROR".equals(message) && !"InternalServerError".equals(message)) {
                z = false;
            }
            if (z) {
                String message2 = e.getMessage();
                StringBuilder sb = new StringBuilder(String.valueOf(message2).length() + 52);
                sb.append("Token retrieval failed: ");
                sb.append(message2);
                sb.append(". Will retry token retrieval");
                Log.w("FirebaseInstanceId", sb.toString());
                return false;
            }
            if (e.getMessage() == null) {
                Log.w("FirebaseInstanceId", "Token retrieval failed without exception message. Will retry token retrieval");
                return false;
            }
            throw e;
        } catch (SecurityException unused) {
            Log.w("FirebaseInstanceId", "Token retrieval failed with SecurityException. Will retry token retrieval");
            return false;
        }
    }

    final Context zza() {
        return this.zzc.zza().getApplicationContext();
    }

    final boolean zzb() {
        ConnectivityManager connectivityManager = (ConnectivityManager) zza().getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
