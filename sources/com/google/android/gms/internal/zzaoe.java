package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.util.zze;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaoe extends zzany {
    private static final Set<String> zzb = Collections.synchronizedSet(new HashSet());
    private static final DecimalFormat zzc = new DecimalFormat("#,###");
    private File zzd;
    private boolean zze;

    public zzaoe(zzann zzannVar) {
        super(zzannVar);
        File cacheDir = this.zza.getCacheDir();
        if (cacheDir == null) {
            zzahw.zze("Context.getCacheDir() returned null");
            return;
        }
        File file = new File(cacheDir, "admobVideoStreams");
        this.zzd = file;
        if (!file.isDirectory() && !this.zzd.mkdirs()) {
            String strValueOf = String.valueOf(this.zzd.getAbsolutePath());
            zzahw.zze(strValueOf.length() != 0 ? "Could not create preload cache directory at ".concat(strValueOf) : new String("Could not create preload cache directory at "));
            this.zzd = null;
        } else {
            if (this.zzd.setReadable(true, false) && this.zzd.setExecutable(true, false)) {
                return;
            }
            String strValueOf2 = String.valueOf(this.zzd.getAbsolutePath());
            zzahw.zze(strValueOf2.length() != 0 ? "Could not set cache file permissions at ".concat(strValueOf2) : new String("Could not set cache file permissions at "));
            this.zzd = null;
        }
    }

    private final File zza(File file) {
        return new File(this.zzd, String.valueOf(file.getName()).concat(".done"));
    }

    @Override // com.google.android.gms.internal.zzany
    public final void zza() {
        this.zze = true;
    }

    /* JADX WARN: Code duplicated, block: B:104:0x02c2  */
    /* JADX WARN: Code duplicated, block: B:108:0x02d8 A[Catch: RuntimeException -> 0x02f2, IOException | RuntimeException -> 0x02f4, TryCatch #29 {IOException | RuntimeException -> 0x02f4, blocks: (B:106:0x02c6, B:108:0x02d8, B:109:0x02de), top: B:241:0x02c6 }] */
    /* JADX WARN: Code duplicated, block: B:109:0x02de A[Catch: RuntimeException -> 0x02f2, IOException | RuntimeException -> 0x02f4, TRY_LEAVE, TryCatch #29 {IOException | RuntimeException -> 0x02f4, blocks: (B:106:0x02c6, B:108:0x02d8, B:109:0x02de), top: B:241:0x02c6 }] */
    /* JADX WARN: Code duplicated, block: B:126:0x0315 A[Catch: IOException | RuntimeException -> 0x03cf, IOException -> 0x03d1, TryCatch #26 {IOException | RuntimeException -> 0x03cf, blocks: (B:121:0x02fb, B:122:0x02fe, B:124:0x0304, B:126:0x0315, B:128:0x031b, B:130:0x0321), top: B:246:0x02fb }] */
    /* JADX WARN: Code duplicated, block: B:128:0x031b A[Catch: IOException | RuntimeException -> 0x03cf, IOException -> 0x03d1, TryCatch #26 {IOException | RuntimeException -> 0x03cf, blocks: (B:121:0x02fb, B:122:0x02fe, B:124:0x0304, B:126:0x0315, B:128:0x031b, B:130:0x0321), top: B:246:0x02fb }] */
    /* JADX WARN: Code duplicated, block: B:130:0x0321 A[Catch: IOException | RuntimeException -> 0x03cf, IOException -> 0x03d1, TRY_LEAVE, TryCatch #26 {IOException | RuntimeException -> 0x03cf, blocks: (B:121:0x02fb, B:122:0x02fe, B:124:0x0304, B:126:0x0315, B:128:0x031b, B:130:0x0321), top: B:246:0x02fb }] */
    /* JADX WARN: Code duplicated, block: B:139:0x0357  */
    /* JADX WARN: Code duplicated, block: B:164:0x03ea A[Catch: RuntimeException -> 0x0421, IOException -> 0x0423, TRY_ENTER, TRY_LEAVE, TryCatch #8 {RuntimeException -> 0x0421, blocks: (B:134:0x0347, B:164:0x03ea, B:174:0x0435, B:176:0x043d), top: B:230:0x0347 }] */
    /* JADX WARN: Code duplicated, block: B:174:0x0435 A[Catch: RuntimeException -> 0x0421, IOException -> 0x0423, TRY_ENTER, TRY_LEAVE, TryCatch #8 {RuntimeException -> 0x0421, blocks: (B:134:0x0347, B:164:0x03ea, B:174:0x0435, B:176:0x043d), top: B:230:0x0347 }] */
    /* JADX WARN: Code duplicated, block: B:208:0x047e  */
    /* JADX WARN: Code duplicated, block: B:20:0x0048  */
    /* JADX WARN: Code duplicated, block: B:212:0x048e  */
    /* JADX WARN: Code duplicated, block: B:213:0x04b2  */
    /* JADX WARN: Code duplicated, block: B:216:0x04db  */
    /* JADX WARN: Code duplicated, block: B:220:0x04f1  */
    /* JADX WARN: Code duplicated, block: B:221:0x04f6  */
    /* JADX WARN: Code duplicated, block: B:231:0x043d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:241:0x02c6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:246:0x02fb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:270:0x03da A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:271:0x038b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:272:0x037b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:276:? A[LOOP:3: B:122:0x02fe->B:276:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:84:0x01b1 A[Catch: IOException | RuntimeException -> 0x01a3, IOException -> 0x01a5, TRY_ENTER, TryCatch #22 {IOException | RuntimeException -> 0x01a3, blocks: (B:65:0x014c, B:67:0x0157, B:69:0x0169, B:70:0x016f, B:84:0x01b1, B:86:0x01bd, B:88:0x01c8, B:87:0x01c2, B:92:0x01f3, B:94:0x022e, B:96:0x0239, B:95:0x0233), top: B:253:0x014c }] */
    /* JADX WARN: Code duplicated, block: B:86:0x01bd A[Catch: IOException | RuntimeException -> 0x01a3, IOException -> 0x01a5, TryCatch #22 {IOException | RuntimeException -> 0x01a3, blocks: (B:65:0x014c, B:67:0x0157, B:69:0x0169, B:70:0x016f, B:84:0x01b1, B:86:0x01bd, B:88:0x01c8, B:87:0x01c2, B:92:0x01f3, B:94:0x022e, B:96:0x0239, B:95:0x0233), top: B:253:0x014c }] */
    /* JADX WARN: Code duplicated, block: B:87:0x01c2 A[Catch: IOException | RuntimeException -> 0x01a3, IOException -> 0x01a5, TryCatch #22 {IOException | RuntimeException -> 0x01a3, blocks: (B:65:0x014c, B:67:0x0157, B:69:0x0169, B:70:0x016f, B:84:0x01b1, B:86:0x01bd, B:88:0x01c8, B:87:0x01c2, B:92:0x01f3, B:94:0x022e, B:96:0x0239, B:95:0x0233), top: B:253:0x014c }] */
    /* JADX WARN: Code duplicated, block: B:90:0x01da A[Catch: RuntimeException -> 0x0471, IOException | RuntimeException -> 0x0473, TRY_ENTER, TRY_LEAVE, TryCatch #30 {IOException | RuntimeException -> 0x0473, blocks: (B:63:0x0131, B:82:0x01ab, B:90:0x01da, B:98:0x0248), top: B:239:0x0131 }] */
    /* JADX WARN: Code duplicated, block: B:92:0x01f3 A[Catch: IOException | RuntimeException -> 0x01a3, IOException -> 0x01a5, TRY_ENTER, TryCatch #22 {IOException | RuntimeException -> 0x01a3, blocks: (B:65:0x014c, B:67:0x0157, B:69:0x0169, B:70:0x016f, B:84:0x01b1, B:86:0x01bd, B:88:0x01c8, B:87:0x01c2, B:92:0x01f3, B:94:0x022e, B:96:0x0239, B:95:0x0233), top: B:253:0x014c }] */
    /* JADX WARN: Code duplicated, block: B:94:0x022e A[Catch: IOException | RuntimeException -> 0x01a3, IOException -> 0x01a5, TryCatch #22 {IOException | RuntimeException -> 0x01a3, blocks: (B:65:0x014c, B:67:0x0157, B:69:0x0169, B:70:0x016f, B:84:0x01b1, B:86:0x01bd, B:88:0x01c8, B:87:0x01c2, B:92:0x01f3, B:94:0x022e, B:96:0x0239, B:95:0x0233), top: B:253:0x014c }] */
    /* JADX WARN: Code duplicated, block: B:95:0x0233 A[Catch: IOException | RuntimeException -> 0x01a3, IOException -> 0x01a5, TryCatch #22 {IOException | RuntimeException -> 0x01a3, blocks: (B:65:0x014c, B:67:0x0157, B:69:0x0169, B:70:0x016f, B:84:0x01b1, B:86:0x01bd, B:88:0x01c8, B:87:0x01c2, B:92:0x01f3, B:94:0x022e, B:96:0x0239, B:95:0x0233), top: B:253:0x014c }] */
    /* JADX WARN: Code duplicated, block: B:98:0x0248 A[Catch: RuntimeException -> 0x0471, IOException | RuntimeException -> 0x0473, TRY_ENTER, TRY_LEAVE, TryCatch #30 {IOException | RuntimeException -> 0x0473, blocks: (B:63:0x0131, B:82:0x01ab, B:90:0x01da, B:98:0x0248), top: B:239:0x0131 }] */
    @Override // com.google.android.gms.internal.zzany
    public final boolean zza(String str) {
        int i;
        String str2;
        Throwable th;
        String str3;
        String strValueOf;
        String str4;
        int contentLength;
        String str5;
        int iIntValue;
        ReadableByteChannel readableByteChannelNewChannel;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2;
        FileChannel channel;
        ByteBuffer byteBufferAllocate;
        zze zzeVarZzl;
        long jZza;
        zzake zzakeVar;
        long jLongValue;
        int i2;
        String str6;
        String str7;
        ByteBuffer byteBuffer;
        String strValueOf2;
        String str8;
        String strValueOf3;
        String str9;
        String strValueOf4;
        String str10;
        boolean zDelete;
        String str11;
        FileOutputStream fileOutputStream3 = null;
        int i3 = 0;
        if (this.zzd == null) {
            str11 = "noCacheDir";
        } else {
            do {
                File file = this.zzd;
                if (file == null) {
                    i = 0;
                } else {
                    i = 0;
                    for (File file2 : file.listFiles()) {
                        if (!file2.getName().endsWith(".done")) {
                            i++;
                        }
                    }
                }
                if (i <= ((Integer) zzlc.zzf().zza(zzoi.zzj)).intValue()) {
                    zzlc.zza();
                    File file3 = new File(this.zzd, zzako.zza(str));
                    File fileZza = zza(file3);
                    if (file3.isFile() && fileZza.isFile()) {
                        int length = (int) file3.length();
                        String strValueOf5 = String.valueOf(str);
                        zzahw.zzb(strValueOf5.length() != 0 ? "Stream cache hit at ".concat(strValueOf5) : new String("Stream cache hit at "));
                        zza(str, file3.getAbsolutePath(), length);
                        return true;
                    }
                    String strValueOf6 = String.valueOf(this.zzd.getAbsolutePath());
                    String strValueOf7 = String.valueOf(str);
                    String strConcat = strValueOf7.length() != 0 ? strValueOf6.concat(strValueOf7) : new String(strValueOf6);
                    synchronized (zzb) {
                        if (zzb.contains(strConcat)) {
                            String strValueOf8 = String.valueOf(str);
                            zzahw.zze(strValueOf8.length() != 0 ? "Stream cache already in progress at ".concat(strValueOf8) : new String("Stream cache already in progress at "));
                            zza(str, file3.getAbsolutePath(), "inProgress", null);
                            return false;
                        }
                        zzb.add(strConcat);
                        String str12 = "error";
                        try {
                            zzbt.zzq();
                            HttpURLConnection httpURLConnectionZza = zzame.zza(str, ((Integer) zzlc.zzf().zza(zzoi.zzo)).intValue());
                            if (httpURLConnectionZza instanceof HttpURLConnection) {
                                try {
                                    int responseCode = httpURLConnectionZza.getResponseCode();
                                    if (responseCode >= 400) {
                                        str12 = "badUrl";
                                        String strValueOf9 = String.valueOf(Integer.toString(responseCode));
                                        String strConcat2 = strValueOf9.length() != 0 ? "HTTP request failed. Code: ".concat(strValueOf9) : new String("HTTP request failed. Code: ");
                                        try {
                                            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 32);
                                            sb.append("HTTP status code ");
                                            sb.append(responseCode);
                                            sb.append(" at ");
                                            sb.append(str);
                                            throw new IOException(sb.toString());
                                        } catch (IOException | RuntimeException e) {
                                            th = e;
                                            str3 = strConcat2;
                                            str2 = strConcat;
                                            if (th instanceof RuntimeException) {
                                                zzbt.zzi().zza(th, "VideoStreamFullFileCache.preload");
                                            }
                                            fileOutputStream3.close();
                                            if (this.zze) {
                                                StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 26);
                                                sb2.append("Preload aborted for URL \"");
                                                sb2.append(str);
                                                sb2.append("\"");
                                                zzahw.zzd(sb2.toString());
                                            } else {
                                                StringBuilder sb3 = new StringBuilder(String.valueOf(str).length() + 25);
                                                sb3.append("Preload failed for URL \"");
                                                sb3.append(str);
                                                sb3.append("\"");
                                                zzahw.zzc(sb3.toString(), th);
                                            }
                                            if (file3.exists()) {
                                                strValueOf = String.valueOf(file3.getAbsolutePath());
                                                if (strValueOf.length() != 0) {
                                                    str4 = "Could not delete partial cache file at ".concat(strValueOf);
                                                } else {
                                                    str4 = new String("Could not delete partial cache file at ");
                                                }
                                                zzahw.zze(str4);
                                            }
                                            zza(str, file3.getAbsolutePath(), str12, str3);
                                            zzb.remove(str2);
                                            return false;
                                        }
                                    }
                                    contentLength = httpURLConnectionZza.getContentLength();
                                    if (contentLength < 0) {
                                        strValueOf4 = String.valueOf(str);
                                        if (strValueOf4.length() != 0) {
                                            str10 = "Stream cache aborted, missing content-length header at ".concat(strValueOf4);
                                        } else {
                                            str10 = new String("Stream cache aborted, missing content-length header at ");
                                        }
                                        zzahw.zze(str10);
                                        zza(str, file3.getAbsolutePath(), "contentLengthMissing", null);
                                        zzb.remove(strConcat);
                                        return false;
                                    }
                                    str5 = zzc.format(contentLength);
                                    iIntValue = ((Integer) zzlc.zzf().zza(zzoi.zzk)).intValue();
                                    if (contentLength > iIntValue) {
                                        StringBuilder sb4 = new StringBuilder(String.valueOf(str5).length() + 33 + String.valueOf(str).length());
                                        sb4.append("Content length ");
                                        sb4.append(str5);
                                        sb4.append(" exceeds limit at ");
                                        sb4.append(str);
                                        zzahw.zze(sb4.toString());
                                        strValueOf3 = String.valueOf(str5);
                                        if (strValueOf3.length() != 0) {
                                            str9 = "File too big for full file cache. Size: ".concat(strValueOf3);
                                        } else {
                                            str9 = new String("File too big for full file cache. Size: ");
                                        }
                                        zza(str, file3.getAbsolutePath(), "sizeExceeded", str9);
                                        zzb.remove(strConcat);
                                        return false;
                                    }
                                    StringBuilder sb5 = new StringBuilder(String.valueOf(str5).length() + 20 + String.valueOf(str).length());
                                    sb5.append("Caching ");
                                    sb5.append(str5);
                                    sb5.append(" bytes from ");
                                    sb5.append(str);
                                    zzahw.zzb(sb5.toString());
                                    readableByteChannelNewChannel = Channels.newChannel(httpURLConnectionZza.getInputStream());
                                    fileOutputStream = new FileOutputStream(file3);
                                    try {
                                        channel = fileOutputStream.getChannel();
                                        byteBufferAllocate = ByteBuffer.allocate(1048576);
                                        zzeVarZzl = zzbt.zzl();
                                        jZza = zzeVarZzl.zza();
                                        str2 = strConcat;
                                        try {
                                            zzakeVar = new zzake(((Long) zzlc.zzf().zza(zzoi.zzn)).longValue());
                                            jLongValue = ((Long) zzlc.zzf().zza(zzoi.zzm)).longValue();
                                            while (true) {
                                                i2 = readableByteChannelNewChannel.read(byteBufferAllocate);
                                                if (i2 < 0) {
                                                    i3 += i2;
                                                    if (i3 > iIntValue) {
                                                        try {
                                                            str12 = "sizeExceeded";
                                                            strValueOf2 = String.valueOf(Integer.toString(i3));
                                                            if (strValueOf2.length() != 0) {
                                                                str8 = "File too big for full file cache. Size: ".concat(strValueOf2);
                                                            } else {
                                                                str8 = new String("File too big for full file cache. Size: ");
                                                            }
                                                            try {
                                                                throw new IOException("stream cache file size limit exceeded");
                                                            } catch (IOException | RuntimeException e2) {
                                                                th = e2;
                                                                str3 = str8;
                                                                fileOutputStream3 = fileOutputStream;
                                                                if (th instanceof RuntimeException) {
                                                                    zzbt.zzi().zza(th, "VideoStreamFullFileCache.preload");
                                                                }
                                                                fileOutputStream3.close();
                                                                if (this.zze) {
                                                                    StringBuilder sb6 = new StringBuilder(String.valueOf(str).length() + 26);
                                                                    sb6.append("Preload aborted for URL \"");
                                                                    sb6.append(str);
                                                                    sb6.append("\"");
                                                                    zzahw.zzd(sb6.toString());
                                                                } else {
                                                                    StringBuilder sb7 = new StringBuilder(String.valueOf(str).length() + 25);
                                                                    sb7.append("Preload failed for URL \"");
                                                                    sb7.append(str);
                                                                    sb7.append("\"");
                                                                    zzahw.zzc(sb7.toString(), th);
                                                                }
                                                                if (file3.exists()) {
                                                                    strValueOf = String.valueOf(file3.getAbsolutePath());
                                                                    if (strValueOf.length() != 0) {
                                                                        str4 = "Could not delete partial cache file at ".concat(strValueOf);
                                                                    } else {
                                                                        str4 = new String("Could not delete partial cache file at ");
                                                                    }
                                                                    zzahw.zze(str4);
                                                                }
                                                                zza(str, file3.getAbsolutePath(), str12, str3);
                                                                zzb.remove(str2);
                                                                return false;
                                                            }
                                                        } catch (IOException | RuntimeException e3) {
                                                            th = e3;
                                                            str3 = null;
                                                        }
                                                    } else {
                                                        try {
                                                            byteBufferAllocate.flip();
                                                            while (channel.write(byteBufferAllocate) > 0) {
                                                            }
                                                            byteBufferAllocate.clear();
                                                            try {
                                                                if (zzeVarZzl.zza() - jZza > 1000 * jLongValue) {
                                                                    byteBuffer = byteBufferAllocate;
                                                                    if (!this.zze) {
                                                                        throw new IOException("abort requested");
                                                                    }
                                                                    if (zzakeVar.zza()) {
                                                                        str7 = str12;
                                                                        try {
                                                                            str6 = str2;
                                                                            fileOutputStream2 = fileOutputStream;
                                                                            try {
                                                                                try {
                                                                                    zzako.zza.post(new zzanz(this, str, file3.getAbsolutePath(), i3, contentLength, false));
                                                                                } catch (RuntimeException e4) {
                                                                                    e = e4;
                                                                                    th = e;
                                                                                    str12 = str7;
                                                                                    str2 = str6;
                                                                                    fileOutputStream3 = fileOutputStream2;
                                                                                    str3 = null;
                                                                                    if (th instanceof RuntimeException) {
                                                                                        zzbt.zzi().zza(th, "VideoStreamFullFileCache.preload");
                                                                                    }
                                                                                    fileOutputStream3.close();
                                                                                    if (this.zze) {
                                                                                        StringBuilder sb8 = new StringBuilder(String.valueOf(str).length() + 26);
                                                                                        sb8.append("Preload aborted for URL \"");
                                                                                        sb8.append(str);
                                                                                        sb8.append("\"");
                                                                                        zzahw.zzd(sb8.toString());
                                                                                    } else {
                                                                                        StringBuilder sb9 = new StringBuilder(String.valueOf(str).length() + 25);
                                                                                        sb9.append("Preload failed for URL \"");
                                                                                        sb9.append(str);
                                                                                        sb9.append("\"");
                                                                                        zzahw.zzc(sb9.toString(), th);
                                                                                    }
                                                                                    if (file3.exists()) {
                                                                                        strValueOf = String.valueOf(file3.getAbsolutePath());
                                                                                        if (strValueOf.length() != 0) {
                                                                                            str4 = "Could not delete partial cache file at ".concat(strValueOf);
                                                                                        } else {
                                                                                            str4 = new String("Could not delete partial cache file at ");
                                                                                        }
                                                                                        zzahw.zze(str4);
                                                                                    }
                                                                                    zza(str, file3.getAbsolutePath(), str12, str3);
                                                                                    zzb.remove(str2);
                                                                                    return false;
                                                                                }
                                                                            } catch (IOException e5) {
                                                                                e = e5;
                                                                                th = e;
                                                                                str12 = str7;
                                                                                str2 = str6;
                                                                                fileOutputStream3 = fileOutputStream2;
                                                                                str3 = null;
                                                                                if (th instanceof RuntimeException) {
                                                                                    zzbt.zzi().zza(th, "VideoStreamFullFileCache.preload");
                                                                                }
                                                                                fileOutputStream3.close();
                                                                                if (this.zze) {
                                                                                    StringBuilder sb10 = new StringBuilder(String.valueOf(str).length() + 26);
                                                                                    sb10.append("Preload aborted for URL \"");
                                                                                    sb10.append(str);
                                                                                    sb10.append("\"");
                                                                                    zzahw.zzd(sb10.toString());
                                                                                } else {
                                                                                    StringBuilder sb11 = new StringBuilder(String.valueOf(str).length() + 25);
                                                                                    sb11.append("Preload failed for URL \"");
                                                                                    sb11.append(str);
                                                                                    sb11.append("\"");
                                                                                    zzahw.zzc(sb11.toString(), th);
                                                                                }
                                                                                if (file3.exists()) {
                                                                                    strValueOf = String.valueOf(file3.getAbsolutePath());
                                                                                    if (strValueOf.length() != 0) {
                                                                                        str4 = "Could not delete partial cache file at ".concat(strValueOf);
                                                                                    } else {
                                                                                        str4 = new String("Could not delete partial cache file at ");
                                                                                    }
                                                                                    zzahw.zze(str4);
                                                                                }
                                                                                zza(str, file3.getAbsolutePath(), str12, str3);
                                                                                zzb.remove(str2);
                                                                                return false;
                                                                            }
                                                                        } catch (IOException e6) {
                                                                            e = e6;
                                                                            fileOutputStream2 = fileOutputStream;
                                                                            th = e;
                                                                            str2 = str2;
                                                                            str12 = str7;
                                                                            fileOutputStream3 = fileOutputStream2;
                                                                            str3 = null;
                                                                            if (th instanceof RuntimeException) {
                                                                                zzbt.zzi().zza(th, "VideoStreamFullFileCache.preload");
                                                                            }
                                                                            fileOutputStream3.close();
                                                                            if (this.zze) {
                                                                                StringBuilder sb12 = new StringBuilder(String.valueOf(str).length() + 26);
                                                                                sb12.append("Preload aborted for URL \"");
                                                                                sb12.append(str);
                                                                                sb12.append("\"");
                                                                                zzahw.zzd(sb12.toString());
                                                                            } else {
                                                                                StringBuilder sb13 = new StringBuilder(String.valueOf(str).length() + 25);
                                                                                sb13.append("Preload failed for URL \"");
                                                                                sb13.append(str);
                                                                                sb13.append("\"");
                                                                                zzahw.zzc(sb13.toString(), th);
                                                                            }
                                                                            if (file3.exists()) {
                                                                                strValueOf = String.valueOf(file3.getAbsolutePath());
                                                                                if (strValueOf.length() != 0) {
                                                                                    str4 = "Could not delete partial cache file at ".concat(strValueOf);
                                                                                } else {
                                                                                    str4 = new String("Could not delete partial cache file at ");
                                                                                }
                                                                                zzahw.zze(str4);
                                                                            }
                                                                            zza(str, file3.getAbsolutePath(), str12, str3);
                                                                            zzb.remove(str2);
                                                                            return false;
                                                                        } catch (RuntimeException e7) {
                                                                            e = e7;
                                                                            fileOutputStream2 = fileOutputStream;
                                                                            th = e;
                                                                            str2 = str2;
                                                                            str12 = str7;
                                                                            fileOutputStream3 = fileOutputStream2;
                                                                            str3 = null;
                                                                            if (th instanceof RuntimeException) {
                                                                                zzbt.zzi().zza(th, "VideoStreamFullFileCache.preload");
                                                                            }
                                                                            fileOutputStream3.close();
                                                                            if (this.zze) {
                                                                                StringBuilder sb14 = new StringBuilder(String.valueOf(str).length() + 26);
                                                                                sb14.append("Preload aborted for URL \"");
                                                                                sb14.append(str);
                                                                                sb14.append("\"");
                                                                                zzahw.zzd(sb14.toString());
                                                                            } else {
                                                                                StringBuilder sb15 = new StringBuilder(String.valueOf(str).length() + 25);
                                                                                sb15.append("Preload failed for URL \"");
                                                                                sb15.append(str);
                                                                                sb15.append("\"");
                                                                                zzahw.zzc(sb15.toString(), th);
                                                                            }
                                                                            if (file3.exists()) {
                                                                                strValueOf = String.valueOf(file3.getAbsolutePath());
                                                                                if (strValueOf.length() != 0) {
                                                                                    str4 = "Could not delete partial cache file at ".concat(strValueOf);
                                                                                } else {
                                                                                    str4 = new String("Could not delete partial cache file at ");
                                                                                }
                                                                                zzahw.zze(str4);
                                                                            }
                                                                            zza(str, file3.getAbsolutePath(), str12, str3);
                                                                            zzb.remove(str2);
                                                                            return false;
                                                                        }
                                                                    } else {
                                                                        fileOutputStream2 = fileOutputStream;
                                                                        str6 = str2;
                                                                        str7 = str12;
                                                                    }
                                                                    byteBufferAllocate = byteBuffer;
                                                                    readableByteChannelNewChannel = readableByteChannelNewChannel;
                                                                    str12 = str7;
                                                                    zzakeVar = zzakeVar;
                                                                    str2 = str6;
                                                                    channel = channel;
                                                                    fileOutputStream = fileOutputStream2;
                                                                    iIntValue = iIntValue;
                                                                    contentLength = contentLength;
                                                                } else {
                                                                    FileOutputStream fileOutputStream4 = fileOutputStream;
                                                                    String str13 = str2;
                                                                    str12 = "downloadTimeout";
                                                                    String string = Long.toString(jLongValue);
                                                                    StringBuilder sb16 = new StringBuilder(String.valueOf(string).length() + 29);
                                                                    sb16.append("Timeout exceeded. Limit: ");
                                                                    sb16.append(string);
                                                                    sb16.append(" sec");
                                                                    String string2 = sb16.toString();
                                                                    try {
                                                                        throw new IOException("stream cache time limit exceeded");
                                                                    } catch (IOException | RuntimeException e8) {
                                                                        th = e8;
                                                                        str3 = string2;
                                                                        str2 = str13;
                                                                        fileOutputStream3 = fileOutputStream4;
                                                                    }
                                                                }
                                                            } catch (IOException | RuntimeException e9) {
                                                                th = e9;
                                                                str2 = str6;
                                                                fileOutputStream3 = fileOutputStream2;
                                                                str3 = null;
                                                            }
                                                        } catch (IOException | RuntimeException e10) {
                                                            fileOutputStream2 = fileOutputStream;
                                                            th = e10;
                                                            str2 = str2;
                                                            fileOutputStream3 = fileOutputStream2;
                                                            str3 = null;
                                                        }
                                                    }
                                                } else {
                                                    fileOutputStream2 = fileOutputStream;
                                                    str6 = str2;
                                                    str7 = str12;
                                                    try {
                                                        fileOutputStream2.close();
                                                        if (zzahw.zza(3)) {
                                                            String str14 = zzc.format(i3);
                                                            StringBuilder sb17 = new StringBuilder(String.valueOf(str14).length() + 22 + String.valueOf(str).length());
                                                            sb17.append("Preloaded ");
                                                            sb17.append(str14);
                                                            sb17.append(" bytes from ");
                                                            sb17.append(str);
                                                            zzahw.zzb(sb17.toString());
                                                        }
                                                        file3.setReadable(true, false);
                                                        if (fileZza.isFile()) {
                                                            fileZza.setLastModified(System.currentTimeMillis());
                                                        } else {
                                                            try {
                                                                fileZza.createNewFile();
                                                            } catch (IOException unused) {
                                                            }
                                                        }
                                                        zza(str, file3.getAbsolutePath(), i3);
                                                        str2 = str6;
                                                        try {
                                                            zzb.remove(str2);
                                                            return true;
                                                        } catch (IOException e11) {
                                                            e = e11;
                                                            th = e;
                                                            str12 = str7;
                                                            fileOutputStream3 = fileOutputStream2;
                                                            str3 = null;
                                                            if (th instanceof RuntimeException) {
                                                                zzbt.zzi().zza(th, "VideoStreamFullFileCache.preload");
                                                            }
                                                            fileOutputStream3.close();
                                                            if (this.zze) {
                                                                StringBuilder sb18 = new StringBuilder(String.valueOf(str).length() + 26);
                                                                sb18.append("Preload aborted for URL \"");
                                                                sb18.append(str);
                                                                sb18.append("\"");
                                                                zzahw.zzd(sb18.toString());
                                                            } else {
                                                                StringBuilder sb19 = new StringBuilder(String.valueOf(str).length() + 25);
                                                                sb19.append("Preload failed for URL \"");
                                                                sb19.append(str);
                                                                sb19.append("\"");
                                                                zzahw.zzc(sb19.toString(), th);
                                                            }
                                                            if (file3.exists()) {
                                                                strValueOf = String.valueOf(file3.getAbsolutePath());
                                                                if (strValueOf.length() != 0) {
                                                                    str4 = "Could not delete partial cache file at ".concat(strValueOf);
                                                                } else {
                                                                    str4 = new String("Could not delete partial cache file at ");
                                                                }
                                                                zzahw.zze(str4);
                                                            }
                                                            zza(str, file3.getAbsolutePath(), str12, str3);
                                                            zzb.remove(str2);
                                                            return false;
                                                        } catch (RuntimeException e12) {
                                                            e = e12;
                                                            th = e;
                                                            str12 = str7;
                                                            fileOutputStream3 = fileOutputStream2;
                                                            str3 = null;
                                                            if (th instanceof RuntimeException) {
                                                                zzbt.zzi().zza(th, "VideoStreamFullFileCache.preload");
                                                            }
                                                            fileOutputStream3.close();
                                                            if (this.zze) {
                                                                StringBuilder sb110 = new StringBuilder(String.valueOf(str).length() + 26);
                                                                sb110.append("Preload aborted for URL \"");
                                                                sb110.append(str);
                                                                sb110.append("\"");
                                                                zzahw.zzd(sb110.toString());
                                                            } else {
                                                                StringBuilder sb111 = new StringBuilder(String.valueOf(str).length() + 25);
                                                                sb111.append("Preload failed for URL \"");
                                                                sb111.append(str);
                                                                sb111.append("\"");
                                                                zzahw.zzc(sb111.toString(), th);
                                                            }
                                                            if (file3.exists()) {
                                                                strValueOf = String.valueOf(file3.getAbsolutePath());
                                                                if (strValueOf.length() != 0) {
                                                                    str4 = "Could not delete partial cache file at ".concat(strValueOf);
                                                                } else {
                                                                    str4 = new String("Could not delete partial cache file at ");
                                                                }
                                                                zzahw.zze(str4);
                                                            }
                                                            zza(str, file3.getAbsolutePath(), str12, str3);
                                                            zzb.remove(str2);
                                                            return false;
                                                        }
                                                    } catch (IOException | RuntimeException e13) {
                                                        e = e13;
                                                        str2 = str6;
                                                    }
                                                }
                                                str12 = str7;
                                                fileOutputStream3 = fileOutputStream2;
                                                str3 = null;
                                            }
                                        } catch (IOException | RuntimeException e14) {
                                            e = e14;
                                            fileOutputStream2 = fileOutputStream;
                                            str2 = str2;
                                            th = e;
                                        }
                                    } catch (IOException | RuntimeException e15) {
                                        e = e15;
                                        fileOutputStream2 = fileOutputStream;
                                        str2 = strConcat;
                                    }
                                } catch (IOException | RuntimeException e16) {
                                    th = e16;
                                    str3 = null;
                                }
                            } else {
                                contentLength = httpURLConnectionZza.getContentLength();
                                if (contentLength < 0) {
                                    strValueOf4 = String.valueOf(str);
                                    if (strValueOf4.length() != 0) {
                                        str10 = "Stream cache aborted, missing content-length header at ".concat(strValueOf4);
                                    } else {
                                        str10 = new String("Stream cache aborted, missing content-length header at ");
                                    }
                                    zzahw.zze(str10);
                                    zza(str, file3.getAbsolutePath(), "contentLengthMissing", null);
                                    zzb.remove(strConcat);
                                    return false;
                                }
                                str5 = zzc.format(contentLength);
                                iIntValue = ((Integer) zzlc.zzf().zza(zzoi.zzk)).intValue();
                                if (contentLength > iIntValue) {
                                    StringBuilder sb20 = new StringBuilder(String.valueOf(str5).length() + 33 + String.valueOf(str).length());
                                    sb20.append("Content length ");
                                    sb20.append(str5);
                                    sb20.append(" exceeds limit at ");
                                    sb20.append(str);
                                    zzahw.zze(sb20.toString());
                                    strValueOf3 = String.valueOf(str5);
                                    if (strValueOf3.length() != 0) {
                                        str9 = "File too big for full file cache. Size: ".concat(strValueOf3);
                                    } else {
                                        str9 = new String("File too big for full file cache. Size: ");
                                    }
                                    zza(str, file3.getAbsolutePath(), "sizeExceeded", str9);
                                    zzb.remove(strConcat);
                                    return false;
                                }
                                StringBuilder sb21 = new StringBuilder(String.valueOf(str5).length() + 20 + String.valueOf(str).length());
                                sb21.append("Caching ");
                                sb21.append(str5);
                                sb21.append(" bytes from ");
                                sb21.append(str);
                                zzahw.zzb(sb21.toString());
                                readableByteChannelNewChannel = Channels.newChannel(httpURLConnectionZza.getInputStream());
                                fileOutputStream = new FileOutputStream(file3);
                                channel = fileOutputStream.getChannel();
                                byteBufferAllocate = ByteBuffer.allocate(1048576);
                                zzeVarZzl = zzbt.zzl();
                                jZza = zzeVarZzl.zza();
                                str2 = strConcat;
                                zzakeVar = new zzake(((Long) zzlc.zzf().zza(zzoi.zzn)).longValue());
                                jLongValue = ((Long) zzlc.zzf().zza(zzoi.zzm)).longValue();
                                while (true) {
                                    i2 = readableByteChannelNewChannel.read(byteBufferAllocate);
                                    if (i2 < 0) {
                                        fileOutputStream2 = fileOutputStream;
                                        str6 = str2;
                                        str7 = str12;
                                        fileOutputStream2.close();
                                        if (zzahw.zza(3)) {
                                            String str15 = zzc.format(i3);
                                            StringBuilder sb112 = new StringBuilder(String.valueOf(str15).length() + 22 + String.valueOf(str).length());
                                            sb112.append("Preloaded ");
                                            sb112.append(str15);
                                            sb112.append(" bytes from ");
                                            sb112.append(str);
                                            zzahw.zzb(sb112.toString());
                                        }
                                        file3.setReadable(true, false);
                                        if (fileZza.isFile()) {
                                            fileZza.setLastModified(System.currentTimeMillis());
                                        } else {
                                            fileZza.createNewFile();
                                        }
                                        zza(str, file3.getAbsolutePath(), i3);
                                        str2 = str6;
                                        zzb.remove(str2);
                                        return true;
                                    }
                                    i3 += i2;
                                    if (i3 > iIntValue) {
                                        str12 = "sizeExceeded";
                                        strValueOf2 = String.valueOf(Integer.toString(i3));
                                        if (strValueOf2.length() != 0) {
                                            str8 = "File too big for full file cache. Size: ".concat(strValueOf2);
                                        } else {
                                            str8 = new String("File too big for full file cache. Size: ");
                                        }
                                        throw new IOException("stream cache file size limit exceeded");
                                    }
                                    byteBufferAllocate.flip();
                                    while (channel.write(byteBufferAllocate) > 0) {
                                    }
                                    byteBufferAllocate.clear();
                                    if (zzeVarZzl.zza() - jZza > 1000 * jLongValue) {
                                        FileOutputStream fileOutputStream5 = fileOutputStream;
                                        String str16 = str2;
                                        str12 = "downloadTimeout";
                                        String string3 = Long.toString(jLongValue);
                                        StringBuilder sb113 = new StringBuilder(String.valueOf(string3).length() + 29);
                                        sb113.append("Timeout exceeded. Limit: ");
                                        sb113.append(string3);
                                        sb113.append(" sec");
                                        String string4 = sb113.toString();
                                        throw new IOException("stream cache time limit exceeded");
                                    }
                                    byteBuffer = byteBufferAllocate;
                                    if (!this.zze) {
                                        throw new IOException("abort requested");
                                    }
                                    if (zzakeVar.zza()) {
                                        str7 = str12;
                                        str6 = str2;
                                        fileOutputStream2 = fileOutputStream;
                                        zzako.zza.post(new zzanz(this, str, file3.getAbsolutePath(), i3, contentLength, false));
                                    } else {
                                        fileOutputStream2 = fileOutputStream;
                                        str6 = str2;
                                        str7 = str12;
                                    }
                                    byteBufferAllocate = byteBuffer;
                                    readableByteChannelNewChannel = readableByteChannelNewChannel;
                                    str12 = str7;
                                    zzakeVar = zzakeVar;
                                    str2 = str6;
                                    channel = channel;
                                    fileOutputStream = fileOutputStream2;
                                    iIntValue = iIntValue;
                                    contentLength = contentLength;
                                    str12 = str7;
                                    fileOutputStream3 = fileOutputStream2;
                                    str3 = null;
                                }
                            }
                        } catch (IOException | RuntimeException e17) {
                            str2 = strConcat;
                            th = e17;
                            str3 = null;
                            fileOutputStream3 = null;
                        }
                        if (th instanceof RuntimeException) {
                            zzbt.zzi().zza(th, "VideoStreamFullFileCache.preload");
                        }
                        try {
                            fileOutputStream3.close();
                        } catch (IOException | NullPointerException unused2) {
                        }
                        if (this.zze) {
                            StringBuilder sb114 = new StringBuilder(String.valueOf(str).length() + 26);
                            sb114.append("Preload aborted for URL \"");
                            sb114.append(str);
                            sb114.append("\"");
                            zzahw.zzd(sb114.toString());
                        } else {
                            StringBuilder sb115 = new StringBuilder(String.valueOf(str).length() + 25);
                            sb115.append("Preload failed for URL \"");
                            sb115.append(str);
                            sb115.append("\"");
                            zzahw.zzc(sb115.toString(), th);
                        }
                        if (file3.exists() && !file3.delete()) {
                            strValueOf = String.valueOf(file3.getAbsolutePath());
                            if (strValueOf.length() != 0) {
                                str4 = "Could not delete partial cache file at ".concat(strValueOf);
                            } else {
                                str4 = new String("Could not delete partial cache file at ");
                            }
                            zzahw.zze(str4);
                        }
                        zza(str, file3.getAbsolutePath(), str12, str3);
                        zzb.remove(str2);
                        return false;
                    }
                }
                File file4 = this.zzd;
                if (file4 == null) {
                    zDelete = false;
                } else {
                    long j = Long.MAX_VALUE;
                    File file5 = null;
                    for (File file6 : file4.listFiles()) {
                        if (!file6.getName().endsWith(".done")) {
                            long jLastModified = file6.lastModified();
                            if (jLastModified < j) {
                                file5 = file6;
                                j = jLastModified;
                            }
                        }
                    }
                    if (file5 != null) {
                        zDelete = file5.delete();
                        File fileZza2 = zza(file5);
                        if (fileZza2.isFile()) {
                            zDelete &= fileZza2.delete();
                        }
                    } else {
                        zDelete = false;
                    }
                }
            } while (zDelete);
            zzahw.zze("Unable to expire stream cache");
            str11 = "expireFailed";
        }
        zza(str, null, str11, null);
        return false;
    }
}
