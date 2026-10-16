package tv.broadpeak.smartlib.player;

import android.os.Handler;
import android.os.Looper;
import com.viaccessorca.voplayer.VOPlayer;
import tv.broadpeak.smartlib.engine.manager.LoggerManager;

/* JADX INFO: loaded from: classes3.dex */
public class VOPlayerStateManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static VOPlayerStateManager f215a;
    public VOPlayer b;
    public long c;
    public long d;
    public IListener e;
    public Handler f = new Handler(Looper.getMainLooper());
    public Runnable g;

    public interface IListener {
        void onInit(VOPlayer vOPlayer);

        void onSeek(VOPlayer vOPlayer, long j, long j2);

        void onStateChange(VOPlayer vOPlayer, boolean z, boolean z2);

        void onUpdatePlayerPosition(VOPlayer vOPlayer, long j);
    }

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            VOPlayerStateManager.this.a();
            VOPlayerStateManager vOPlayerStateManager = VOPlayerStateManager.this;
            vOPlayerStateManager.f.postDelayed(vOPlayerStateManager.g, 200L);
        }
    }

    public static synchronized VOPlayerStateManager getInstance() {
        if (f215a == null) {
            f215a = new VOPlayerStateManager();
        }
        return f215a;
    }

    public final void a() {
        synchronized (this) {
            VOPlayer vOPlayer = this.b;
            if (vOPlayer != null && this.e != null && vOPlayer.getState() != VOPlayer.PlayerState.RELEASED) {
                VOPlayer vOPlayer2 = this.b;
                if (vOPlayer2 != null && this.e != null && vOPlayer2.getState() != VOPlayer.PlayerState.RELEASED) {
                    long playerPosition = getPlayerPosition();
                    this.e.onUpdatePlayerPosition(this.b, playerPosition);
                    long j = this.c;
                    long j2 = j + 1000;
                    if (playerPosition < j - 1000 || playerPosition > j2) {
                        this.e.onSeek(this.b, j, playerPosition);
                    }
                    this.c = getPlayerPosition() + 200;
                }
                this.d = System.currentTimeMillis();
            }
        }
    }

    public void destroy() {
        LoggerManager.getInstance().printDebugLogs("BpkStateManager", "Destroying");
        synchronized (this) {
            this.f.removeCallbacks(this.g);
            this.g = null;
            this.b = null;
            this.e = null;
        }
    }

    public void forcePollPlayerPosition() {
        this.c = (this.c - 200) + (System.currentTimeMillis() - this.d);
        a();
    }

    public long getPlayerPosition() {
        VOPlayer vOPlayer = this.b;
        if (vOPlayer == null || vOPlayer.getState() == VOPlayer.PlayerState.RELEASED) {
            return 0L;
        }
        return this.b.getCurrentPTS() > 1639000000000L ? this.b.getCurrentPTS() : this.b.getCurrentPosition();
    }

    public void init(VOPlayer vOPlayer, IListener iListener) {
        this.b = vOPlayer;
        this.c = getPlayerPosition();
        this.d = 0L;
        this.e = iListener;
        a aVar = new a();
        this.g = aVar;
        aVar.run();
    }
}
