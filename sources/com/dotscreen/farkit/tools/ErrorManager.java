package com.dotscreen.farkit.tools;

import android.content.Context;
import com.android.volley.VolleyError;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ErrorManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\b&\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\u0007\u001a\u00020\b2\n\u0010\t\u001a\u00060\nj\u0002`\u000bH&J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u000eH&R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/dotscreen/farkit/tools/ErrorManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "getErrorMessage", "", "error", "Ljava/lang/Exception;", "Lkotlin/Exception;", "handleLoginError", "", "", "Companion", "farkit_release"}, k = 1, mv = {1, 1, 16})
public abstract class ErrorManager {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG;
    private final Context context;

    public abstract String getErrorMessage(Exception error);

    public abstract void handleLoginError(Throwable error);

    public ErrorManager(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
    }

    public final Context getContext() {
        return this.context;
    }

    /* JADX INFO: compiled from: ErrorManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lcom/dotscreen/farkit/tools/ErrorManager$Companion;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "getErrorStatusCode", "", "error", "", "farkit_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getTAG() {
            return ErrorManager.TAG;
        }

        public final int getErrorStatusCode(Throwable error) {
            Intrinsics.checkParameterIsNotNull(error, "error");
            if (error instanceof VolleyError) {
                VolleyError volleyError = (VolleyError) error;
                if (volleyError.networkResponse != null) {
                    return volleyError.networkResponse.statusCode;
                }
            }
            return -1;
        }
    }

    static {
        String simpleName = ErrorManager.class.getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "ErrorManager::class.java.simpleName");
        TAG = simpleName;
    }
}
