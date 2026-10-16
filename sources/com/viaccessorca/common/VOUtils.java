package com.viaccessorca.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.dotscreen.tv.common.search.LocalSearchProvider;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.UByte;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class VOUtils {
    public static final String HTTP_GET = "GET";
    public static final String HTTP_POST = "POST";
    public static final String HTTP_PUT = "PUT";
    public static final int SDK_INT;
    public static final int TIMEOUT_CONNECT = 30000;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected static final char[] f8a = "0123456789ABCDEF".toCharArray();

    public static class RequestResponse {
        public byte[] data;
        public Map<String, List<String>> headers;
        public int responseCode;
    }

    public enum a {
        UNKNOWN,
        ETHERNET,
        WIFI,
        CELLULAR
    }

    static {
        SDK_INT = (Build.VERSION.SDK_INT == 23 && Build.VERSION.CODENAME.charAt(0) == 'N') ? 24 : Build.VERSION.SDK_INT;
    }

    private static String a(Reader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            int i = reader.read();
            if (i == -1) {
                return sb.toString();
            }
            sb.append((char) i);
        }
    }

    public static final String bytesToAsciiString(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] <= 2 || bArr[i] >= 126) {
                stringBuffer.append(" ");
            } else {
                stringBuffer.append((char) bArr[i]);
            }
        }
        return stringBuffer.toString();
    }

    public static String bytesToHex(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & UByte.MAX_VALUE;
            int i3 = i * 2;
            char[] cArr2 = f8a;
            cArr[i3] = cArr2[i2 >>> 4];
            cArr[i3 + 1] = cArr2[i2 & 15];
        }
        return new String(cArr);
    }

    public static UUID bytesToUUID(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        if (bArr.length < 16) {
            byte[] bArr2 = (byte[]) bArr.clone();
            bArr = new byte[bArr.length + 1];
            System.arraycopy(bArr2, 0, bArr, 0, bArr2.length);
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        return new UUID(byteBufferWrap.getLong(), byteBufferWrap.getLong());
    }

    public static String convertTimeToText(int i) {
        String string;
        Integer num = 60;
        Integer numValueOf = Integer.valueOf(num.intValue() * 60);
        int i2 = i / 1000;
        int iIntValue = i2 / numValueOf.intValue();
        int iIntValue2 = i2 % numValueOf.intValue();
        int iIntValue3 = iIntValue2 / num.intValue();
        int iIntValue4 = iIntValue2 % num.intValue();
        StringBuilder sb = new StringBuilder();
        String str = LocalSearchProvider.NO_LIVE_CONTENTS;
        if (iIntValue > 0) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(iIntValue < 10 ? LocalSearchProvider.NO_LIVE_CONTENTS : "");
            sb2.append(String.valueOf(iIntValue));
            sb2.append(":");
            string = sb2.toString();
        } else {
            string = "";
        }
        sb.append(string);
        sb.append(iIntValue3 < 10 ? LocalSearchProvider.NO_LIVE_CONTENTS : "");
        sb.append(String.valueOf(iIntValue3));
        sb.append(":");
        if (iIntValue4 >= 10) {
            str = "";
        }
        sb.append(str);
        sb.append(String.valueOf(iIntValue4));
        return sb.toString();
    }

    public static Rect getMediaCodecSurfacePosition(int i, int i2, int i3, float f, int i4, int i5) {
        int i6;
        int i7;
        if (i2 <= 0 || i3 <= 0 || f <= 0.0f || i4 <= 0 || i5 <= 0) {
            return null;
        }
        Rect rect = new Rect();
        int i8 = (int) (i2 * f);
        if (i != 0) {
            if (i != 2) {
                rect.top = 0;
            } else if (i8 * i5 > i4 * i3) {
                rect.top = 0;
                rect.bottom = i5;
                i7 = (int) (((double) i4) - ((((double) i5) * ((double) i8)) / ((double) i3)));
                int i9 = i7 / 2;
                rect.right = i9;
                rect.left = i4 - i9;
            } else {
                i6 = (int) (((double) i5) - ((((double) i4) * ((double) i3)) / ((double) i8)));
                int i10 = i6 / 2;
                rect.top = i10;
                i5 -= i10;
            }
            rect.bottom = i5;
            rect.left = 0;
            rect.right = i4;
        } else {
            int i11 = i4 * i3;
            int i12 = i8 * i5;
            if (i11 > i12) {
                rect.top = 0;
                rect.bottom = i5;
                i7 = i4 - (i12 / i3);
                int i13 = i7 / 2;
                rect.right = i13;
                rect.left = i4 - i13;
            } else {
                i6 = i5 - (i11 / i8);
                int i14 = i6 / 2;
                rect.top = i14;
                i5 -= i14;
                rect.bottom = i5;
                rect.left = 0;
                rect.right = i4;
            }
        }
        return rect;
    }

    public static a getNetworkType(Context context) {
        a aVar;
        a aVar2 = a.UNKNOWN;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
            NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
            NetworkInfo networkInfo3 = connectivityManager.getNetworkInfo(9);
            if (networkInfo3 != null && networkInfo3.isAvailable() && networkInfo3.isConnected()) {
                aVar = a.ETHERNET;
            } else if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
                aVar = a.WIFI;
            } else {
                if (networkInfo2 == null || !networkInfo2.isAvailable() || !networkInfo2.isConnected()) {
                    return aVar2;
                }
                aVar = a.CELLULAR;
            }
            return aVar;
        } catch (Exception unused) {
            return aVar2;
        }
    }

    public static byte[] getSHA256Hash(String str) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException unused) {
            messageDigest = null;
        }
        messageDigest.reset();
        try {
            return messageDigest.digest(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException unused2) {
            return null;
        }
    }

    public static byte[] hexStringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    public static Bitmap readBitmapFromUrl(String str) throws IOException {
        InputStream inputStreamOpenStream = new URL(str).openStream();
        try {
            return BitmapFactory.decodeStream(inputStreamOpenStream);
        } catch (Exception unused) {
            return null;
        } finally {
            inputStreamOpenStream.close();
        }
    }

    public static JSONObject readJsonFromUrl(String str) throws JSONException, IOException {
        InputStream inputStreamOpenStream = new URL(str).openStream();
        try {
            return new JSONObject(a(new BufferedReader(new InputStreamReader(inputStreamOpenStream, Charset.forName("UTF-8")))));
        } catch (Exception unused) {
            return null;
        } finally {
            inputStreamOpenStream.close();
        }
    }

    public static RequestResponse sendHTTPRequest(String str, String str2, byte[] bArr, Map<String, String> map, boolean z) {
        try {
            return sendHTTPRequestWithException(str, str2, TIMEOUT_CONNECT, TIMEOUT_CONNECT, bArr, map, z);
        } catch (Exception unused) {
            return null;
        }
    }

    public static RequestResponse sendHTTPRequestWithException(String str, String str2, int i, int i2, byte[] bArr, Map<String, String> map, boolean z) throws Exception {
        RequestResponse requestResponse = new RequestResponse();
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
                try {
                    httpURLConnection2.setConnectTimeout(i);
                    httpURLConnection2.setReadTimeout(i2);
                    if (true == z) {
                        httpURLConnection2.setRequestProperty("Connection", ToolboxAnalyticsModuleKt.EVENT_CLOSE);
                    }
                    if (map != null) {
                        for (String str3 : map.keySet()) {
                            httpURLConnection2.setRequestProperty(str3, map.get(str3));
                        }
                    }
                    httpURLConnection2.setRequestMethod(str2);
                    if (HTTP_POST == str2 || HTTP_PUT == str2) {
                        httpURLConnection2.setDoOutput(true);
                        if (bArr != null) {
                            httpURLConnection2.setFixedLengthStreamingMode(bArr.length);
                            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection2.getOutputStream());
                            bufferedOutputStream.write(bArr);
                            bufferedOutputStream.flush();
                            bufferedOutputStream.close();
                        }
                    } else {
                        httpURLConnection2.setDoInput(true);
                    }
                    requestResponse.responseCode = httpURLConnection2.getResponseCode();
                    requestResponse.headers = httpURLConnection2.getHeaderFields();
                    if (requestResponse.responseCode >= 200 && requestResponse.responseCode < 300) {
                        requestResponse.data = toByteArray(httpURLConnection2.getInputStream());
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                } catch (IOException e) {
                    e = e;
                    httpURLConnection = httpURLConnection2;
                    if (e.getMessage() != null && (e.getMessage().contains("Write error:") || e.getMessage().contains("sendto failed"))) {
                        requestResponse.responseCode = 1;
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection2 = httpURLConnection;
                        httpURLConnection2.disconnect();
                    }
                } catch (Exception e2) {
                    throw e2;
                } catch (Throwable th) {
                    th = th;
                    httpURLConnection = httpURLConnection2;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e3) {
            e = e3;
        } catch (Exception e4) {
            throw e4;
        }
        return requestResponse;
    }

    public static byte[] toByteArray(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[4096];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, i);
        }
    }

    public static byte[] uuidToBytesArray(UUID uuid) {
        byte[] bArr = new byte[0];
        if (uuid == null) {
            return bArr;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[16]);
        byteBufferWrap.putLong(uuid.getMostSignificantBits());
        byteBufferWrap.putLong(uuid.getLeastSignificantBits());
        return byteBufferWrap.array();
    }
}
