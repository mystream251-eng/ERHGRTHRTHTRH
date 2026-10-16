package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.util.zzp;
import java.io.DataInputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzacv extends zzbgl {
    public static final Parcelable.Creator<zzacv> CREATOR = new zzacx();
    private ParcelFileDescriptor zza;
    private Parcelable zzb;
    private boolean zzc;

    public zzacv(ParcelFileDescriptor parcelFileDescriptor) {
        this.zza = parcelFileDescriptor;
        this.zzb = null;
        this.zzc = true;
    }

    public zzacv(zzbgp zzbgpVar) {
        this.zza = null;
        this.zzb = zzbgpVar;
        this.zzc = false;
    }

    private final ParcelFileDescriptor zza() {
        if (this.zza == null) {
            Parcel parcelObtain = Parcel.obtain();
            try {
                this.zzb.writeToParcel(parcelObtain, 0);
                byte[] bArrMarshall = parcelObtain.marshall();
                parcelObtain.recycle();
                this.zza = zza(bArrMarshall);
            } catch (Throwable th) {
                parcelObtain.recycle();
                throw th;
            }
        }
        return this.zza;
    }

    private final <T> ParcelFileDescriptor zza(byte[] bArr) {
        ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream;
        try {
            ParcelFileDescriptor[] parcelFileDescriptorArrCreatePipe = ParcelFileDescriptor.createPipe();
            autoCloseOutputStream = new ParcelFileDescriptor.AutoCloseOutputStream(parcelFileDescriptorArrCreatePipe[1]);
            try {
                new Thread(new zzacw(this, autoCloseOutputStream, bArr)).start();
                return parcelFileDescriptorArrCreatePipe[0];
            } catch (IOException e) {
                e = e;
                zzahw.zzb("Error transporting the ad response", e);
                zzbt.zzi().zza(e, "LargeParcelTeleporter.pipeData.2");
                zzp.zza(autoCloseOutputStream);
                return null;
            }
        } catch (IOException e2) {
            e = e2;
            autoCloseOutputStream = null;
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        zza();
        int iZza = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 2, this.zza, i, false);
        zzbgo.zza(parcel, iZza);
    }

    public final <T extends zzbgp> T zza(Parcelable.Creator<T> creator) {
        if (this.zzc) {
            if (this.zza == null) {
                zzahw.zzc("File descriptor is empty, returning null.");
                return null;
            }
            DataInputStream dataInputStream = new DataInputStream(new ParcelFileDescriptor.AutoCloseInputStream(this.zza));
            try {
                try {
                    int i = dataInputStream.readInt();
                    byte[] bArr = new byte[i];
                    dataInputStream.readFully(bArr, 0, i);
                    zzp.zza(dataInputStream);
                    Parcel parcelObtain = Parcel.obtain();
                    try {
                        parcelObtain.unmarshall(bArr, 0, i);
                        parcelObtain.setDataPosition(0);
                        this.zzb = creator.createFromParcel(parcelObtain);
                        parcelObtain.recycle();
                        this.zzc = false;
                    } catch (Throwable th) {
                        parcelObtain.recycle();
                        throw th;
                    }
                } catch (IOException e) {
                    zzahw.zzb("Could not read from parcel file descriptor", e);
                    zzp.zza(dataInputStream);
                    return null;
                }
            } catch (Throwable th2) {
                zzp.zza(dataInputStream);
                throw th2;
            }
        }
        return this.zzb;
    }
}
