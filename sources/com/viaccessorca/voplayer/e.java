package com.viaccessorca.voplayer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Message;
import android.os.Process;
import android.util.Pair;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.gms.common.util.GmsVersion;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes2.dex */
class e implements VOPlayer.OnBufferingUpdateListener {
    private static int E;
    private static int F;
    private static int G;
    private static int H;
    private static int I;
    private static int J;
    private static int K;
    private static int L;
    private Condition B;
    private Condition C;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final VOPlayer f127a;
    private VOPlayer.ScrubbingSpriteInfo b;
    private h c;
    private VOPlayer.ThumbnailContext d;
    private k e;
    private String f;
    private boolean g;
    private ArrayList<g> h;
    private int i;
    private ExecutorService j;
    private Thread k;
    private ArrayList<Pair<String, Boolean>> l;
    private Hashtable<Integer, Bitmap> m;
    private Hashtable<Integer, Pair<b, Future<?>>> n;
    HttpURLConnection o;
    HttpURLConnection p;
    HttpURLConnection q;
    private Thread r;
    private Thread s;
    private i v;
    private VOPlayer.ScrubbingConfiguration w;
    private boolean x;
    private Thread y;
    private String z;
    private int t = 0;
    private j u = j.INIT;
    private final Lock A = new ReentrantLock();
    private int D = 0;

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f128a;

