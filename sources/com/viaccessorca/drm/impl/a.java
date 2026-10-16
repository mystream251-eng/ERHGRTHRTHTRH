package com.viaccessorca.drm.impl;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
class a implements BluetoothProfile.ServiceListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private BluetoothA2dp f35a = null;

    a() {
    }

    public BluetoothA2dp a() {
        return this.f35a;
    }

    public boolean b() {
        BluetoothA2dp bluetoothA2dp = this.f35a;
        if (bluetoothA2dp == null) {
            return false;
        }
        Iterator<BluetoothDevice> it = bluetoothA2dp.getConnectedDevices().iterator();
        while (it.hasNext()) {
            if (this.f35a.isA2dpPlaying(it.next())) {
                return true;
            }
        }
        return false;
    }

    @Override // android.bluetooth.BluetoothProfile.ServiceListener
    public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
        if (i == 2) {
            this.f35a = (BluetoothA2dp) bluetoothProfile;
        }
    }

    @Override // android.bluetooth.BluetoothProfile.ServiceListener
    public void onServiceDisconnected(int i) {
        if (i == 2) {
            this.f35a = null;
        }
    }
}
