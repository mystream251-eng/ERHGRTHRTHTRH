package com.dotscreen.portal.ui.cards;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.leanback.widget.BaseCardView;
import com.dotscreen.farkit.design.DesignManager;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.model.ApplicationWidget;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayStoreCardView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cR\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006\u001d"}, d2 = {"Lcom/dotscreen/portal/ui/cards/PlayStoreCardView;", "Landroidx/leanback/widget/BaseCardView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "TAG", "", "kotlin.jvm.PlatformType", "icon", "Landroid/widget/ImageView;", "getIcon", "()Landroid/widget/ImageView;", "setIcon", "(Landroid/widget/ImageView;)V", "sessionId", "title", "Landroid/widget/TextView;", "getTitle", "()Landroid/widget/TextView;", "setTitle", "(Landroid/widget/TextView;)V", "setItem", "", "widget", "Lcom/dotscreen/portal/model/ApplicationWidget;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class PlayStoreCardView extends BaseCardView {
    private final String TAG;
    private HashMap _$_findViewCache;
    private ImageView icon;
    private final int sessionId;
    private TextView title;

    public PlayStoreCardView(Context context) {
        this(context, null, 0, 6, null);
    }

    public PlayStoreCardView(Context context, AttributeSet attributeSet) {
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
    public PlayStoreCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = PlayStoreCardView.class.getSimpleName();
        this.sessionId = -1;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        Intrinsics.checkExpressionValueIsNotNull(layoutInflaterFrom, "LayoutInflater.from(getContext())");
        layoutInflaterFrom.inflate(R.layout.card_playstore, this);
        this.icon = (ImageView) findViewById(R.id.icon);
        this.title = (TextView) findViewById(R.id.title);
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
    }

    public /* synthetic */ PlayStoreCardView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? (AttributeSet) null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public final ImageView getIcon() {
        return this.icon;
    }

    public final void setIcon(ImageView imageView) {
        this.icon = imageView;
    }

    public final TextView getTitle() {
        return this.title;
    }

    public final void setTitle(TextView textView) {
        this.title = textView;
    }

    public final void setItem(ApplicationWidget widget) {
        Drawable activityIcon;
        PackageManager packageManager;
        PackageManager packageManager2;
        Intrinsics.checkParameterIsNotNull(widget, "widget");
        try {
            Context context = getContext();
            if (context == null || (packageManager2 = context.getPackageManager()) == null || (activityIcon = packageManager2.getApplicationIcon(widget.getPackageName())) == null) {
                Context context2 = getContext();
                activityIcon = (context2 == null || (packageManager = context2.getPackageManager()) == null) ? null : packageManager.getActivityIcon(widget.getIntent());
            }
            if (activityIcon != null) {
                ImageView imageView = this.icon;
                if (imageView != null) {
                    imageView.setImageDrawable(activityIcon);
                }
            } else {
                Log.w(this.TAG, "Can't find icon for package: " + widget.getPackageName());
            }
            TextView textView = this.title;
            if (textView != null) {
                textView.setText(widget.getTitle());
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }
}
