package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.ads.reward.RewardItem;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public final class zzagd extends zzbgl {
    public static final Parcelable.Creator<zzagd> CREATOR = new zzage();
    public final String zza;
    public final int zzb;

    public zzagd(RewardItem rewardItem) {
        this(rewardItem.getType(), rewardItem.getAmount());
    }

    public zzagd(String str, int i) {
        this.zza = str;
        this.zzb = i;
    }

    public static zzagd zza(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return zza(new JSONArray(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    public static zzagd zza(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        return new zzagd(jSONArray.getJSONObject(0).optString("rb_type"), jSONArray.getJSONObject(0).optInt("rb_amount"));
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof zzagd)) {
            zzagd zzagdVar = (zzagd) obj;
            if (com.google.android.gms.common.internal.zzbg.zza(this.zza, zzagdVar.zza) && com.google.android.gms.common.internal.zzbg.zza(Integer.valueOf(this.zzb), Integer.valueOf(zzagdVar.zzb))) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, Integer.valueOf(this.zzb)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 2, this.zza, false);
        zzbgo.zza(parcel, 3, this.zzb);
        zzbgo.zza(parcel, iZza);
    }
}
