package st.suite.android.suitestinstrumentalservice.view_util;

import android.util.SparseArray;

/* JADX INFO: loaded from: classes3.dex */
public class Util {

    public static class VideoInfo {

        public enum State {
            ERROR(-1),
            IDLE(0),
            PREPARING(1),
            PREPARED(2),
            PLAYING(3),
            PAUSED(4),
            PLAYBACK_COMPLETED(5),
            UNKNOWN(1000);

            private static SparseArray<State> map = new SparseArray<>();
            private int originalValue;

            static {
                for (State state : values()) {
                    map.put(state.originalValue, state);
                }
            }

            State(int i) {
                this.originalValue = i;
            }

            public static State valueOf(int i) {
                State state = map.get(i);
                return state == null ? UNKNOWN : state;
            }

            public String serializedName() {
                return name().toLowerCase();
            }
        }
    }
}
