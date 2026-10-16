package com.viaccessorca.voplayer;

import android.os.Build;
import android.os.Message;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaLoadOptions;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.viaccessorca.drm.impl.DrmAgent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class b {
    private static CastSession L = null;
    private static boolean M = false;
    private static boolean N = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private CastContext f115a = null;
    private boolean b = false;
    private VOPlayer c = null;
    private SessionManager d = null;
    private MediaMetadata e = null;
    private int f = 0;
    private boolean g = false;
    private int h = -1;
    private int i = -1;
    private String j = null;
    private String k = null;
    private String[] l = null;
    private String[] m = null;
    private String n = null;
    private DrmAgent.EDrmType o = DrmAgent.EDrmType.DRM_TYPE_UNKNOWN;
    private boolean p = true;
    private boolean q = true;
    private boolean r = false;
    private String s = null;
    private String t = null;
    private String u = null;
    private String[] v = null;
    private String[] w = null;
    private String x = null;
    private VOCCastExternalSubtitle[] y = null;
    private C0185b z = null;
    private VOAudioTrack[] A = null;
    private VOSubtitleTrack[] B = null;
    private HashMap<Long, VOSubtitleTrack> C = null;
    private HashMap<Long, VOAudioTrack> D = null;
    private String E = null;
    private int F = 0;
    private String G = null;
    private Lock H = null;
    private Lock I = null;
    private final RemoteMediaClient.Callback J = new c();
    private SessionManagerListener K = null;

    class a implements ResultCallback {
        a(b bVar) {
        }

        @Override // com.google.android.gms.common.api.ResultCallback
        public void onResult(Result result) {
            if (result instanceof RemoteMediaClient.MediaChannelResult) {
                ((RemoteMediaClient.MediaChannelResult) result).getStatus().isSuccess();
            }
        }
    }

    /* JADX INFO: renamed from: com.viaccessorca.voplayer.b$b, reason: collision with other inner class name */
    private class C0185b implements Cast.MessageReceivedCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f116a;

        C0185b(b bVar, String str) {
            this.f116a = null;
            this.f116a = str;
        }

        public String getNamespace() {
            return this.f116a;
        }

        public void onMessageReceived(CastDevice castDevice, String str, String str2) {
            try {
                new JSONObject(str2).getString("type").equals("playbackerror");
            } catch (JSONException unused) {
            }
        }
    }

    private class c extends RemoteMediaClient.Callback {
        public c() {
        }

        public void onStatusUpdated() {
            if (b.L == null || b.L.getRemoteMediaClient() == null) {
                return;
            }
            int playerState = b.L.getRemoteMediaClient().getPlayerState();
            b.this.i = playerState;
            if (playerState == 1) {
                int idleReason = b.L.getRemoteMediaClient().getIdleReason();
                if (idleReason == 1) {
                    b.this.e(2);
                    b.this.r = false;
                    b.this.f = 0;
                    b.this.v();
                } else if (idleReason == 4) {
                    b.this.e(200);
                    b.this.r = false;
                }
            } else if (playerState == 2) {
                b.this.r = true;
                if (b.this.g) {
                    b.this.w();
                    b.this.a();
                    b.this.g = false;
                }
            } else if (playerState == 4 && b.this.h != -1) {
                b bVar = b.this;
                bVar.a(bVar.h);
                b.this.h = -1;
            }
            super.onMetadataUpdated();
        }
    }

    private class d implements SessionManagerListener {
        private d() {
        }

        /* synthetic */ d(b bVar, a aVar) {
            this();
        }

        public void onSessionEnded(Session session, int i) {
            if (b.N) {
                return;
            }
            boolean unused = b.M = false;
            CastSession unused2 = b.L = b.this.d.getCurrentCastSession();
            if (b.L != null && b.L.getRemoteMediaClient() != null) {
                b.L.getRemoteMediaClient().unregisterCallback(b.this.J);
            }
            try {
                if (b.this.q) {
                    b.this.c.startAt(b.this.f);
                } else {
                    b.this.c.seekTo(b.this.f);
                }
            } catch (IllegalStateException unused3) {
            }
            if (b.this.E != null && b.this.F != 1) {
                b.this.c.setSubtitleTrack(b.this.E, b.this.F);
            }
            if (b.this.G != null) {
                b.this.c.setAudioTrack(b.this.G);
            }
        }

        public void onSessionEnding(Session session) {
            b bVar = b.this;
            bVar.q = bVar.f();
            b.this.v();
        }

        public void onSessionResumeFailed(Session session, int i) {
        }

        public void onSessionResumed(Session session, boolean z) {
            boolean unused = b.N = false;
            CastSession unused2 = b.L = b.this.d.getCurrentCastSession();
        }

        public void onSessionResuming(Session session, String str) {
        }

        public void onSessionStartFailed(Session session, int i) {
        }

        public void onSessionStarted(Session session, String str) {
            CastSession unused = b.L = b.this.d.getCurrentCastSession();
            boolean unused2 = b.M = true;
            boolean unused3 = b.N = false;
            if (b.this.z != null && b.L != null) {
                try {
                    b.L.setMessageReceivedCallbacks(b.this.z.getNamespace(), b.this.z);
                } catch (IOException unused4) {
                }
            }
            if (b.L != null && b.L.getRemoteMediaClient() != null) {
                b.L.getRemoteMediaClient().registerCallback(b.this.J);
            }
            b bVar = b.this;
            bVar.p = bVar.c.c();
            boolean unused5 = b.this.p;
            try {
                b.this.c.prepareChromeCast();
            } catch (IllegalStateException unused6) {
            }
        }

        public void onSessionStarting(Session session) {
        }

        public void onSessionSuspended(Session session, int i) {
            boolean unused = b.N = true;
        }
    }

    protected b(Object obj, VOPlayer vOPlayer) {
        a(vOPlayer);
        a((CastContext) obj);
    }

    protected b(String str, VOPlayer vOPlayer) {
        a(vOPlayer);
        b(str);
    }

    private int a(long j) {
        ArrayList<Long> arrayList = new ArrayList<>();
        long[] jArrO = o();
        if (-1 == j) {
            return -1;
        }
        for (long j2 : jArrO) {
            if (j2 != j) {
                arrayList.add(Long.valueOf(j2));
            }
        }
        return a(arrayList);
    }

    private int a(long j, int i) {
        ArrayList<Long> arrayList = new ArrayList<>();
        long[] jArrO = o();
        long jC = c(i);
        if (-1 == j) {
            return -1;
        }
        for (long j2 : jArrO) {
            if (j2 != jC) {
                arrayList.add(Long.valueOf(j2));
            }
        }
        arrayList.add(Long.valueOf(j));
        return a(arrayList);
    }

    private int a(ArrayList<Long> arrayList) {
        if (arrayList == null) {
            return -1;
        }
        int size = arrayList.size();
        long[] jArr = new long[size];
        for (int i = 0; i < size; i++) {
            jArr[i] = arrayList.get(i).longValue();
        }
        return a(jArr);
    }

    private int a(long[] jArr) {
        CastSession castSession = L;
        if (castSession == null || castSession.getRemoteMediaClient() == null || jArr == null || jArr.length == 0) {
            return -1;
        }
        for (long j : jArr) {
        }
        L.getRemoteMediaClient().setActiveMediaTracks(jArr).setResultCallback(new a(this));
        ArrayList arrayList = new ArrayList();
        for (long j2 : jArr) {
            arrayList.add(Long.valueOf(j2));
        }
        synchronized (this.H) {
            if (this.D != null) {
                Iterator<Long> it = this.D.keySet().iterator();
                while (it.hasNext()) {
                    long jLongValue = it.next().longValue();
                    b(jLongValue).setSelected(arrayList.contains(Long.valueOf(jLongValue)));
                }
            }
        }
        synchronized (this.I) {
            if (this.C != null) {
                Iterator<Long> it2 = this.C.keySet().iterator();
                while (it2.hasNext()) {
                    long jLongValue2 = it2.next().longValue();
                    c(jLongValue2).setSelected(arrayList.contains(Long.valueOf(jLongValue2)));
                }
            }
        }
        return 0;
    }

    private void a(VOPlayer vOPlayer) {
        if (this.b) {
            return;
        }
        this.c = vOPlayer;
        this.b = true;
        this.H = new ReentrantReadWriteLock().writeLock();
        this.I = new ReentrantReadWriteLock().writeLock();
        this.g = true;
    }

    private void a(String str, String str2, String str3, double d2, boolean z) {
        int i;
        long j = (long) d2;
        if (z) {
            i = 2;
            if (j != 0) {
                this.h = (int) j;
            }
            j = -1;
        } else {
            i = 1;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("licenseUrl", str2);
            jSONObject.put("protectionSystem", str3);
            jSONObject.put("licenseCustomData", this.x == null ? this.k : this.x);
            if (this.v != null || this.l != null) {
                jSONObject.put("cookies", Build.VERSION.SDK_INT >= 19 ? new JSONArray(this.v == null ? this.l : this.v) : null);
            }
            if (this.w != null || this.m != null) {
                jSONObject.put("customHeaders", new JSONArray(this.w == null ? this.m : this.w));
            }
            ArrayList arrayList = new ArrayList();
            if (this.y != null && this.y.length > 0) {
                for (int i2 = 0; i2 < this.y.length; i2++) {
                    arrayList.add(new MediaTrack.Builder(i2, 1).setName(this.y[i2].getName()).setSubtype(1).setContentId(this.y[i2].getUrl()).setLanguage(this.y[i2].getLanguage()).build());
                }
            }
            RemoteMediaClient remoteMediaClient = L.getRemoteMediaClient();
            if (remoteMediaClient == null || str == null) {
                return;
            }
            remoteMediaClient.load(new MediaInfo.Builder(str).setStreamType(i).setContentType(f(str)).setMetadata(this.e).setMediaTracks(arrayList).setCustomData(jSONObject).build(), new MediaLoadOptions.Builder().setAutoplay(this.p).setPlayPosition(j).setActiveTrackIds((long[]) null).build());
        } catch (JSONException unused) {
        }
    }

    private boolean a(String str, String str2, String str3) {
        String string;
        String str4;
        RemoteMediaClient remoteMediaClient = L.getRemoteMediaClient();
        String contentId = remoteMediaClient.getMediaInfo().getContentId();
        String string2 = null;
        try {
            JSONObject customData = remoteMediaClient.getMediaInfo().getCustomData();
            if (customData != null) {
                string = customData.getString("licenseUrl");
                try {
                    string2 = customData.getString("protectionSystem");
                } catch (JSONException unused) {
                }
                str4 = string2;
                string2 = string;
            } else {
                str4 = null;
            }
        } catch (JSONException unused2) {
            string = null;
        }
        if (contentId != null && contentId.equals(str)) {
            if (string2 == null && str2 == null) {
                return true;
            }
            if (string2 != null && string2.equals(str2) && str4 != null && str4.equals(str3)) {
                return true;
            }
        }
        return false;
    }

    private long b(String str, int i) {
        long jLongValue = -1;
        if (i != 1) {
            if (i == 2) {
                synchronized (this.H) {
                    if (this.D != null) {
                        for (Long l : this.D.keySet()) {
                            if (str.equals(this.D.get(l).getName())) {
                                jLongValue = l.longValue();
                                break;
                            }
                        }
                    }
                }
            }
        } else {
            synchronized (this.I) {
                if (this.C != null) {
                    for (Long l2 : this.C.keySet()) {
                        if (str.equals(this.C.get(l2).getName())) {
                            jLongValue = l2.longValue();
                            break;
                        }
                    }
                }
            }
        }
        return jLongValue;
    }

    private VOAudioTrack b(long j) {
        VOAudioTrack vOAudioTrack;
        synchronized (this.H) {
            vOAudioTrack = (this.D == null || !this.D.containsKey(Long.valueOf(j))) ? null : this.D.get(Long.valueOf(j));
        }
        return vOAudioTrack;
    }

    private long c(int i) {
        long j = -1;
        for (long j2 : o()) {
            if (i == 1 ? c(j2) != null : !(i != 2 || b(j2) == null)) {
                j = j2;
            }
            if (-1 != j) {
                break;
            }
        }
        return j;
    }

    private VOSubtitleTrack c(long j) {
        VOSubtitleTrack vOSubtitleTrack;
        synchronized (this.I) {
            vOSubtitleTrack = (this.C == null || !this.C.containsKey(Long.valueOf(j))) ? null : this.C.get(Long.valueOf(j));
        }
        return vOSubtitleTrack;
    }

    private int d(int i) {
        int[] httpStreamingSeekableRange = this.c.getHttpStreamingSeekableRange();
        int i2 = httpStreamingSeekableRange[1] - httpStreamingSeekableRange[0];
        if (i2 <= 0) {
            return i;
        }
        long jP = p();
        long jQ = q();
        long j = jP - jQ;
        return (0 == jP || 0 >= j) ? i : (int) (((((long) Math.round((i * 100) / i2)) * j) / 100) + jQ);
    }

    private boolean d(long j) {
        VOCCastExternalSubtitle[] vOCCastExternalSubtitleArr = this.y;
        return vOCCastExternalSubtitleArr != null && ((long) vOCCastExternalSubtitleArr.length) >= j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        int i2;
        VOPlayer vOPlayer = this.c;
        if (vOPlayer != null) {
            Message messageObtainMessage = vOPlayer.j.obtainMessage();
            if (i != 2) {
                if (i == 200) {
                    messageObtainMessage.what = 200;
                    messageObtainMessage.arg1 = VOPlayer.MEDIA_INFO_CHROMECAST;
                    i2 = VOPlayer.MEDIA_INFO_CHROMECAST_ERROR_PLAYBACK;
                }
                this.c.j.sendMessage(messageObtainMessage);
            }
            messageObtainMessage.what = 2;
            i2 = 0;
            messageObtainMessage.arg1 = 0;
            messageObtainMessage.arg2 = i2;
            this.c.j.sendMessage(messageObtainMessage);
        }
    }

    private String f(String str) {
        if (str.indexOf(".mpd") != -1) {
            return MimeTypes.APPLICATION_MPD;
        }
        if (str.indexOf(".m3u8") != -1) {
            return "application/x-mpegurl";
        }
        return str.indexOf(".ism") != -1 ? MimeTypes.APPLICATION_SS : "";
    }

    private int g(String str) {
        if (str == null) {
            return 0;
        }
        if (str.equals("text/vtt")) {
            return 3;
        }
        if (str.equals(MimeTypes.APPLICATION_TTML) || str.equals(MimeTypes.APPLICATION_MP4)) {
            return 6;
        }
        return str.equals("text/cea-708") ? 2 : 0;
    }

    private long[] o() {
        CastSession castSession = L;
        return (castSession == null || castSession.getRemoteMediaClient() == null || L.getRemoteMediaClient().getMediaStatus() == null || L.getRemoteMediaClient().getMediaStatus().getActiveTrackIds() == null) ? new long[0] : L.getRemoteMediaClient().getMediaStatus().getActiveTrackIds();
    }

    private long p() {
        CastSession castSession = L;
        if (castSession == null || castSession.getRemoteMediaClient() == null || this.i == -1) {
            return 0L;
        }
        return L.getRemoteMediaClient().getApproximateLiveSeekableRangeEnd();
    }

    private long q() {
        CastSession castSession = L;
        if (castSession == null || castSession.getRemoteMediaClient() == null || this.i == -1) {
            return 0L;
        }
        return L.getRemoteMediaClient().getApproximateLiveSeekableRangeStart();
    }

    private long r() {
        CastSession castSession = L;
        if (castSession == null || castSession.getRemoteMediaClient() == null || this.i == -1) {
            return 0L;
        }
        return L.getRemoteMediaClient().getApproximateStreamPosition();
    }

    private List<MediaTrack> s() {
        CastSession castSession = L;
        return (castSession == null || castSession.getRemoteMediaClient() == null || L.getRemoteMediaClient().getMediaInfo() == null || L.getRemoteMediaClient().getMediaInfo().getMediaTracks() == null) ? new ArrayList(0) : L.getRemoteMediaClient().getMediaInfo().getMediaTracks();
    }

    protected static boolean t() {
        return L != null && M;
    }

    private boolean u() {
        CastSession castSession = L;
        if (castSession == null || castSession.getRemoteMediaClient() == null) {
            return false;
        }
        return L.getRemoteMediaClient().isLiveStream();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        long[] jArrO = o();
        CastSession castSession = L;
        if (castSession == null || castSession.getRemoteMediaClient() == null || L.getRemoteMediaClient().getMediaStatus() == null) {
            return;
        }
        VOAudioTrack vOAudioTrack = null;
        VOSubtitleTrack vOSubtitleTrack = null;
        for (long j : jArrO) {
            VOAudioTrack vOAudioTrackB = b(j);
            VOSubtitleTrack vOSubtitleTrackC = c(j);
            if (vOSubtitleTrackC != null) {
                vOSubtitleTrack = vOSubtitleTrackC;
            }
            if (vOAudioTrackB != null) {
                vOAudioTrack = vOAudioTrackB;
            }
            if (vOAudioTrack != null && vOSubtitleTrack != null) {
                break;
            }
        }
        this.G = null;
        if (vOAudioTrack != null) {
            this.G = vOAudioTrack.getName();
        }
        this.F = 0;
        this.E = null;
        if (vOSubtitleTrack != null) {
            this.E = vOSubtitleTrack.getName();
            this.F = vOSubtitleTrack.getSubtitleType();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        synchronized (this.I) {
            if (this.C == null) {
                h();
            }
        }
        synchronized (this.H) {
            if (this.D == null) {
                g();
            }
        }
    }

    protected int a(String str) {
        if (this.r) {
            return a(b(str, 2), 2);
        }
        this.G = str;
        return 0;
    }

    protected int a(String str, int i) {
        if (!this.r) {
            this.E = str;
            this.F = i;
        } else {
            if (str != null) {
                return a(b(str, 1), 1);
            }
            l();
        }
        return 0;
    }

    protected void a() {
        long jLongValue;
        long jLongValue2;
        int i;
        HashMap<Long, VOAudioTrack> map;
        HashMap<Long, VOSubtitleTrack> map2;
        if (this.E != null && (map2 = this.C) != null) {
            Iterator<Long> it = map2.keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    jLongValue = -1;
                    break;
                }
                Long next = it.next();
                if (this.E.equals(c(next.longValue()).getName())) {
                    jLongValue = next.longValue();
                    this.E = null;
                    break;
                }
            }
        } else {
            jLongValue = -1;
            break;
        }
        if (this.G != null && (map = this.D) != null) {
            Iterator<Long> it2 = map.keySet().iterator();
            while (true) {
                if (!it2.hasNext()) {
                    jLongValue2 = -1;
                    break;
                }
                Long next2 = it2.next();
                if (this.G.equals(b(next2.longValue()).getName())) {
                    jLongValue2 = next2.longValue();
                    this.G = null;
                    break;
                }
            }
        } else {
            jLongValue2 = -1;
            break;
        }
        int i2 = jLongValue2 != -1 ? 1 : 0;
        if (jLongValue != -1) {
            i2++;
        }
        long[] jArr = new long[i2];
        if (jLongValue2 != -1) {
            i = i2 - 1;
            jArr[i] = jLongValue2;
        } else {
            i = i2;
        }
        if (jLongValue != -1) {
            jArr[i - 1] = jLongValue;
        }
        if (i2 > 0) {
            a(jArr);
        }
    }

    protected void a(double d2, VOAudioTrack[] vOAudioTrackArr, String str, VOSubtitleTrack[] vOSubtitleTrackArr, String str2, boolean z) {
        String str3 = this.s;
        if (str3 == null) {
            str3 = this.j;
        }
        String str4 = str3;
        String str5 = this.t;
        if (str5 == null) {
            str5 = this.n;
        }
        String str6 = str5;
        String string = this.u;
        if (string == null) {
            string = this.o.toString();
        }
        String str7 = string;
        RemoteMediaClient remoteMediaClient = L.getRemoteMediaClient();
        if (remoteMediaClient != null) {
            this.A = vOAudioTrackArr;
            this.B = vOSubtitleTrackArr;
            this.G = str;
            if (str == null && vOAudioTrackArr != null) {
                for (VOAudioTrack vOAudioTrack : vOAudioTrackArr) {
                    if (vOAudioTrack.IsSelected()) {
                        this.G = vOAudioTrack.getName();
                        break;
                    }
                }
            }
            this.E = str2;
            this.F = 0;
            this.D = null;
            this.C = null;
            this.g = true;
            if (remoteMediaClient.hasMediaSession() && remoteMediaClient.getMediaInfo() != null) {
                if (a(str4, str6, str7)) {
                    if (remoteMediaClient.isPaused() && this.r) {
                        remoteMediaClient.play();
                    }
                    this.r = true;
                    w();
                    return;
                }
                remoteMediaClient.stop();
            }
            a(str4, str6, str7, d2, z);
        }
    }

    protected void a(int i) {
        RemoteMediaClient remoteMediaClient = L.getRemoteMediaClient();
        if (remoteMediaClient != null) {
            if (u()) {
                i = d(i);
            }
            remoteMediaClient.seek(i);
        }
    }

    protected void a(DrmAgent.EDrmType eDrmType) {
        this.o = eDrmType;
    }

    protected void a(Object obj) {
        a aVar = null;
        if (obj == null) {
            this.f115a = null;
            SessionManager sessionManager = this.d;
            if (sessionManager != null) {
                sessionManager.removeSessionManagerListener(this.K);
            }
            CastSession castSession = L;
            if (castSession != null && castSession.getRemoteMediaClient() != null) {
                L.getRemoteMediaClient().unregisterCallback(this.J);
            }
            this.d = null;
            L = null;
            this.z = null;
            this.r = false;
            return;
        }
        this.f115a = (CastContext) obj;
        if (this.K == null) {
            this.K = new d(this, aVar);
        }
        SessionManager sessionManager2 = this.f115a.getSessionManager();
        this.d = sessionManager2;
        sessionManager2.addSessionManagerListener(this.K);
        CastSession currentCastSession = this.d.getCurrentCastSession();
        L = currentCastSession;
        C0185b c0185b = this.z;
        if (c0185b != null && currentCastSession != null) {
            try {
                currentCastSession.setMessageReceivedCallbacks(c0185b.getNamespace(), this.z);
            } catch (IOException unused) {
            }
        }
        CastSession castSession2 = L;
        if (castSession2 == null || castSession2.getRemoteMediaClient() == null) {
            return;
        }
        L.getRemoteMediaClient().registerCallback(this.J);
    }

    protected void a(String str, String str2, DrmAgent.EDrmType eDrmType, String[] strArr, String[] strArr2, String str3, VOCCastExternalSubtitle[] vOCCastExternalSubtitleArr) {
        if (str != null) {
            this.s = str;
        }
        if (str2 != null && !str2.isEmpty()) {
            this.t = str2;
        }
        if (eDrmType != null && !eDrmType.toString().equals("UNKNOWN")) {
            this.u = eDrmType.toString();
        }
        if (strArr != null && strArr.length != 0) {
            String[] strArr3 = new String[strArr.length];
            this.v = strArr3;
            System.arraycopy(strArr, 0, strArr3, 0, strArr.length);
        }
        if (strArr2 != null && strArr2.length != 0) {
            String[] strArr4 = new String[strArr2.length];
            this.w = strArr4;
            System.arraycopy(strArr2, 0, strArr4, 0, strArr2.length);
        }
        if (str3 != null && !str3.isEmpty()) {
            this.x = str3;
        }
        if (vOCCastExternalSubtitleArr == null || vOCCastExternalSubtitleArr.length <= 0) {
            return;
        }
        this.y = vOCCastExternalSubtitleArr;
    }

    protected void a(String[] strArr) {
        if (strArr == null) {
            this.l = null;
            return;
        }
        for (String str : strArr) {
            this.l = strArr[0].split(";");
        }
    }

    protected void b(int i) {
        this.f = i;
    }

    protected void b(Object obj) {
        this.e = obj != null ? (MediaMetadata) obj : null;
    }

    protected void b(String str) {
        if (this.z == null) {
            C0185b c0185b = new C0185b(this, str);
            this.z = c0185b;
            CastSession castSession = L;
            if (castSession != null) {
                M = true;
                try {
                    castSession.setMessageReceivedCallbacks(c0185b.getNamespace(), this.z);
                } catch (IOException unused) {
                }
            }
        }
    }

    protected void b(String[] strArr) {
        this.m = strArr;
    }

    protected VOAudioTrack[] b() {
        HashMap<Long, VOAudioTrack> map = this.D;
        if (map == null) {
            return null;
        }
        return (VOAudioTrack[]) map.values().toArray(new VOAudioTrack[this.D.size()]);
    }

    protected int c() {
        long jR = r();
        long jQ = q();
        long jP = p();
        long j = jP - jQ;
        if (u() && 0 != jP && 0 < j) {
            int[] httpStreamingSeekableRange = this.c.getHttpStreamingSeekableRange();
            long j2 = httpStreamingSeekableRange[1] - httpStreamingSeekableRange[0];
            long j3 = jR - jQ;
            jR = (j3 < 0 || j == 0) ? j2 : (j3 * j2) / j;
        }
        return (int) jR;
    }

    protected void c(String str) {
        this.k = str;
    }

    protected int d() {
        return this.f;
    }

    protected void d(String str) {
        this.n = str;
    }

    protected void e(String str) {
        this.j = str;
    }

    protected VOSubtitleTrack[] e() {
        HashMap<Long, VOSubtitleTrack> map = this.C;
        if (map == null) {
            return null;
        }
        return (VOSubtitleTrack[]) map.values().toArray(new VOSubtitleTrack[this.C.size()]);
    }

    protected boolean f() {
        RemoteMediaClient remoteMediaClient;
        CastSession castSession = L;
        if (castSession == null || (remoteMediaClient = castSession.getRemoteMediaClient()) == null || !remoteMediaClient.hasMediaSession()) {
            return false;
        }
        return remoteMediaClient.isPlaying();
    }

    protected void g() {
        boolean z;
        VOAudioTrack[] vOAudioTrackArr;
        CastSession castSession = L;
        if (castSession == null || castSession.getRemoteMediaClient() == null || L.getRemoteMediaClient().getMediaInfo() == null || L.getRemoteMediaClient().getMediaInfo().getMediaTracks() == null || L.getRemoteMediaClient().getMediaStatus() == null || L.getRemoteMediaClient().getMediaStatus().getActiveTrackIds() == null) {
            return;
        }
        List<MediaTrack> listS = s();
        long[] jArrO = o();
        HashMap<Long, VOAudioTrack> map = new HashMap<>();
        int i = 0;
        for (int i2 = 0; i2 < listS.size(); i2++) {
            MediaTrack mediaTrack = listS.get(i2);
            if (mediaTrack.getType() == 2) {
                long id = mediaTrack.getId();
                String name = mediaTrack.getName();
                String language = mediaTrack.getLanguage();
                String contentType = mediaTrack.getContentType();
                if (name == null && (vOAudioTrackArr = this.A) != null && i < vOAudioTrackArr.length && vOAudioTrackArr[i] != null) {
                    name = vOAudioTrackArr[i].getName();
                    i++;
                }
                String str = name == null ? language : name;
                int length = jArrO.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        z = false;
                        break;
                    } else {
                        if (jArrO[i3] == id) {
                            z = true;
                            break;
                        }
                        i3++;
                    }
                }
                map.put(Long.valueOf(id), new VOAudioTrack(str, language, contentType, z, false));
            }
        }
        this.D = map;
    }

    protected void h() {
        boolean z;
        String str;
        int i;
        String language;
        VOSubtitleTrack[] vOSubtitleTrackArr;
        CastSession castSession = L;
        if (castSession == null || castSession.getRemoteMediaClient() == null || L.getRemoteMediaClient().getMediaInfo() == null || L.getRemoteMediaClient().getMediaInfo().getMediaTracks() == null || L.getRemoteMediaClient().getMediaStatus() == null || L.getRemoteMediaClient().getMediaStatus().getActiveTrackIds() == null) {
            return;
        }
        List<MediaTrack> listS = s();
        long[] jArrO = o();
        HashMap<Long, VOSubtitleTrack> map = new HashMap<>();
        int i2 = 0;
        for (int i3 = 0; i3 < listS.size(); i3++) {
            MediaTrack mediaTrack = listS.get(i3);
            if (mediaTrack.getType() == 1) {
                long id = mediaTrack.getId();
                String name = mediaTrack.getName();
                String language2 = mediaTrack.getLanguage();
                int subtype = mediaTrack.getSubtype();
                int length = jArrO.length;
                int i4 = 0;
                while (true) {
                    if (i4 >= length) {
                        z = false;
                        break;
                    } else {
                        if (jArrO[i4] == id) {
                            z = true;
                            break;
                        }
                        i4++;
                    }
                }
                if (d(i3)) {
                    int i5 = (int) id;
                    String name2 = this.y[i5].getName();
                    language = this.y[i5].getLanguage();
                    i = 1;
                    str = name2;
                } else {
                    int iG = g(mediaTrack.getContentType());
                    if (name == null && (vOSubtitleTrackArr = this.B) != null && i2 < vOSubtitleTrackArr.length && vOSubtitleTrackArr[i2] != null) {
                        name = vOSubtitleTrackArr[i2].getName();
                        i2++;
                    }
                    if (name == null) {
                        i = iG;
                        str = language2;
                        language = str;
                    } else {
                        str = name;
                        i = iG;
                        language = language2;
                    }
                }
                map.put(Long.valueOf(id), new VOSubtitleTrack(str, language, i, z, subtype));
            }
        }
        this.C = map;
    }

    protected void i() {
        RemoteMediaClient remoteMediaClient = L.getRemoteMediaClient();
        if (remoteMediaClient != null) {
            try {
                remoteMediaClient.pause();
            } catch (IllegalStateException unused) {
            }
        }
    }

    protected void j() {
        SessionManager sessionManager = this.d;
        if (sessionManager != null) {
            sessionManager.removeSessionManagerListener(this.K);
        }
    }

    protected void k() {
        RemoteMediaClient remoteMediaClient = L.getRemoteMediaClient();
        if (remoteMediaClient != null) {
            remoteMediaClient.stop();
        }
        this.y = null;
        this.A = null;
        this.B = null;
        this.D = null;
        this.C = null;
        this.G = null;
        this.E = null;
        this.F = 0;
        this.h = -1;
        this.f = 0;
        this.i = -1;
    }

    protected void l() {
        a(c(1));
        this.E = null;
        this.F = 0;
    }
}
