package com.google.android.gms.internal;

import androidx.exifinterface.media.ExifInterface;
import com.dotscreen.tv.common.search.LocalSearchProvider;
import com.dotscreen.tv.common.utils.notifications.NotificationsContract;
import com.google.android.gms.ads.internal.gmsg.zzt;
import com.google.android.gms.ads.internal.zzbt;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzaoc implements zzt<zzann> {
    private zzany zza;

    private static Integer zza(Map<String, String> map, String str) {
        if (!map.containsKey(str)) {
            return null;
        }
        try {
            return Integer.valueOf(Integer.parseInt(map.get(str)));
        } catch (NumberFormatException unused) {
            String str2 = map.get(str);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 39 + String.valueOf(str2).length());
            sb.append("Precache invalid numeric parameter '");
            sb.append(str);
            sb.append("': ");
            sb.append(str2);
            zzahw.zze(sb.toString());
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:48:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:56:0x00d7  */
    /* JADX WARN: Code duplicated, block: B:59:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:62:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:65:0x00f8  */
    /* JADX WARN: Code duplicated, block: B:72:? A[RETURN, SYNTHETIC] */
    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final /* synthetic */ void zza(zzann zzannVar, Map map) {
        String str;
        Integer numZza;
        Integer numZza2;
        Integer numZza3;
        Integer numZza4;
        zzann zzannVar2 = zzannVar;
        zzbt.zzy();
        if (map.containsKey("abort")) {
            zzany zzanyVar = this.zza;
            if (zzanyVar != null) {
                zzanyVar.zza();
                return;
            } else {
                if (zzanx.zza(zzannVar2)) {
                    return;
                }
                zzahw.zze("Precache abort but no precache task running.");
                return;
            }
        }
        String str2 = (String) map.get("src");
        if (str2 == null) {
            if (zzanx.zzb(zzannVar2) == null && this.zza == null) {
                str = "Precache must specify a source.";
                zzahw.zze(str);
            }
            numZza = zza((Map<String, String>) map, "minBufferMs");
            if (numZza != null) {
                numZza.intValue();
            }
            numZza2 = zza((Map<String, String>) map, "maxBufferMs");
            if (numZza2 != null) {
                numZza2.intValue();
            }
            numZza3 = zza((Map<String, String>) map, "bufferForPlaybackMs");
            if (numZza3 != null) {
                numZza3.intValue();
            }
            numZza4 = zza((Map<String, String>) map, "bufferForPlaybackAfterRebufferMs");
            if (numZza4 != null) {
                numZza4.intValue();
                return;
            }
            return;
        }
        if (this.zza != null) {
            str = "Threadless precache task has already started.";
        } else if (zzanx.zzb(zzannVar2) != null) {
            str = "Precache task is already running.";
        } else {
            if (zzannVar2.zze() != null) {
                zzanm zzanmVar = new zzanm((String) map.get(NotificationsContract.COLUMN_FLAGS));
                Integer numZza5 = zza((Map<String, String>) map, "notifyBytes");
                Integer numZza6 = zza((Map<String, String>) map, "notifyMillis");
                Integer numZza7 = zza((Map<String, String>) map, "player");
                boolean z = false;
                if (numZza7 == null) {
                    numZza7 = 0;
                }
                zzany zzanyVarZza = zzannVar2.zze().zza.zza(zzannVar2, numZza7.intValue(), null, zzanmVar);
                if ((numZza5 == null && numZza6 == null) ? false : true) {
                    for (String str3 : zzanmVar.zza.split(",")) {
                        if (!str3.equals(LocalSearchProvider.LIVE_CONTENTS) && !str3.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                            z = true;
                            break;
                        }
                    }
                    if (z) {
                        if (numZza5 != null) {
                            numZza5.intValue();
                        } else {
                            numZza6.intValue();
                        }
                        this.zza = zzanyVarZza;
                    } else {
                        new zzanv(zzannVar2, zzanyVarZza, str2).zzc();
                    }
                } else {
                    new zzanv(zzannVar2, zzanyVarZza, str2).zzc();
                }
                numZza = zza((Map<String, String>) map, "minBufferMs");
                if (numZza != null) {
                    numZza.intValue();
                }
                numZza2 = zza((Map<String, String>) map, "maxBufferMs");
                if (numZza2 != null) {
                    numZza2.intValue();
                }
                numZza3 = zza((Map<String, String>) map, "bufferForPlaybackMs");
                if (numZza3 != null) {
                    numZza3.intValue();
                }
                numZza4 = zza((Map<String, String>) map, "bufferForPlaybackAfterRebufferMs");
                if (numZza4 != null) {
                    numZza4.intValue();
                    return;
                }
                return;
            }
            str = "Precache requires a dependency provider.";
        }
        zzahw.zze(str);
    }
}
