package com.dotscreen.portal.components;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.android.tv.common.SharedPreferencesUtils;
import com.dotscreen.farkit.tools.DSLog;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.tv.common.utils.notifications.NotificationManager;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NotificationView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\b\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u001cH\u0014J\u000e\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020 J$\u0010!\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00020\t2\b\b\u0002\u0010#\u001a\u00020 2\b\b\u0002\u0010$\u001a\u00020 H\u0002R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/dotscreen/portal/components/NotificationView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "TAG", "", "image_bell", "Landroid/widget/ImageView;", "main_layout", "Landroid/widget/RelativeLayout;", "notificationCountDisposable", "Lio/reactivex/disposables/Disposable;", "notificationsCount", "notificatonManager", "Lcom/dotscreen/tv/common/utils/notifications/NotificationManager;", "rounded_back", "text_number_notification", "Landroid/widget/TextView;", "listenNotificationStatusChanged", "", "onDetachedFromWindow", "setFocusableLocal", "focus", "", "setNotificationStatus", "numberItem", "unread", "keepSameState", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class NotificationView extends LinearLayout {
    private final String TAG;
    private HashMap _$_findViewCache;
    private final ImageView image_bell;
    private final RelativeLayout main_layout;
    private Disposable notificationCountDisposable;
    private int notificationsCount;
    private NotificationManager notificatonManager;
    private final RelativeLayout rounded_back;
    private final TextView text_number_notification;

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
    public NotificationView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkParameterIsNotNull(context, "context");
        String simpleName = NotificationView.class.getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "NotificationView::class.java.simpleName");
        this.TAG = simpleName;
        View viewInflate = View.inflate(getContext(), R.layout.view_notification, this);
        if (viewInflate == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
        }
        View viewFindViewById = findViewById(R.id.image_bell);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById(R.id.image_bell)");
        this.image_bell = (ImageView) viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.rounded_back);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "findViewById(R.id.rounded_back)");
        this.rounded_back = (RelativeLayout) viewFindViewById2;
        View viewFindViewById3 = findViewById(R.id.main_layout);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById3, "findViewById(R.id.main_layout)");
        this.main_layout = (RelativeLayout) viewFindViewById3;
        View viewFindViewById4 = findViewById(R.id.text_number_notification);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById4, "findViewById(R.id.text_number_notification)");
        this.text_number_notification = (TextView) viewFindViewById4;
        if (isInEditMode()) {
            return;
        }
        PortalApplication.Companion companion = PortalApplication.INSTANCE;
        Context context2 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "getContext()");
        this.notificatonManager = companion.getSingleton(context2).getNotificationManager();
        setOnClickListener(new View.OnClickListener() { // from class: com.dotscreen.portal.components.NotificationView.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DSLog.INSTANCE.a("Clicked on Notifications");
                NotificationManager notificationManager = NotificationView.this.notificatonManager;
                if (notificationManager != null) {
                    Context context3 = NotificationView.this.getContext();
                    if (context3 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
                    }
                    notificationManager.openNotificationPannel((Activity) context3);
                }
            }
        });
        setBackgroundResource(R.drawable.top_menu_selector);
        setFocusable(true);
        if (isInEditMode()) {
            return;
        }
        listenNotificationStatusChanged();
    }

    public /* synthetic */ NotificationView(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? (AttributeSet) null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public NotificationView(Context context) {
        this(context, null, 0, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public NotificationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public NotificationView(Context context, AttributeSet attrs, int i) {
        this(context, attrs, i, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
    }

    public final void setFocusableLocal(boolean focus) {
        setFocusable(focus);
        setFocusableInTouchMode(focus);
    }

    private final void listenNotificationStatusChanged() {
        PortalApplication.Companion companion = PortalApplication.INSTANCE;
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        this.notificationCountDisposable = companion.getSingleton(context).getNotificationManager().getStatus().subscribe(new Consumer<NotificationManager.NotificationStatus>() { // from class: com.dotscreen.portal.components.NotificationView.listenNotificationStatusChanged.1
            @Override // io.reactivex.functions.Consumer
            public final void accept(NotificationManager.NotificationStatus notificationStatus) {
                NotificationView.this.setNotificationStatus(notificationStatus.getCount(), notificationStatus.getUnread(), notificationStatus.getKeepSameState());
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.components.NotificationView.listenNotificationStatusChanged.2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Log.e(NotificationView.this.TAG, "Error", th);
            }
        }, new Action() { // from class: com.dotscreen.portal.components.NotificationView.listenNotificationStatusChanged.3
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        });
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        Disposable disposable = this.notificationCountDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        this.notificationCountDisposable = (Disposable) null;
        super.onDetachedFromWindow();
    }

    static /* synthetic */ void setNotificationStatus$default(NotificationView notificationView, int i, boolean z, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            z2 = false;
        }
        notificationView.setNotificationStatus(i, z, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setNotificationStatus(int numberItem, boolean unread, boolean keepSameState) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(SharedPreferencesUtils.SHARED_PREF_NOTIFICATION_NUMBER, 0);
        Log.e(this.TAG, "setStatus = " + numberItem);
        if (numberItem > 0) {
            if (!keepSameState) {
                if (unread) {
                    if (sharedPreferences.getInt("last_value", 0) < numberItem) {
                        sharedPreferences.edit().putBoolean("current_status_red", true).apply();
                        this.image_bell.setImageResource(R.drawable.ring_red);
                        this.rounded_back.setBackgroundResource(R.drawable.notification_number_red_background);
                        this.text_number_notification.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
                    } else if (sharedPreferences.getInt("last_value", 0) <= numberItem && sharedPreferences.getBoolean("current_status_red", false)) {
                        sharedPreferences.edit().putBoolean("current_status_red", true).apply();
                        this.image_bell.setImageResource(R.drawable.ring_red);
                        this.rounded_back.setBackgroundResource(R.drawable.notification_number_red_background);
                        this.text_number_notification.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
                    } else {
                        sharedPreferences.edit().putBoolean("current_status_red", false).apply();
                        this.image_bell.setImageResource(R.drawable.ring_white);
                        this.rounded_back.setBackgroundResource(R.drawable.notification_number_background);
                        this.text_number_notification.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
                    }
                } else {
                    sharedPreferences.edit().putBoolean("current_status_red", false).apply();
                    this.image_bell.setImageResource(R.drawable.ring_white);
                    this.rounded_back.setBackgroundResource(R.drawable.notification_number_background);
                    this.text_number_notification.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
                }
            }
            this.rounded_back.setVisibility(0);
            if (numberItem < 10) {
                this.text_number_notification.setText(String.valueOf(numberItem));
            } else {
                this.text_number_notification.setText("9+");
            }
        } else {
            sharedPreferences.edit().putBoolean("current_status_red", false).apply();
            this.rounded_back.setVisibility(8);
            this.image_bell.setImageResource(R.drawable.ring_white);
        }
        sharedPreferences.edit().putInt("last_value", numberItem).apply();
    }
}
