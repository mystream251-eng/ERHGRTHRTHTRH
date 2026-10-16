package net.oregan.sparq.dtvsupportlibrary;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import net.oregan.sparq.dtvsupportlibraryaidl.DVBTunerMetrics;
import net.oregan.sparq.dtvsupportlibraryaidl.IDVB;
import net.oregan.sparq.dtvsupportlibraryaidl.IDVBNetworkIdChangeListener;
import net.oregan.sparq.dtvsupportlibraryaidl.IDVBScanProgressListener;
import net.oregan.sparq.dtvsupportlibraryaidl.IDVBTunerLockChangedListener;

/* JADX INFO: loaded from: classes3.dex */
public class DVB {
    private static DVB ourInstance;
    private IDVB iDVBService;
    private final Context mContext;
    private RemoteServiceConnection serviceConnection;
    private String TAG = "DVB";
    private boolean connecting = false;
    IDVBScanProgressListener mDvbScanProgressListener = null;
    IDVBNetworkIdChangeListener mDVBNetworkIdChangeListener = null;
    IDVBTunerLockChangedListener mDVBTunerLockChangedListener = null;
    private IDVBNetworkIdChangeListener mDVBNetworkIdChangeListenerWrapper = new IDVBNetworkIdChangeListener.Stub() { // from class: net.oregan.sparq.dtvsupportlibrary.DVB.1
        @Override // net.oregan.sparq.dtvsupportlibraryaidl.IDVBNetworkIdChangeListener
        public void onNetworkIdChange(int i, int i2) {
            Log.d(DVB.this.TAG, "mDVBNetworkIdChangeListenerWrapper:onNetworkIdChange received - expectedNetworkId=" + i + " foundNetworkId=" + i2);
            if (DVB.this.mDVBNetworkIdChangeListener != null) {
                try {
                    DVB.this.mDVBNetworkIdChangeListener.onNetworkIdChange(i, i2);
                } catch (Exception unused) {
                }
            }
        }
    };
    private IDVBTunerLockChangedListener mDVBTunerLockChangedListenerWrapper = new IDVBTunerLockChangedListener.Stub() { // from class: net.oregan.sparq.dtvsupportlibrary.DVB.2
        @Override // net.oregan.sparq.dtvsupportlibraryaidl.IDVBTunerLockChangedListener
        public void onTunerLockChanged(boolean z) {
            Log.d(DVB.this.TAG, "mDVBTunerLockChangedListenerWrapper:onTunerLockChanged received - lock status: " + z);
            if (DVB.this.mDVBTunerLockChangedListener != null) {
                try {
                    DVB.this.mDVBTunerLockChangedListener.onTunerLockChanged(z);
                } catch (Exception unused) {
                }
            }
        }
    };

    public static DVB getInstance(Context context) {
        if (ourInstance == null) {
            synchronized (DVB.class) {
                if (ourInstance == null) {
                    ourInstance = new DVB(context);
                }
            }
        }
        return ourInstance;
    }

    private DVB(Context context) {
        this.mContext = context.getApplicationContext();
        Log.d(this.TAG, "Connecting to service!");
        connectService();
    }

    private void connectService() {
        this.serviceConnection = new RemoteServiceConnection();
        Intent intent = new Intent("net.oregan.sparq.dtvsupportlibraryservice.DTVService.IDVB");
        intent.setPackage("net.oregan.sparq.dtvsupportlibraryservice");
        this.connecting = this.mContext.bindService(intent, this.serviceConnection, 1);
        Log.d(this.TAG, "bindService returned " + this.connecting);
    }

    private boolean validateService() {
        if (this.iDVBService == null) {
            Log.d(this.TAG, "service is NULL!");
            if (this.connecting) {
                Log.d(this.TAG, "waiting for service to start");
                try {
                    Thread.sleep(1000L);
                } catch (Exception unused) {
                }
            }
        }
        return this.iDVBService != null;
    }

