package com.dotscreen.bokit.services;

import com.dotscreen.bokit.model.User;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UserService.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lcom/dotscreen/bokit/services/UserStatus;", "", "()V", "Anonymous", "Authenticated", "Lcom/dotscreen/bokit/services/UserStatus$Anonymous;", "Lcom/dotscreen/bokit/services/UserStatus$Authenticated;", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public abstract class UserStatus {

    /* JADX INFO: compiled from: UserService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/dotscreen/bokit/services/UserStatus$Anonymous;", "Lcom/dotscreen/bokit/services/UserStatus;", "()V", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
    public static final class Anonymous extends UserStatus {
        public Anonymous() {
            super(null);
        }
    }

    private UserStatus() {
    }

    public /* synthetic */ UserStatus(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: UserService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/dotscreen/bokit/services/UserStatus$Authenticated;", "Lcom/dotscreen/bokit/services/UserStatus;", ToolboxAnalyticsModuleKt.KEY_USER, "Lcom/dotscreen/bokit/model/User;", "(Lcom/dotscreen/bokit/model/User;)V", "getUser", "()Lcom/dotscreen/bokit/model/User;", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
    public static final class Authenticated extends UserStatus {
        private final User user;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Authenticated(User user) {
            super(null);
            Intrinsics.checkNotNullParameter(user, "user");
            this.user = user;
        }

        public final User getUser() {
            return this.user;
        }
    }
}
