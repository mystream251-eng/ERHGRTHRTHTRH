package com.viaccessorca.voplayer;

import android.content.Context;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.squareup.moshi.MsgpackFormat;
import com.viaccessorca.common.VOUtils;
import com.viaccessorca.vodownloader.VODownloader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: classes2.dex */
class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static File f126a;
    private static File b;
    private static File c;
    private static byte[] d;
    private static final String[] e = {"faas_v1-codecexceptionbybrandandcolorformat.csv", "faas_v1-codecexceptionbymodelname.csv", "faas_v1-exceptionsfordevicemodels_vsc_sdk.csv"};

    static int a(Context context, VOSystemInfo vOSystemInfo, String str, String str2) {
        String str3;
        String str4;
        String str5;
        int i;
        String str6;
        File filesDir = context.getFilesDir();
        int i2 = vOSystemInfo.androidVersion;
        String strReplace = vOSystemInfo.deviceModel.replace(" ", "_");
        try {
            strReplace = URLEncoder.encode(strReplace, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
        }
        String str7 = strReplace;
        String strReplace2 = vOSystemInfo.deviceBrand.replace(" ", "_");
        String str8 = vOSystemInfo.codecName;
        int i3 = vOSystemInfo.codecColorFormat;
        String str9 = vOSystemInfo.osArch;
        int i4 = vOSystemInfo.codecTypeException;
        String str10 = vOSystemInfo.devicePlatform;
        File file = new File(filesDir.getAbsolutePath() + "/dbFaasOffline.zip");
        file.delete();
        File file2 = new File(filesDir.getAbsolutePath() + "/dbFaasOffline_orig.zip");
        file2.delete();
        File file3 = new File(filesDir.getAbsolutePath() + "/faas_v1-codecexceptionbybrandandcolorformat.csv");
        f126a = file3;
        file3.delete();
        File file4 = new File(filesDir.getAbsolutePath() + "/faas_v1-codecexceptionbymodelname.csv");
        b = file4;
        file4.delete();
        File file5 = new File(filesDir.getAbsolutePath() + "/faas_v1-exceptionsfordevicemodels_vsc_sdk.csv");
        c = file5;
        file5.delete();
        d = a();
        if (str2.startsWith("http")) {
            if (str2.toLowerCase().contains(".zip")) {
                try {
                    VOUtils.RequestResponse requestResponseSendHTTPRequest = VOUtils.sendHTTPRequest(str2, VOUtils.HTTP_GET, null, null, false);
                    if (requestResponseSendHTTPRequest == null) {
                        return -1;
                    }
                    if (requestResponseSendHTTPRequest.responseCode >= 200 && requestResponseSendHTTPRequest.responseCode < 300) {
                        FileOutputStream fileOutputStream = new FileOutputStream(file2);
                        fileOutputStream.write(requestResponseSendHTTPRequest.data);
                        fileOutputStream.close();
                    }
                    byte[] bArr = new byte[4];
                    FileInputStream fileInputStream = new FileInputStream(file2);
                    FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                    while (true) {
                        int i5 = fileInputStream.read(bArr);
                        if (i5 <= 0) {
                            break;
                        }
                        a(bArr, i5);
                        fileOutputStream2.write(bArr, 0, i5);
                        str8 = str8;
                    }
                    str4 = str8;
                    fileOutputStream2.close();
                    fileInputStream.close();
                } catch (IOException unused2) {
                    return -1;
                }
            } else {
                str4 = str8;
                String[] strArr = e;
                int length = strArr.length;
                int i6 = 0;
                while (i6 < length) {
                    String str11 = strArr[i6];
                    String[] strArr2 = strArr;
                    try {
                        StringBuilder sb = new StringBuilder();
                        int i7 = length;
                        sb.append(filesDir.getAbsolutePath());
                        sb.append("/");
                        sb.append(str11);
                        String string = sb.toString();
                        String str12 = strReplace2;
                        VOUtils.RequestResponse requestResponseSendHTTPRequest2 = VOUtils.sendHTTPRequest(str2 + "/" + str11, VOUtils.HTTP_GET, null, null, false);
                        if (requestResponseSendHTTPRequest2 == null) {
                            return -1;
                        }
                        if (requestResponseSendHTTPRequest2.data != null) {
                            FileOutputStream fileOutputStream3 = new FileOutputStream(new File(string));
                            fileOutputStream3.write(requestResponseSendHTTPRequest2.data);
                            fileOutputStream3.close();
                        }
                        i6++;
                        strArr = strArr2;
                        length = i7;
                        strReplace2 = str12;
                    } catch (IOException unused3) {
                        return -1;
                    }
                }
            }
            str3 = strReplace2;
        } else {
            str3 = strReplace2;
            str4 = str8;
            filesDir.getAbsolutePath();
            try {
                byte[] bArr2 = new byte[4];
                FileInputStream fileInputStream2 = new FileInputStream(new File(str2));
                FileOutputStream fileOutputStream4 = new FileOutputStream(file);
                while (true) {
                    int i8 = fileInputStream2.read(bArr2);
                    if (i8 <= 0) {
                        break;
                    }
                    a(bArr2, i8);
                    fileOutputStream4.write(bArr2, 0, i8);
                }
                fileOutputStream4.close();
                fileInputStream2.close();
            } catch (IOException unused4) {
                return -1;
            }
        }
        if (str2.toLowerCase().contains(".zip")) {
            try {
                new ZipFile(file);
                try {
                    ZipFile zipFile = new ZipFile(file);
                    Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
                    while (enumerationEntries.hasMoreElements()) {
                        ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                        if (!zipEntryNextElement.isDirectory()) {
                            String lowerCase = zipEntryNextElement.getName().toLowerCase();
                            if (lowerCase.contentEquals("faas_v1-codecexceptionbybrandandcolorformat.csv") || lowerCase.contentEquals("faas_v1-codecexceptionbymodelname.csv") || lowerCase.contentEquals("faas_v1-exceptionsfordevicemodels_vsc_sdk.csv")) {
                                byte[] bArr3 = new byte[1024];
                                File file6 = new File(filesDir.getAbsolutePath() + "/" + lowerCase);
                                InputStream inputStream = zipFile.getInputStream(zipEntryNextElement);
                                FileOutputStream fileOutputStream5 = new FileOutputStream(file6);
                                while (true) {
                                    int i9 = inputStream.read(bArr3);
                                    if (i9 <= 0) {
                                        break;
                                    }
                                    fileOutputStream5.write(bArr3, 0, i9);
                                }
                                inputStream.close();
                                fileOutputStream5.close();
                            }
                        }
                    }
                    zipFile.close();
                } catch (IOException unused5) {
                    return -1;
                }
            } catch (IOException unused6) {
                return -1;
            }
        }
        try {
            int iA = a(str, i2, str7, str3, str4, i3, str9, i4);
            if (iA == -1) {
                str5 = str;
                try {
                    iA = b(str5, i2, str7);
                } catch (FileNotFoundException unused7) {
                    return -1;
                }
            } else {
                str5 = str;
            }
            if (iA == -1) {
                i = i3;
                iA = a(str5, str3, i);
            } else {
                i = i3;
            }
            if (iA == -1) {
                iA = a(str5, i2, str9);
            }
            if (iA == -1) {
                str6 = str4;
                iA = a(str5, i2, str6, i, str10);
            } else {
                str6 = str4;
            }
            if (iA == -1) {
                iA = a(str5, i2, str6, i, i4);
            }
            if (iA == -1) {
                iA = 0;
            }
            new File(filesDir.getAbsolutePath() + "/dbFaasOffline.zip").delete();
            new File(filesDir.getAbsolutePath() + "/dbFaasOffline_orig.zip").delete();
            File file7 = new File(filesDir.getAbsolutePath() + "/faas_v1-codecexceptionbybrandandcolorformat.csv");
            f126a = file7;
            file7.delete();
            File file8 = new File(filesDir.getAbsolutePath() + "/faas_v1-codecexceptionbymodelname.csv");
            b = file8;
            file8.delete();
            File file9 = new File(filesDir.getAbsolutePath() + "/faas_v1-exceptionsfordevicemodels_vsc_sdk.csv");
            c = file9;
            file9.delete();
            return iA;
        } catch (FileNotFoundException unused8) {
            return -1;
        }
    }

    private static int a(String str, int i, String str2) {
        try {
            if (str2.contentEquals("i686") || str2.contentEquals("x86_64") || str2.contentEquals("x86")) {
                return i >= 16 ? 2 : 5;
            }
            return -1;
        } catch (Exception unused) {
            return -1;
        }
    }

    private static int a(String str, int i, String str2, int i2, int i3) {
        if (i3 == 3) {
            try {
                if (str2.contentEquals("OMX.ST.VFM.H264Dec") && i2 == 2141192192) {
                    return 4;
                }
            } catch (Exception unused) {
                return -1;
            }
        }
        if (str2.contains("Nvidia") && i2 == 19) {
            return i < 20 ? 4 : 0;
        }
        if (str2.contains("DUCATI1")) {
            return i >= 20 ? 2 : 4;
        }
        if (str2.contains("Exynos")) {
            return i >= 16 ? 2 : 4;
        }
        if (str2.contains("SEC")) {
            return i >= 16 ? 2 : 4;
        }
        if (str2.contains("MSVDX")) {
            return i >= 16 ? 2 : 4;
        }
        if (str2.contains("k3")) {
            return i >= 16 ? 1 : 5;
        }
        if (str2.contains("MARVELL")) {
            return i >= 16 ? 2 : 5;
        }
        if (str2.contains("MTK")) {
            if (i >= 18) {
                return 2;
            }
            if (i >= 16) {
                return (i2 == 2130706433 || i2 == 2130706944) ? 1 : 2;
            }
            return 5;
        }
        if (str2.contains("ST")) {
            if (i >= 20) {
                return 2;
            }
            return i >= 16 ? 4 : 5;
        }
        if (str2.contains("amlogic")) {
            return i >= 16 ? 6 : 5;
        }
        return -1;
    }

    private static int a(String str, int i, String str2, int i2, String str3) {
        try {
            if (str3.contains("sofia3g")) {
                return i >= 16 ? 1 : 5;
            }
            return -1;
        } catch (Exception unused) {
            return -1;
        }
    }

    private static int a(String str, int i, String str2, String str3, String str4, int i2, String str5, int i3) throws FileNotFoundException {
        String line;
        if (str.toLowerCase().contains("vsc") && str4.toLowerCase().contains("sec")) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(c)));
                do {
                    line = bufferedReader.readLine();
                    if (line == null) {
                        bufferedReader.close();
                    }
                } while (!line.split(";")[0].contentEquals(str2));
                return i < 16 ? 5 : 2;
            } catch (IOException unused) {
            }
        }
        return -1;
    }

    private static int a(String str, String str2, int i) throws FileNotFoundException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(f126a)));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    bufferedReader.close();
                    break;
                }
                String[] strArrSplit = line.split(";");
                if (strArrSplit[1].contentEquals(str2) && Integer.parseInt(strArrSplit[2]) == i) {
                    return Integer.parseInt(strArrSplit[3]);
                }
            }
        } catch (IOException unused) {
        }
        return -1;
    }

    private static void a(byte[] bArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            byte[] bArr2 = d;
            bArr[i2] = (byte) (bArr2[i2] ^ bArr[i2]);
            bArr2[i2] = bArr[i2];
        }
    }

    private static byte[] a() {
        byte[] bArr = new byte[4];
        bArr[0] = (byte) (((bArr[0] - 14) | PsExtractor.PRIVATE_STREAM_1) & 255);
        bArr[1] = (byte) (((bArr[1] + 94) - 232) & 255);
        bArr[3] = (byte) ((bArr[3] - 69) & 255);
        bArr[0] = (byte) ((bArr[0] + 191) & 255);
        bArr[2] = (byte) (((bArr[2] | bArr[3]) - bArr[1]) & 255);
        bArr[3] = (byte) (((bArr[3] | 232) + 199) & 255);
        bArr[0] = (byte) ((bArr[0] + bArr[1]) & 255);
        bArr[3] = (byte) ((bArr[3] + bArr[0] + bArr[1]) & 255);
        bArr[3] = (byte) ((bArr[3] - 246) & 255);
        bArr[2] = (byte) ((bArr[2] - 162) & 255);
        bArr[0] = (byte) (((bArr[0] & 6) - bArr[1]) & 255);
        bArr[3] = (byte) ((bArr[3] * 25) & 255);
        bArr[0] = (byte) (((bArr[0] | bArr[0]) * 40) & 255);
        bArr[1] = (byte) ((bArr[1] | 190) & 228 & 255);
        bArr[1] = (byte) ((bArr[1] | 79) & 255);
        bArr[0] = (byte) ((bArr[0] + bArr[2] + 209) & 255);
        bArr[2] = (byte) ((bArr[2] - 86) & 255);
        bArr[1] = (byte) (((bArr[1] - 80) - 37) & 255);
        bArr[0] = (byte) ((bArr[0] - 13) & 255);
        bArr[2] = (byte) ((bArr[2] + 21) & 255);
        bArr[3] = (byte) ((bArr[3] * 200) & PsExtractor.AUDIO_STREAM & 255);
        bArr[0] = (byte) (((bArr[0] & bArr[3]) ^ 36) & 255);
        bArr[2] = (byte) ((bArr[2] | 238) & 255);
        bArr[3] = (byte) (((bArr[3] & 189) | 100) & 255);
        bArr[0] = (byte) ((bArr[0] ^ bArr[2]) & 255);
        bArr[0] = (byte) ((bArr[0] | bArr[2]) & 133 & 255);
        bArr[3] = (byte) ((bArr[3] + bArr[0] + bArr[3]) & 255);
        bArr[3] = (byte) (((bArr[3] ^ 154) + bArr[1]) & 255);
        bArr[0] = (byte) ((bArr[0] * bArr[1]) & 255);
        bArr[2] = (byte) ((bArr[2] + 9) & 255);
        bArr[2] = (byte) (bArr[2] & bArr[0] & 160 & 255);
        bArr[2] = (byte) ((bArr[2] - 211) & 255);
        bArr[0] = (byte) ((bArr[0] * 222) & 255);
        bArr[2] = (byte) ((bArr[2] - bArr[1]) & 255);
        bArr[0] = (byte) ((bArr[0] ^ 233) & 255);
        bArr[3] = (byte) ((bArr[3] * 126 * bArr[0]) & 255);
        bArr[3] = (byte) ((bArr[3] - 42) & 255);
        bArr[2] = (byte) ((bArr[2] ^ MsgpackFormat.FLOAT_64) & 255);
        bArr[0] = (byte) (((4 ^ bArr[0]) | bArr[1]) & 255);
        bArr[2] = (byte) ((bArr[2] * bArr[3] * 126) & 255);
        bArr[3] = (byte) ((bArr[3] + 175) & 255);
        bArr[0] = (byte) (((bArr[0] - 24) - 95) & 255);
        bArr[3] = (byte) (((bArr[3] & 43) * 110) & 255);
        bArr[0] = (byte) ((bArr[0] * bArr[0] * 7) & 255);
        bArr[2] = (byte) (((bArr[2] + bArr[1]) ^ bArr[1]) & 255);
        bArr[3] = (byte) ((bArr[3] + bArr[3]) & bArr[3] & 255);
        bArr[3] = (byte) (bArr[3] & 35 & 255);
        bArr[0] = (byte) (((bArr[0] | bArr[3]) ^ bArr[1]) & 255);
        bArr[0] = (byte) ((bArr[0] * bArr[3]) & 255);
        bArr[2] = (byte) ((bArr[2] * 49) & 255);
        bArr[3] = (byte) (((bArr[3] ^ 123) ^ bArr[0]) & 255);
        bArr[3] = (byte) (bArr[3] & 215 & 255);
        bArr[3] = (byte) ((bArr[3] | 184) & 255);
        bArr[2] = (byte) (((bArr[2] - bArr[0]) ^ 5) & 255);
        bArr[3] = (byte) ((bArr[3] | 199) & 255);
        bArr[3] = (byte) (bArr[3] & bArr[1] & 186 & 255);
        bArr[3] = (byte) (((bArr[3] & 38) - bArr[0]) & 255);
        bArr[2] = (byte) (((bArr[2] - bArr[1]) * 62) & 255);
        bArr[0] = (byte) ((bArr[0] | bArr[1]) & 255);
        bArr[3] = (byte) (((bArr[3] | 199) - bArr[1]) & 255);
        bArr[2] = (byte) (((bArr[2] - 132) | 127) & 255);
        bArr[0] = (byte) ((bArr[0] + bArr[3]) & 255);
        bArr[0] = (byte) ((bArr[0] * 98) & 255);
        bArr[3] = (byte) (((bArr[3] + 136) - bArr[1]) & 255);
        bArr[0] = (byte) (((bArr[0] & 73) - 130) & 255);
        bArr[2] = (byte) ((bArr[2] - 187) & 255);
        bArr[2] = (byte) (bArr[2] & bArr[1] & bArr[3] & 255);
        bArr[0] = (byte) ((bArr[0] | 166) & 255);
        bArr[0] = (byte) ((bArr[0] ^ bArr[3]) & 255);
        bArr[2] = (byte) ((bArr[2] * bArr[3]) & 239 & 255);
        bArr[3] = (byte) ((bArr[3] - bArr[1]) & 255);
        bArr[2] = (byte) (((bArr[2] ^ 56) + bArr[2]) & 255);
        bArr[3] = (byte) ((bArr[3] | bArr[1]) & 255);
        bArr[3] = (byte) (((bArr[3] & MsgpackFormat.UINT_32) ^ TsExtractor.TS_STREAM_TYPE_SPLICE_INFO) & 255);
        bArr[3] = (byte) ((bArr[3] ^ 155) & 255);
        bArr[3] = (byte) ((bArr[3] + 67) & 255);
        bArr[0] = (byte) ((bArr[0] | bArr[0] | 81) & 255);
        bArr[2] = (byte) (((bArr[2] * bArr[2]) | 104) & 255);
        bArr[2] = (byte) (((bArr[2] | 184) * 8) & 255);
        bArr[0] = (byte) (((bArr[0] ^ 73) ^ 56) & 255);
        bArr[0] = (byte) ((bArr[0] + 239) & 255);
        bArr[2] = (byte) (((bArr[2] ^ 170) + VODownloader.DOWNLOADER_ERROR_LIVE_UNSUPPORTED) & 255);
        bArr[0] = (byte) (((bArr[0] + bArr[3]) ^ PsExtractor.PRIVATE_STREAM_1) & 255);
        bArr[3] = (byte) (((bArr[3] ^ bArr[2]) * 216) & 255);
        bArr[3] = (byte) ((bArr[3] | 225) & 255);
        bArr[2] = (byte) ((bArr[2] + 173) & 255);
        bArr[0] = (byte) ((bArr[0] ^ bArr[3]) & 255);
        bArr[0] = (byte) (((bArr[0] + bArr[1]) ^ 92) & 255);
        bArr[2] = (byte) ((bArr[2] - bArr[1]) & 255);
        bArr[2] = (byte) ((bArr[2] | 143) & 255);
        bArr[2] = (byte) (((bArr[2] & bArr[3]) * 101) & 255);
        bArr[2] = (byte) ((bArr[2] ^ bArr[2]) & 255);
        bArr[0] = (byte) ((bArr[0] | 80) & 255);
        bArr[3] = (byte) ((bArr[3] ^ 175) & 255);
        bArr[2] = (byte) ((bArr[2] + bArr[1] + bArr[2]) & 255);
        bArr[2] = (byte) ((bArr[2] * 179) & bArr[0] & 255);
        bArr[2] = (byte) ((bArr[2] - 170) & 255);
        bArr[0] = (byte) ((bArr[0] - 182) & 255);
        bArr[3] = (byte) ((bArr[3] ^ 146) & 255);
        bArr[2] = (byte) (((bArr[2] ^ 21) | bArr[0]) & 255);
        return bArr;
    }

    private static int b(String str, int i, String str2) throws FileNotFoundException {
        String[] strArrSplit;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(b)));
            do {
                String line = bufferedReader.readLine();
                if (line == null) {
                    bufferedReader.close();
                    return -1;
                }
                strArrSplit = line.split(";");
            } while (!strArrSplit[0].contentEquals(str2));
            return i >= 20 ? Integer.parseInt(strArrSplit[3]) : Integer.parseInt(strArrSplit[2]);
        } catch (IOException unused) {
        }
    }
}
