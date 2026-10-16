package com.google.android.gms.common.api.internal;

import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public final class zak {
    private int zadd;
    private final ArrayMap<zai<?>, String> zadb = new ArrayMap<>();
    private final TaskCompletionSource<Map<zai<?>, String>> zadc = new TaskCompletionSource<>();
    private boolean zade = false;
    private final ArrayMap<zai<?>, ConnectionResult> zaay = new ArrayMap<>();

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
    public zak(Iterable<? extends GoogleApi<?>> iterable) {
        Iterator<? extends GoogleApi<?>> it = iterable.iterator();
        while (it.hasNext()) {
            this.zaay.put(it.next().zak(), null);
        }
        this.zadd = this.zaay.keySet().size();
    }

    public final Set<zai<?>> zap() {
        return this.zaay.keySet();
    }

    public final Task<Map<zai<?>, String>> getTask() {
        return this.zadc.getTask();
    }

    public final void zaa(zai<?> zaiVar, ConnectionResult connectionResult, String str) {
        this.zaay.put(zaiVar, connectionResult);
        this.zadb.put(zaiVar, str);
        this.zadd--;
        if (!connectionResult.isSuccess()) {
            this.zade = true;
        }
        if (this.zadd == 0) {
            if (this.zade) {
                this.zadc.setException(new AvailabilityException(this.zaay));
            } else {
                this.zadc.setResult(this.zadb);
            }
        }
    }
}
