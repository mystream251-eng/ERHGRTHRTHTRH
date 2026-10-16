package com.dotscreen.farkit.updater;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UpdateManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00060\u0001j\u0002`\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/dotscreen/farkit/updater/PackageNotFoundException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "packageName", "", "versionCode", "", "availableBundle", "Lcom/dotscreen/farkit/updater/UpdateManager$BundleItem;", "(Ljava/lang/String;ILcom/dotscreen/farkit/updater/UpdateManager$BundleItem;)V", "getAvailableBundle", "()Lcom/dotscreen/farkit/updater/UpdateManager$BundleItem;", "getPackageName", "()Ljava/lang/String;", "getVersionCode", "()I", "farkit_release"}, k = 1, mv = {1, 1, 16})
public final class PackageNotFoundException extends Exception {
    private final UpdateManager.BundleItem availableBundle;
    private final String packageName;
    private final int versionCode;

    public PackageNotFoundException(String packageName, int i, UpdateManager.BundleItem bundleItem) {
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        this.packageName = packageName;
        this.versionCode = i;
        this.availableBundle = bundleItem;
    }

    public final UpdateManager.BundleItem getAvailableBundle() {
        return this.availableBundle;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final int getVersionCode() {
        return this.versionCode;
    }
}
