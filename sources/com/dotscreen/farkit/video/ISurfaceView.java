package com.dotscreen.farkit.video;

import android.view.SurfaceHolder;
import android.view.View;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: ISurfaceView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH&¨\u0006\u000f"}, d2 = {"Lcom/dotscreen/farkit/video/ISurfaceView;", "", "addSurfaceHolderCallback", "", "callback", "Landroid/view/SurfaceHolder$Callback;", "getView", "Landroid/view/View;", "setSurfaceHolderFixedSize", ToolboxAnalyticsModuleKt.M_WIDTH, "", ToolboxAnalyticsModuleKt.M_HEIGHT, "setSurfaceHolderKeepScreenOn", "screenOn", "", "farkit_release"}, k = 1, mv = {1, 1, 16})
public interface ISurfaceView {
    void addSurfaceHolderCallback(SurfaceHolder.Callback callback);

    View getView();

    void setSurfaceHolderFixedSize(int width, int height);

    void setSurfaceHolderKeepScreenOn(boolean screenOn);
}
