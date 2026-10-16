package com.dotscreen.portal.ui.cards;

import android.content.Context;
import android.content.Intent;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Binder;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import androidx.cardview.widget.CardView;
import androidx.core.content.IntentCompat;
import com.bumptech.glide.request.RequestListener;
import com.dotscreen.farkit.design.DesignManager;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.model.ApplicationWidget;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.dotscreen.tv.ui.ImageLoaderManager;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HeroZoneAppCardView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0017\u001a\u00020\u0018H\u0014J\b\u0010\u0019\u001a\u00020\u0018H\u0014J\u000e\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001cR\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/dotscreen/portal/ui/cards/HeroZoneAppCardView;", "Landroidx/cardview/widget/CardView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "TAG", "", "kotlin.jvm.PlatformType", "appImg", "Landroid/widget/ImageView;", "getAppImg", "()Landroid/widget/ImageView;", "setAppImg", "(Landroid/widget/ImageView;)V", "attachedToWindow", "", "mInstallerCallback", "Landroid/content/pm/PackageInstaller$SessionCallback;", "sessionId", "onAttachedToWindow", "", "onDetachedFromWindow", "setItem", "widget", "Lcom/dotscreen/portal/model/ApplicationWidget;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class HeroZoneAppCardView extends CardView {
    private final String TAG;
    private HashMap _$_findViewCache;
    private ImageView appImg;
    private boolean attachedToWindow;
    private final PackageInstaller.SessionCallback mInstallerCallback;
    private final int sessionId;

    public HeroZoneAppCardView(Context context) {
        this(context, null, 0, 6, null);
    }

    public HeroZoneAppCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

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
    public HeroZoneAppCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = HeroZoneAppCardView.class.getSimpleName();
        this.sessionId = -1;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        Intrinsics.checkExpressionValueIsNotNull(layoutInflaterFrom, "LayoutInflater.from(getContext())");
        layoutInflaterFrom.inflate(R.layout.herozone_app_card_view, this);
        this.appImg = (ImageView) findViewById(R.id.image);
        setFocusable(true);
        setFocusableInTouchMode(true);
        Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        PortalApplication.Config value = ((PortalApplication) applicationContext).getConfig().getValue();
        if (value != null) {
            View viewFindViewById = findViewById(R.id.root);
            if (viewFindViewById != null) {
                DesignManager designManager = value.getDesignManager();
                Drawable foreground = viewFindViewById.getForeground();
                Intrinsics.checkExpressionValueIsNotNull(foreground, "it.foreground");
                viewFindViewById.setForeground(designManager.tintDrawable(foreground, "standard.item.selected.outline.color"));
            }
            Integer color = value.getDesignManager().getColor("standard.item.default.background.color");
            if (color == null) {
                Intrinsics.throwNpe();
            }
            setBackgroundColor(color.intValue());
        }
        this.mInstallerCallback = new PackageInstaller.SessionCallback() { // from class: com.dotscreen.portal.ui.cards.HeroZoneAppCardView$mInstallerCallback$1
            @Override // android.content.pm.PackageInstaller.SessionCallback
            public void onFinished(int sessionId, boolean success) {
            }

            @Override // android.content.pm.PackageInstaller.SessionCallback
            public void onCreated(int sid) {
                if (sid == this.this$0.sessionId) {
                    Log.d(this.this$0.TAG, "onCreated(" + sid + ')');
                }
            }

            @Override // android.content.pm.PackageInstaller.SessionCallback
            public void onBadgingChanged(int sid) {
                if (sid == this.this$0.sessionId) {
                    Log.d(this.this$0.TAG, "onBadgingChanged(" + sid + ')');
                }
            }

            @Override // android.content.pm.PackageInstaller.SessionCallback
            public void onActiveChanged(int sid, boolean active) {
                if (sid == this.this$0.sessionId) {
                    Log.d(this.this$0.TAG, "onActiveChanged(" + sid + ", " + active + ')');
                }
            }

            @Override // android.content.pm.PackageInstaller.SessionCallback
            public void onProgressChanged(int sid, float progress) {
                if (sid != this.this$0.sessionId) {
                    return;
                }
                Context context2 = this.this$0.getContext();
                Intrinsics.checkExpressionValueIsNotNull(context2, "getContext()");
                PackageManager packageManager = context2.getPackageManager();
                Intrinsics.checkExpressionValueIsNotNull(packageManager, "getContext().packageManager");
                PackageInstaller.SessionInfo sessionInfo = packageManager.getPackageInstaller().getSessionInfo(sid);
                Intrinsics.checkExpressionValueIsNotNull(sessionInfo, "getContext().packageMana…aller.getSessionInfo(sid)");
                Log.d(this.this$0.TAG, "bitmap = " + sessionInfo.getAppIcon());
                Log.d(this.this$0.TAG, "label = " + sessionInfo.getAppLabel());
                Log.d(this.this$0.TAG, "package = " + sessionInfo.getAppPackageName());
                try {
                    Object systemService = this.this$0.getContext().getSystemService("launcherapps");
                    if (systemService == null) {
                        throw new TypeCastException("null cannot be cast to non-null type android.content.pm.LauncherApps");
                    }
                    List<LauncherActivityInfo> activityList = ((LauncherApps) systemService).getActivityList(null, Binder.getCallingUserHandle());
                    Intrinsics.checkExpressionValueIsNotNull(activityList, "launcherApps.getActivity…r.getCallingUserHandle())");
                    for (LauncherActivityInfo launcherActivityInfo : activityList) {
                        Log.d(this.this$0.TAG, "activityInfo: " + launcherActivityInfo.getLabel().toString() + ", " + launcherActivityInfo.getComponentName().toString() + ", " + launcherActivityInfo.getIcon(CommonUtils.VIDEO_UNAVAILABLE_REASON_TCH_ACCESS_DENIED_GEO_BLACKOUT));
                    }
                    try {
                        Intent intentCreateDetailsIntent = sessionInfo.createDetailsIntent();
                        Intrinsics.checkExpressionValueIsNotNull(intentCreateDetailsIntent, "info.createDetailsIntent()");
                        Log.d(this.this$0.TAG, "detailsIntent -> " + intentCreateDetailsIntent);
                        Context context3 = this.this$0.getContext();
                        Intrinsics.checkExpressionValueIsNotNull(context3, "getContext()");
                        ResolveInfo resolveInfoResolveActivity = context3.getPackageManager().resolveActivity(intentCreateDetailsIntent, 131072);
                        Intrinsics.checkExpressionValueIsNotNull(resolveInfoResolveActivity, "getContext().packageMana…PackageManager.MATCH_ALL)");
                        Log.d(this.this$0.TAG, "resolveInfo -> " + resolveInfoResolveActivity);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Context context4 = this.this$0.getContext();
                    Intrinsics.checkExpressionValueIsNotNull(context4, "getContext()");
                    List<ResolveInfo> listQueryIntentActivities = context4.getPackageManager().queryIntentActivities(new Intent("android.intent.action.MAIN").addCategory(IntentCompat.CATEGORY_LEANBACK_LAUNCHER), 131072);
                    Intrinsics.checkExpressionValueIsNotNull(listQueryIntentActivities, "getContext().packageMana…PackageManager.MATCH_ALL)");
                    for (ResolveInfo resolveInfo : listQueryIntentActivities) {
                        String str = this.this$0.TAG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("resolveInfo: ");
                        sb.append(resolveInfo.resolvePackageName);
                        sb.append(", ");
                        Context context5 = this.this$0.getContext();
                        Intrinsics.checkExpressionValueIsNotNull(context5, "getContext()");
                        sb.append(resolveInfo.loadLabel(context5.getPackageManager()));
                        Log.d(str, sb.toString());
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        };
    }

    public /* synthetic */ HeroZoneAppCardView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? (AttributeSet) null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public final ImageView getAppImg() {
        return this.appImg;
    }

    public final void setAppImg(ImageView imageView) {
        this.appImg = imageView;
    }

    public final void setItem(ApplicationWidget widget) {
        Intrinsics.checkParameterIsNotNull(widget, "widget");
        Log.d(this.TAG, "setItem: " + widget);
        if (widget.getBannerUri() != null) {
            Context context = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            new ImageLoaderManager(context).loadImage(widget.getBannerUri(), (1016 & 2) != 0 ? (ImageView) null : this.appImg, (1016 & 4) != 0 ? false : true, (1016 & 8) != 0 ? 300 : 0, (1016 & 16) != 0 ? 0 : 0, (1016 & 32) != 0 ? 0 : 0, (1016 & 64) != 0 ? (ImageView.ScaleType) null : null, (1016 & 128) != 0 ? 0 : 0, (1016 & 256) == 0 ? 0 : 0, (1016 & 512) != 0 ? (RequestListener) null : null);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attachedToWindow = true;
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        PackageManager packageManager = context.getPackageManager();
        Intrinsics.checkExpressionValueIsNotNull(packageManager, "context.packageManager");
        PackageInstaller packageInstaller = packageManager.getPackageInstaller();
        Intrinsics.checkExpressionValueIsNotNull(packageInstaller, "context.packageManager.packageInstaller");
        packageInstaller.registerSessionCallback(this.mInstallerCallback);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        this.attachedToWindow = false;
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        PackageManager packageManager = context.getPackageManager();
        Intrinsics.checkExpressionValueIsNotNull(packageManager, "context.packageManager");
        PackageInstaller packageInstaller = packageManager.getPackageInstaller();
        Intrinsics.checkExpressionValueIsNotNull(packageInstaller, "context.packageManager.packageInstaller");
        packageInstaller.unregisterSessionCallback(this.mInstallerCallback);
        super.onDetachedFromWindow();
    }
}
