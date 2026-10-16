package com.dotscreen.megacable.portal;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.ui.screens.guide.GuideActivity;
import java.net.URISyntaxException;

/* JADX INFO: loaded from: classes.dex */
public class GlobalKeyReceiver extends BroadcastReceiver {
    private static final String ACTION_GLOBAL_BUTTON = "android.intent.action.GLOBAL_BUTTON";
    private static final boolean DEBUG = true;
    private static final String TAG = "GlobalKeyReceiver";

    /* JADX WARN: Code duplicated, block: B:22:0x009e  */
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        PortalApplication portalApplication = (PortalApplication) context.getApplicationContext();
        Activity activity = portalApplication.get_activeActivity();
        Log.d(TAG, "onReceive intent : " + intent + " Current Activity : " + portalApplication.get_activeActivity());
        if (ACTION_GLOBAL_BUTTON.equals(intent.getAction())) {
            KeyEvent keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
            Log.d(TAG, "onReceive: " + keyEvent);
            int keyCode = keyEvent.getKeyCode();
            if (keyEvent.getAction() == 1) {
                String str = null;
                if (keyCode == 133) {
                    str = "megacable://com.dotscreen.megacable.portal/vod";
                } else if (keyCode == 134) {
                    str = "megacable://com.dotscreen.megacable.portal/settings";
                } else if (keyCode != 172) {
                    if (keyCode != 198) {
                        portalApplication.showToastMessage("Inactive Key : " + KeyEvent.keyCodeToString(keyCode), 0);
                    } else {
                        str = "megacable://com.dotscreen.megacable.portal/vod";
                    }
                } else if (portalApplication.getPortalIsInForeground() && (activity instanceof GuideActivity)) {
                    activity.dispatchKeyEvent(keyEvent);
                } else {
                    str = "megacable://com.dotscreen.megacable.portal/epg/guide";
                }
                if (str != null) {
                    try {
                        context.startActivity(Intent.parseUri(str, 0).addFlags(872415232));
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