    class RemoteServiceConnection implements ServiceConnection {
        RemoteServiceConnection() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(DVB.this.TAG, "Service connected");
            DVB.this.iDVBService = IDVB.Stub.asInterface(iBinder);
            DVB.this.connecting = false;
            try {
                DVB.this.iDVBService.setNetworkIdChangeListener(DVB.this.mDVBNetworkIdChangeListenerWrapper);
                DVB.this.iDVBService.setTunerLockChangedListener(DVB.this.mDVBTunerLockChangedListenerWrapper);
            } catch (Exception e) {
                Log.e(DVB.this.TAG, "Exception when setting Listeners on iDVBService", e);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            DVB.this.iDVBService = null;
            Log.d(DVB.this.TAG, "Service disconnected");
        }
    }

    public boolean startScan(IDVBScanProgressListener iDVBScanProgressListener) {
        boolean zStartScan;
        Log.d(this.TAG, "startScan()");
        if (validateService()) {
            try {
                this.mDvbScanProgressListener = iDVBScanProgressListener;
                zStartScan = this.iDVBService.startScan(iDVBScanProgressListener);
            } catch (Exception unused) {
                zStartScan = false;
            }
        } else {
            zStartScan = false;
        }
        Log.d(this.TAG, "startScan()->" + zStartScan);
        return zStartScan;
    }

    public void stopScan() {
        Log.d(this.TAG, "stopScan()");
        if (validateService()) {
            try {
                this.iDVBService.stopScan();
            } catch (Exception unused) {
            }
        }
    }

    public int closeTunerAllIndex() {
        Log.d(this.TAG, "closeTunerAllIndex()");
        int iCloseTunerAllIndex = -1;
        if (!validateService()) {
            return -1;
        }
        try {
            iCloseTunerAllIndex = this.iDVBService.closeTunerAllIndex();
            Log.d(this.TAG, "closeTunerAllIndex() returned " + iCloseTunerAllIndex);
            return iCloseTunerAllIndex;
        } catch (Exception e) {
            Log.e(this.TAG, "closeTunerAllIndex() exception: ", e);
            return iCloseTunerAllIndex;
        }
    }

    public boolean tuneToHomeMux() {
        boolean zTuneToHomeMux;
        Log.d(this.TAG, "tuneToHomeMux() called");
        if (validateService()) {
            try {
                zTuneToHomeMux = this.iDVBService.tuneToHomeMux();
            } catch (Exception unused) {
                zTuneToHomeMux = false;
            }
        } else {
            zTuneToHomeMux = false;
        }
        Log.d(this.TAG, "tuneToHomeMux()->" + zTuneToHomeMux);
        return zTuneToHomeMux;
    }

    public int getTunerCount() {
        int tunerCount;
        Log.d(this.TAG, "getTunerCount() called");
        if (validateService()) {
            try {
                tunerCount = this.iDVBService.getTunerCount();
            } catch (Exception unused) {
                tunerCount = 0;
            }
        } else {
            tunerCount = 0;
        }
        Log.d(this.TAG, "getTunerCount()->" + tunerCount);
        return tunerCount;
    }

    public DVBTunerMetrics getTunerSignalMetrics(int i) {
        Log.d(this.TAG, "getTunerSignalMetrics(" + i + ")");
        if (validateService()) {
            try {
                return this.iDVBService.getTunerSignalMetrics(i);
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public void setNetworkIdChangeListener(IDVBNetworkIdChangeListener iDVBNetworkIdChangeListener) {
        Log.d(this.TAG, "setNetworkIdChangeListener()");
        this.mDVBNetworkIdChangeListener = iDVBNetworkIdChangeListener;
    }

    public void setTunerLockChangedListener(IDVBTunerLockChangedListener iDVBTunerLockChangedListener) {
        Log.d(this.TAG, "setTunerLockChangedListener()");
        this.mDVBTunerLockChangedListener = iDVBTunerLockChangedListener;
    }

    public int getNetworkID() {
        Log.d(this.TAG, "getNetworkID()");
        if (validateService()) {
            try {
                return this.iDVBService.getNetworkID();
            } catch (Exception unused) {
            }
        }
        return 0;
    }
}
