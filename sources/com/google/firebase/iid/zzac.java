package com.google.firebase.iid;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: com.google.firebase:firebase-iid@@20.1.4 */
/* JADX INFO: loaded from: classes2.dex */
final class zzac implements ServiceConnection {
    int zza;
    final Messenger zzb;
    zzal zzc;
    final Queue<zzan<?>> zzd;
    final SparseArray<zzan<?>> zze;
    final /* synthetic */ zzab zzf;

    private zzac(zzab zzabVar) {
        this.zzf = zzabVar;
        this.zza = 0;
        this.zzb = new Messenger(new com.google.android.gms.internal.firebase_messaging.zze(Looper.getMainLooper(), new Handler.Callback(this) { // from class: com.google.firebase.iid.zzaf
            private final zzac zza;

            {
                this.zza = this;
            }

            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return this.zza.zza(message);
            }
        }));
        this.zzd = new ArrayDeque();
        this.zze = new SparseArray<>();
    }

    final synchronized boolean zza(zzan<?> zzanVar) {
        int i = this.zza;
        if (i == 0) {
            this.zzd.add(zzanVar);
            Preconditions.checkState(this.zza == 0);
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Starting bind to GmsCore");
            }
            this.zza = 1;
            Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
            intent.setPackage("com.google.android.gms");
            if (!ConnectionTracker.getInstance().bindService(this.zzf.zzb, intent, this, 1)) {
                zza(0, "Unable to bind to service");
            } else {
                this.zzf.zzc.schedule(new Runnable(this) { // from class: com.google.firebase.iid.zzae
                    private final zzac zza;

                    {
                        this.zza = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        this.zza.zzc();
                    }
                }, 30L, TimeUnit.SECONDS);
            }
            return true;
        }
        if (i == 1) {
            this.zzd.add(zzanVar);
            return true;
        }
        if (i == 2) {
            this.zzd.add(zzanVar);
            zza();
            return true;
        }
        if (i != 3 && i != 4) {
            int i2 = this.zza;
            StringBuilder sb = new StringBuilder(26);
            sb.append("Unknown state: ");
            sb.append(i2);
            throw new IllegalStateException(sb.toString());
        }
        return false;
    }

    final boolean zza(Message message) {
        int i = message.arg1;
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            StringBuilder sb = new StringBuilder(41);
            sb.append("Received response to request: ");
            sb.append(i);
            Log.d("MessengerIpcClient", sb.toString());
        }
        synchronized (this) {
            zzan<?> zzanVar = this.zze.get(i);
            if (zzanVar == null) {
                StringBuilder sb2 = new StringBuilder(50);
                sb2.append("Received response for unknown request: ");
                sb2.append(i);
                Log.w("MessengerIpcClient", sb2.toString());
                return true;
            }
            this.zze.remove(i);
            zzb();
            Bundle data = message.getData();
            if (data.getBoolean("unsupported", false)) {
                zzanVar.zza(new zzam(4, "Not supported by GmsCore"));
            } else {
                zzanVar.zza(data);
            }
            return true;
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, final IBinder iBinder) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service connected");
        }
        this.zzf.zzc.execute(new Runnable(this, iBinder) { // from class: com.google.firebase.iid.zzah
            private final zzac zza;
            private final IBinder zzb;

            {
                this.zza = this;
                this.zzb = iBinder;
            }

            @Override // java.lang.Runnable
            public final void run() {
                zzac zzacVar = this.zza;
                IBinder iBinder2 = this.zzb;
                synchronized (zzacVar) {
                    try {
                        if (iBinder2 == null) {
                            zzacVar.zza(0, "Null service connection");
                            return;
                        }
                        try {
                            zzacVar.zzc = new zzal(iBinder2);
                            zzacVar.zza = 2;
                            zzacVar.zza();
                        } catch (RemoteException e) {
                            zzacVar.zza(0, e.getMessage());
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        });
    }

    final void zza() {
        this.zzf.zzc.execute(new Runnable(this) { // from class: com.google.firebase.iid.zzag
            private final zzac zza;

            {
                this.zza = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                final zzan<?> zzanVarPoll;
                final zzac zzacVar = this.zza;
                while (true) {
                    synchronized (zzacVar) {
                        if (zzacVar.zza != 2) {
                            return;
                        }
                        if (zzacVar.zzd.isEmpty()) {
                            zzacVar.zzb();
                            return;
                        } else {
                            zzanVarPoll = zzacVar.zzd.poll();
                            zzacVar.zze.put(zzanVarPoll.zza, zzanVarPoll);
                            zzacVar.zzf.zzc.schedule(new Runnable(zzacVar, zzanVarPoll) { // from class: com.google.firebase.iid.zzai
                                private final zzac zza;
                                private final zzan zzb;

                                {
                                    this.zza = zzacVar;
                                    this.zzb = zzanVarPoll;
                                }

                                @Override // java.lang.Runnable
                                public final void run() {
                                    this.zza.zza(this.zzb.zza);
                                }
                            }, 30L, TimeUnit.SECONDS);
                        }
                    }
                    if (Log.isLoggable("MessengerIpcClient", 3)) {
                        String strValueOf = String.valueOf(zzanVarPoll);
                        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 8);
                        sb.append("Sending ");
                        sb.append(strValueOf);
                        Log.d("MessengerIpcClient", sb.toString());
                    }
                    Context context = zzacVar.zzf.zzb;
                    Messenger messenger = zzacVar.zzb;
                    Message messageObtain = Message.obtain();
                    messageObtain.what = zzanVarPoll.zzc;
                    messageObtain.arg1 = zzanVarPoll.zza;
                    messageObtain.replyTo = messenger;
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("oneWay", zzanVarPoll.zza());
                    bundle.putString("pkg", context.getPackageName());
                    bundle.putBundle("data", zzanVarPoll.zzd);
                    messageObtain.setData(bundle);
                    try {
                        zzacVar.zzc.zza(messageObtain);
                    } catch (RemoteException e) {
                        zzacVar.zza(2, e.getMessage());
                    }
                }
            }
        });
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service disconnected");
        }
        this.zzf.zzc.execute(new Runnable(this) { // from class: com.google.firebase.iid.zzaj
            private final zzac zza;

            {
                this.zza = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zza(2, "Service disconnected");
            }
        });
    }

    final synchronized void zza(int i, String str) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String strValueOf = String.valueOf(str);
            Log.d("MessengerIpcClient", strValueOf.length() != 0 ? "Disconnected: ".concat(strValueOf) : new String("Disconnected: "));
        }
        int i2 = this.zza;
        if (i2 == 0) {
            throw new IllegalStateException();
        }
        if (i2 != 1 && i2 != 2) {
            if (i2 == 3) {
                this.zza = 4;
                return;
            } else {
                if (i2 == 4) {
                    return;
                }
                int i3 = this.zza;
                StringBuilder sb = new StringBuilder(26);
                sb.append("Unknown state: ");
                sb.append(i3);
                throw new IllegalStateException(sb.toString());
            }
        }
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Unbinding service");
        }
        this.zza = 4;
        ConnectionTracker.getInstance().unbindService(this.zzf.zzb, this);
        zzam zzamVar = new zzam(i, str);
        Iterator<zzan<?>> it = this.zzd.iterator();
        while (it.hasNext()) {
            it.next().zza(zzamVar);
        }
        this.zzd.clear();
        for (int i4 = 0; i4 < this.zze.size(); i4++) {
            this.zze.valueAt(i4).zza(zzamVar);
        }
        this.zze.clear();
    }

    final synchronized void zzb() {
        if (this.zza == 2 && this.zzd.isEmpty() && this.zze.size() == 0) {
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Finished handling requests, unbinding");
            }
            this.zza = 3;
            ConnectionTracker.getInstance().unbindService(this.zzf.zzb, this);
        }
    }

    final synchronized void zzc() {
        if (this.zza == 1) {
            zza(1, "Timed out while binding");
        }
    }

    final synchronized void zza(int i) {
        zzan<?> zzanVar = this.zze.get(i);
        if (zzanVar != null) {
            StringBuilder sb = new StringBuilder(31);
            sb.append("Timing out request: ");
            sb.append(i);
            Log.w("MessengerIpcClient", sb.toString());
            this.zze.remove(i);
            zzanVar.zza(new zzam(3, "Timed out waiting for response"));
            zzb();
        }
    }
}
