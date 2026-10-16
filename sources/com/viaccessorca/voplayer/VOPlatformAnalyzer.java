package com.viaccessorca.voplayer;

import android.content.Context;
import android.os.Build;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
class VOPlatformAnalyzer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String[] f71a = {"2710E", "X315", "Z710E"};
    private static final String[] b = {"HTC Sensation"};
    private static String c = null;
    private static int d = -1;

    class a implements FileFilter {
        a() {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            return Pattern.matches("cpu[0-9]", file.getName());
        }
    }

    private static native int MTRBMEU0NTFGRTc0NDAxMDkzMUQ1NDgzNUVDQzc3RTI(String str);

    private static native int MzZFODg3NTdBQjlGNEFBMTkwQUY2RkFERDI2OTc0NkU();

    private static native int NTZGQUE4OUY3NTdDNEE3NkJBNkQ0QjY2QUUwRTQzN0Y();

    private static native int ODVFQ0E4OTNCMjM1NDBCMjg0QUVEOTYwRkM2NkI5RjM();

    private static native boolean QTJGMUIwQkRGNThENDRCREIyNzU1QzkyNDRCRkRBMEU();

    private static native String QUQ3NEY3RUJFMjVCNEE3ODlDOEEyNkUxN0JCNkRFNTc(String str);

    private static native String QzI2NDNCRTgzMzIzNEZFMEJCRUZENzhBQTJBODkzNjc();

    protected static int a() {
        return ODVFQ0E4OTNCMjM1NDBCMjg0QUVEOTYwRkM2NkI5RjM();
    }

    protected static int a(Context context) {
        File filesDir;
        if (d < 0 && context != null && (filesDir = context.getFilesDir()) != null) {
            d = a(filesDir.getAbsolutePath() + "/HWBenchmark.txt");
        }
        return d;
    }

    private static int a(String str) {
        return MTRBMEU0NTFGRTc0NDAxMDkzMUQ1NDgzNUVDQzc3RTI(str);
    }

    protected static int b() {
        return NTZGQUE4OUY3NTdDNEE3NkJBNkQ0QjY2QUUwRTQzN0Y();
    }

    protected static String b(Context context) {
        File filesDir;
        if (c == null && context != null && (filesDir = context.getFilesDir()) != null) {
            c = b(filesDir.getAbsolutePath() + "/HWBenchmark.txt");
        }
        return c;
    }

    private static String b(String str) {
        return QUQ3NEY3RUJFMjVCNEE3ODlDOEEyNkUxN0JCNkRFNTc(str);
    }

    protected static int c() {
        return MzZFODg3NTdBQjlGNEFBMTkwQUY2RkFERDI2OTc0NkU();
    }

    protected static int d() {
        String[] strArrSplit;
        int i = -1;
        try {
            FileReader fileReader = new FileReader(new File("/sys/devices/system/cpu/present"));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            if (line != null && (strArrSplit = line.split("-")) != null) {
                i = Integer.parseInt(strArrSplit[strArrSplit.length - 1].replaceAll("[^\\d.]", "")) + 1;
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (NumberFormatException e3) {
            e3.printStackTrace();
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        return i;
    }

    protected static int e() {
        int i;
        int i2 = 0;
        try {
            File[] fileArrListFiles = new File("/sys/devices/system/cpu/").listFiles(new a());
            i = 0;
            while (i2 < fileArrListFiles.length) {
                try {
                    FileReader fileReader = new FileReader(new File("/sys/devices/system/cpu/cpu" + i2 + "/cpufreq/cpuinfo_max_freq"));
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String line = bufferedReader.readLine();
                    if (line != null) {
                        i += Integer.parseInt(line.trim().replaceAll("[^\\d.]", ""));
                    }
                    bufferedReader.close();
                    fileReader.close();
                    i2++;
                } catch (FileNotFoundException | IOException | NumberFormatException | Exception unused) {
                }
            }
        } catch (FileNotFoundException | IOException | NumberFormatException | Exception unused2) {
            i = 0;
        }
        if (i2 <= 0 || i == 0) {
            return -1;
        }
        return i / i2;
    }

    protected static String f() {
        return QzI2NDNCRTgzMzIzNEZFMEJCRUZENzhBQTJBODkzNjc();
    }

    protected static boolean g() {
        return !h();
    }

    private static boolean h() {
        String str = Build.MODEL;
        for (String str2 : f71a) {
            if (str.equalsIgnoreCase(str2)) {
                return true;
            }
        }
        for (String str3 : b) {
            if (str.toLowerCase(Locale.US).startsWith(str3.toLowerCase(Locale.US))) {
                return true;
            }
        }
        return false;
    }

    protected static boolean i() {
        return QTJGMUIwQkRGNThENDRCREIyNzU1QzkyNDRCRkRBMEU();
    }
}
