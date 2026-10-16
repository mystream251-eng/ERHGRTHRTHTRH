package com.dotscreen.portal.ui.cards;

import android.content.Context;
import android.content.Intent;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Binder;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.content.IntentCompat;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.dotscreen.farkit.design.DesignManager;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.model.ApplicationWidget;
import com.dotscreen.tv.common.utils.CommonUtils;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AppCardView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020!H\u0014J\b\u0010#\u001a\u00020!H\u0014J\u000e\u0010$\u001a\u00020!2\u0006\u0010%\u001a\u00020&R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\b\u0018\u00010\u0013R\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/dotscreen/portal/ui/cards/AppCardView;", "Landroidx/cardview/widget/CardView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "TAG", "", "kotlin.jvm.PlatformType", "appImg", "Landroid/widget/ImageView;", "getAppImg", "()Landroid/widget/ImageView;", "setAppImg", "(Landroid/widget/ImageView;)V", "appImgContainer", "Lcom/android/volley/toolbox/ImageLoader$ImageContainer;", "Lcom/android/volley/toolbox/ImageLoader;", "appName", "Landroid/widget/TextView;", "getAppName", "()Landroid/widget/TextView;", "setAppName", "(Landroid/widget/TextView;)V", "attachedToWindow", "", "mInstallerCallback", "Landroid/content/pm/PackageInstaller$SessionCallback;", "sessionId", "fadeInImg", "", "onAttachedToWindow", "onDetachedFromWindow", "setItem", "widget", "Lcom/dotscreen/portal/model/ApplicationWidget;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class AppCardView extends CardView {
    private final String TAG;
    private HashMap _$_findViewCache;
    private ImageView appImg;
    private ImageLoader.ImageContainer appImgContainer;
    private TextView appName;
    private boolean attachedToWindow;
    private final PackageInstaller.SessionCallback mInstallerCallback;
    private final int sessionId;

    public AppCardView(Context context) {
        this(context, null, 0, 6, null);
    }

    public AppCardView(Context context, AttributeSet attributeSet) {
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
    public AppCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = AppCardView.class.getSimpleName();
        this.sessionId = -1;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        Intrinsics.checkExpressionValueIsNotNull(layoutInflaterFrom, "LayoutInflater.from(getContext())");
        layoutInflaterFrom.inflate(R.layout.card_image, this);
        this.appImg = (ImageView) findViewById(R.id.image);
        TextView textView = (TextView) findViewById(R.id.title);
        this.appName = textView;
        if (textView == null) {
            Intrinsics.throwNpe();
        }
        textView.setVisibility(8);
        setFocusable(true);
        setFocusableInTouchMode(true);
        View viewFindViewById = findViewById(R.id.root);
        if (viewFindViewById != null) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
            }
            PortalApplication.Config value = ((PortalApplication) applicationContext).getConfig().getValue();
            if (value != null) {
                DesignManager designManager = value.getDesignManager();
                Drawable foreground = viewFindViewById.getForeground();
                Intrinsics.checkExpressionValueIsNotNull(foreground, "it.foreground");
                viewFindViewById.setForeground(designManager.tintDrawable(foreground, "standard.item.selected.outline.color"));
            }
        }
        setBackground((Drawable) null);
        this.mInstallerCallback = new PackageInstaller.SessionCallback() { // from class: com.dotscreen.portal.ui.cards.AppCardView$mInstallerCallback$1
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
                TextView appName = this.this$0.getAppName();
                if (appName == null) {
                    Intrinsics.throwNpe();
                }
                appName.setText("Install: " + (progress * 100) + '%');
                TextView appName2 = this.this$0.getAppName();
                if (appName2 == null) {
                    Intrinsics.throwNpe();
                }
                appName2.setVisibility(0);
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

    public /* synthetic */ AppCardView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? (AttributeSet) null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public final ImageView getAppImg() {
        return this.appImg;
    }

    public final void setAppImg(ImageView imageView) {
        this.appImg = imageView;
    }

    public final TextView getAppName() {
        return this.appName;
    }

    public final void setAppName(TextView textView) {
        this.appName = textView;
    }

    public final void setItem(final ApplicationWidget widget) {
        ImageLoader localImageLoader;
        Intrinsics.checkParameterIsNotNull(widget, "widget");
        TextView textView = this.appName;
        if (textView == null) {
            Intrinsics.throwNpe();
        }
        textView.setVisibility(8);
        try {
            String packageName = widget.getPackageName();
            Context context = getContext();
            if (context == null) {
                Intrinsics.throwNpe();
            }
            Drawable applicationBanner = context.getPackageManager().getApplicationBanner(packageName);
            if (applicationBanner == null && widget.getIntent() != null) {
                Context context2 = getContext();
                if (context2 == null) {
                    Intrinsics.throwNpe();
                }
                applicationBanner = context2.getPackageManager().getActivityBanner(widget.getIntent());
            }
            if (applicationBanner != null) {
                ImageView imageView = this.appImg;
                if (imageView == null) {
                    Intrinsics.throwNpe();
                }
                imageView.setImageDrawable(applicationBanner);
                return;
            }
            Log.w(this.TAG, "Can't find banner for package: " + packageName);
            ImageView imageView2 = this.appImg;
            if (imageView2 == null) {
                Intrinsics.throwNpe();
            }
            Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.ph_horizontal);
            if (drawable == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.graphics.drawable.BitmapDrawable");
            }
            imageView2.setImageBitmap(((BitmapDrawable) drawable).getBitmap());
            TextView textView2 = this.appName;
            if (textView2 == null) {
                Intrinsics.throwNpe();
            }
            textView2.setText(widget.getTitle());
            TextView textView3 = this.appName;
            if (textView3 == null) {
                Intrinsics.throwNpe();
            }
            textView3.setVisibility(0);
        } catch (PackageManager.NameNotFoundException unused) {
            if (widget.getBannerUri() != null) {
                if (StringsKt.startsWith$default(widget.getBannerUri(), "http", false, 2, (Object) null)) {
                    PortalApplication.Companion companion = PortalApplication.INSTANCE;
                    Context context3 = getContext();
                    Intrinsics.checkExpressionValueIsNotNull(context3, "context");
                    localImageLoader = companion.getSingleton(context3).getImageLoader();
                } else {
                    PortalApplication.Companion companion2 = PortalApplication.INSTANCE;
                    Context context4 = getContext();
                    Intrinsics.checkExpressionValueIsNotNull(context4, "context");
                    localImageLoader = companion2.getSingleton(context4).getLocalImageLoader();
                }
                this.appImgContainer = localImageLoader.get(widget.getBannerUri(), new ImageLoader.ImageListener() { // from class: com.dotscreen.portal.ui.cards.AppCardView.setItem.1
                    @Override // com.android.volley.toolbox.ImageLoader.ImageListener
                    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                        Intrinsics.checkParameterIsNotNull(response, "response");
                        ImageView appImg = AppCardView.this.getAppImg();
                        if (appImg == null) {
                            Intrinsics.throwNpe();
                        }
                        appImg.setImageBitmap(response.getBitmap());
                        TextView appName = AppCardView.this.getAppName();
                        if (appName == null) {
                            Intrinsics.throwNpe();
                        }
                        appName.setVisibility(8);
                        AppCardView.this.fadeInImg();
                    }

                    @Override // com.android.volley.Response.ErrorListener
                    public void onErrorResponse(VolleyError error) {
                        Intrinsics.checkParameterIsNotNull(error, "error");
                        error.printStackTrace();
                        ImageView appImg = AppCardView.this.getAppImg();
                        if (appImg == null) {
                            Intrinsics.throwNpe();
                        }
                        Drawable drawable2 = ContextCompat.getDrawable(AppCardView.this.getContext(), R.drawable.ph_horizontal);
                        if (drawable2 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type android.graphics.drawable.BitmapDrawable");
                        }
                        appImg.setImageBitmap(((BitmapDrawable) drawable2).getBitmap());
                        TextView appName = AppCardView.this.getAppName();
                        if (appName == null) {
                            Intrinsics.throwNpe();
                        }
                        appName.setText(widget.getTitle());
                        TextView appName2 = AppCardView.this.getAppName();
                        if (appName2 == null) {
                            Intrinsics.throwNpe();
                        }
                        appName2.setVisibility(0);
                    }
                });
                return;
            }
            if (widget.getBannerBitmap() != null) {
                ImageView imageView3 = this.appImg;
                if (imageView3 == null) {
                    Intrinsics.throwNpe();
                }
                imageView3.setImageBitmap(widget.getBannerBitmap());
                TextView textView4 = this.appName;
                if (textView4 == null) {
                    Intrinsics.throwNpe();
                }
                textView4.setVisibility(8);
                fadeInImg();
                return;
            }
            ImageView imageView4 = this.appImg;
            if (imageView4 == null) {
                Intrinsics.throwNpe();
            }
            Drawable drawable2 = ContextCompat.getDrawable(getContext(), R.drawable.ph_horizontal);
            if (drawable2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.graphics.drawable.BitmapDrawable");
            }
            imageView4.setImageBitmap(((BitmapDrawable) drawable2).getBitmap());
            TextView textView5 = this.appName;
            if (textView5 == null) {
                Intrinsics.throwNpe();
            }
            textView5.setText(widget.getTitle());
            TextView textView6 = this.appName;
            if (textView6 == null) {
                Intrinsics.throwNpe();
            }
            textView6.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void fadeInImg() {
        ImageView imageView = this.appImg;
        if (imageView == null) {
            Intrinsics.throwNpe();
        }
        imageView.setAlpha(0.0f);
        if (this.attachedToWindow) {
            ImageView imageView2 = this.appImg;
            if (imageView2 == null) {
                Intrinsics.throwNpe();
            }
            ViewPropertyAnimator viewPropertyAnimatorAlpha = imageView2.animate().alpha(1.0f);
            Intrinsics.checkExpressionValueIsNotNull(viewPropertyAnimatorAlpha, "appImg!!.animate()\n                    .alpha(1f)");
            viewPropertyAnimatorAlpha.setDuration(getResources().getInteger(android.R.integer.config_shortAnimTime));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attachedToWindow = true;
        ImageView imageView = this.appImg;
        if (imageView == null) {
            Intrinsics.throwNpe();
        }
        if (imageView.getAlpha() == 0.0f) {
            fadeInImg();
        }
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
        ImageView imageView = this.appImg;
        if (imageView == null) {
            Intrinsics.throwNpe();
        }
        imageView.animate().cancel();
        ImageView imageView2 = this.appImg;
        if (imageView2 == null) {
            Intrinsics.throwNpe();
        }
        imageView2.setAlpha(1.0f);
        ImageLoader.ImageContainer imageContainer = this.appImgContainer;
        if (imageContainer != null) {
            if (imageContainer == null) {
                Intrinsics.throwNpe();
            }
            imageContainer.cancelRequest();
        }
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
