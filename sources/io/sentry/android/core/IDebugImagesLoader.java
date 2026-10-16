package io.sentry.android.core;

import io.sentry.protocol.DebugImage;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IDebugImagesLoader {
    void clearDebugImages();

    List<DebugImage> loadDebugImages();
}
