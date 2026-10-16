package com.dotscreen.portal.utils;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import com.dotscreen.farkit.tools.DSLog;
import com.dotscreen.farkit.updater.UpdateManager;
import com.dotscreen.farkit.updater.UpdateManagerKt;
import com.dotscreen.farkit.updater.UpdaterInfo;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.dotscreen.tv.ui.dialog.PortalDialog;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ActionBroadcastReceiver.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\fB\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/utils/ActionBroadcastReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "RebootDialogActivity", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class ActionBroadcastReceiver extends BroadcastReceiver {
    private final String TAG = ActionBroadcastReceiver.class.getSimpleName();

    /* JADX INFO: compiled from: ActionBroadcastReceiver.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014¨\u0006\u0007"}, d2 = {"Lcom/dotscreen/portal/utils/ActionBroadcastReceiver$RebootDialogActivity;", "Landroid/app/Activity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class RebootDialogActivity extends Activity {
        private HashMap _$_findViewCache;

        public void _$_clearFindViewByIdCache() {
            HashMap map = this._$_findViewCache;
            if (map != null) {
                map.clear();
            }
        }

        public View _$_findCachedViewById(int i) {
            if (this._$_findViewCache == null) {
                this._$_findViewCache = new HashMap();
            }
            View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
            if (view != null) {
                return view;
            }
            View viewFindViewById = findViewById(i);
            this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
            return viewFindViewById;
        }

        @Override // android.app.Activity
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            new PortalDialog.Builder(this).setMessage(R.string.dialog_reboot_message).setPositiveButton(R.string.dialog_reboot_no, new DialogInterface.OnClickListener() { // from class: com.dotscreen.portal.utils.ActionBroadcastReceiver$RebootDialogActivity$onCreate$1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    DSLog.INSTANCE.a("Clicked on positive button");
                    this.this$0.finish();
                }
            }).setNegativeButton(R.string.dialog_reboot_yes, new DialogInterface.OnClickListener() { // from class: com.dotscreen.portal.utils.ActionBroadcastReceiver$RebootDialogActivity$onCreate$2
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    DSLog.INSTANCE.a("Clicked on negative button");
                    Object systemService = this.this$0.getSystemService("power");
                    if (systemService == null) {
                        throw new TypeCastException("null cannot be cast to non-null type android.os.PowerManager");
                    }
                    ((PowerManager) systemService).reboot(null);
                }
            }).create().show();
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action;
        long j;
        if (intent == null || (action = intent.getAction()) == null) {
            return;
        }
        switch (action.hashCode()) {
            case -1186284671:
                if (action.equals(UpdateManagerKt.ACTION_NOTIFY_CHECK_FAILED)) {
                    if (context != null) {
                        Context applicationContext = context.getApplicationContext();
                        if (applicationContext == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                        }
                        ((PortalApplication) applicationContext).getUpdaterInfo().setCheckError(true);
                        return;
                    }
                    return;
                }
                break;
            case -1149808436:
                if (action.equals(UpdateManagerKt.ACTION_NOTIFY_FIRST_CHECK)) {
                    if (context != null) {
                        Bundle extras = intent.getExtras();
                        j = extras != null ? extras.getLong(UpdateManagerKt.EXTRA_CHECK_DELAY) : 0L;
                        Calendar calendar = Calendar.getInstance();
                        Intrinsics.checkExpressionValueIsNotNull(calendar, "Calendar.getInstance()");
                        Date time = calendar.getTime();
                        Intrinsics.checkExpressionValueIsNotNull(time, "Calendar.getInstance().time");
                        Date date = new Date(time.getTime() + j);
                        Context applicationContext2 = context.getApplicationContext();
                        if (applicationContext2 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                        }
                        ((PortalApplication) applicationContext2).getUpdaterInfo().setFirstCheck(date);
                        return;
                    }
                    return;
                }
                break;
            case -1077282658:
                if (action.equals(ActionBroadcastReceiverKt.ACTION_REBOOT)) {
                    Log.d(this.TAG, "1. receive com.dotscreen.megacable.portal.REBOOT");
                    if (context != null) {
                        context.startActivity(new Intent(context, (Class<?>) RebootDialogActivity.class));
                        return;
                    }
                    return;
                }
                break;
            case -838733983:
                if (action.equals(UpdateManagerKt.ACTION_NOTIFY_CHECK_RESULT)) {
                    if (context != null) {
                        try {
                            Bundle extras2 = intent.getExtras();
                            ArrayList<UpdateManager.LiteUpdateItem> parcelableArrayList = extras2 != null ? extras2.getParcelableArrayList(UpdateManagerKt.EXTRA_CHECK_RESULT_DATA) : null;
                            Log.d(this.TAG, "from UpdaterInfo : " + parcelableArrayList);
                            if (parcelableArrayList != null) {
                                Context applicationContext3 = context.getApplicationContext();
                                if (applicationContext3 == null) {
                                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                                }
                                ((PortalApplication) applicationContext3).getUpdaterInfo().setUpdateItems(parcelableArrayList);
                                Unit unit = Unit.INSTANCE;
                                return;
                            }
                            return;
                        } catch (Exception unused) {
                            Integer.valueOf(Log.d(this.TAG, "ACTION_NOTIFY_CHECK_RESULT failed class from UpdaterManager is not available"));
                            return;
                        }
                    }
                    return;
                }
                break;
            case -335828712:
                if (action.equals(CommonUtils.ACTION_CLOSE_PORTAL_APP)) {
                    Log.d(this.TAG, "Received close app message");
                    Runtime.getRuntime().exit(0);
                    return;
                }
                break;
            case 212871145:
                if (action.equals(UpdateManagerKt.ACTION_NOTIFY_NEXT_CHECK)) {
                    if (context != null) {
                        Calendar calendar2 = Calendar.getInstance();
                        Intrinsics.checkExpressionValueIsNotNull(calendar2, "Calendar.getInstance()");
                        Date time2 = calendar2.getTime();
                        Context applicationContext4 = context.getApplicationContext();
                        if (applicationContext4 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                        }
                        ((PortalApplication) applicationContext4).getUpdaterInfo().setNextRegularCheck(time2);
                        return;
                    }
                    return;
                }
                break;
            case 1080621567:
                if (action.equals(UpdateManagerKt.ACTION_NOTIFY_UPDATE_CHECK)) {
                    if (context != null) {
                        Context applicationContext5 = context.getApplicationContext();
                        if (applicationContext5 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                        }
                        UpdaterInfo updaterInfo = ((PortalApplication) applicationContext5).getUpdaterInfo();
                        Calendar calendar3 = Calendar.getInstance();
                        Intrinsics.checkExpressionValueIsNotNull(calendar3, "Calendar.getInstance()");
                        updaterInfo.setLastCheck(calendar3.getTime());
                        return;
                    }
                    return;
                }
                break;
            case 2146339491:
                if (action.equals(UpdateManagerKt.ACTION_NOTIFY_PERIODIC_CHECK_VALUE)) {
                    if (context != null) {
                        Bundle extras3 = intent.getExtras();
                        j = extras3 != null ? extras3.getLong(UpdateManagerKt.EXTRA_CHECK_PERIOD) : 0L;
                        Log.d(this.TAG, "Updater => set check period to " + j + ' ');
                        Context applicationContext6 = context.getApplicationContext();
                        if (applicationContext6 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                        }
                        ((PortalApplication) applicationContext6).getUpdaterInfo().setPeriodicInterval(Long.valueOf(j));
                        return;
                    }
                    return;
                }
                break;
        }
        Log.d(this.TAG, "receive unknown action");
    }
}
