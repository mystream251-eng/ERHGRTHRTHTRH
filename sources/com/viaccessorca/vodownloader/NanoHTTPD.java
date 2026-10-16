package com.viaccessorca.vodownloader;

import android.support.v4.media.session.PlaybackStateCompat;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.viaccessorca.common.VOUtils;
import com.viaccessorca.voplayer.VOPlayer;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.Vector;

/* JADX INFO: loaded from: classes2.dex */
public class NanoHTTPD {
    public static final String HTTP_BADREQUEST = "400 Bad Request";
    public static final String HTTP_DATANOTYET = "408 Request Timeout";
    public static final String HTTP_FORBIDDEN = "403 Forbidden";
    public static final String HTTP_INTERNALERROR = "500 Internal Server Error";
    public static final String HTTP_NOTFOUND = "404 Not Found";
    public static final String HTTP_NOTIMPLEMENTED = "501 Not Implemented";
    public static final String HTTP_NOTMODIFIED = "304 Not Modified";
    public static final String HTTP_OK = "200 OK";
    public static final String HTTP_PARTIALCONTENT = "206 Partial Content";
    public static final String HTTP_RANGE_NOT_SATISFIABLE = "416 Requested Range Not Satisfiable";
    public static final String HTTP_REDIRECT = "301 Moved Permanently";
    public static final String MIME_DEFAULT_BINARY = "application/octet-stream";
    public static final String MIME_HTML = "text/html";
    public static final String MIME_PLAINTEXT = "text/plain";
    public static final String MIME_XML = "text/xml";
    private static Hashtable f = new Hashtable();
    private static int g;
    protected static PrintStream h;
    protected static PrintStream i;
    private static SimpleDateFormat j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f43a;
    private final ServerSocket b;
    private Thread c;
    private File d;
    private int e = -1;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            while (true) {
                try {
                    NanoHTTPD.this.new c(NanoHTTPD.this.b.accept());
                } catch (IOException unused) {
                    return;
                }
            }
        }
    }

    class b extends FileInputStream {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ long f45a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(NanoHTTPD nanoHTTPD, File file, long j) {
            super(file);
            this.f45a = j;
        }

        @Override // java.io.FileInputStream, java.io.InputStream
        public int available() throws IOException {
            return (int) this.f45a;
        }
    }

    private class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Socket f46a;

        public c(Socket socket) {
            this.f46a = socket;
            Thread thread = new Thread(this);
            thread.setDaemon(true);
            thread.start();
        }

        private int a(byte[] bArr, int i) {
            while (i < bArr.length) {
                if (bArr[i] == 13) {
                    i++;
                    if (bArr[i] == 10) {
                        i++;
                        if (bArr[i] == 13) {
                            i++;
                            if (bArr[i] == 10) {
                                break;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
                i++;
            }
            return i + 1;
        }

        private String a(String str) throws InterruptedException {
            try {
                StringBuffer stringBuffer = new StringBuffer();
                int i = 0;
                while (i < str.length()) {
                    char cCharAt = str.charAt(i);
                    if (cCharAt == '%') {
                        stringBuffer.append((char) Integer.parseInt(str.substring(i + 1, i + 3), 16));
                        i += 2;
                    } else if (cCharAt != '+') {
                        stringBuffer.append(cCharAt);
                    } else {
                        stringBuffer.append(' ');
                    }
                    i++;
                }
                return stringBuffer.toString();
            } catch (Exception unused) {
                a(NanoHTTPD.HTTP_BADREQUEST, "BAD REQUEST: Bad percent-encoding.");
                throw null;
            }
        }

        private String a(byte[] bArr, int i, int i2) {
            if (i2 <= 0) {
                return "";
            }
            try {
                File fileCreateTempFile = File.createTempFile("NanoHTTPD", "", new File(System.getProperty("java.io.tmpdir")));
                FileOutputStream fileOutputStream = new FileOutputStream(fileCreateTempFile);
                fileOutputStream.write(bArr, i, i2);
                fileOutputStream.close();
                return fileCreateTempFile.getAbsolutePath();
            } catch (Exception e) {
                NanoHTTPD.i.println("Error: " + e.getMessage());
                return "";
            }
        }

        private void a(BufferedReader bufferedReader, Properties properties, Properties properties2, Properties properties3) throws InterruptedException {
            String strA;
            try {
                String line = bufferedReader.readLine();
                if (line == null) {
                    return;
                }
                StringTokenizer stringTokenizer = new StringTokenizer(line);
                if (!stringTokenizer.hasMoreTokens()) {
                    a(NanoHTTPD.HTTP_BADREQUEST, "BAD REQUEST: Syntax error. Usage: GET /example/file.html");
                    throw null;
                }
                properties.put("method", stringTokenizer.nextToken());
                if (!stringTokenizer.hasMoreTokens()) {
                    a(NanoHTTPD.HTTP_BADREQUEST, "BAD REQUEST: Missing URI. Usage: GET /example/file.html");
                    throw null;
                }
                String strNextToken = stringTokenizer.nextToken();
                int iIndexOf = strNextToken.indexOf(63);
                if (iIndexOf >= 0) {
                    a(strNextToken.substring(iIndexOf + 1), properties2);
                    strA = a(strNextToken.substring(0, iIndexOf));
                } else {
                    strA = a(strNextToken);
                }
                if (stringTokenizer.hasMoreTokens()) {
                    while (true) {
                        String line2 = bufferedReader.readLine();
                        if (line2 == null || line2.trim().length() <= 0) {
                            break;
                        }
                        int iIndexOf2 = line2.indexOf(58);
                        if (iIndexOf2 >= 0) {
                            properties3.put(line2.substring(0, iIndexOf2).trim().toLowerCase(), line2.substring(iIndexOf2 + 1).trim());
                        }
                    }
                }
                properties.put("uri", strA);
            } catch (IOException e) {
                a(NanoHTTPD.HTTP_INTERNALERROR, "SERVER INTERNAL ERROR: IOException: " + e.getMessage());
                throw null;
            }
        }

        private void a(String str, String str2) throws InterruptedException {
            a(str, NanoHTTPD.MIME_PLAINTEXT, (Properties) null, new ByteArrayInputStream(str2.getBytes()));
            throw new InterruptedException();
        }

        private void a(String str, String str2, Properties properties, InputStream inputStream) {
            try {
                try {
                    if (str == null) {
                        throw new Error("sendResponse(): Status can't be null.");
                    }
                    OutputStream outputStream = this.f46a.getOutputStream();
                    PrintWriter printWriter = new PrintWriter(outputStream);
                    printWriter.print("HTTP/1.0 " + str + " \r\n");
                    if (str2 != null) {
                        printWriter.print("Content-Type: " + str2 + "\r\n");
                    }
                    if (properties == null || properties.getProperty("Date") == null) {
                        printWriter.print("Date: " + NanoHTTPD.j.format(new Date()) + "\r\n");
                    }
                    if (properties != null) {
                        Enumeration enumerationKeys = properties.keys();
                        while (enumerationKeys.hasMoreElements()) {
                            String str3 = (String) enumerationKeys.nextElement();
                            printWriter.print(str3 + ": " + properties.getProperty(str3) + "\r\n");
                        }
                    }
                    printWriter.print("\r\n");
                    printWriter.flush();
                    if (inputStream != null) {
                        int iAvailable = inputStream.available();
                        byte[] bArr = new byte[NanoHTTPD.g];
                        while (iAvailable > 0) {
                            long jNanoTime = System.nanoTime();
                            int i = inputStream.read(bArr, 0, iAvailable > NanoHTTPD.g ? NanoHTTPD.g : iAvailable);
                            if (i <= 0) {
                                break;
                            }
                            outputStream.write(bArr, 0, i);
                            iAvailable -= i;
                            if (NanoHTTPD.this.e > 0) {
                                try {
                                    Thread.sleep(((long) (((i * 8) * 1000) / (NanoHTTPD.this.e + VOPlayer.MEDIA_INFO_WAO))) - ((System.nanoTime() - jNanoTime) / 1000000));
                                } catch (Exception unused) {
                                }
                            }
                        }
                    }
                    outputStream.flush();
                    outputStream.close();
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException unused2) {
                    this.f46a.close();
                }
            } catch (Throwable unused3) {
            }
        }

        private void a(String str, Properties properties) throws InterruptedException {
            if (str == null) {
                return;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(str, "&");
            while (stringTokenizer.hasMoreTokens()) {
                String strNextToken = stringTokenizer.nextToken();
                int iIndexOf = strNextToken.indexOf(61);
                if (iIndexOf >= 0) {
                    properties.put(a(strNextToken.substring(0, iIndexOf)).trim(), a(strNextToken.substring(iIndexOf + 1)));
                }
            }
        }

        private void a(String str, byte[] bArr, BufferedReader bufferedReader, Properties properties, Properties properties2) throws InterruptedException {
            String line;
            String strSubstring;
            StringBuilder sb;
            try {
                int[] iArrA = a(bArr, str.getBytes());
                String line2 = bufferedReader.readLine();
                int i = 1;
                while (line2 != null) {
                    if (line2.indexOf(str) == -1) {
                        a(NanoHTTPD.HTTP_BADREQUEST, "BAD REQUEST: Content type is multipart/form-data but next chunk does not start with boundary. Usage: GET /example/file.html");
                        throw null;
                    }
                    i++;
                    Properties properties3 = new Properties();
                    while (true) {
                        line = bufferedReader.readLine();
                        if (line == null || line.trim().length() <= 0) {
                            break;
                        }
                        int iIndexOf = line.indexOf(58);
                        if (iIndexOf != -1) {
                            properties3.put(line.substring(0, iIndexOf).trim().toLowerCase(), line.substring(iIndexOf + 1).trim());
                        }
                    }
                    if (line != null) {
                        String property = properties3.getProperty("content-disposition");
                        if (property == null) {
                            a(NanoHTTPD.HTTP_BADREQUEST, "BAD REQUEST: Content type is multipart/form-data but no content-disposition info found. Usage: GET /example/file.html");
                            throw null;
                        }
                        StringTokenizer stringTokenizer = new StringTokenizer(property, "; ");
                        Properties properties4 = new Properties();
                        while (stringTokenizer.hasMoreTokens()) {
                            String strNextToken = stringTokenizer.nextToken();
                            int iIndexOf2 = strNextToken.indexOf(61);
                            if (iIndexOf2 != -1) {
                                properties4.put(strNextToken.substring(0, iIndexOf2).trim().toLowerCase(), strNextToken.substring(iIndexOf2 + 1).trim());
                            }
                        }
                        String property2 = properties4.getProperty("name");
                        String strSubstring2 = property2.substring(1, property2.length() - 1);
                        if (properties3.getProperty("content-type") == null) {
                            strSubstring = "";
                            while (line != null && line.indexOf(str) == -1) {
                                line = bufferedReader.readLine();
                                if (line != null) {
                                    int iIndexOf3 = line.indexOf(str);
                                    if (iIndexOf3 == -1) {
                                        sb = new StringBuilder();
                                        sb.append(strSubstring);
                                        sb.append(line);
                                    } else {
                                        StringBuilder sb2 = new StringBuilder();
                                        sb2.append(strSubstring);
                                        sb2.append(line.substring(0, iIndexOf3 - 2));
                                        sb = sb2;
                                    }
                                    strSubstring = sb.toString();
                                }
                            }
                        } else {
                            if (i > iArrA.length) {
                                a(NanoHTTPD.HTTP_INTERNALERROR, "Error processing request");
                                throw null;
                            }
                            int iA = a(bArr, iArrA[i - 2]);
                            properties2.put(strSubstring2, a(bArr, iA, (iArrA[i - 1] - iA) - 4));
                            String property3 = properties4.getProperty("filename");
                            strSubstring = property3.substring(1, property3.length() - 1);
                            do {
                                line = bufferedReader.readLine();
                                if (line == null) {
                                    break;
                                }
                            } while (line.indexOf(str) == -1);
                        }
                        properties.put(strSubstring2, strSubstring);
                    }
                    line2 = line;
                }
            } catch (IOException e) {
                a(NanoHTTPD.HTTP_INTERNALERROR, "SERVER INTERNAL ERROR: IOException: " + e.getMessage());
                throw null;
            }
        }

        public int[] a(byte[] bArr, byte[] bArr2) {
            Vector vector = new Vector();
            int i = -1;
            int i2 = 0;
            int i3 = 0;
            while (i2 < bArr.length) {
                if (bArr[i2] == bArr2[i3]) {
                    if (i3 == 0) {
                        i = i2;
                    }
                    i3++;
                    if (i3 == bArr2.length) {
                        vector.addElement(new Integer(i));
                    }
                    i2++;
                } else {
                    i2 -= i3;
                }
                i = -1;
                i3 = 0;
                i2++;
            }
            int size = vector.size();
            int[] iArr = new int[size];
            for (int i4 = 0; i4 < size; i4++) {
                iArr[i4] = ((Integer) vector.elementAt(i4)).intValue();
            }
            return iArr;
        }

        @Override // java.lang.Runnable
        public void run() throws InterruptedException {
            byte[] bArr;
            int i;
            long j;
            int i2;
            boolean z;
            try {
                InputStream inputStream = this.f46a.getInputStream();
                if (inputStream != null && (i = inputStream.read((bArr = new byte[8192]), 0, 8192)) > 0) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bArr, 0, i)));
                    Properties properties = new Properties();
                    Properties properties2 = new Properties();
                    Properties properties3 = new Properties();
                    Properties properties4 = new Properties();
                    a(bufferedReader, properties, properties2, properties3);
                    String property = properties.getProperty("method");
                    String property2 = properties.getProperty("uri");
                    String property3 = properties3.getProperty("content-length");
                    if (property3 != null) {
                        try {
                            j = Integer.parseInt(property3);
                        } catch (NumberFormatException unused) {
                            j = Long.MAX_VALUE;
                        }
                    } else {
                        j = Long.MAX_VALUE;
                    }
                    int i3 = 0;
                    while (true) {
                        if (i3 >= i) {
                            i2 = 1;
                            z = false;
                            break;
                        }
                        if (bArr[i3] == 13) {
                            i3++;
                            if (bArr[i3] == 10) {
                                i3++;
                                if (bArr[i3] == 13) {
                                    i3++;
                                    if (bArr[i3] == 10) {
                                        i2 = 1;
                                        z = true;
                                        break;
                                    }
                                } else {
                                    continue;
                                }
                            } else {
                                continue;
                            }
                        }
                        i3++;
                    }
                    int i4 = i3 + i2;
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    if (i4 < i) {
                        byteArrayOutputStream.write(bArr, i4, i - i4);
                    }
                    if (i4 < i) {
                        j -= (long) ((i - i4) + 1);
                    } else if (!z || j == Long.MAX_VALUE) {
                        j = 0;
                    }
                    int i5 = 512;
                    byte[] bArr2 = new byte[512];
                    while (i >= 0 && j > 0) {
                        int i6 = inputStream.read(bArr2, 0, i5);
                        j -= (long) i6;
                        if (i6 > 0) {
                            byteArrayOutputStream.write(bArr2, 0, i6);
                        }
                        i = i6;
                        i5 = 512;
                    }
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(byteArray)));
                    if (property.equalsIgnoreCase(VOUtils.HTTP_POST)) {
                        StringTokenizer stringTokenizer = new StringTokenizer(properties3.getProperty("content-type"), "; ");
                        String str = "";
                        if (!(stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken() : "").equalsIgnoreCase("multipart/form-data")) {
                            char[] cArr = new char[512];
                            while (true) {
                                int i7 = bufferedReader2.read(cArr);
                                if (i7 < 0 || str.endsWith("\r\n")) {
                                    break;
                                }
                                str = str + String.valueOf(cArr, 0, i7);
                            }
                            a(str.trim(), properties2);
                        } else {
                            if (!stringTokenizer.hasMoreTokens()) {
                                a(NanoHTTPD.HTTP_BADREQUEST, "BAD REQUEST: Content type is multipart/form-data but boundary missing. Usage: GET /example/file.html");
                                throw null;
                            }
                            StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken(), "=");
                            if (stringTokenizer2.countTokens() != 2) {
                                a(NanoHTTPD.HTTP_BADREQUEST, "BAD REQUEST: Content type is multipart/form-data but boundary syntax error. Usage: GET /example/file.html");
                                throw null;
                            }
                            stringTokenizer2.nextToken();
                            a(stringTokenizer2.nextToken(), byteArray, bufferedReader2, properties2, properties4);
                        }
                    }
                    if (property.equalsIgnoreCase(VOUtils.HTTP_PUT)) {
                        properties4.put(ToolboxAnalyticsModuleKt.KEY_CONTENT, a(byteArray, 0, byteArrayOutputStream.size()));
                    }
                    d dVarServe = NanoHTTPD.this.serve(property2, property, properties3, properties2, properties4);
                    if (dVarServe == null) {
                        a(NanoHTTPD.HTTP_INTERNALERROR, "SERVER INTERNAL ERROR: Serve() returned a null response.");
                        throw null;
                    }
                    a(dVarServe.f47a, dVarServe.b, dVarServe.d, dVarServe.c);
                    bufferedReader.close();
                    bufferedReader2.close();
                    inputStream.close();
                }
            } catch (IOException e) {
                a(NanoHTTPD.HTTP_INTERNALERROR, "SERVER INTERNAL ERROR: IOException: " + e.getMessage());
                throw null;
            } catch (Throwable unused2) {
            }
        }
    }

    public class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f47a;
        public String b;
        public InputStream c;
        public Properties d = new Properties();

        public d(NanoHTTPD nanoHTTPD, String str, String str2, InputStream inputStream) {
            this.f47a = str;
            this.b = str2;
            this.c = inputStream;
        }

        public d(NanoHTTPD nanoHTTPD, String str, String str2, String str3) {
            this.f47a = str;
            this.b = str2;
            try {
                this.c = new ByteArrayInputStream(str3.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        public void a(String str, String str2) {
            this.d.put(str, str2);
        }
    }

    static {
        StringTokenizer stringTokenizer = new StringTokenizer("css\t\ttext/css htm\t\ttext/html html\t\ttext/html xml\t\ttext/xml txt\t\ttext/plain asc\t\ttext/plain gif\t\timage/gif jpg\t\timage/jpeg jpeg\t\timage/jpeg png\t\timage/png mp3\t\taudio/mpeg m3u\t\taudio/mpeg-url mp4\t\tvideo/mp4 ogv\t\tvideo/ogg flv\t\tvideo/x-flv mov\t\tvideo/quicktime swf\t\tapplication/x-shockwave-flash js\t\t\tapplication/javascript pdf\t\tapplication/pdf doc\t\tapplication/msword ogg\t\tapplication/x-ogg zip\t\tapplication/octet-stream exe\t\tapplication/octet-stream class\t\tapplication/octet-stream ");
        while (stringTokenizer.hasMoreTokens()) {
            f.put(stringTokenizer.nextToken(), stringTokenizer.nextToken());
        }
        g = 16384;
        h = System.out;
        i = System.err;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        j = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    public NanoHTTPD(int i2, File file) throws IOException {
        this.f43a = i2;
        this.d = file;
        this.b = new ServerSocket(this.f43a);
        Thread thread = new Thread(new a());
        this.c = thread;
        thread.setDaemon(true);
        this.c.start();
    }

    private String a(String str) {
        StringBuilder sb;
        StringTokenizer stringTokenizer = new StringTokenizer(str, "/ ", true);
        String string = "";
        while (stringTokenizer.hasMoreTokens()) {
            String strNextToken = stringTokenizer.nextToken();
            if (strNextToken.equals("/")) {
                sb = new StringBuilder();
                sb.append(string);
                sb.append("/");
            } else if (strNextToken.equals(" ")) {
                sb = new StringBuilder();
                sb.append(string);
                sb.append("%20");
            } else {
                string = string + URLEncoder.encode(strNextToken);
            }
            string = sb.toString();
        }
        return string;
    }

    public static void main(String[] strArr) {
        h.println("NanoHTTPD 1.25 (C) 2001,2005-2011 Jarno Elonen and (C) 2010 Konstantinos Togias\n(Command line options: [-p port] [-d root-dir] [--licence])\n");
        File absoluteFile = new File(".").getAbsoluteFile();
        int i2 = 80;
        for (int i3 = 0; i3 < strArr.length; i3++) {
            if (!strArr[i3].equalsIgnoreCase("-p")) {
                if (!strArr[i3].equalsIgnoreCase("-d")) {
                    if (strArr[i3].toLowerCase().endsWith("licence")) {
                        h.println("Copyright (C) 2001,2005-2011 by Jarno Elonen <elonen@iki.fi>\nand Copyright (C) 2010 by Konstantinos Togias <info@ktogias.gr>\n\nRedistribution and use in source and binary forms, with or without\nmodification, are permitted provided that the following conditions\nare met:\n\nRedistributions of source code must retain the above copyright notice,\nthis list of conditions and the following disclaimer. Redistributions in\nbinary form must reproduce the above copyright notice, this list of\nconditions and the following disclaimer in the documentation and/or other\nmaterials provided with the distribution. The name of the author may not\nbe used to endorse or promote products derived from this software without\nspecific prior written permission. \n \nTHIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR\nIMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES\nOF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.\nIN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,\nINCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT\nNOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,\nDATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY\nTHEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT\n(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE\nOF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.\n");
                        break;
                    }
                } else {
                    absoluteFile = new File(strArr[i3 + 1]).getAbsoluteFile();
                }
            } else {
                i2 = Integer.parseInt(strArr[i3 + 1]);
            }
        }
        try {
            new NanoHTTPD(i2, absoluteFile);
        } catch (IOException e) {
            i.println("Couldn't start server:\n" + e);
            System.exit(-1);
        }
        h.println("Now serving files in port " + i2 + " from \"" + absoluteFile + "\"");
        h.println("Hit Enter to stop.\n");
        try {
            System.in.read();
        } catch (Throwable unused) {
        }
    }

    public d serve(String str, String str2, Properties properties, Properties properties2, Properties properties3) {
        h.println(str2 + " '" + str + "' ");
        Enumeration<?> enumerationPropertyNames = properties.propertyNames();
        while (enumerationPropertyNames.hasMoreElements()) {
            String str3 = (String) enumerationPropertyNames.nextElement();
            h.println("  HDR: '" + str3 + "' = '" + properties.getProperty(str3) + "'");
        }
        Enumeration<?> enumerationPropertyNames2 = properties2.propertyNames();
        while (enumerationPropertyNames2.hasMoreElements()) {
            String str4 = (String) enumerationPropertyNames2.nextElement();
            h.println("  PRM: '" + str4 + "' = '" + properties2.getProperty(str4) + "'");
        }
        Enumeration<?> enumerationPropertyNames3 = properties3.propertyNames();
        while (enumerationPropertyNames3.hasMoreElements()) {
            String str5 = (String) enumerationPropertyNames3.nextElement();
            h.println("  UPLOADED: '" + str5 + "' = '" + properties3.getProperty(str5) + "'");
        }
        return serveFile(str, properties, this.d, true);
    }

    /* JADX WARN: Code duplicated, block: B:117:0x042c A[Catch: IOException -> 0x046d, TryCatch #2 {IOException -> 0x046d, blocks: (B:122:0x0463, B:116:0x03ef, B:121:0x0460, B:117:0x042c, B:119:0x043e, B:120:0x0447), top: B:135:0x0397 }] */
    /* JADX WARN: Code duplicated, block: B:119:0x043e A[Catch: IOException -> 0x046d, TryCatch #2 {IOException -> 0x046d, blocks: (B:122:0x0463, B:116:0x03ef, B:121:0x0460, B:117:0x042c, B:119:0x043e, B:120:0x0447), top: B:135:0x0397 }] */
    /* JADX WARN: Code duplicated, block: B:120:0x0447 A[Catch: IOException -> 0x046d, TryCatch #2 {IOException -> 0x046d, blocks: (B:122:0x0463, B:116:0x03ef, B:121:0x0460, B:117:0x042c, B:119:0x043e, B:120:0x0447), top: B:135:0x0397 }] */
    /* JADX WARN: Code duplicated, block: B:126:0x0479 A[PHI: r4
  0x0479: PHI (r4v15 com.viaccessorca.vodownloader.NanoHTTPD$d) = (r4v6 com.viaccessorca.vodownloader.NanoHTTPD$d), (r4v11 com.viaccessorca.vodownloader.NanoHTTPD$d) binds: [B:77:0x0300, B:119:0x043e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:132:0x0302 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:80:0x030e A[Catch: IOException -> 0x0469, TRY_LEAVE, TryCatch #0 {IOException -> 0x0469, blocks: (B:78:0x0302, B:80:0x030e, B:85:0x032b, B:87:0x035e, B:89:0x0366, B:92:0x0374, B:94:0x0380, B:98:0x038d), top: B:132:0x0302 }] */
    /* JADX WARN: Code duplicated, block: B:82:0x0326  */
    /* JADX WARN: Code duplicated, block: B:84:0x0329  */
    /* JADX WARN: Code duplicated, block: B:96:0x0389 A[PHI: r9
  0x0389: PHI (r9v3 java.lang.String) = (r9v2 java.lang.String), (r9v2 java.lang.String), (r9v7 java.lang.String), (r9v7 java.lang.String) binds: [B:86:0x035c, B:88:0x0364, B:90:0x0371, B:131:0x0389] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Instruction removed from duplicated block: B:120:0x0447, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r16v0 */
    /* JADX WARN: Type inference failed for: r16v1 */
    /* JADX WARN: Type inference failed for: r16v14 */
    /* JADX WARN: Type inference failed for: r16v15 */
    /* JADX WARN: Type inference failed for: r16v16 */
    /* JADX WARN: Type inference failed for: r16v2 */
    /* JADX WARN: Type inference failed for: r16v3, types: [long] */
    /* JADX WARN: Type inference failed for: r16v5 */
    /* JADX WARN: Type inference failed for: r16v6 */
    /* JADX WARN: Type inference failed for: r1v14, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r4v7, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v2, types: [long] */
    public d serveFile(String str, Properties properties, File file, boolean z) {
        String strReplace;
        String str2;
        String str3;
        File file2;
        ?? r16;
        String str4;
        d dVar;
        int iLastIndexOf;
        String str5;
        String str6;
        String hexString;
        String property;
        long j2;
        long length;
        String str7;
        String str8;
        ?? r17;
        int iIndexOf;
        String[] strArr;
        String str9;
        String strSubstring;
        int iLastIndexOf2;
        String str10 = "";
        boolean zIsDirectory = file.isDirectory();
        String str11 = MIME_PLAINTEXT;
        d dVar2 = !zIsDirectory ? new d(this, HTTP_INTERNALERROR, MIME_PLAINTEXT, "INTERNAL ERRROR: serveFile(): given homeDir is not a directory.") : null;
        if (dVar2 == null) {
            strReplace = str.trim().replace(File.separatorChar, '/');
            if (strReplace.indexOf(63) >= 0) {
                strReplace = strReplace.substring(0, strReplace.indexOf(63));
            }
            if (strReplace.startsWith("..") || strReplace.endsWith("..") || strReplace.indexOf("../") >= 0) {
                dVar2 = new d(this, HTTP_FORBIDDEN, MIME_PLAINTEXT, "FORBIDDEN: Won't serve ../ for security reasons.");
            }
        } else {
            strReplace = str;
        }
        File file3 = new File(file, strReplace);
        if (dVar2 == null && !file3.exists()) {
            dVar2 = new d(this, HTTP_DATANOTYET, MIME_PLAINTEXT, "Error 408, Request Timeout.");
        }
        if (dVar2 == null && file3.isDirectory()) {
            String str12 = "\">";
            if (!strReplace.endsWith("/")) {
                strReplace = strReplace + "/";
                dVar2 = new d(this, HTTP_REDIRECT, MIME_HTML, "<html><body>Redirected: <a href=\"" + strReplace + "\">" + strReplace + "</a></body></html>");
                dVar2.a("Location", strReplace);
            }
            if (dVar2 == null) {
                if (new File(file3, "index.html").exists()) {
                    file3 = new File(file, strReplace + "/index.html");
                } else if (new File(file3, "index.htm").exists()) {
                    file3 = new File(file, strReplace + "/index.htm");
                } else if (z && file3.canRead()) {
                    String[] list = file3.list();
                    String str13 = "<html><body><h1>Directory " + strReplace + "</h1><br/>";
                    if (strReplace.length() > 1 && (iLastIndexOf2 = (strSubstring = strReplace.substring(0, strReplace.length() - 1)).lastIndexOf(47)) >= 0 && iLastIndexOf2 < strSubstring.length()) {
                        str13 = str13 + "<b><a href=\"" + strReplace.substring(0, iLastIndexOf2 + 1) + "\">..</a></b><br/>";
                    }
                    if (list != null) {
                        int i2 = 0;
                        while (i2 < list.length) {
                            File file4 = new File(file3, list[i2]);
                            boolean zIsDirectory2 = file4.isDirectory();
                            File file5 = file3;
                            if (zIsDirectory2) {
                                list[i2] = list[i2] + "/";
                                str13 = str13 + "<b>";
                            }
                            StringBuilder sb = new StringBuilder();
                            sb.append(str13);
                            sb.append("<a href=\"");
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(strReplace);
                            String str14 = strReplace;
                            sb2.append(list[i2]);
                            sb.append(a(sb2.toString()));
                            sb.append(str12);
                            sb.append(list[i2]);
                            sb.append("</a>");
                            String string = sb.toString();
                            if (file4.isFile()) {
                                long length2 = file4.length();
                                String str15 = string + " &nbsp;<font size=2>(";
                                if (length2 < PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
                                    str9 = str15 + length2 + " bytes";
                                    strArr = list;
                                } else {
                                    strArr = list;
                                    if (length2 < PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) {
                                        str9 = str15 + (length2 / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) + "." + (((length2 % PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) / 10) % 100) + " KB";
                                    } else {
                                        str10 = str10;
                                        str9 = str15 + (length2 / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) + "." + (((length2 % PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) / 10) % 100) + " MB";
                                    }
                                    string = str9 + ")</font>";
                                }
                                string = str9 + ")</font>";
                            } else {
                                strArr = list;
                                str10 = str10;
                                str11 = str11;
                                str12 = str12;
                            }
                            String str16 = string + "<br/>";
                            if (zIsDirectory2) {
                                str16 = str16 + "</b>";
                            }
                            str13 = str16;
                            i2++;
                            list = strArr;
                            str11 = str11;
                            file3 = file5;
                            strReplace = str14;
                            str12 = str12;
                            str10 = str10;
                        }
                    }
                    str2 = str10;
                    file2 = file3;
                    dVar2 = new d(this, HTTP_OK, MIME_HTML, str13 + "</body></html>");
                    str3 = str11;
                } else {
                    str2 = "";
                    file2 = file3;
                    str3 = MIME_PLAINTEXT;
                    dVar2 = new d(this, HTTP_FORBIDDEN, str3, "FORBIDDEN: No directory listing.");
                }
                str2 = "";
                str3 = MIME_PLAINTEXT;
            } else {
                str2 = "";
                str3 = MIME_PLAINTEXT;
            }
            if (dVar2 == null) {
                try {
                    iLastIndexOf = file3.getCanonicalPath().lastIndexOf(46);
                    if (iLastIndexOf >= 0) {
                        str5 = (String) f.get(file3.getCanonicalPath().substring(iLastIndexOf + 1).toLowerCase());
                    } else {
                        str5 = null;
                    }
                    if (str5 == null) {
                        str5 = MIME_DEFAULT_BINARY;
                    }
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(file3.getAbsolutePath());
                    sb3.append(file3.lastModified());
                    str6 = str2;
                    sb3.append(str6);
                    sb3.append(file3.length());
                    hexString = Integer.toHexString(sb3.toString().hashCode());
                    property = properties.getProperty("range");
                    if (property == null && property.startsWith("bytes=") && (iIndexOf = (property = property.substring(6)).indexOf(45)) > 0) {
                        try {
                            j2 = Long.parseLong(property.substring(0, iIndexOf));
                            try {
                                r16 = Long.parseLong(property.substring(iIndexOf + 1));
                            } catch (NumberFormatException unused) {
                                r16 = -1;
                            }
                        } catch (NumberFormatException unused2) {
                            j2 = 0;
                        }
                    } else {
                        j2 = 0;
                        r16 = -1;
                    }
                    length = file3.length();
                    str7 = "Content-Length";
                    str4 = HTTP_FORBIDDEN;
                    try {
                        if (property != null || j2 < 0) {
                            hexString = hexString;
                            if (hexString.equals(properties.getProperty("if-none-match"))) {
                                dVar2 = new d(this, HTTP_NOTMODIFIED, str5, str6);
                                dVar = dVar2;
                            } else {
                                dVar = new d(this, HTTP_OK, str5, new FileInputStream(file3));
                                str8 = str6 + length;
                            }
                        } else {
                            try {
                                if (j2 >= length) {
                                    dVar = new d(this, HTTP_RANGE_NOT_SATISFIABLE, str3, str6);
                                    dVar.a("Content-Range", "bytes 0-0/" + length);
                                } else {
                                    if (r16 < 0) {
                                        r17 = r16;
                                        r17 = length - 1;
                                    }
                                    r17 = r16;
                                    ?? r8 = r17;
                                    long j3 = (r8 - j2) + 1;
                                    long j4 = j3 < 0 ? 0L : j3;
                                    b bVar = new b(this, file3, j4);
                                    bVar.skip(j2);
                                    d dVar3 = new d(this, HTTP_PARTIALCONTENT, str5, bVar);
                                    dVar3.a("Content-Length", str6 + j4);
                                    str7 = "Content-Range";
                                    hexString = hexString;
                                    str8 = "bytes " + j2 + "-" + r8 + "/" + length;
                                    dVar = dVar3;
                                }
                                dVar.a("ETag", hexString);
                            } catch (IOException unused3) {
                                r16 = str3;
                                dVar = new d(this, str4, (String) r16, "FORBIDDEN: Reading file failed.");
                            }
                        }
                        dVar.a(str7, str8);
                        dVar.a("ETag", hexString);
                    } catch (IOException unused4) {
                    }
                } catch (IOException unused5) {
                    r16 = str3;
                    str4 = HTTP_FORBIDDEN;
                }
            } else {
                dVar = dVar2;
            }
            dVar.a("Accept-Ranges", "bytes");
            return dVar;
        }
        str2 = "";
        str3 = MIME_PLAINTEXT;
        file2 = file3;
        file3 = file2;
        if (dVar2 == null) {
            iLastIndexOf = file3.getCanonicalPath().lastIndexOf(46);
            if (iLastIndexOf >= 0) {
                str5 = (String) f.get(file3.getCanonicalPath().substring(iLastIndexOf + 1).toLowerCase());
            } else {
                str5 = null;
            }
            if (str5 == null) {
                str5 = MIME_DEFAULT_BINARY;
            }
            StringBuilder sb4 = new StringBuilder();
            sb4.append(file3.getAbsolutePath());
            sb4.append(file3.lastModified());
            str6 = str2;
            sb4.append(str6);
            sb4.append(file3.length());
            hexString = Integer.toHexString(sb4.toString().hashCode());
            property = properties.getProperty("range");
            if (property == null) {
                j2 = 0;
                r16 = -1;
            } else {
                j2 = 0;
                r16 = -1;
            }
            length = file3.length();
            str7 = "Content-Length";
            str4 = HTTP_FORBIDDEN;
            if (property != null) {
                hexString = hexString;
                if (hexString.equals(properties.getProperty("if-none-match"))) {
                    dVar2 = new d(this, HTTP_NOTMODIFIED, str5, str6);
                    dVar = dVar2;
                } else {
                    dVar = new d(this, HTTP_OK, str5, new FileInputStream(file3));
                    str8 = str6 + length;
                    dVar.a(str7, str8);
                }
            } else {
                hexString = hexString;
                if (hexString.equals(properties.getProperty("if-none-match"))) {
                    dVar2 = new d(this, HTTP_NOTMODIFIED, str5, str6);
                    dVar = dVar2;
                } else {
                    dVar = new d(this, HTTP_OK, str5, new FileInputStream(file3));
                    str8 = str6 + length;
                    dVar.a(str7, str8);
                }
            }
            dVar.a("ETag", hexString);
        } else {
            dVar = dVar2;
        }
        dVar.a("Accept-Ranges", "bytes");
        return dVar;
    }

    public void setBandwidth(int i2) {
        this.e = i2;
    }

    public void stop() {
        try {
            this.b.close();
            this.c.join();
        } catch (IOException | InterruptedException unused) {
        }
    }
}
