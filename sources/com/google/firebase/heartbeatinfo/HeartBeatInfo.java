package com.google.firebase.heartbeatinfo;

/* JADX INFO: compiled from: com.google.firebase:firebase-common@@19.3.0 */
/* JADX INFO: loaded from: classes2.dex */
public interface HeartBeatInfo {
    HeartBeat getHeartBeatCode(String str);

    /* JADX INFO: compiled from: com.google.firebase:firebase-common@@19.3.0 */
    public enum HeartBeat {
        NONE(0),
        SDK(1),
        GLOBAL(2),
        COMBINED(3);

        private final int code;

        HeartBeat(int i) {
            this.code = i;
        }

        public int getCode() {
            return this.code;
        }
    }
}
