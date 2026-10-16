package net.oregan.sparq.dtvsupportlibrary;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import net.oregan.sparq.dtvsupportlibraryaidl.ICAS;
import net.oregan.sparq.dtvsupportlibraryaidl.ICASErrorListener;

/* JADX INFO: loaded from: classes3.dex */
public class CAS {
    private static CAS ourInstance;
    private ICAS iCASService;
    private final Context mContext;
    private RemoteServiceConnection serviceConnection;
    private String TAG = "CAS";
    private boolean connecting = false;
    ICASErrorListener mCasErrorListener = null;
    private ICASErrorListener mCASErrorListenerListenerWrapper = new ICASErrorListener.Stub() { // from class: net.oregan.sparq.dtvsupportlibrary.CAS.1
        @Override // net.oregan.sparq.dtvsupportlibraryaidl.ICASErrorListener
        public void onCasError(int i, int i2, int i3) {
            Log.d(CAS.this.TAG, "mCASErrorListenerListenerWrapper:Got CAS error: serviceID:" + i + " errorCode:" + i2 + " internalErrorCode:" + i3);
            if (CAS.this.mCasErrorListener != null) {
                try {
                    CAS.this.mCasErrorListener.onCasError(i, i2, i3);
                } catch (Exception unused) {
                }
            }
        }
    };

    public static CAS getInstance(Context context) {
        if (ourInstance == null) {
            synchronized (CAS.class) {
                if (ourInstance == null) {
                    ourInstance = new CAS(context);
                }
            }
        }
        return ourInstance;
    }

    private CAS(Context context) {
        this.mContext = context.getApplicationContext();
        Log.d(this.TAG, "Connecting to service!");
        connectService();
    }

    private void connectService() {
        this.serviceConnection = new RemoteServiceConnection();
        Intent intent = new Intent("net.oregan.sparq.dtvsupportlibraryservice.DTVService.ICAS");
        intent.setPackage("net.oregan.sparq.dtvsupportlibraryservice");
        this.connecting = this.mContext.bindService(intent, this.serviceConnection, 1);
        Log.d(this.TAG, "bindService returned " + this.connecting);
    }

    private boolean validateService() {
        if (this.iCASService == null) {
            Log.d(this.TAG, "service is NULL!");
            if (this.connecting) {
                Log.d(this.TAG, "waiting for service to start");
                try {
                    Thread.sleep(1000L);
                } catch (Exception unused) {
                }
            }
        }
        return this.iCASService != null;
    }

    class RemoteServiceConnection implements ServiceConnection {
        RemoteServiceConnection() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(CAS.this.TAG, "Service connected");
            CAS.this.iCASService = ICAS.Stub.asInterface(iBinder);
            CAS.this.connecting = false;
            try {
                CAS.this.iCASService.setCasErrorListener(CAS.this.mCASErrorListenerListenerWrapper);
            } catch (Exception unused) {
                Log.e(CAS.this.TAG, "Exception when calling setCasErrorListener");
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            CAS.this.iCASService = null;
            Log.d(CAS.this.TAG, "Service disconnected");
        }
    }

    public void setCasErrorListener(ICASErrorListener iCASErrorListener) {
        Log.d(this.TAG, "setCasErrorListener()");
        this.mCasErrorListener = iCASErrorListener;
    }

    public boolean setVcasConfiguration(int i, String str, String str2) {
        boolean vcasConfiguration;
        Log.d(this.TAG, "setVcasConfiguration()");
        if (validateService()) {
            try {
                vcasConfiguration = this.iCASService.setVcasConfiguration(i, str, str2);
            } catch (Exception unused) {
                vcasConfiguration = false;
            }
        } else {
            vcasConfiguration = false;
        }
        Log.d(this.TAG, "setVcasConfiguration()->" + vcasConfiguration);
        return vcasConfiguration;
    }
}
