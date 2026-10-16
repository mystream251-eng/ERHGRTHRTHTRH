package com.google.android.gms.internal;

import androidx.core.view.PointerIconCompat;
import com.viaccessorca.voplayer.VOPlayer;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zziw {

    public static final class zza extends zzfhu<zza, C0184zza> implements zzfje {
        private static final zza zzd;
        private static volatile zzfjl<zza> zze;

        /* JADX INFO: renamed from: com.google.android.gms.internal.zziw$zza$zza, reason: collision with other inner class name */
        public static final class C0184zza extends zzfhu.zza<zza, C0184zza> implements zzfje {
            private C0184zza() {
                super(zza.zzd);
            }

            /* synthetic */ C0184zza(zzix zzixVar) {
                this();
            }
        }

        public enum zzb implements zzfia {
            UNKNOWN_EVENT_TYPE(0),
            AD_REQUEST(1),
            AD_LOADED(2),
            AD_FAILED_TO_LOAD(3),
            AD_FAILED_TO_LOAD_NO_FILL(4),
            AD_IMPRESSION(5),
            AD_FIRST_CLICK(6),
            AD_SUBSEQUENT_CLICK(7),
            REQUEST_WILL_START(8),
            REQUEST_DID_END(9),
            REQUEST_WILL_UPDATE_SIGNALS(10),
            REQUEST_DID_UPDATE_SIGNALS(11),
            REQUEST_WILL_BUILD_URL(12),
            REQUEST_DID_BUILD_URL(13),
            REQUEST_WILL_MAKE_NETWORK_REQUEST(14),
            REQUEST_DID_RECEIVE_NETWORK_RESPONSE(15),
            REQUEST_WILL_PROCESS_RESPONSE(16),
            REQUEST_DID_PROCESS_RESPONSE(17),
            REQUEST_WILL_RENDER(18),
            REQUEST_DID_RENDER(19),
            REQUEST_WILL_UPDATE_GMS_SIGNALS(1000),
            REQUEST_DID_UPDATE_GMS_SIGNALS(1001),
            REQUEST_FAILED_TO_UPDATE_GMS_SIGNALS(1002),
            REQUEST_FAILED_TO_BUILD_URL(PointerIconCompat.TYPE_HELP),
            REQUEST_FAILED_TO_MAKE_NETWORK_REQUEST(1004),
            REQUEST_FAILED_TO_PROCESS_RESPONSE(VOPlayer.MEDIA_INFO_EVENT_PLAYLIST_STOP);

            private static final zzfib<zzb> zzaa = new zziy();
            private final int zzab;

            zzb(int i) {
                this.zzab = i;
            }

            public final int zza() {
                return this.zzab;
            }
        }

        static {
            zza zzaVar = new zza();
            zzd = zzaVar;
            zzaVar.zza(zzfhu.zzg.zzf, (Object) null, (Object) null);
            ((zzfhu) zzaVar).zzb.zzc();
        }

        private zza() {
        }

        public final int zza() {
            int i = this.zzc;
            if (i != -1) {
                return i;
            }
            int iZze = this.zzb.zze() + 0;
            this.zzc = iZze;
            return iZze;
        }

        protected final Object zza(int i, Object obj, Object obj2) {
            zzix zzixVar = null;
            switch (zzix.zza[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return zzd;
                case 3:
                    return null;
                case 4:
                    return new C0184zza(zzixVar);
                case 5:
                    return this;
                case 6:
                    zzfhb zzfhbVar = (zzfhb) obj;
                    if (((zzfhm) obj2) == null) {
                        throw null;
                    }
                    boolean z = false;
                    while (!z) {
                        try {
                            int iZza = zzfhbVar.zza();
                            if (iZza == 0 || !zza(iZza, zzfhbVar)) {
                                z = true;
                            }
                        } catch (zzfie e) {
                            throw new RuntimeException((Throwable) e.zza(this));
                        } catch (IOException e2) {
                            throw new RuntimeException((Throwable) new zzfie(e2.getMessage()).zza(this));
                        }
                    }
                    break;
                    break;
                case 7:
                    break;
                case 8:
                    if (zze == null) {
                        synchronized (zza.class) {
                            if (zze == null) {
                                zze = new zzfhu.zzb(zzd);
                            }
                            break;
                        }
                    }
                    return zze;
                case 9:
                    return (byte) 1;
                case 10:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
            return zzd;
        }

        public final void zza(zzfhg zzfhgVar) throws IOException {
            this.zzb.zza(zzfhgVar);
        }
    }
}
