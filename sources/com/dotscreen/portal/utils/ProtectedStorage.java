package com.dotscreen.portal.utils;

import android.content.Context;
import android.util.Log;
import androidx.security.crypto.EncryptedFile;
import androidx.security.crypto.MasterKeys;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes.dex */
public class ProtectedStorage {
    private static final String TAG = "ProtectedStorage";
    private EncryptedFile encryptedFile;
    private File file;
    private String masterKeyAlias;

    public ProtectedStorage(Context context, String str) throws GeneralSecurityException, IOException {
        this.masterKeyAlias = null;
        this.file = null;
        this.encryptedFile = null;
        this.masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
        this.file = new File(context.getApplicationContext().getFilesDir(), str);
        this.encryptedFile = new EncryptedFile.Builder(this.file, context.getApplicationContext(), this.masterKeyAlias, EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB).build();
    }

    public boolean fileExists() {
        return this.file.exists();
    }

    public void storeData(byte[] bArr) throws GeneralSecurityException, IOException {
        this.file.delete();
        FileOutputStream fileOutputStreamOpenFileOutput = this.encryptedFile.openFileOutput();
        try {
            fileOutputStreamOpenFileOutput.write(bArr);
        } finally {
            fileOutputStreamOpenFileOutput.close();
        }
    }

    public byte[] retrieveData(int i) throws GeneralSecurityException, IOException {
        byte[] bArr = new byte[i];
        FileInputStream fileInputStreamOpenFileInput = this.encryptedFile.openFileInput();
        try {
            int i2 = fileInputStreamOpenFileInput.read(bArr);
            if (i2 != i) {
                Log.e(TAG, "Data retrieval failed. Data length " + i + ", Bytes read " + i2);
            }
            return bArr;
        } finally {
            fileInputStreamOpenFileInput.close();
        }
    }
}
