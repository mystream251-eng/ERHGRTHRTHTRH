package com.dotscreen.portal.model;

import android.content.Intent;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: BaseWidget.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0000\b\u0016\u0018\u00002\u00020\u0001B3\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\tJ\b\u0010\u0014\u001a\u00020\u0015H\u0016R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011¨\u0006\u0016"}, d2 = {"Lcom/dotscreen/portal/model/BaseWidget;", "", "title", "", "intent", "Landroid/content/Intent;", "passwordProtected", "", "subtitle", "(Ljava/lang/String;Landroid/content/Intent;ZLjava/lang/String;)V", "getIntent", "()Landroid/content/Intent;", "getPasswordProtected", "()Z", "getSubtitle", "()Ljava/lang/String;", "setSubtitle", "(Ljava/lang/String;)V", "getTitle", "setTitle", "hashCode", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public class BaseWidget {
    private final Intent intent;
    private final boolean passwordProtected;
    private String subtitle;
    private String title;

    public BaseWidget() {
        this(null, null, false, null, 15, null);
    }

    public BaseWidget(String str, Intent intent, boolean z, String str2) {
        this.title = str;
        this.intent = intent;
        this.passwordProtected = z;
        this.subtitle = str2;
    }

    public /* synthetic */ BaseWidget(String str, Intent intent, boolean z, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (Intent) null : intent, (i & 4) != 0 ? false : z, (i & 8) != 0 ? (String) null : str2);
    }

    public final Intent getIntent() {
        return this.intent;
    }

    public final boolean getPasswordProtected() {
        return this.passwordProtected;
    }

    public final String getSubtitle() {
        return this.subtitle;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setSubtitle(String str) {
        this.subtitle = str;
    }

    public final void setTitle(String str) {
        this.title = str;
    }

    public int hashCode() {
        return Objects.hash(this.title, this.intent);
    }
}
