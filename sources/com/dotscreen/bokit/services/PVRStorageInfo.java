package com.dotscreen.bokit.services;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PVRService.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u000e\b\u0016\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007¢\u0006\u0002\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcom/dotscreen/bokit/services/PVRStorageInfo;", "", "mode", "Lcom/dotscreen/bokit/services/PVRStorageMode;", "name", "", "remainingBytes", "", "remainingSeconds", "totalBytes", "totalSeconds", "(Lcom/dotscreen/bokit/services/PVRStorageMode;Ljava/lang/String;JJJJ)V", "getMode", "()Lcom/dotscreen/bokit/services/PVRStorageMode;", "getName", "()Ljava/lang/String;", "getRemainingBytes", "()J", "getRemainingSeconds", "getTotalBytes", "getTotalSeconds", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public class PVRStorageInfo {
    private final PVRStorageMode mode;
    private final String name;
    private final long remainingBytes;
    private final long remainingSeconds;
    private final long totalBytes;
    private final long totalSeconds;

    public PVRStorageInfo(PVRStorageMode mode, String name, long j, long j2, long j3, long j4) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(name, "name");
        this.mode = mode;
        this.name = name;
        this.remainingBytes = j;
        this.remainingSeconds = j2;
        this.totalBytes = j3;
        this.totalSeconds = j4;
    }

    public final PVRStorageMode getMode() {
        return this.mode;
    }

    public final String getName() {
        return this.name;
    }

    public final long getRemainingBytes() {
        return this.remainingBytes;
    }

    public final long getRemainingSeconds() {
        return this.remainingSeconds;
    }

    public final long getTotalBytes() {
        return this.totalBytes;
    }

    public final long getTotalSeconds() {
        return this.totalSeconds;
    }
}