        static {
            int[] iArr = new int[j.values().length];
            f128a = iArr;
            try {
                iArr[j.NOT_AVAILABLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f128a[j.AVAILABLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f128a[j.COMPLETE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    protected interface b extends Runnable {
        void cancel();
    }

    private class c extends Thread {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f129a;
        private int b = 0;
        private String c = null;

        c(String str) {
            e.h();
            this.f129a = str;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            e eVar;
            e eVar2;
            String str;
            int i;
            e eVar3;
            e eVar4;
            String str2;
            int i2;
            e eVar5;
            e eVar6;
            String str3;
            int i3;
            try {
                try {
                    e.this.o = (HttpURLConnection) new URL(this.f129a).openConnection();
                    e.this.o.setInstanceFollowRedirects(false);
                    this.b = e.this.o.getResponseCode();
                    this.c = e.this.o.getHeaderField("Location");
                    if (e.this.e == k.SERVER) {
                        String str4 = this.c;
                        if (str4 != null) {
                            int iLastIndexOf = str4.lastIndexOf("&");
                            if (e.this.w.isRedirectAsPermanent || (i3 = this.b) == 301 || i3 == 308) {
                                e.this.z = this.c.substring(0, iLastIndexOf);
                            }
                        }
                    } else {
                        k unused = e.this.e;
                        k kVar = k.WEBVTT;
                    }
                    e.this.A.lock();
                    try {
                        if (e.this.e == k.SERVER && e.this.c.e == l.STARTOVER) {
                            if (e.this.z != null) {
                                eVar5 = e.this;
                                eVar6 = e.this;
                                str3 = e.this.z;
                            } else {
                                eVar5 = e.this;
                                eVar6 = e.this;
                                str3 = e.this.f;
                            }
                            eVar5.z = eVar6.a(str3, (Boolean) false);
                        }
                        e.this.u = j.CHECKED;
                        if (e.this.B != null) {
                            e.this.B.signalAll();
                        }
                    } finally {
                        e.this.A.unlock();
                    }
                } catch (Exception unused2) {
                    e.this.a(j.NOT_AVAILABLE);
                    if (e.this.e == k.SERVER) {
                        String str5 = this.c;
                        if (str5 != null) {
                            int iLastIndexOf2 = str5.lastIndexOf("&");
                            if (e.this.w.isRedirectAsPermanent || (i2 = this.b) == 301 || i2 == 308) {
                                e.this.z = this.c.substring(0, iLastIndexOf2);
                            }
                        }
                    } else {
                        k unused3 = e.this.e;
                        k kVar2 = k.WEBVTT;
                    }
                    e.this.A.lock();
                    try {
                        if (e.this.e == k.SERVER && e.this.c.e == l.STARTOVER) {
                            if (e.this.z != null) {
                                eVar3 = e.this;
                                eVar4 = e.this;
                                str2 = e.this.z;
                            } else {
                                eVar3 = e.this;
                                eVar4 = e.this;
                                str2 = e.this.f;
                            }
                            eVar3.z = eVar4.a(str2, (Boolean) false);
                        }
                        e.this.u = j.CHECKED;
                        if (e.this.B != null) {
                            e.this.B.signalAll();
                        }
                    } finally {
                        e.this.A.unlock();
                    }
                }
            } catch (Throwable th) {
                if (e.this.e == k.SERVER) {
                    String str6 = this.c;
                    if (str6 != null) {
                        int iLastIndexOf3 = str6.lastIndexOf("&");
                        if (e.this.w.isRedirectAsPermanent || (i = this.b) == 301 || i == 308) {
                            e.this.z = this.c.substring(0, iLastIndexOf3);
                        }
                    }
                } else {
                    k unused4 = e.this.e;
                    k kVar3 = k.WEBVTT;
                }
                e.this.A.lock();
                try {
                    if (e.this.e == k.SERVER && e.this.c.e == l.STARTOVER) {
                        if (e.this.z != null) {
                            eVar = e.this;
                            eVar2 = e.this;
                            str = e.this.z;
                        } else {
                            eVar = e.this;
                            eVar2 = e.this;
                            str = e.this.f;
                        }
                        eVar.z = eVar2.a(str, (Boolean) false);
                    }
                    e.this.u = j.CHECKED;
                    if (e.this.B != null) {
                        e.this.B.signalAll();
                    }
                    throw th;
                } finally {
                    e.this.A.unlock();
                }
            }
        }
    }

    private class d implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f130a;
        private final int b;
        private final VOPlayer.ScrubbingSpriteInfo c;
        private AtomicBoolean d = new AtomicBoolean(false);
        private InputStream e = null;

        d(String str, int i, VOPlayer.ScrubbingSpriteInfo scrubbingSpriteInfo) {
            e.h();
            this.f130a = str;
            this.b = i;
            this.c = scrubbingSpriteInfo;
        }

        @Override // com.viaccessorca.voplayer.e.b
        public void cancel() {
            this.d.set(true);
        }

        /* JADX WARN: Code duplicated, block: B:115:0x0283 A[DONT_INVERT, PHI: r1 r2 r4
  0x0283: PHI (r1v35 java.lang.Boolean) = 
  (r1v5 java.lang.Boolean)
  (r1v5 java.lang.Boolean)
  (r1v5 java.lang.Boolean)
  (r1v36 java.lang.Boolean)
  (r1v5 java.lang.Boolean)
 binds: [B:91:0x01fe, B:93:0x0208, B:95:0x0210, B:114:0x027f, B:52:0x0149] A[DONT_GENERATE, DONT_INLINE]
  0x0283: PHI (r2v20 android.graphics.Bitmap) = 
  (r2v1 android.graphics.Bitmap)
  (r2v1 android.graphics.Bitmap)
  (r2v19 android.graphics.Bitmap)
  (r2v24 android.graphics.Bitmap)
  (r2v25 android.graphics.Bitmap)
 binds: [B:91:0x01fe, B:93:0x0208, B:95:0x0210, B:114:0x027f, B:52:0x0149] A[DONT_GENERATE, DONT_INLINE]
  0x0283: PHI (r4v18 java.lang.Boolean) = 
  (r4v0 java.lang.Boolean)
  (r4v0 java.lang.Boolean)
  (r4v0 java.lang.Boolean)
  (r4v20 java.lang.Boolean)
  (r4v0 java.lang.Boolean)
 binds: [B:91:0x01fe, B:93:0x0208, B:95:0x0210, B:114:0x027f, B:52:0x0149] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:116:0x0285  */
        /* JADX WARN: Code duplicated, block: B:121:0x0296  */
        /* JADX WARN: Code duplicated, block: B:193:0x01b6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:79:0x01be A[Catch: all -> 0x01f1, TryCatch #2 {, blocks: (B:77:0x01b6, B:79:0x01be, B:81:0x01c8, B:83:0x01e3, B:82:0x01d7, B:84:0x01ea, B:85:0x01eb), top: B:193:0x01b6, outer: #11 }] */
        /* JADX WARN: Code duplicated, block: B:81:0x01c8 A[Catch: all -> 0x01f1, TryCatch #2 {, blocks: (B:77:0x01b6, B:79:0x01be, B:81:0x01c8, B:83:0x01e3, B:82:0x01d7, B:84:0x01ea, B:85:0x01eb), top: B:193:0x01b6, outer: #11 }] */
        /* JADX WARN: Code duplicated, block: B:82:0x01d7 A[Catch: all -> 0x01f1, TryCatch #2 {, blocks: (B:77:0x01b6, B:79:0x01be, B:81:0x01c8, B:83:0x01e3, B:82:0x01d7, B:84:0x01ea, B:85:0x01eb), top: B:193:0x01b6, outer: #11 }] */
        @Override // java.lang.Runnable
        public void run() {
            InputStream inputStream;
            InputStream inputStream2;
            ArrayList arrayList;
            int i;
            Pair pair;
            int i2;
            int i3;
            VOPlayer.ScrubbingSpriteInfo scrubbingSpriteInfo;
            Hashtable hashtable;
            int i4;
            InputStream inputStream3;
            e.this.A.lock();
            while (e.this.u == j.STARTING) {
                try {
                    e.this.B.await();
                } catch (InterruptedException unused) {
                } catch (Throwable th) {
                    e.this.A.unlock();
                    throw th;
                }
            }
            e.this.A.unlock();
            synchronized (e.this) {
                if (e.this.u != j.CHECKED && e.this.u != j.AVAILABLE && e.this.u != j.COMPLETE) {
                    return;
                }
                if (e.this.z != null) {
                    e eVar = e.this;
                    this.f130a = eVar.a(eVar.z, this.b);
                }
                Process.setThreadPriority(e.this.v.b);
                Boolean bool = false;
                Bitmap bitmapDecodeStream = null;
                Boolean bool2 = true;
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.f130a).openConnection();
                    e.this.q = httpURLConnection;
                    if (e.this.q.getResponseCode() >= 400) {
                        if (e.this.D < 3) {
                            e.this.u = j.NETWORK_ERROR;
                            e.this.new o(this.b).start();
                        } else {
                            e.this.c();
                            if (e.this.c == null || e.this.c.e == l.VOD || e.this.c.d != e.this.c.c) {
                                e.this.a(j.NOT_AVAILABLE);
                            } else {
                                e.this.u = j.COMPLETE;
                                e.this.i = 0;
                                e.this.D = 0;
                            }
                        }
                        e.r(e.this);
                        if (this.d.get() || (inputStream3 = this.e) == null) {
                            return;
                        }
                        try {
                            inputStream3.close();
                            return;
                        } catch (IOException unused2) {
                            return;
                        }
                    }
                    this.e = new BufferedInputStream(httpURLConnection.getInputStream(), 8192);
                    if (!this.d.get() && (e.this.e == k.SERVER || e.this.e == k.DASH)) {
                        bitmapDecodeStream = BitmapFactory.decodeStream(this.e);
                        if (bitmapDecodeStream != null) {
                            int width = bitmapDecodeStream.getWidth() / this.c.mSpriteColums;
                            int height = bitmapDecodeStream.getHeight() / this.c.mSpriteLines;
                            int i5 = 0;
                            for (int i6 = 0; !this.d.get() && i6 < this.c.mSpriteLines; i6++) {
                                for (int i7 = 0; !this.d.get() && i7 < this.c.mSpriteColums; i7++) {
                                    Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeStream, width * i7, height * i6, width, height);
                                    if (bitmapCreateBitmap == bitmapDecodeStream) {
                                        bool2 = false;
                                    }
                                    if (e.this.e == k.SERVER) {
                                        i3 = this.b;
                                        scrubbingSpriteInfo = this.c;
                                    } else {
                                        if (e.this.e == k.DASH) {
                                            i3 = this.b - 1;
                                            scrubbingSpriteInfo = this.c;
                                        } else {
                                            i2 = 0;
                                        }
                                        synchronized (e.this) {
                                            if (e.this.m != null) {
                                                if (e.this.e == k.DASH) {
                                                    hashtable = e.this.m;
                                                    i4 = (i2 + i5) * this.c.mInterval * 1000;
                                                } else {
                                                    hashtable = e.this.m;
                                                    i4 = (i2 + i5) * this.c.mInterval;
                                                }
                                                hashtable.put(Integer.valueOf(i4), bitmapCreateBitmap);
                                            }
                                        }
                                        i5++;
                                    }
                                    i2 = i3 * scrubbingSpriteInfo.mSpriteColums * this.c.mSpriteLines;
                                    synchronized (e.this) {
                                        if (e.this.m != null) {
                                            if (e.this.e == k.DASH) {
                                                hashtable = e.this.m;
                                                i4 = (i2 + i5) * this.c.mInterval * 1000;
                                            } else {
                                                hashtable = e.this.m;
                                                i4 = (i2 + i5) * this.c.mInterval;
                                            }
                                            hashtable.put(Integer.valueOf(i4), bitmapCreateBitmap);
                                        }
                                        i5++;
                                    }
                                }
                            }
                            bool = true;
                            if (bitmapDecodeStream != null) {
                                bitmapDecodeStream.recycle();
                            }
                            if (!this.d.get()) {
                                inputStream2.close();
                            }
                        } else {
                            if (bitmapDecodeStream != null) {
                                bitmapDecodeStream.recycle();
                            }
                            if (!this.d.get()) {
                                inputStream2.close();
                            }
                        }
                    } else if (this.d.get() || e.this.e != k.WEBVTT || (bitmapDecodeStream = BitmapFactory.decodeStream(this.e)) == null) {
                        if (bitmapDecodeStream != null && true == bool2.booleanValue()) {
                            bitmapDecodeStream.recycle();
                        }
                        if (!this.d.get() && (inputStream2 = this.e) != null) {
                            try {
                                inputStream2.close();
                            } catch (IOException unused3) {
                            }
                        }
                    } else {
                        for (int i8 = 0; !this.d.get() && i8 < e.this.h.size(); i8++) {
                            g gVar = (g) e.this.h.get(i8);
                            if (this.f130a.equals(gVar.d)) {
                                Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(bitmapDecodeStream, gVar.c.left, gVar.c.top, gVar.c.right, gVar.c.bottom);
                                synchronized (e.this) {
                                    if (e.this.m != null) {
                                        e.this.m.put(Integer.valueOf(gVar.b), bitmapCreateBitmap2);
                                    }
                                }
                            }
                        }
                        bool = true;
                        if (bitmapDecodeStream != null) {
                            bitmapDecodeStream.recycle();
                        }
                        if (!this.d.get()) {
                            inputStream2.close();
                        }
                    }
                    if (e.this.u == j.INIT || e.this.u == j.NOT_AVAILABLE || e.this.u == j.NETWORK_ERROR) {
                        return;
                    }
                    synchronized (e.this) {
                        if (bool.booleanValue()) {
                            e.j(e.this);
                            if (e.this.e == k.DASH) {
                                Pair pair2 = (Pair) e.this.l.get(this.b - 1);
                                arrayList = e.this.l;
                                i = this.b - 1;
                                pair = new Pair(pair2.first, true);
                            } else {
                                Pair pair3 = (Pair) e.this.l.get(this.b);
                                arrayList = e.this.l;
                                i = this.b;
                                pair = new Pair(pair3.first, true);
                            }
                            arrayList.set(i, pair);
                            if (e.this.u == j.CHECKED) {
                                e.this.a(j.AVAILABLE);
                            }
                            if (e.this.i == 0) {
                                e.this.a(j.COMPLETE);
                                e.this.D = 0;
                            }
                        }
                        if (e.this.n != null) {
                            e.this.n.remove(Integer.valueOf(this.b));
                        }
                    }
                } catch (InterruptedIOException unused4) {
                    if (bitmapDecodeStream != null && true == bool2.booleanValue()) {
                        bitmapDecodeStream.recycle();
                    }
                    if (!this.d.get() && (inputStream2 = this.e) != null) {
                    }
                } catch (Exception unused5) {
                    if (bitmapDecodeStream != null && true == bool2.booleanValue()) {
                        bitmapDecodeStream.recycle();
                    }
                    if (!this.d.get() && (inputStream2 = this.e) != null) {
                    }
                } catch (Throwable th2) {
                    if (bitmapDecodeStream != null && true == bool2.booleanValue()) {
                        bitmapDecodeStream.recycle();
                    }
                    if (!this.d.get() && (inputStream = this.e) != null) {
                        try {
                            inputStream.close();
                        } catch (IOException unused6) {
                        }
                    }
                    throw th2;
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.viaccessorca.voplayer.e$e, reason: collision with other inner class name */
    private class C0188e extends Thread {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f131a;

        C0188e(String str) {
            e.h();
            this.f131a = str;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                try {
                    URL url = new URL(this.f131a);
                    e.this.p = (HttpURLConnection) url.openConnection();
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(e.this.p.getInputStream(), 8192);
                    try {
                        e.this.a(bufferedInputStream);
                        bufferedInputStream.close();
                        if (e.this.x) {
                            e.this.m();
                        }
                    } catch (IOException unused) {
                        e.this.a(j.NOT_AVAILABLE);
                    } catch (IllegalStateException unused2) {
                    }
                    e.this.A.lock();
                    try {
                        if (e.this.C != null) {
                            e.this.C.signal();
                        }
                    } finally {
                        e.this.A.unlock();
                    }
                } catch (Throwable th) {
                    e.this.A.lock();
                    try {
                        if (e.this.C != null) {
                            e.this.C.signal();
                        }
                        throw th;
                    } finally {
                        e.this.A.unlock();
                    }
                }
            } catch (Exception unused3) {
                e.this.a(j.NOT_AVAILABLE);
                e.this.A.lock();
                try {
                    if (e.this.C != null) {
                        e.this.C.signal();
                    }
                } finally {
                    e.this.A.unlock();
                }
            }
        }
    }

    private class f extends Thread {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f132a;
        private final ArrayList<Pair<String, Boolean>> b;

        f(int i, ArrayList<Pair<String, Boolean>> arrayList) {
            e.h();
            this.f132a = i;
            this.b = arrayList;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            d dVar;
            try {
                e.this.n = new Hashtable();
                int i = 0;
                while (this.f132a > 0) {
                    if (e.this.u != j.STARTING && e.this.u != j.CHECKED && e.this.u != j.AVAILABLE && e.this.u != j.COMPLETE) {
                        return;
                    }
                    int i2 = this.f132a >= e.this.v.f135a ? e.this.v.f135a : this.f132a;
                    e.this.j = Executors.newFixedThreadPool(i2);
                    this.f132a -= e.this.v.f135a;
                    int i3 = 0;
                    while (i3 < i2 && e.this.u != j.NETWORK_ERROR) {
                        try {
                            if (e.this.e == k.SERVER) {
                                dVar = e.this.new d((String) this.b.get(i).first, Integer.parseInt(((String) this.b.get(i).first).substring(((String) this.b.get(i).first).lastIndexOf("&page=") + 6)), e.this.b);
                            } else {
                                dVar = e.this.e == k.DASH ? e.this.new d((String) this.b.get(i).first, i + 1, e.this.b) : e.this.new d((String) this.b.get(i).first, i, e.this.b);
                            }
                            synchronized (e.this.n) {
                                try {
                                    e.this.n.put(Integer.valueOf(i), new Pair(dVar, e.this.j.submit(dVar)));
                                } catch (Throwable th) {
                                    throw th;
                                }
                            }
                            i3++;
                            i++;
                        } catch (Exception unused) {
                            if (e.this.D > 3) {
                                e.this.a(j.NOT_AVAILABLE);
                                e.this.j.shutdown();
                                e.this.j.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
                            }
                        } catch (OutOfMemoryError unused2) {
                            e.this.a(j.NOT_AVAILABLE);
                        }
                    }
                    e.this.j.shutdown();
                    try {
                        e.this.j.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
                    } catch (InterruptedException unused3) {
                        e.this.j = null;
                        return;
                    }
                }
            } catch (Exception unused4) {
            }
        }
    }

    private class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f133a;
        private int b;
        private Rect c;
        private String d;

        private g(e eVar) {
        }

        /* synthetic */ g(e eVar, a aVar) {
            this(eVar);
        }
    }

    private class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f134a;
        private int b;
        private int c;
        private int d;
        private l e;

        private h(e eVar) {
        }

        /* synthetic */ h(e eVar, a aVar) {
            this(eVar);
        }
    }

    enum i {
        LOW(e.F, 19),
        MEDIUM_LOW(e.G, 19),
        MEDIUM(e.H, 0),
        HIGH(e.I, -4);


        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f135a;
        int b;

        i(int i, int i2) {
            this.f135a = i;
            this.b = i2;
        }

        public static i getProfile(int i) {
            i iVar = HIGH;
            if (i < e.J) {
                return LOW;
            }
            if (i < e.K) {
                return MEDIUM_LOW;
            }
            return i < e.L ? MEDIUM : iVar;
        }
    }

    enum j {
        INIT,
        CONFIGURED,
        STARTING,
        CHECKED,
        AVAILABLE,
        COMPLETE,
        NOT_AVAILABLE,
        NETWORK_ERROR
    }

    enum k {
        UNDEFINED,
        WEBVTT,
        SERVER,
        DASH
    }

    enum l {
        STARTOVER,
        VOD,
        LIVE
    }

    private class m extends Thread {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private HttpURLConnection f139a;
        private HttpURLConnection b;
        private HttpURLConnection c;

        m() {
            this.f139a = e.this.o;
            this.b = e.this.p;
            this.c = e.this.q;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            e.this.a(this.f139a);
            e.this.a(this.b);
            e.this.a(this.c);
        }
    }

    private class n extends Thread {
        private n() {
        }

        /* synthetic */ n(e eVar, a aVar) {
            this();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                if (e.this.u == j.CONFIGURED) {
                    e.this.m();
                }
            } catch (Exception unused) {
            }
        }
    }

    private class o extends Thread {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f141a;

        o(int i) {
            e.h();
            this.f141a = i;
        }

        /* JADX WARN: Code duplicated, block: B:23:0x00ad  */
        /* JADX WARN: Code duplicated, block: B:25:0x00c3  */
        /* JADX WARN: Code duplicated, block: B:33:0x00dd A[SYNTHETIC] */
        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            e eVar;
            c cVar;
            ArrayList arrayList;
            int i;
            e.this.c();
            e.this.u = j.STARTING;
            e.this.z = null;
            if (e.this.y != null) {
                e.this.y.interrupt();
                if (e.this.B != null) {
                    e.this.A.lock();
                    try {
                        e.this.B.signalAll();
                        e.this.A.unlock();
                        e.this.B = null;
                    } catch (Throwable th) {
                        e.this.A.unlock();
                        throw th;
                    }
                }
                e.this.y = null;
            }
            if (e.this.e != k.SERVER) {
                if (e.this.e == k.WEBVTT) {
                    eVar = e.this;
                    cVar = eVar.new c(eVar.f);
                }
                e.this.y.start();
                arrayList = new ArrayList();
                for (i = 0; i < e.this.l.size(); i++) {
                    if (!((Boolean) ((Pair) e.this.l.get(i)).second).booleanValue()) {
                        arrayList.add(new Pair(((Pair) e.this.l.get(i)).first, false));
                    }
                }
                e eVar2 = e.this;
                eVar2.k = eVar2.new f(eVar2.i, arrayList);
                e.this.k.start();
            }
            eVar = e.this;
            cVar = eVar.new c(eVar.a(eVar.f, this.f141a));
            eVar.y = cVar;
            e.this.y.start();
            arrayList = new ArrayList();
            while (i < e.this.l.size()) {
                if (!((Boolean) ((Pair) e.this.l.get(i)).second).booleanValue()) {
                    arrayList.add(new Pair(((Pair) e.this.l.get(i)).first, false));
                }
            }
            e eVar3 = e.this;
            eVar3.k = eVar3.new f(eVar3.i, arrayList);
            e.this.k.start();
        }
    }

