package com.google.android.gms.internal;

import java.util.Comparator;

/* JADX INFO: loaded from: classes2.dex */
final class zzhu implements Comparator<zzia> {
    zzhu(zzht zzhtVar) {
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(zzia zziaVar, zzia zziaVar2) {
        zzia zziaVar3 = zziaVar;
        zzia zziaVar4 = zziaVar2;
        int i = zziaVar3.zzc - zziaVar4.zzc;
        return i != 0 ? i : (int) (zziaVar3.zza - zziaVar4.zza);
    }
}
