package io.sentry.android.ndk;

import io.sentry.protocol.SdkVersion;

/* JADX INFO: loaded from: classes2.dex */
final class SentryNdkUtil {
    private SentryNdkUtil() {
    }

    static void addPackage(SdkVersion sdkVersion) {
        if (sdkVersion == null) {
            return;
        }
        sdkVersion.addPackage("maven:io.sentry:sentry-android-ndk", "5.6.1");
    }
}
