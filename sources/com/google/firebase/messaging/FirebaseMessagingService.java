package com.google.firebase.messaging;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.TransportFactory;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.iid.zzab;
import com.google.firebase.iid.zzaw;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: com.google.firebase:firebase-messaging@@20.1.4 */
/* JADX INFO: loaded from: classes2.dex */
public class FirebaseMessagingService extends zzf {
    private static final Queue<String> zza = new ArrayDeque(10);

    public void onDeletedMessages() {
    }

    public void onMessageReceived(RemoteMessage remoteMessage) {
    }

    public void onMessageSent(String str) {
    }

    public void onNewToken(String str) {
    }

    public void onSendError(String str, Exception exc) {
    }

    @Override // com.google.firebase.messaging.zzf
    protected final Intent zza(Intent intent) {
        return zzaw.zza().zzb();
    }

    @Override // com.google.firebase.messaging.zzf
    public final boolean zzb(Intent intent) {
        if (!"com.google.firebase.messaging.NOTIFICATION_OPEN".equals(intent.getAction())) {
            return false;
        }
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("pending_intent");
        if (pendingIntent != null) {
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException unused) {
                Log.e("FirebaseMessaging", "Notification pending intent canceled");
            }
        }
        if (!zzp.zzd(intent)) {
            return true;
        }
        zzp.zza(intent);
        return true;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.google.firebase.messaging.zzf
    public final void zzc(Intent intent) {
        Task<Void> taskZza;
        boolean z;
        String action = intent.getAction();
        if ("com.google.android.c2dm.intent.RECEIVE".equals(action) || "com.google.firebase.messaging.RECEIVE_DIRECT_BOOT".equals(action)) {
            String stringExtra = intent.getStringExtra("google.message_id");
            if (TextUtils.isEmpty(stringExtra)) {
                taskZza = Tasks.forResult(null);
            } else {
                Bundle bundle = new Bundle();
                bundle.putString("google.message_id", stringExtra);
                taskZza = zzab.zza(this).zza(2, bundle);
            }
            byte b = 0;
            if (TextUtils.isEmpty(stringExtra)) {
                z = false;
            } else if (zza.contains(stringExtra)) {
                if (Log.isLoggable("FirebaseMessaging", 3)) {
                    String strValueOf = String.valueOf(stringExtra);
                    Log.d("FirebaseMessaging", strValueOf.length() != 0 ? "Received duplicate message: ".concat(strValueOf) : new String("Received duplicate message: "));
                }
                z = true;
            } else {
                if (zza.size() >= 10) {
                    zza.remove();
                }
                zza.add(stringExtra);
                z = false;
            }
            if (!z) {
                String stringExtra2 = intent.getStringExtra("message_type");
                if (stringExtra2 == null) {
                    stringExtra2 = "gcm";
                }
                switch (stringExtra2.hashCode()) {
                    case -2062414158:
                        b = !stringExtra2.equals("deleted_messages") ? (byte) -1 : (byte) 1;
                        break;
                    case 102161:
                        if (!stringExtra2.equals("gcm")) {
                            b = -1;
                        }
                        break;
                    case 814694033:
                        b = !stringExtra2.equals("send_error") ? (byte) -1 : (byte) 3;
                        break;
                    case 814800675:
                        b = !stringExtra2.equals("send_event") ? (byte) -1 : (byte) 2;
                        break;
                    default:
                        b = -1;
                        break;
                }
                if (b == 0) {
                    if (zzp.zzd(intent)) {
                        zzp.zza(intent, (Transport<String>) null);
                    }
                    if (zzp.zze(intent)) {
                        TransportFactory transportFactory = FirebaseMessaging.zza;
                        if (transportFactory != null) {
                            zzp.zza(intent, (Transport<String>) transportFactory.getTransport("FCM_CLIENT_EVENT_LOGGING", String.class, Encoding.of("json"), zzn.zza));
                        } else {
                            Log.e("FirebaseMessaging", "TransportFactory is null. Skip exporting message delivery metrics to Big Query");
                        }
                    }
                    Bundle extras = intent.getExtras();
                    if (extras == null) {
                        extras = new Bundle();
                    }
                    extras.remove("androidx.contentpager.content.wakelockid");
                    if (zzr.zza(extras)) {
                        zzr zzrVar = new zzr(extras);
                        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor(new NamedThreadFactory("Firebase-Messaging-Network-Io"));
                        try {
                            if (new zzc(this, zzrVar, executorServiceNewSingleThreadExecutor).zza()) {
                                executorServiceNewSingleThreadExecutor.shutdown();
                            } else {
                                executorServiceNewSingleThreadExecutor.shutdown();
                                if (zzp.zzd(intent)) {
                                    zzp.zzc(intent);
                                }
                                onMessageReceived(new RemoteMessage(extras));
                            }
                        } catch (Throwable th) {
                            executorServiceNewSingleThreadExecutor.shutdown();
                            throw th;
                        }
                    } else {
                        onMessageReceived(new RemoteMessage(extras));
                    }
                } else if (b == 1) {
                    onDeletedMessages();
                } else if (b == 2) {
                    onMessageSent(intent.getStringExtra("google.message_id"));
                } else if (b == 3) {
                    String stringExtra3 = intent.getStringExtra("google.message_id");
                    if (stringExtra3 == null) {
                        stringExtra3 = intent.getStringExtra("message_id");
                    }
                    onSendError(stringExtra3, new SendException(intent.getStringExtra("error")));
                } else {
                    String strValueOf2 = String.valueOf(stringExtra2);
                    Log.w("FirebaseMessaging", strValueOf2.length() != 0 ? "Received message with unknown type: ".concat(strValueOf2) : new String("Received message with unknown type: "));
                }
            }
            try {
                Tasks.await(taskZza, TimeUnit.SECONDS.toMillis(1L), TimeUnit.MILLISECONDS);
                return;
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                String strValueOf3 = String.valueOf(e);
                StringBuilder sb = new StringBuilder(String.valueOf(strValueOf3).length() + 20);
                sb.append("Message ack failed: ");
                sb.append(strValueOf3);
                Log.w("FirebaseMessaging", sb.toString());
                return;
            }
        }
        if ("com.google.firebase.messaging.NOTIFICATION_DISMISS".equals(action)) {
            if (zzp.zzd(intent)) {
                zzp.zzb(intent);
            }
        } else if ("com.google.firebase.messaging.NEW_TOKEN".equals(action)) {
            onNewToken(intent.getStringExtra("token"));
        } else {
            String strValueOf4 = String.valueOf(intent.getAction());
            Log.d("FirebaseMessaging", strValueOf4.length() != 0 ? "Unknown intent action: ".concat(strValueOf4) : new String("Unknown intent action: "));
        }
    }
}
