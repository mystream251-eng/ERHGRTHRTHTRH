package com.google.android.gms.internal;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes2.dex */
final class zzos extends zzop {
    zzos() {
    }

    private static String zza(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        int i = 0;
        int length = str.length();
        while (i < str.length() && str.charAt(i) == ',') {
            i++;
        }
        while (length > 0 && str.charAt(length - 1) == ',') {
            length--;
        }
        return (i == 0 && length == str.length()) ? str : str.substring(i, length);
    }

    @Override // com.google.android.gms.internal.zzop
    public final String zza(String str, String str2) {
        String strZza = zza(str);
        String strZza2 = zza(str2);
        if (TextUtils.isEmpty(strZza)) {
            return strZza2;
        }
        if (TextUtils.isEmpty(strZza2)) {
            return strZza;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(strZza).length() + 1 + String.valueOf(strZza2).length());
        sb.append(strZza);
        sb.append(",");
        sb.append(strZza2);
        return sb.toString();
    }
}
