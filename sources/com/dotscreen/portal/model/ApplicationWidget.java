package com.dotscreen.portal.model;

import android.content.Intent;
import android.graphics.Bitmap;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ApplicationWidget.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B+\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB+\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\u000bB!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\fB3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\rJ\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcom/dotscreen/portal/model/ApplicationWidget;", "Lcom/dotscreen/portal/model/BaseWidget;", "title", "", "packageName", "bannerUri", "intent", "Landroid/content/Intent;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/content/Intent;)V", "bannerBitmap", "Landroid/graphics/Bitmap;", "(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Bitmap;Landroid/content/Intent;)V", "(Ljava/lang/String;Ljava/lang/String;Landroid/content/Intent;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Bitmap;Landroid/content/Intent;)V", "getBannerBitmap", "()Landroid/graphics/Bitmap;", "getBannerUri", "()Ljava/lang/String;", "getPackageName", "hashCode", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class ApplicationWidget extends BaseWidget {
    private final Bitmap bannerBitmap;
    private final String bannerUri;
    private final String packageName;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ApplicationWidget(String title, String packageName, String str, Bitmap bitmap, Intent intent) {
        super(title, intent, false, null, 12, null);
        Intrinsics.checkParameterIsNotNull(title, "title");
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        this.packageName = packageName;
        this.bannerUri = str;
        this.bannerBitmap = bitmap;
    }

    public final Bitmap getBannerBitmap() {
        return this.bannerBitmap;
    }

    public final String getBannerUri() {
        return this.bannerUri;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ApplicationWidget(String title, String packageName, String str, Intent intent) {
        this(title, packageName, str, null, intent);
        Intrinsics.checkParameterIsNotNull(title, "title");
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ApplicationWidget(String title, String packageName, Bitmap bitmap, Intent intent) {
        this(title, packageName, null, bitmap, intent);
        Intrinsics.checkParameterIsNotNull(title, "title");
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ApplicationWidget(String title, String packageName, Intent intent) {
        this(title, packageName, null, null, intent);
        Intrinsics.checkParameterIsNotNull(title, "title");
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
    }

    @Override // com.dotscreen.portal.model.BaseWidget
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.packageName);
    }
}
