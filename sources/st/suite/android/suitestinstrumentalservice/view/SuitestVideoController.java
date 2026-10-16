package st.suite.android.suitestinstrumentalservice.view;

import st.suite.android.suitestinstrumentalservice.view_util.Util;

/* JADX INFO: loaded from: classes3.dex */
public interface SuitestVideoController {
    int getCurrentPosition();

    int getDuration();

    Util.VideoInfo.State getState();

    String getUrl();
}
