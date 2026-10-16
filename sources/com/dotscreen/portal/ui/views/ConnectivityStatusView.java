package com.dotscreen.portal.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.dotscreen.farkit.network.NetworkStatus;
import com.dotscreen.farkit.network.UnreachabilityReason;
import com.dotscreen.megacable.portal.R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ConnectivityStatusView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eR\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/dotscreen/portal/ui/views/ConnectivityStatusView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "networkStatus", "Lcom/dotscreen/farkit/network/NetworkStatus;", "(Landroid/content/Context;Landroid/util/AttributeSet;IILcom/dotscreen/farkit/network/NetworkStatus;)V", "getNetworkStatus", "()Lcom/dotscreen/farkit/network/NetworkStatus;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class ConnectivityStatusView extends LinearLayout {
    private HashMap _$_findViewCache;
    private final NetworkStatus networkStatus;

    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConnectivityStatusView(Context context, AttributeSet attributeSet, int i, int i2, NetworkStatus networkStatus) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.networkStatus = networkStatus;
        View viewInflate = View.inflate(getContext(), R.layout.view_connectivity_status, this);
        if (viewInflate == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
        }
        if (this.networkStatus instanceof NetworkStatus.ConnectedNoBackendAccess) {
            TextView connectivity_text = (TextView) _$_findCachedViewById(com.dotscreen.portal.R.id.connectivity_text);
            Intrinsics.checkExpressionValueIsNotNull(connectivity_text, "connectivity_text");
            connectivity_text.setText(context.getText(((NetworkStatus.ConnectedNoBackendAccess) this.networkStatus).getReason() == UnreachabilityReason.SSLCannotBeValidated ? R.string.connectivity_sync_failed : R.string.connectivity_no_service));
            setAlpha(0.5f);
        }
    }

    public /* synthetic */ ConnectivityStatusView(Context context, AttributeSet attributeSet, int i, int i2, NetworkStatus networkStatus, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? (AttributeSet) null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2, (i3 & 16) != 0 ? (NetworkStatus) null : networkStatus);
    }

    public final NetworkStatus getNetworkStatus() {
        return this.networkStatus;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ConnectivityStatusView(Context context) {
        this(context, null, 0, 0, null, 16, null);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ConnectivityStatusView(Context context, AttributeSet attrs) {
        this(context, attrs, 0, 0, null, 16, null);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ConnectivityStatusView(Context context, AttributeSet attrs, int i) {
        this(context, attrs, i, 0, null, 16, null);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
    }
}
