package net.oregan.sparq.dtvsupportlibrary;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import net.oregan.sparq.dtvsupportlibraryaidl.ICleanUpCompleteListener;
import net.oregan.sparq.dtvsupportlibraryaidl.ITV;

/* JADX INFO: loaded from: classes3.dex */
public class TV {
    private static TV ourInstance;
    private String TAG = "TV";
    private boolean connecting = false;
    private ITV iTVService;
    private final Context mContext;
    private RemoteServiceConnection serviceConnection;

    public static TV getInstance(Context context) {
        if (ourInstance == null) {
            synchronized (TV.class) {
                if (ourInstance == null) {
                    ourInstance = new TV(context);
                }
            }
        }
        return ourInstance;
    }

    private TV(Context context) {
        this.mContext = context.getApplicationContext();
        connectService();
    }

    public String getVersion() {
        String version;
        Log.d(this.TAG, "getVersion() called");
        if (validateTVService()) {
            try {
                version = this.iTVService.getVersion();
            } catch (Exception e) {
                Log.e(this.TAG, "Failed to call service " + e);
                version = "";
            }
        } else {
            version = "";
        }
        Log.d(this.TAG, "TV getVersion()->" + version);
        return version;
    }

    public int cleanUpStaleChannels(ICleanUpCompleteListener iCleanUpCompleteListener) {
        int iCleanUpStaleChannels;
        Log.d(this.TAG, "cleanUpStaleChannels() called");
        if (validateTVService()) {
            try {
                iCleanUpStaleChannels = this.iTVService.cleanUpStaleChannels(iCleanUpCompleteListener);
            } catch (Exception e) {
                Log.e(this.TAG, "Failed to call service " + e);
                iCleanUpStaleChannels = -1;
            }
        } else {
            iCleanUpStaleChannels = -1;
        }
        Log.d(this.TAG, "TV cleanUpStaleChannels() returned " + iCleanUpStaleChannels);
        return iCleanUpStaleChannels;
    }

    private void connectService() {
        Log.d(this.TAG, "connectService called ");
        this.serviceConnection = new RemoteServiceConnection();
        try {
            Intent intent = new Intent("net.oregan.sparq.dtvsupportlibraryservice.DTVService.ITV");
            intent.setPackage("net.oregan.sparq.dtvsupportlibraryservice");
            this.connecting = this.mContext.bindService(intent, this.serviceConnection, 1);
            Log.d(this.TAG, "bindService returned " + this.connecting);
        } catch (Exception e) {
            Log.d(this.TAG, "Caught exception");
            e.printStackTrace();
        }
    }

    private boolean validateTVService() {
        if (this.iTVService == null) {
            Log.d(this.TAG, "service is NULL!");
            if (this.connecting) {
                Log.d(this.TAG, "waiting for service to start");
                try {
                    Thread.sleep(1000L);
                } catch (Exception unused) {
                }
            }
        }
        return this.iTVService != null;
    }

    class RemoteServiceConnection implements ServiceConnection {
        RemoteServiceConnection() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            TV.this.iTVService = ITV.Stub.asInterface(iBinder);
            TV.this.connecting = false;
            Log.d(TV.this.TAG, "Service connected");
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            TV.this.iTVService = null;
            Log.d(TV.this.TAG, "Service disconnected");
        }
    }
}
