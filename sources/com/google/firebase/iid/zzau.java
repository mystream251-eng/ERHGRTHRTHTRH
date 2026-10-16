package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import androidx.work.WorkRequest;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import io.sentry.protocol.App;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: com.google.firebase:firebase-iid@@20.1.4 */
/* JADX INFO: loaded from: classes2.dex */
final class zzau {
    private static int zza;
    private static PendingIntent zzb;
    private final Context zzd;
    private final zzao zze;
    private Messenger zzg;
    private zzj zzh;
    private final SimpleArrayMap<String, TaskCompletionSource<Bundle>> zzc = new SimpleArrayMap<>();
    private Messenger zzf = new Messenger(new zzax(this, Looper.getMainLooper()));

    public zzau(Context context, zzao zzaoVar) {
        this.zzd = context;
        this.zze = zzaoVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(Message message) {
        if (message != null && (message.obj instanceof Intent)) {
            Intent intent = (Intent) message.obj;
            intent.setExtrasClassLoader(new zzj.zza());
            if (intent.hasExtra("google.messenger")) {
                Parcelable parcelableExtra = intent.getParcelableExtra("google.messenger");
                if (parcelableExtra instanceof zzj) {
                    this.zzh = (zzj) parcelableExtra;
                }
                if (parcelableExtra instanceof Messenger) {
                    this.zzg = (Messenger) parcelableExtra;
                }
            }
            Intent intent2 = (Intent) message.obj;
            String action = intent2.getAction();
            if (!"com.google.android.c2dm.intent.REGISTRATION".equals(action)) {
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    String strValueOf = String.valueOf(action);
                    Log.d("FirebaseInstanceId", strValueOf.length() != 0 ? "Unexpected response action: ".concat(strValueOf) : new String("Unexpected response action: "));
                    return;
                }
                return;
            }
            String stringExtra = intent2.getStringExtra("registration_id");
            if (stringExtra == null) {
                stringExtra = intent2.getStringExtra("unregistered");
            }
            if (stringExtra == null) {
                String stringExtra2 = intent2.getStringExtra("error");
                if (stringExtra2 == null) {
                    String strValueOf2 = String.valueOf(intent2.getExtras());
                    StringBuilder sb = new StringBuilder(String.valueOf(strValueOf2).length() + 49);
                    sb.append("Unexpected response, no error or registration id ");
                    sb.append(strValueOf2);
                    Log.w("FirebaseInstanceId", sb.toString());
                    return;
                }
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    String strValueOf3 = String.valueOf(stringExtra2);
                    Log.d("FirebaseInstanceId", strValueOf3.length() != 0 ? "Received InstanceID error ".concat(strValueOf3) : new String("Received InstanceID error "));
                }
                if (stringExtra2.startsWith("|")) {
                    String[] strArrSplit = stringExtra2.split("\\|");
                    if (strArrSplit.length <= 2 || !"ID".equals(strArrSplit[1])) {
                        String strValueOf4 = String.valueOf(stringExtra2);
                        Log.w("FirebaseInstanceId", strValueOf4.length() != 0 ? "Unexpected structured response ".concat(strValueOf4) : new String("Unexpected structured response "));
                        return;
                    }
                    String str = strArrSplit[2];
                    String strSubstring = strArrSplit[3];
                    if (strSubstring.startsWith(":")) {
                        strSubstring = strSubstring.substring(1);
                    }
                    zza(str, intent2.putExtra("error", strSubstring).getExtras());
                    return;
                }
                synchronized (this.zzc) {
                    for (int i = 0; i < this.zzc.size(); i++) {
                        zza(this.zzc.keyAt(i), intent2.getExtras());
                    }
                }
                return;
            }
            Matcher matcher = Pattern.compile("\\|ID\\|([^|]+)\\|:?+(.*)").matcher(stringExtra);
            if (!matcher.matches()) {
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    String strValueOf5 = String.valueOf(stringExtra);
                    Log.d("FirebaseInstanceId", strValueOf5.length() != 0 ? "Unexpected response string: ".concat(strValueOf5) : new String("Unexpected response string: "));
                    return;
                }
                return;
            }
            String strGroup = matcher.group(1);
            String strGroup2 = matcher.group(2);
            Bundle extras = intent2.getExtras();
            extras.putString("registration_id", strGroup2);
            zza(strGroup, extras);
            return;
        }
        Log.w("FirebaseInstanceId", "Dropping invalid message");
    }

    private static synchronized void zza(Context context, Intent intent) {
        if (zzb == null) {
            Intent intent2 = new Intent();
            intent2.setPackage("com.google.example.invalidpackage");
            zzb = PendingIntent.getBroadcast(context, 0, intent2, 0);
        }
        intent.putExtra(App.TYPE, zzb);
    }

    private final void zza(String str, Bundle bundle) {
        synchronized (this.zzc) {
            TaskCompletionSource<Bundle> taskCompletionSourceRemove = this.zzc.remove(str);
            if (taskCompletionSourceRemove == null) {
                String strValueOf = String.valueOf(str);
                Log.w("FirebaseInstanceId", strValueOf.length() != 0 ? "Missing callback for ".concat(strValueOf) : new String("Missing callback for "));
            } else {
                taskCompletionSourceRemove.setResult(bundle);
            }
        }
    }

    final Bundle zza(Bundle bundle) throws IOException {
        if (this.zze.zze() >= 12000000) {
            try {
                return (Bundle) Tasks.await(zzab.zza(this.zzd).zzb(1, bundle));
            } catch (InterruptedException | ExecutionException e) {
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    String strValueOf = String.valueOf(e);
                    StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 22);
                    sb.append("Error making request: ");
                    sb.append(strValueOf);
                    Log.d("FirebaseInstanceId", sb.toString());
                }
                if ((e.getCause() instanceof zzam) && ((zzam) e.getCause()).zza() == 4) {
                    return zzb(bundle);
                }
                return null;
            }
        }
        return zzb(bundle);
    }

    private final Bundle zzb(Bundle bundle) throws IOException {
        Bundle bundleZzc = zzc(bundle);
        if (bundleZzc == null || !bundleZzc.containsKey("google.messenger")) {
            return bundleZzc;
        }
        Bundle bundleZzc2 = zzc(bundle);
        if (bundleZzc2 == null || !bundleZzc2.containsKey("google.messenger")) {
            return bundleZzc2;
        }
        return null;
    }

    private static synchronized String zza() {
        int i;
        i = zza;
        zza = i + 1;
        return Integer.toString(i);
    }

    /* JADX WARN: Code duplicated, block: B:30:0x00d2  */
    /* JADX WARN: Code duplicated, block: B:31:0x00d8  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.google.android.gms.tasks.TaskCompletionSource, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v14, types: [java.util.concurrent.TimeUnit] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x00d2 -> B:65:0x00dd). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x00d8 -> B:65:0x00dd). Please report as a decompilation issue!!! */
    private final Bundle zzc(Bundle bundle) throws IOException {
        TaskCompletionSource taskCompletionSource;
        ?? r1;
        String strZza = zza();
        ?? taskCompletionSource2 = new TaskCompletionSource();
        synchronized (this.zzc) {
            this.zzc.put(strZza, taskCompletionSource2);
        }
        if (this.zze.zzb() == 0) {
            throw new IOException("MISSING_INSTANCEID_SERVICE");
        }
        Intent intent = new Intent();
        intent.setPackage("com.google.android.gms");
        if (this.zze.zzb() == 2) {
            intent.setAction("com.google.iid.TOKEN_REQUEST");
        } else {
            intent.setAction("com.google.android.c2dm.intent.REGISTER");
        }
        intent.putExtras(bundle);
        zza(this.zzd, intent);
        StringBuilder sb = new StringBuilder(String.valueOf(strZza).length() + 5);
        sb.append("|ID|");
        sb.append(strZza);
        sb.append("|");
        intent.putExtra("kid", sb.toString());
        int i = 3;
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String strValueOf = String.valueOf(intent.getExtras());
            StringBuilder sb2 = new StringBuilder(String.valueOf(strValueOf).length() + 8);
            sb2.append("Sending ");
            sb2.append(strValueOf);
            Log.d("FirebaseInstanceId", sb2.toString());
        }
        intent.putExtra("google.messenger", this.zzf);
        if (this.zzg != null || this.zzh != null) {
            r1 = taskCompletionSource2;
            Message messageObtain = Message.obtain();
            messageObtain.obj = intent;
            try {
                if (this.zzg != null) {
                    this.zzg.send(messageObtain);
                    taskCompletionSource = taskCompletionSource2;
                } else {
                    this.zzh.zza(messageObtain);
                    taskCompletionSource = taskCompletionSource2;
                }
            } catch (RemoteException unused) {
                r1 = taskCompletionSource2;
                if (Log.isLoggable("FirebaseInstanceId", i)) {
                    Log.d("FirebaseInstanceId", "Messenger failed, fallback to startService");
                    r1 = taskCompletionSource2;
                }
                r1 = taskCompletionSource2;
                if (this.zze.zzb() == 2) {
                    this.zzd.sendBroadcast(intent);
                    taskCompletionSource = r1;
                } else {
                    this.zzd.startService(intent);
                    taskCompletionSource = r1;
                }
            }
        } else {
            r1 = taskCompletionSource2;
            if (this.zze.zzb() == 2) {
                this.zzd.sendBroadcast(intent);
                taskCompletionSource = r1;
            } else {
                this.zzd.startService(intent);
                taskCompletionSource = r1;
            }
        }
        try {
            try {
                Task task = taskCompletionSource.getTask();
                taskCompletionSource2 = 30000;
                i = TimeUnit.MILLISECONDS;
                Bundle bundle2 = (Bundle) Tasks.await(task, WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS, i);
                synchronized (this.zzc) {
                    this.zzc.remove(strZza);
                }
                return bundle2;
            } catch (Throwable th) {
                synchronized (this.zzc) {
                    this.zzc.remove(strZza);
                    throw th;
                }
            }
        } catch (InterruptedException | TimeoutException unused2) {
            Log.w("FirebaseInstanceId", "No response");
            throw new IOException("TIMEOUT");
        } catch (ExecutionException e) {
            throw new IOException(e);
        }
    }
}
