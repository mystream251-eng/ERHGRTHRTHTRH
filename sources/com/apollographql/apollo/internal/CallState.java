package com.apollographql.apollo.internal;

/* JADX INFO: loaded from: classes.dex */
enum CallState {
    IDLE,
    ACTIVE,
    TERMINATED,
    CANCELED;

    static class IllegalStateMessage {
        private final CallState callState;

        private IllegalStateMessage(CallState callState) {
            this.callState = callState;
        }

        static IllegalStateMessage forCurrentState(CallState callState) {
            return new IllegalStateMessage(callState);
        }

        String expected(CallState... callStateArr) {
            StringBuilder sb = new StringBuilder("Found: " + this.callState.name() + ", but expected [");
            int length = callStateArr.length;
            String str = "";
            int i = 0;
            while (i < length) {
                CallState callState = callStateArr[i];
                sb.append(str);
                sb.append(callState.name());
                i++;
                str = ", ";
            }
            sb.append("]");
            return sb.toString();
        }
    }
}
