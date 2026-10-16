package com.dotscreen.tv.common.bokit;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;

/* JADX INFO: compiled from: RightVRequestManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lcom/dotscreen/tv/common/bokit/BackendName;", "", "shortName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getShortName", "()Ljava/lang/String;", "RightV", "Compass", "Toolbox", "IDPM", "Megacable", "SessionManagement", "PCW", "PADI", "ADS", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public enum BackendName {
    RightV("R"),
    Compass("C"),
    Toolbox(ExifInterface.GPS_DIRECTION_TRUE),
    IDPM("ID"),
    Megacable("M"),
    SessionManagement("R"),
    PCW("PCW"),
    PADI("PDI"),
    ADS("ADS");

    private final String shortName;

    BackendName(String str) {
        this.shortName = str;
    }

    public final String getShortName() {
        return this.shortName;
    }
}
