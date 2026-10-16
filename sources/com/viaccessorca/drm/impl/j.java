package com.viaccessorca.drm.impl;

import android.content.Context;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class j {
    protected static int a(String str) {
        int length = str.length();
        int iCharAt = 0;
        for (int i = 0; i < length; i++) {
            iCharAt = (iCharAt << 8) | str.charAt(i);
        }
        return iCharAt;
    }

    public static JSONObject a(Context context, String str) {
        if (context == null) {
            return null;
        }
        File filesDir = context.getFilesDir();
        try {
            JSONObject jSONObject = new JSONObject("{}");
            if (filesDir == null) {
                return jSONObject;
            }
            StringBuilder sb = new StringBuilder();
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filesDir.getAbsolutePath() + "/" + str)));
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    sb.append(line);
                }
                JSONObject jSONObject2 = new JSONObject(sb.toString());
                try {
                    bufferedReader.close();
                } catch (FileNotFoundException | IOException | JSONException unused) {
                }
                return jSONObject2;
            } catch (FileNotFoundException | IOException | JSONException unused2) {
                return jSONObject;
            }
        } catch (JSONException unused3) {
            return null;
        }
    }

    protected static byte[] a(UUID uuid, UUID[] uuidArr, byte[] bArr) {
        boolean z = uuidArr != null;
        int length = bArr != null ? bArr.length : 0;
        int length2 = length + 32;
        if (z) {
            length2 += (uuidArr.length * 16) + 4;
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(length2);
        byteBufferAllocate.putInt(length2);
        byteBufferAllocate.putInt(a("pssh"));
        byteBufferAllocate.putInt(z ? 16777216 : 0);
        byteBufferAllocate.putLong(uuid.getMostSignificantBits());
        byteBufferAllocate.putLong(uuid.getLeastSignificantBits());
        if (z) {
            byteBufferAllocate.putInt(uuidArr.length);
            for (UUID uuid2 : uuidArr) {
                byteBufferAllocate.putLong(uuid2.getMostSignificantBits());
                byteBufferAllocate.putLong(uuid2.getLeastSignificantBits());
            }
        }
        if (length != 0) {
            byteBufferAllocate.putInt(bArr.length);
            byteBufferAllocate.put(bArr);
        }
        return byteBufferAllocate.array();
    }
}
