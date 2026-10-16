package net.oregan.sparq.dtvsupportlibrary;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import net.oregan.sparq.dtvsupportlibraryaidl.IIPTV;

/* JADX INFO: loaded from: classes3.dex */
public class IPTV {
    private static IPTV ourInstance;
    private String TAG = "IIPTV";
    private boolean connecting = false;
    private IIPTV iIPTVService;
    private final Context mContext;
    private RemoteServiceConnection serviceConnection;

    public static IPTV getInstance(Context context) {
        if (ourInstance == null) {
            synchronized (IPTV.class) {
                if (ourInstance == null) {
                    ourInstance = new IPTV(context);
                }
            }
        }
        return ourInstance;
    }

    private IPTV(Context context) {
        this.mContext = context.getApplicationContext();
        Log.d(this.TAG, "Connecting to service!");
        connectService();
    }

    private void connectService() {
        this.serviceConnection = new RemoteServiceConnection();
        Intent intent = new Intent("net.oregan.sparq.dtvsupportlibraryservice.DTVService.IIPTV");
        intent.setPackage("net.oregan.sparq.dtvsupportlibraryservice");
        this.connecting = this.mContext.bindService(intent, this.serviceConnection, 1);
        Log.d(this.TAG, "bindService returned " + this.connecting);
    }

    private boolean validateService() {
        if (this.iIPTVService == null) {
            Log.d(this.TAG, "service is NULL!");
            if (this.connecting) {
                Log.d(this.TAG, "waiting for service to start");
                try {
                    Thread.sleep(1000L);
                } catch (Exception unused) {
                }
            }
        }
        return this.iIPTVService != null;
    }

    class RemoteServiceConnection implements ServiceConnection {
        RemoteServiceConnection() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IPTV.this.iIPTVService = IIPTV.Stub.asInterface(iBinder);
            IPTV.this.connecting = false;
            Log.d(IPTV.this.TAG, "Service connected");
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            IPTV.this.iIPTVService = null;
            Log.d(IPTV.this.TAG, "Service disconnected");
        }
    }

    public String getIptvTvInputId() {
        String iptvTvInputId;
        Log.d(this.TAG, "getIptvTvInputId() called");
        if (validateService()) {
            try {
                iptvTvInputId = this.iIPTVService.getIptvTvInputId();
            } catch (Exception unused) {
                iptvTvInputId = "";
            }
        } else {
            iptvTvInputId = "";
        }
        Log.d(this.TAG, "getIptvTvInputId()->" + iptvTvInputId);
        return iptvTvInputId;
    }
}
