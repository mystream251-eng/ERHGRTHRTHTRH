package com.dotscreen.tv.common.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.dotscreen.tv.common.IUserSuspendListener;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UserSuspendBroadcastReceiver.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0010"}, d2 = {"Lcom/dotscreen/tv/common/receiver/UserSuspendedBroadcastReceiver;", "Landroid/content/BroadcastReceiver;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/dotscreen/tv/common/IUserSuspendListener;", "(Lcom/dotscreen/tv/common/IUserSuspendListener;)V", "TAG", "", "kotlin.jvm.PlatformType", "getListener", "()Lcom/dotscreen/tv/common/IUserSuspendListener;", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class UserSuspendedBroadcastReceiver extends BroadcastReceiver {
    private final String TAG;
    private final IUserSuspendListener listener;

    public UserSuspendedBroadcastReceiver(IUserSuspendListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.listener = listener;
        this.TAG = UserSuspendedBroadcastReceiver.class.getSimpleName();
    }

    public final IUserSuspendListener getListener() {
        return this.listener;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Receiver ");
        sb.append(intent != null ? intent.getAction() : null);
        Log.d(str, sb.toString());
        if (intent == null || !Intrinsics.areEqual(CommonUtils.ACTION_USER_SUSPENDED, intent.getAction())) {
            return;
        }
        this.listener.onUserSuspend();
    }
}
