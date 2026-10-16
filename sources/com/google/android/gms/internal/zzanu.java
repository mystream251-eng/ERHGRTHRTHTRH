package com.google.android.gms.internal;

import android.text.TextUtils;
import com.dotscreen.tv.common.search.LocalSearchProvider;
import com.google.android.gms.ads.internal.gmsg.zzt;
import com.google.android.gms.ads.internal.zzbt;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzanu implements zzt<zzann> {
    @Override // com.google.android.gms.ads.internal.gmsg.zzt
    public final /* synthetic */ void zza(zzann zzannVar, Map map) {
        zzann zzannVar2 = zzannVar;
        if (((Boolean) zzlc.zzf().zza(zzoi.zzdm)).booleanValue()) {
            zzaou zzaouVarZzb = zzannVar2.zzb();
            if (zzaouVarZzb == null) {
                try {
                    zzaou zzaouVar = new zzaou(zzannVar2, Float.parseFloat((String) map.get("duration")), LocalSearchProvider.LIVE_CONTENTS.equals(map.get("customControlsAllowed")), LocalSearchProvider.LIVE_CONTENTS.equals(map.get("clickToExpandAllowed")));
                    zzannVar2.zza(zzaouVar);
                    zzaouVarZzb = zzaouVar;
                } catch (NullPointerException e) {
                    e = e;
                    zzahw.zzb("Unable to parse videoMeta message.", e);
                    zzbt.zzi().zza(e, "VideoMetaGmsgHandler.onGmsg");
                    return;
                } catch (NumberFormatException e2) {
                    e = e2;
                    zzahw.zzb("Unable to parse videoMeta message.", e);
                    zzbt.zzi().zza(e, "VideoMetaGmsgHandler.onGmsg");
                    return;
                }
            }
            boolean zEquals = LocalSearchProvider.LIVE_CONTENTS.equals(map.get("muted"));
            float f = Float.parseFloat((String) map.get("currentTime"));
            int i = Integer.parseInt((String) map.get("playbackState"));
            if (i < 0 || 3 < i) {
                i = 0;
            }
            String str = (String) map.get("aspectRatio");
            float f2 = TextUtils.isEmpty(str) ? 0.0f : Float.parseFloat(str);
            if (zzahw.zza(3)) {
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 79);
                sb.append("Video Meta GMSG: isMuted : ");
                sb.append(zEquals);
                sb.append(" , playbackState : ");
                sb.append(i);
                sb.append(" , aspectRatio : ");
                sb.append(str);
                zzahw.zzb(sb.toString());
            }
            zzaouVarZzb.zza(f, i, zEquals, f2);
        }
    }
}
