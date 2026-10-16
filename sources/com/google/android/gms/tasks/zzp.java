package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-tasks@@17.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzp<TResult, TContinuationResult> implements OnCanceledListener, OnFailureListener, OnSuccessListener<TContinuationResult>, zzr<TResult> {
    private final Executor zza;
    private final SuccessContinuation<TResult, TContinuationResult> zzb;
    private final zzu<TContinuationResult> zzc;

    public zzp(Executor executor, SuccessContinuation<TResult, TContinuationResult> successContinuation, zzu<TContinuationResult> zzuVar) {
        this.zza = executor;
        this.zzb = successContinuation;
        this.zzc = zzuVar;
    }

    @Override // com.google.android.gms.tasks.zzr
    public final void zza(Task<TResult> task) {
        this.zza.execute(new zzo(this, task));
    }

    @Override // com.google.android.gms.tasks.zzr
    public final void zza() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final void onSuccess(TContinuationResult tcontinuationresult) {
        this.zzc.zza(tcontinuationresult);
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception exc) {
        this.zzc.zza(exc);
    }

    @Override // com.google.android.gms.tasks.OnCanceledListener
    public final void onCanceled() {
        this.zzc.zza();
    }
}
