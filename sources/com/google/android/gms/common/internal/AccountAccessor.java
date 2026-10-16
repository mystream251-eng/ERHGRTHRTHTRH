package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Binder;
import android.os.RemoteException;
import android.util.Log;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
public class AccountAccessor extends IAccountAccessor.Stub {
    public static Account getAccountBinderSafe(IAccountAccessor iAccountAccessor) {
        Account accountZza;
        if (iAccountAccessor != null) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                try {
                    accountZza = iAccountAccessor.zza();
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                } catch (RemoteException unused) {
                    Log.w("AccountAccessor", "Remote account accessor probably died");
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                    accountZza = null;
                    return (Account) Preconditions.checkNotNull(accountZza);
                }
            } catch (Throwable th) {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
                throw th;
            }
        } else {
            accountZza = null;
        }
        return (Account) Preconditions.checkNotNull(accountZza);
    }

    @Override // com.google.android.gms.common.internal.IAccountAccessor
    public final Account zza() {
        throw new NoSuchMethodError();
    }

    public boolean equals(Object obj) {
        throw new NoSuchMethodError();
    }
}