    e(VOPlayer vOPlayer, VOPlayer.ScrubbingConfiguration scrubbingConfiguration, boolean z) {
        this.e = k.UNDEFINED;
        this.f127a = vOPlayer;
        vOPlayer.setOnBufferingUpdateListener(this);
        VOPlayer.ScrubbingConfiguration scrubbingConfiguration2 = new VOPlayer.ScrubbingConfiguration();
        this.w = scrubbingConfiguration2;
        if (scrubbingConfiguration != null) {
            this.w = scrubbingConfiguration;
        } else {
            scrubbingConfiguration2.isRedirectAsPermanent = false;
            scrubbingConfiguration2.liveStartPosition = 0L;
        }
        this.x = z;
        this.e = k.UNDEFINED;
        this.c = null;
    }

    private synchronized long a(String str) {
        long jLongValue;
        long jLongValue2;
        int iIndexOf = str.indexOf("start=");
        if (iIndexOf == -1) {
            if (this.w.liveStartPosition != 0) {
                jLongValue = Long.valueOf(System.currentTimeMillis()).longValue();
                jLongValue2 = this.w.liveStartPosition;
                return jLongValue - jLongValue2;
            }
            return 0L;
        }
        String strSubstring = str.substring(iIndexOf + 6);
        int iIndexOf2 = strSubstring.indexOf("&");
        if (iIndexOf2 != -1) {
            try {
                Long lValueOf = Long.valueOf(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.ENGLISH).parse(strSubstring.substring(0, iIndexOf2)).getTime());
                jLongValue = Long.valueOf(System.currentTimeMillis()).longValue();
                jLongValue2 = lValueOf.longValue();
                return jLongValue - jLongValue2;
            } catch (ParseException e) {
                e.printStackTrace();
                return 0L;
            }
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(String str, int i2) {
        if (this.b == null || str == null) {
            a(j.NOT_AVAILABLE);
        } else {
            k kVar = this.e;
            if (kVar == k.SERVER) {
                return str + "&page=" + i2;
            }
            if (kVar == k.DASH) {
                return str.replace("$Number$", Integer.toString(i2));
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized String a(String str, Boolean bool) {
        int iIndexOf = str.indexOf("end=");
        if (iIndexOf != -1) {
            String strSubstring = str.substring(0, iIndexOf);
            String strSubstring2 = str.substring(iIndexOf + 4);
            int iIndexOf2 = strSubstring2.indexOf("Z&");
            String strSubstring3 = iIndexOf2 != -1 ? strSubstring2.substring(iIndexOf2) : strSubstring2;
            int iIndexOf3 = strSubstring2.indexOf("&");
            if (iIndexOf3 != -1) {
                Date date = null;
                try {
                    date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.ENGLISH).parse(strSubstring2.substring(0, iIndexOf3));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long lValueOf = Long.valueOf(date.getTime());
                Long lValueOf2 = Long.valueOf(System.currentTimeMillis());
                if (bool.booleanValue() || lValueOf.longValue() > lValueOf2.longValue()) {
                    Date date2 = new Date(Long.valueOf(lValueOf2.longValue() - DefaultLoadErrorHandlingPolicy.DEFAULT_TRACK_BLACKLIST_MS).longValue());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                    return strSubstring + ("end=" + simpleDateFormat.format(date2)) + strSubstring3;
                }
            }
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(j jVar) {
        int i2;
        j jVar2 = this.u;
        if (jVar2 != jVar) {
            if (j.INIT == jVar2 && j.NOT_AVAILABLE == jVar) {
                return;
            }
            this.u = jVar;
            Message messageObtainMessage = this.f127a.j.obtainMessage();
            messageObtainMessage.what = 200;
            messageObtainMessage.arg1 = 17000;
            int i3 = a.f128a[jVar.ordinal()];
            if (i3 == 1) {
                i2 = VOPlayer.MEDIA_INFO_SCRUBBING_NOT_AVAILABLE;
            } else {
                if (i3 != 2) {
                    if (i3 == 3) {
                        i2 = 17002;
                    }
                    this.f127a.j.sendMessage(messageObtainMessage);
                }
                i2 = VOPlayer.MEDIA_INFO_SCRUBBING_AVAILABLE;
            }
            messageObtainMessage.arg2 = i2;
            this.f127a.j.sendMessage(messageObtainMessage);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:22:0x0050 A[Catch: all -> 0x0154, TryCatch #0 {, blocks: (B:5:0x0004, B:9:0x001f, B:12:0x0027, B:42:0x0145, B:16:0x0032, B:18:0x0038, B:20:0x0042, B:22:0x0050, B:24:0x0063, B:26:0x0066, B:31:0x00c5, B:30:0x00c3, B:33:0x00d0, B:35:0x00d9, B:36:0x00ee, B:38:0x00f2, B:39:0x00ff, B:41:0x0117, B:45:0x014c, B:46:0x0153), top: B:50:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:35:0x00d9 A[Catch: all -> 0x0154, TryCatch #0 {, blocks: (B:5:0x0004, B:9:0x001f, B:12:0x0027, B:42:0x0145, B:16:0x0032, B:18:0x0038, B:20:0x0042, B:22:0x0050, B:24:0x0063, B:26:0x0066, B:31:0x00c5, B:30:0x00c3, B:33:0x00d0, B:35:0x00d9, B:36:0x00ee, B:38:0x00f2, B:39:0x00ff, B:41:0x0117, B:45:0x014c, B:46:0x0153), top: B:50:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:38:0x00f2 A[Catch: all -> 0x0154, LOOP:2: B:36:0x00ee->B:38:0x00f2, LOOP_END, TryCatch #0 {, blocks: (B:5:0x0004, B:9:0x001f, B:12:0x0027, B:42:0x0145, B:16:0x0032, B:18:0x0038, B:20:0x0042, B:22:0x0050, B:24:0x0063, B:26:0x0066, B:31:0x00c5, B:30:0x00c3, B:33:0x00d0, B:35:0x00d9, B:36:0x00ee, B:38:0x00f2, B:39:0x00ff, B:41:0x0117, B:45:0x014c, B:46:0x0153), top: B:50:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:41:0x0117 A[Catch: all -> 0x0154, LOOP:0: B:8:0x001e->B:41:0x0117, LOOP_END, TryCatch #0 {, blocks: (B:5:0x0004, B:9:0x001f, B:12:0x0027, B:42:0x0145, B:16:0x0032, B:18:0x0038, B:20:0x0042, B:22:0x0050, B:24:0x0063, B:26:0x0066, B:31:0x00c5, B:30:0x00c3, B:33:0x00d0, B:35:0x00d9, B:36:0x00ee, B:38:0x00f2, B:39:0x00ff, B:41:0x0117, B:45:0x014c, B:46:0x0153), top: B:50:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:53:0x0145 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:56:0x0145 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:57:0x0145 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:58:0x0038 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:59:0x00ce A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:60:0x00d0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:61:0x0042 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:63:0x001f A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:64:0x001f A[SYNTHETIC] */
    public synchronized void a(InputStream inputStream) throws IOException {
        String[] strArrSplit;
        String[] strArrSplit2;
        String[] strArrSplit3;
        String[] strArrSplit4;
        StringBuilder sb;
        int i2;
        String[] strArrSplit5;
        this.g = true;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        if (this.u != j.CONFIGURED) {
            throw new IllegalStateException("ScrubbingManager state invalid");
        }
        a aVar = null;
        boolean z = true;
        g gVar = null;
        int i3 = -1;
        loop0: while (true) {
            boolean z2 = false;
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    if (z) {
                        if (line.equals("WEBVTT")) {
                            z = false;
                            if (line.isEmpty()) {
                                if (line.contains(" --> ")) {
                                    gVar = new g(this, aVar);
                                    strArrSplit = line.split(" --> ");
                                    if (strArrSplit.length == 2) {
                                        strArrSplit2 = strArrSplit[0].split("\\.");
                                        String[] strArrSplit6 = strArrSplit2[0].split(":");
                                        if (strArrSplit2.length != 2) {
                                        }
                                    }
                                } else if (z2) {
                                    strArrSplit3 = line.split("#xywh=");
                                    if (strArrSplit3.length == 2) {
                                        strArrSplit4 = this.f.split("/");
                                        sb = new StringBuilder(strArrSplit4[0]);
                                        sb.append("/");
                                        for (i2 = 1; i2 < strArrSplit4.length - 1; i2++) {
                                            sb.append(strArrSplit4[i2]);
                                            sb.append("/");
                                        }
                                        sb.append(strArrSplit3[0]);
                                        gVar.d = sb.toString();
                                        strArrSplit5 = strArrSplit3[1].split(",");
                                        if (strArrSplit5.length == 4) {
                                            gVar.c = new Rect();
                                            gVar.c.set(Integer.parseInt(strArrSplit5[0]), Integer.parseInt(strArrSplit5[1]), Integer.parseInt(strArrSplit5[2]), Integer.parseInt(strArrSplit5[3]));
                                            this.h.add(gVar);
                                        }
                                    }
                                }
                            }
                        }
                    } else if (line.isEmpty()) {
                        if (line.contains(" --> ")) {
                            gVar = new g(this, aVar);
                            strArrSplit = line.split(" --> ");
                            if (strArrSplit.length == 2) {
                                strArrSplit2 = strArrSplit[0].split("\\.");
                                String[] strArrSplit7 = strArrSplit2[0].split(":");
                                if (strArrSplit2.length != 2 && strArrSplit7.length == 3) {
                                    int i4 = (Integer.parseInt(strArrSplit7[0]) * 3600000) + (Integer.parseInt(strArrSplit7[1]) * 60000) + (Integer.parseInt(strArrSplit7[2]) * 1000) + Integer.parseInt(strArrSplit2[1]);
                                    String[] strArrSplit8 = strArrSplit[1].split("\\.");
                                    String[] strArrSplit9 = strArrSplit8[0].split(":");
                                    int i5 = ((((Integer.parseInt(strArrSplit9[0]) * 3600000) + (Integer.parseInt(strArrSplit9[1]) * 60000)) + (Integer.parseInt(strArrSplit9[2]) * 1000)) + Integer.parseInt(strArrSplit8[1])) - i4;
                                    if (i3 == -1) {
                                        i3 = i5;
                                    } else if (i3 != i5) {
                                        this.g = false;
                                    }
                                    gVar.f133a = i5;
                                    gVar.b = i4;
                                    z2 = true;
                                }
                            }
                        } else if (z2) {
                            strArrSplit3 = line.split("#xywh=");
                            if (strArrSplit3.length == 2) {
                                strArrSplit4 = this.f.split("/");
                                sb = new StringBuilder(strArrSplit4[0]);
                                sb.append("/");
                                while (i2 < strArrSplit4.length - 1) {
                                    sb.append(strArrSplit4[i2]);
                                    sb.append("/");
                                }
                                sb.append(strArrSplit3[0]);
                                gVar.d = sb.toString();
                                strArrSplit5 = strArrSplit3[1].split(",");
                                if (strArrSplit5.length == 4) {
                                    gVar.c = new Rect();
                                    gVar.c.set(Integer.parseInt(strArrSplit5[0]), Integer.parseInt(strArrSplit5[1]), Integer.parseInt(strArrSplit5[2]), Integer.parseInt(strArrSplit5[3]));
                                    this.h.add(gVar);
                                }
                            }
                        }
                    }
                    a(j.NOT_AVAILABLE);
                    break loop0;
                }
                break loop0;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception unused) {
            }
        }
    }

    static /* synthetic */ int h() {
        int i2 = E;
        E = i2 + 1;
        return i2;
    }

    static /* synthetic */ int j(e eVar) {
        int i2 = eVar.i;
        eVar.i = i2 - 1;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:52:0x00f3 A[Catch: all -> 0x023e, TryCatch #3 {, blocks: (B:3:0x0001, B:5:0x0007, B:8:0x000e, B:9:0x0012, B:10:0x001e, B:12:0x002c, B:15:0x0032, B:17:0x0036, B:28:0x0055, B:29:0x005a, B:30:0x005b, B:34:0x0068, B:35:0x007c, B:37:0x0082, B:39:0x008e, B:41:0x0094, B:42:0x00a4, B:92:0x0218, B:96:0x0220, B:43:0x00ae, B:45:0x00b4, B:46:0x00bf, B:50:0x00d6, B:52:0x00f3, B:54:0x00f9, B:59:0x011d, B:61:0x012f, B:62:0x0135, B:74:0x019e, B:76:0x01a8, B:82:0x01d3, B:86:0x01de, B:87:0x01f5, B:91:0x01fc, B:77:0x01c6, B:79:0x01cc, B:81:0x01d1, B:55:0x0109, B:57:0x010d, B:63:0x0137, B:65:0x013d, B:67:0x016d, B:68:0x0171, B:71:0x0178, B:73:0x0182, B:99:0x0230, B:47:0x00c2, B:49:0x00c6, B:102:0x0237, B:18:0x003b, B:20:0x0043, B:22:0x0047, B:25:0x004e), top: B:111:0x0001, inners: #1, #4 }] */
    /* JADX WARN: Code duplicated, block: B:54:0x00f9 A[Catch: all -> 0x023e, TryCatch #3 {, blocks: (B:3:0x0001, B:5:0x0007, B:8:0x000e, B:9:0x0012, B:10:0x001e, B:12:0x002c, B:15:0x0032, B:17:0x0036, B:28:0x0055, B:29:0x005a, B:30:0x005b, B:34:0x0068, B:35:0x007c, B:37:0x0082, B:39:0x008e, B:41:0x0094, B:42:0x00a4, B:92:0x0218, B:96:0x0220, B:43:0x00ae, B:45:0x00b4, B:46:0x00bf, B:50:0x00d6, B:52:0x00f3, B:54:0x00f9, B:59:0x011d, B:61:0x012f, B:62:0x0135, B:74:0x019e, B:76:0x01a8, B:82:0x01d3, B:86:0x01de, B:87:0x01f5, B:91:0x01fc, B:77:0x01c6, B:79:0x01cc, B:81:0x01d1, B:55:0x0109, B:57:0x010d, B:63:0x0137, B:65:0x013d, B:67:0x016d, B:68:0x0171, B:71:0x0178, B:73:0x0182, B:99:0x0230, B:47:0x00c2, B:49:0x00c6, B:102:0x0237, B:18:0x003b, B:20:0x0043, B:22:0x0047, B:25:0x004e), top: B:111:0x0001, inners: #1, #4 }] */
    /* JADX WARN: Code duplicated, block: B:55:0x0109 A[Catch: all -> 0x023e, TryCatch #3 {, blocks: (B:3:0x0001, B:5:0x0007, B:8:0x000e, B:9:0x0012, B:10:0x001e, B:12:0x002c, B:15:0x0032, B:17:0x0036, B:28:0x0055, B:29:0x005a, B:30:0x005b, B:34:0x0068, B:35:0x007c, B:37:0x0082, B:39:0x008e, B:41:0x0094, B:42:0x00a4, B:92:0x0218, B:96:0x0220, B:43:0x00ae, B:45:0x00b4, B:46:0x00bf, B:50:0x00d6, B:52:0x00f3, B:54:0x00f9, B:59:0x011d, B:61:0x012f, B:62:0x0135, B:74:0x019e, B:76:0x01a8, B:82:0x01d3, B:86:0x01de, B:87:0x01f5, B:91:0x01fc, B:77:0x01c6, B:79:0x01cc, B:81:0x01d1, B:55:0x0109, B:57:0x010d, B:63:0x0137, B:65:0x013d, B:67:0x016d, B:68:0x0171, B:71:0x0178, B:73:0x0182, B:99:0x0230, B:47:0x00c2, B:49:0x00c6, B:102:0x0237, B:18:0x003b, B:20:0x0043, B:22:0x0047, B:25:0x004e), top: B:111:0x0001, inners: #1, #4 }] */
    /* JADX WARN: Code duplicated, block: B:57:0x010d A[Catch: all -> 0x023e, TryCatch #3 {, blocks: (B:3:0x0001, B:5:0x0007, B:8:0x000e, B:9:0x0012, B:10:0x001e, B:12:0x002c, B:15:0x0032, B:17:0x0036, B:28:0x0055, B:29:0x005a, B:30:0x005b, B:34:0x0068, B:35:0x007c, B:37:0x0082, B:39:0x008e, B:41:0x0094, B:42:0x00a4, B:92:0x0218, B:96:0x0220, B:43:0x00ae, B:45:0x00b4, B:46:0x00bf, B:50:0x00d6, B:52:0x00f3, B:54:0x00f9, B:59:0x011d, B:61:0x012f, B:62:0x0135, B:74:0x019e, B:76:0x01a8, B:82:0x01d3, B:86:0x01de, B:87:0x01f5, B:91:0x01fc, B:77:0x01c6, B:79:0x01cc, B:81:0x01d1, B:55:0x0109, B:57:0x010d, B:63:0x0137, B:65:0x013d, B:67:0x016d, B:68:0x0171, B:71:0x0178, B:73:0x0182, B:99:0x0230, B:47:0x00c2, B:49:0x00c6, B:102:0x0237, B:18:0x003b, B:20:0x0043, B:22:0x0047, B:25:0x004e), top: B:111:0x0001, inners: #1, #4 }] */
    /* JADX WARN: Code duplicated, block: B:58:0x011c  */
    /* JADX WARN: Code duplicated, block: B:61:0x012f A[Catch: all -> 0x023e, TryCatch #3 {, blocks: (B:3:0x0001, B:5:0x0007, B:8:0x000e, B:9:0x0012, B:10:0x001e, B:12:0x002c, B:15:0x0032, B:17:0x0036, B:28:0x0055, B:29:0x005a, B:30:0x005b, B:34:0x0068, B:35:0x007c, B:37:0x0082, B:39:0x008e, B:41:0x0094, B:42:0x00a4, B:92:0x0218, B:96:0x0220, B:43:0x00ae, B:45:0x00b4, B:46:0x00bf, B:50:0x00d6, B:52:0x00f3, B:54:0x00f9, B:59:0x011d, B:61:0x012f, B:62:0x0135, B:74:0x019e, B:76:0x01a8, B:82:0x01d3, B:86:0x01de, B:87:0x01f5, B:91:0x01fc, B:77:0x01c6, B:79:0x01cc, B:81:0x01d1, B:55:0x0109, B:57:0x010d, B:63:0x0137, B:65:0x013d, B:67:0x016d, B:68:0x0171, B:71:0x0178, B:73:0x0182, B:99:0x0230, B:47:0x00c2, B:49:0x00c6, B:102:0x0237, B:18:0x003b, B:20:0x0043, B:22:0x0047, B:25:0x004e), top: B:111:0x0001, inners: #1, #4 }] */
    /* JADX WARN: Code duplicated, block: B:63:0x0137 A[Catch: all -> 0x023e, TryCatch #3 {, blocks: (B:3:0x0001, B:5:0x0007, B:8:0x000e, B:9:0x0012, B:10:0x001e, B:12:0x002c, B:15:0x0032, B:17:0x0036, B:28:0x0055, B:29:0x005a, B:30:0x005b, B:34:0x0068, B:35:0x007c, B:37:0x0082, B:39:0x008e, B:41:0x0094, B:42:0x00a4, B:92:0x0218, B:96:0x0220, B:43:0x00ae, B:45:0x00b4, B:46:0x00bf, B:50:0x00d6, B:52:0x00f3, B:54:0x00f9, B:59:0x011d, B:61:0x012f, B:62:0x0135, B:74:0x019e, B:76:0x01a8, B:82:0x01d3, B:86:0x01de, B:87:0x01f5, B:91:0x01fc, B:77:0x01c6, B:79:0x01cc, B:81:0x01d1, B:55:0x0109, B:57:0x010d, B:63:0x0137, B:65:0x013d, B:67:0x016d, B:68:0x0171, B:71:0x0178, B:73:0x0182, B:99:0x0230, B:47:0x00c2, B:49:0x00c6, B:102:0x0237, B:18:0x003b, B:20:0x0043, B:22:0x0047, B:25:0x004e), top: B:111:0x0001, inners: #1, #4 }] */
    /* JADX WARN: Code duplicated, block: B:65:0x013d A[Catch: all -> 0x023e, TryCatch #3 {, blocks: (B:3:0x0001, B:5:0x0007, B:8:0x000e, B:9:0x0012, B:10:0x001e, B:12:0x002c, B:15:0x0032, B:17:0x0036, B:28:0x0055, B:29:0x005a, B:30:0x005b, B:34:0x0068, B:35:0x007c, B:37:0x0082, B:39:0x008e, B:41:0x0094, B:42:0x00a4, B:92:0x0218, B:96:0x0220, B:43:0x00ae, B:45:0x00b4, B:46:0x00bf, B:50:0x00d6, B:52:0x00f3, B:54:0x00f9, B:59:0x011d, B:61:0x012f, B:62:0x0135, B:74:0x019e, B:76:0x01a8, B:82:0x01d3, B:86:0x01de, B:87:0x01f5, B:91:0x01fc, B:77:0x01c6, B:79:0x01cc, B:81:0x01d1, B:55:0x0109, B:57:0x010d, B:63:0x0137, B:65:0x013d, B:67:0x016d, B:68:0x0171, B:71:0x0178, B:73:0x0182, B:99:0x0230, B:47:0x00c2, B:49:0x00c6, B:102:0x0237, B:18:0x003b, B:20:0x0043, B:22:0x0047, B:25:0x004e), top: B:111:0x0001, inners: #1, #4 }] */
    /* JADX WARN: Code duplicated, block: B:67:0x016d A[Catch: all -> 0x023e, TryCatch #3 {, blocks: (B:3:0x0001, B:5:0x0007, B:8:0x000e, B:9:0x0012, B:10:0x001e, B:12:0x002c, B:15:0x0032, B:17:0x0036, B:28:0x0055, B:29:0x005a, B:30:0x005b, B:34:0x0068, B:35:0x007c, B:37:0x0082, B:39:0x008e, B:41:0x0094, B:42:0x00a4, B:92:0x0218, B:96:0x0220, B:43:0x00ae, B:45:0x00b4, B:46:0x00bf, B:50:0x00d6, B:52:0x00f3, B:54:0x00f9, B:59:0x011d, B:61:0x012f, B:62:0x0135, B:74:0x019e, B:76:0x01a8, B:82:0x01d3, B:86:0x01de, B:87:0x01f5, B:91:0x01fc, B:77:0x01c6, B:79:0x01cc, B:81:0x01d1, B:55:0x0109, B:57:0x010d, B:63:0x0137, B:65:0x013d, B:67:0x016d, B:68:0x0171, B:71:0x0178, B:73:0x0182, B:99:0x0230, B:47:0x00c2, B:49:0x00c6, B:102:0x0237, B:18:0x003b, B:20:0x0043, B:22:0x0047, B:25:0x004e), top: B:111:0x0001, inners: #1, #4 }] */
    /* JADX WARN: Code duplicated, block: B:71:0x0178 A[Catch: all -> 0x023e, TRY_ENTER, TryCatch #3 {, blocks: (B:3:0x0001, B:5:0x0007, B:8:0x000e, B:9:0x0012, B:10:0x001e, B:12:0x002c, B:15:0x0032, B:17:0x0036, B:28:0x0055, B:29:0x005a, B:30:0x005b, B:34:0x0068, B:35:0x007c, B:37:0x0082, B:39:0x008e, B:41:0x0094, B:42:0x00a4, B:92:0x0218, B:96:0x0220, B:43:0x00ae, B:45:0x00b4, B:46:0x00bf, B:50:0x00d6, B:52:0x00f3, B:54:0x00f9, B:59:0x011d, B:61:0x012f, B:62:0x0135, B:74:0x019e, B:76:0x01a8, B:82:0x01d3, B:86:0x01de, B:87:0x01f5, B:91:0x01fc, B:77:0x01c6, B:79:0x01cc, B:81:0x01d1, B:55:0x0109, B:57:0x010d, B:63:0x0137, B:65:0x013d, B:67:0x016d, B:68:0x0171, B:71:0x0178, B:73:0x0182, B:99:0x0230, B:47:0x00c2, B:49:0x00c6, B:102:0x0237, B:18:0x003b, B:20:0x0043, B:22:0x0047, B:25:0x004e), top: B:111:0x0001, inners: #1, #4 }] */
    /* JADX WARN: Code duplicated, block: B:73:0x0182 A[Catch: all -> 0x023e, TryCatch #3 {, blocks: (B:3:0x0001, B:5:0x0007, B:8:0x000e, B:9:0x0012, B:10:0x001e, B:12:0x002c, B:15:0x0032, B:17:0x0036, B:28:0x0055, B:29:0x005a, B:30:0x005b, B:34:0x0068, B:35:0x007c, B:37:0x0082, B:39:0x008e, B:41:0x0094, B:42:0x00a4, B:92:0x0218, B:96:0x0220, B:43:0x00ae, B:45:0x00b4, B:46:0x00bf, B:50:0x00d6, B:52:0x00f3, B:54:0x00f9, B:59:0x011d, B:61:0x012f, B:62:0x0135, B:74:0x019e, B:76:0x01a8, B:82:0x01d3, B:86:0x01de, B:87:0x01f5, B:91:0x01fc, B:77:0x01c6, B:79:0x01cc, B:81:0x01d1, B:55:0x0109, B:57:0x010d, B:63:0x0137, B:65:0x013d, B:67:0x016d, B:68:0x0171, B:71:0x0178, B:73:0x0182, B:99:0x0230, B:47:0x00c2, B:49:0x00c6, B:102:0x0237, B:18:0x003b, B:20:0x0043, B:22:0x0047, B:25:0x004e), top: B:111:0x0001, inners: #1, #4 }] */
    /* JADX WARN: Code duplicated, block: B:76:0x01a8 A[Catch: all -> 0x023e, TryCatch #3 {, blocks: (B:3:0x0001, B:5:0x0007, B:8:0x000e, B:9:0x0012, B:10:0x001e, B:12:0x002c, B:15:0x0032, B:17:0x0036, B:28:0x0055, B:29:0x005a, B:30:0x005b, B:34:0x0068, B:35:0x007c, B:37:0x0082, B:39:0x008e, B:41:0x0094, B:42:0x00a4, B:92:0x0218, B:96:0x0220, B:43:0x00ae, B:45:0x00b4, B:46:0x00bf, B:50:0x00d6, B:52:0x00f3, B:54:0x00f9, B:59:0x011d, B:61:0x012f, B:62:0x0135, B:74:0x019e, B:76:0x01a8, B:82:0x01d3, B:86:0x01de, B:87:0x01f5, B:91:0x01fc, B:77:0x01c6, B:79:0x01cc, B:81:0x01d1, B:55:0x0109, B:57:0x010d, B:63:0x0137, B:65:0x013d, B:67:0x016d, B:68:0x0171, B:71:0x0178, B:73:0x0182, B:99:0x0230, B:47:0x00c2, B:49:0x00c6, B:102:0x0237, B:18:0x003b, B:20:0x0043, B:22:0x0047, B:25:0x004e), top: B:111:0x0001, inners: #1, #4 }] */
    /* JADX WARN: Code duplicated, block: B:77:0x01c6 A[Catch: all -> 0x023e, TryCatch #3 {, blocks: (B:3:0x0001, B:5:0x0007, B:8:0x000e, B:9:0x0012, B:10:0x001e, B:12:0x002c, B:15:0x0032, B:17:0x0036, B:28:0x0055, B:29:0x005a, B:30:0x005b, B:34:0x0068, B:35:0x007c, B:37:0x0082, B:39:0x008e, B:41:0x0094, B:42:0x00a4, B:92:0x0218, B:96:0x0220, B:43:0x00ae, B:45:0x00b4, B:46:0x00bf, B:50:0x00d6, B:52:0x00f3, B:54:0x00f9, B:59:0x011d, B:61:0x012f, B:62:0x0135, B:74:0x019e, B:76:0x01a8, B:82:0x01d3, B:86:0x01de, B:87:0x01f5, B:91:0x01fc, B:77:0x01c6, B:79:0x01cc, B:81:0x01d1, B:55:0x0109, B:57:0x010d, B:63:0x0137, B:65:0x013d, B:67:0x016d, B:68:0x0171, B:71:0x0178, B:73:0x0182, B:99:0x0230, B:47:0x00c2, B:49:0x00c6, B:102:0x0237, B:18:0x003b, B:20:0x0043, B:22:0x0047, B:25:0x004e), top: B:111:0x0001, inners: #1, #4 }] */
    /* JADX WARN: Code duplicated, block: B:84:0x01db  */
    /* JADX WARN: Code duplicated, block: B:86:0x01de A[Catch: all -> 0x023e, LOOP:2: B:85:0x01dc->B:86:0x01de, LOOP_END, TryCatch #3 {, blocks: (B:3:0x0001, B:5:0x0007, B:8:0x000e, B:9:0x0012, B:10:0x001e, B:12:0x002c, B:15:0x0032, B:17:0x0036, B:28:0x0055, B:29:0x005a, B:30:0x005b, B:34:0x0068, B:35:0x007c, B:37:0x0082, B:39:0x008e, B:41:0x0094, B:42:0x00a4, B:92:0x0218, B:96:0x0220, B:43:0x00ae, B:45:0x00b4, B:46:0x00bf, B:50:0x00d6, B:52:0x00f3, B:54:0x00f9, B:59:0x011d, B:61:0x012f, B:62:0x0135, B:74:0x019e, B:76:0x01a8, B:82:0x01d3, B:86:0x01de, B:87:0x01f5, B:91:0x01fc, B:77:0x01c6, B:79:0x01cc, B:81:0x01d1, B:55:0x0109, B:57:0x010d, B:63:0x0137, B:65:0x013d, B:67:0x016d, B:68:0x0171, B:71:0x0178, B:73:0x0182, B:99:0x0230, B:47:0x00c2, B:49:0x00c6, B:102:0x0237, B:18:0x003b, B:20:0x0043, B:22:0x0047, B:25:0x004e), top: B:111:0x0001, inners: #1, #4 }] */
    /* JADX WARN: Code duplicated, block: B:87:0x01f5 A[Catch: all -> 0x023e, TryCatch #3 {, blocks: (B:3:0x0001, B:5:0x0007, B:8:0x000e, B:9:0x0012, B:10:0x001e, B:12:0x002c, B:15:0x0032, B:17:0x0036, B:28:0x0055, B:29:0x005a, B:30:0x005b, B:34:0x0068, B:35:0x007c, B:37:0x0082, B:39:0x008e, B:41:0x0094, B:42:0x00a4, B:92:0x0218, B:96:0x0220, B:43:0x00ae, B:45:0x00b4, B:46:0x00bf, B:50:0x00d6, B:52:0x00f3, B:54:0x00f9, B:59:0x011d, B:61:0x012f, B:62:0x0135, B:74:0x019e, B:76:0x01a8, B:82:0x01d3, B:86:0x01de, B:87:0x01f5, B:91:0x01fc, B:77:0x01c6, B:79:0x01cc, B:81:0x01d1, B:55:0x0109, B:57:0x010d, B:63:0x0137, B:65:0x013d, B:67:0x016d, B:68:0x0171, B:71:0x0178, B:73:0x0182, B:99:0x0230, B:47:0x00c2, B:49:0x00c6, B:102:0x0237, B:18:0x003b, B:20:0x0043, B:22:0x0047, B:25:0x004e), top: B:111:0x0001, inners: #1, #4 }] */
    /* JADX WARN: Code duplicated, block: B:89:0x01f9  */
    /* JADX WARN: Code duplicated, block: B:91:0x01fc A[Catch: all -> 0x023e, LOOP:3: B:90:0x01fa->B:91:0x01fc, LOOP_END, TryCatch #3 {, blocks: (B:3:0x0001, B:5:0x0007, B:8:0x000e, B:9:0x0012, B:10:0x001e, B:12:0x002c, B:15:0x0032, B:17:0x0036, B:28:0x0055, B:29:0x005a, B:30:0x005b, B:34:0x0068, B:35:0x007c, B:37:0x0082, B:39:0x008e, B:41:0x0094, B:42:0x00a4, B:92:0x0218, B:96:0x0220, B:43:0x00ae, B:45:0x00b4, B:46:0x00bf, B:50:0x00d6, B:52:0x00f3, B:54:0x00f9, B:59:0x011d, B:61:0x012f, B:62:0x0135, B:74:0x019e, B:76:0x01a8, B:82:0x01d3, B:86:0x01de, B:87:0x01f5, B:91:0x01fc, B:77:0x01c6, B:79:0x01cc, B:81:0x01d1, B:55:0x0109, B:57:0x010d, B:63:0x0137, B:65:0x013d, B:67:0x016d, B:68:0x0171, B:71:0x0178, B:73:0x0182, B:99:0x0230, B:47:0x00c2, B:49:0x00c6, B:102:0x0237, B:18:0x003b, B:20:0x0043, B:22:0x0047, B:25:0x004e), top: B:111:0x0001, inners: #1, #4 }] */
    /* JADX WARN: Code duplicated, block: B:99:0x0230 A[Catch: all -> 0x023e, TRY_ENTER, TRY_LEAVE, TryCatch #3 {, blocks: (B:3:0x0001, B:5:0x0007, B:8:0x000e, B:9:0x0012, B:10:0x001e, B:12:0x002c, B:15:0x0032, B:17:0x0036, B:28:0x0055, B:29:0x005a, B:30:0x005b, B:34:0x0068, B:35:0x007c, B:37:0x0082, B:39:0x008e, B:41:0x0094, B:42:0x00a4, B:92:0x0218, B:96:0x0220, B:43:0x00ae, B:45:0x00b4, B:46:0x00bf, B:50:0x00d6, B:52:0x00f3, B:54:0x00f9, B:59:0x011d, B:61:0x012f, B:62:0x0135, B:74:0x019e, B:76:0x01a8, B:82:0x01d3, B:86:0x01de, B:87:0x01f5, B:91:0x01fc, B:77:0x01c6, B:79:0x01cc, B:81:0x01d1, B:55:0x0109, B:57:0x010d, B:63:0x0137, B:65:0x013d, B:67:0x016d, B:68:0x0171, B:71:0x0178, B:73:0x0182, B:99:0x0230, B:47:0x00c2, B:49:0x00c6, B:102:0x0237, B:18:0x003b, B:20:0x0043, B:22:0x0047, B:25:0x004e), top: B:111:0x0001, inners: #1, #4 }] */
    public synchronized void m() {
        c cVar;
        float fCeil;
        float f2;
        float f3;
        VOPlayer.ScrubbingSpriteInfo scrubbingSpriteInfo;
        int i2;
        k kVar;
        int i3;
        int i4;
        k kVar2;
        float f4;
        VOPlayer.ScrubbingSpriteInfo scrubbingSpriteInfo2;
        if (this.u != j.CONFIGURED) {
            a(j.NOT_AVAILABLE);
            return;
        }
        this.u = j.STARTING;
        try {
            this.v = i.getProfile(this.f127a.getHttpStreamingCurrentBitrate());
            this.l = new ArrayList<>();
            k kVar3 = this.e;
            if (kVar3 == k.SERVER || kVar3 == k.DASH) {
                k kVar4 = this.e;
                if (kVar4 == k.SERVER) {
                    cVar = new c(a(this.f, 0));
                } else if (kVar4 == k.DASH) {
                    cVar = new c(a(this.f, this.d.startNumber));
                } else {
                    this.y.start();
                    this.c.f134a = this.f127a.getPrimaryDuration();
                    if (this.c.f134a != 0) {
                        kVar2 = this.e;
                        if (kVar2 == k.SERVER) {
                            fCeil = this.c.f134a / this.c.c;
                        } else if (kVar2 == k.DASH) {
                            fCeil = this.c.f134a / (this.d.duration * 1000.0f);
                        } else {
                            fCeil = 0.0f;
                        }
                        f4 = fCeil - ((int) fCeil);
                        scrubbingSpriteInfo2 = this.b;
                        if (f4 > 1.0f / (scrubbingSpriteInfo2.mSpriteColums * scrubbingSpriteInfo2.mSpriteLines)) {
                            fCeil = (float) Math.ceil(fCeil);
                        }
                    } else {
                        if (this.e == k.DASH) {
                            int[] httpStreamingSeekableRange = this.f127a.getHttpStreamingSeekableRange();
                            this.c.f134a = httpStreamingSeekableRange[1] - httpStreamingSeekableRange[0];
                            f2 = this.c.f134a / (this.d.duration * 1000.0f);
                            f3 = f2 - ((int) f2);
                            scrubbingSpriteInfo = this.b;
                            if (f3 > 1.0f / (scrubbingSpriteInfo.mSpriteColums * scrubbingSpriteInfo.mSpriteLines)) {
                                Math.ceil(f2);
                            }
                            a(j.NOT_AVAILABLE);
                            return;
                        }
                        if (this.c.e == l.LIVE) {
                            a(j.NOT_AVAILABLE);
                            return;
                        } else {
                            this.c.f134a = (int) a(this.f);
                            fCeil = this.c.f134a / this.c.c;
                        }
                    }
                    i2 = (int) fCeil;
                    if (this.c.e != l.VOD) {
                        this.c.b = i2;
                        h hVar = this.c;
                        hVar.d = hVar.c - (this.c.f134a - (this.c.c * i2));
                    } else if (this.e == k.SERVER && fCeil % 1.0f > 0.0f) {
                        i2++;
                    }
                    this.i = i2;
                    kVar = this.e;
                    if (kVar == k.SERVER) {
                        for (i4 = 0; i4 < i2; i4++) {
                            this.l.add(new Pair<>(a(this.f, i4), false));
                        }
                    } else if (kVar == k.DASH) {
                        for (i3 = 0; i3 < i2; i3++) {
                            this.l.add(new Pair<>(a(this.f, this.d.startNumber + i3), false));
                        }
                    }
                }
                this.y = cVar;
                this.y.start();
                this.c.f134a = this.f127a.getPrimaryDuration();
                if (this.c.f134a != 0) {
                    kVar2 = this.e;
                    if (kVar2 == k.SERVER) {
                        fCeil = this.c.f134a / this.c.c;
                    } else if (kVar2 == k.DASH) {
                        fCeil = this.c.f134a / (this.d.duration * 1000.0f);
                    } else {
                        fCeil = 0.0f;
                    }
                    f4 = fCeil - ((int) fCeil);
                    scrubbingSpriteInfo2 = this.b;
                    if (f4 > 1.0f / (scrubbingSpriteInfo2.mSpriteColums * scrubbingSpriteInfo2.mSpriteLines)) {
                        fCeil = (float) Math.ceil(fCeil);
                    }
                } else {
                    if (this.e == k.DASH) {
                        int[] httpStreamingSeekableRange2 = this.f127a.getHttpStreamingSeekableRange();
                        this.c.f134a = httpStreamingSeekableRange2[1] - httpStreamingSeekableRange2[0];
                        f2 = this.c.f134a / (this.d.duration * 1000.0f);
                        f3 = f2 - ((int) f2);
                        scrubbingSpriteInfo = this.b;
                        if (f3 > 1.0f / (scrubbingSpriteInfo.mSpriteColums * scrubbingSpriteInfo.mSpriteLines)) {
                            Math.ceil(f2);
                        }
                        a(j.NOT_AVAILABLE);
                        return;
                    }
                    if (this.c.e == l.LIVE) {
                        a(j.NOT_AVAILABLE);
                        return;
                    } else {
                        this.c.f134a = (int) a(this.f);
                        fCeil = this.c.f134a / this.c.c;
                    }
                }
                i2 = (int) fCeil;
                if (this.c.e != l.VOD) {
                    this.c.b = i2;
                    h hVar2 = this.c;
                    hVar2.d = hVar2.c - (this.c.f134a - (this.c.c * i2));
                } else if (this.e == k.SERVER) {
                    i2++;
                }
                this.i = i2;
                kVar = this.e;
                if (kVar == k.SERVER) {
                    while (i4 < i2) {
                        this.l.add(new Pair<>(a(this.f, i4), false));
                    }
                } else if (kVar == k.DASH) {
                    while (i3 < i2) {
                        this.l.add(new Pair<>(a(this.f, this.d.startNumber + i3), false));
                    }
                }
            } else if (kVar3 == k.WEBVTT) {
                this.A.lock();
                while (this.h.size() == 0) {
                    try {
                        if (this.C != null) {
                            if (this.u == j.INIT) {
                                break;
                            } else {
                                this.C.await();
                            }
                        }
                    } catch (InterruptedException unused) {
                    } catch (Throwable th) {
                        this.A.unlock();
                        throw th;
                    }
                }
                this.A.unlock();
                if (j.INIT == this.u) {
                    return;
                }
                c cVar2 = new c(this.f);
                this.y = cVar2;
                cVar2.start();
                String str = "";
                Iterator<g> it = this.h.iterator();
                while (it.hasNext()) {
                    String str2 = it.next().d;
                    if (str2 != null && !str2.equals(str)) {
                        this.l.add(new Pair<>(str2, false));
                        str = str2;
                    }
                }
                this.i = this.l.size();
            }
            if (j.INIT == this.u) {
                return;
            }
            f fVar = new f(this.i, this.l);
            this.k = fVar;
            fVar.start();
        } catch (IllegalStateException unused2) {
            a(j.NOT_AVAILABLE);
        }
    }

    private void n() {
        Thread thread = this.r;
        if (thread != null) {
            thread.interrupt();
            this.r = null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0033 A[Catch: all -> 0x00e1, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x000d, B:7:0x0014, B:9:0x001e, B:11:0x0027, B:12:0x002c, B:13:0x002d, B:14:0x002f, B:16:0x0033, B:18:0x003a, B:20:0x0044, B:22:0x004d, B:23:0x0052, B:24:0x0053, B:25:0x0055, B:27:0x0059, B:28:0x0061, B:30:0x0067, B:31:0x008d, B:32:0x0094, B:34:0x0098, B:35:0x009d, B:37:0x00a1, B:38:0x00a6, B:40:0x00ad, B:41:0x00b5, B:43:0x00bb, B:45:0x00cb, B:46:0x00cf, B:47:0x00d6, B:49:0x00da, B:50:0x00dd, B:8:0x0019, B:19:0x003f), top: B:56:0x0001, inners: #1, #2 }] */
    /* JADX WARN: Code duplicated, block: B:18:0x003a A[Catch: all -> 0x00e1, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x000d, B:7:0x0014, B:9:0x001e, B:11:0x0027, B:12:0x002c, B:13:0x002d, B:14:0x002f, B:16:0x0033, B:18:0x003a, B:20:0x0044, B:22:0x004d, B:23:0x0052, B:24:0x0053, B:25:0x0055, B:27:0x0059, B:28:0x0061, B:30:0x0067, B:31:0x008d, B:32:0x0094, B:34:0x0098, B:35:0x009d, B:37:0x00a1, B:38:0x00a6, B:40:0x00ad, B:41:0x00b5, B:43:0x00bb, B:45:0x00cb, B:46:0x00cf, B:47:0x00d6, B:49:0x00da, B:50:0x00dd, B:8:0x0019, B:19:0x003f), top: B:56:0x0001, inners: #1, #2 }] */
    /* JADX WARN: Code duplicated, block: B:27:0x0059 A[Catch: all -> 0x00e1, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x000d, B:7:0x0014, B:9:0x001e, B:11:0x0027, B:12:0x002c, B:13:0x002d, B:14:0x002f, B:16:0x0033, B:18:0x003a, B:20:0x0044, B:22:0x004d, B:23:0x0052, B:24:0x0053, B:25:0x0055, B:27:0x0059, B:28:0x0061, B:30:0x0067, B:31:0x008d, B:32:0x0094, B:34:0x0098, B:35:0x009d, B:37:0x00a1, B:38:0x00a6, B:40:0x00ad, B:41:0x00b5, B:43:0x00bb, B:45:0x00cb, B:46:0x00cf, B:47:0x00d6, B:49:0x00da, B:50:0x00dd, B:8:0x0019, B:19:0x003f), top: B:56:0x0001, inners: #1, #2 }] */
    /* JADX WARN: Code duplicated, block: B:30:0x0067 A[Catch: all -> 0x00e1, LOOP:0: B:28:0x0061->B:30:0x0067, LOOP_END, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x000d, B:7:0x0014, B:9:0x001e, B:11:0x0027, B:12:0x002c, B:13:0x002d, B:14:0x002f, B:16:0x0033, B:18:0x003a, B:20:0x0044, B:22:0x004d, B:23:0x0052, B:24:0x0053, B:25:0x0055, B:27:0x0059, B:28:0x0061, B:30:0x0067, B:31:0x008d, B:32:0x0094, B:34:0x0098, B:35:0x009d, B:37:0x00a1, B:38:0x00a6, B:40:0x00ad, B:41:0x00b5, B:43:0x00bb, B:45:0x00cb, B:46:0x00cf, B:47:0x00d6, B:49:0x00da, B:50:0x00dd, B:8:0x0019, B:19:0x003f), top: B:56:0x0001, inners: #1, #2 }] */
    /* JADX WARN: Code duplicated, block: B:34:0x0098 A[Catch: all -> 0x00e1, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x000d, B:7:0x0014, B:9:0x001e, B:11:0x0027, B:12:0x002c, B:13:0x002d, B:14:0x002f, B:16:0x0033, B:18:0x003a, B:20:0x0044, B:22:0x004d, B:23:0x0052, B:24:0x0053, B:25:0x0055, B:27:0x0059, B:28:0x0061, B:30:0x0067, B:31:0x008d, B:32:0x0094, B:34:0x0098, B:35:0x009d, B:37:0x00a1, B:38:0x00a6, B:40:0x00ad, B:41:0x00b5, B:43:0x00bb, B:45:0x00cb, B:46:0x00cf, B:47:0x00d6, B:49:0x00da, B:50:0x00dd, B:8:0x0019, B:19:0x003f), top: B:56:0x0001, inners: #1, #2 }] */
    /* JADX WARN: Code duplicated, block: B:37:0x00a1 A[Catch: all -> 0x00e1, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x000d, B:7:0x0014, B:9:0x001e, B:11:0x0027, B:12:0x002c, B:13:0x002d, B:14:0x002f, B:16:0x0033, B:18:0x003a, B:20:0x0044, B:22:0x004d, B:23:0x0052, B:24:0x0053, B:25:0x0055, B:27:0x0059, B:28:0x0061, B:30:0x0067, B:31:0x008d, B:32:0x0094, B:34:0x0098, B:35:0x009d, B:37:0x00a1, B:38:0x00a6, B:40:0x00ad, B:41:0x00b5, B:43:0x00bb, B:45:0x00cb, B:46:0x00cf, B:47:0x00d6, B:49:0x00da, B:50:0x00dd, B:8:0x0019, B:19:0x003f), top: B:56:0x0001, inners: #1, #2 }] */
    /* JADX WARN: Code duplicated, block: B:40:0x00ad A[Catch: all -> 0x00e1, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x000d, B:7:0x0014, B:9:0x001e, B:11:0x0027, B:12:0x002c, B:13:0x002d, B:14:0x002f, B:16:0x0033, B:18:0x003a, B:20:0x0044, B:22:0x004d, B:23:0x0052, B:24:0x0053, B:25:0x0055, B:27:0x0059, B:28:0x0061, B:30:0x0067, B:31:0x008d, B:32:0x0094, B:34:0x0098, B:35:0x009d, B:37:0x00a1, B:38:0x00a6, B:40:0x00ad, B:41:0x00b5, B:43:0x00bb, B:45:0x00cb, B:46:0x00cf, B:47:0x00d6, B:49:0x00da, B:50:0x00dd, B:8:0x0019, B:19:0x003f), top: B:56:0x0001, inners: #1, #2 }] */
    /* JADX WARN: Code duplicated, block: B:43:0x00bb A[Catch: all -> 0x00e1, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x000d, B:7:0x0014, B:9:0x001e, B:11:0x0027, B:12:0x002c, B:13:0x002d, B:14:0x002f, B:16:0x0033, B:18:0x003a, B:20:0x0044, B:22:0x004d, B:23:0x0052, B:24:0x0053, B:25:0x0055, B:27:0x0059, B:28:0x0061, B:30:0x0067, B:31:0x008d, B:32:0x0094, B:34:0x0098, B:35:0x009d, B:37:0x00a1, B:38:0x00a6, B:40:0x00ad, B:41:0x00b5, B:43:0x00bb, B:45:0x00cb, B:46:0x00cf, B:47:0x00d6, B:49:0x00da, B:50:0x00dd, B:8:0x0019, B:19:0x003f), top: B:56:0x0001, inners: #1, #2 }] */
    /* JADX WARN: Code duplicated, block: B:49:0x00da A[Catch: all -> 0x00e1, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x000d, B:7:0x0014, B:9:0x001e, B:11:0x0027, B:12:0x002c, B:13:0x002d, B:14:0x002f, B:16:0x0033, B:18:0x003a, B:20:0x0044, B:22:0x004d, B:23:0x0052, B:24:0x0053, B:25:0x0055, B:27:0x0059, B:28:0x0061, B:30:0x0067, B:31:0x008d, B:32:0x0094, B:34:0x0098, B:35:0x009d, B:37:0x00a1, B:38:0x00a6, B:40:0x00ad, B:41:0x00b5, B:43:0x00bb, B:45:0x00cb, B:46:0x00cf, B:47:0x00d6, B:49:0x00da, B:50:0x00dd, B:8:0x0019, B:19:0x003f), top: B:56:0x0001, inners: #1, #2 }] */
    /* JADX WARN: Code duplicated, block: B:63:0x00cb A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:65:0x00b5 A[SYNTHETIC] */
    private synchronized void o() {
        Thread thread;
        Hashtable<Integer, Pair<b, Future<?>>> hashtable;
        Thread thread2;
        ArrayList<Pair<String, Boolean>> arrayList;
        Hashtable<Integer, Bitmap> hashtable2;
        ArrayList<g> arrayList2;
        Iterator<Integer> it;
        Bitmap bitmap;
        this.u = j.INIT;
        this.D = 0;
        Thread thread3 = this.s;
        if (thread3 != null) {
            thread3.interrupt();
            if (this.C != null) {
                this.A.lock();
                try {
                    this.C.signal();
                    this.A.unlock();
                    this.C = null;
                } catch (Throwable th) {
                    this.A.unlock();
                    throw th;
                }
            }
            this.s = null;
            thread = this.y;
            if (thread != null) {
                thread.interrupt();
                if (this.B != null) {
                    this.A.lock();
                    try {
                        this.B.signalAll();
                        this.A.unlock();
                        this.B = null;
                    } catch (Throwable th2) {
                        this.A.unlock();
                        throw th2;
                    }
                }
                this.y = null;
            }
            hashtable = this.n;
            if (hashtable != null) {
                for (Integer num : hashtable.keySet()) {
                    ((Future) this.n.get(num).second).cancel(true);
                    ((b) this.n.get(num).first).cancel();
                }
                this.n.clear();
                this.n = null;
            }
            thread2 = this.k;
            if (thread2 != null) {
                thread2.interrupt();
                this.k = null;
            }
            arrayList = this.l;
            if (arrayList != null) {
                arrayList.clear();
                this.l = null;
            }
            n();
            hashtable2 = this.m;
            if (hashtable2 != null) {
                it = hashtable2.keySet().iterator();
                while (it.hasNext()) {
                    bitmap = this.m.get(it.next());
                    if (bitmap != null) {
                        bitmap.recycle();
                    }
                }
                this.m.clear();
                this.m = null;
            }
            arrayList2 = this.h;
            if (arrayList2 != null) {
                arrayList2.clear();
            }
            this.i = 0;
        } else {
            thread = this.y;
            if (thread != null) {
                thread.interrupt();
                if (this.B != null) {
                    this.A.lock();
                    this.B.signalAll();
                    this.A.unlock();
                    this.B = null;
                }
                this.y = null;
            }
            hashtable = this.n;
            if (hashtable != null) {
                while (r1.hasNext()) {
                    ((Future) this.n.get(num).second).cancel(true);
                    ((b) this.n.get(num).first).cancel();
                }
                this.n.clear();
                this.n = null;
            }
            thread2 = this.k;
            if (thread2 != null) {
                thread2.interrupt();
                this.k = null;
            }
            arrayList = this.l;
            if (arrayList != null) {
                arrayList.clear();
                this.l = null;
            }
            n();
            hashtable2 = this.m;
            if (hashtable2 != null) {
                it = hashtable2.keySet().iterator();
                while (it.hasNext()) {
                    bitmap = this.m.get(it.next());
                    if (bitmap != null) {
                        bitmap.recycle();
                    }
                }
                this.m.clear();
                this.m = null;
            }
            arrayList2 = this.h;
            if (arrayList2 != null) {
                arrayList2.clear();
            }
            this.i = 0;
        }
        throw th;
    }

    static /* synthetic */ int r(e eVar) {
        int i2 = eVar.D;
        eVar.D = i2 + 1;
        return i2;
    }

    int a() {
        int i2 = this.t;
        this.t = 0;
        return i2;
    }

    /* JADX WARN: Code duplicated, block: B:33:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:34:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:44:0x00d4 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:45:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:47:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:54:0x0122  */
    /* JADX WARN: Code duplicated, block: B:55:0x0126  */
    /* JADX WARN: Code duplicated, block: B:66:? A[RETURN, SYNTHETIC] */
    Bitmap a(int i2) {
        int i3;
        int iCeil;
        int i4;
        Hashtable<Integer, Bitmap> hashtable;
        Bitmap bitmap;
        float fB;
        int width;
        int height;
        k kVar = this.e;
        if (kVar == k.SERVER) {
            iCeil = (int) Math.ceil(((double) i2) / ((double) this.b.mInterval));
            i4 = this.b.mInterval;
        } else {
            if (kVar != k.DASH) {
                if (this.g && kVar == k.WEBVTT) {
                    if (this.h.size() <= 0) {
                        return null;
                    }
                    iCeil = (int) Math.ceil(i2 / this.h.get(0).f133a);
                    i4 = this.h.get(0).f133a;
                } else {
                    if (this.e != k.WEBVTT) {
                        return null;
                    }
                    int iIntValue = 0;
                    for (Integer num : this.m.keySet()) {
                        if (i2 > num.intValue() && num.intValue() > iIntValue) {
                            iIntValue = num.intValue();
                        }
                    }
                    i3 = iIntValue;
                }
                hashtable = this.m;
                if (hashtable != null) {
                    bitmap = hashtable.get(Integer.valueOf(i3));
                } else {
                    bitmap = null;
                }
                if (bitmap == null && this.e == k.WEBVTT && this.u == j.COMPLETE && i2 <= this.f127a.getPrimaryDuration()) {
                    if (this.h.size() <= 0) {
                        return null;
                    }
                    ArrayList<g> arrayList = this.h;
                    i3 = arrayList.get(arrayList.size() - 1).b;
                    bitmap = this.m.get(Integer.valueOf(i3));
                }
                if (bitmap != null) {
                    return bitmap;
                }
                if (this.e == k.DASH && this.f127a.getPrimaryDuration() == 0) {
                    i3 -= this.f127a.getHttpStreamingSeekableRange()[0];
                }
                fB = this.f127a.b();
                width = bitmap.getWidth();
                height = bitmap.getHeight();
                if (fB > 1.0d) {
                    height = (int) (height / fB);
                } else {
                    width = (int) (width / fB);
                }
                Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
                this.t = i3;
                return bitmapCreateScaledBitmap;
            }
            if (this.f127a.getPrimaryDuration() == 0) {
                i2 += this.f127a.getHttpStreamingSeekableRange()[0];
            }
            iCeil = (int) Math.ceil(((double) i2) / ((double) (this.b.mInterval * 1000)));
            i4 = this.b.mInterval * 1000;
        }
        i3 = iCeil * i4;
        hashtable = this.m;
        if (hashtable != null) {
            bitmap = hashtable.get(Integer.valueOf(i3));
        } else {
            bitmap = null;
        }
        if (bitmap == null) {
            if (this.h.size() <= 0) {
                return null;
            }
            ArrayList<g> arrayList2 = this.h;
            i3 = arrayList2.get(arrayList2.size() - 1).b;
            bitmap = this.m.get(Integer.valueOf(i3));
        }
        if (bitmap != null) {
            return bitmap;
        }
        if (this.e == k.DASH) {
            i3 -= this.f127a.getHttpStreamingSeekableRange()[0];
        }
        fB = this.f127a.b();
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        if (fB > 1.0d) {
            height = (int) (height / fB);
        } else {
            width = (int) (width / fB);
        }
        Bitmap bitmapCreateScaledBitmap2 = Bitmap.createScaledBitmap(bitmap, width, height, false);
        this.t = i3;
        return bitmapCreateScaledBitmap2;
    }

    void a(VOPlayer.ThumbnailContext thumbnailContext) {
        this.d = thumbnailContext;
    }

    synchronized void a(String str, VOPlayer.ScrubbingSpriteInfo scrubbingSpriteInfo) {
        n nVar;
        h hVar;
        l lVar;
        this.f = str;
        this.D = 0;
        F = 1;
        G = 3;
        H = 5;
        I = 15;
        HashMap<VOPlayer.ThreadPriority, Integer> map = this.w.threadPriority;
        if (map != null) {
            for (VOPlayer.ThreadPriority threadPriority : map.keySet()) {
                if (threadPriority == VOPlayer.ThreadPriority.SCRUBBING_THREAD_LOW) {
                    F = this.w.threadPriority.get(threadPriority).intValue() < 30 ? this.w.threadPriority.get(threadPriority).intValue() : F;
                } else if (threadPriority == VOPlayer.ThreadPriority.SCRUBBING_THREAD_MEDIUM_LOW) {
                    G = this.w.threadPriority.get(threadPriority).intValue() < 30 ? this.w.threadPriority.get(threadPriority).intValue() : G;
                } else if (threadPriority == VOPlayer.ThreadPriority.SCRUBBING_THREAD_MEDIUM) {
                    H = this.w.threadPriority.get(threadPriority).intValue() < 30 ? this.w.threadPriority.get(threadPriority).intValue() : H;
                } else if (threadPriority == VOPlayer.ThreadPriority.SCRUBBING_THREAD_HIGH) {
                    I = this.w.threadPriority.get(threadPriority).intValue() < 30 ? this.w.threadPriority.get(threadPriority).intValue() : I;
                }
            }
        }
        J = GmsVersion.VERSION_LONGHORN;
        K = GmsVersion.VERSION_ORLA;
        L = 10000000;
        HashMap<VOPlayer.BandwidthThreshold, Integer> map2 = this.w.bandwidthThreshold;
        if (map2 != null) {
            for (VOPlayer.BandwidthThreshold bandwidthThreshold : map2.keySet()) {
                if (bandwidthThreshold == VOPlayer.BandwidthThreshold.SCRUBBING_BANDWIDTH_LOW) {
                    J = this.w.bandwidthThreshold.get(bandwidthThreshold).intValue();
                } else if (bandwidthThreshold == VOPlayer.BandwidthThreshold.SCRUBBING_BANDWIDTH_MEDIUM) {
                    K = this.w.bandwidthThreshold.get(bandwidthThreshold).intValue();
                } else if (bandwidthThreshold == VOPlayer.BandwidthThreshold.SCRUBBING_BANDWIDTH_HIGH) {
                    L = this.w.bandwidthThreshold.get(bandwidthThreshold).intValue();
                }
            }
        }
        this.u = j.CONFIGURED;
        this.m = new Hashtable<>();
        this.B = this.A.newCondition();
        if (str == null && scrubbingSpriteInfo == null && this.e == k.UNDEFINED) {
            this.e = k.DASH;
        }
        k kVar = this.e;
        a aVar = null;
        if (kVar == k.UNDEFINED) {
            if (str.contains(".vtt")) {
                this.e = k.WEBVTT;
                this.h = new ArrayList<>();
                this.C = this.A.newCondition();
                C0188e c0188e = new C0188e(str);
                this.s = c0188e;
                c0188e.start();
            } else if (str.contains(".vtt") || scrubbingSpriteInfo == null) {
                a(j.NOT_AVAILABLE);
            } else {
                this.e = k.SERVER;
                this.b = scrubbingSpriteInfo;
                this.c = new h(this, aVar);
                if (this.f.contains("sprite=CATCHUP")) {
                    hVar = this.c;
                    lVar = l.STARTOVER;
                } else if (this.f.contains("sprite=LIVE")) {
                    hVar = this.c;
                    lVar = l.LIVE;
                } else {
                    hVar = this.c;
                    lVar = l.VOD;
                }
                hVar.e = lVar;
                h hVar2 = this.c;
                VOPlayer.ScrubbingSpriteInfo scrubbingSpriteInfo2 = this.b;
                hVar2.c = scrubbingSpriteInfo2.mSpriteLines * scrubbingSpriteInfo2.mSpriteColums * scrubbingSpriteInfo2.mInterval;
                if (this.x) {
                    nVar = new n(this, aVar);
                    this.r = nVar;
                    nVar.start();
                }
            }
        } else if (kVar == k.DASH) {
            if (this.b != null) {
                h hVar3 = new h(this, aVar);
                this.c = hVar3;
                hVar3.e = l.VOD;
                h hVar4 = this.c;
                VOPlayer.ScrubbingSpriteInfo scrubbingSpriteInfo3 = this.b;
                hVar4.c = scrubbingSpriteInfo3.mSpriteLines * scrubbingSpriteInfo3.mSpriteColums * scrubbingSpriteInfo3.mInterval;
            }
            if (this.x) {
                nVar = new n(this, aVar);
                this.r = nVar;
                nVar.start();
            }
        }
    }

    void a(boolean z) {
        if (this.e == k.SERVER && this.c.e != l.VOD && this.u == j.COMPLETE && this.i == 0) {
            int iA = this.c.e == l.LIVE ? (int) a(this.f) : this.f127a.getPrimaryDuration();
            int i2 = iA - this.c.f134a;
            if ((i2 <= 0 || i2 < this.c.d) && !z) {
                return;
            }
            this.z = null;
            if (this.c.e == l.STARTOVER) {
                this.f = a(this.f, (Boolean) true);
            }
            int i3 = i2 > this.c.c ? i2 / this.c.c : 1;
            this.l = new ArrayList<>();
            for (int i4 = this.c.b; i4 < this.c.b + i3; i4++) {
                this.l.add(new Pair<>(a(this.f, i4), false));
            }
            this.i = this.l.size();
            this.c.b += this.i;
            this.c.f134a = iA;
            h hVar = this.c;
            hVar.d = hVar.c;
            this.k = null;
            f fVar = new f(this.i, this.l);
            this.k = fVar;
            fVar.start();
        }
    }

    j b() {
        return this.u;
    }

    void b(boolean z) {
        VOPlayer.ThumbnailContext thumbnailContext = this.d;
        if (thumbnailContext == null || thumbnailContext.thumbnailTrackInfo == null) {
            return;
        }
        this.x = z;
        if (this.b == null) {
            this.b = new VOPlayer.ScrubbingSpriteInfo();
        }
        if (this.e == k.UNDEFINED) {
            this.e = k.DASH;
        }
        VOPlayer.ThumbnailTrackInfo thumbnailTrackInfo = this.d.thumbnailTrackInfo[0];
        String str = thumbnailTrackInfo.value;
        String[] strArrSplit = str.split("x");
        if (strArrSplit.length == 2) {
            this.b.mSpriteColums = Integer.parseInt(strArrSplit[0], 10);
            this.b.mSpriteLines = Integer.parseInt(strArrSplit[1], 10);
        } else {
            this.b.mSpriteColums = Integer.parseInt(str, 10);
            this.b.mSpriteLines = 1;
        }
        VOPlayer.ScrubbingSpriteInfo scrubbingSpriteInfo = this.b;
        scrubbingSpriteInfo.mInterval = Math.round(this.d.duration / (scrubbingSpriteInfo.mSpriteColums * scrubbingSpriteInfo.mSpriteLines));
        a(thumbnailTrackInfo.thumbnailURL, this.b);
    }

    synchronized void c() {
        Thread thread = this.k;
        if (thread != null) {
            thread.interrupt();
            this.k = null;
        }
        Hashtable<Integer, Pair<b, Future<?>>> hashtable = this.n;
        if (hashtable != null) {
            synchronized (hashtable) {
                for (Integer num : this.n.keySet()) {
                    ((Future) this.n.get(num).second).cancel(true);
                    ((b) this.n.get(num).first).cancel();
                }
                this.n.clear();
                this.n = null;
            }
        }
    }

    protected void d() {
        this.u = j.INIT;
        this.e = k.UNDEFINED;
        new m().start();
        n();
        o();
    }

    @Override // com.viaccessorca.voplayer.VOPlayer.OnBufferingUpdateListener
    public void onBufferingUpdate(VOPlayer vOPlayer, int i2) {
        if (!this.x && i2 == 100 && this.u == j.CONFIGURED) {
            n nVar = new n(this, null);
            this.r = nVar;
            nVar.start();
        }
    }
}
