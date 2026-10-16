package com.google.android.exoplayer2;

/* JADX INFO: loaded from: classes.dex */
public interface ControlDispatcher {
    boolean dispatchSeekTo(Player player, int i, long j);

    boolean dispatchSetPlayWhenReady(Player player, boolean z);

    boolean dispatchSetRepeatMode(Player player, int i);

    boolean dispatchSetShuffleModeEnabled(Player player, boolean z);

    boolean dispatchStop(Player player, boolean z);
}
